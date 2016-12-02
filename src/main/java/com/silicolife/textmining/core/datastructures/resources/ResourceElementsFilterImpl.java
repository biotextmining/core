package com.silicolife.textmining.core.datastructures.resources;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;

public class ResourceElementsFilterImpl implements IResourceElementsFilter{
	
	private Set<Long> resourceIds;
	
	private Set<Long> sourceIds;
	
	public ResourceElementsFilterImpl(){
		this.resourceIds = new HashSet<>();
		this.sourceIds = new HashSet<>();
	}

	public Set<Long> getResourceIds() {
		return resourceIds;
	}

	public Set<Long> getSourceIds() {
		return sourceIds;
	}

	public void setResourceIds(Set<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public void setSourceIds(Set<Long> sourceIds) {
		this.sourceIds = sourceIds;
	}
	
	@JsonIgnore
	public void addResources(Set<IResource<IResourceElement>> resources){
		for(IResource<IResourceElement> resource : resources){
			this.resourceIds.add(resource.getId());
		}
	}
	
	@JsonIgnore
	public void addSources(Set<ISource> sources){
		for(ISource source : sources){
			this.sourceIds.add(source.getSourceID());
		}
	}

	@JsonIgnore
	public void addResource(IResource<IResourceElement> resource) {
		this.resourceIds.add(resource.getId());
		
	}

	@JsonIgnore
	public void addSource(ISource source) {
		this.sourceIds.add(source.getSourceID());
	}
	

}
