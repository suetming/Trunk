/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.entity.embedded;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.model.AbstractObject;
import net.luoteng.model.enums.Realm;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RealmEntity extends AbstractObject {

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Realm realm;

    @Getter
    @Setter
    @Column(nullable = true)
    private String entityId;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.realm);
        hash = 73 * hash + Objects.hashCode(this.entityId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final RealmEntity other = (RealmEntity) obj;
        if (this.realm != other.realm) {
            return false;
        }
        
        return Objects.equals(this.entityId, other.entityId);
    }

    @Override
    public String toString() {
        if (realm != null && entityId != null) {
            return realm + ":" + entityId;
        }
        return "null";
    }
    
    /**
     * 构造函数
     * @param realm
     * @param entityId
     * @return 
     */
    public static RealmEntity of(Realm realm, String entityId) {
        return new RealmEntity(realm, entityId);
    }
    
}
