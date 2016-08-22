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
import net.luoteng.enums.PayType;
import net.luoteng.model.AbstractObject;

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
    @NotNull
    private PayType payType;
    
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
     */
    @Getter
    @Setter
    @Min(0)
    private int amount;
    
    @Getter
    @Setter
    private String outTradeNo;
    
    @Getter
    @Setter
    private String ip;
    
}
