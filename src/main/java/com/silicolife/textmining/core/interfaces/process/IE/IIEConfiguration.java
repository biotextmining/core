package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.datastructures.process.ProcessRunStatusConfigurationEnum;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

public interface IIEConfiguration extends IConfiguration{
	
	public ICorpus getCorpus();
	public void setCorpus(ICorpus corpus);
	public ProcessRunStatusConfigurationEnum getProcessRunStatus();
	public IIEProcess getIEProcess();
	
}
