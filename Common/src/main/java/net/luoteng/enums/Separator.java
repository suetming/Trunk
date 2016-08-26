/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author suetming <suetming.ma at gmail.com>
 */
public enum Separator implements BaseEnum {
    
    DOT("."),
    COLON(":"),
    SEMICOLON(";")
    ;
    
    private final String msg;
    
    Separator(String msg) {
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
