package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;

public interface IREClassTypesEvaluation {
	
	public List<IRelationMultiType> getAllRelationTypes();

	public Map<IRelationMultiType, Float> getRecallOfEachRelationType();
	public Map<IRelationMultiType, Float> getPrecisionOfEachRelationType();
	public Map<IRelationMultiType, Float> getFscoreOfEachRelationType();
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeTruePositives();
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalsePositives();
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalseNegatives();

}
