/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.service;

import java.util.List;
import net.luoteng.comment.entity.Comment;
import net.luoteng.comment.enums.CommentStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface CommentService {

    /**
     * list current comment all children
     * 
     * @param commentId
     * @param statusList
     * @param withSelf
     * @param pageable
     * @return 
     */
    public Page<Comment> listDescendant(String commentId, List<CommentStatus> statusList, boolean withSelf, Pageable pageable);
    
}
