package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.OrderDetails;

public class OrderDetailsCache extends CacheImpl<Integer, OrderDetails>{
	
    private static ConcurrentHashMap<Integer, OrderDetails> orderDetailsMap = new ConcurrentHashMap<>();
	
	private static OrderDetailsCache orderDetailsCache = new OrderDetailsCache(OrderDetails.class); 

	private OrderDetailsCache(Class<OrderDetails> type) {
		super(type);
	}
	
	public static OrderDetailsCache getCache() {
		return orderDetailsCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, OrderDetails> getCacheMap() {
		return orderDetailsMap;
	}
}