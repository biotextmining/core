package com.silicolife.textmining.core.datastructures.process.evaluation.re;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.evaluation.IRESchemaEvaluationReport;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.re.eval.IRESchemasEvaluationResults;

public class RESchemasEvaluationReportImpl extends ReportImpl implements IRESchemaEvaluationReport{

	private IRESchemasEvaluationResults evaluation;
	private IRESchema goldenStandard;
	private IRESchema toCompare;

	
	
	public RESchemasEvaluationReportImpl(IRESchemasEvaluationResults evaluation,IRESchema goldenStandard,IRESchema toCompare) {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.re.evaluation.report"));
		this.evaluation = evaluation;
		this.goldenStandard = goldenStandard;
		this.toCompare = toCompare;
	}

	public IRESchemasEvaluationResults getEvaluation() {
		return evaluation;
	}

	public IRESchema getGoldenStandardRESchema() {
		return goldenStandard;
	}

	public IRESchema getRESchemaCompared() {
		return toCompare;
	}

}
