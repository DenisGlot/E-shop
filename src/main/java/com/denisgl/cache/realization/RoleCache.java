package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Role;


/**
 * Singletone
 */
public class RoleCache extends CacheImpl<Integer,Role>{

	private static ConcurrentHashMap<Integer, Role> roleMap = new ConcurrentHashMap<>();
	
	private static RoleCache roleCache = new RoleCache(Role.class); 
	
	private RoleCache(Class<Role> type) {
		super(type);
		load();
	}
	
	public static RoleCache getCache() {
		return roleCache;
	}

	private void load() {
		cache.put(1, dao.findById(1));
		cache.put(2, dao.findById(2));
	}

	@Override
	protected ConcurrentHashMap<Integer, Role> getCacheMap() {
		return roleMap;
	}

}
