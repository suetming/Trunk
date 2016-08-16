/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.model.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.model.AbstractObject;

/**
 * 
 *
 * @author mamian
 * @mail mamianskyma@aliyun.com
 * @date 2016-8-3 19:56:53
 * @copyright ©2016 马面 All Rights Reserved
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrepayResponse extends AbstractObject {

    /**
     * 返回状态码
     */
    private String return_code;
    
    /**
     * 返回信息
     */
    private String return_msg;
    
    /**
     * 公众账号ID
     */
    private String appid;
    
    /**
     * 商户号
     */
    private String mch_id;
    
    /**
     * 设备号
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
     * 业务结果
     */
    private String result_code;
    
    /**
     * 错误代码
     */
    private String err_code;
    
    /**
     * 错误代码描述
     */
    private String err_code_des;
    
    /**
     * 交易类型
     */
    private String trade_type;
    
    /**
     * 预支付交易会话标识
     */
    private String prepay_id;
    
    /**
     * 二维码链接
     */
    private String code_url;
    
    
    /**
     * 对预支付订单信息进行再签名
     */
    private String mySign;
    
    private String myTimestamp;
    
    private String myNoncestr;
    
}