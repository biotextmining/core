package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

public class GenericLuceneDaoImpl<T> implements IGenericLuceneDao<T> {

	private SessionFactory sessionFactory;
	private Class<T> klass;

	public GenericLuceneDaoImpl(SessionFactory sessionFactory, Class<T> klass) {
		this.sessionFactory = sessionFactory;
		this.klass = klass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBySentenceOnField(String sentence, String field) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		Query luceneQuery = qb.phrase().onField(field).sentence(sentence).createQuery();
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		return fullTextQuery.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByAttributes(Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		for(String sentence : eqSentenceOnField.keySet()){
			Query luceneQuery = qb.phrase().onField(eqSentenceOnField.get(sentence)).sentence(sentence).createQuery();
			combinedQuery.must(luceneQuery);
		}
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery.list();
	}

	private FullTextSession getFullTextSession() {
		return Search.getFullTextSession(sessionFactory.getCurrentSession());
	}
	
	private QueryBuilder getQueryBuilder(FullTextSession fullTextSession) {
		return fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(klass).get();
	}
	
	
}
