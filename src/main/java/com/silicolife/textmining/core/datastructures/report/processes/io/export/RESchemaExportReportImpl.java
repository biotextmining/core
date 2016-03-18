package com.silicolife.textmining.core.datastructures.report.processes.io.export;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.IRESchemaExportReport;

public class RESchemaExportReportImpl extends ReportImpl implements IRESchemaExportReport{

	private int relationsExported;
	
	
	public RESchemaExportReportImpl() {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.re.exportschema"));
		this.relationsExported = 0;
	}

	@Override
	public int relationsExported() {
		return relationsExported;
	}

	@Override
	public void incrementeRelationsExported(int increment) {
		relationsExported += increment;	
	}

}
