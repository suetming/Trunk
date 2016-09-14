/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.wechat.properties;

import net.luoteng.model.AbstractObject;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@ConfigurationProperties(prefix = "net.luoteng.wechat")
public class WechatProperties extends AbstractObject {

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
     * 商户密钥
     * 
     * @return
     */
    private String mchSecret;
    
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
     * 获取access token
     */
    private String uriAccessToken;

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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getUriPrepay() {
        return uriPrepay;
    }

    public void setUriPrepay(String uriPrepay) {
        this.uriPrepay = uriPrepay;
    }

    public String getUriNotify() {
        return uriNotify;
    }

    public void setUriNotify(String uriNotify) {
        this.uriNotify = uriNotify;
    }

    public String getUriNotifyReward() {
        return uriNotifyReward;
    }

    public void setUriNotifyReward(String uriNotifyReward) {
        this.uriNotifyReward = uriNotifyReward;
    }

    public String getUriNotifyDeposit() {
        return uriNotifyDeposit;
    }

    public void setUriNotifyDeposit(String uriNotifyDeposit) {
        this.uriNotifyDeposit = uriNotifyDeposit;
    }

    public String getUriOrder() {
        return uriOrder;
    }

    public void setUriOrder(String uriOrder) {
        this.uriOrder = uriOrder;
    }

    public String getUriUserInfo() {
        return uriUserInfo;
    }

    public void setUriUserInfo(String uriUserInfo) {
        this.uriUserInfo = uriUserInfo;
    }

    public String getUriSendMessage() {
        return uriSendMessage;
    }

    public void setUriSendMessage(String uriSendMessage) {
        this.uriSendMessage = uriSendMessage;
    }

    public String getUriSendTemplateMessage() {
        return uriSendTemplateMessage;
    }

    public void setUriSendTemplateMessage(String uriSendTemplateMessage) {
        this.uriSendTemplateMessage = uriSendTemplateMessage;
    }

    public String getUriCloseOrder() {
        return uriCloseOrder;
    }

    public void setUriCloseOrder(String uriCloseOrder) {
        this.uriCloseOrder = uriCloseOrder;
    }

    public String getUriPayCorp2User() {
        return uriPayCorp2User;
    }

    public void setUriPayCorp2User(String uriPayCorp2User) {
        this.uriPayCorp2User = uriPayCorp2User;
    }

    public String getUriRefund() {
        return uriRefund;
    }

    public void setUriRefund(String uriRefund) {
        this.uriRefund = uriRefund;
    }

    public String getPathLocalCert() {
        return pathLocalCert;
    }

    public void setPathLocalCert(String pathLocalCert) {
        this.pathLocalCert = pathLocalCert;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    public String getMchSecret() {
        return mchSecret;
    }

    public void setMchSecret(String mchSecret) {
        this.mchSecret = mchSecret;
    }

    public String getUriAccessToken() {
        return uriAccessToken;
    }

    public void setUriAccessToken(String uriAccessToken) {
        this.uriAccessToken = uriAccessToken;
    }
    
}
