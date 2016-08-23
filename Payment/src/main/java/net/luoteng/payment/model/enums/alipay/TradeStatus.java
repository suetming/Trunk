/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model.enums.alipay;

import net.luoteng.enums.BaseEnum;

/**
 * alipay trade status
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum TradeStatus implements BaseEnum {

    TRADE_SUCCESS("交易成功"),
    
    TRADE_FINISHED("交易成功且结束"),
    /**
     * 未支付或已退款时关闭交易
     */
    TRADE_CLOSED("关闭交易"),
    /**
     * 交易创建,等待买家付款
     */
    WAIT_BUYER_PAY ("等待买家付款");

    private final String msg;

    private TradeStatus(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
