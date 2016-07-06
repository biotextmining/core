package com.silicolife.textmining.core.datastructures.report.resources;

import java.io.File;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class PortResourceUpdateReport {
	
	private IResourceUpdateReport report;
	private IResource<IResourceElement> resource;
	private boolean usevalidation = true; 


	
	public PortResourceUpdateReport(String font)
	{
		setReport(new ResourceUpdateReportImpl("", null, new File(""), font));
	}


	public IResourceUpdateReport getReport() {
		return report;
	}


	public void setReport(IResourceUpdateReport report) {
		this.report = report;
	}
	
	protected void memoryAndProgress(int step, int total) {
		System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
//		Runtime.getRuntime().gc();
//		System.out.println((Runtime.getRuntime().totalMemory()- Runtime.getRuntime().freeMemory())/(1024*1024) + " MB ");
	}
	
	protected void memoryAndProgress(long startTime,int step, int total) {
		System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
//		Runtime.getRuntime().gc();
//		System.out.println((Runtime.getRuntime().totalMemory()- Runtime.getRuntime().freeMemory())/(1024*1024) + " MB ");
	}
	
	public void setResource(IResource<IResourceElement> resource) {
		this.resource = resource;
		try {
			this.usevalidation = InitConfiguration.getDataAccess().getResourceContent(resource).getTermNumber() != 0;
		} catch (ANoteException e) {
			this.usevalidation = true;
		}
		this.report.setResourceDestination(resource);
	}


	public IResource<IResourceElement> getResource() {
		return resource;
	}


	public boolean usevalidation() {
		return usevalidation;
	}
	
	

}
