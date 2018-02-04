package com.denisgl.cache;

import java.util.List;

public interface Cache<K, E> {

	
	E findById(K key);

	/**
	 *  Not caching
	 */
	E findByCriteria(String name, Object like);

	List<E> getAllByCriteria(String name, Object like);

	List<E> getAll();

	boolean update(E entity);

	boolean delete(E entity);

	boolean save(E entity);

}