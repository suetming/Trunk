/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.enums.PayType;
import net.luoteng.fund.enums.FundRecordOperation;

/**
 * 资金记录
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_fund_record", uniqueConstraints = {
      @UniqueConstraint(columnNames = {"userId", "operat", "orderId", "payType"})
})
public class FundRecord extends AbstractTimeScopeEntity {

    /**
     * 用户ID
     * 
     * @return
     */
    @Column(nullable = false)
    private String userId;
    
    /**
     * 资金操作
     * 
     * @return
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FundRecordOperation operat;
    
    
    /**
     * 本资金记录与哪个订单有关(1个订单可能有多笔资金记录)
     * order.orderId
     * 
     * @return
     */
    @NotNull
    @Column(nullable = false)
    private String orderId;
    
    /**
     * 支付方式(如三方支出、余额收入)
     * 
     * @return
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType payType;
    
    
    /**
     * 金额(可能与order的金额不一致：如order的收益分给多个用户)
     * 
     * @return
     */
    private long amount;
    
    /**
     * 特定资金记录特定字段
     * 
     * @return
     */
    @Lob
    @Column(nullable = true)
    private String priv;

    public FundRecord(String userId, FundRecordOperation operat, String orderId, PayType payType, long amount) {
        this.userId = userId;
        this.operat = operat;
        this.orderId = orderId;
        this.payType = payType;
        this.amount = amount;
    }
    
}
