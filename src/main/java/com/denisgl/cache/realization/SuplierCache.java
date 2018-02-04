package com.denisgl.cache.realization;

import java.util.concurrent.ConcurrentHashMap;

import com.denisgl.cache.CacheImpl;
import com.denisgl.dao.entities.Suplier;

public class SuplierCache extends CacheImpl<Integer, Suplier>{
	
    private static ConcurrentHashMap<Integer, Suplier> suplierMap = new ConcurrentHashMap<>();
	
	private static SuplierCache suplierCache = new SuplierCache(Suplier.class); 

	private SuplierCache(Class<Suplier> type) {
		super(type);
	}
	
	public static SuplierCache getCache() {
		return suplierCache;
	}
	
	@Override
	protected ConcurrentHashMap<Integer, Suplier> getCacheMap() {
		return suplierMap;
	}
}