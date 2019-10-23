package com.alibaba.csp.sentinel.dashboard.rule.redis.publisher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.consts.RedisKeyPrefix;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.IRedisPublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;

@Component("paramFlowRuleRedisPublisher")
public class ParamFlowRuleRedisPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {
	
	@Autowired
	IRedisPublisher redisPublisher;
	
	@Override
	public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
		AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        String rulesStr = JSON.toJSONString(rules.stream().map(ParamFlowRuleEntity::toParamFlowRule).collect(Collectors.toList()));
        redisPublisher.publish(RedisKeyPrefix.PARAMFLOW_PREFIX + "channel:" + app, RedisKeyPrefix.PARAMFLOW_PREFIX + app, rulesStr);
	}

}
