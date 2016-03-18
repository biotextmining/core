package com.silicolife.textmining.core.interfaces.resource.merge;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourceMergeConfiguration {
	
	public IResource<IResourceElement> getDestinyResource();
	public IResource<IResourceElement> getSourceResource();
	
	public Set<IAnoteClass> getDestinyClasses();
	public Set<IAnoteClass> getSourceClasses();
	
	public boolean createANewResourceAsResult();
	public IResource<IResourceElement> getNewToMergeResources();

	
}
