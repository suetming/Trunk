/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * time scope entity
 * 
 * @author suetming <suetming.ma at creditcloud.com>
 */
@MappedSuperclass
public abstract class AbstractTimeScopeEntity extends AbstractUUIDEntity {

    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeCreated;

    @Getter
    @Setter
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeLastUpdated;

    @PrePersist
    private void setup() {
        Date date = new Date();
        this.timeCreated = date;
        this.timeLastUpdated = date;
    }

}
