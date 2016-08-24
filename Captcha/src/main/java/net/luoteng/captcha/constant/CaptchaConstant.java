/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.constant;

/**
 * 
 * @author suetming <suetming.ma at creditcloud.com>
 */
public interface CaptchaConstant {

    /**
     * 图形验证码默认宽度
     */
    int CAPTCHA_WIDTH = 150;

    /**
     * 图形验证码默认高度
     */
    int CAPTCHA_HEIGHT = 50;
    
    /**
     * 验证码缓存个数大小
     */
    int CAPTCHA_CACHE_SIZE = 3000;
 
    /**
     * 图片验证码过期时间
     */
    int CAPTCHA_EXPIRES = 3 * 60; //超时时间3min
    
    
    String CAPTCHA_IMAGE_TYPE = "png";
    
    
    String CAPTCHA_COOKIE_NAME = "captcha";
    
}
