/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.service.impl;

import java.util.Random;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.captcha.constant.CaptchaConstant;
import net.luoteng.captcha.service.CaptchaService;
import net.luoteng.enums.Realm;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${net.luoteng.captcha.cache.size}")
    int cacheSize;
    
    Random seed;
    
//    @Autowired
//    RedisService redisService;
    
//    @Autowired
//    StringRedisTemplate redisTemplate;
    
//    RedisCache cache;
    
    @PostConstruct
    void init () {
        log.info("captcha service init");
//        cache = new RedisCache(CACHE_NAME, CACHE_NAME.getBytes(), template, EXPIRE_TIME);
//        
//        if (cacheSize <= 0) {
//            cacheSize = CAPTCHA_CACHE_SIZE;
//        }
//        
//        seed  = new Random();
//        
//        long startTime = System.currentTimeMillis();
//        //prepare captcha cache
//        final ExecutorService managedExecutorService = Executors.newFixedThreadPool(25);
//        List<Future> futures = new ArrayList<>();
//        for (int i = 0; i < cacheSize; i++) {
//            final int idx = i;
//            futures.add(managedExecutorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    Captcha captcha = CaptchaUtils.getCaptcha();
//                    try (ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);) {
//                        ImageIO.write(captcha.getImage(), "png", bo);
//                        
//                        redisService.set(Realm.GRAPHCAPTCHA, String.valueOf(idx), 
//                                new net.luoteng.captcha.model.Captcha(
//                                        UUID.randomUUID().toString(), 
//                                        captcha.getAnswer(), 
//                                        bo.toByteArray()));
//                    } catch (IOException ex) {
//                        log.error("captcha service init {}", ex);
//                    }
//                }
//            }));
//        }
//        try {
//            for (Future future : futures) {
//                future.get();
//            }
//        } catch (InterruptedException | ExecutionException ex) {
//            log.error("captcha service init {}", ex);
//        } finally {
//            managedExecutorService.shutdown();
//        }
//
//        log.info("captcha init finished in {}ms", System.currentTimeMillis() - startTime);
    }
    
    @Override
    public net.luoteng.captcha.model.Captcha random() {
        return null;
//        return (net.luoteng.captcha.model.Captcha)redisService.get(Realm.GRAPHCAPTCHA, String.valueOf(seed.nextInt(cacheSize))).getParam();
    }
}
