package com.denisgl.cache;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.denisgl.dao.DAO;
import com.denisgl.dao.DAOImpl;
import com.denisgl.dao.entities.User;
import com.denisgl.dao.entities.annotations.MyEntity;

/**
 * This is synchronized Cache
 * getCacheMap() is Abstract Method
 * @param <K> Key
 * @param <E> Entity
 */
public abstract class CacheImpl<K, E> implements Cache<K, E> {
	
	private final Logger logger = Logger.getLogger(CacheImpl.class);

	protected int DEFAULT_MAX_SIZE = 64;
	
	private String idName;
	
	private Field fieldWithKey;

	protected DAO<E, K> dao;
	
	protected ConcurrentHashMap<K, E> cache;

	private Class<E> type;
	
	protected CacheImpl(Class<E> type) {
		this.type = type;
		cache = getCacheMap();
		dao = new DAOImpl<>(type);
		declareIdName();
		declareFieldWithKey();
	}
	private void declareIdName() {
		if(type.equals(User.class)) {
			idName = "phone";
		} else {
			MyEntity annotationEntity = type.getAnnotation(MyEntity.class);
		    idName = annotationEntity.id();
		}
	}
	private void declareFieldWithKey() {
		try {
			fieldWithKey = type.getDeclaredField(idName);
			fieldWithKey.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			logger.error(e);
		}
	}
	
	protected abstract ConcurrentHashMap<K, E> getCacheMap();

	@Override
	public E findById(K key) {
		checkSizeOfCache();
		E entity = cache.get(key);
		if(entity == null) {
			entity = dao.findByCriteria(idName, key);
			logger.debug(entity + "is not in cach");
			putInCacheIfExist(key, entity);
		} 
		return entity;
	}
	private void putInCacheIfExist(K key, E entity) {
		if(entity == null) {
			logger.debug(entity + "is not in database");
		} else {
			cache.put(key, entity);
			logger.debug(entity + "is in cach");
		}
	}
	
	private void checkSizeOfCache() {
		if(cache.size() + 1 >= DEFAULT_MAX_SIZE) {
			cache.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.denisgl.cache.Cache#findByCriteria(java.lang.String, java.lang.Object)
	 */
	@Override
	public E findByCriteria(String name, Object like) {
		return dao.findByCriteria(name, like);
	}
	
	
	
	@Override
	public List<E> getAllByCriteria(String name, Object like){
		return putListIfPossibleAndReturn(dao.getAllByCriteria(name, like));
	}
	
	@Override
	public List<E> getAll(){
		return putListIfPossibleAndReturn(dao.getAll());
	}
	private List<E> putListIfPossibleAndReturn(List<E> listFromDAO){
		if(isSizeAccessable(listFromDAO)) {
			putListInCache(listFromDAO);
		}
		return listFromDAO;
	}
	private boolean isSizeAccessable(List<E> list) {
		return list.size() + cache.size()<=DEFAULT_MAX_SIZE;
	}
	
	private void putListInCache(List<E> list) {
		list.forEach((entity)-> {
			cache.put(declareKey(entity), entity);
		});
	}
	
	/**
	 * @throws NullPointerException
	 */
	@SuppressWarnings("unchecked")
	private K declareKey(E entity){
		try {
			return (K) fieldWithKey.get(entity);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e);
			throw new NullPointerException("Could not declare key of entity!");
		}
	}
	
	
	
	@Override
	public boolean update(E entity) {
		if (dao.update(entity)) {
			K key = declareKey(entity);
			cache.put(key, entity);
			return true;
		} 
		return false;
	}

	
	@Override
	public boolean delete(E entity) {
		if (dao.delete(entity)) {
			removeFromCache(entity);
            return true;
		} 
		return false;
	}
	private void removeFromCache(E entity) {
		K key = declareKey(entity);
		cache.remove(key);
		key = null;
	}

	
	@Override
	public boolean save(E entity) {
		checkSizeOfCache();
		if (dao.save(entity)) {
			putEntityInCache(entity);
			return true;
		}
		return false;
	}
	private void putEntityInCache(E entity) {
		K key = declareKey(entity);
		cache.put(key, entity);
	}

	/**
	 * DEFAULT_MAX_SIZE by defualt equals 16
	 * It checks if the parameter more than zero
	 */
	protected void setMaxSize(int max_size) {
		if(max_size < 0) {
			logger.warn("The parameter in method setMaxSize was less then zero!");
			return;
		}
		DEFAULT_MAX_SIZE = max_size;
	}
}
