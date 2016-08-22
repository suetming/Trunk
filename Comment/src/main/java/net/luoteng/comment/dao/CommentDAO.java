/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.dao;

import java.util.List;
import net.luoteng.comment.entity.Comment;
import net.luoteng.comment.enums.CommentStatus;
import net.luoteng.entity.embedded.RealmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface CommentDAO extends PagingAndSortingRepository<Comment, String> {

    /**
     * 根据评论对应实体获取列表
     * 
     * @param entity
     * @param statusList
     * @param pageable
     * @return 
     */
    Page<Comment> listByOwner(RealmEntity entity, List<CommentStatus> statusList, Pageable pageable);
    
    
}
