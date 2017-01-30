package com.silicolife.textmining.core.datastructures.annotation;

import java.util.HashSet;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class AnnotationsFilterImpl implements IAnnotationsFilter{

	private Set<Long> anoteClassIds;
	private Set<Long> resourceElementIds;

	public AnnotationsFilterImpl(){
		this.anoteClassIds = new HashSet<>();
		this.resourceElementIds = new HashSet<>();
	}
	
	@Override
	public Set<Long> getAnoteClassIds() {
		return anoteClassIds;
	}

	@Override
	public Set<Long> getResourceElementIds() {
		return resourceElementIds;
	}

	@Override
	public void addResourceElement(IResourceElement resourceElement) {
		getResourceElementIds().add(resourceElement.getId());
	}

	@Override
	public void addAnoteClass(IAnoteClass klass) {
		getAnoteClassIds().add(klass.getId());
	}

	@Override
	public void addResourceElements(Set<IResourceElement> resourceElements) {
		for(IResourceElement resourceElement : resourceElements)
			addResourceElement(resourceElement);
	}

	@Override
	public void addAnoteClasses(Set<IAnoteClass> klasses) {
		for(IAnoteClass klass: klasses)
			addAnoteClass(klass);
	}

}
