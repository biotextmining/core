package com.silicolife.textmining.core.datastructures.process.evaluation.ner;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.evaluation.INESchemasEvaluationReport;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;
import com.silicolife.textmining.core.interfaces.process.IE.ner.eval.INERSchemaEvaluationResult;

public class NERSchemasEvaluationReportImpl extends ReportImpl implements INESchemasEvaluationReport{

	private INERSchemaEvaluationResult nerSchemaEvaluation;
	private INERSchema goldenStandard;
	private INERSchema toCompare;
	
	
	public NERSchemasEvaluationReportImpl(String title,INERSchemaEvaluationResult nerSchemaEvaluation,INERSchema goldenStandard,INERSchema toCompare) {
		super(title);
		this.nerSchemaEvaluation = nerSchemaEvaluation;
		this.goldenStandard = goldenStandard;
		this.toCompare = toCompare;
	}

	public INERSchemaEvaluationResult getEvaluation() {
		return nerSchemaEvaluation;
	}

	public INERSchema getGoldenStandardNERSchema() {
		return goldenStandard;
	}

	public INERSchema getNERSchemaCompared() {
		return toCompare;
	}
	
	

}
