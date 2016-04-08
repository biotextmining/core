package com.silicolife.textmining.core.datastructures.process.evaluation.re;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;

public class RESchemasEvaluationResultsImpl {
	
	
	private List<IEventAnnotation> sameInBothSchemas;
	private List<IEventAnnotation> justInGoldenStandard;
	private List<IEventAnnotation> justInToCompare;
	private List<IEventAnnotation> failByEntitiesMissing;
	
	private Map<Long, IEventAnnotation> documentSameInBothSchemas;
	private Map<Long, IEventAnnotation> documentjustInGoldenStandard;
	private Map<Long, IEventAnnotation> documentjustInToCompare;
	private Map<Long, IEventAnnotation> documentfailByEntitiesMissing;
	
	private REEvaluationMap mapRelationTypeInBothSchemas;
	private REEvaluationMap mapRelationTypeInGoldenStandard;
	private REEvaluationMap mapRelationTypeInjustInToCompare;
	
	private List<IRelationMultiType> relationTypes;

	
	public RESchemasEvaluationResultsImpl()
	{
		this.sameInBothSchemas = new ArrayList<IEventAnnotation>();
		this.justInGoldenStandard = new ArrayList<IEventAnnotation>();
		this.justInToCompare = new ArrayList<IEventAnnotation>();
		this.failByEntitiesMissing = new ArrayList<IEventAnnotation>();
		this.documentSameInBothSchemas = new HashMap<Long, IEventAnnotation>();
		this.documentjustInGoldenStandard = new HashMap<Long, IEventAnnotation>();
		this.documentjustInToCompare = new HashMap<Long, IEventAnnotation>();
		this.documentfailByEntitiesMissing = new HashMap<Long, IEventAnnotation>();
		this.mapRelationTypeInBothSchemas = new REEvaluationMap();
		this.mapRelationTypeInGoldenStandard = new REEvaluationMap();
		this.mapRelationTypeInjustInToCompare = new REEvaluationMap();
		this.relationTypes = new ArrayList<IRelationMultiType>();
	}
	
	public void addEventMissingForMissingEntities(IAnnotatedDocument goldenStandardDocument,IEventAnnotation eventGold) {
		this.failByEntitiesMissing.add(eventGold);
		this.documentfailByEntitiesMissing.put(goldenStandardDocument.getId(), eventGold);	
	}
	
	/**
	 * Stores events and relations type that are only present in gold standard schema.
	 * 
	 * @param goldenStandardDocument
	 * @param eventGold
	 */
	public void addEventJustInGoldenStandard(IAnnotatedDocument goldenStandardDocument,IEventAnnotation eventGold) {
		this.justInGoldenStandard.add(eventGold);
		this.documentjustInGoldenStandard.put(goldenStandardDocument.getId(), eventGold);
		IRelationMultiType rtype = eventGold.getRelationType();
		if(!this.mapRelationTypeInGoldenStandard.containsKey(rtype))
		{
			this.mapRelationTypeInGoldenStandard.put(rtype, new ArrayList<IEventAnnotation>());
		}
		if(!relationTypes.contains(rtype))
		{
			this.relationTypes.add(rtype);
		}	
		this.mapRelationTypeInGoldenStandard.get(rtype).add(eventGold);
	}
	
	/**
	 * Stores events and relations type that are present in both gold standard and to compare schemas
	 * 
	 * @param goldenStandardDocument
	 * @param eventGold
	 */
	public void addEventMatching(IAnnotatedDocument goldenStandardDocument,IEventAnnotation eventGold) {
		this.sameInBothSchemas.add(eventGold);
		this.documentSameInBothSchemas.put(goldenStandardDocument.getId(), eventGold);
		IRelationMultiType rtype = eventGold.getRelationType();
		if(!this.mapRelationTypeInBothSchemas.containsKey(rtype)){
			this.mapRelationTypeInBothSchemas.put(rtype, new ArrayList<IEventAnnotation>());
		}
		if(!relationTypes.contains(rtype))
		{
			this.relationTypes.add(rtype);
		}
		this.mapRelationTypeInBothSchemas.get(rtype).add(eventGold);
	}
	
	/**
	 * Stores events and relations type that are only present in to compare schema.
	 * 
	 * @param goldenStandardDocument
	 * @param events
	 */
	
	public void addEventJustInToCompare(IAnnotatedDocument goldenStandardDocument,Collection<IEventAnnotation> events) {
		for(IEventAnnotation ev:events)
		{
			this.justInToCompare.add(ev);
			this.documentjustInToCompare.put(goldenStandardDocument.getId(), ev);
			IRelationMultiType rtype = ev.getRelationType();
			if(!this.mapRelationTypeInjustInToCompare.containsKey(rtype)){
				this.mapRelationTypeInjustInToCompare.put(rtype, new ArrayList<IEventAnnotation>());
			}
			if(!relationTypes.contains(rtype))
			{
				this.relationTypes.add(rtype);
			}
			this.mapRelationTypeInjustInToCompare.get(rtype).add(ev);
		}
	}

	public Map<Long, IEventAnnotation> getDocumentSameInBothSchemas() {
		return documentSameInBothSchemas;
	}

	public Map<Long, IEventAnnotation> getDocumentjustInGoldenStandard() {
		return documentjustInGoldenStandard;
	}

	public Map<Long, IEventAnnotation> getDocumentjustInToCompare() {
		return documentjustInToCompare;
	}

	public Map<Long, IEventAnnotation> getDocumentfailByEntitiesMissing() {
		return documentfailByEntitiesMissing;
	}

	public List<IEventAnnotation> getSameInBothSchemas() {
		return sameInBothSchemas;
	}

	public List<IEventAnnotation> getJustInGoldenStandard() {
		return justInGoldenStandard;
	}

	public List<IEventAnnotation> getJustInToCompare() {
		return justInToCompare;
	}

	public List<IEventAnnotation> getFailByEntitiesMissing() {
		return failByEntitiesMissing;
	}
	
	public REEvaluationMap getMapRelationTypeInBothSchemas() {
		return mapRelationTypeInBothSchemas;
	}

	public REEvaluationMap getMapRelationTypeInGoldenStandard() {
		return mapRelationTypeInGoldenStandard;
	}

	public REEvaluationMap getMapRelationTypeInjustInToCompare() {
		return mapRelationTypeInjustInToCompare;
	}
	
	public List<IRelationMultiType> getAllRelationTypes(){
		return relationTypes;
	}
}
