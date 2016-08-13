/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * time record scope entity
 * @author suetming <suetming.ma at creditcloud.com>
 */
public abstract class AbstractRecordScopeEntity extends AbstractUUIDEntity {

    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeRecorded;

    @PrePersist
    private void setup() {
        Date date = new Date();
        this.timeRecorded = date;
    }

}
