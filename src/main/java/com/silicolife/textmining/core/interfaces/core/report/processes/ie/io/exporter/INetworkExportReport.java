package com.silicolife.textmining.core.interfaces.core.report.processes.ie.io.exporter;

import com.silicolife.textmining.core.interfaces.core.report.IReport;

public interface INetworkExportReport extends IReport{
	public int getNumberOFNodes();
	public int getNumberofEdges();
}
