package com.silicolife.textmining.core.datastructures.report.processes;

import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchUpdateReport;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public class IRSearchUpdateReportImpl extends IRSearchReportImpl implements IIRSearchUpdateReport{

	public IRSearchUpdateReportImpl(IQuery query) {
		super(query);
	}

}
