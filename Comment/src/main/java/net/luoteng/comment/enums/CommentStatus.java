/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.comment.enums;

import net.luoteng.model.enums.BaseEnum;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 */
public enum CommentStatus implements BaseEnum {

    /**
     * 评论都是公开的，一经发表所有人可见<p>
     * 所以NEW和READ都是针对评论对象实体owner的状态,如贷款的申请人<p>
     * 作为未来可能的扩展,评论内容支持@someone功能，那么NEW和READ就是针对someone<p>
     */
    NEW("新建"),
    
    /**
     * 已批准
     */
    APPROVED("已批准"),
    
    /**
     * 已驳回
     */
    REJECTED("已驳回"),
    
    /**
     * 已读
     */
    READ("已读"),
    
    /**
     * 被投诉
     */
    BEEN_COMPLAINTS("被投诉"), 
    
    /**
     * 已删除
     */
    DELETED("已删除");

    private final String msg;

    private CommentStatus(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
