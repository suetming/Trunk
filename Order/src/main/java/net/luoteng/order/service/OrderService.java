/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.service;

import java.util.List;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import net.luoteng.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * order service
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface OrderService extends BaseService<Order> {

    /**
     * 获取所有订单
     * 
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    Page<Order> list(List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable);
    
    /**
     * 获取用户订单
     * 
     * @param userId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    Page<Order> listByUser(String userId, List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable);
    
    /**
     * 
     * @param orderId
     * @return 
     */
    Order get(String orderId);
    
    /**
     * 获取订单
     * 
     * @param userId
     * @param type
     * @param status
     * @param owner
     * @return 
     */
    Order getByUser(String userId, OrderType type, OrderStatus status, RealmEntity owner);
    
    /**
     * 预生成订单
     * 
     * @param userId
     * @param owner
     * @param outOrderId
     * @param type
     * @param payType
     * @param amount
     * @param couponAmount
     * @param balanceAmount
     * @return 
     */
    Order generate(
            String userId, 
            RealmEntity owner, 
            String outOrderId, 
            OrderType type, 
            PayType payType,
            long amount,
            long couponAmount,
            long balanceAmount
    );
    
    /**
     * 标记支付成功
     * 
     * @param id
     * @return 
     */
    boolean toSuccess(String id);
    
    /**
     * 标记取消支付
     * 
     * @param id
     * @return 
     */
    boolean toCancle(String id);
    
    /**
     * 标记已过期
     * 
     * @param id
     * @return 
     */
    boolean toExpire(String id);
}
