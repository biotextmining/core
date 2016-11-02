package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao;

import java.util.List;
import java.util.Map;

public interface IGenericLuceneDao<T> {
	
	public List<T> findBySentenceOnField(String sentence, String field);

	public List<T> findByAttributes(Map<String, String> eqSentenceOnField);
}
