package com.silicolife.textmining.core.datastructures.documents.query;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum QueriesLuceneFields {
	
	
	keywords("q_keywordsNCS", "q_keywordsCS" ),
	organism ("q_organismNCS","q_organismCS");
	
	
	private final String NCS;
	private final String CS;
	
	
	QueriesLuceneFields(String NCS, String CS ){
		this.NCS = NCS;
		this.CS = CS;
	}
	
	
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		QueriesLuceneFields queryLuceneField = QueriesLuceneFields.valueOf(field);

			if(searchProperties.isCaseSensitive())
				return queryLuceneField.CS;
			else
				return queryLuceneField.NCS;
		
		
	}


}
