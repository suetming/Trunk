/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.dao;

import java.awt.print.Pageable;
import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Transactional
public interface OrderDAO extends PagingAndSortingRepository<Order, String> {

    /**
     * 获取订单
     * @param userId
     * @param type
     * @param status
     * @param entity
     * @return 
     */
    @Query("select o from Order o where o.userId = :userId and o.type = :type and o.status = :status and o.owner= :owner")
    public Order getByUser(@Param("userId") String userId,
                           @Param("type") OrderType type,
                           @Param("status") OrderStatus status,
                           @Param("owner") RealmEntity entity);
    
    /**
     * 根据用户查找订单
     * 
     * @param userId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    @Query("select o from Order o where o.userId = :userId and o.type in :typeList and o.status in :statusList order by o.timeCreated desc")
    public Page<Order> listByUserTypeStatus(@Param("userId") String userId,
                                            @Param("typeList") List<OrderType> typeList,
                                            @Param("statusList") List<OrderStatus> statusList,
                                            Pageable pageable);
    
    /**
     * 取消预支付订单
     * 
     * @param userId
     * @param owner
     * @param status
     */
//    @Modifying
//    @Query("update Order o set o.status = :status where o.userId = :userId and o.owner = :owner")
//    public void markStatus(@Param("userId") String userId, @Param("owner") RealmEntity owner, @Param("status") OrderStatus status);
    
}
