package com.silicolife.textmining.core.interfaces.process.IE.ner.eval;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.evaluation.INESchemasEvaluationReport;

/**
 * Interface that contains methods to compare two NERSchemas
 * 
 * @author Hugo Costa
 *
 */
public interface INERSchemasEvaluation {
	
	/**
	 * Method that compare two NERSchemas according to the configuration and return a NESchemasEvaluationReport
	 * 
	 * @param configuration
	 * @return
	 */
	public INESchemasEvaluationReport evaluateNERSchemas(INERSchemasEvaluationConfiguration configuration) throws ANoteException;

}
