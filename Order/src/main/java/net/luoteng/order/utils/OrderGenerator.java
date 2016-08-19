/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.luoteng.order.constant.OrderConstant;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * order generator
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class OrderGenerator implements OrderConstant {

    private final static OrderGenerator GENERATOR = new OrderGenerator(1);
    
    private long lastTimestamp = -1L;
    
    private final long orderId;
    
    /**
     * 并发控制
     */
    long sequence = 0L;
    
    /**
     *  4095,111111111111,12位
     */
    long sequenceMask  = -1L ^ -1L << ORDER_SEQUENCE_BITS;  
    
    private OrderGenerator(long orderId) {
        if (orderId > ORDER_MAX || orderId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", ORDER_MAX));
        }
        this.orderId = orderId;
    }
    
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            throw new UnsupportedOperationException(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        return timestamp - ORDER_EPOCH << ORDER_TIMESTAMP_LEFT_SHIFT | this.orderId << ORDER_SHIFT | this.sequence;
    }
    
    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }
    
    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    
    /**
     * only ORDER_UNSAFE_WITH_NUMERIC length will work
     * 
     * @param type
     * @param length
     * @return
     */
    public static String order(int type, int length) {
        if (length < 10) 
            throw new IllegalArgumentException("order generator length must greater than 10");
        switch(type) {
            case ORDER_UNSAFE_WITH_NUMERIC:
                return RandomStringUtils.randomNumeric(length);
            case ORDER_UNSAFE_WITH_TIMESTAMP:
                return orderWithTimestamp(length);
            case ORDER_SAFE:
            default:
                return Long.toOctalString(GENERATOR.nextId());
        }
    }
    
    public static String order() {
        return order(ORDER_UNSAFE_WITH_TIMESTAMP, ORDER_DEFAULT_LENGTH);
    }
    
    public static String order(int type) {
        return order(type, ORDER_DEFAULT_LENGTH);
    }
    
    public static String orderWithTimestamp(int length) {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        return String.format("%1$s%2$s", format.format(new Date()), RandomStringUtils.randomNumeric(length - 10));
    }

}
