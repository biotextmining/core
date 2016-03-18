package com.silicolife.textmining.core.datastructures.clustering;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.process.cluestering.ICLusteringReport;

public class ClusteringReportImpl extends ReportImpl implements ICLusteringReport{

	
	private IClusterProcess clustering;
	private String algorithm;
	private IQuery query;
	
	public ClusteringReportImpl(IClusterProcess clustering, String algorithm, IQuery query) {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.carrot.clusteringreport"));
		this.clustering = clustering;
		this.algorithm = algorithm;
		this.query = query;
	}

	@Override
	public IClusterProcess getClustering() {
		return clustering;
	}

	@Override
	public String getAlgorithm() {
		return algorithm;
	}


	@Override
	public IQuery getQuery() {
		return query;
	}

}
