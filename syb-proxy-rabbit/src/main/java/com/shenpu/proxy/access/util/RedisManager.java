package com.shenpu.proxy.access.util;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component
public class RedisManager {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

    private String project;
    private String trade;
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public RedisManager(String project, String trade){
	    this.project = project;
	    this.trade = trade;
	}
	public RedisManager(){
	    
	}
	/**
	 * 值自增
	 * @param key
	 * @throws Exception
	 */
	public long incr(String key) throws Exception {
	    String realKey = this.project + "_" + this.trade + "_" + key;
	    Long incNum = 0l;
	    try {
	    	incNum = redisTemplate.opsForValue().increment(realKey, 1);
	    } catch (Exception e) {
		    e.printStackTrace();
		    throw e;
	    }
	    return incNum;
	}
	
	/**
	 * 值自减
	 * @param key
	 * @throws Exception
	 */
	public long incr2(String key) throws Exception {
	    String realKey = this.project + "_" + this.trade + "_" + key;
	    Long incNum = 0l;
	    try {
	    	incNum = redisTemplate.opsForValue().increment(realKey, -1);
	    } catch (Exception e) {
		    e.printStackTrace();
		    throw e;
	    }
	    return incNum;
	}
	
	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void delValue(final String key) {
		String realKey = this.project + "_" + this.trade + "_" + key;
		if (exists(realKey)) {
			redisTemplate.delete(realKey);
		}
	}
	
	/**
	 * 写入缓存设置时效时间
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setExpireString(final String key, int expireTime) {
		boolean result = false;
		String realKey = this.project + "_" + this.trade + "_" + key;
		try {
			redisTemplate.expire(realKey, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setByStringKey(final String key, Object value) {
		boolean result = false;
		String realKey = this.project + "_" + this.trade + "_" + key;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(realKey, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存设置时效时间
	 * @param key
	 * @param value
	 * @returnint
	 */
	public boolean setByStringKey(final String key, Object value, int expireTime) {
		boolean result = false;
		String realKey = this.project + "_" + this.trade + "_" + key;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(realKey, value);
			redisTemplate.expire(realKey, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		String realKey = this.project + "_" + this.trade + "_" + key;
		return redisTemplate.hasKey(realKey);
	}
	
	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	public Object getByStringKey(final String key) {
		Object result = null;
		String realKey = this.project + "_" + this.trade + "_" + key;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result =  operations.get(realKey);
		return result;
	}
	
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hmSet(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		
		hash.put(key, hashKey, value);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hmGet(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public void lPush(String k, Object v) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(k, v);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<Object> lRange(String k, long l, long l1) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return list.range(k, l, l1);
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, Object value, double scoure) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}

	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.rangeByScore(key, scoure, scoure1);
	}
}