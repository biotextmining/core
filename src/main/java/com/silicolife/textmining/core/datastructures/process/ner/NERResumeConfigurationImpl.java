package com.silicolife.textmining.core.datastructures.process.ner;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.process.IEConfigurationImpl;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERResumeConfiguration;

public class NERResumeConfigurationImpl extends IEConfigurationImpl implements INERResumeConfiguration{
	
	private IIEProcess iEProcess;

	public  NERResumeConfigurationImpl() {
		super();
	}
	
	public NERResumeConfigurationImpl(IIEProcess iePorcess)
	{
		super(iePorcess.getCorpus(),iePorcess.getName(),iePorcess.getNotes());
		this.iEProcess=iePorcess;
	}
	
	@JsonDeserialize(as=IEProcessImpl.class)
	public IIEProcess getIEProcess() {
		return iEProcess;
	}

	public void setIEProcess(IIEProcess iEProcess) {
		this.iEProcess = iEProcess;
	}
	
}
