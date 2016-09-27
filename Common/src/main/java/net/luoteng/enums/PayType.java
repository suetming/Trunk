/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.enums;

import java.util.Arrays;
import java.util.List;


/**
 * payment type
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum PayType implements BaseEnum {

    /**
     * 支付宝
     */
    alipay("支付宝"),
    
    /**
     * 微信
     */
    wechat("微信支付"),
    
    /**
     * 贝宝
     */
    paypal("贝宝"),
    
    /**
     * 新浪
     */
    sina("新浪支付"),
    
    /**
     * 银联
     */
    unionpay("银联支付"),
    
    /**
     * 仅使用红包支付
     */
    coupon("红包"),
    
    /**
     * 仅使用“钱包”余额支付
     */
    balance("余额"),
    
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
