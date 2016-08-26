/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.service;

import net.luoteng.captcha.model.Captcha;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface CaptchaService {

    /**
     * <p>获取一个验证码
     * 
     * @param expire (秒)
     * @return 
     */
    Captcha random(int expire);
    
    /**
     * 获取验证码的答案
     * 
     * @param captchaId
     * @return 
     */
    String getAnswerById(String captchaId);
    
}
