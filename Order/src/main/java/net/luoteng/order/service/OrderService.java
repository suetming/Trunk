/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.service;

import java.awt.print.Pageable;
import java.util.List;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import net.luoteng.service.CrudService;
import org.springframework.data.domain.Page;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface OrderService extends CrudService<Order>{

    /**
     * 获取订单
     * 
     * @param userId
     * @param type
     * @param status
     * @param owner
     * @return 
     */
    public Order getByUser(String userId, OrderType type, OrderStatus status, RealmEntity owner);
    
    /**
     * 根据用户查找订单
     * 
     * @param userId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    public Page<Order> listByUserTypeStatus(String userId, List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable);
    
    /**
     * 取消预支付订单
     * 
     * @param userId
     * @param owner
     * @param status 
     */
    public void markStatus(String userId, RealmEntity owner, OrderStatus status);
    
}
