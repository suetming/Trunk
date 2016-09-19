/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.service.impl;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import net.luoteng.model.common.RestResponse;
import net.luoteng.payment.model.OrderRequest;
import net.luoteng.payment.model.Response;
import net.luoteng.payment.model.wechat.PaymentResponse;
import net.luoteng.payment.model.wechat.WechatOrder;
import net.luoteng.payment.service.PaymentService;
import net.luoteng.utils.MD5Utils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.constant.GlobalConstant;
import net.luoteng.constant.TimeConstant;
import net.luoteng.enums.PayType;
import net.luoteng.enums.SignType;
import net.luoteng.payment.model.alipay.AlipayOrder;
import net.luoteng.payment.model.alipay.AlipayOrderQueryRequest;
import net.luoteng.payment.model.alipay.AlipayOrderQueryResponse;
import net.luoteng.payment.model.enums.alipay.Service;
import net.luoteng.payment.model.wechat.WechatOrderQueryRequest;
import net.luoteng.payment.properties.AlipayProperties;
import net.luoteng.payment.utils.SignUtils;
import net.luoteng.utils.EnumUtils;
import net.luoteng.utils.FormUtils;
import net.luoteng.utils.XMLUtils;
import net.luoteng.wechat.enums.TradeType;
import static net.luoteng.wechat.enums.TradeType.APP;
import static net.luoteng.wechat.enums.TradeType.JSAPI;
import static net.luoteng.wechat.enums.TradeType.NATIVE;
import net.luoteng.wechat.properties.WechatNativeProperties;
import net.luoteng.wechat.properties.WechatProperties;
import net.luoteng.wechat.properties.WechatPublicProperties;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * payment service
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Slf4j
@Component
@Transactional
public class PaymentServiceImpl implements PaymentService, TimeConstant, GlobalConstant {

    @Autowired
    AlipayProperties alipayConfig;

    @Autowired
    WechatProperties wechatConfig;

    @Autowired
    WechatPublicProperties wechatPublicConfig;

    @Autowired
    WechatNativeProperties wechatNativeConfig;

    OkHttpClient client;

    /**
     * 微信企业向用户转账，需加载证书
     */
    OkHttpClient weixinClient;

    @PostConstruct
    public void init() {
        log.info("payment service init");

        client = new OkHttpClient();
        try (FileInputStream instream = new FileInputStream(new File(wechatConfig.getPathLocalCert()))) {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            keyStore.load(instream, wechatConfig.getCertPassword().toCharArray());

            Set<KeyManager> keymanagers = new LinkedHashSet();
            KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmfactory.init(keyStore, wechatConfig.getCertPassword().toCharArray());
            KeyManager[] kms = kmfactory.getKeyManagers();
            if (kms != null) {
                keymanagers.addAll(Arrays.asList(kms));
            }

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(!keymanagers.isEmpty() ? (KeyManager[]) keymanagers.toArray(new KeyManager[keymanagers.size()]) : null,
                    null,
                    new SecureRandom());

            weixinClient = new OkHttpClient.Builder().sslSocketFactory(sslcontext.getSocketFactory()).build();

            log.info("payment service init successed");
        } catch (KeyStoreException | FileNotFoundException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            log.error("payment service  something is wrong when load weixin cert {}", ex);
        } catch (IOException | java.security.cert.CertificateException ex) {
            log.error("payment service  is wrong when load weixin cert {}", ex);
        }
    }

