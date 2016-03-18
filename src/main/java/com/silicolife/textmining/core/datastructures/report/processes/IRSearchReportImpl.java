package com.silicolife.textmining.core.datastructures.report.processes;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchProcessReport;
import com.silicolife.textmining.core.interfaces.process.ProcessTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public class IRSearchReportImpl extends ReportImpl implements IIRSearchProcessReport{

	private int numberofdocuments;
	private IQuery query;
	
	public IRSearchReportImpl(IQuery query) {
		super("IR Report");
		this.numberofdocuments = 0;
		this.query = query;
	}

	@Override
	public int getNumberOfDocuments() {
		return numberofdocuments;
	}

	@Override
	public ProcessTypeEnum getProcessType() {
		return ProcessTypeEnum.IR;
	}

	@Override
	public IQuery getQuery() {
		return query;
	}

	@Override
	public void incrementDocumentRetrieval(int size) {
		numberofdocuments=numberofdocuments+size;		
	}

}
