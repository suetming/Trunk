/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.fund.enums;

import java.util.Arrays;
import java.util.List;
import net.luoteng.enums.BaseEnum;

/**
 * 资金操作
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum FundRecordOperation implements BaseEnum {

    /**
     * 资金转入
     */
    IN("资金转入"),
    
    /**
     * 资金转出
     */
    OUT("资金转出"),
    
    /**
     * 冻结
     */
    FREEZE("冻结"),
    
    /**
     * 解冻
     */
    RELEASE("解冻"),
    
    ;

    private final String msg;

    private FundRecordOperation(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
