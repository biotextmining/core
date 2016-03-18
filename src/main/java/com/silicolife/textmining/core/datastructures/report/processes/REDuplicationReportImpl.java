
package com.silicolife.textmining.core.datastructures.report.processes;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;

public class REDuplicationReportImpl extends ReportImpl implements IREDuplicationReport{

	private IRESchema reSchema;
	
	public REDuplicationReportImpl(String title,IRESchema reSchema) {
		super(title);
		this.reSchema = reSchema;
	}

	@Override
	public IRESchema getNewDuplicatedRESchema() {
		return reSchema;
	}
}
