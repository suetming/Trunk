/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.entity.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.entity.constant.EntityConstant;
import net.luoteng.model.AbstractObject;

/**
 * tree path identification
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TreePathID extends AbstractObject implements EntityConstant {

    /**
     * 父节点
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false, length = ENTITY_LENGTH_UUID)
    private String ancestorId;
    
    /**
     * 子节点
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = false, length = ENTITY_LENGTH_UUID)
    private String descendantId;
    
    
}

