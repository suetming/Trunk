/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.constant;

/**
 * 参考tweeter的snowflake
 * 
 * (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 *     注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id
 * (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface OrderConstant {

    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间
     */
    long ORDER_EPOCH = 1403854494756L;
    
    /**
     * 机器标识位数
     */
    long ORDER_BITS = 10L;
    
    /**
     * 机器ID最大值: 1023
     */
    long ORDER_MAX = -1L ^ -1L << ORDER_BITS;
    
    /**
     * 毫秒内自增位
     */
    long ORDER_SEQUENCE_BITS = 12L;

    /**
     * 12
     */
    long ORDER_SHIFT = ORDER_SEQUENCE_BITS; 
    
    /**
     * 222
     */
    long ORDER_TIMESTAMP_LEFT_SHIFT = ORDER_SEQUENCE_BITS + ORDER_BITS;
    
    /**
     * 
     */
    int ORDER_UNSAFE_WITH_TIMESTAMP = 1;
    int ORDER_UNSAFE_WITH_NUMERIC = 2;
    int ORDER_SAFE = 3;
    
    
    int ORDER_DEFAULT_LENGTH = 20;
}
