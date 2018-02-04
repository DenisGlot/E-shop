package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.User;

/**
 * The email was the key Object but now it's the phone
 * @author Denis
 *
 */
public class UserCache extends CacheImpl<String,User> {
	
	private static ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
	
	private static UserCache userCache = new UserCache(User.class);
	
	private UserCache(Class<User> type) {
		super(type);
		load();
		
	}
	
	public static UserCache getCache() {
		return userCache;
	}
	
	@Override
	protected ConcurrentHashMap<String, User> getCacheMap() {
		return userMap;
	}

	private void load() {
		 cache.put("admin",dao.findByCriteria("email", "admin"));
		 cache.put("iliya",dao.findByCriteria("email", "iliya"));
		 cache.put("denis",dao.findByCriteria("email", "denis"));
	}
	
}
