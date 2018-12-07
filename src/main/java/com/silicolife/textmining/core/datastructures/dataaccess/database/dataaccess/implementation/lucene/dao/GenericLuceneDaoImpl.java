package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.PhraseContext;
import org.hibernate.search.query.dsl.PhraseMatchingContext;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;

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
	
	private QueryBuilder getQueryBuilderForAuth(FullTextSession fullTextSession) {
		return fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(AuthUserDataObjects.class).get();
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
	private BooleanJunction<BooleanJunction> addShouldPhraseWithAttributesOnFields(Map<String, String> eqSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			combinedQuery.should(luceneQuery); //must(luceneQuery);
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addShouldPhraseWithMultipleAttributesOnFields(Map<String, List<String>> eqSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			List<String> values = eqSentenceOnField.get(field);
			for(String value : values) {
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			combinedQuery.should(luceneQuery); //must(luceneQuery);
			}
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addMustFieldsWithShouldPhraseWithMultipleAtributes(Map<String, List<String>> eqSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery){	
		for(String field : eqSentenceOnField.keySet()){
			List<String> values = eqSentenceOnField.get(field);
			BooleanJunction<BooleanJunction> fieldQuery = qb.bool();
			for(String value : values) {
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			fieldQuery.should(luceneQuery); //must(luceneQuery);
			}
			combinedQuery.must(fieldQuery.createQuery());
		}
		return combinedQuery;				
			}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addShouldKeywordsWithAttributesOnFields(Map<String, String> eqSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.keyword()
					.onField(field)
					.matching(value)
					.createQuery();
			combinedQuery.should(luceneQuery); //must(luceneQuery);
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addMustKeywordsWithAttributesOnFields(Map<String, String> eqSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.keyword()
					.onField(field)
					.matching(value)
					.createQuery();
			combinedQuery.must(luceneQuery); //must(luceneQuery);
		}
		return combinedQuery;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addShouldWMustPhraseWithAttributesOnFields(Map<String, String> eqSentenceOnField,Map<String, String> eqMustSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			combinedQuery.should(luceneQuery); //must(luceneQuery);
		}
		combinedQuery.must(combinedQuery.createQuery());
		//combinedQuery.setMinimumShouldMatch(2);
		for(String field : eqMustSentenceOnField.keySet()){
			String value = eqMustSentenceOnField.get(field);
			Query luceneQuery = qb.phrase()
					.onField(field)
					.sentence(value)
					.createQuery();
			combinedQuery.must(luceneQuery); //must(luceneQuery);
		}
		return combinedQuery;
	}
	
	@SuppressWarnings("rawtypes")
	private BooleanJunction<BooleanJunction> addShouldWMustKeywordsWithAttributesOnFields(Map<String, String> eqSentenceOnField,Map<String, String> eqMustSentenceOnField,  QueryBuilder qb,
			BooleanJunction<BooleanJunction> combinedQuery) {
		for(String field : eqSentenceOnField.keySet()){
			String value = eqSentenceOnField.get(field);
			Query luceneQuery = qb.keyword()
					.onField(field)
					.matching(value)
					.createQuery();
			combinedQuery.should(luceneQuery); //must(luceneQuery);
		}
		combinedQuery.must(combinedQuery.createQuery());
		//combinedQuery.setMinimumShouldMatch(2);
		for(String field : eqMustSentenceOnField.keySet()){
			String value = eqMustSentenceOnField.get(field);
			Query luceneQuery = qb.keyword()
					.onField(field)
					.matching(value)
					.createQuery();
			combinedQuery.must(luceneQuery); //must(luceneQuery);
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
	private FullTextQuery createFullTextQueryForAuthUsersfindExactByAttribute(Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilderForAuth(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addMustPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
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
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindNotExactByAttribute(Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindNotExactByAttribute(Map<String, String> eqSentenceOnField, Map<String, List<String>> filtersOnFields) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindNotExactByAttributeWAuth(Map<String, String> eqSentenceOnField, Map<String, String> authFields, String idField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		Query permissionsQuery= createQueryToGetResourcesWPermissions(authFields,qb,idField);
		combinedQuery.must(permissionsQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindNotExactByAttributeWKeywords(Map<String, String> eqSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldKeywordsWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindNotExactByAttributeWKeywordsWAuth(Map<String, String> eqSentenceOnField, Map<String, String> authFields, String idField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldKeywordsWithAttributesOnFields(eqSentenceOnField, qb, combinedQuery);
		Query permissionsQuery = createQueryToGetResourcesWPermissions(authFields, qb, idField);
		combinedQuery.must(permissionsQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindMixedByAttribute(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldWMustPhraseWithAttributesOnFields(eqSentenceOnField,eqMustSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,Map<Map<String, String>, Set<Map<String, Set<String>>>> uniqueRestrictions, Map<String, String> eqMustSentenceOnField,Map<String, String> permissionFields,String idField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		BooleanJunction<BooleanJunction> sentenceQuery = qb.bool();	
		if(setOfAttributeForMultipleFieldsMap.size()>0) {
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			
			for(Map<String, Set<String>> attributeForMultipleFieldsMap : setOfAttributeForMultipleFieldsMap){
					BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
					subCombinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, subCombinedQuery);
					atributeQuery.should(subCombinedQuery.createQuery());
			}
			
			sentenceQuery.should(atributeQuery.createQuery());
		}
		
		if(uniqueRestrictions.size()>0) {
			
			for(Map<String, String> restrictions : uniqueRestrictions.keySet()) {
				for(Map<String, Set<String>> attributeForMultipleFieldsMap :  uniqueRestrictions.get(restrictions)){
					BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
					//System.out.println(restrictions.toString());
					//System.out.println(uniqueRestrictions.get(restrictions).toString());
					BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
					subCombinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, subCombinedQuery);
					subCombinedQuery = addMustPhraseWithAttributesOnFields(restrictions, qb, subCombinedQuery);
					atributeQuery.must(subCombinedQuery.createQuery());
					sentenceQuery.should(atributeQuery.createQuery());
			}
			}
		}
		
		if(uniqueRestrictions.size()>0 || setOfAttributeForMultipleFieldsMap.size()>0) {
			//System.out.println("must Sentence");
			combinedQuery.must(sentenceQuery.createQuery());
		}
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(permissionFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			//System.out.println(a.getId().getAudoUidResource());
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		//QueryBuilder qb2 = getQueryBuilder(fullTextSession);
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField,Map<String, String> permissionFields,String idField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
			
		if(setOfAttributeForMultipleFieldsMap.size()>0) {
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			
			for(Map<String, Set<String>> attributeForMultipleFieldsMap : setOfAttributeForMultipleFieldsMap){
					BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
					subCombinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, subCombinedQuery);
					atributeQuery.should(subCombinedQuery.createQuery());
			}
			
			combinedQuery.must(atributeQuery.createQuery());
		}
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(permissionFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			//System.out.println(a.getId().getAudoUidResource());
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		//QueryBuilder qb2 = getQueryBuilder(fullTextSession);
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTableWPermissions(Map<String, String> eqSentenceOnField,
			Map<Map<String, String>, Map<String, String>> uniqueRestrictions,
		 Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields,String idField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		BooleanJunction<BooleanJunction> sentenceQuery = qb.bool();
		if(eqSentenceOnField.size()>0) {
			//System.out.println(eqSentenceOnField.toString());
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			if(isPhrase) {
				atributeQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}else {
				atributeQuery = addShouldKeywordsWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}
			sentenceQuery.should(atributeQuery.createQuery());
		}
		
		
		if(uniqueRestrictions.size()>0) {
			for(Map<String, String> restrictions : uniqueRestrictions.keySet()) {
				//System.out.println(restrictions.toString());
				//System.out.println(uniqueRestrictions.get(restrictions).toString());
				BooleanJunction<BooleanJunction> restrictionQuery = qb.bool();
				if(isPhrase) {
					restrictionQuery = addMustPhraseWithAttributesOnFields(uniqueRestrictions.get(restrictions), qb, restrictionQuery);
						
				}else {
					restrictionQuery = addMustKeywordsWithAttributesOnFields(uniqueRestrictions.get(restrictions), qb, restrictionQuery);
				}
				restrictionQuery = addMustPhraseWithAttributesOnFields(restrictions, qb, restrictionQuery);
				sentenceQuery.should(restrictionQuery.createQuery());
			}
		}
		
		if(eqSentenceOnField.size()>0 || uniqueRestrictions.size()>0)
			combinedQuery.must(sentenceQuery.createQuery());
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(permissionFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			//System.out.println(a.getId().getAudoUidResource());
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		//QueryBuilder qb2 = getQueryBuilder(fullTextSession);
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	

	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTableWPermissions(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,Map<String, String> permissionFields,String idField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		if(eqSentenceOnField.size()>0) {
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			if(isPhrase) {
				atributeQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}else {
				atributeQuery = addShouldKeywordsWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}
			combinedQuery.must(atributeQuery.createQuery());
		}
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(permissionFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			//System.out.println(a.getId().getAudoUidResource());
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		//QueryBuilder qb2 = getQueryBuilder(fullTextSession);
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public Query createShouldPhraseQuery(Map<String, String> eqSentenceOnField, QueryBuilder qb) {
		BooleanJunction<BooleanJunction> query = qb.bool();
		query = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, query);
		return query.createQuery();
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public Query createMustQuery(List<Query> queries, QueryBuilder qb) {
		BooleanJunction<BooleanJunction> query = qb.bool();
		for(Query q : queries) {
			query.must(q);
		}
		return query.createQuery();
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public Query createMustNot(List<Query> queries, QueryBuilder qb) {
		BooleanJunction<BooleanJunction> query = qb.bool();
		for(Query q : queries) {
			query.must(q).not();
		}
		return query.createQuery();
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public Query createShouldQuery(List<Query> queries, QueryBuilder qb) {
		BooleanJunction<BooleanJunction> query = qb.bool();
		for(Query q : queries) {
			query.should(q);
		}
		return query.createQuery();
	}
	
	@Override
	public QueryBuilder createQueryBuilder() {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		return qb;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTable(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		if(eqSentenceOnField.size()>0) {
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			if(isPhrase) {
				atributeQuery = addShouldPhraseWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}else {
				atributeQuery = addShouldKeywordsWithAttributesOnFields(eqSentenceOnField, qb, atributeQuery);
			}
			combinedQuery.must(atributeQuery.createQuery());
		}
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTable(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
			
		if(setOfAttributeForMultipleFieldsMap.size()>0) {
			BooleanJunction<BooleanJunction> atributeQuery = qb.bool();
			
			for(Map<String, Set<String>> attributeForMultipleFieldsMap : setOfAttributeForMultipleFieldsMap){
					BooleanJunction<BooleanJunction> subCombinedQuery = qb.bool();
					subCombinedQuery = addMustPhraseWithMultiFieldOnAttribute(attributeForMultipleFieldsMap, qb, subCombinedQuery);
					atributeQuery.should(subCombinedQuery.createQuery());
			}
			
			combinedQuery.must(atributeQuery.createQuery());
		}
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForWebTable(Query q, Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();

			
		combinedQuery.must(q);
		
		
		if(eqMustSentenceOnField.size()>0) {
			combinedQuery = addMustPhraseWithAttributesOnFields(eqMustSentenceOnField, qb, combinedQuery);	
		}
		
		if(filtersOnFields.size()>0) {
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		}
		
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindMixedByAttribute(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldWMustPhraseWithAttributesOnFields(eqSentenceOnField,eqMustSentenceOnField, qb, combinedQuery);
		combinedQuery = addMustFieldsWithShouldPhraseWithMultipleAtributes(filtersOnFields,qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private Query createQueryToGetResourcesWPermissions(Map<String, String> authFields,QueryBuilder qb, String idField) {
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(authFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		return combinedQuery2.createQuery();
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindMixedByAttributeWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldWMustPhraseWithAttributesOnFields(eqSentenceOnField,eqMustSentenceOnField, qb, combinedQuery);
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(authFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			//System.out.println(a.getId().getAudoUidResource());
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		//QueryBuilder qb2 = getQueryBuilder(fullTextSession);
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindMixedByAttributeWKeywords(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldWMustKeywordsWithAttributesOnFields(eqSentenceOnField,eqMustSentenceOnField, qb, combinedQuery);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combinedQuery.createQuery());
		return fullTextQuery;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private FullTextQuery createFullTextQueryForfindMixedByAttributeWKeywordsWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField
			, Map<String, String> authFields, String idField) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = getQueryBuilder(fullTextSession);
		BooleanJunction<BooleanJunction> combinedQuery = qb.bool();
		combinedQuery = addShouldWMustKeywordsWithAttributesOnFields(eqSentenceOnField,eqMustSentenceOnField, qb, combinedQuery);
		List<AuthUserDataObjects> l = this.findExactByAttributesForAuth(authFields);
		Map<String, List<String>> ids = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			  list.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		ids.put(idField,list);
		BooleanJunction<BooleanJunction> combinedQuery2 = qb.bool();
		combinedQuery2= this.addShouldPhraseWithMultipleAttributesOnFields(ids, qb,combinedQuery2 );
		combinedQuery.must(combinedQuery2.createQuery());
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuthUserDataObjects> findExactByAttributesForAuth(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForAuthUsersfindExactByAttribute(eqSentenceOnField);
		//fullTextQuery.setProjection("audoUidResource");
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributes(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttribute(eqSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWAuth(Map<String, String> eqSentenceOnField, Map<String, String> authFields,String idField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWAuth(eqSentenceOnField, authFields, idField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywords(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywords(eqSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywordsWAuth(Map<String, String> eqSentenceOnField, Map<String, String> authFields,String idField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywordsWAuth(eqSentenceOnField,authFields,idField);
		return fullTextQuery.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributes (Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttribute(eqSentenceOnField, eqMustSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWAuth (Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, String> authFields,String idField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		return fullTextQuery.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywords (Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField ) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywordsWAuth (Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField
			, Map<String, String> authFields,String idField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywordsWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		return fullTextQuery.list();
	}
	
	

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object[]> findStartingUsingWildcardAndExactByAttributesWithProjection(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField, List<String> projections) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		for(String projection : projections)
			fullTextQuery.setProjection(projection);
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
	public List<T> findMixedByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			 Map<String, String> authFields,String idField ,int index,int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			 Map<String, String> authFields,String idField ,int index,int paginationSize,String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesPaginated(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttribute(eqSentenceOnField, eqMustSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, int index, int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttribute(eqSentenceOnField, eqMustSentenceOnField, filtersOnFields);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTablePaginated(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(eqSentenceOnField, eqMustSentenceOnField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTablePaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(setOfAttributeForMultipleFieldsMap, eqMustSentenceOnField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTableWExpressionPaginated(Query q, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(q, eqMustSentenceOnField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTableWPermissionsPaginated(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields,String idField,Map<String, List<String>> filtersOnFields, boolean isPhrase, int index,
			 int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(eqSentenceOnField, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTableWPermissionsPaginated(Map<String, String> eqSentenceOnField,
			Map<Map<String, String>, Map<String, String>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields,String idField,Map<String, List<String>> filtersOnFields, boolean isPhrase, int index,
			 int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(eqSentenceOnField,uniqueRestrictions, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTableWPermissionsPaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, 
			Map<Map<String, String>, Set<Map<String, Set<String>>>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields,String idField,Map<String, List<String>> filtersOnFields, 
			boolean isPhrase, int index,
			 int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(setOfAttributeForMultipleFieldsMap, uniqueRestrictions,eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForWebTableWPermissionsPaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields,String idField,Map<String, List<String>> filtersOnFields, boolean isPhrase, int index,
			 int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(setOfAttributeForMultipleFieldsMap, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		if(sortField!="none") {
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		}
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTable(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(eqSentenceOnField, eqMustSentenceOnField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTableWExpression(Query q, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(q, eqMustSentenceOnField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTableWPermissions(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, 
			Map<String, String> permissionFields,String idField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(eqSentenceOnField, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size();//too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTableWPermissions(Map<String, String> eqSentenceOnField,
			Map<Map<String, String>, Map<String, String>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField, 
			Map<String, String> permissionFields,String idField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(eqSentenceOnField,uniqueRestrictions, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField, 
			Map<String, String> permissionFields,String idField, Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(setOfAttributeForMultipleFieldsMap, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, 
			Map<Map<String, String>, Set<Map<String, Set<String>>>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField, 
			Map<String, String> permissionFields,String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTableWPermissions(setOfAttributeForMultipleFieldsMap,uniqueRestrictions, eqMustSentenceOnField,permissionFields, idField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countForWebTable(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap, Map<String, String> eqMustSentenceOnField, 
			Map<String, List<String>> filtersOnFields, boolean isPhrase) {
		FullTextQuery fullTextQuery = createFullTextQueryForWebTable(setOfAttributeForMultipleFieldsMap, eqMustSentenceOnField, filtersOnFields, isPhrase);
		
		//return fullTextQuery.list().size(); //too slow, since we only want lucene queries, it's pointless forcing it going to check de DB.
		return fullTextQuery.getResultSize();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, int index,
			int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttribute(eqSentenceOnField, eqMustSentenceOnField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywordsPaginated(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywordsPaginatedWSort(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField, int index,
			int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, String> authFields,String idField,int index,int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywordsWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMixedByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, String> authFields,String idField,int index,int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywordsWAuth(eqSentenceOnField, eqMustSentenceOnField, authFields, idField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttribute(eqSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField, int index,
			int paginationSize,  String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttribute(eqSentenceOnField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField, 
			Map<String, List<String>> filtersOnFields,int index, int paginationSize,  String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttribute(eqSentenceOnField, filtersOnFields);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields,String idField, int index,int paginationSize,  String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWAuth(eqSentenceOnField,authFields, idField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields,String idField, int index,int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWAuth(eqSentenceOnField,authFields, idField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywordsPaginated(Map<String, String> eqSentenceOnField, int index,
			int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywords(eqSentenceOnField);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywordsPaginatedWSort(Map<String, String> eqSentenceOnField, int index,
			int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywords(eqSentenceOnField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields,String idField,int index,int paginationSize, String sortField, SortField.Type sortType, boolean asc) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywordsWAuth(eqSentenceOnField, authFields, idField);
		Sort s = new Sort(new SortField( sortField, sortType, asc ));
		fullTextQuery.setSort(s);
		fullTextQuery.setFirstResult(index);
		fullTextQuery.setMaxResults(paginationSize);
		fullTextQuery.setFetchSize(paginationSize);
		return fullTextQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findNotExactByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields,String idField,int index,int paginationSize) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywordsWAuth(eqSentenceOnField, authFields, idField);
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
	public Integer countNotExactByAttributes(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttribute(eqSentenceOnField);
		return fullTextQuery.getResultSize();
	}
	
	@Override
	public Integer countNotExactByAttributesWKeywords(Map<String, String> eqSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindNotExactByAttributeWKeywords(eqSentenceOnField);
		return fullTextQuery.getResultSize();
	}
	
	
	
	@Override
	public Integer countMixedByAttributes(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttribute(eqSentenceOnField, eqMustSentenceOnField);
		return fullTextQuery.getResultSize();
	}
	
	@Override
	public Integer countMixedByAttributesWKeywords(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField) {
		FullTextQuery fullTextQuery = createFullTextQueryForfindMixedByAttributeWKeywords(eqSentenceOnField, eqMustSentenceOnField);
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
