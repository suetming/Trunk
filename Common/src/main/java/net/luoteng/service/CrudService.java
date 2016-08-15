/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.service;

import net.luoteng.model.AbstractObject;

/**
 * 
 * @param <T>
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface CrudService<T extends AbstractObject> extends BaseService {

    /**
     * save entity
     * @param t
     * @return 
     */
    T save(T t);
    
    /**
     * remove entity
     * 
     * @param id 
     */
    void delete(String id);
    
    /**
     * get entity
     * 
     * @param id
     * @return 
     */
    T get(String id);
}
