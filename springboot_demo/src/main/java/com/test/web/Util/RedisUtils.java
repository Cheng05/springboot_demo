package com.test.web.Util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtils {
	private static Logger log = LoggerFactory.getLogger(RedisUtils.class);
  	//private static Properties properties = ReadProperties.getProperties(RedisUtils.class, "config.properties");
	private static JedisPool clientPool = null;
	static {

		String server ="";
		String password ="";
		int port =6379;
		if (clientPool == null) {
			log.debug("ready to connect to redis server {} on {}", server, port);
			try {
				JedisPoolConfig config = new JedisPoolConfig();
				//config.setMaxTotal(200);
				 config.setMaxIdle(200);
				    //最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
				    config.setMaxTotal(30);
				    config.setTestOnBorrow(false);
				    config.setTestOnReturn(false);
				clientPool = new JedisPool(config, server, port, 100000,password);
 				//clientPool = new JedisPool(config, server, port);
				log.debug("connected");
			} catch (Exception e) {
				log.error("exception raised with message: {}", e.getMessage());
				// return false;
			}
		} else {
			log.debug("reinit detected!");
		}
	}

	 
    public static Jedis getConnection()  {
    	try {
    	  	Jedis conn =  clientPool.getResource();
        	return conn;
			
		} catch (Exception e) {
    		e.printStackTrace();

		}
    	
    	return null;
  
    }
    public static void close(Jedis client){
    	if (client != null)
			clientPool.returnResource(client);
    }


    public  static String getStr(String key){
		Jedis conn = RedisUtils.getConnection();
		String value =  conn.get(key);
		RedisUtils.close(conn);
		return value;
	}



	public  static void setStrWithExpire(String key,String value,int seconds){
		Jedis conn = RedisUtils.getConnection();
		conn.set(key,value);
		conn.expire("expire",seconds);
		System.out.println("redisvalue="+getStr(key));

		RedisUtils.close(conn);

}

	public  static void setSt(String key,String value){
		Jedis conn = RedisUtils.getConnection();
		conn.set(key,value);
		RedisUtils.close(conn);
	}





}