package com.silicolife.textmining.core.datastructures.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;

public interface IREDuplicationReport extends IReport{
	public IRESchema getNewDuplicatedRESchema();
}
