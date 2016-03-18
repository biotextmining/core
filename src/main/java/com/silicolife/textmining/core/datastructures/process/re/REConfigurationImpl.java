package com.silicolife.textmining.core.datastructures.process.re;

import com.silicolife.textmining.core.datastructures.process.IEConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.re.IREConfiguration;

public abstract class REConfigurationImpl extends IEConfigurationImpl implements IREConfiguration{

	private IIEProcess entityProcess;
	private String processUID;
	private boolean useManualCurationFromOtherProcess;
	private IIEProcess manualCurationProcess;
	
	public REConfigurationImpl(String processUID,ICorpus corpus,IIEProcess entityProcess,boolean useManualCurationFromOtherProcess,IIEProcess manualCurationProcess) {
		super(corpus);
		this.entityProcess = entityProcess;
		this.processUID = processUID;
		this.useManualCurationFromOtherProcess = useManualCurationFromOtherProcess;
		this.manualCurationProcess = manualCurationProcess;
	}

	@Override
	public IIEProcess getIEProcess() {
		return entityProcess;
	}

	@Override
	public void setIEProcess(IIEProcess entityProcess) {
		this.entityProcess = entityProcess;
	}

	@Override
	public String getUniqueProcessID() {
		return processUID;
	}
	
	public boolean useManualCurationFromOtherProcess()
	{
		return useManualCurationFromOtherProcess;
	}
	
	
	public IIEProcess getManualCurationFromOtherProcess()
	{
		return manualCurationProcess;
	}

}
