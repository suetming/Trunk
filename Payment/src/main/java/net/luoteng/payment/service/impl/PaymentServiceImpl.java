/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.transaction.Transactional;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.model.common.RestResponse;
import net.luoteng.payment.model.OrderRequest;
import net.luoteng.payment.model.Response;
import net.luoteng.payment.model.enums.TradeType;
import net.luoteng.payment.model.wechat.PaymentResponse;
import net.luoteng.payment.model.wechat.WechatOrder;
import net.luoteng.payment.service.PaymentService;
import net.luoteng.utils.MD5Util;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Slf4j
@Component
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    AlipayProperties alipayConfig;

    @Autowired
    WechatProperties wechatConfig;

    @Autowired
    WechatPublicProperties wechatPublicConfig;

    OkHttpClient client;

    /**
     * 微信企业向用户转账，需加载证书
     */
    OkHttpClient weixinClient;

    @PostConstruct
    public void init() {
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
        } catch (KeyStoreException | FileNotFoundException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            log.error("something is wrong when load weixin cert {}", ex);
        } catch (IOException | java.security.cert.CertificateException ex) {
            log.error("something is wrong when load weixin cert {}", ex);
        }
    }

    @Override
    public RestResponse preOrders(String userId, OrderRequest request) {
        RestResponse response = new RestResponse();
        switch (request.getPayType()) {
            case Alipay:
                return null;
            case Wechat:
                return response.success(preWechatOrders(userId, request));
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public boolean verifyNotify(Response response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PaymentResponse preWechatOrders(String userId, OrderRequest request) {
        WechatOrder order = new WechatOrder(userId,
                TradeType.APP.name(),
                wechatConfig.getAppId(),
                wechatConfig.getMchId(),
                request.getSubject(),
                null != request.getBody() && request.getBody().length() > 40 ? request.getBody().substring(0, 40) : request.getBody(),
                request.getOutTradeNo(),
                request.getAmount(),
                request.getIp(),
                wechatConfig.getUriNotify());

        try {
            String orderInfo = order.orderInfoXML(wechatConfig.getAppSecret(), false);
            return getResponse(orderInfo, true);
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
    private PaymentResponse getResponse(String orderInfoXML, boolean ios) {
        log.debug("get weixin prepayid.orderInfoXML=[{}].", orderInfoXML);
        try {
            String response = post(wechatConfig.getUriPrepay(), orderInfoXML);
            return prepayResponse(response, ios, false);
        } catch (JDOMException | IOException ex) {
            log.error("weixin prepayid return bad response.", ex);
            return null;
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8;"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private PaymentResponse prepayResponse(String strxml, boolean ios, boolean publicPlatform) throws JDOMException, IOException {
        PaymentResponse prepayResponse = new PaymentResponse();
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        org.jdom.Document doc = builder.build(in);
        List list = doc.getRootElement().getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = e.getTextNormalize();
            m.put(k, v);

            switch (k) {
                case "return_code":
                    prepayResponse.setReturn_code(e.getTextNormalize());
                    break;
                case "return_msg":
                    prepayResponse.setReturn_msg(e.getTextNormalize());
                    break;
                case "appid":
                    prepayResponse.setAppid(e.getTextNormalize());
                    break;
                case "mch_id":
                    prepayResponse.setMch_id(e.getTextNormalize());
                    break;
                case "device_info":
                    prepayResponse.setDevice_info(e.getTextNormalize());
                    break;
                case "nonce_str":
                    prepayResponse.setNonce_str(e.getTextNormalize());
                    break;
                case "sign":
                    prepayResponse.setSign(e.getTextNormalize());
                    break;
                case "result_code":
                    prepayResponse.setResult_code(e.getTextNormalize());
                    break;
                case "err_code":
                    prepayResponse.setErr_code(e.getTextNormalize());
                    break;
                case "err_code_des":
                    prepayResponse.setErr_code_des(e.getTextNormalize());
                    break;
                case "trade_type":
                    prepayResponse.setTrade_type(e.getTextNormalize());
                    break;
                case "prepay_id":
                    prepayResponse.setPrepay_id(e.getTextNormalize());
                    break;
                case "code_url":
                    prepayResponse.setCode_url(e.getTextNormalize());
                    break;
            }
        }

        String pack = ios ? "&package=Sign=WXPay" : "&package=prepay_id=" + prepayResponse.getPrepay_id();

        String myNoncestr = new Date().getTime() + "";
        String myTimestamp = new Date().getTime() / 1000 + "";
        String toSign = (publicPlatform ? "appId=" + prepayResponse.getAppid() : "appid=" + prepayResponse.getAppid())
                + (publicPlatform ? "&nonceStr=" + myNoncestr : "&noncestr=" + myNoncestr)
                + pack
                + (publicPlatform ? "" : "&partnerid=" + prepayResponse.getMch_id())
                + (publicPlatform ? "" : "&prepayid=" + prepayResponse.getPrepay_id())
                + (publicPlatform ? "&signType=MD5" : "")
                + (publicPlatform ? "&timeStamp=" + myTimestamp : "&timestamp=" + myTimestamp)
                + "&key=" + (publicPlatform ? wechatPublicConfig.getAppSecret() : wechatPublicConfig.getAppSecret());
        String mySign = MD5Util.MD5Encode(toSign, "UTF-8").toUpperCase();
        prepayResponse.setMySign(mySign);
        prepayResponse.setMyTimestamp(myTimestamp);
        prepayResponse.setMyNoncestr(myNoncestr);

        /**
         * 关闭流
         */
        in.close();
        log.debug("wechat public prepayResponse=[{}]", prepayResponse);
        return prepayResponse;
    }

    @Value
    @ConfigurationProperties(prefix = "net.luoteng.payment.wechat.public")
    public static class WechatPublicProperties {

        /**
         * 公众号APPID 微信开发平台应用id
         *
         * @return
         */
        private String appId;

        /**
         * @return
         */
        private String appSecret;

        /**
         * @return
         */
        private String appPublicSecret;

        /**
         * 微信支付商户号
         *
         * @return
         */
        private String mchId;

        /**
         * 链接超时限制
         * <p>
         * 毫秒数
         *
         * @return
         */
        private int connectionTimeout;

        /**
         * 读取数据超时限制
         * <p>
         * 毫秒数
         *
         * @return
         */
        private int readTimeout;

        /**
         * 预支付交易单调用地址
         *
         * @return
         */
        private String uriPrepay;

    }

    @Value
    @ConfigurationProperties(prefix = "net.luoteng.payment.wechat")
    public static class WechatProperties {

        /**
         * 公众号APPID 微信开发平台应用id
         *
         * @return
         */
        private String appId;

        /**
         * @return
         */
        private String appSecret;

        /**
         * 微信支付商户号
         *
         * @return
         */
        private String mchId;

        /**
         * 预支付交易单调用地址
         *
         * @return
         */
        private String uriPrepay;

        /**
         * 通知回调
         *
         * @return
         */
        private String uriNotify;

        /**
         * 打赏支付：通知回调
         *
         * @return
         */
        private String uriNotifyReward;

        /**
         * 充值回调
         *
         * @return
         */
        private String uriNotifyDeposit;

        /**
         * 查询订单状态 地址
         *
         * @return
         */
        private String uriOrder;

        /**
         * 获取用户信息
         *
         * @return
         */
        private String uriUserInfo;

        /**
         * 消息推送
         *
         * @return
         */
        private String uriSendMessage;

        /**
         * 模板消息推送
         *
         * @return
         */
        private String uriSendTemplateMessage;

        /**
         * 关闭订单 地址
         *
         * @return
         */
        private String uriCloseOrder;

        /**
         * 企业向用户付款 地址
         *
         * @return
         */
        private String uriPayCorp2User;

        /**
         * 平台向买家退款 地址
         *
         * @return
         */
        private String uriRefund;

        /**
         * HTTPS证书的本地路径(pkcs8格式)
         *
         * @return
         */
        private String pathLocalCert;

        /**
         * HTTPS证书密码，默认密码等于商户号MCHID
         *
         * @return
         */
        private String certPassword;
    }

    @Value
    @ConfigurationProperties(prefix = "net.luoteng.payment.alipay")
    public static class AlipayProperties {

        /**
         * 公司（付款方）的支付宝账户名
         *
         * @return
         */
        private String accountName;

        /**
         * 销售者ID
         *
         * @return
         */
        private String sellId;

        /**
         * 合作身份者ID， 以2088开头由16位纯数字组成的字符串
         *
         * @return
         */
        private String partner;

        /**
         * 商户的公钥
         *
         * @return
         */
        private String merchantPublicKey;

        /**
         * 合作伙伴密钥:支付宝的公钥，无需修改该值
         *
         * @return
         */
        private String alipayPublicKey;

        /**
         * 商户私钥，pkcs8格式
         *
         * @return
         */
        private String pks8PrivateKey;

        /**
         * 安全校验码
         *
         * @return
         */
        private String key;

        /**
         * 打赏支付：通知回调
         *
         * @return
         */
        private String uriNotifyReward;

        /**
         * 通知回调
         *
         * @return
         */
        private String uriNotify;

        /**
         * 充值回调
         *
         * @return
         */
        private String uriNotifyDeposit;

        /**
         * 退款通知回调
         *
         * @return
         */
        private String uriNotifyRefund;

        /**
         * 转账回调
         *
         * @return
         */
        private String uriNotifyTrans;

        /**
         * 支付网关
         *
         * @return
         */
        private String uriGateway;
    }

}
