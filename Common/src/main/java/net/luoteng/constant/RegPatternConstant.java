/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.constant;

/**
 * regular expressions constant
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface RegPatternConstant {

    /**
     * 密码的强度必须是包含大小写字母和数字的组合，不能使用特殊字符，长度在n1-n2之间
     */
    String REG_PASSWORD = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{%1$d,%2$d}$";
    
    /**
     * 字符串仅能是中文
     */
    String REG_CHINESE = "^[\\\\u4e00-\\\\u9fa5]{0,}$";
    
    /**
     * 由数字、26个英文字母或下划线组成的字符串
     */
    String REG_WORD = "^\\\\w+$";
    
    /**
     * 校验E-Mail地址合规性
     */
    String REG_EMAIL = "[\\\\w!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[\\\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\\\w](?:[\\\\w-]*[\\\\w])?\\\\.)+[\\\\w](?:[\\\\w-]*[\\\\w])?";

    /**
     * 身份证号码的正则校验 15位
     */
    String REG_ID_CARD_15 = "^[1-9]\\\\d{7}((0\\\\d)|(1[0-2]))(([0|1|2]\\\\d)|3[0-1])\\\\d{3}$";
    
    /**
     * 身份证号码的正则校验 18位
     */
    String REG_ID_CARD_18 = "^[1-9]\\\\d{5}[1-9]\\\\d{3}((0\\\\d)|(1[0-2]))(([0|1|2]\\\\d)|3[0-1])\\\\d{3}([0-9]|X)$";
    
    /**
     * “yyyy-mm-dd“ 格式的日期校验，已考虑平闰年
     */
    String REG_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    
    /**
     * 金额校验，精确到2位小数
     */
    String REG_AMOUNT = "^[0-9]+(.[0-9]{2})?$";
    
    /**
     * 国内 13、15、18开头的手机号
     */
    String REG_MOBILE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\\\d{8}$";
}
