package com.alibaba.csp.sentinel.dashboard.rule.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RedisPublisherImpl implements IRedisPublisher{

	@Autowired
	@Qualifier("redis1")
	Jedis redis1;
	
	/*@Autowired
	@Qualifier("redis2")
	Jedis redis2;
	
	@Autowired
	@Qualifier("redis3")
	Jedis redis3;
	
	@Autowired
	@Qualifier("redis4")
	Jedis redis4;*/
	
	
	@Override
	public void publish(String channel,String ruleKey, String message) {
		//发布消息
		redis1.publish(channel, message);
        //持久化到redis消息
        redis1.set(ruleKey, message);
        
        /*//发布消息
		redis2.publish(channel, message);
	    //持久化到redis消息
	    redis2.set(ruleKey, message);
	    
	    //发布消息
  		redis3.publish(channel, message);
  	    //持久化到redis消息
  	    redis3.set(ruleKey, message);
  	    
  	    //发布消息
		redis4.publish(channel, message);
	    //持久化到redis消息
	    redis4.set(ruleKey, message);*/
	}

}
