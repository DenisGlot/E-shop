package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Category;


public class CategoryCache extends CacheImpl<Integer, Category>{
	
    private static ConcurrentHashMap<Integer, Category> categoryMap = new ConcurrentHashMap<>();
	
	private static CategoryCache categoryCache = new CategoryCache(Category.class); 

	private CategoryCache(Class<Category> type) {
		super(type);
		load();
	}
	
	public static CategoryCache getCache() {
		return categoryCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, Category> getCacheMap() {
		return categoryMap;
	}
	
	private void load() {
		cache.put(1, dao.findById(1));
		cache.put(2, dao.findById(2));
	}
}
