/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.service.impl;

import java.util.List;
import net.luoteng.message.dao.MessageDAO;
import net.luoteng.message.entity.Message;
import net.luoteng.message.enums.MessageStatus;
import net.luoteng.message.enums.MessageType;
import net.luoteng.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDAO msgDAO;
    
    @Override
    public Page<Message> listByReceiver(String receiver, List<MessageType> types, List<MessageStatus> statuses, Pageable pageable) {
        return msgDAO.listByReceiver(receiver, types, statuses, pageable);
    }

    @Override
    public Page<Message> list(List<MessageType> types, List<MessageStatus> statuses, Pageable pageable) {
        return msgDAO.list(types, statuses, pageable);
    }

    @Override
    public void read(List<String> messageIds) {
        msgDAO.markStatus(messageIds, MessageStatus.READ);
    }
    
    @Override
    public void archive(List<String> messageIds) {
        msgDAO.markStatus(messageIds, MessageStatus.ARCHIVED);
    }
    
    @Override
    public Message create(Message message) {
        message.setStatus(MessageStatus.UNREAD);
        return msgDAO.save(message);
    }

}
