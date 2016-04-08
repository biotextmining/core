package com.silicolife.textmining.core.interfaces.process.IE.re.eval;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.utils.IEvaluation;

public interface IRESchemasEvaluationResults extends IEvaluation{
	
	/**
	 * Return Relations in both RESchemas
	 * 
	 * @return
	 */
	public List<IEventAnnotation> getSameInBothSchemas();
	
	/**
	 * Return Relations only in Gold Standard RE Schema
	 * 
	 * @return
	 */
	public List<IEventAnnotation> getJustInGoldStandard();
	
	/**
	 * Return Relations only in To Compare RESchema
	 * 
	 * @return
	 */
	public List<IEventAnnotation> getJustInToCompare();
	
	/**
	 * Return Relations missing by entities
	 * 
	 * @return
	 */
	public List<IEventAnnotation> getFailByEntitiesMissing();
	
	public Map<Long, IEventAnnotation> getDocumentSameInBothSchemas();
	public Map<Long, IEventAnnotation> getDocumentjustInGoldenStandard();
	public Map<Long, IEventAnnotation> getDocumentjustInToCompare();
	public Map<Long, IEventAnnotation> getDocumentfailByEntitiesMissing();
	
	/**
	 * Return RESchema evaluation by Relation type
	 * 
	 * @return
	 */
	public IRESchemasClassTypesEvaluationResult getScoresofEachRelationType();

}
