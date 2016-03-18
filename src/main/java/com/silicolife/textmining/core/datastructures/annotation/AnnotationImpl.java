package com.silicolife.textmining.core.datastructures.annotation;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;

/**
 * 
 * @author Hugo Costa
 *
 */
public class AnnotationImpl implements IAnnotation{

	private long id;
	private long startOffset;
	private long endOffset;
	private long sentenceStartOffset;
	private long sentenceEndOffset;
	private String annotationType;
	private String notes;
	private Properties properties;
	private boolean active;
	
	public AnnotationImpl(long id,long start,long end,String annotationType,Properties properties,boolean active)
	{
		this.id=id;
		this.startOffset=start;
		this.endOffset=end;
		this.sentenceStartOffset=-1;
		this.sentenceEndOffset=-1;
		this.properties=properties;
		this.annotationType=annotationType;
		this.active=active;
	}
	
	public AnnotationImpl(long start,long end,String annotationType,Properties properties)
	{
		this(GenerateRandomId.generateID(), start, end,annotationType,properties,true);
	}
	
	
	public AnnotationImpl() {
		super();
	}

	@Override
	public long getStartOffset() {
		return startOffset;
	}
	
	public void setStartOffset(long startOffset) {
		this.startOffset = startOffset;
	}
	
	@Override
	public long getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(long endOffset) {
		this.endOffset = endOffset;
	}
	
	@Override
	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getSentenceStartOffset() {
		return sentenceStartOffset;
	}

	@Override
	public long getSentenceEndOffset() {
		return sentenceEndOffset;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void setSentenceStartOffset(long startSentenceOffset) {
		this.sentenceStartOffset=startSentenceOffset;
	}

	@Override
	public void setSentenceEndOffset(long endSentenceOffset) {
		this.sentenceEndOffset=endSentenceOffset;
	}

	@Override
	public void setNotes(String notes) {
		this.notes=notes;
	}

	@Override
	public void generateNewId() {
		this.id=GenerateRandomId.generateID();
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public IAnnotation clone()
	{
		return new AnnotationImpl(this.getId(),this.getStartOffset(), this.getEndOffset(),this.getAnnotationType(),this.getProperties(),this.isActive());
	}
}
