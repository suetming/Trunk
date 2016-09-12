package net.luoteng.payment.model.wechat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.luoteng.payment.model.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@XmlRootElement(name="xml")
public class PaymentResponse extends Response {

    /**
     * 返回状态码
     */
    @XmlElement
    public String return_code;
    
    /**
     * 返回信息
     */
    @XmlElement
    public String return_msg;
    
    /**
     * 公众账号ID
     */
    @XmlElement
    public String appid;
    
    /**
     * 商户号
     */
    @XmlElement
    public String mch_id;
    
    /**
     * 设备号
     */
    @XmlElement
    public String device_info;
    
    /**
     * 随机字符串
     */
    @XmlElement
    public String nonce_str;
    
    /**
     * 签名
     */
    @XmlElement
    public String sign;
    
    /**
     * 业务结果
     */
    @XmlElement
    public String result_code;
    
    /**
     * 错误代码
     */
    @XmlElement
    public String err_code;
    
    /**
     * 错误代码描述
     */
    @XmlElement
    public String err_code_des;
    
    /**
     * 交易类型
     */
    @XmlElement
    public String trade_type;
    
    /**
     * 预支付交易会话标识
     */
    @XmlElement
    public String prepay_id;
    
    /**
     * 二维码链接
     */
    @XmlElement
    public String code_url;
    
    
    /**
     * 对预支付订单信息进行再签名
     */
    public String mySign;
    
    /**
     * 
     */
    public String myTimestamp;
    
    /**
     * 
     */
    public String myNoncestr;
    
    
    public boolean isSuccess() {
        return StringUtils.isNoneBlank(return_code) && return_code.contentEquals("SUCCESS");
    }

}
