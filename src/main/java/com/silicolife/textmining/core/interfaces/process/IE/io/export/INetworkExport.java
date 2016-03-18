package com.silicolife.textmining.core.interfaces.process.IE.io.export;

import java.io.File;

import com.silicolife.textmining.core.interfaces.core.report.processes.ie.io.exporter.INetworkExportReport;
import com.silicolife.textmining.core.interfaces.process.IE.network.IEdge;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetwork;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;

public interface INetworkExport {
	public File getFile();
	public INetwork<? extends INode, ? extends IEdge> getNetwork();
	public String getFileExtention();
	public INetworkExportReport export();
}
