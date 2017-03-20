package com.silicolife.textmining.core.datastructures.documents;

public enum PublicationFieldsEnum {
	title ("pubTitle"),
	authors ("pubAuthors"),
	yeardate ("pubYeardate");
	
	
	private final String uniqueIdentifier;
	
	PublicationFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
