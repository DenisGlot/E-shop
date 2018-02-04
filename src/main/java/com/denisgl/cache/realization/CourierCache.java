package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Courier;

public class CourierCache extends CacheImpl<Integer, Courier>{
	
    private static ConcurrentHashMap<Integer, Courier> courierMap = new ConcurrentHashMap<>();
	
	private static CourierCache courierCache = new CourierCache(Courier.class); 

	private CourierCache(Class<Courier> type) {
		super(type);
	}
	
	public static CourierCache getCache() {
		return courierCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, Courier> getCacheMap() {
		return courierMap;
	}
}
