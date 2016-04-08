package com.silicolife.textmining.core.datastructures.process.evaluation.re;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;
import com.silicolife.textmining.core.interfaces.process.IE.re.eval.IRESchemasClassTypesEvaluationResult;

public class REClassTypesEvaluationImpl implements IRESchemasClassTypesEvaluationResult{


	private List<IRelationMultiType> allRelationTypes;
	private Map<IRelationMultiType, Float> recallOfEachRelationType;
	private Map<IRelationMultiType, Float> precisionOfEachRelationType;
	private Map<IRelationMultiType, Float> fscoreOfEachRelationType;
	private Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationTruePositives;
	private Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationFalsePositives;
	private Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationFalseNegatives;


	public REClassTypesEvaluationImpl(List<IRelationMultiType> allRelationTypes, Map<IRelationMultiType, Float> recallOfEachRelationType,
			Map<IRelationMultiType, Float> precisionOfEachRelationType, Map<IRelationMultiType, Float> fscoreOfEachRelationType,
			Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationExpectedRelations,
			Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationFalseNegatives,
			Map<IRelationMultiType,  List<IEventAnnotation>> mapRelationFalsePositives){
		this.allRelationTypes = allRelationTypes;
		this.recallOfEachRelationType = recallOfEachRelationType;
		this.precisionOfEachRelationType = precisionOfEachRelationType;
		this.fscoreOfEachRelationType = fscoreOfEachRelationType;
		this.mapRelationTruePositives = mapRelationExpectedRelations;
		this.mapRelationFalseNegatives = mapRelationFalseNegatives;
		this.mapRelationFalsePositives = mapRelationFalsePositives;
	}

	public List<IRelationMultiType> getAllRelationTypes() {
		return allRelationTypes;
	}

	public Map<IRelationMultiType, Float> getRecallOfEachRelationType() {
		return recallOfEachRelationType;
	}

	public Map<IRelationMultiType, Float> getPrecisionOfEachRelationType() {
		return precisionOfEachRelationType;
	}

	public Map<IRelationMultiType, Float> getFscoreOfEachRelationType() {
		return fscoreOfEachRelationType;
	}

	@Override
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeTruePositives() {
		return mapRelationTruePositives;
	}

	@Override
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalsePositives() {
		// TODO Auto-generated method stub
		return mapRelationFalsePositives;
	}

	@Override
	public Map<IRelationMultiType, List<IEventAnnotation>> getMapRelationTypeFalseNegatives() {
		// TODO Auto-generated method stub
		return mapRelationFalseNegatives;
	}


	
}
