package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass, "al");

		if(!sortBy.equals("none")){
			//String uniqueId = PublicationFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
			String sortAlias = "al."+sortBy;
			
			if(asc)
				c.addOrder(Order.asc(sortAlias));
			else
				c.addOrder(Order.desc(sortAlias));
			}
			c.setFirstResult(paginationIndex);
			c.setMaxResults(paginationSize);
			c.setFetchSize(paginationSize);
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
	
	@Override
	public Long countByAttributes(Map<String, Serializable> eqRestrictions) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		return (Long) c.setProjection(Projections.rowCount())
                .uniqueResult();
	}
	
	@Override
	public Long countByAttributesDistinctBy(Map<String, Serializable> eqRestrictions, List<String> distinctBy) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		ProjectionList projectionListToDistinct = Projections.projectionList();
		for(String distinctType : 	distinctBy)
			projectionListToDistinct.add(Projections.property(distinctType), distinctType);
		c.setProjection(Projections.distinct(projectionListToDistinct));
		return (Long) c.setProjection(Projections.rowCount())
                .uniqueResult();
	}
	
	@Override
	public Long countByAttributesWithAliasDistinctBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, List<String> distinctBy) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		
		for(Entry<String, String> aliasentry: alias.entrySet())
			c.createAlias(aliasentry.getKey(), aliasentry.getValue());
		
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		
		ProjectionList projectionListToDistinct = Projections.projectionList();
		for(String distinctType : 	distinctBy)
			projectionListToDistinct.add(Projections.property(distinctType), distinctType);
		c.setProjection(Projections.distinct(projectionListToDistinct));
		return (Long) c.setProjection(Projections.rowCount())
                .uniqueResult();
	}
	
	@Override
	public Long countByAttributesInListsWithAliasDistinctBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, Map<String, Serializable> inRestrictions, List<String> distinctBy){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		
		for(Entry<String, String> aliasentry: alias.entrySet())
			c.createAlias(aliasentry.getKey(), aliasentry.getValue());
		
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.in(entry.getKey(), entry.getValue()));
		
		ProjectionList projectionListToDistinct = Projections.projectionList();
		for(String distinctType : 	distinctBy)
			projectionListToDistinct.add(Projections.property(distinctType), distinctType);
		c.setProjection(Projections.distinct(projectionListToDistinct));
		return (Long) c.setProjection(Projections.rowCount())
                .uniqueResult();
	}
	
	@Override
	public Map<String, Long> countByAttributesGroupedBy(Map<String, Serializable> eqRestrictions, List<String> groups) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		
		ProjectionList plist = Projections.projectionList();
		for(String group : groups)
			plist.add(Projections.groupProperty(group));
		
		plist.add(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Object[]> result = c.setProjection(plist).list();
		Map<String, Long> resultmap =  new HashMap<>();
		for(Object[] row :result) {
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<= row.length-2;i++) {
				if(sb.length()!=0)
					sb.append("-");
				sb.append(row[i].toString());
			}
			resultmap.put(sb.toString(), Long.valueOf(row[row.length-1].toString()));
		}
			
		return resultmap;
	}
	
	@Override
	public Map<String, Long> countByAttributesWithAliasGroupedBy(Map<String, String> alias, Map<String, Serializable> eqRestrictions, List<String> groups){
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		
		for(Entry<String, String> aliasentry: alias.entrySet())
			c.createAlias(aliasentry.getKey(), aliasentry.getValue());
		
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		
		ProjectionList plist = Projections.projectionList();
		for(String group : groups)
			plist.add(Projections.groupProperty(group));
		
		plist.add(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Object[]> result = c.setProjection(plist).list();
		Map<String, Long> resultmap =  new HashMap<>();
		for(Object[] row :result) {
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<= row.length-2;i++) {
				if(sb.length()!=0)
					sb.append("-");
				sb.append(row[i].toString());
			}
			resultmap.put(sb.toString(), Long.valueOf(row[row.length-1].toString()));
		}
			
		return resultmap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByAttributesBetween(Map<String, Serializable> eqRestrictions, Map<String, Serializable> greaterOrEqual,Map<String, Serializable> lessOrEqual) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		for (Map.Entry<String, Serializable> entry : greaterOrEqual.entrySet())
			c.add(Restrictions.ge(entry.getKey(), entry.getValue()));
		for (Map.Entry<String, Serializable> entry : lessOrEqual.entrySet())
			c.add(Restrictions.le(entry.getKey(), entry.getValue()));
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
	
	@Override
	public T merge(Object object) {
		@SuppressWarnings("unchecked")
		T castedObj = (T) sessionFactory.getCurrentSession().merge(object);
		return castedObj;	
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> findByAttributesCaseSensitive(Map<String, Serializable> eqRestrictions) {
//
//		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
//		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet()) {
//			String key = entry.getKey().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
//			// if(entry.getValue() instanceof Long){
//			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
//			// entry.getValue(),new LongType() ));
//			// }else if (entry.getValue() instanceof Boolean){
//			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
//			// entry.getValue(),new BooleanType() ));
//			// }else if(entry.getValue() instanceof Integer){
//			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
//			// entry.getValue(),new IntegerType() ));
//			// }else if(entry.getValue() instanceof Date){
//			// c.add(Restrictions.sqlRestriction( key + " = ? collate utf8_bin",
//			// entry.getValue(),new DateType() ));
//			if (entry.getValue() instanceof String) {
//				c.add(Restrictions.sqlRestriction(key + " = ? collate utf8_bin", entry.getValue(), new StringType()));
//			} else {
//				c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
//			}
//		}
//
//		return c.list();
//	}
//
//	@Override
//	public T findUniqueByAttributeCaseSensitive(String attribute, Serializable value) {
//
//		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
//		eqRestrictions.put(attribute, value);
//		List<T> result = findByAttributesCaseSensitive(eqRestrictions);
//		if (result.size() == 1)
//			return result.get(0);
//
//		return null;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> findByAttributesCaseSensitiveWithLimit(Map<String, Serializable> eqRestrictions, int limit) {
//		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
//		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet()) {
//		
//			String key = entry.getKey().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
//			if (entry.getValue() instanceof String) {
//				c.add(Restrictions.sqlRestriction(key + " = ? collate utf8_bin", entry.getValue(), new StringType()));
//			} else {
//				c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
//			}
//		}
//		c.setMaxResults(limit);
//		//c.setFirstResult(limit);
//		
//		return c.list();
//	}

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
