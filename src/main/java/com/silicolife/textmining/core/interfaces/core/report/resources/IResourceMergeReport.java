package com.silicolife.textmining.core.interfaces.core.report.resources;

import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourceMergeReport extends IResourceReport{
	
	public IResource<IResourceElement> getResourceSource2();
	public void setResourceSource2(IResource<IResourceElement> resourceSource2);
	public IResource<IResourceElement> getResourceSource();
	public IResource<IResourceElement> getNewResource();
	public boolean isCreatedNewResource();

}
