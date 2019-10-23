package com.alibaba.csp.sentinel.dashboard.rule.redis;

public interface IRedisPublisher {
	public void publish(String channel,String ruleKey, String message);
}
