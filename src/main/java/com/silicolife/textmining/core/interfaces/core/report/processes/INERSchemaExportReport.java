package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.IReport;

public interface INERSchemaExportReport extends IReport{
	public int entitiesExported();
	public void incremetExportedEntity(int incrementNumber);
}
