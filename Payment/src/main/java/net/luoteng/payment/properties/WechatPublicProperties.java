/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.payment.properties;

import net.luoteng.model.AbstractObject;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@ConfigurationProperties(prefix = "net.luoteng.payment.wechat.public")
public class WechatPublicProperties extends AbstractObject {

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppPublicSecret() {
        return appPublicSecret;
    }

    public void setAppPublicSecret(String appPublicSecret) {
        this.appPublicSecret = appPublicSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getUriPrepay() {
        return uriPrepay;
    }

    public void setUriPrepay(String uriPrepay) {
        this.uriPrepay = uriPrepay;
    }
    
    
}
