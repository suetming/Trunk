/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.order.dao;

import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.order.entity.Order;
import net.luoteng.order.enums.OrderStatus;
import net.luoteng.order.enums.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Transactional
public interface OrderDAO extends PagingAndSortingRepository<Order, String> {

    /**
     * 查找订单
     * 
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    @Query("select o from Order o where o.type in :typeList and o.status in :statusList")
    Page<Order> list(List<OrderType> typeList, List<OrderStatus> statusList, Pageable pageable);
    
    /**
     * 根据用户查找订单
     *
     * @param userId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return
     */
    @Query("select o from Order o where o.userId = :userId and o.type in :typeList and o.status in :statusList")
    Page<Order> listByUser(@Param("userId") String userId, @Param("typeList") List<OrderType> typeList, @Param("statusList") List<OrderStatus> statusList, Pageable pageable);

    /**
     * 获取订单
     *
     * @param userId
     * @param type
     * @param status
     * @param entity
     * @return
     */
    @Query("select o from Order o where o.userId = :userId and o.type = :type and o.status = :status and o.owner= :owner")
    Order getByUser(@Param("userId") String userId, @Param("type") OrderType type, @Param("status") OrderStatus status, @Param("owner") RealmEntity entity);

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
    Page<Order> listByUserTypeStatus(@Param("userId") String userId, @Param("typeList") List<OrderType> typeList, @Param("statusList") List<OrderStatus> statusList, Pageable pageable);

    /**
     * 更新订单状态
     *
     * @param userId
     * @param owner
     * @param preStatus
     * @param aftStatus
     */
    @Modifying
    @Query("update Order o set o.status = :aftStatus where o.userId = :userId and o.owner = :owner and o.status=:preStatus")
    void markStatus(@Param("userId") String userId, @Param("owner") RealmEntity owner, @Param("preStatus") OrderStatus preStatus, @Param("aftStatus") OrderStatus aftStatus);

    /**
     * 更新订单状态
     *
     * @param id
     * @param status
     */
    @Modifying
    @Query("update Order o set o.status = :status where o.id=:id and o.status=:status")
    void markStatus(@Param("id") String id, @Param("status") OrderStatus status);

}
