package com.denisgl.models;

import com.denisgl.cache.realization.ProductCache;
import com.denisgl.cache.realization.SuplierCache;
import com.denisgl.dao.entities.Product;

public class ProductGetter {

	public static Product getProductById(Integer id) {
		return ProductCache.getCache().findById(id);
	}

	public static String getSuplierNameById(Integer id){
		return SuplierCache.getCache().findById(id).getName();
	}
}
