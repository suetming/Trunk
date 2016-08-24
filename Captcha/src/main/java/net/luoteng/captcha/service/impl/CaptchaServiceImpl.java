/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.captcha.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
import net.luoteng.constant.GlobalConstant;
import static net.luoteng.constant.GlobalConstant.GLOBAL_ENCODING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

/**
 *
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc. All Rights Reserved.
 */
@Slf4j
@Component
public class CaptchaServiceImpl implements CaptchaService, GlobalConstant, CaptchaConstant {

    @Value("${net.luoteng.captcha.cache.size}")
    int cacheSize;

    /**
     * 过期时间设为0，表示永不过期
     */
    @Value("${net.luoteng.captcha.cache.expire}")
    int expireTime;

    Random seed;

    /**
     * TODO not elegant
     */
    @Autowired
    StringRedisTemplate redisTemplate;

    @PostConstruct
    void init() {
        log.info("captcha service init");
        if (cacheSize <= 0) {
            cacheSize = CAPTCHA_CACHE_SIZE;
        }
        
        cacheSize = 0;

        seed = new Random();

        long startTime = System.currentTimeMillis();
        //prepare captcha cache
        final ExecutorService managedExecutorService = Executors.newFixedThreadPool(25);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < cacheSize; i++) {
            final int idx = i;
            futures.add(managedExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    nl.captcha.Captcha captcha = CaptchaUtils.getCaptcha();
                    try (ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);) {
                        ImageIO.write(captcha.getImage(), "png", bo);
                        byte[] data = SerializationUtils.serialize(new net.luoteng.captcha.model.Captcha(
                                UUID.randomUUID().toString(),
                                captcha.getAnswer(),
                                bo.toString(GLOBAL_ENCODING)));
                        String result = new String(data, GLOBAL_ENCODING);
                        redisTemplate.opsForValue().set(String.valueOf(idx), result);
                        log.info("captcha service init {}", idx);
                    } catch (IOException ex) {
                        log.error("captcha service init {}", ex);
                    }
                }
            }));
        }
        try {
            for (Future future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException ex) {
            log.error("captcha service init {}", ex);
        } finally {
            managedExecutorService.shutdown();
        }

//        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! captcha {}", random());
        log.info("captcha init finished in {}ms", System.currentTimeMillis() - startTime);
    }

    @Override
    public net.luoteng.captcha.model.Captcha random() {
        try {
            int idx = seed.nextInt(cacheSize);

            String data = redisTemplate.opsForValue().get(String.valueOf(idx));
            net.luoteng.captcha.model.Captcha model = (net.luoteng.captcha.model.Captcha) SerializationUtils.deserialize(data.getBytes(GLOBAL_ENCODING));
            log.info("!!!!!!!!!!!!!!!!!!!!!!!!!captcha service get {}, {}", idx, model);
            return model;
        } catch (UnsupportedEncodingException ex) {
            log.error("random captcha {}", ex);
        }
        return null;
    }
}
