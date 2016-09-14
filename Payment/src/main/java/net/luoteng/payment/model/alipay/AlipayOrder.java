/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model.alipay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.luoteng.payment.model.Response;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@AllArgsConstructor
@NoArgsConstructor
public class AlipayOrder extends Response {

    /**
     * 签约合作者身份ID
     */
    @Getter
    @Setter
    private String partner;
    
    /**
     * 签约卖家支付宝账号
     */
    @Getter
    @Setter
    private String seller_id;
    
    /**
     * 商户网站唯一订单号
     */
    @Getter
    @Setter
    private String out_trade_no;
    
    /**
     * 商品名称
     */
    @Getter
    @Setter
    private String subject;
    
    /**
     * 商品详情
     */
    @Getter
    @Setter
    private String body;
    
    /**
     * 商品金额
     */
    @Getter
    @Setter
    private String total_fee;
    
    /**
     * 服务器异步通知页面路径
     */
    @Getter
    @Setter
    private String notify_url;
    
    /**
     * 服务器同步跳转页面路径
     */
    @Getter
    @Setter
    private String return_url;
    
    /**
     * 服务接口名称
     */
    @Getter
    @Setter
    private String service;
    
    /**
     * 支付类型
     */
    @Getter
    @Setter
    private String payment_type;
    
    /**
     * 参数编码
     */
    @Getter
    @Setter
    private String _input_charset;
    
    /**
     * 设置未付款交易的超时时间默认30分钟，
     * 一旦超时，该笔交易就会自动被关闭取值范围：
     * 1m～15d。m-分钟，h-小时，
     * 该参数数值不接受小数点，如1.5h，可转换为90m。
     */
    @Getter
    @Setter
    private String it_b_pay;

    /**
     * 扫码支付的方式，支持前置模式和跳转模式。
     */
    @Getter
    @Setter
    private String qr_pay_mode;
    
} 