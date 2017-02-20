package com.silicolife.textmining.core.interfaces.core.annotation;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.re.IEventProperties;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;

/**
 * 
 * Event is also known as Relation extends an {@link IAnnotation} and represents @Note2 Event Annotation for Text Miming.
 * 
 * @author Hugo Costa
 *
 */
public interface IEventAnnotation extends IAnnotation{
	
	/**
	 * Method that return a {@link List} of {@link IEntityAnnotation} at left of event center
	 * 
	 * @return {@link List}{@link IEntityAnnotation}
	 */
	public List<IEntityAnnotation> getEntitiesAtLeft();
	
	/**
	 * Method that return a {@link List} of {@link IEntityAnnotation} at right of event center
	 * 
	 * @return {@link List}{@link IEntityAnnotation}
	 */
	public List<IEntityAnnotation> getEntitiesAtRight();
	
	/**
	 * Method that return Event Clue ( if exist).
	 * 
	 * @return {@link String}
	 */
	public String getEventClue(); // Verb
		
	/**
	 * Method that return {@link IEventProperties}
	 * 
	 * @return {@link IEventProperties}
	 */
	public IEventProperties getEventProperties();
	
	/**
	 * Methof to add an {@link IEntityAnnotation} to the left side of event
	 * 
	 * @param ent
	 */
	public void addEntityAtLeft(IEntityAnnotation ent);
	
	/**
	 * Methof to add an {@link IEntityAnnotation} to the right side of event
	 * 
	 * @param ent
	 */
	public void addEntityAtRight(IEntityAnnotation ent);
	
	/**
	 * Methof to add a property to event
	 * 
	 * @param ent
	 */
	public void addEventProperty(String key,String value);
	
	
	/**
	 * 
	 * @param ent
	 */
	public void setEntitiesAtLeft(List<IEntityAnnotation> entAtLeft);
	
	/**
	 * 
	 * @param ent
	 */
	public void setEntitiesAtRight(List<IEntityAnnotation> entAtRight);
	
	/**
	 * 
	 * @param properties
	 */
	public void setEventProperties(IEventProperties eventProperties);
	
	
	public IEventAnnotation clone();
	
	/**
	 * get relation Type 
	 * 
	 * @return
	 */
	public IRelationMultiType getRelationType();
}
