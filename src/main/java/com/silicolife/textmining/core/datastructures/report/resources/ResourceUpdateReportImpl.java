package com.silicolife.textmining.core.datastructures.report.resources;

import java.io.File;

import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IOntology;

public class ResourceUpdateReportImpl extends ResourceReportImpl implements IResourceUpdateReport{

	
	private String file;
	private String font;
	
	public ResourceUpdateReportImpl(String title,IResource<IResourceElement> resource,File file,String font) {
		super(title, resource);
	}

	public String getFile() {
		return file;
	}

	public String getDataFont() {
		return font;
	}

	public void updateFile(File file) {
		this.file = file.getAbsolutePath();
	}
	
	public IResourceUpdateReport clone()
	{
		IResourceUpdateReport newReport = new ResourceUpdateReportImpl(getTitle(),getResourceDestination(), new File(getFile()), getDataFont());
		newReport.addClassesAdding(getClassesAdding());
		newReport.addAllConflit(getConflicts());
		newReport.addExternalIDs(getExternalIDs());
		newReport.addSynonymsAdding(getSynonymsAdding());
		newReport.addTermAdding(getTermsAdding());
		newReport.setTime(getTime());
		return newReport;
		
	}

}
