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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.fund.enums.FundRecordOperation;

/**
 * 
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
     * 资金操作
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FundRecordOperation operat;
    
    
}
