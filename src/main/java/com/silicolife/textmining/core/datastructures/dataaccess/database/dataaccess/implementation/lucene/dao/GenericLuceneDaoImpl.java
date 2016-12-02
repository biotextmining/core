package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.PhraseContext;
import org.hibernate.search.query.dsl.PhraseMatchingContext;
import org.hibernate.search.query.dsl.QueryBuilder;

public class GenericLuceneDaoImpl<T> implements IGenericLuceneDao<T> {

	private SessionFactory sessionFactory;
	private Class<T> klass;

	public GenericLuceneDaoImpl(SessionFactory sessionFactory, Class<T> klass) {
		this.sessionFactory = sessionFactory;
		this.klass = klass;
	}
	
	private FullTextSession getFullTextSession() {
		return Search.getFullTextSession(sessionFactory.getCurrentSession());
	}
	
	private QueryBuilder getQueryBuilder(FullTextSession fullTextSession) {
		return fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(klass).get();
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addMustPhraseWithAttributesOnFields(Map<String, String> eqSentenceOnField, QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			combinedQuery.must(luceneQuery);
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addMustWildcardWithStartingAttributesOnFields(Map<String, String> startSentenceOnField,
			QueryBuilder qb, BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : startSentenceOnField.keySet()){
			String value = startSentenceOnField.get(field);
			if(!value.isEmpty()){
				String wildcardValue = value + "*";
				Query luceneQuery = qb.keyword().wildcard()
						.onField(field)
						.matching(wildcardValue)
						.createQuery();
				combinedQuery.must(luceneQuery);
			}
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addMustPhraseWithMultiFieldOnAttribute(Map<String, Set<String>> attributeForMultipleFieldsMap,
			QueryBuilder qb, BooleanJunction<BooleanJunction> combinedQuery) {
		PhraseContext queryParse = qb.phrase();
		for(String value :attributeForMultipleFieldsMap.keySet()){
			Set<String> attributes = attributeForMultipleFieldsMap.get(value);
			PhraseMatchingContext pharseMatchingContext = null;
			for(String attribute : attributes){
				if(!attribute.isEmpty()){
					if(pharseMatchingContext == null){
						pharseMatchingContext = queryParse.onField(attribute);
					}
					else{
						pharseMatchingContext.andField(attribute);
					}
				}
			}
			if(pharseMatchingContext != null){
				Query luceneQuery = pharseMatchingContext.sentence(value).createQuery();
				combinedQuery.must(luceneQuery);
			}
				
		}
		return combinedQuery;
	}

	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindExactByAttribute(Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}

	@SuppressWarnings("rawtypes")
	private FullTextQuery createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		combinedQuery = addMustWildcardWithStartingAttributesOnFields(startSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private FullTextQuery createFullTextQueryForfindMultiFieldSameAttributesAndExactByAttributes(Map<String, Set<String>> attributeForMultipleFieldsMap,
			Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		combinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}

	@SuppressWarnings("rawtypes")
	private FullTextQuery createFullTextQueryForfindSetOfExactByAttributes(
			Set<Map<String, String>> setEqSentenceOnFields) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		for(Map<String, String> eqSentenceOnField : setEqSentenceOnFields){
			BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
			subCombinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, subCombinedQuery);
			combinedQuery.should(subCombinedQuery.createQuery());
		}
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private FullTextQuery createFullTextQueryForfindSetOfStartingUsingWildcardAndSetOfExactByAttributes(
			Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		for(Map<String, String> startSentenceOnField : setOfStartSentenceOnFields){
			for(Map<String, String> eqSentenceOnField : setOfEqSentenceOnFields){
			BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
			subCombinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, subCombinedQuery);
			subCombinedQuery = addMustWildcardWithStartingAttributesOnFields(startSentenceOnField, qb, subCombinedQuery);
			combinedQuery.should(subCombinedQuery.createQuery());
			}
		}
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}

	@SuppressWarnings("rawtypes")
	private FullTextQuery createFullTextQueryForfindSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(
			Set<Map<String, Set<String>>> setAttributeForMultipleFieldsMaps,
			Set<Map<String, String>> setEqSentenceOnFields) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		for(Map<String, Set<String>> attributeForMultipleFieldsMap : setAttributeForMultipleFieldsMaps){
			for(Map<String, String> eqSentenceOnField : setEqSentenceOnFields){
				BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
				subCombinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, subCombinedQuery);
				subCombinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, subCombinedQuery);
				combinedQuery.should(subCombinedQuery.createQuery());
			}
		}
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findExactByAttributes(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindExactByAttribute(eqSentenceOnField);
		return fullTextQuery.list();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		return fullTextQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMultiFieldSameAttributesAndExactByAttributes(
			Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSetOfExactByAttributes(Set<Map<String, String>> setEqSentenceOnFields) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfExactByAttributes(setEqSentenceOnFields);
		return fullTextQuery.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSetOfStartingUsingWildcardAndSetOfExactByAttributes(
			Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfStartingUsingWildcardAndSetOfExactByAttributes(
				setOfStartSentenceOnFields, setOfEqSentenceOnFields);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(
			Set<Map<String, Set<String>>> setAttributeForMultipleFieldsMaps, Set<Map<String, String>> setEqSentenceOnFields) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setAttributeForMultipleFieldsMaps, setEqSentenceOnFields);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findSetOfMultiFieldSameAttributesAndSetOfExactByAttributesPaginated(Set<Map<String, Set<String>>> setAttributeForMultipleFieldsMaps, 
			Set<Map<String, String>> setEqSentenceOnFields, int index, int paginationSize){
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setAttributeForMultipleFieldsMaps, setEqSentenceOnFields);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindExactByAttribute(eqSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findStartingUsingWildcardAndExactByAttributesPaginated(Map<String, String> startSentenceOnField,
			Map<String, String> eqSentenceOnField, int index, int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSetOfExactByAttributesPaginated(Set<Map<String, String>> setEqSentenceOnFields, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfExactByAttributes(setEqSentenceOnFields);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSetOfStartingUsingWildcardAndSetOfExactByAttributesPaginated(
			Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields,
			int index, int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfStartingUsingWildcardAndSetOfExactByAttributes(setOfStartSentenceOnFields, setOfEqSentenceOnFields);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMultiFieldSameAttributesAndExactByAttributesPaginated(
			Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}

	@Override
	public Integer countExactByAttributes(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindExactByAttribute(eqSentenceOnField);
		return fullTextQuery.getResultSize();
	}

	@Override
	public Integer countStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField,
			Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		return fullTextQuery.getResultSize();
	}

	@Override
	public Integer countMultiFieldSameAttributesAndExactByAttributes(
			Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
		return fullTextQuery.getResultSize();
	}

	@Override
	public Integer countSetOfExactByAttributes(Set<Map<String, String>> setOfeqSentenceOnFields) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfExactByAttributes(setOfeqSentenceOnFields);
		return fullTextQuery.getResultSize();
	}

	@Override
	public Integer countSetOfStartingUsingWildcardAndSetOfExactByAttributes(
			Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfStartingUsingWildcardAndSetOfExactByAttributes(setOfStartSentenceOnFields, setOfEqSentenceOnFields);
		return fullTextQuery.getResultSize();
	}
	
	@Override
	public Integer countSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(
			Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Set<Map<String, String>> setOfEqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMap, setOfEqSentenceOnField);
		return fullTextQuery.getResultSize();
	}

	
}
