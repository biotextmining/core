package com.silicolife.textmining.core.datastructures.process.evaluation.ner;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;

public class NERSchemasEvaluationResultsImpl {
	
	
	private List<IEntityAnnotation> entitiesInBothNER;
	private List<IEntityAnnotation> entitiesOnlyNERGoldStandart;
	private List<IEntityAnnotation> entitiesOnlyNERForComparing;
	
//	private SortedMap<Integer, List<IEntityAnnotation>> entitiesInBothNERbyDocument;
//	private SortedMap<Integer, List<IEntityAnnotation>> entitiesOnlyNERGoldStandartbyDocument;
//	private SortedMap<Integer, List<IEntityAnnotation>> entitiesOnlyNERForComparingbyDocument;
	
	private SortedMap<String, Integer> entityClassesInBothNER;
	private SortedMap<String, Integer> entityClassesOnlyNERGoldStandart;
	private SortedMap<String, Integer> entityClassesOnlyNERForComparing;

	public NERSchemasEvaluationResultsImpl() {
		this.entitiesInBothNER = new ArrayList<IEntityAnnotation>();
		this.entitiesOnlyNERGoldStandart = new ArrayList<IEntityAnnotation>();
		this.entitiesOnlyNERForComparing = new ArrayList<IEntityAnnotation>();
//		this.entitiesInBothNERbyDocument = new TreeMap<Integer, List<IEntityAnnotation>>();
//		this.entitiesOnlyNERGoldStandartbyDocument = new TreeMap<Integer, List<IEntityAnnotation>>();
//		this.entitiesOnlyNERForComparingbyDocument = new TreeMap<Integer, List<IEntityAnnotation>>();
		this.entityClassesInBothNER = new TreeMap<String, Integer>();
		this.entityClassesOnlyNERGoldStandart = new TreeMap<String, Integer>();
		this.entityClassesOnlyNERForComparing = new TreeMap<String, Integer>();
	}
	
	public void addToEntitiesInBothNER(Long docID, IEntityAnnotation entity){
		this.entitiesInBothNER.add(entity);
//		if(!entitiesInBothNERbyDocument.containsKey(docID)){
//			this.entitiesInBothNERbyDocument.put(docID, new ArrayList<IEntityAnnotation>());
//		}
//		this.entitiesInBothNERbyDocument.get(docID).add(entity);
		String classType = entity.getClassAnnotation().getName();
		if(!entityClassesInBothNER.containsKey(classType)){
			this.entityClassesInBothNER.put(classType, 0);
		}
		int updateValue = this.entityClassesInBothNER.get(classType) + 1;
		this.entityClassesInBothNER.put(classType, updateValue);
	}
	
	public void addToentitiesOnlyNERGoldStandart(Long docID, IEntityAnnotation entity){
		this.entitiesOnlyNERGoldStandart.add(entity);
//		if(!entitiesOnlyNERGoldStandartbyDocument.containsKey(docID)){
//			this.entitiesOnlyNERGoldStandartbyDocument.put(docID, new ArrayList<IEntityAnnotation>());
//		}
//		this.entitiesOnlyNERGoldStandartbyDocument.get(docID).add(entity);
		String classType = entity.getClassAnnotation().getName();
		if(!entityClassesOnlyNERGoldStandart.containsKey(classType)){
			this.entityClassesOnlyNERGoldStandart.put(classType, 0);
		}
		int updateValue = this.entityClassesOnlyNERGoldStandart.get(classType) + 1;
		this.entityClassesOnlyNERGoldStandart.put(classType, updateValue);
	}
	
	public void addToentitiesOnlyNERForComparingbyDocument(Long docID, IEntityAnnotation entity){
		this.entitiesOnlyNERForComparing.add(entity);
//		if(!entitiesOnlyNERForComparingbyDocument.containsKey(docID)){
//			this.entitiesOnlyNERForComparingbyDocument.put(docID, new ArrayList<IEntityAnnotation>());
//		}
//		this.entitiesOnlyNERForComparingbyDocument.get(docID).add(entity);
		String classType = entity.getClassAnnotation().getName();
		if(!entityClassesOnlyNERForComparing.containsKey(classType)){
			this.entityClassesOnlyNERForComparing.put(classType, 0);
		}
		int updateValue = this.entityClassesOnlyNERForComparing.get(classType) + 1;
		this.entityClassesOnlyNERForComparing.put(classType, updateValue);
	}
	
	public List<IEntityAnnotation> getEntitiesInBothNER() {
		return entitiesInBothNER;
	}

	public List<IEntityAnnotation> getEntitiesOnlyNERGoldStandart() {
		return entitiesOnlyNERGoldStandart;
	}

	public List<IEntityAnnotation> getEntitiesOnlyNERForComparing() {
		return entitiesOnlyNERForComparing;
	}

//	public SortedMap<Integer, List<IEntityAnnotation>> getEntitiesInBothNERbyDocument() {
//		return entitiesInBothNERbyDocument;
//	}
//
//	public SortedMap<Integer, List<IEntityAnnotation>> getEntitiesOnlyNERGoldStandartbyDocument() {
//		return entitiesOnlyNERGoldStandartbyDocument;
//	}
//
//	public SortedMap<Integer, List<IEntityAnnotation>> getEntitiesOnlyNERForComparingbyDocument() {
//		return entitiesOnlyNERForComparingbyDocument;
//	}
	
	public SortedSet<String> getAllAnnotatedClasses(){
		SortedSet<String> allAnnotatedClasses = new TreeSet<String>();
		allAnnotatedClasses.addAll(entityClassesInBothNER.keySet());
		allAnnotatedClasses.addAll(entityClassesOnlyNERGoldStandart.keySet());
		allAnnotatedClasses.addAll(entityClassesOnlyNERForComparing.keySet());
		return allAnnotatedClasses;
	}

	public SortedMap<String, Integer> getEntityClassesInBothNER() {
		return entityClassesInBothNER;
	}

	public SortedMap<String, Integer> getEntityClassesOnlyNERGoldStandart() {
		return entityClassesOnlyNERGoldStandart;
	}

	public SortedMap<String, Integer> getEntityClassesOnlyNERForComparing() {
		return entityClassesOnlyNERForComparing;
	}
	
	
	

}
