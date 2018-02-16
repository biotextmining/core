package com.silicolife.textmining.core.datastructures.corpora;

import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfigurationGlobal;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationGlobalImpl extends CorpusCreateConfigurationImpl implements ICorpusCreateConfigurationGlobal {
	
	private String searchString;
	public  static final String  configurationUID = "corpus.globalSearchCreation";
	
	public CorpusCreateConfigurationGlobalImpl() {
		super();
	}
	
	public CorpusCreateConfigurationGlobalImpl( String corpusName,String notes,
			CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,Properties properties, CorpusCreateSourceEnum corpusSource, String searchString) {
		super(corpusName, notes, corpusTextType, processJournalRetrievalBeforeNeeded,properties,corpusSource );
		this.searchString = searchString;
	}
	
	@Override
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	@JsonIgnore
	public void setDocumentsIDs(Set<Long> documentsIDs) {
		super.setDocumentsIDs(documentsIDs);
	}

	@Override
	public String toString() {
		return "CorpusCreateConfigurationGlobalImpl [searchString=" + searchString + ", getCorpusName()="
				+ getCorpusName() + ", getDocuments()=" + getDocuments() + ", getCorpusTextType()="
				+ getCorpusTextType() + ", isProcessJournalRetrievalBeforeNeeded()="
				+ isProcessJournalRetrievalBeforeNeeded() + ", getProperties()=" + getProperties()
				+ ", getCorpusNotes()=" + getCorpusNotes() + ", getConfigurationUID()=" + getConfigurationUID()
				+ ", getDocumentsIDs()=" + getDocumentsIDs() + ", getCorpusSource()=" + getCorpusSource()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
