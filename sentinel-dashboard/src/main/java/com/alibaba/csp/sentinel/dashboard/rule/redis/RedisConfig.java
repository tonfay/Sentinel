package com.alibaba.csp.sentinel.dashboard.rule.redis;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
	@Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean("redis1")
    public Jedis getResource1() {
        return RedisUtil1.getJedis();
    }
    /*@Bean("redis2")
    public Jedis getResource2() {
        return RedisUtil2.getJedis();
    }
    @Bean("redis3")
    public Jedis getResource3() {
        return RedisUtil3.getJedis();
    }
    @Bean("redis4")
    public Jedis getResource4() {
        return RedisUtil4.getJedis();
    }*/
    
    
}
