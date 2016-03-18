package com.silicolife.textmining.core.datastructures.annotation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.re.EventAnnotationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;

public class ManualCurationAnnotationsImpl implements IManualCurationAnnotations{
	
	@JsonDeserialize(contentAs = EntityAnnotationImpl.class)
	List<IEntityAnnotation> entityAnnotations;
	@JsonDeserialize(contentAs = EventAnnotationImpl.class)
	List<IEventAnnotation> eventAnootations;
	
	
	public ManualCurationAnnotationsImpl() {
		entityAnnotations = new ArrayList<IEntityAnnotation>();
		eventAnootations = new ArrayList<IEventAnnotation>();
	}

	public List<IEntityAnnotation> getEntityAnnotations() {
		return entityAnnotations;
	}

	public List<IEventAnnotation> getEventAnootations() {
		return eventAnootations;
	}

	public void setEntityAnnotations(List<IEntityAnnotation> entityAnnotations) {
		this.entityAnnotations = entityAnnotations;
	}

	public void setEventAnootations(List<IEventAnnotation> eventAnootations) {
		this.eventAnootations = eventAnootations;
	}

	@Override
	@JsonIgnore
	public List<IAnnotation> getAnnotations() {
		List<IAnnotation> list = new ArrayList<IAnnotation>();
		if(entityAnnotations!=null)
			list.addAll(entityAnnotations);
		if(eventAnootations!=null)
			list.addAll(eventAnootations);
		return list;
	}

}
