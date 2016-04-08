package com.silicolife.textmining.core.interfaces.process.IE.re.eval;

import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;

/**
 * Class that represent a RESchemas evaluation usually a toCompare RESchema against Gold Standard 
 * 
 * 
 * @author Hugo Costa
 *
 */
public interface IRESchemasEvaluationConfiguration {
	
	/**
	 * Return the Gold Standard Schema
	 * 
	 * @return
	 */
	public IRESchema getGoldStandard();
	
	/**
	 * Return the to Compare RESchema ( against Gold Schema)
	 * 
	 * @return
	 */
	public IRESchema getToCompare();
	
	/**
	 * Allow partial matching in clues according to clue overlap ( one inside other ) 
	 * 
	 * @return
	 */
	public boolean allowClueOverlap();
	
	/**
	 * Allow matching according to any entity and entity synonyms
	 * 
	 * @return
	 */
	public boolean allowsynonymsRange();

}
