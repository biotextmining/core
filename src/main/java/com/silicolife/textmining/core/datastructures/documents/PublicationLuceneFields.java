package com.silicolife.textmining.core.datastructures.documents;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum PublicationLuceneFields {
	/*
	TITLEKNCS("titleKNCS"),
	TITLEKCS("titleKCS"),
	TITLEPNCS("titlePNCS"),
	TITLEPCS("titlePCS"),
	*/
	
	title("titleNCS", "titleCS", "titleNCS", "titleCS", "titleSCS", "titleSNC", "pubTitleSort", SortField.Type.STRING ),
	authors ("authorsNCS","authorsCS","authorsNCS", "authorsCS", "authorsSCS", "authorsSNCS", "pubAuthorsSort", SortField.Type.STRING),
	journal ("journalNCS","journalCS","journalNCS", "journalCS", "journalSCS", "journalSNCS", "", null),
	notes("notesNCS","notesCS","notesNCS","notesCS","notesSCS","notesSNCS","", null),
	abstractSection("abstractNCS", "abstractCS", "abstractNCS", "abstractCS", "abstractSCS", "abstractSNCS", "",null),
	fullContent("fullContentNCS", "fullContentCS","fullContentNCS","fullContentCS",  "fullContentSCS","fullContentSNCS", "", null  ),
	queryId("queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId", "queryHasPublicationses.id.qhbQueryId", "queryHasPublicationses.id.qhbQueryId", "", null),
	corpusId("corpusHasPublicationses.id.chpCorpusId","corpusHasPublicationses.id.chpCorpusId","corpusHasPublicationses.id.chpCorpusId","corpusHasPublicationses.id.chpCorpusId","corpusHasPublicationses.id.chpCorpusId","corpusHasPublicationses.id.chpCorpusId", "", null),
	yeardate("","","","","","","pubYeardateSort", SortField.Type.INT);
	/*
	AUTHORSKNCS("authorsKNCS"),
	AUTHORSKCS("authorsKCS"),
	AUTHORSPNCS("authorsPNCS"),
	AUTHORSPCS("authorsPCS"),
	
	JOURNALKNCS("journalKNCS"),
	JOURNALKCS("journalKCS"),
	JOURNALPNCS("journalPNCS"),
	JOURNALPCS("journalPCS"),
	
	ABSTRACTKNCS("abstractKNCS"),
	ABSTRACTKCS("abstractKCS"),
	ABSTRACTPNCS("abstractPNCS"),
	ABSTRACTPCS("abstractPCS"),
	
	FULLCONTENTKNCS("fullContentKNCS"),
	FULLCONTENTKCS("fullContentKCS"),
	FULLCONTENTPNCS("fullContentPNCS"),
	FULLCONTENTPCS("fullContentPCS");
	*/
	
	private final String kNCS;
	private final String kCS;
	private final String PNCS;
	private final String PCS;
	private final String SCS;
	private final String SNCS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	PublicationLuceneFields(String kNCS, String kCS,String PNCS, String PCS, String SCS,String SNCS, String SORT, SortField.Type SORTTYPE ){
		this.kNCS = kNCS;
		this.kCS = kCS;
		this.PNCS = PNCS;
		this.PCS = PCS;
		this.SCS = SCS;
		this.SNCS = SNCS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	
	
	/*
	private final String name;
	
	PublicationLuceneFields(String name){
		this.name = name;
	}
	*/
	
	/*
	public static PublicationLuceneFields getTitlebyAnalyser(String analyzer){
		switch(analyzer){
			case "KNCS":
				return PublicationLuceneFields.TITLEKNCS;
			
			case "KCS":
				return PublicationLuceneFields.TITLEKCS;
				
			case "PNCS":
				return PublicationLuceneFields.TITLEPNCS;
				
			case "PCS":
				return PublicationLuceneFields.TITLEPCS;
				
		}
		
		return PublicationLuceneFields.TITLEKNCS;
	}
	
	
	public static PublicationLuceneFields getAuthorsbyAnalyser(String analyzer){
		switch(analyzer){
			case "KNCS":
				return PublicationLuceneFields.AUTHORSKNCS;
			
			case "KCS":
				return PublicationLuceneFields.AUTHORSKCS;
				
			case "PNCS":
				return PublicationLuceneFields.AUTHORSPNCS;
				
			case "PCS":
				return PublicationLuceneFields.AUTHORSPCS;
				
		}
		
		return PublicationLuceneFields.AUTHORSKNCS;
	}
	
	public static PublicationLuceneFields getJournalbyAnalyser(String analyzer){
		switch(analyzer){
			case "KNCS":
				return PublicationLuceneFields.JOURNALKNCS;
			
			case "KCS":
				return PublicationLuceneFields.JOURNALKCS;
				
			case "PNCS":
				return PublicationLuceneFields.JOURNALPNCS;
				
			case "PCS":
				return PublicationLuceneFields.JOURNALPCS;
				
		}
		
		return PublicationLuceneFields.JOURNALKNCS;
	}
	
	public static PublicationLuceneFields getAbstractbyAnalyser(String analyzer){
		
		switch(analyzer){
			case "KNCS":
				return PublicationLuceneFields.ABSTRACTKNCS;
			
			case "KCS":
				return PublicationLuceneFields.ABSTRACTKCS;
				
			case "PNCS":
				return PublicationLuceneFields.ABSTRACTPNCS;
				
			case "PCS":
				return PublicationLuceneFields.ABSTRACTPCS;
				
		}
		
		return PublicationLuceneFields.ABSTRACTKNCS;
	}
	
public static PublicationLuceneFields getFullContentbyAnalyser(String analyzer){

		switch(analyzer){
			case "KNCS":
				return PublicationLuceneFields.FULLCONTENTKNCS;
			
			case "KCS":
				return PublicationLuceneFields.FULLCONTENTKCS;
				
			case "PNCS":
				return PublicationLuceneFields.FULLCONTENTPNCS;
				
			case "PCS":
				return PublicationLuceneFields.FULLCONTENTPCS;
				
		}
		
		return PublicationLuceneFields.FULLCONTENTKNCS;
	}

	*/
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		PublicationLuceneFields pubLuceneField = PublicationLuceneFields.valueOf(field);
		/*if(searchProperties.isSugestions()) {
			if(searchProperties.isCaseSensitive()) {
				return pubLuceneField.SCS;
			}else
			{
				return pubLuceneField.SNCS;
			}
		}
		else {*/
		if(searchProperties.isKeywords()){
			if(searchProperties.isCaseSensitive())
				return pubLuceneField.kCS;
			else
				return pubLuceneField.kNCS;
		}
		else{
			if(searchProperties.isCaseSensitive())
				return pubLuceneField.PCS;
			else
				return pubLuceneField.PNCS;
		}
		//}
	}
	
	public static String getSortField(String field) {
		PublicationLuceneFields pubLuceneField = PublicationLuceneFields.valueOf(field);
		return pubLuceneField.SORT;
	}
	
	public static SortField.Type getSortType(String field) {
		PublicationLuceneFields pubLuceneField = PublicationLuceneFields.valueOf(field);
		return pubLuceneField.SORTTYPE;
	}
	
	public String getKNCS(){
		return kNCS;
	}


}
