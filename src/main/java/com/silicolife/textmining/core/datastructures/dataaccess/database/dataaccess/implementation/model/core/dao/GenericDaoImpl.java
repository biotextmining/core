package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

/**
 * This classes represents the implementation of generic methods to access the
 * data
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 * @param <T>
 */

public class GenericDaoImpl<T> implements GenericDao<T> {

	private SessionFactory sessionFactory;
	private Class<T> klass;

	public GenericDaoImpl(SessionFactory sessionFactory, Class<T> klass) {
		this.sessionFactory = sessionFactory;
		this.klass = klass;
	}

	@Override
	public T findById(Serializable id) {
		T T = (T) sessionFactory.getCurrentSession().get(klass, id);
		return T;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		return c.list();
	}

	@Override
	public T findUniqueByAttribute(String attribute, Serializable value) {
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put(attribute, value);
		List<T> result = findByAttributes(eqRestrictions);
		if (result.size() == 1)
			return result.get(0);

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByAttributes(Map<String, Serializable> eqRestrictions) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByOrAttributes(Map<String, Serializable> orRestrictions) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		Disjunction or = Restrictions.disjunction();
		for (Map.Entry<String, Serializable> entry : orRestrictions.entrySet())
			or.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		c.add(or);
		return c.list();
	}

	@Override
	public void flushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void save(Object object) {
		sessionFactory.getCurrentSession().save(object);
		// sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void update(Object object) {
		sessionFactory.getCurrentSession().update(object);
		// sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveOrUpdate(Object object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
		// sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);
		// sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void refresh(Object object) {
		sessionFactory.getCurrentSession().refresh(object);

	}

	@Override
	public void evict(Object object) {
		sessionFactory.getCurrentSession().evict(object);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByAttributesCaseSensitive(Map<String, Serializable> eqRestrictions) {

		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet()) {
			String key = entry.getKey().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
			// if(entry.getValue() instanceof Long){
			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
			// entry.getValue(),new LongType() ));
			// }else if (entry.getValue() instanceof Boolean){
			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
			// entry.getValue(),new BooleanType() ));
			// }else if(entry.getValue() instanceof Integer){
			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
			// entry.getValue(),new IntegerType() ));
			// }else if(entry.getValue() instanceof Date){
			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
			// entry.getValue(),new DateType() ));
			if (entry.getValue() instanceof String) {
				c.add(Restrictions.sqlRestriction(key + " = ? collate utf8_bin", entry.getValue(), new StringType()));
			} else {
				c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}

		return c.list();
	}

	@Override
	public T findUniqueByAttributeCaseSensitive(String attribute, Serializable value) {

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put(attribute, value);
		List<T> result = findByAttributesCaseSensitive(eqRestrictions);
		if (result.size() == 1)
			return result.get(0);

		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByAttributesCaseSensitiveWithLimit(Map<String, Serializable> eqRestrictions, int limit) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet()) {
		
			String key = entry.getKey().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
			if (entry.getValue() instanceof String) {
				c.add(Restrictions.sqlRestriction(key + " = ? collate utf8_bin", entry.getValue(), new StringType()));
			} else {
				c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		c.setMaxResults(limit);
		//c.setFirstResult(limit);
		
		return c.list();
	}

	@Override
	public void clearSession() {
		sessionFactory.getCurrentSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByAttributesWithPagniation(Map<String, Serializable> eqRestrictions, int index, int limit) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		c.setFirstResult(index);
		c.setMaxResults(limit);
		c.setFetchSize(limit);
		return c.list();
	}
}
