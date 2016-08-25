/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.entity;

import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.message.enums.MessageStatus;
import net.luoteng.model.AbstractObject;

/**
 * message
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class Message extends AbstractObject {

    /**
     * 标题
     */
    private String title;
    
    /**
     * 正文
     */
    private String description;
    
    /**
     * 关联方
     */
    private RealmEntity owner;
    
    /**
     * 消息状态
     */
    private MessageStatus status;
    
    
}
