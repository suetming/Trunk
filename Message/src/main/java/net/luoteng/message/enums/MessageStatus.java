/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.message.enums;

import java.util.Arrays;
import java.util.List;
import net.luoteng.enums.BaseEnum;

/**
 * message status
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum MessageStatus implements BaseEnum {

    UNREAD("未读"),
    READ("已读"),
    ARCHIVED("已归档");
    
    private final String msg;

    MessageStatus(String msg) {
        this.msg = msg;
    }
    
    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public List toList() {
        return Arrays.asList(values());
    }

}
