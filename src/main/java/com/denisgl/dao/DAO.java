package com.denisgl.dao;

import java.util.List;

/**
 * Here K is Primary Key and E is entity
 * @author Denis
 *
 * @param <E> Entity
 * @param <K> Key
 */
public interface DAO<E,K> {
    List<E> getAll();
    List<E> getAllByCriteria(String name, Object like);
    E findById(K id);
    E findByCriteria(String name, Object like);
    boolean update(E entity);
    boolean delete(E entity);
    boolean save(E entity);
   
}
