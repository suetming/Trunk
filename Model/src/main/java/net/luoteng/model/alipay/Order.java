/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.model.alipay;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.luoteng.model.AbstractObject;

/**
 * 订单
 *
 * @author mamian
 * @mail mamianskyma@aliyun.com
 * @date 2016-8-3 17:03:18
 * @copyright ©2016 马面 All Rights Reserved
 */
@Data
@AllArgsConstructor
public class Order extends AbstractObject {

    /**
     * 签约合作者身份ID
     */
    private String partner;
    
    /**
     * 签约卖家支付宝账号
     */
    private String seller_id;
    
    /**
     * 商户网站唯一订单号
     */
    private String out_trade_no;
    
    /**
     * 商品名称
     */
    private String subject;
    
    /**
     * 商品详情
     */
    private String body;
    
    /**
     * 商品金额
     */
    private String total_fee;
    
    /**
     * 服务器异步通知页面路径
     */
    private String notify_url;
    
    /**
     * 服务接口名称
     */
    private String service;
    
    /**
     * 支付类型
     */
    private String payment_type;
    
    /**
     * 参数编码
     */
    private String _input_charset;
    
    /**
     * 设置未付款交易的超时时间默认30分钟，
     * 一旦超时，该笔交易就会自动被关闭取值范围：
     * 1m～15d。m-分钟，h-小时，
     * 该参数数值不接受小数点，如1.5h，可转换为90m。
     */
    private String it_b_pay;
    
}
