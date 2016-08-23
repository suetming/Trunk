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
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.payment.properties.AlipayProperties;
import net.luoteng.payment.properties.WechatProperties;
import net.luoteng.payment.properties.WechatPublicProperties;
import org.apache.commons.lang3.StringUtils;

/**
 * payment service
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
        log.info("payment service init {}, {}", alipayConfig.getAccountName(), wechatConfig.getPathLocalCert());
        
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
                return null;
            case wechat:
                return response.success(preWechatOrders(userId, request));
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public boolean verifyNotify(Response response) {
        log.debug("verify WeixinNotify response=[{}]", response);
        
        if (response instanceof net.luoteng.payment.model.alipay.NotifyResponse) {
            
        } else if (response instanceof net.luoteng.payment.model.wechat.NotifyResponse) {
            return verifyWechatNotify((net.luoteng.payment.model.wechat.NotifyResponse)response);
        }
        return false;
    }
    
    private boolean verifyWechatNotify(net.luoteng.payment.model.wechat.NotifyResponse response) {// TODO 待优化代码
        String verifyStr = "";
        if(StringUtils.isNotBlank(response.getAppid())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "appid="+response.getAppid();
        }
        if(StringUtils.isNotBlank(response.getAttach())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "attach="+response.getAttach();
        }
        if(StringUtils.isNotBlank(response.getBank_type())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "bank_type="+response.getBank_type();
        }
        if(null != response.getCash_fee()){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "cash_fee="+response.getCash_fee();
        }
        if(StringUtils.isNotBlank(response.getCash_fee_type())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "cash_fee_type="+response.getCash_fee_type();
        }
        if(null != response.getCoupon_count()){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "coupon_count="+response.getCoupon_count();
        }
        if(null != response.getCoupon_fee()){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "coupon_fee="+response.getCoupon_fee();
        }
        if(StringUtils.isNotBlank(response.getCoupon_fee_0())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "coupon_fee_0="+response.getCoupon_fee_0();
        }
        if(StringUtils.isNotBlank(response.getCoupon_id_0())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "coupon_id_0="+response.getCoupon_id_0();
        }
        if(StringUtils.isNotBlank(response.getDevice_info())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "device_info="+response.getDevice_info();
        }
        if(StringUtils.isNotBlank(response.getErr_code())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "err_code="+response.getErr_code();
        }
        if(StringUtils.isNotBlank(response.getErr_code_des())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "err_code_des="+response.getErr_code_des();
        }
        if(StringUtils.isNotBlank(response.getFee_type())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "fee_type="+response.getFee_type();
        }
        if(StringUtils.isNotBlank(response.getIs_subscribe())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "is_subscribe="+response.getIs_subscribe();
        }
        if(StringUtils.isNotBlank(response.getMch_id())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "mch_id="+response.getMch_id();
        }
        if(StringUtils.isNotBlank(response.getNonce_str())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "nonce_str="+response.getNonce_str();
        }
        if(StringUtils.isNotBlank(response.getOpenid())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "openid="+response.getOpenid();
        }
        if(StringUtils.isNotBlank(response.getOut_trade_no())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "out_trade_no="+response.getOut_trade_no();
        }
        if(StringUtils.isNotBlank(response.getResult_code())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "result_code="+response.getResult_code();
        }
        if(StringUtils.isNotBlank(response.getReturn_code())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "return_code="+response.getReturn_code();
        }
        if(StringUtils.isNotBlank(response.getReturn_msg())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "return_msg="+response.getReturn_msg();
        }
        if(StringUtils.isNotBlank(response.getTime_end())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "time_end="+response.getTime_end();
        }
        if(null != response.getTotal_fee()){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "total_fee="+response.getTotal_fee();
        }
        if(StringUtils.isNotBlank(response.getTrade_type())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "trade_type="+response.getTrade_type();
        }
        if(StringUtils.isNotBlank(response.getTransaction_id())){
            verifyStr += verifyStr.equals("") ? "": "&";
            verifyStr += "transaction_id="+response.getTransaction_id();
        }
        String key = "JSAPI".equals(response.getTrade_type()) ?  wechatPublicConfig.getAppSecret(): wechatConfig.getAppSecret();
        verifyStr += "&key="+ key;
        String mySign = MD5Util.MD5Encode(verifyStr, "UTF-8").toUpperCase();
        log.debug("verify WeixinNotify, mySign=[{}] sign=[{}]", mySign ,response.getSign());
        return mySign.equals(response.getSign());
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

    private PaymentResponse prepayResponse(String strxml, boolean ios, boolean publicPlatform) throws JDOMException, IOException {// TODO 待优化代码
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
}
