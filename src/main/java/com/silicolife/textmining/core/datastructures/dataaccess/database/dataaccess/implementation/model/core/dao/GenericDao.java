package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Define all generic methos to access the data.
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 * @param <T>
 */
public interface GenericDao<T> {

	public T findById(Serializable id);

	public List<T> findAll();

	public List<T> findByAttributes(Map<String, Serializable> eqRestrictions);

	public List<T> findByAttributesCaseSensitive(Map<String, Serializable> eqRestrictions);
	
	public List<T> findByAttributesCaseSensitiveWithLimit(Map<String, Serializable> eqRestrictions, int limit);
	
	public List<T> findByAttributesWithPagniation(Map<String, Serializable> eqRestrictions, int index, int limit);

	public T findUniqueByAttribute(String attribute, Serializable value);

	public List<T> findByOrAttributes(Map<String, Serializable> orRestrictions);

	public void flushSession();
	
	public void clearSession();

	public void save(Object object);

	public void update(Object object);

	public void saveOrUpdate(Object object);

	public void delete(Object object);

	public void refresh(Object object);

	public void evict(Object object);
	
	public T merge(Object object);

	public T findUniqueByAttributeCaseSensitive(String attribute, Serializable value);
}
