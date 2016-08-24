/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.captcha.constant.CaptchaConstant;
import net.luoteng.captcha.service.CaptchaService;
import net.luoteng.captcha.utils.CaptchaUtils;
import nl.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Slf4j
@Component
public class CaptchaServiceImpl implements CaptchaService, CaptchaConstant {

//    @Autowired
//    RedisService redisService;
    
    @PostConstruct
    void init () {
        log.info("captcha service init");
        
        long startTime = System.currentTimeMillis();
        //prepare captcha cache
//        final ExecutorService managedExecutorService = Executors.newFixedThreadPool(25);
//        List<Future> futures = new ArrayList<>();
//        for (int i = 0; i < CAPTCHA_CACHE_SIZE; i++) {
//            futures.add(managedExecutorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    Captcha captcha = CaptchaUtils.getCaptcha();
//                    try (ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);) {
//                        ImageIO.write(captcha.getImage(), "png", bo);
//                        captchaCache.put(captcha.getAnswer(), bo.toByteArray());
//                        
//                        redisService.put(CAPTCHA_IMAGE_TYPE, value);
//                    } catch (IOException ex) {
//                        log.error("", ex);
//                    }
//                }
//            }));
//        }
//        try {
//            for (Future future : futures) {
//                future.get();
//            }
//        } catch (InterruptedException | ExecutionException ex) {
//            log.error("", ex);
//        } finally {
//            managedExecutorService.shutdown();
//        }
//        keys = captchaCache.keySet().toArray(new String[captchaCache.size()]);

        log.info("Worker started in {}ms", System.currentTimeMillis() - startTime);
    }
}
