package com.silicolife.textmining.core.interfaces.process.IE.re.eval;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.evaluation.IRESchemaEvaluationReport;


/**
 * Interface that contains methods to compare two RESchemas
 * 
 * @author Hugo Costa
 *
 */
public interface IRESchemasEvaluation {
	
	/**
	 * Method that compare two RESchemas according to the configuration and return a RESchemaEvaluationReport
	 * 
	 * @param configuration
	 * @return
	 */
	public IRESchemaEvaluationReport evaluateRESchemas(IRESchemasEvaluationConfiguration configuration) throws ANoteException;

}
