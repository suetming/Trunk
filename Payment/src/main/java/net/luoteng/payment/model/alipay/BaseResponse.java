/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.payment.model.alipay;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.luoteng.payment.model.Response;

/**
 *
 *
 * @author mamian
 * @mail mamianskyma@aliyun.com
 * @date 2016-8-5 16:50:06
 * @copyright ©2016 马面 All Rights Reserved
 */
@Data
@NoArgsConstructor
public class BaseResponse extends Response {

    /**
     * 签名方式 固定取值为 RSA
     */
    @NotNull
    private String sign_type;

    /**
     * 签名
     */
    @NotNull
    private String sign;

}
