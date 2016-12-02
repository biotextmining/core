package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGenericLuceneDao<T> {

	public List<T> findExactByAttributes(Map<String, String> eqSentenceOnField);
	
	public List<T> findStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributes(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField);
	
	//set of attributes 
	
	public List<T> findSetOfExactByAttributes(Set<Map<String, String>> setEqSentenceOnFields);
	
	public List<T> findSetOfStartingUsingWildcardAndSetOfExactByAttributes(Set<Map<String, String>> setOfStartSentenceOnFields, Set<Map<String, String>> setOfEqSentenceOnFields);
	
	public List<T> findSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps, Set<Map<String, String>> setOfEqSentenceOnFields);
	
	// pagination methods
	
	public List<T> findExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findStartingUsingWildcardAndExactByAttributesPaginated(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributesPaginated(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
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
}
