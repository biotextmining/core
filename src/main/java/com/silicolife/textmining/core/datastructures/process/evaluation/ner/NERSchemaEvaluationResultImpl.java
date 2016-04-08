package com.silicolife.textmining.core.datastructures.process.evaluation.ner;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.silicolife.textmining.core.datastructures.process.evaluation.EvaluationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.process.IE.ner.eval.INERSchemaClassEvaluationResults;
import com.silicolife.textmining.core.interfaces.process.IE.ner.eval.INERSchemaEvaluationResult;

public class NERSchemaEvaluationResultImpl extends EvaluationImpl implements INERSchemaEvaluationResult{

	private NERSchemasEvaluationResultsImpl results;
	
	public NERSchemaEvaluationResultImpl(NERSchemasEvaluationResultsImpl results) {
		super(calculatePrecision(results), calculateRecall(results), calculateFScore(results));
		this.results = results;
	}

	@Override
	public List<IEntityAnnotation> getHitEntities() {
		return results.getEntitiesInBothNER();
	}

	@Override
	public List<IEntityAnnotation> getMissingEntitiesInGoldStandard() {
		return results.getEntitiesOnlyNERGoldStandart();
	}

	@Override
	public List<IEntityAnnotation> getAditionalgEntitiesInGoldStandard() {
		return results.getEntitiesOnlyNERForComparing();
	}
	
	private static float calculatePrecision( NERSchemasEvaluationResultsImpl results ){
		if ( (results.getEntitiesInBothNER().size() + results.getEntitiesOnlyNERForComparing().size()) == 0) {
			return 0;
		}
		return (float)results.getEntitiesInBothNER().size()/(float)(results.getEntitiesInBothNER().size() + results.getEntitiesOnlyNERForComparing().size());
	}
	
	private static float calculateRecall( NERSchemasEvaluationResultsImpl results){
		if ((results.getEntitiesInBothNER().size() + results.getEntitiesOnlyNERGoldStandart().size()) == 0){
			return 0;
		}
		return (float)results.getEntitiesInBothNER().size()/(float)(results.getEntitiesInBothNER().size() + results.getEntitiesOnlyNERGoldStandart().size());
	}
	
	private static float calculateFScore(NERSchemasEvaluationResultsImpl results){
		if (calculatePrecision(results) + calculateRecall(results) == 0){
			return 0;
		}
		return 2 * (calculatePrecision(results) * calculateRecall(results) ) / (calculatePrecision(results) + calculateRecall(results) );
	}
	
	public INERSchemaClassEvaluationResults getClassTypeScores(){
		SortedMap<String, Float> recallOfEachClassType = new TreeMap<String, Float>();
		SortedMap<String, Float> precisionOfEachClassType = new TreeMap<String, Float>();
		SortedMap<String, Float> fscoreOfEachClassType = new TreeMap<String, Float>();
		for( String classType : results.getAllAnnotatedClasses()){
			int numBothSchemas = 0;
			if( results.getEntityClassesInBothNER().containsKey(classType)){
				numBothSchemas = results.getEntityClassesInBothNER().get(classType);
			}
			int	numToCompare = 0;
			if( results.getEntityClassesOnlyNERForComparing().containsKey(classType)){
				numToCompare = results.getEntityClassesOnlyNERForComparing().get(classType);
			}
			int numGoldStandard = 0;
			if( results.getEntityClassesOnlyNERGoldStandart().containsKey(classType)){
				numGoldStandard = results.getEntityClassesOnlyNERGoldStandart().get(classType);
			}

			float recall = 0;
			if( (numBothSchemas+numGoldStandard)==  0){
				recallOfEachClassType.put(classType,(float)0);
			} else { 
				recall = (float)numBothSchemas/((float)numBothSchemas+(float)numGoldStandard);
				recallOfEachClassType.put(classType, recall);
			}
			float precision = 0;
			if((numBothSchemas+numToCompare)== 0){
				precisionOfEachClassType.put(classType, (float) 0);
			} else {
				precision =  (float)numBothSchemas/((float)numBothSchemas+(float)numToCompare);
				precisionOfEachClassType.put(classType,precision);
			}
			
			if( (precision+recall) == (float) 0){
				fscoreOfEachClassType.put(classType, (float)0 );
			}
			fscoreOfEachClassType.put(classType, 2*(precision*recall)/(precision+recall));
				
		}
		
		return new NERClassEvaluationImpl(results.getAllAnnotatedClasses(), recallOfEachClassType, precisionOfEachClassType, fscoreOfEachClassType);
	}
	
}
