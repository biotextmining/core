package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGenericLuceneDao<T> {

	public List<T> findExactByAttributes(Map<String, String> eqSentenceOnField);
	
	public List<T> findStartingUsingWildcardAndExactByAttributes(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributes(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField);
	
	public List<T> findExactByAttributesPaginated(Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findStartingUsingWildcardAndExactByAttributesPaginated(Map<String, String> startSentenceOnField, Map<String, String> eqSentenceOnField, int index, int paginationSize);
	
	public List<T> findMultiFieldSameAttributesAndExactByAttributesPaginated(Map<String, Set<String>> attributeForMultipleFieldsMap, Map<String, String> eqSentenceOnField, int index, int paginationSize);
}
