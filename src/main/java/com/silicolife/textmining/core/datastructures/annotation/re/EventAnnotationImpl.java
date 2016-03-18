package com.silicolife.textmining.core.datastructures.annotation.re;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.process.re.RelationMultiTypeImpl;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.IEventProperties;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;


public class EventAnnotationImpl extends AnnotationImpl implements IEventAnnotation{

	@JsonDeserialize(contentAs = EntityAnnotationImpl.class)
	private List<IEntityAnnotation> entitiesAtLeft;
	@JsonDeserialize(contentAs = EntityAnnotationImpl.class)
	private List<IEntityAnnotation> entitiesAtRight;
	private String eventClue;
	private long ontologicalClassID;
	private String ontologicalClass;
	@JsonDeserialize(as = EventPropertiesImpl.class)
	private IEventProperties eventProperties;
		
	public EventAnnotationImpl(long id,long start, long end, String type,
			List<IEntityAnnotation> left,List<IEntityAnnotation> right,
			String clue,long ontologicalClassID,String ontologicalClass,
			IEventProperties eventProperties,boolean active) {
		super(id,start, end,AnnotationType.re.name(),eventProperties.getProperties(),active);
		this.entitiesAtLeft=left;
		this.entitiesAtRight=right;
		this.eventClue=clue;
		this.ontologicalClass=ontologicalClass;
		this.ontologicalClassID=ontologicalClassID;
		this.eventProperties=eventProperties;
	}
	
	public EventAnnotationImpl(long start, long end, String type,
			List<IEntityAnnotation> left,List<IEntityAnnotation> right,
			String clue,long ontologicalClassID,String ontologicalClass,
			IEventProperties eventProperties) {
		super(start, end,AnnotationType.re.name(),eventProperties.getProperties());
		this.entitiesAtLeft=left;
		this.entitiesAtRight=right;
		this.eventClue=clue;
		this.ontologicalClass=ontologicalClass;
		this.ontologicalClassID=ontologicalClassID;
		this.eventProperties=eventProperties;
	}

	public EventAnnotationImpl() {
		super();
	}

	
	@Override
	public List<IEntityAnnotation> getEntitiesAtLeft() {
		return this.entitiesAtLeft;
	}
	
	@Override
	public void setEntitiesAtLeft(List<IEntityAnnotation> entitiesAtLeft) {
		this.entitiesAtLeft = entitiesAtLeft;
	}
	
	@Override
	public List<IEntityAnnotation> getEntitiesAtRight() {
		return this.entitiesAtRight;
	}
	
	@Override
	public void setEntitiesAtRight(List<IEntityAnnotation> entitiesAtRight) {
		this.entitiesAtRight = entitiesAtRight;
	}
	
	@Override
	public String getEventClue() {
		return this.eventClue;
	}

	public void setEventClue(String eventClue) {
		this.eventClue = eventClue;
	}

	@Override
	public long getOntologicalClassID() {
		return this.ontologicalClassID;
	}
	
	public void setOntologicalClassID(long ontologicalClassID) {
		this.ontologicalClassID = ontologicalClassID;
	}

	@Override
	public IEventProperties getEventProperties() {
		return eventProperties;
	}

	
	@Override
	public void setEventProperties(IEventProperties eventProperties) {
		this.eventProperties = eventProperties;
	}
	
	@Override
	public String getOntologicalClass() {
		return this.ontologicalClass;
	}
	

	public void setOntologicalClass(String ontologicalClass) {
		this.ontologicalClass = ontologicalClass;
	}

	public void addEntityAtLeft(IEntityAnnotation ent) {
		if(entitiesAtLeft == null)
			entitiesAtLeft = new ArrayList<IEntityAnnotation>();
		entitiesAtLeft.add(ent);
	}

	public void addEntityAtRight(IEntityAnnotation ent) {
		if(entitiesAtRight == null)
			entitiesAtRight = new ArrayList<IEntityAnnotation>();
		entitiesAtRight.add(ent);	
	}

	public void addEventProperty(String key, String value) {
		if(eventProperties == null)
		{
			eventProperties = new EventPropertiesImpl();
		}
		if(key.equals(GlobalNames.relationPropertyLemma))
		{
			eventProperties.setLemma(value);
		}
		else if(key.equals(GlobalNames.relationPropertyPolarity))
		{
			eventProperties.setPolarity(PolarityEnum.covertIntToPolarityEnum(Integer.valueOf(value)));
		}
		else if(key.equals(GlobalNames.relationPropertyDirectionally))
		{
			eventProperties.setDirectionally(DirectionallyEnum.covertIntToDirectionallyEnum(Integer.valueOf(value)));
		}
		else
		{
			eventProperties.setGeneralProperty(key, value);
		}	
	}
	
	public IEventAnnotation clone(){
		return new EventAnnotationImpl(this.getId(), this.getStartOffset(), this.getEndOffset(), this.getAnnotationType(), this.getEntitiesAtLeft(), this.getEntitiesAtRight(), this.getEventClue(), this.getOntologicalClassID(), this.getOntologicalClass(), this.getEventProperties(),this.isActive());
	}
	
	public String toString()
	{
		String value = new String();
		value = "Relation "+getEventClue()+" ( "+getStartOffset()+"-"+getEndOffset()+" )+\n";
		value = value +"\tEntities At Left :";
		for(IEntityAnnotation entLeft : getEntitiesAtLeft())
			value = value + "\t\t"+entLeft.toString() + "\n";
		value = value +"\tEntities At Right :";
		for(IEntityAnnotation entRight : getEntitiesAtRight())
			value = value + "\t\t"+entRight.toString() + "\n";
		return value;
		
	}

	@JsonIgnore
	@Override
	public IRelationMultiType getRelationType() {
		SortedSet<Long> setClassEntityAtLeft = new TreeSet<Long>();
		for(IEntityAnnotation entityAtLeft : this.getEntitiesAtLeft())
		{
			if(!setClassEntityAtLeft.contains(entityAtLeft.getClassAnnotation().getId()))
				setClassEntityAtLeft.add(entityAtLeft.getClassAnnotation().getId());
		}
		SortedSet<Long> setClassEntityAtRight = new TreeSet<Long>();
		for(IEntityAnnotation entityAtRight : this.getEntitiesAtRight())
		{
			if(!setClassEntityAtRight.contains(entityAtRight.getClassAnnotation().getId()))
				setClassEntityAtRight.add(entityAtRight.getClassAnnotation().getId());
		}
		return new RelationMultiTypeImpl(setClassEntityAtLeft, setClassEntityAtRight);
	}
}
