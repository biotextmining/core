package com.silicolife.textmining.core.interfaces.process.IE.re.eval;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;

/**
 * Interface that saves the RESchema Evaluation result per Relation type
 * 
 * @author Hugo Costa
 *
 */
public interface IRESchemasClassTypesEvaluationResult {
	
	/**
	 * Return all Relation types present in either RESchema
	 * 
	 * @return
	 */
	public List<IRelationMultiType> getAllRelationTypes();

	/**
	 * return map with recall per each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, Float> getRecallOfEachRelationType();
	
	/**
	 * return map with precision per each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, Float> getPrecisionOfEachRelationType();
	
	/**
	 * return map with FScore per each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, Float> getFscoreOfEachRelationType();
	
	/**
	 * return True Positives for each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeTruePositives();
	
	/**
	 * return False Positives for each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalsePositives();
	
	/**
	 * return False Negatives for each Relation Type
	 * 
	 * @return
	 */
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalseNegatives();

}
