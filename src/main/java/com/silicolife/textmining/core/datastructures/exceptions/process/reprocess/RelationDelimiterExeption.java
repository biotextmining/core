package com.silicolife.textmining.core.datastructures.exceptions.process.reprocess;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;


public class RelationDelimiterExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RelationDelimiterExeption()
	{
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.re.relation.error.impossibledetectrelationdelimiter"));
	}
	public RelationDelimiterExeption(String e){
		super(e);
	}
	

}
