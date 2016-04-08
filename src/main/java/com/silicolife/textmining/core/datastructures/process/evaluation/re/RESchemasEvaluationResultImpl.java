package com.silicolife.textmining.core.datastructures.process.evaluation.re;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.datastructures.process.evaluation.EvaluationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;
import com.silicolife.textmining.core.interfaces.process.IE.re.eval.IRESchemasClassTypesEvaluationResult;
import com.silicolife.textmining.core.interfaces.process.IE.re.eval.IRESchemasEvaluationResults;

public class RESchemasEvaluationResultImpl extends EvaluationImpl implements IRESchemasEvaluationResults{
	
	private RESchemasEvaluationResultsImpl results;


	public RESchemasEvaluationResultImpl(RESchemasEvaluationResultsImpl results) {
		super(calculatePrecision(results),calculateRecall(results),calculateFscore(results));
		this.results = results;
	}

	private static float calculateFscore(RESchemasEvaluationResultsImpl results) {
		if(calculateRecall(results) + calculatePrecision(results) == 0){
			return 0;
		}
		return 2*(calculateRecall(results)*calculatePrecision(results))/(calculateRecall(results) + calculatePrecision(results));
	}

	// Precisao
	private static float calculatePrecision(RESchemasEvaluationResultsImpl results) {
		if(results.getSameInBothSchemas().size() + results.getJustInToCompare().size() == 0)
			return 0;
		return (float) results.getSameInBothSchemas().size() / (float) (results.getSameInBothSchemas().size() + results.getJustInToCompare().size());
	}

	// Acerto
	private static float calculateRecall(RESchemasEvaluationResultsImpl results) {
		if(results.getSameInBothSchemas().size() + results.getJustInGoldenStandard().size() + results.getFailByEntitiesMissing().size()== 0)
			return 0;
		return (float) results.getSameInBothSchemas().size() / (float) (results.getSameInBothSchemas().size() + results.getJustInGoldenStandard().size() + results.getFailByEntitiesMissing().size());
	}

	public List<IEventAnnotation> getSameInBothSchemas() {
		return results.getSameInBothSchemas();
	}

	public List<IEventAnnotation> getJustInGoldStandard() {
		return results.getJustInGoldenStandard();
	}

	public List<IEventAnnotation> getJustInToCompare() {
		return results.getJustInToCompare();
	}

	public List<IEventAnnotation> getFailByEntitiesMissing() {
		return results.getFailByEntitiesMissing();
	}

	public Map<Long, IEventAnnotation> getDocumentSameInBothSchemas() {
		return results.getDocumentSameInBothSchemas();
	}

	public Map<Long, IEventAnnotation> getDocumentjustInGoldenStandard() {
		return results.getDocumentjustInGoldenStandard();
	}

	public Map<Long, IEventAnnotation> getDocumentjustInToCompare() {
		return results.getDocumentjustInToCompare();
	}

	public Map<Long, IEventAnnotation> getDocumentfailByEntitiesMissing() {
		return results.getDocumentfailByEntitiesMissing();
	}
	
	
	public IRESchemasClassTypesEvaluationResult getScoresofEachRelationType(){
		List<IRelationMultiType> allRelationTypes = results.getAllRelationTypes();
		Map<IRelationMultiType, Float> recallofEachRelationType = new HashMap<IRelationMultiType, Float>();
		Map<IRelationMultiType, Float> precisionofEachRelationType = new HashMap<IRelationMultiType, Float>();
		Map<IRelationMultiType, Float> fscoreofEachRelationType = new HashMap<IRelationMultiType, Float>();
		Map<IRelationMultiType, List<IEventAnnotation>> mapRelationTruePositives = new HashMap<IRelationMultiType, List<IEventAnnotation>>();
		Map<IRelationMultiType, List<IEventAnnotation>> mapRelationFalseNegatives = new HashMap<IRelationMultiType, List<IEventAnnotation>>();
		Map<IRelationMultiType, List<IEventAnnotation>> mapRelationFalsePositives = new HashMap<IRelationMultiType, List<IEventAnnotation>>();

		for( IRelationMultiType relationType : allRelationTypes){
			mapRelationTruePositives.put(relationType, new ArrayList<IEventAnnotation>());
			mapRelationFalsePositives.put(relationType, new ArrayList<IEventAnnotation>());
			mapRelationFalseNegatives.put(relationType, new ArrayList<IEventAnnotation>());
			int numBothSchemas = 0;
			if(results.getMapRelationTypeInBothSchemas().containsKey(relationType))
			{
				 numBothSchemas = results.getMapRelationTypeInBothSchemas().get(relationType).size();
				 mapRelationTruePositives.get(relationType).addAll( results.getMapRelationTypeInBothSchemas().get(relationType));
			}
			int numToCompare = 0;
			if(results.getMapRelationTypeInjustInToCompare().containsKey(relationType)){
				numToCompare = results.getMapRelationTypeInjustInToCompare().get(relationType).size();
				mapRelationFalsePositives.get(relationType).addAll( results.getMapRelationTypeInjustInToCompare().get(relationType));
			}
			int numGoldStandard = 0;
			if(results.getMapRelationTypeInGoldenStandard().containsKey(relationType)){
				numGoldStandard = results.getMapRelationTypeInGoldenStandard().get(relationType).size();
				mapRelationFalseNegatives.put(relationType, results.getMapRelationTypeInGoldenStandard().get(relationType));
			}

			float precision = 0;
			if( (numBothSchemas+numToCompare)==  0){
				precisionofEachRelationType.put(relationType,(float)0);
			} else { 
				precision = (float)numBothSchemas/((float)numBothSchemas+(float)numToCompare);
				precisionofEachRelationType.put(relationType, precision);
			}
			
			float recall = 0;
			if((numBothSchemas+numGoldStandard)== (float)0){
				recallofEachRelationType.put(relationType, (float) 0);
			} else {
				recall =  (float)numBothSchemas/((float)numBothSchemas+(float)numGoldStandard);
				recallofEachRelationType.put(relationType,recall);
			}


			
			if( (precision+recall) == 0){
				fscoreofEachRelationType.put(relationType, (float)0 );
			}
			fscoreofEachRelationType.put(relationType, 2*(precision*recall)/(precision+recall));
		}
		return new REClassTypesEvaluationImpl(allRelationTypes,recallofEachRelationType,precisionofEachRelationType,fscoreofEachRelationType,mapRelationTruePositives,mapRelationFalseNegatives,mapRelationFalsePositives);
	}

}
