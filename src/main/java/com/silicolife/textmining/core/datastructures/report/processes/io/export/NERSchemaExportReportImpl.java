package com.silicolife.textmining.core.datastructures.report.processes.io.export;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERSchemaExportReport;

public class NERSchemaExportReportImpl extends ReportImpl implements INERSchemaExportReport{

	public int entitiesExported;
	
	public NERSchemaExportReportImpl() {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.ner.exportschema"));
		entitiesExported = 0;
	}

	
	@Override
	public int entitiesExported() {
		return entitiesExported;
	}


	@Override
	public void incremetExportedEntity(int incrementNumber) {
		this.entitiesExported += incrementNumber;
	}


}
