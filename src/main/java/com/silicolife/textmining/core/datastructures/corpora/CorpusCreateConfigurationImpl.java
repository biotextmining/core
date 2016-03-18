package com.silicolife.textmining.core.datastructures.corpora;

import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationImpl implements ICorpusCreateConfiguration{

	private String name;
	private String notes;
	private Set<IPublication> docs;
	private CorpusTextType textType;
	private boolean journalRetrievalBefore;
	private Properties properties;
	
	
	private CorpusCreateConfigurationImpl(String name,String notes,Properties properties) {
		super();
		this.name = name;
		this.properties = properties;
		
	}
	
	public CorpusCreateConfigurationImpl(String name,String notes, Set<IPublication> docIds,CorpusTextType textType, boolean journalRetrievalBefore) {
		this(name,notes,new Properties());
		this.name = name;
		this.docs = docIds;
		this.textType = textType;
		this.journalRetrievalBefore = journalRetrievalBefore;
	}
	
	public CorpusCreateConfigurationImpl(String name,String notes, Set<IPublication> docIds,CorpusTextType textType, boolean journalRetrievalBefore,Properties properties) {
		this(name,notes,properties);
		this.name = name;
		this.docs = docIds;
		this.textType = textType;
		this.journalRetrievalBefore = journalRetrievalBefore;
	}

	@Override
	public String getCorpusName() {
		return name;
	}

	@Override
	public Set<IPublication> getDocuments() {
		return docs;
	}

	@Override
	public CorpusTextType getCorpusTextType() {
		return textType;
	}

	@Override
	public boolean processJournalRetrievalBefore() {
		return journalRetrievalBefore;
	}


	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getCorpusNotes() {
		return notes;
	}

	@Override
	public void setDocuments(Set<IPublication> publictions) {
		this.docs = publictions;
	}

}
