package com.silicolife.textmining.core.datastructures.report.processes;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERMergeProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;

public class NERMergeSchemasReportImpl extends ReportImpl implements INERMergeProcess{

	private List<IIEProcess> nertomerge;
	private INERSchema newProcess;
	
	
	public NERMergeSchemasReportImpl(String title, INERSchema newProcess2,IIEProcess primary,List<IIEProcess> nertomerge) {
		super(title);
		this.nertomerge = new ArrayList<>();
		this.nertomerge.add(primary);
		this.nertomerge.addAll(nertomerge);
		this.newProcess = newProcess2;
	}
	
	@Override
	public List<IIEProcess> nerProcessesMerged() {
		return nertomerge;
	}

	@Override
	public INERSchema getNERSchema() {
		return newProcess;
	}
	

}
