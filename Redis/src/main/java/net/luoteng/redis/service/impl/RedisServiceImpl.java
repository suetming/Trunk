/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.redis.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.luoteng.constant.GlobalConstant;
import static net.luoteng.constant.GlobalConstant.GLOBAL_NAMESPACE;
import net.luoteng.enums.Realm;
import net.luoteng.enums.ResponseCode;
import net.luoteng.model.common.RestResponse;
import net.luoteng.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Slf4j
@Component
public class RedisServiceImpl implements RedisService, GlobalConstant {

    @Value("${net.luoteng.client}")
    String client;
    
    @Autowired 
    StringRedisTemplate redis;
    
    @Override
    public RestResponse get(Realm realm, String key) {
        Object result = null;
        String redisKey = key(realm, key);
        switch (realm) {
            case CAPTCHA:
            case GRAPHCAPTCHA:
                result = redis.opsForValue().get(redisKey);
                break;
        }
        
        if (result == null)
            return new RestResponse().error(ResponseCode.ERROR_MOBILE_CAPTCHA_INVALID);
        else
            return new RestResponse(result);
    }
    
    @Override
    public void set(Realm realm, String key, String value, int expire) {
        String redisKey = key(realm, key);
        redis.opsForValue().set(redisKey, value, expire, TimeUnit.SECONDS);
    }
    
    @Override
    public void set(Realm realm, String key, String value) {
        String redisKey = key(realm, key);
        redis.opsForValue().set(redisKey, value);
    }
    
    @Override
    public void delete(Realm realm, String key) {
        String redisKey = key(realm, key);
        redis.delete(redisKey);
    }
    
    @Override
    public void set(Realm realm, String key, byte[] value) {
        try {
            set(realm, key, new String(value, GLOBAL_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            log.error("redis service error {}", ex);
        }
    }
    
    @Override
    public void set(Realm realm, String key, byte[] value, int expire) {
        try {
            set(realm, key, new String(value, GLOBAL_ENCODING), expire);
        } catch (UnsupportedEncodingException ex) {
            log.error("redis service error {}", ex);
        }
    }
    
    @Override
    public String key(Realm realm, String key) {
        return String.format("1$s:%2$s:%3$s%4$s", GLOBAL_NAMESPACE, client, realm.name(), key);
    }

}
