package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.INERSchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.IRESchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IREProcess;

public interface IREProcessReport extends INERProcessReport{
	public int getNumberOfRelations();
	public IIEProcess nerBasedProcesss();
	public void increaseRelations(int newRelations);
	public IREProcess getREProcess();
	public IRESchemaWithManualCurationReport getRESchemachemaWithManualCurationReport();
	public INERSchemaWithManualCurationReport getNERSchemachemaWithManualCurationReport();
	public boolean useMCAnnoations();
}
