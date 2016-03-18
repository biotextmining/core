
package com.silicolife.textmining.core.datastructures.report.processes;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;

public class NERDuplicationReportImpl extends ReportImpl implements INERDuplicationReport{

	private INERSchema nerSchema;
	
	public NERDuplicationReportImpl(String title,INERSchema neeSchema) {
		super(title);
		this.nerSchema = neeSchema;
	}

	@Override
	public INERSchema getNewDuplicatedNERSchema() {
		return nerSchema;
	}

}
