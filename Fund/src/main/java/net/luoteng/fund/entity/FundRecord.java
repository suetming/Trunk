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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.fund.enums.FundRecordOperation;
import net.luoteng.fund.enums.FundRecordStatus;

/**
 * 资金记录
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_fund_record")
public class FundRecord extends AbstractTimeScopeEntity {

    /**
     * 用户ID
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private String userId;
    
    /**
     * 关联对象
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private RealmEntity owner;
    
    /**
     * 资金操作
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FundRecordOperation operat;
    
    
    /**
     * 本资金记录与哪个订单有关(1个订单可能有多笔资金记录)
     * order.orderId
     * 
     * @return
     */
    @Getter
    @Setter
    private String orderId;
    
    /**
     * 支付方式(如三方支出、余额收入)
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType payType;
    
    
    /**
     * 金额(可能与order的金额不一致：如order的收益分给多个用户)
     * 
     * @return
     */
    @Getter
    @Setter
    private long amount;
    
    /**
     * 特定资金记录特定字段
     */
    @Getter
    @Setter
    @Lob
    @Column(nullable = true)
    private String priv;
    
}
