package com.silicolife.textmining.core.datastructures.process.ner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.process.IEConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;

public abstract class NERConfigurationImpl extends IEConfigurationImpl implements INERConfiguration{

	private String processUID;
	
	public NERConfigurationImpl() {
		super();
	}
	
	public NERConfigurationImpl(ICorpus corpus,String processUID,String processName) {
		super(corpus,processName,null);
		this.processUID = processUID;
	}

	@Override
	@JsonIgnore
	public String getUniqueProcessID() {
		return processUID;
	}
	
	
}
