package com.silicolife.textmining.core.interfaces.process.cluestering;

import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface ICLusteringReport extends IReport{
	public IClusterProcess getClustering();
	public String getAlgorithm();
	public IQuery getQuery();

}
