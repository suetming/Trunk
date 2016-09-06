/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.model.AbstractObject;
import net.luoteng.payment.model.enums.TradeType;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest extends AbstractObject {

    @Getter
    @Setter
    private RealmEntity entity;
    
    @Getter
    @Setter
    @NotNull
    private PayType payType;
    
    @Getter
    @Setter
    @NotNull
    private TradeType tradeType;
    
    @Getter
    @Setter
    @NotNull
    private String subject;
    
    @Getter
    @Setter
    @NotNull
    private String body;
    
    /**
     * 通过支付渠道支付的金额 单位 分
     * 
     * @return
     */
    @Getter
    @Setter
    @Min(0)
    private int amount;
    
    /**
     * 奖券金额
     * 
     * @return
     */
    @Getter
    @Setter
    @Min(0)
    private long couponAmount;
    
    /**
     * 资金账户余额
     * 
     * @return
     */
    @Getter
    @Setter
    @Min(0)
    private long balanceAmount;
                       
    /**
     * 第三方交易号
     * 
     * @return
     */
    @Getter
    @Setter
    private String outTradeNo;
    
    /**
     * 有效期
     */
    @Getter
    @Setter
    private long expire;
    
    /**
     * 
     */
    @Getter
    @Setter
    private String ip;
    
}
