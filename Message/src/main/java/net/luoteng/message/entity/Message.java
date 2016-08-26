/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.entity.AbstractTimeScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;
import net.luoteng.message.enums.MessageStatus;
import net.luoteng.message.enums.MessageType;

/**
 * message entity
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_message")
public class Message extends AbstractTimeScopeEntity {

    /**
     * 标题
     */
    private String title;
    
    /**
     * 正文
     */
    @Lob
    private String description;
    
    /**
     * 关联方
     */
    private RealmEntity owner;
    
    /**
     * 消息状态
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status;
    
    /**
     * 消息类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;
    
    /**
     * 消息子类型
     */
    @Column(nullable = false)
    private String subType;
    
    /**
     * 
     */
    @Lob
    private String priv;
    
}
