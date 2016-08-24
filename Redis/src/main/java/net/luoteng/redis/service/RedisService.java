/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.redis.service;

import net.luoteng.enums.Realm;
import net.luoteng.model.common.RestResponse;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface RedisService {

    public RestResponse get(Realm realm, String key);
    
    public void set(Realm realm, String key, String value, int expire);
    
    public void set(Realm realm, String key, String value);
    
    public void set(Realm realm, String key, byte[] value);
    
    public void set(Realm realm, String key, byte[] value, int expire);
    
    public void delete(Realm realm, String key);
    
    public String key(Realm realm, String key);
    
}
