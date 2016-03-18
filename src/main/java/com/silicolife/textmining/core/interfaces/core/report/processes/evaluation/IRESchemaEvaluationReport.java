package com.silicolife.textmining.core.interfaces.core.report.processes.evaluation;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRESchemaEvaluation;

public interface IRESchemaEvaluationReport extends IReport{
	public IRESchemaEvaluation getEvaluation();
	public IRESchema getGoldenStandardRESchema();
	public IRESchema getRESchemaCompared();

}
