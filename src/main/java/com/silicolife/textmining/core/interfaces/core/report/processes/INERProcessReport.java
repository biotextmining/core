package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.process.IE.INERProcess;

public interface INERProcessReport extends IProcessReport{
	public int getNumberOFEntities();
	public void incrementDocument();
	public void incrementEntitiesAnnotated(int nesAnnotations);
	public INERProcess getNERProcess();

}
