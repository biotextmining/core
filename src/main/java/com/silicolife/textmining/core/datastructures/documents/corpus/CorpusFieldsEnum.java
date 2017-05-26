package com.silicolife.textmining.core.datastructures.documents.corpus;

public enum CorpusFieldsEnum {
	
	description ("crp_corpus_name"),
	processesNumber ("corpus_has_processes"),
	publicationsNumber ("corpus_has_publications");
	
	
	private final String uniqueIdentifier;
	
	CorpusFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
