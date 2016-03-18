package com.silicolife.textmining.core.interfaces.core.report.processes.ir;

import com.silicolife.textmining.core.interfaces.core.report.processes.IProcessReport;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface IIRSearchProcessReport extends IProcessReport{
	public IQuery getQuery();
	public void incrementDocumentRetrieval(int size);
}
