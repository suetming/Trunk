/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model;

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
    private PayType payType;
    
    @Getter
    @Setter
    private String subject;
    
    @Getter
    @Setter
    private String body;
    
    @Getter
    @Setter
    private int amount;
    
    @Getter
    @Setter
    private String outTradeNo;
    
    @Getter
    @Setter
    private String ip;
    
}
