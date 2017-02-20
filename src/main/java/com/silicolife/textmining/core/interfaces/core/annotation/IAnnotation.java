package com.silicolife.textmining.core.interfaces.core.annotation;

import java.util.Properties;

/**
 * Interface that represents a Simple Annotation
 * 
 * @author Hugo Costa
 *
 */
public interface IAnnotation extends Cloneable{

	
	/**
	 * Return DatabaseID for annotation
	 * 
	 * @return
	 */
	public long getId();
	
	public void generateNewId();
	
	/**
	 * Return Annotation Type (NER,RE)
	 * 
	 * @return {@link String}
	 */
	public String getAnnotationType();
	
	/**
	 * Return start offset in text for Annotation
	 * 
	 * @return {@link Long}
	 */
	public long getStartOffset();
	
	/**
	 * Return end offset in text for Annotation
	 * 
	 * @return
	 */
	public long getEndOffset();
	
	public String getNotes();
	
	public void setNotes(String notes);
	
	public Properties getProperties();

	
	/**
	 * Method that return if annotation was manual validated
	 * 
	 * @return
	 */
	public boolean isValidated();

	
	public boolean isActive();
	
	public IAnnotation clone();

}
