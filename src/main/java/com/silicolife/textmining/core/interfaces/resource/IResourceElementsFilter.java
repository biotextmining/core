package com.silicolife.textmining.core.interfaces.resource;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public interface IResourceElementsFilter {
	
	public Set<Long> getSourceIds();
	
	public Set<Long> getResourceIds();
	
	public void addResource(IResource<IResourceElement> resource);
	
	public void addSource(ISource source);
	
	public void addResources(Set<IResource<IResourceElement>> resources);
	
	public void addSources(Set<ISource> sources);

}
