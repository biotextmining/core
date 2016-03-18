package com.silicolife.textmining.core.datastructures.process.ner;

import com.silicolife.textmining.core.datastructures.process.IEConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;

public abstract class NERConfigurationImpl extends IEConfigurationImpl implements INERConfiguration{

	private String processName;
	private String processUID;
	
	public NERConfigurationImpl(ICorpus corpus,String processUID,String processName) {
		super(corpus);
		this.processName = processName;
		this.processUID = processUID;
	}

	@Override
	public String getName() {
		return processName;
	}

	@Override
	public String getUniqueProcessID() {
		return processUID;
	}
	
	
}
