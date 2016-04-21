package com.silicolife.textmining.core.datastructures.corpora;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationImpl implements ICorpusCreateConfiguration{

	public String configurationUID = "corpus.cretation";
	
	private String corpusName;
	private String corpusNotes;
	private Set<IPublication> docs;
	private CorpusTextType corpusTextType;
	private boolean processJournalRetrievalBeforeNeeded;
	private Properties properties;
	
	
	private CorpusCreateConfigurationImpl(String corpusName,String notes,Properties properties) {
		super();
		this.corpusName = corpusName;
		this.properties = properties;
		
	}
	
	public CorpusCreateConfigurationImpl(String corpusName,String notes, Set<IPublication> docIds,CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded) {
		this(corpusName,notes,new Properties());
		this.docs = docIds;
		this.corpusTextType = corpusTextType;
		this.processJournalRetrievalBeforeNeeded = processJournalRetrievalBeforeNeeded;
	}
	
	public CorpusCreateConfigurationImpl(String corpusName,String notes, Set<IPublication> docIds,CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,Properties properties) {
		this(corpusName,notes,properties);
		this.docs = docIds;
		this.corpusTextType = corpusTextType;
		this.processJournalRetrievalBeforeNeeded = processJournalRetrievalBeforeNeeded;
	}

	@Override
	public String getCorpusName() {
		return corpusName;
	}

	@Override
	@JsonIgnore
	public Set<IPublication> getDocuments() {
		return docs;
	}

	@Override
	public CorpusTextType getCorpusTextType() {
		return corpusTextType;
	}

	@Override
	public boolean isProcessJournalRetrievalBeforeNeeded() {
		return processJournalRetrievalBeforeNeeded;
	}


	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getCorpusNotes() {
		return corpusNotes;
	}

	@JsonIgnore
	public void setDocuments(Set<IPublication> publictions) {
		this.docs = publictions;
	}

	public String getConfigurationUID() {
		return configurationUID;
	}

	@Override
	public void setConfigurationUID(String configurationUID) {
		this.configurationUID=configurationUID;
	}

	@Override
	public Set<Long> getDocumentsIDs() {
		Set<Long> documentIDs = new HashSet<>();
		for(IPublication pub:getDocuments())
		{
			documentIDs.add(pub.getId());
		}
		return documentIDs;
	}

}
