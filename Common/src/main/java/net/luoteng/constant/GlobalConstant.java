/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.constant;

/**
 * global constant
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface GlobalConstant {

    /**
     * 
     */
    String GLOBAL_APPLICATION_JSON = "application/json; charset=utf-8";
    
    /**
     * 项目名称
     */
    String GLOBAL_NAME = "罗藤";
    
    /**
     * 全局申明
     */
    String GLOBAL_NAMESPACE = "luoteng";
    
    
    /**
     * 编码格式。发送编码格式统一用UTF-8
     */
    String GLOBAL_ENCODING = "utf-8";
    
    /**
     * 签名方式
     */
    String GLOBAL_SIGN_TYPE = "RSA";
    
    /**
     * 默认密码
     */
    String GLOBAL_PASSWORD = "Luoteng2016";
    
    /**
     * 过期时间
     */
    long GLOBAL_TIME_EXPIRED = 1000 * 60 * 60 * 72;
    
}
