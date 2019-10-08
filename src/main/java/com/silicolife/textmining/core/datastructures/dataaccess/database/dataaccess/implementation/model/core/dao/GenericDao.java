package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;

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

//	public List<T> findByAttributesCaseSensitive(Map<String, Serializable> eqRestrictions);
	
//	public List<T> findByAttributesCaseSensitiveWithLimit(Map<String, Serializable> eqRestrictions, int limit);
	
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
	
	//only works if sortBy is already the same as in the core entity that maps the database 
	public List<T> findAllPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public List<T> findByAttributesBetween(Map<String, Serializable> eqRestrictions, Map<String, Serializable> greater, Map<String, Serializable> less);

	public Long countByAttributes(Map<String, Serializable> eqRestrictions);

	public Map<String, Long> countByAttributesGroupedBy(Map<String, Serializable> eqRestrictions, List<String> groups);

	public Map<String, Long> countByAttributesWithAliasGroupedBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, List<String> groups);
	
	public Long countByAttributesDistinctBy(Map<String, Serializable> eqRestrictions, List<String> distinctBy);
	
	public Long countByAttributesWithAliasDistinctBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, List<String> distinctBy);
	
	public Long countByAttributesInListsWithAliasDistinctBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, Map<String, Serializable> inRestrictions, List<String> distinctBy);
	
//	public T findUniqueByAttributeCaseSensitive(String attribute, Serializable value);
}
