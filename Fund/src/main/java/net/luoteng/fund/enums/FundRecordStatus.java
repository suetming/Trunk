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
 * 资金记录状态
 * 
 * @author suetming <suetming.ma at gmail.com>
 */
public enum FundRecordStatus implements BaseEnum {
    
    /**
     * 此状态的点师入账记录表示，用户即将支付，但还不明确用户是否支付成功
     */
    INITIALIZED("初始"),
    /**
     * 此状态的点师入账记录表示，用户支付成功，但资金还未到点师资金账户
     */
    PROCESSING("处理中"),
    SUCCESSFUL("成功"),
    FAILED("失败"),
    CANCELED("取消");

    private final String msg;
       
    FundRecordStatus(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
