package com.silicolife.textmining.core.datastructures.process;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;

public class IEConfigurationImpl extends ConfigurationImpl implements IIEConfiguration{
	
	private ICorpus corpus;
	private String notes;
	private String name;
	private Properties properties;
	
	public IEConfigurationImpl(ICorpus corpus)
	{
		this.corpus = corpus;
	}
	
	public ICorpus getCorpus() {
		return corpus;
	}

	@Override
	public void setCorpus(ICorpus corpus) {
		this.corpus = corpus;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void setNotes(String notes) {
		this.notes=notes;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
