/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.enums.PayType;
import net.luoteng.order.dao.OrderDAO;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import net.luoteng.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OrderDAO orderDAO;
    
    @Override
    public Order getByUser(String userId, OrderType type, OrderStatus status, RealmEntity owner) {
        return orderDAO.getByUser(userId, type, status, owner);
    }

    @Override
    public Order generate(String userId, RealmEntity owner, String outOrderId, OrderType type, PayType payType, long amount, long couponAmount, long balanceAmount) {
        //撤销以前创建的所有预支付订单
        cancelPreOrders(userId, owner);
        
        Order order = new Order(outOrderId, userId, type, OrderStatus.INITIALIZED, payType, owner, amount, couponAmount, balanceAmount);
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
    public boolean markSuccess(String id) {// TODO
        orderDAO.markStatus(id, OrderStatus.SUCCESSED);
        return true;
    }

}
