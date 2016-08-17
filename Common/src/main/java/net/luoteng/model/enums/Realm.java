/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.model.enums;

/**
 * realm
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public enum Realm implements BaseEnum {
    /**
     * 基本类型
     */
    USER("用戶"),
    EMPLOYEE("员工"),
    ROLE("角色"),
    USERGROUP("用户组"),
    
    /**
     * 资金业务类型
     */
    FUND("资金"),
    WITHDRAW("取现"),
    REFUND_REQUEST("退款申请"),
    
    /**
     * 教育业务类型
     */
    COURSE("课程"),
    COURSE_TAG("课程类别"),
    TEACHER("老师"),
    
    /**
     * 其他
     */
    IMAGE("图片"),
    FILE("文件"),
    COUPON("奖券"),
    
    /**
     * 订单
     */
    ORDER("订单"),
    ;
    
    private final String msg;

    private Realm(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
