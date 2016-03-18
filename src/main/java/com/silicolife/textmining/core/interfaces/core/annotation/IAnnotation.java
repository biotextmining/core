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
	
	/**
	 * Return DatabaseID for annotation
	 * 
	 * @return
	 */
	public long getId();
	
	
	public IAnnotation clone();
	
	/**
	 * Get Start Sentence index for annotation
	 * 
	 * @return
	 */
	public long getSentenceStartOffset();
	
	
	
	public void setSentenceStartOffset(long sentenceStartOffset);
	
	
	/**
	 * Get End Sentence index for annotation
	 * 
	 * @return
	 */
	public long getSentenceEndOffset();
	
	
	public void setSentenceEndOffset(long sentenceEndOffset);
	
	
	public String getNotes();
	
	public void setNotes(String notes);
	
	public Properties getProperties();

	public void generateNewId();
	
	public boolean isActive();

}
