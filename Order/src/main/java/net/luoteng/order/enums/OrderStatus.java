/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.enums;

import net.luoteng.enums.BaseEnum;

/**
 * order status
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum OrderStatus implements BaseEnum {

    /**
     * 预支付
     */
    INITIALIZED("初始化"),
    
    /**
     * 支付中
     */
    PROCESSING("处理中"),
    
    /**
     * 支付成功
     */
    SUCCESSED("成功"),
    
    /**
     * 支付失败
     */
    FAILED("失败"),
    
    /**
     * 取消支付
     */
    CANCELED("取消"),
    
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
