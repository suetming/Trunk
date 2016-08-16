/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.model.wechat;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.luoteng.model.AbstractObject;
import net.luoteng.utils.MD5Util;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Data
@AllArgsConstructor
public class Order extends AbstractObject {

    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号 非必填
     */
    private String device_info;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商品详情 非必填
     */
    private String detail;

    /**
     * 附加数据（原样返回） 非必填
     */
    private String attach;

    /**
     * 商户网站唯一订单号
     */
    private String out_trade_no;

    /**
     * 货币类型，非必填，默认“CNY”
     */
    private String fee_type;

    /**
     * 总金额
     */
    private Integer total_fee;

    /**
     * 终端ip
     */
    private String spbill_create_ip;

    /**
     * 交易起始时间 非必填
     */
    private String time_start;

    /**
     * 交易结束时间 非必填
     */
    private String time_expire;

    /**
     * 商品标记 非必填
     */
    private String goods_tag;

    /**
     * 异步通知地址
     */
    private String notify_url;

    /**
     * 交易类型：JSAPI(公众号支付)，NATIVE(原生扫码支付)，APP(app支付)
     */
    private String trade_type;

    /**
     * 商品id，商户平台自定义 非必填
     */
    private String product_id;

    /**
     * 指定支付方式,非必填 no_credit--指定不能使用信用卡支付
     */
    private String limit_pay;

    /**
     * 用户在商户appid下的唯一标识 非必填
     */
    private String openid;

    public Order(String userId,
            String trade_Type,
            String appid,
            String mchId,
            String body,
            String detail,
            String out_trade_no,
            Integer total_fee,
            String spbill_create_ip,
            String notifyUrl) {
        this.appid = appid;
        this.mch_id = mchId;
        this.nonce_str = new Date().getTime() + "";
        this.body = body;
        this.detail = detail;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = String.format(notifyUrl, userId);
        this.trade_type = trade_Type;
        this.openid = userId;
    }

    public Order(String userId,
            String openId,
            String trade_Type,
            String appid,
            String mchId,
            String body,
            String detail,
            String out_trade_no,
            Integer total_fee,
            String spbill_create_ip,
            String notifyUrl) {
        this.appid = appid;
        this.mch_id = mchId;
        this.nonce_str = new Date().getTime() + "";
        this.body = body;
        this.detail = detail;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = String.format(notifyUrl, userId);
        this.trade_type = trade_Type;
        this.openid = openId;
    }

    /**
     * 创建微信订单信息
     *
     * @param order
     * @return
     */
    private String orderInfo(boolean withOpenId) throws UnsupportedEncodingException {

        // 参数编码， 固定值
        String orderInfo = "appid=" + this.appid;

        // 签约合作者身份ID
        orderInfo += "&body=" + this.body;

        // 商品详情
        orderInfo += "&mch_id=" + this.mch_id;

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&nonce_str=" + this.nonce_str;

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + this.notify_url;

        // openId
        if (withOpenId) {
            orderInfo += "&openid=" + this.openid;
        }

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + this.out_trade_no;

        // 支付类型， 固定值
        orderInfo += "&spbill_create_ip=" + this.spbill_create_ip;

        // 商品金额
        orderInfo += "&total_fee=" + this.total_fee;

        // 签约卖家支付宝账号
        orderInfo += "&trade_type=" + this.trade_type;
        return orderInfo;
    }

    private String sign(String appKey, boolean withOpenId) throws UnsupportedEncodingException {
        String temp = this.orderInfo(withOpenId);
        temp += "&key=" + appKey;
        String result = MD5Util.MD5Encode(temp, "UTF-8").toUpperCase();
        return result;
    }

    /**
     * 创建微信订单信息
     *
     * @param appKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public String orderInfoXML(String appKey, boolean withOpenId) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer("<xml>\r\n");

        sb.append("<appid>" + appid + "</appid>" + "\r\n");
        sb.append("<body>" + body + "</body>" + "\r\n");
        sb.append("<mch_id>" + mch_id + "</mch_id>" + "\r\n");
        sb.append("<nonce_str>" + nonce_str + "</nonce_str>" + "\r\n");
        sb.append("<notify_url>" + notify_url + "</notify_url>" + "\r\n");
        if (withOpenId) {
            sb.append("<openid>" + openid + "</openid>" + "\r\n");
        }
        sb.append("<out_trade_no>" + out_trade_no + "</out_trade_no>" + "\r\n");
        sb.append("<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" + "\r\n");
        sb.append("<total_fee>" + total_fee + "</total_fee>" + "\r\n");
        sb.append("<trade_type>" + trade_type + "</trade_type>" + "\r\n");
        sb.append("<sign>" + this.sign(appKey, withOpenId) + "</sign>" + "\r\n");

        sb.append("</xml>");

        return sb.toString();
    }

}
