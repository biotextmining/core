package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface INERProcessReport extends IProcessReport{
	public int getNumberOFEntities();
	public void incrementDocument();
	public void incrementEntitiesAnnotated(int nesAnnotations);
	public IIEProcess getNERProcess();

}
