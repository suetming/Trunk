/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.captcha.config;

import java.io.Serializable;
import net.luoteng.model.AbstractObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Configuration
public class RedisConfig {

    @Bean(name = "abstractObjectRedisTemplate")
    public RedisTemplate<String, AbstractObject> serializableTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, AbstractObject> byteTemplate = new RedisTemplate<>();
        byteTemplate.setConnectionFactory(redisConnectionFactory);
        byteTemplate.afterPropertiesSet();
        return byteTemplate;
    }
    
}
