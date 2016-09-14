/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@ConfigurationProperties(prefix = "net.luoteng.payment.alipay")
public class AlipayProperties {

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
     * 同步通知
     * 
     * @return
     */
    private String uriReturn;
    
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

    
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getMerchantPublicKey() {
        return merchantPublicKey;
    }

    public void setMerchantPublicKey(String merchantPublicKey) {
        this.merchantPublicKey = merchantPublicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getPks8PrivateKey() {
        return pks8PrivateKey;
    }

    public void setPks8PrivateKey(String pks8PrivateKey) {
        this.pks8PrivateKey = pks8PrivateKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUriNotifyReward() {
        return uriNotifyReward;
    }

    public void setUriNotifyReward(String uriNotifyReward) {
        this.uriNotifyReward = uriNotifyReward;
    }

    public String getUriNotify() {
        return uriNotify;
    }

    public void setUriNotify(String uriNotify) {
        this.uriNotify = uriNotify;
    }

    public String getUriNotifyDeposit() {
        return uriNotifyDeposit;
    }

    public void setUriNotifyDeposit(String uriNotifyDeposit) {
        this.uriNotifyDeposit = uriNotifyDeposit;
    }

    public String getUriNotifyRefund() {
        return uriNotifyRefund;
    }

    public void setUriNotifyRefund(String uriNotifyRefund) {
        this.uriNotifyRefund = uriNotifyRefund;
    }

    public String getUriNotifyTrans() {
        return uriNotifyTrans;
    }

    public void setUriNotifyTrans(String uriNotifyTrans) {
        this.uriNotifyTrans = uriNotifyTrans;
    }

    public String getUriGateway() {
        return uriGateway;
    }

    public void setUriGateway(String uriGateway) {
        this.uriGateway = uriGateway;
    }

    public String getUriReturn() {
        return uriReturn;
    }

    public void setUriReturn(String uriReturn) {
        this.uriReturn = uriReturn;
    }
    
}
