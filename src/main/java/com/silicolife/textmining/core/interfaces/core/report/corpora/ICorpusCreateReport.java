package com.silicolife.textmining.core.interfaces.core.report.corpora;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface ICorpusCreateReport extends IReport{
	public ICorpus getCorpus();
	public int getDocumentNumber();
	public Set<IIEProcess> getProcesses(); 
	public CorpusTextType getCorpusTextType();
	public void addProcess(IIEProcess process);
//	public void setCorpus(ICorpus corpus);
}
