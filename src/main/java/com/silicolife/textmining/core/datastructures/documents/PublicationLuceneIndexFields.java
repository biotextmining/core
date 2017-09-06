package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum PublicationLuceneIndexFields {

	title("titleCS", "titleNCS"),
	authors ("authorsCS","authorsNCS"),
	journal ("journalCS","journalNCS" ),
	abstractSection("abstractCS", "abstractNCS" ),
	fullContent("fullContentCS", "fullContentNCS"),
	notes("notesCS","notesNCS"),
	queryId("queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId");
	corpusId("corpusHasPublicationses.id.chpCorpusId", "corpusHasPublicationses.id.chpCorpusId");
	
	
	private final String cS;
	private final String nCS;
	
	private PublicationLuceneIndexFields(String cS, String nCS) {
		this.cS = cS;
		this.nCS = nCS;
	}
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		PublicationLuceneIndexFields pubLuceneField = PublicationLuceneIndexFields.valueOf(field);
		
		if(searchProperties.isCaseSensitive())
			return pubLuceneField.cS;
		else
			return pubLuceneField.nCS;
		
	}
	
}
