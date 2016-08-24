/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.enums;

/**
 * response code
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 * 
 */
public enum ResponseCode implements BaseEnum {

    SUCCESS("成功", 0000),
    ERROR_UNKNOW("未知错误", 100),
    
    /**
     * 客户端错误
     */
    ERROR_TOKEN_INVALID("无效的TOKEN", 3000),
    ERROR_PARAM_INVALID("无效的参数", 4000),
    
    ERROR_USER_NO_PERMISSION("无操作权限", 4001),
    ERROR_USER_INVALID_PASSWORD("无效的用户密码", 4002),
    ERROR_USER_NOT_EXIST("用户不存在", 4003),
    ERROR_LOGINNAME_EXIST("用户名已存在", 4004),
    ERROR_USER_INVALID_LOGINNAME_OR_PASSWORD("无效的用户名或者密码", 4005),
    ERROR_EDUCATIONINFO_NO_EXIST("教育经历不存在", 4006),
    ERROR_NAME_IS_EMPTY("名字不能为空", 4007),
    
    /**
     * 手机验证码相关
     */
    ERROR_MOBILE_ALREADY_EXISTED("手机号已经存在", 4100),
    ERROR_MOBILE_CAPTCHA_NOT_RESEND_IN_SPECIFIC_TIME("手机验证码一分钟内不重复发送", 4101),
    ERROR_MOBILE_NOT_EXISTED("手机号不存在", 4102),
    ERROR_MOBILE_IS_EMPTY("手机号不能为空", 4103),
    ERROR_MOBILE_CAPTCHA_INVALID("手机验证码不存在或者已经失效", 4104),
    ERROR_MOBILE_CAPTCHA_EXPIRED("手机验证码已经过期", 4105),
    ERROR_MOBILE_ALREADY_BINDED("手机号已经绑定", 4106),
    ERROR_MOBILE_COUNTRY_CODE_NOT_EXISTED("国家代码手机号不存在", 4107),
    ERROR_MOBILE_NOT_DOMESTIC("仅限国内手机号", 4108),
    ERROR_MOBILE_CERTIFICATION_ALREADY_EXISTED("已经绑定", 4109),
    
    /**
     * 邮箱已经存在
     */
    ERROR_EMAIL_ALREADY_EXISTED("邮箱已经存在", 4200),
    ERROR_EMAIL_IS_EMPTY("邮箱不能为空", 4201),
    
    ERROR_ADDTEXTBOOK_EXIST("目标教材已存在", 5000),
    ERROR_ADDTEXTBOOK_NO_EXIST("教材不存在", 5001),
    ERROR_ASSGIN_NO_EXIST("指定关系不存在", 5002),
    ERROR_TEXTBOOK_NO_EXIST("教材不存在", 5003),
    ERROR_COURSEPRICEINFO_EXIST("同类型同课时的价格信息已添加", 5004),
    ERROR_COURSEPRICEINFO_NO_EXIST("价格信息不存在", 5005),
    ERROR_COURSE_NOT_EXIST("课程不存在", 5006),
    ERROR_COURSETAG_EXIST("当前添加分类已存在", 5007),
    ERROR_COURSE_TAG_NOT_EXIST("当前修改分类不存在", 5008),
    ERROR_TEACHERCOURSE_EXIST("目标课程已存在", 5009),
    ERROR_TEACHERCOURSE_NO_EXIST("课程不存在", 5010),
    ERROR_COURSEENROLL_EXIST("已报名该课程", 5011),
    ERROR_COURSEENROLL_NO_EXIST("报名不存在", 5012),
    ERROR_COURSESTATUS_NOT_UNPUBLISH("该课程未下架", 5013),
    ERROR_COURSE_IS_SHOW_MAIN("该课程处于首页展示状态", 5014),
    ERROR_COURSESTATUS_NOT_PUBLISH("该课程未上架", 5015),
    ERROR_COURSE_NOT_SHOW_MAIN("该课程未在首页展示", 5016),
    ERROR_COURSE_PATTERN_NOT_EXIST("课程套餐存在", 5017),
    ERROR_COURSE_PATTERN_EXIST("课程套餐已存在", 5018),
    
    /**
     * 其他情况
     */
    ERROR_CACHE_DATA_NOT_EXIST("缓存数据不存在", 6001),
    ERROR_CAPTCHA_EMPTY("验证码为空", 6002),
    ERROR_GRAPH_CAPTCHA_INVALID("验证码错误", 6003),
    //====================================================================================================================
    ;

    final private String msg;
    
    final private int code;
    
    ResponseCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
    
}
