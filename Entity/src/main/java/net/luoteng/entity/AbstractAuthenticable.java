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
 * authenticable entity
 * 
 * @author suetming <suetming.ma at creditcloud.com>
 */
public abstract class AbstractAuthenticable extends AbstractTimeScopeEntity {

    /**
     * passphrase value
     */
    @Getter
    @Setter
    @Column(nullable = false, length = 40)
    protected String passphrase;

    /**
     * salt value in hex
     */
    @Getter
    @Setter
    @Column(nullable = false, length = 120)
    protected String salt;

    /**
     * The timestamp that last successfully login
     */
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastLoginDate;

    /**
     * The time that this guy registered, the value is immutable
     */
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    protected Date registerDate;

    /**
     * indicate whether the entity need to change its password on login
     */
    @Getter
    @Setter
    @Column(nullable = false)
    protected boolean needChangePassword;
    
    /**
     * 账户是否可以用.
     * 
     * 用于封禁账户或类似功能
     */
    @Getter
    @Setter
    @Column(nullable = false)
    protected boolean enabled;
    
    /**
     * Setup when the entity been created.
     *
     * We setup the registerDate here.
     */
    @PrePersist
    private void setup() {
        this.registerDate = new Date();
    }
    
}
