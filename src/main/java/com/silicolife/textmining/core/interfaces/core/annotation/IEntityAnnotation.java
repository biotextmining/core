package com.silicolife.textmining.core.interfaces.core.annotation;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IEntityAnnotation extends IAnnotation, Comparable<IEntityAnnotation> {
	
	/**
	 * Method that returns annotation text value
	 * 
	 * @return annotation original representation
	 */
	public String getAnnotationValue();
	
	/**
	 * Method that returns associated resourceID if annotation was provide for a resource element
	 * 
	 * @return returns associated resourceID
	 * 		  -1 if do not have resourceID associated
	 */
	public IResourceElement getResourceElement();
	
	/**
	 * Method that return classID for annotation
	 * 
	 * @return 
	 */
	public IAnoteClass getClassAnnotation();
	
	/**
	 * Method that set ResourceID for element
	 * 
	 * @param resourceElmID
	 */
	public void setResourceElement(IResourceElement resourceelement);
	
	public IEntityAnnotation clone();
	
	
	public void setClassAnnotation(IAnoteClass newKlass);


	/**
	 * Define if a NER annotation
	 * 
	 * @return
	 */
	public boolean isAbreviation();
}
