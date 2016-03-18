package com.silicolife.textmining.core.interfaces.core.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.IReport;

public interface IRESchemaExportReport extends IReport{
	public int relationsExported();
	public void incrementeRelationsExported(int increment);
}
