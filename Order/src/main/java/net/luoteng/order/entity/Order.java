/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;

/**
 * 订单 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order", uniqueConstraints = {
      @UniqueConstraint(columnNames = {"userId", "type", "status"})
})
public class Order extends AbstractTimeScopeEntity {

    /**
     * 本平台生成的订单号
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private String orderID;
    
    /**
     * 三方生成的订单号
     * 
     * @return
     */
    @Getter
    @Setter
    private String outOrderId;
    
    /**
     * 订单发起人
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private String userId;
    
    /**
     * 订单类型
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;
    
    /**
     * 订单状态
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    
    /**
     * 支付方式
     * 
     * @return
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private PayType payType;
    
    /**
     * 为哪个对象发起支付
     * 
     * @return
     */
    @Getter
    @Setter
    @AttributeOverrides({
        @AttributeOverride(name = "realm", column =
                @Column(name = "owner_realm")),
        @AttributeOverride(name = "entityId", column =
                @Column(name = "owner_entity_id"))
    })
    private RealmEntity owner;
    
    /**
     * 订单成功时间
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSuccessed;
    
    /**
     * 订单失败时间
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFailed;
    
    /**
     * 订单取消时间
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCaneled;
    
    /**
     * 备注
     * 
     * @return
     */
    @Getter
    @Setter
    private String remark;
    
    /**
     * 三方支付返回的数据
     * 
     * @return
     */
    @Getter
    @Setter
    @Lob
    private String priv;
    
}
