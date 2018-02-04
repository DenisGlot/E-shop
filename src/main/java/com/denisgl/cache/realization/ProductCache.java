package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Product;

public class ProductCache extends CacheImpl<Integer, Product>{
	
    private static ConcurrentHashMap<Integer, Product> productMap = new ConcurrentHashMap<>();
	
	private static ProductCache productCache = new ProductCache(Product.class); 

	private ProductCache(Class<Product> type) {
		super(type);
	}
	
	public static ProductCache getCache() {
		return productCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, Product> getCacheMap() {
		return productMap;
	}
}
