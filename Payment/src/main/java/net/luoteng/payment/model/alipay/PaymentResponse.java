/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.payment.model.alipay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@NoArgsConstructor
public class PaymentResponse extends BaseResponse {

    /**
     * 通知发送时间(格式为 yyyy-MM-dd HH:mm:ss)
     */
    @Getter
    @Setter
    protected String notify_time;

    /**
     * 通知类型
     */
    @Getter
    @Setter
    protected String notify_type;

    /**
     * 通知校验ID
     */
    @Getter
    @Setter
    protected String notify_id;
    
}
