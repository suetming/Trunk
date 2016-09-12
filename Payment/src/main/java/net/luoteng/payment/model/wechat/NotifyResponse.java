/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.payment.model.wechat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.luoteng.payment.model.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class NotifyResponse extends Response {

    /**
     * 返回状态码
     */
    @Getter
    @XmlElement
    private String return_code;

    /**
     * 返回信息
     */
    @Getter
    @XmlElement
    private String return_msg;

    /**
     * 公众账号ID
     */
    @Getter
    @XmlElement
    private String appid;

    /**
     * 商户号
     */
    @Getter
    @XmlElement
    private String mch_id;

    /**
     * 设备号
     */
    @Getter
    @XmlElement
    private String device_info;

    /**
     * 随机字符串
     */
    @Getter
    @XmlElement
    private String nonce_str;

    /**
     * 签名
     */
    @Getter
    @XmlElement
    private String sign;

    /**
     * 业务结果
     */
    @Getter
    @XmlElement
    private String result_code;

    /**
     * 错误代码
     */
    @Getter
    @XmlElement
    private String err_code;

    /**
     * 错误代码描述
     */
    @Getter
    @XmlElement
    private String err_code_des;

    /**
     * 用户标识  用户在商户appid下的唯一标识
     */
    @Getter
    @XmlElement
    private String openid;

    /**
     * 是否关注公众账号  Y  N
     */
    @Getter
    @XmlElement
    private String is_subscribe;

    /**
     * 交易类型
     */
    @Getter
    @XmlElement
    private String trade_type;

    /**
     * 付款银行
     */
    @Getter
    @XmlElement
    private String bank_type;

    /**
     * 总金额 分
     */
    @Getter
    @XmlElement
    private Integer total_fee;

    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @Getter
    @XmlElement
    private Integer settlement_total_fee;
    
    /**
     * 货币种类 默认CNY
     */
    @Getter
    @XmlElement
    private String fee_type;

    /**
     * 现金支付金额
     */
    @Getter
    @XmlElement
    private Integer cash_fee;

    /**
     * 现金支付货币类型
     */
    @Getter
    @XmlElement
    private String cash_fee_type;

    /**
     * 代金券或立减优惠金额
     */
    @Getter
    @XmlElement
    private Integer coupon_fee;

    /**
     * 代金券或立减优惠使用数量
     */
    @Getter
    @XmlElement
    private Integer coupon_count;

    /**
     * 微信支付订单号
     */
    @Getter
    @XmlElement
    private String transaction_id;

    /**
     * 商户订单号
     */
    @Getter
    @XmlElement
    private String out_trade_no;

    /**
     * 商家数据包
     */
    @Getter
    @XmlElement
    private String attach;

    /**
     * 支付完成时间  20141030133525
     */
    @Getter
    @XmlElement
    private String time_end;

    /**
     * 代金券或立减优惠ID
     */
    @Getter
    @XmlElement
    private String coupon_id_0;

    /**
     * 单个代金券或立减优惠支付金额
     */
    @Getter
    @XmlElement
    private String coupon_fee_0;
    
    /**
     * 对当前查询订单状态的描述和下一步操作的指引
     */
    @Getter
    @XmlElement
    private String trade_state_desc;
    
    public boolean isSuccess(){
        return StringUtils.isNotBlank(return_code) && this.return_code.equals("SUCCESS") && this.result_code.equals("SUCCESS");
    }
    
    public String successResponse(){
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
    
    public String failResponse(String msg){
        return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+msg+"]]></return_msg></xml>";
    }
    
}
