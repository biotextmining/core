package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.ProcessTypeEnum;

public interface IProcessReport extends IReport{
	
	public int getNumberOfDocuments();
	public ProcessTypeEnum getProcessType();

}
