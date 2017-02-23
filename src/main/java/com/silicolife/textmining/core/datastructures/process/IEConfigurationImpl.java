package com.silicolife.textmining.core.datastructures.process;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class IEConfigurationImpl extends ConfigurationImpl implements IIEConfiguration{
	
	private ICorpus corpus;
	private IIEProcess ieprocess;
	private ProcessRunStatusConfigurationEnum processRunStatus;
	
	
	public IEConfigurationImpl() {
		super();
		this.processRunStatus = ProcessRunStatusConfigurationEnum.createnew;
	}
	
	public IEConfigurationImpl(ICorpus corpus, IIEProcess ieprocess,ProcessRunStatusConfigurationEnum processRunStatus) {
		this.corpus = corpus;
		this.ieprocess=ieprocess;
		this.processRunStatus =processRunStatus;
	}

	@JsonDeserialize(as=CorpusImpl.class)
	public ICorpus getCorpus() {
		return corpus;
	}

	public ProcessRunStatusConfigurationEnum getProcessRunStatus() {
		return processRunStatus;
	}

	public void setProcessRunStatus(ProcessRunStatusConfigurationEnum processRunStatus) {
		this.processRunStatus = processRunStatus;
	}

	@JsonDeserialize(as=IEProcessImpl.class)
	public IIEProcess getIEProcess() {
		return ieprocess;
	}

	@Override
	public void setCorpus(ICorpus corpus) {
		this.corpus=corpus;
	}

}
