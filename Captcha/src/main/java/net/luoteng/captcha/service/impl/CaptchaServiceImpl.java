/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.luoteng.captcha.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.captcha.constant.CaptchaConstant;
import net.luoteng.captcha.model.Captcha;
import net.luoteng.captcha.service.CaptchaService;
import net.luoteng.captcha.utils.CaptchaUtils;
import net.luoteng.constant.GlobalConstant;
import static net.luoteng.constant.GlobalConstant.GLOBAL_NAMESPACE;
import net.luoteng.enums.Realm;
import net.luoteng.model.AbstractObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Value("${net.luoteng.client}")
    String client;
    
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
    @Qualifier("abstractObjectRedisTemplate")
    RedisTemplate<String, AbstractObject> redisTemplate;

    @PostConstruct
    void init() {
        log.info("captcha service init");
        if (cacheSize <= 0) {
            cacheSize = CAPTCHA_CACHE_SIZE;
        }

        seed = new Random();

        long startTime = System.currentTimeMillis();

        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection rc) throws DataAccessException {
                
                int tick = 0;
                for (int i = 0; i < cacheSize; i++) {
                    nl.captcha.Captcha captcha = CaptchaUtils.getCaptcha();
                    try (ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);) {
                        ImageIO.write(captcha.getImage(), "png", bo);
                        net.luoteng.captcha.model.Captcha model = new net.luoteng.captcha.model.Captcha(
                                UUID.randomUUID().toString(),
                                captcha.getAnswer(),
                                bo.toByteArray());

                        byte[] value = SerializationUtils.serialize(model);
                        byte[] key = SerializationUtils.serialize(key(Realm.GRAPHCAPTCHA, i));
                        rc.set(key, value);
                        tick ++;
                    } catch (IOException ex) {
                        log.error("captcha service init {}", ex);
                    }
                }
                
                cacheSize = tick;

                return true;
            }
        }, false, true);

        log.info("captcha init finished in {}ms, generate cache total {}", System.currentTimeMillis() - startTime, cacheSize);
    }

    @Override
    public net.luoteng.captcha.model.Captcha random() {
        net.luoteng.captcha.model.Captcha captcha = (net.luoteng.captcha.model.Captcha) redisTemplate.opsForValue().get(key(Realm.GRAPHCAPTCHA, seed.nextInt(cacheSize)));
        return captcha;
    }
    
    private String key(Realm realm, int key) {
        return String.format("1$s:%2$s:%3$s%4$s", GLOBAL_NAMESPACE, client, realm.name(), String.valueOf(key));
    }
    
}
