package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Order;

public class OrderCache extends CacheImpl<Integer, Order>{
	
    private static ConcurrentHashMap<Integer, Order> orderMap = new ConcurrentHashMap<>();
	
	private static OrderCache orderCache = new OrderCache(Order.class); 

	private OrderCache(Class<Order> type) {
		super(type);
	}
	
	public static OrderCache getCache() {
		return orderCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, Order> getCacheMap() {
		return orderMap;
	}
}
