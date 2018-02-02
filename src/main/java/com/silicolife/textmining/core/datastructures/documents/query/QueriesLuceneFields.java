package com.silicolife.textmining.core.datastructures.documents.query;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum QueriesLuceneFields {
	
	
	keywords("q_keywordsNCS", "q_keywordsCS", "", null ),
	organism ("q_organismNCS","q_organismCS", "", null),
	name("q_query_nameNCS","q_query_nameCS","quNameSort",SortField.Type.STRING),
	publicationsSize("", "", "quMatchingPublicationsSort", SortField.Type.INT),
	date ("","","quDateSort",SortField.Type.LONG ),
	none("","","none",null);
	
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	
	QueriesLuceneFields(String NCS, String CS, String SORT, SortField.Type SORTTYPE ){
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	
	
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		QueriesLuceneFields queryLuceneField = QueriesLuceneFields.valueOf(field);

			if(searchProperties.isCaseSensitive())
				return queryLuceneField.CS;
			else
				return queryLuceneField.NCS;
		
		
	}
	
	public static String getSortField(String field) {
		QueriesLuceneFields qLuceneField = QueriesLuceneFields.valueOf(field);
		return qLuceneField.SORT;
	}
	
	public static SortField.Type getSortType(String field) {
		QueriesLuceneFields qLuceneField = QueriesLuceneFields.valueOf(field);
		return qLuceneField.SORTTYPE;
	}


}
