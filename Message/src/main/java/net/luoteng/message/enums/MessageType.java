/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.enums;

import net.luoteng.enums.BaseEnum;

/**
 * message status
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum MessageType implements BaseEnum {

    NOTIFICATION("通知"),
    MESSAGE("私信"),
    SMS("短信");
    
    private final String msg;

    MessageType(String msg) {
        this.msg = msg;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }

}
