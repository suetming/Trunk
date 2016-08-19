/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.payment.model.alipay;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class NotifyResponse extends BaseResponse {

    /**
     * 商户网站唯一订单号(64位，对应商户网站的订单系统中的唯一订单号,非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数,原样返回。)
     */
    private String out_trade_no;

    /**
     * 商品名称(128位，商品的标题/交易标题/订单标题 /订单关键字等。它在支付宝的交易明细中排在 第一列,对于财务对账尤为重 要。是请求时对应的参数,原样 通知回来)
     */
    private String subject;

    /**
     * 支付类型 4位    支付类型。默认值为:1(商品 购买)。
     */
    private String payment_type;

    /**
     * 支付宝交易号String(64 )该交易在支付宝系统中的交易流水号。最短16位,最长64位
     */
    private String trade_no;

    /**
     * 交易状态，如 TRADE_SUCCESS
     */
    private String trade_status;

    /**
     * 卖家支付宝 用户号String(30 )卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
     */
    private String seller_id;

    /**
     * 卖家支付宝 账号String(100)卖家支付宝账号,可以是 email 和手机号码。   jin@luoteng.net
     */
    private String seller_email;

    /**
     * 买家支付宝 用户号String(30 )买家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。
     */
    private String buyer_id;

    /**
     * 买家支付宝 账号String(100)买家支付宝账号,可以是 Email 或手机号码。
     */
    private String buyer_email;

    /**
     * 交易金额  该笔订单的总金额。请求时对应的参数,原样通知回 来。
     */
    private String total_fee;

    /**
     * 购买数量 购买数量,固定取值为 1(请求 时使用的是 total_fee)
     */
    private String quantity;

    /**
     * 商品单价price 等于 total_fee(请求时使 用的是 total_fee)
     */
    private String price;

    /**
     * 商品􏰀述String(512)该笔订单的备注、描述、明细等。对应请求时的 body 参数,原样 通知回来。
     */
    private String body;

    /**
     * 该笔交易创建的时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    private String gmt_create;

    /**
     * 该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    private String gmt_payment;

    /**
     * 该交易是否调整过价格  如 “Ｎ”
     */
    private String is_total_fee_adjust;

    /**
     * 是否在交易过程中使用了红包   如 “Ｎ”
     */
    private String use_coupon;

    /**
     * 折扣   支付宝系统会把 discount 的值 加到交易金额上,如果有折扣, 本参数为负数,单位为元
     */
    private String discount;

    /**
     * 退款状态 如  REFUND_SUCCESS
     */
    private String refund_status;

    /**
     * 卖家退款的时间,退款通知时会发送。格式为 yyyy-MM-dd HH:mm:ss。
     */
    private String gmt_refund;
}
