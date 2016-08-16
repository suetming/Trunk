/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.model.alipay;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包含支付宝返回值的基本参数
 *
 * @author mamian
 * @mail mamianskyma@aliyun.com
 * @date 2016-8-5 16:50:06
 * @copyright ©2016 马面 All Rights Reserved
 */
@Data
@XmlRootElement
@NoArgsConstructor
public class PaymentResponse extends BaseResponse {

    /**
     * 通知发送时间(格式为 yyyy-MM-dd HH:mm:ss)
     */
    @NotNull
    private String notify_time;

    /**
     * 通知类型
     */
    @NotNull
    private String notify_type;

    /**
     * 通知校验ID
     */
    @NotNull
    private String notify_id;
    
}
