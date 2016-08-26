/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.service;

import java.util.List;
import net.luoteng.message.entity.Message;
import net.luoteng.message.enums.MessageStatus;
import net.luoteng.message.enums.MessageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface MessageService {

    /**
     * 
     * @param userId
     * @param types
     * @param statuses
     * @param pageable
     * @return 
     */
    Page<Message> listByUser(String userId, List<MessageType> types, List<MessageStatus> statuses, Pageable pageable);
    
    /**
     * 
     * @param types
     * @param statuses
     * @param pageable
     * @return 
     */
    Page<Message> list(List<MessageType> types, List<MessageStatus> statuses, Pageable pageable);
 
    /**
     * 已读消息
     * 
     * @param messageIds 
     */
    void read(List<String> messageIds);
    
    /**
     * 创建消息
     * 
     * @param message 
     */
    void create(Message message);
}
