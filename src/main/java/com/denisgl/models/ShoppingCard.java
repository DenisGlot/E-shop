package com.denisgl.models;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.denisgl.dao.entities.Product;
import com.denisgl.dao.entities.User;

public class ShoppingCard {
	
	private final Logger logger = Logger.getLogger(ShoppingCard.class);

	private User user;
	
	private ConcurrentHashMap<Product, Integer> products;
	
	public ShoppingCard(User user) {
		this.user = user;
		products = new ConcurrentHashMap<>();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public ConcurrentHashMap<Product, Integer> getProducts() {
		return products;
	}

	/**
	 * Method put() in ConcurrentHashMap is working like replace
	 * So be careful if user wants to put in basket two same products
	 */
	public void addProduct(Product product, Integer quantity) {
		products.put(product, quantity);
	}
	
	public void setQuantityOfProduct(Product product,Integer quantity) {
		if(products.replace(product, quantity)==null) {
			logger.warn("In method setQuantityOfProducts was not any replacement!!!");
		}
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public void removeAllProducts() {
		products.clear();
	}
	
	public int size() {
		return products.size();
	}
	
	@Override
	public String toString() {
		return "ShoppingCard [user=" + user + "]";
	}

}
