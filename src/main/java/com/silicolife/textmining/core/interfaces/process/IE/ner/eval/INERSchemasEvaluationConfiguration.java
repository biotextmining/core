package com.silicolife.textmining.core.interfaces.process.IE.ner.eval;

import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;

/**
 * Class that represent a NERSchemas evaluation usually a toCompare NERSchema against Gold Standard 
 * 
 * 
 * @author Hugo Costa
 *
 */
public interface INERSchemasEvaluationConfiguration {
	
	/**
	 * Return the Gold Standard Schema
	 * 
	 * @return
	 */
	public INERSchema getGoldStandard();
	
	/**
	 * Return the to Compare NERSchema ( against Gold Schema)
	 * 
	 * @return
	 */
	public INERSchema getToCompare();
	
	/**
	 * Configuration property that allow ignore class in evalutation
	 * 
	 * @return
	 */
	public boolean isIgnoreClasses();
	
}
