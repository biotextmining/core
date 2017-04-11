package com.silicolife.textmining.core.datastructures.corpora;

import java.util.Properties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusUpdateConfiguration;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public class CorpusUpdateConfigurationImpl extends ConfigurationImpl implements ICorpusUpdateConfiguration{

	public  static final String configurationUID = "corpus.update";
	private ICorpus corpusToUpdate;
	private String publicationsDirectory;
	private Properties properties;
	private CorpusCreateSourceEnum corpusSource;
	
	public CorpusUpdateConfigurationImpl(){
		super();
	}
	
	public CorpusUpdateConfigurationImpl(ICorpus corpusToUpdate, String publicationsDirectory, Properties properties,CorpusCreateSourceEnum corpusSource){
		super();
		this.corpusToUpdate = corpusToUpdate;
		this.publicationsDirectory = publicationsDirectory;
		this.properties = properties;
		this.corpusSource=corpusSource;
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

	@Override
	public Properties getProperties() {
		return properties;
	}
	
	public void setCorpusToUpdate(ICorpus corpusToUpdate) {
		this.corpusToUpdate = corpusToUpdate;
	}

	public void setPublicationsDirectory(String publicationsDirectory) {
		this.publicationsDirectory = publicationsDirectory;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String getConfigurationUID() {
		return configurationUID;
	}

	@Override
	public CorpusCreateSourceEnum getCorpusSource() {
		return corpusSource;
	}

	public void setCorpusSource(CorpusCreateSourceEnum corpusSource) {
		this.corpusSource = corpusSource;
	}

}
