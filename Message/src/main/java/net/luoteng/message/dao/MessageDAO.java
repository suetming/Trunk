/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.dao;

import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.message.entity.Message;
import net.luoteng.message.enums.MessageStatus;
import net.luoteng.message.enums.MessageType;
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
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Transactional
public interface MessageDAO extends PagingAndSortingRepository<Message, String> {

    @Query("select m from Message m where m.receiver=:receiver and m.type in :typeList and m.status in :statusList")
    Page<Message> listByReceiver(@Param("receiver") String userId, @Param("typeList") List<MessageType> types, @Param("statusList") List<MessageStatus> statuses, Pageable pageable);

    @Query("select count(m) from Message m where m.receiver=:receiver and m.type in :typeList and m.status in :statusList")
    int countByReceiver(@Param("receiver") String userId, @Param("typeList") List<MessageType> types, @Param("statusList") List<MessageStatus> statuses);
    
    @Query("select m from Message m where m.type in :typeList and m.status in :statusList")
    Page<Message> list(@Param("typeList") List<MessageType> typeList, @Param("statusList") List<MessageStatus> statuses, Pageable pageable);

    @Query("select m from Message m where m.owner=:owner and m.type in :typeList and m.status in :statusList")
    Page<Message> listByOwner(@Param("owner") RealmEntity owner, @Param("typeList") List<MessageType> types, @Param("statusList") List<MessageStatus> statuses, Pageable pageable);
    
    @Modifying
    @Query("update Message m set m.status = :status where m.id in :idList")
    void markStatus(@Param("idList") List<String> messageIds, @Param("status") MessageStatus status);

    @Modifying
    @Query("update Message m set m.status = :status where m.owner = :owner")
    void markStatusByOwner(@Param("owner") RealmEntity owner, @Param("status") MessageStatus status);

}