    @Override
    public RestResponse preOrders(String userId, OrderRequest request) {
        RestResponse response = new RestResponse();
        switch (request.getPayType()) {
            case alipay:
                return response.success(preAlipayOrders(userId, request));
            case wechat:
                return response.success(preWechatOrders(userId, request));
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public RestResponse query(String orderId, PayType payType) {
        RestResponse response = new RestResponse();
        // 订单
        switch (payType) {
            case wechat:
                response.success(queryWechatOrder(orderId));
                break;
            case alipay:
                response.success(queryAlipayOrder(orderId));
                break;
        }

        return response;
    }

    @Override
    public boolean verifyNotify(Response response) {
        log.debug("verify WeixinNotify response=[{}]", response);

        if (response instanceof net.luoteng.payment.model.alipay.PaymentNotifyResponse) {

        } else if (response instanceof net.luoteng.payment.model.wechat.NotifyResponse) {
            return verifyWechatNotify((net.luoteng.payment.model.wechat.NotifyResponse) response);
        }
        return false;
    }

    private boolean verifyWechatNotify(net.luoteng.payment.model.wechat.NotifyResponse response) {// TODO 待优化代码
        String verifyStr = "";
        if (StringUtils.isNotBlank(response.getAppid())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "appid=" + response.getAppid();
        }
        if (StringUtils.isNotBlank(response.getAttach())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "attach=" + response.getAttach();
        }
        if (StringUtils.isNotBlank(response.getBank_type())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "bank_type=" + response.getBank_type();
        }
        if (null != response.getCash_fee()) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "cash_fee=" + response.getCash_fee();
        }
        if (StringUtils.isNotBlank(response.getCash_fee_type())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "cash_fee_type=" + response.getCash_fee_type();
        }
        if (null != response.getCoupon_count()) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "coupon_count=" + response.getCoupon_count();
        }
        if (null != response.getCoupon_fee()) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "coupon_fee=" + response.getCoupon_fee();
        }
        if (StringUtils.isNotBlank(response.getCoupon_fee_0())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "coupon_fee_0=" + response.getCoupon_fee_0();
        }
        if (StringUtils.isNotBlank(response.getCoupon_id_0())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "coupon_id_0=" + response.getCoupon_id_0();
        }
        if (StringUtils.isNotBlank(response.getDevice_info())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "device_info=" + response.getDevice_info();
        }
        if (StringUtils.isNotBlank(response.getErr_code())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "err_code=" + response.getErr_code();
        }
        if (StringUtils.isNotBlank(response.getErr_code_des())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "err_code_des=" + response.getErr_code_des();
        }
        if (StringUtils.isNotBlank(response.getFee_type())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "fee_type=" + response.getFee_type();
        }
        if (StringUtils.isNotBlank(response.getIs_subscribe())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "is_subscribe=" + response.getIs_subscribe();
        }
        if (StringUtils.isNotBlank(response.getMch_id())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "mch_id=" + response.getMch_id();
        }
        if (StringUtils.isNotBlank(response.getNonce_str())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "nonce_str=" + response.getNonce_str();
        }
        if (StringUtils.isNotBlank(response.getOpenid())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "openid=" + response.getOpenid();
        }
        if (StringUtils.isNotBlank(response.getOut_trade_no())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "out_trade_no=" + response.getOut_trade_no();
        }
        if (StringUtils.isNotBlank(response.getResult_code())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "result_code=" + response.getResult_code();
        }
        if (StringUtils.isNotBlank(response.getReturn_code())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "return_code=" + response.getReturn_code();
        }
        if (StringUtils.isNotBlank(response.getReturn_msg())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "return_msg=" + response.getReturn_msg();
        }
        if (StringUtils.isNotBlank(response.getTime_end())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "time_end=" + response.getTime_end();
        }
        if (null != response.getTotal_fee()) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "total_fee=" + response.getTotal_fee();
        }
        if (StringUtils.isNotBlank(response.getTrade_type())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "trade_type=" + response.getTrade_type();
        }
        if (StringUtils.isNotBlank(response.getTransaction_id())) {
            verifyStr += verifyStr.equals("") ? "" : "&";
            verifyStr += "transaction_id=" + response.getTransaction_id();
        }

        verifyStr += "&key=" + wechatAppSecret(EnumUtils.getEnumByNameOrNull(TradeType.class, response.getTrade_type()));
        String mySign = MD5Utils.MD5Encode(verifyStr, "UTF-8").toUpperCase();
        log.debug("verify WechatNotify, mySign=[{}] sign=[{}]", mySign, response.getSign());
        return mySign.equals(response.getSign());
    }

    private String preAlipayOrders(String userId, OrderRequest request) {
        try {
            AlipayOrder order = new AlipayOrder(
                    alipayConfig.getPartner(),
                    alipayConfig.getPartner(),
                    request.getOutTradeNo(),
                    request.getSubject(),
                    null != request.getBody() && request.getBody().length() > 150 ? request.getBody().substring(0, 150) : request.getBody(),
                    String.format("%.2f", request.getAmount() / 100.0),
                    String.format(alipayConfig.getUriNotify(), userId, request.getEntity().getRealm().name(), request.getEntity().getEntityId()),
                    StringUtils.isBlank(request.getReturnUrl()) ? alipayConfig.getUriReturn() : request.getReturnUrl(),
                    Service.CREATE_DIRECT_PAY_BY_USER.getMsg(),
                    "1",
                    GLOBAL_ENCODING,
                    String.format("%dm", request.getExpire() / 60000), null, request.getPriv(), null, null);
            // 订单
            String form = FormUtils.toForm(order, true);

            // 对订单做RSA 签名
            order.setSign(SignUtils.sign(form, alipayConfig.getPks8PrivateKey(), SignType.SHA1_WITH_RSA));
            order.setSign_type("RSA");

            // 完整的符合支付宝参数规范的订单信息
            return FormUtils.toFormUrlEncode(order);

        } catch (UnsupportedEncodingException ex) {
            log.error("pre alipay order error {}", ex);
        }

        return null;
    }

    private PaymentResponse preWechatOrders(String userId, OrderRequest request) {
        WechatOrder order = new WechatOrder(userId,
                request.getTradeType().name(),
                wechatAppId(request.getTradeType()),
                wechatMchId(request.getTradeType()),
                null != request.getSubject() && request.getSubject().length() > 40 ? request.getSubject().substring(0, 40) : request.getSubject(),
                null,
                request.getOutTradeNo(),
                request.getAmount(),
                request.getIp(),
                String.format(wechatConfig.getUriNotify(), userId, request.getEntity().getRealm().name(), request.getEntity().getEntityId()));
        order.setAttach(request.getEntity().getEntityId());
        order.setTime_expire(new DateTime(Calendar.getInstance().getTimeInMillis() + request.getExpire()).toLocalDateTime().toString(TIME_SHORT));

        if (request.getTradeType() == TradeType.NATIVE) {
            order.setDevice_info("WEB");
            order.setSpbill_create_ip(wechatNativeConfig.getIpaddress());
        }

        order.setProduct_id(request.getEntity().getEntityId().replaceAll("-", ""));

        try {
            String orderInfo = order.orderInfoXML(wechatAppSecret(request.getTradeType()));
            return getResponse(wechatConfig.getUriPrepay(), orderInfo, true);
        } catch (UnsupportedEncodingException ex) {
            log.error("unsupported encoding exception userId:{}, request:{}", userId, request);
        }

        return null;
    }

    /**
     * 获取信息
     *
     * @param orderInfoXML
     * @param ios
     * @return
     */
    private PaymentResponse getResponse(String url, String orderInfoXML, boolean ios) {
        log.info("get wechat prepayid.orderInfoXML=[{}].", orderInfoXML);
        try {
            String response = post(url, orderInfoXML);
            PaymentResponse prepayResponse = XMLUtils.toObject(response, PaymentResponse.class);
            adjust(prepayResponse, ios);
            return prepayResponse;
        } catch (IOException | JAXBException ex) {
            log.error("weixin prepayid return bad response.", ex);
            return null;
        }
    }

    private String post(String url, String plain) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8;"), plain);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        String result = response.body().string();
        log.info("post url {}, plain {} result {}", url, plain, result);
        return result;
    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        String result = response.body().string();
        log.info("get url {}, plain {} result {}", url, result);
        return result;
    }

    private String wechatAppSecret(TradeType type) {
        switch (type) {
            case JSAPI:
                return wechatConfig.getMchSecret();
            case NATIVE:
                return wechatConfig.getMchSecret();
            case APP:
            default:
                return wechatConfig.getAppSecret();
        }
    }

    private String wechatAppId(TradeType type) {
        switch (type) {
            case JSAPI:
                return wechatPublicConfig.getAppId();
            case NATIVE:
                return wechatNativeConfig.getAppId();
            case APP:
            default:
                return wechatConfig.getAppId();
        }
    }

    private String wechatMchId(TradeType type) {
        switch (type) {
            case JSAPI:
                return wechatPublicConfig.getMchId();
            case NATIVE:
                return wechatPublicConfig.getMchId();
            case APP:
            default:
                return wechatConfig.getMchId();
        }
    }

    private void adjust(PaymentResponse response, boolean ios) {
        if (!response.isSuccess()) {
            return;
        }

        String pack = ios ? "&package=Sign=WXPay" : "&package=prepay_id=" + response.prepay_id;
        boolean platform = response.trade_type.contentEquals("JSAPI");

        String nonce = String.valueOf(System.currentTimeMillis());
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String toSign = "appId=" + response.appid
                + "&nonceStr=" + nonce
                + pack
                + (platform ? "" : "&partnerid=" + response.mch_id)
                + (platform ? "" : "&prepayid=" + response.prepay_id)
                + (platform ? "&signType=MD5" : "")
                + "&timeStamp=" + timestamp
                + "&key=" + wechatAppSecret(EnumUtils.getEnumByNameOrNull(TradeType.class, response.trade_type));
        String mySign = MD5Utils.MD5Encode(toSign, "UTF-8").toUpperCase();
        response.mySign = mySign;
        response.myTimestamp = String.valueOf(System.currentTimeMillis() / 1000);
        response.myNoncestr = nonce;
    }

    private PaymentResponse queryWechatOrder(String orderId) {
        try {
            WechatOrderQueryRequest request = new WechatOrderQueryRequest(
                    wechatAppId(TradeType.NATIVE),
                    wechatMchId(TradeType.NATIVE),
                    String.valueOf(System.currentTimeMillis()),
                    orderId);

            String form = String.format("%1$s&key=%2$s", FormUtils.toFormUrlEncode(request, true), wechatAppSecret(TradeType.NATIVE));

            request.setSign(MD5Utils.MD5Encode(form, "UTF-8").toUpperCase());

            return XMLUtils.toObject(post(wechatConfig.getUriOrder(), XMLUtils.toXML(request)), PaymentResponse.class);
        } catch (JAXBException | IOException ex) {
            log.error("query wechat order error {}", ex);
        }
        return null;
    }

    private AlipayOrderQueryResponse queryAlipayOrder(String orderId) {
        try {
            AlipayOrderQueryRequest request = new AlipayOrderQueryRequest(alipayConfig.getAppId(), orderId);
            String form = FormUtils.toForm(request,true);
            request.setSign(SignUtils.sign(form, alipayConfig.getPks8PrivateKey(), SignType.SHA1_WITH_RSA));
            
            String result = get(String.format("%1$s?%2$s", alipayConfig.getUriApiGateway(), FormUtils.toFormUrlEncode(request)));
            return JSON.parseObject(result, AlipayOrderQueryResponse.class);
        } catch (IOException ex) {
            log.error("query alipay order error {}", ex);
        }
        return null;
    }

}
