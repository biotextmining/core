package com.silicolife.textmining.core.datastructures.resources.merge;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.merge.IResourceMergeConfiguration;

public class ResourceMergeConfigurationImpl implements IResourceMergeConfiguration{
	
	private IResource<IResourceElement> newResource;
	private IResource<IResourceElement> sourceResource;
	private Set<IAnoteClass> sourceResourceClasses;
	
	private IResource<IResourceElement> destinyResource;
	private Set<IAnoteClass> destinyResourceClasses;
	
	public ResourceMergeConfigurationImpl(IResource<IResourceElement> newResource,IResource<IResourceElement> sourceResource,Set<IAnoteClass> sourceResourceClasses,
			IResource<IResourceElement> destinyResource,Set<IAnoteClass> destinyResourceClasses)
	{
		this.newResource = newResource;
		this.sourceResource=sourceResource;
		this.sourceResourceClasses = sourceResourceClasses;
		this.destinyResource = destinyResource;
		this.destinyResourceClasses = destinyResourceClasses;
	}
	
	public ResourceMergeConfigurationImpl(IResource<IResourceElement> sourceResource,Set<IAnoteClass> sourceResourceClasses,
			IResource<IResourceElement> destinyResource,Set<IAnoteClass> destinyResourceClasses)
	{
		this.newResource = null;
		this.sourceResource=sourceResource;
		this.sourceResourceClasses = sourceResourceClasses;
		this.destinyResource = destinyResource;
		this.destinyResourceClasses = destinyResourceClasses;
	}

	@Override
	public IResource<IResourceElement> getDestinyResource() {
		return destinyResource;
	}

	@Override
	public IResource<IResourceElement> getSourceResource() {
		return sourceResource;
	}

	@Override
	public Set<IAnoteClass> getDestinyClasses() {
		return destinyResourceClasses;
	}

	@Override
	public Set<IAnoteClass> getSourceClasses() {
		return sourceResourceClasses;
	}

	@Override
	public boolean createANewResourceAsResult() {
		return newResource != null;
	}

	@Override
	public IResource<IResourceElement> getNewToMergeResources() {
		return newResource;
	}

}
