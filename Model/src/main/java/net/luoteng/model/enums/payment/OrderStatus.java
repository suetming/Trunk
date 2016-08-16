/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.model.enums.payment;

import net.luoteng.model.enums.BaseEnum;

/**
 * order status
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum OrderStatus implements BaseEnum {

    /**
     * 预支付
     */
    INITIAL("初始"),
    
    /**
     * 支付中
     */
    PROCESSING("处理中"),
    
    /**
     * 支付成功
     */
    SUCCESS("成功"),
    
    /**
     * 支付失败
     */
    FAIL("失败"),
    
    /**
     * 取消支付
     */
    CANCEL("取消"),
    
    ;
    
    private final String msg;

    private OrderStatus(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
    
}
