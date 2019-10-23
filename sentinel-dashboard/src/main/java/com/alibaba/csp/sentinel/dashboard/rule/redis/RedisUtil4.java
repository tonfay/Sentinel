package com.alibaba.csp.sentinel.dashboard.rule.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RedisUtil4 {
	//Redis服务器IP ,端口,密码
    private static String ADDR = "redis.shouqiev.net";
    private static int PORT = 6379;
    private static String AUTH="3QpT%LA5";
    private static int database = 1;
    //可连接的最大数目,默认值是8
    //如果赋值是-1,代表不限制,如果pool已经分配了maxActive个jedis实例
    //则此时pool的状态Wieexhausted(耗尽)
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)jedis实例,默认值也是8
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待
    //时间，则直接抛出 JedisConnectionException；
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
//    在 borrow 一个 jedis 实例时，是否提前进行 validate 操作；如果为 true，则
 //   得到的 jedis 实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    //初始化Redis连接池
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,
                    AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取实例
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                resource.select(database);
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 释放 jedis 资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.close();
        }
    }
//    public static void main(String[] args) {
//    	getJedis().set("fffffffffffffffffffff", "111111111111111");
//    	getJedis().publish("aaa","111");
//    	Jedis jedis = getJedis();
//    	new java.lang.Thread(new Runnable() {  
//            public void run() {  
//            	getJedis().subscribe(new JedisPubSub() {
//            		@Override
//            		public void onMessage(String channel, String message) {
//            			System.out.println("1111111111");
//            		}
//				}, "aaa"); 
//           }  
//       }).start();
//    	getJedis().publish("aaa","111");
//    	
//    	getJedis().publish("aaa","111");
//    	getJedis().publish("aaa","111");
//    	getJedis().publish("aaa","111");
//    	getJedis().publish("aaa","111");
//    	getJedis().publish("aaa","111");
//    	getJedis().publish("aaa","111");
//	}
}
