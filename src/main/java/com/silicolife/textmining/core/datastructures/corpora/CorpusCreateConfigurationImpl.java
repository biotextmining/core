package com.silicolife.textmining.core.datastructures.corpora;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationImpl extends ConfigurationImpl implements ICorpusCreateConfiguration{

	public  static final String  configurationUID = "corpus.cretation";
	
	private String corpusName;
	private String corpusNotes;
	private Set<IPublication> docs;
	private CorpusTextType corpusTextType;
	private boolean processJournalRetrievalBeforeNeeded;
	private Properties properties;
	private Set<Long> documentsIDs;
	private CorpusCreateSourceEnum corpusCreateSourceEnum;

	public CorpusCreateConfigurationImpl()
	{
		super();
	}

	public CorpusCreateConfigurationImpl(String corpusName,String notes,Properties properties,CorpusCreateSourceEnum corpusCreateSourceEnum) {
		super();
		this.corpusName = corpusName;
		this.corpusNotes = notes;
		this.properties = properties;
		if(properties.containsKey(GlobalNames.textType)){
			this.corpusTextType = CorpusTextType.convertStringToCorpusType(properties.getProperty(GlobalNames.textType));
		}
		
	}
	
	public CorpusCreateConfigurationImpl(String corpusName,String notes, Set<IPublication> docIds,CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,CorpusCreateSourceEnum corpusCreateSourceEnum) {
		this(corpusName,notes,new Properties(),corpusCreateSourceEnum);
		setDocuments(docIds);
		this.corpusTextType = corpusTextType;
		this.processJournalRetrievalBeforeNeeded = processJournalRetrievalBeforeNeeded;
	}
	
	public CorpusCreateConfigurationImpl(String corpusName,String notes, Set<IPublication> docIds,CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,Properties properties,CorpusCreateSourceEnum corpusCreateSourceEnum) {
		this(corpusName,notes,properties,corpusCreateSourceEnum);
		setDocuments(docIds);
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
		this.documentsIDs = new HashSet<>();
		for(IPublication pub:publictions)
			documentsIDs.add(pub.getId());
	}

	public String getConfigurationUID() {
		return configurationUID;
	}

	@Override
	public Set<Long> getDocumentsIDs() {
		return documentsIDs;
	}
	
	public void setCorpusName(String corpusName) {
		this.corpusName = corpusName;
	}

	public void setCorpusNotes(String corpusNotes) {
		this.corpusNotes = corpusNotes;
	}

	public void setCorpusTextType(CorpusTextType corpusTextType) {
		this.corpusTextType = corpusTextType;
	}

	public void setProcessJournalRetrievalBeforeNeeded(
			boolean processJournalRetrievalBeforeNeeded) {
		this.processJournalRetrievalBeforeNeeded = processJournalRetrievalBeforeNeeded;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public void setDocumentsIDs(Set<Long> documentsIDs) {
		this.documentsIDs = documentsIDs;
	}

	@Override
	public CorpusCreateSourceEnum getCorpusSource() {
		return corpusCreateSourceEnum;
	}

}
