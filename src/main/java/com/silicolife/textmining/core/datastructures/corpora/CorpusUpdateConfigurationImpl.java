package com.silicolife.textmining.core.datastructures.corpora;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusUpdateConfiguration;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public class CorpusUpdateConfigurationImpl extends ConfigurationImpl implements ICorpusUpdateConfiguration{

	public  static final String configurationUID = "corpus.update";
	private ICorpus corpusToUpdate;
	private String publicationsDirectory;
	
	public CorpusUpdateConfigurationImpl(){
		super();
	}
	
	public CorpusUpdateConfigurationImpl(ICorpus corpusToUpdate, String publicationsDirectory){
		super();
		this.corpusToUpdate = corpusToUpdate;
		this.publicationsDirectory = publicationsDirectory;
	}
	
	@Override
	@JsonDeserialize(as=CorpusImpl.class)
	public ICorpus getCorpusToUpdate() {
		return corpusToUpdate;
	}

	@Override
	public String getPublicationsDirectory() {
		return publicationsDirectory;
	}

	public void setCorpusToUpdate(ICorpus corpusToUpdate) {
		this.corpusToUpdate = corpusToUpdate;
	}

	public void setPublicationsDirectory(String publicationsDirectory) {
		this.publicationsDirectory = publicationsDirectory;
	}
	
	@Override
	public String getConfigurationUID() {
		return configurationUID;
	}

}
