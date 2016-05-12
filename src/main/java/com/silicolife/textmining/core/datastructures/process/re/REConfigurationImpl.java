package com.silicolife.textmining.core.datastructures.process.re;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.process.IEConfigurationImpl;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.re.IREConfiguration;

public abstract class REConfigurationImpl extends IEConfigurationImpl implements IREConfiguration{

	private IIEProcess entityBasedProcess;
	private String processUID;
	private boolean useManualCurationFromOtherProcess;
	private IIEProcess manualCurationFromOtherProcess;
	
	public REConfigurationImpl()
	{
		super();
	}
	
	public REConfigurationImpl(String processUID,ICorpus corpus,IIEProcess entityProcess,boolean useManualCurationFromOtherProcess,IIEProcess manualCurationProcess) {
		super(corpus);
		this.entityBasedProcess = entityProcess;
		this.processUID = processUID;
		this.useManualCurationFromOtherProcess = useManualCurationFromOtherProcess;
		this.manualCurationFromOtherProcess = manualCurationProcess;
	}

	@Override
	@JsonDeserialize(as=IEProcessImpl.class)
	public IIEProcess getEntityBasedProcess() {
		return entityBasedProcess;
	}

	public void setEntityBasedProcess(IIEProcess entityBasedProcess) {
		this.entityBasedProcess = entityBasedProcess;
	}

	@Override
	@JsonIgnore
	public String getUniqueProcessID() {
		return processUID;
	}
	
	public boolean isUseManualCurationFromOtherProcess()
	{
		return useManualCurationFromOtherProcess;
	}
	
	public void setUseManualCurationFromOtherProcess(
			boolean useManualCurationFromOtherProcess) {
		this.useManualCurationFromOtherProcess = useManualCurationFromOtherProcess;
	}

	@JsonDeserialize(as=IEProcessImpl.class)
	public IIEProcess getManualCurationFromOtherProcess()
	{
		return manualCurationFromOtherProcess;
	}

	public void setManualCurationFromOtherProcess(
			IIEProcess manualCurationFromOtherProcess) {
		this.manualCurationFromOtherProcess = manualCurationFromOtherProcess;
	}

	
}
