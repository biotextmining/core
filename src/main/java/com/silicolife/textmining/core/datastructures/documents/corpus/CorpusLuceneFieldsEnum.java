package com.silicolife.textmining.core.datastructures.documents.corpus;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum CorpusLuceneFieldsEnum {
	
	name ("crp_nameNCS","crp_nameCS");
	
	private final String NCS;
	private final String CS;
	
	 CorpusLuceneFieldsEnum(String NCS,String CS) {
		this.NCS = NCS;
		this.CS = CS;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 CorpusLuceneFieldsEnum copusLuceneField = CorpusLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return copusLuceneField.CS;
				else
					return copusLuceneField.NCS;
		}

}
