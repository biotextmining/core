package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField.Type;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;

public interface IGenericLuceneDao<T> {

	public List<T> findExactByAttributes(Map<String, String> eqSentenceOnField);
	
	public List<T> findNotExactByAttributes(Map<String, String> eqSentenceOnField);
	
	public List<T> findStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributes(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField);
		
	public List<Object[]> findStartingUsingWildcardAndExactByAttributesWithProjection(Map<String, String> startSentenceOnField,
			Map<String, String> eqSentenceOnField, List<String> projections);

	public List<T> findMixedByAttributes(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField);
	
	public List<T> findNotExactByAttributesWKeywords(Map<String, String> eqSentenceOnField);
	
	//set of attributes 
	
	public List<T> findSetOfExactByAttributes(Set<Map<String, String>> setEqSentenceOnFields);
	
	public List<T> findSetOfStartingUsingWildcardAndSetOfExactByAttributes(Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields);
	
	public List<T> findSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps, Set<Map<String, String>> setOfEqSentenceOnFields);
	
	// pagination methods
	
	public List<T> findExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findStartingUsingWildcardAndExactByAttributesPaginated(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributesPaginated(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findMixedByAttributesPaginated(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, int index, int paginationSize);
	
	public List<T> findNotExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index, int paginationSize);

	public List<T> findMixedByAttributesWKeywords(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField);

	public List<T> findMixedByAttributesWKeywordsPaginated(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, int index, int paginationSize);
	
	public List<T> findNotExactByAttributesWKeywordsPaginated(Map<String, String> eqSentenceOnField, int index,
			int paginationSize);
	
	//set of attributes pagination
	
	public List<T> findSetOfExactByAttributesPaginated(Set<Map<String, String>> setEqSentenceOnFields, int index, int paginationSize);
	
	public List<T> findSetOfStartingUsingWildcardAndSetOfExactByAttributesPaginated(Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields, int index, int paginationSize);
	
	public List<T> findSetOfMultiFieldSameAttributesAndSetOfExactByAttributesPaginated(Set<Map<String, Set<String>>> setAttributeForMultipleFieldsMaps, Set<Map<String, String>> setEqSentenceOnFields, int index, int paginationSize);
	
	//counting methods
	
	public Integer countExactByAttributes(Map<String, String> eqSentenceOnField);
	
	public Integer countStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField);
	
	public Integer countMultiFieldSameAttributesAndExactByAttributes(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField);
	
	// set of counting methods
	
	public Integer countSetOfExactByAttributes(Set<Map<String, String>> setOfeqSentenceOnFields);
	
	public Integer countSetOfStartingUsingWildcardAndSetOfExactByAttributes(Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields);
	
	public Integer countSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps, Set<Map<String, String>> setOfEqSentenceOnFields);


	public Integer countNotExactByAttributes(Map<String, String> eqSentenceOnField);

	public Integer countMixedByAttributes(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField);

	public Integer countMixedByAttributesWKeywords(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField);

	public Integer countNotExactByAttributesWKeywords(Map<String, String> eqSentenceOnField);

	public List<AuthUserDataObjects> findExactByAttributesForAuth(Map<String, String> eqSentenceOnField);

	// Lucene methods that filters results based on the permission to the resource (only returns resources that the user has some permission on them)
	public List<T> findMixedByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField, int index,
			int paginationSize);


	public List<T> findMixedByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField, int index,
			int paginationSize);

	public List<T> findNotExactByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields, String idField, int index, int paginationSize);

	public List<T> findNotExactByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields, String idField, int index, int paginationSize);

	public List<T> findMixedByAttributesWAuth(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, String> authFields, String idField);

	public List<T> findMixedByAttributesWKeywordsWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField);

	public List<T> findNotExactByAttributesWAuth(Map<String, String> eqSentenceOnField, Map<String, String> authFields,
			String idField);

	public List<T> findNotExactByAttributesWKeywordsWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields, String idField);

	public List<T> findMixedByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, int index, int paginationSize, String sortField, Type sortType,
			boolean asc);

	public List<T> findMixedByAttributesWKeywordsPaginatedWSort(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, int index, int paginationSize, String sortField, Type sortType,
			boolean asc);

	public List<T> findNotExactByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public List<T> findNotExactByAttributesWKeywordsPaginatedWSort(Map<String, String> eqSentenceOnField, int index,
			int paginationSize, String sortField, Type sortType, boolean asc);

	public List<T> findMixedByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField, int index,
			int paginationSize, String sortField, Type sortType, boolean asc);

	public List<T> findMixedByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> authFields, String idField, int index,
			int paginationSize, String sortField, Type sortType, boolean asc);

	public List<T> findNotExactByAttributesPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields, String idField, int index, int paginationSize, String sortField,
			Type sortType, boolean asc);

	public List<T> findNotExactByAttributesWKeywordsPaginatedWAuth(Map<String, String> eqSentenceOnField,
			Map<String, String> authFields, String idField, int index, int paginationSize, String sortField,
			Type sortType, boolean asc);

	public List<T> findMixedByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, int index,
			int paginationSize, String sortField, Type sortType, boolean asc);


	public List<T> findNotExactByAttributesPaginatedWSort(Map<String, String> eqSentenceOnField,
			Map<String, List<String>> filtersOnFields, int index, int paginationSize, String sortField, Type sortType,
			boolean asc);

	public List<T> findForWebTablePaginated(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public Integer countForWebTable(Map<String, String> eqSentenceOnField, Map<String, String> eqMustSentenceOnField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase);

	public List<T> findForWebTableWPermissionsPaginated(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public Integer countForWebTableWPermissions(Map<String, String> eqSentenceOnField,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase);

	public QueryBuilder createQueryBuilder();

	public Query createShouldQuery(List<Query> queries, QueryBuilder qb);

	public Query createMustQuery(List<Query> queries, QueryBuilder qb);

	public Query createShouldPhraseQuery(Map<String, String> eqSentenceOnField, QueryBuilder qb);

	public List<T> findForWebTableWExpressionPaginated(Query q, Map<String, String> eqMustSentenceOnField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public Integer countForWebTableWExpression(Query q, Map<String, String> eqMustSentenceOnField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase);

	public Query createMustNot(List<Query> queries, QueryBuilder qb);

	public List<T> findForWebTableWPermissionsPaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public Integer countForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase);


	public List<T> findForWebTablePaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, boolean isPhrase,
			int index, int paginationSize, String sortField, Type sortType, boolean asc);

	public Integer countForWebTable(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<String, String> eqMustSentenceOnField, Map<String, List<String>> filtersOnFields, boolean isPhrase);

	public List<T> findForWebTableWPermissionsPaginated(Map<String, String> eqSentenceOnField,
			Map<Map<String, String>, Map<String, String>> uniqueRestrictions, Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields, String idField, Map<String, List<String>> filtersOnFields,
			boolean isPhrase, int index, int paginationSize, String sortField, Type sortType, boolean asc);

	public List<T> findForWebTableWPermissionsPaginated(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<Map<String, String>, Set<Map<String, Set<String>>>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase, int index, int paginationSize,
			String sortField, Type sortType, boolean asc);

	public Integer countForWebTableWPermissions(Map<String, String> eqSentenceOnField,
			Map<Map<String, String>, Map<String, String>> uniqueRestrictions, Map<String, String> eqMustSentenceOnField,
			Map<String, String> permissionFields, String idField, Map<String, List<String>> filtersOnFields,
			boolean isPhrase);

	public Integer countForWebTableWPermissions(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap,
			Map<Map<String, String>, Set<Map<String, Set<String>>>> uniqueRestrictions,
			Map<String, String> eqMustSentenceOnField, Map<String, String> permissionFields, String idField,
			Map<String, List<String>> filtersOnFields, boolean isPhrase);








	

	
}
