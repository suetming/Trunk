/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.service;

import java.util.List;
import net.luoteng.comment.entity.Comment;
import net.luoteng.comment.enums.CommentStatus;
import net.luoteng.comment.enums.CommentType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface CommentService {

    /**
     * list comment all children
     * 
     * @param commentId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    Page<Comment> listDescendant(String commentId, List<CommentType> typeList, List<CommentStatus> statusList, Pageable pageable);
 
    /**
     * list comment all ancestor
     * 
     * @param commentId
     * @param typeList
     * @param statusList
     * @param pageable
     * @return 
     */
    Page<Comment> listAncestor(String commentId, List<CommentType> typeList, List<CommentStatus> statusList, Pageable pageable);
    
    /**
     * 
     * 
     * @param sender
     * @param typeList
     * @param statusList
     * @return 
     */
    Page<Comment> listBySender(String sender, List<CommentType> typeList, List<CommentStatus> statusList);
    
    /**
     * 
     * @param receiver
     * @param typeList
     * @param statusList
     * @return 
     */
    Page<Comment> listByReceiver(String receiver, List<CommentType> typeList, List<CommentStatus> statusList);
    
    
}
