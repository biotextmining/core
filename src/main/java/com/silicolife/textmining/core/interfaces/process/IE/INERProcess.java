package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERProcessReport;

public interface INERProcess extends IIEProcess{
	public INERProcessReport executeCorpusNER(ICorpus corpus) throws ANoteException;
	public void stop();
	
}
