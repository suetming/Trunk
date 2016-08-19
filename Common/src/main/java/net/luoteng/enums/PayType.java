/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.enums;


/**
 * payment type
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum PayType implements BaseEnum {

    /**
     * 支付宝
     */
    Alipay("支付宝"),
    
    /**
     * 微信
     */
    Wechat("微信支付"),
    
    /**
     * 贝宝
     */
    Paypal("贝宝"),
    
    /**
     * 新浪
     */
    Sina("新浪支付"),
    
    /**
     * 仅使用红包支付
     */
    Coupon("红包"),
    
    /**
     * 仅使用“钱包”余额支付
     */
    Balance("余额"),
    
    ;
    
    private final String msg;

    private PayType(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
    
}
