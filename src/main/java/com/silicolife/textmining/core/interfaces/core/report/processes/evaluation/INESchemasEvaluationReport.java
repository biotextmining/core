package com.silicolife.textmining.core.interfaces.core.report.processes.evaluation;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERSchemaEvaluation;

public interface INESchemasEvaluationReport extends IReport{
		
	public INERSchemaEvaluation getEvaluation();
	public INERSchema getGoldenStandardNERSchema();
	public INERSchema getNERSchemaCompared();

}
