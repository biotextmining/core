package com.silicolife.textmining.core.interfaces.process.IE.ner.eval;

import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Interface that save the NER Schema evaluation results per class
 * 
 * @author Hugo Costa
 *
 */
public interface INERSchemaClassEvaluationResults {
	
	/**
	 * Return all classes present in either corpus
	 * 
	 * @return
	 */
	public SortedSet<String> getAllClassTypes();

	/**
	 * Return recall by class
	 * 
	 * @return
	 */
	public SortedMap<String, Float> getRecallOfEachClassType();

	/**
	 * Return precision by class
	 * 
	 * @return
	 */
	public SortedMap<String, Float> getPrecisionOfEachClassType();

	
	/**
	 * Return FScore by class
	 * 
	 * @return
	 */
	public SortedMap<String, Float> getFscoreOfEachClassnType();

}
