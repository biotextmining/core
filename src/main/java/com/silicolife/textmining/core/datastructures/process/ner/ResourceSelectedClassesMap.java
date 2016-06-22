package com.silicolife.textmining.core.datastructures.process.ner;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceSelectedClassesMap {
	
	private IResource<IResourceElement> resource;
	private Set<Long> selectedClassesID;
	private Set<Long> allClassesID;

	public ResourceSelectedClassesMap(){
	}
	
	public ResourceSelectedClassesMap(IResource<IResourceElement> resource, Set<Long> selectedClassesID, Set<Long> allClassesID){
		this.resource = resource;
		this.selectedClassesID = selectedClassesID;
		this.allClassesID = allClassesID;
	}

	@JsonDeserialize(as=ResourceImpl.class)
	public IResource<IResourceElement> getResource() {
		return resource;
	}
	
	public Set<Long> getSelectedClassesID() {
		return selectedClassesID;
	}

	public Set<Long> getAllClassesID() {
		return allClassesID;
	}

	public void setResource(IResource<IResourceElement> resource) {
		this.resource = resource;
	}

	public void setSelectedClassesID(Set<Long> selectedClassesID) {
		this.selectedClassesID = selectedClassesID;
	}

	public void setAllClassesID(Set<Long> allClassesID) {
		this.allClassesID = allClassesID;
	}

	@Override
	public String toString() {
		return "ResourceSelectedClassesMap [resource=" + resource + ", selectedClassesID=" + selectedClassesID
				+ ", allClassesID=" + allClassesID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allClassesID == null) ? 0 : allClassesID.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((selectedClassesID == null) ? 0 : selectedClassesID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceSelectedClassesMap other = (ResourceSelectedClassesMap) obj;
		if (allClassesID == null) {
			if (other.allClassesID != null)
				return false;
		} else if (!allClassesID.equals(other.allClassesID))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (selectedClassesID == null) {
			if (other.selectedClassesID != null)
				return false;
		} else if (!selectedClassesID.equals(other.selectedClassesID))
			return false;
		return true;
	}
	

}
