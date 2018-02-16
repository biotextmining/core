package com.silicolife.textmining.core.datastructures.corpora;

import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfigurationLuceneSearch;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationLuceneSearchImpl extends CorpusCreateConfigurationImpl implements ICorpusCreateConfigurationLuceneSearch{

	
public  static final String  configurationUID = "corpus.luceneSearchCreation";
	
	private ISearchProperties searchProperties;

	public CorpusCreateConfigurationLuceneSearchImpl() {
		super();
	}
	
	public CorpusCreateConfigurationLuceneSearchImpl( String corpusName,String notes,
			CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,Properties properties, CorpusCreateSourceEnum corpusSource, ISearchProperties searchProperties) {
		super(corpusName, notes, corpusTextType, processJournalRetrievalBeforeNeeded,properties,corpusSource );
		this.searchProperties = searchProperties;
	}
	
	
	@JsonIgnore
	public void setDocumentsIDs(Set<Long> documentsIDs) {
		super.setDocumentsIDs(documentsIDs);
	}
	
	@Override
	public ISearchProperties getSearchProperties() {
		return searchProperties;
	}

	public void setSearchProperties(ISearchProperties searchProperties) {
		this.searchProperties = searchProperties;
	}
	
	
}
