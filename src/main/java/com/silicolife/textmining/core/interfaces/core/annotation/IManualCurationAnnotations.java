package com.silicolife.textmining.core.interfaces.core.annotation;

import java.util.List;

public interface IManualCurationAnnotations {
	
	public List<IAnnotation> getAnnotations();
	
	public List<IEntityAnnotation> getEntityAnnotations();

	public List<IEventAnnotation> getEventAnootations();
	
	public void setEntityAnnotations(List<IEntityAnnotation> entityAnnotations);

	public void setEventAnootations(List<IEventAnnotation> eventAnootations);

}
