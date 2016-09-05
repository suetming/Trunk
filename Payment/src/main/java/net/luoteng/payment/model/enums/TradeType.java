/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model.enums;

import net.luoteng.enums.BaseEnum;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 */
public enum TradeType implements BaseEnum {
    
    /**
     * APP支付
     */
    APP,
    
    /**
     * 原生扫码支付
     */
    NATIVE,
    
    /**
     * 公众号支付
     */
    JSAPI;

    TradeType() {
    }
    
    @Override
    public String getMsg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
