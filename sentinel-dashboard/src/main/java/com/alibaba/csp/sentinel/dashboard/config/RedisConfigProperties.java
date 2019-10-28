package com.alibaba.csp.sentinel.dashboard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="datasource.redis1",ignoreUnknownFields = true)
public class RedisConfigProperties {
	private String host;
	private String password;
	private int port;
	private int database;
	private long timeout;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getDatabase() {
		return database;
	}
	public void setDatabase(int database) {
		this.database = database;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
}
