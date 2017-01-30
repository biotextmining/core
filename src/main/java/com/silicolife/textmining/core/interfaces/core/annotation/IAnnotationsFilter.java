package com.silicolife.textmining.core.interfaces.core.annotation;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IAnnotationsFilter {

	/**
	 * 
	 * The annotations that belong to same publication must have the classes added to this set.
	 * 
	 * @return
	 */
	public Set<Long> getAnoteClassIds();
	
	/**
	 * 
	 * The annotations that belong to same publication must have at least one resource element from this set.
	 * 
	 * @return
	 */
	public Set<Long> getResourceElementIds();
	
	public void addResourceElement(IResourceElement resourceElement);
	
	public void addAnoteClass(IAnoteClass klass);
	
	public void addResourceElements(Set<IResourceElement> resourceElements);
	
	public void addAnoteClasses(Set<IAnoteClass> klasses);
	
}
