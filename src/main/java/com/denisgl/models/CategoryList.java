package com.denisgl.models;

import java.util.ArrayList;
import java.util.List;

import com.denisgl.cache.realization.CategoryCache;
import com.denisgl.cache.realization.ProductCache;
import com.denisgl.dao.entities.Product;

public class CategoryList {
 
	public static List<Product> getProductsByCategoryId(Integer categoryId){
		if(isCategoryExist(categoryId)) {
			return getCatalogByCategory(categoryId);
		} else {
			return new ArrayList<>();
		}
	} 
	
	private static boolean isCategoryExist(Integer categoryId) {
		return CategoryCache.getCache().findById(categoryId)!=null;	
	}
	
	private static List<Product> getCatalogByCategory(Integer categoryId) {
		return ProductCache.getCache().getAllByCriteria("categoryid", categoryId);
	}
	
	public static String getNameOfCategory(Integer categoryId) {
		return CategoryCache.getCache().findById(categoryId).getName();
	}
}
