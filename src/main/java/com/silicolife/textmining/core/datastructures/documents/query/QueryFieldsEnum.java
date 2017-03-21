package com.silicolife.textmining.core.datastructures.documents.query;

public enum QueryFieldsEnum {
	
	name ("qu_query_name"), //quQueryName
	date ("qu_query_date"), //quQueryDate
	publicationsSize ("qu_matching_publications"); //quMatchingPublications
	
	
	private final String uniqueIdentifier;
	
	QueryFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	

}
