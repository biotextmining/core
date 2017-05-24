package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum PublicationLuceneFields {
	/*
	TITLEKNCS("titleKNCS"),
	TITLEKCS("titleKCS"),
	TITLEPNCS("titlePNCS"),
	TITLEPCS("titlePCS"),
	*/
	
	title("titleKNCS", "titleKCS", "titlePNCS", "titlePCS" ),
	authors ("authorsKNCS","authorsKCS","authorsPNCS", "authorsPCS"),
	journal ("journalKNCS","journalKCS","journalPNCS", "journalPCS" ),
	abstractSection("abstractKNCS", "abstractKCS", "abstractPNCS", "abstractPCS"),
	fullContent("fullContentKNCS", "fullContentKCS","fullContentPNCS", "fullContentPCS" ),
	queryId("queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId");
	
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
	
	PublicationLuceneFields(String kNCS, String kCS,String PNCS, String PCS ){
		this.kNCS = kNCS;
		this.kCS = kCS;
		this.PNCS = PNCS;
		this.PCS = PCS;
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
	}
	
	public String getKNCS(){
		return kNCS;
	}

}
