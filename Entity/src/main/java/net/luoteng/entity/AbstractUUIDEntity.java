/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import net.luoteng.AbstractObject;

/**
 * uuid entity
 * 
 * @author suetming <suetming.ma at creditcloud.com>
 */
@MappedSuperclass
public abstract class AbstractUUIDEntity extends AbstractObject {
    
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", unique = true)
    private String id;
    
}
