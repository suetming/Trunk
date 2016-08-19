/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.comment.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.comment.entity.embedded.TreePathID;
import net.luoteng.model.AbstractObject;

/**
 * Tree Closure
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Entity
@Table(name = "t_tree_path")
@NoArgsConstructor
@AllArgsConstructor
public class TreePath extends AbstractObject {

    @Getter
    @Setter
    @EmbeddedId
    private TreePathID treePathID;
    
    @Getter
    @Setter
    @MapsId("ancestorId")  
    @JoinColumn(name = "ancestor")  
    private Comment ancestorComment;
    
    @Getter
    @Setter
    @MapsId("descendantId")  
    @JoinColumn(name="descendant")  
    private Comment descendantComment;
    
    /**
     * 节点深度(自我引用为0)
     */
    @Getter
    @Setter
    private int pathLength;
    
}
