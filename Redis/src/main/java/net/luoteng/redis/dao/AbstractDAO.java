/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.redis.dao;

import javax.annotation.Resource;
import net.luoteng.model.AbstractObject;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * @param <T>
 * @param <ID> 
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public abstract class AbstractDAO<T extends AbstractObject, ID extends AbstractObject> implements CrudDAO<T, ID> {

    @Resource(name = "")
    protected StringRedisTemplate redisTemplate;
    
    
}
