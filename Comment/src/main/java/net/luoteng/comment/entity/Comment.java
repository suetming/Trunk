/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.comment.enums.CommentStatus;
import net.luoteng.comment.enums.CommentType;
import net.luoteng.entity.AbstractRecordScopeEntity;
import net.luoteng.entity.embedded.RealmEntity;

/**
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractRecordScopeEntity {

    /**
     * 此评论回复的其他评论id，从而支持评论的树状显示<p>
     * TODO 目前为简化流程，CommentService接口中暂不暴露此id
     */
    @Column(nullable = true)
    private String parentId;

    /**
     * 评论对应的实体
     */
    @AttributeOverrides({
        @AttributeOverride(name = "realm", column =
                                           @Column(name = "owner_realm")),
        @AttributeOverride(name = "entityId", column =
                                              @Column(name = "owner_id"))
    })
    @Column(nullable = false)
    private RealmEntity owner;

    /**
     * 评论内容
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 评论发送人
     */
    @Column(nullable = false)
    private String sender;

    /**
     * 评论接收人
     */
    @Column(nullable = false)
    private String receiver;

    /**
     * 评论状态
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentStatus status;
   
    /**
     * 评星
     * 
     * 1 = 1 1.5 = 3 2 = 4 2.5 = 5 3 = 6
     */
    private int starCount;
    
    /**
     * 评论类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CommentType type;
 
    /**
     * 额外数据
     */
    @Lob
    @Column(nullable = true, columnDefinition = "TEXT")
    private String priv;
    
}
