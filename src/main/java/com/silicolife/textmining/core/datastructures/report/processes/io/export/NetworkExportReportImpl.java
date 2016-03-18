package com.silicolife.textmining.core.datastructures.report.processes.io.export;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.ie.io.exporter.INetworkExportReport;

public class NetworkExportReportImpl extends ReportImpl implements INetworkExportReport{

	private int numberOFNodes;
	private int numberofEdges;
	
	public NetworkExportReportImpl(String title,int numberOFNodes,int numberofEdges) {
		super(title);
		this.numberOFNodes = numberOFNodes;
		this.numberofEdges = numberofEdges;
	}
	
	public int getNumberOFNodes() {
		return numberOFNodes;
	}
	public int getNumberofEdges() {
		return numberofEdges;
	}
	
	
}
