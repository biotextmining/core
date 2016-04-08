package com.silicolife.textmining.core.datastructures.process.evaluation.ner;

import java.util.SortedMap;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.process.IE.ner.eval.INERSchemaClassEvaluationResults;

public class NERClassEvaluationImpl implements INERSchemaClassEvaluationResults{

	private SortedSet<String> allClassTypes;
	private SortedMap<String, Float> recallOfEachClassType;
	private SortedMap<String, Float> precisionOfEachClassType;
	private SortedMap<String, Float> fscoreOfEachClassType;

	public NERClassEvaluationImpl(SortedSet<String> allRelationTypes, SortedMap<String, Float> recallOfEachRelationType,
			SortedMap<String, Float> precisionOfEachRelationType, SortedMap<String, Float> fscoreOfEachRelationType){
		this.allClassTypes = allRelationTypes;
		this.recallOfEachClassType = recallOfEachRelationType;
		this.precisionOfEachClassType = precisionOfEachRelationType;
		this.fscoreOfEachClassType = fscoreOfEachRelationType;
	}

	public SortedSet<String> getAllClassTypes() {
		return allClassTypes;
	}

	public SortedMap<String, Float> getRecallOfEachClassType() {
		return recallOfEachClassType;
	}

	public SortedMap<String, Float> getPrecisionOfEachClassType() {
		return precisionOfEachClassType;
	}

	public SortedMap<String, Float> getFscoreOfEachClassnType() {
		return fscoreOfEachClassType;
	}
	
}