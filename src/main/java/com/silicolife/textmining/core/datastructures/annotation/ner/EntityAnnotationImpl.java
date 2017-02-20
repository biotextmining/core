package com.silicolife.textmining.core.datastructures.annotation.ner;

import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

/**
 * Class that represent an bio-entity annotation
 * 
 * @author Hugo Costa
 *
 */
public class EntityAnnotationImpl extends AnnotationImpl implements IEntityAnnotation{

	@JsonDeserialize(as = AnoteClass.class)
	private IAnoteClass classAnnotation;
	@JsonDeserialize(as = ResourceElementImpl.class)
	private IResourceElement resourceElement;
	private String annotationValue;
	private boolean abreviation;
	
	

	public EntityAnnotationImpl() {
		super();
	}
	
	public EntityAnnotationImpl(long id, long start, long end,IAnoteClass classAnnotation,IResourceElement resourceElement,
			String value,boolean abreviation,Properties properties,boolean active,boolean validated) {
		super(id, start, end,AnnotationType.ner.name(),properties,active,validated);
		this.classAnnotation=classAnnotation;
		this.annotationValue=value;
		this.resourceElement=resourceElement;
		this.abreviation=abreviation;
	}
	

	public EntityAnnotationImpl(long start, long end,IAnoteClass classAnnotation,IResourceElement resourceElement,String value,boolean abreviation,boolean validated,Properties properties)
	{
		this(GenerateRandomId.generateID(),start,end,classAnnotation,resourceElement,value,abreviation,properties,true,abreviation);
	}

	@Override
	public void setClassAnnotation(IAnoteClass classAnnotation) {
		this.classAnnotation=classAnnotation;
	}

	@Override
	public IAnoteClass getClassAnnotation() {
		return classAnnotation;
	}
	
	public void setAnnotationValue(String annotationValue) {
		this.annotationValue = annotationValue;
	}

	@Override
	public String getAnnotationValue() {
		return annotationValue;
	}

	@Override
	public void setResourceElement(IResourceElement resourceElement) {
		this.resourceElement = resourceElement;
	}

	public IResourceElement getResourceElement() {
		return resourceElement;
	}
	
	public IEntityAnnotation clone()
	{
		IEntityAnnotation ent = new EntityAnnotationImpl(super.getId(),super.getStartOffset(),super.getEndOffset(),
				getClassAnnotation(),getResourceElement(), getAnnotationValue(),isAbreviation(),getProperties(),isActive(),isValidated());
		return ent;		
	}
	
	public boolean equals(IEntityAnnotation o)
	{
		return compareTo(o) == 0;
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof IEntityAnnotation)
		{
			return compareTo((IEntityAnnotation)o) == 0;
		}
		else
		{
			return false;
		}
	}
	
	public static String convertEntitiesToString(List<IEntityAnnotation> entities) {
		String entityToString = new String();
		for(IEntityAnnotation ent:entities)
		{
			entityToString = entityToString + ent.getAnnotationValue() + " (" + ent.getClassAnnotation().getName() + ") \n";
		}
		return entityToString + "\n";
	}
	
	
	/**
	 * 
	 * This method must be reviewed!
	 */
	public int compareTo(IEntityAnnotation o) {
		if(this.getResourceElement()!=null && o.getResourceElement()!=null && this.getResourceElement().equals(o.getResourceElement()))
		{
			return 0;
		}
		else if(this.getAnnotationValue().equals(o.getAnnotationValue()))
		{
			return (int) (this.getClassAnnotation().getId() - o.getClassAnnotation().getId());
		}
		else
		{
			return this.getAnnotationValue().compareTo(o.getAnnotationValue());
		}
	}

	public String toString()
	{
		return getAnnotationValue()+ "( "+getStartOffset()+"-"+getEndOffset()+" )";
	}

	@Override
	public boolean isAbreviation() {
		return abreviation;
	}
	
	public void setAbreviation(boolean abreviation) {
		this.abreviation = abreviation;
	}

}
