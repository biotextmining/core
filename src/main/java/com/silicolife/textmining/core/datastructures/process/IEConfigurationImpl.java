package com.silicolife.textmining.core.datastructures.process;

import java.util.Properties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;

public class IEConfigurationImpl extends ConfigurationImpl implements IIEConfiguration{
	
	private ICorpus corpus;
	private String processNotes;
	private String processName;
	private Properties properties;
	
	
	public IEConfigurationImpl() {
		super();
	}
	
	public IEConfigurationImpl(ICorpus corpus)
	{
		this.corpus = corpus;
	}
	
	public IEConfigurationImpl(ICorpus corpus, String processName, String processNotes) {
		this(corpus);
		this.processName=processName;
		this.processNotes=processNotes;
	}

	@JsonDeserialize(as=CorpusImpl.class)
	public ICorpus getCorpus() {
		return corpus;
	}

	@Override
	public void setCorpus(ICorpus corpus) {
		this.corpus = corpus;
	}

	@Override
	public String getProcessNotes() {
		return processNotes;
	}

	@Override
	public void setProcessNotes(String processNotes) {
		this.processNotes=processNotes;
	}

	public String getProcessName() {
		return processName;
	}
	
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
