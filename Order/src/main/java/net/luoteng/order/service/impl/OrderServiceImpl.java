/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.order.dao.OrderDAO;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import net.luoteng.order.service.OrderService;
import net.luoteng.order.utils.OrderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * order service
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Value("${net.luoteng.task.order.cancel.expire}")
    long expire;
    
    @Autowired
    OrderDAO orderDAO;
    
    @Override
    public String orderId() {
        return OrderGenerator.order();
    }
    
    @Override
    public Order getByUser(String userId, OrderType type, OrderStatus status, RealmEntity owner) {
        return orderDAO.getByUser(userId, type, status, owner);
    }

    @Override
    public Order generate(
            String userId, 
            RealmEntity owner, 
            OrderType type, 
            PayType payType, 
            long _expire, 
            long amount, 
            long couponAmount,
            long balanceAmount) {
        //撤销以前创建的所有预支付订单
        cancelPreOrders(userId, owner);
        if (_expire <= 0) {
            expire = _expire;
        }
        
        Order order = new Order(OrderGenerator.order(), userId, type, OrderStatus.INITIALIZED, payType, owner, amount, couponAmount, balanceAmount);
        order.setTimeExpired(new Date(System.currentTimeMillis() + expire));
        return orderDAO.save(order);
    }
    
    /**
     * 取消预支付订单
     * 
     * @param userId
     * @param payObj
     * @param payObjId
     */
    private void cancelPreOrders(String userId, RealmEntity owner){
        orderDAO.markStatus(userId, owner, OrderStatus.INITIALIZED, OrderStatus.CANCELED);
    }

    @Override
    public Page<Order> list(List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable) {
        return orderDAO.list(typeList, statusList, pageable);
    }
    
    @Override
    public Page<Order> listByUser(String userId, List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable) {
        return orderDAO.listByUser(userId, typeList, statusList, pageable);
    }

    @Override
    public boolean toSuccess(String id) {// TODO
        Order order = orderDAO.findOne(id);
        order.setStatus(OrderStatus.SUCCESSED);
        order.setTimeSuccessed(new Date());
        orderDAO.save(order);
        return true;
    }

    @Override
    public boolean toCancle(String id) {
        Order order = orderDAO.findOne(id);
        order.setStatus(OrderStatus.CANCELED);
        order.setTimeCaneled(new Date());
        orderDAO.save(order);
        return true;
    }
    
    @Override
    public boolean toExpire(String id) {
        Order order = orderDAO.findOne(id);
        order.setStatus(OrderStatus.EXPIRED);
        orderDAO.save(order);
        return true;
    }

    @Override
    public Order get(String orderId) {
        return orderDAO.findOne(orderId);
    }

}
