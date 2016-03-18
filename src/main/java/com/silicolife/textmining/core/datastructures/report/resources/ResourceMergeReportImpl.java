package com.silicolife.textmining.core.datastructures.report.resources;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceMergeReport;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceMergeReportImpl extends ResourceReportImpl implements IResourceMergeReport{

	private IResource<IResourceElement> newResource;
	private IResource<IResourceElement> source;
	private IResource<IResourceElement> source2;

	
	public ResourceMergeReportImpl(String title,IResource<IResourceElement> newResource,IResource<IResourceElement> source,IResource<IResourceElement> destiny) {
		super(title,destiny);
		this.newResource=newResource;
		this.source = source;
	}
	
	public ResourceMergeReportImpl(String title,Properties properties,IResource<IResourceElement> newResource,IResource<IResourceElement> source,IResource<IResourceElement> destiny) {
		super(title,properties);
		this.source = source;
		this.newResource=newResource;
	}

	public void setResourceSource2(IResource<IResourceElement> source2) {
		this.source2 = source2;
	}

	public IResource<IResourceElement> getResourceSource() {
		return source;
	}

	public IResource<IResourceElement> getResourceSource2() {
		return source2;
	}

	@Override
	public IResource<IResourceElement> getNewResource() {
		return newResource;
	}

	@Override
	public boolean isCreatedNewResource() {
		return newResource !=null;
	}


}
