package com.silicolife.textmining.core.interfaces.core.report.processes;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;

public interface INERMergeProcess extends IReport{
	public List<IIEProcess> nerProcessesMerged();
	public INERSchema getNERSchema();
}
