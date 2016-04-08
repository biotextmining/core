
package com.silicolife.textmining.core.interfaces.process.IE.ner.eval;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.process.utils.IEvaluation;


public interface INERSchemaEvaluationResult extends IEvaluation{
	
	/**
	 * Return The entities matching in both NERschemas 
	 * 
	 * @return
	 */
	List<IEntityAnnotation> getHitEntities();
	
	/**
	 * Return entities missing match in Gold
	 * 
	 * @return
	 */
	List<IEntityAnnotation> getMissingEntitiesInGoldStandard();
	
	/**
	 * Return entities in Gold Standard that will not match
	 * 
	 * @return
	 */
	List<IEntityAnnotation> getAditionalgEntitiesInGoldStandard();
	
	/**
	 * Return result by class
	 * 
	 * @return
	 */
	INERSchemaClassEvaluationResults getClassTypeScores();
}
