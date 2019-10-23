package com.alibaba.csp.sentinel.dashboard.rule.redis.publisher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.consts.RedisKeyPrefix;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.IRedisPublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;

@Component("flowRuleRedisPublisher")
public class FlowRuleRedisPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {
	
	@Autowired
	IRedisPublisher redisPublisher;
	
	@Override
	public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
		AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        String rulesStr = JSON.toJSONString(rules.stream().map(FlowRuleEntity::toFlowRule).collect(Collectors.toList()));
        redisPublisher.publish(RedisKeyPrefix.FLOW_PREFIX + "channel:" + app, RedisKeyPrefix.FLOW_PREFIX + app, rulesStr);
	}

}
