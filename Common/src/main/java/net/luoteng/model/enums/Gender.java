/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.model.enums;

import lombok.Getter;

/**
 * gender
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum Gender implements BaseEnum{

    MALE("男"),
    FEMALE("女"),
    SECRET("保密");

    private Gender(String msg) {
        this.msg = msg;
    }

   
        
    @Getter
    private final String msg;
    
}
