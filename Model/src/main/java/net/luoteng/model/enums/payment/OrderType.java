/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.model.enums.payment;

import net.luoteng.model.enums.BaseEnum;

/**
 * order type
 * @author suetming <suetming.ma at gmail.com>
 */
public enum OrderType implements BaseEnum {

    /**
     * 学费
     */
    COURSE("学费"),
    
    /**
     * 导师将自己的收入提现到微信或支付宝
     */
    WITHDRAW("提现"),
    
    /**
     * 用户申请退款
     */
    REFUND("退款"),
    
    /**
     * 打赏
     */
    REWARD("打赏"),
    
    /**
     * 充值
     */
    DEPOSIT("充值"),
    
    ;
    
    private final String msg;

    private OrderType(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
    
}
