package com.silicolife.textmining.core.interfaces.core.report.resources;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourceReport extends IReport{
	public boolean isActive();
	public IResource<IResourceElement> getResourceDestination();
	public void setResourceDestination(IResource<IResourceElement> resource);
	public int getNumberConflits();
	public List<IResourceUpdateConflits> getConflicts();
	public void addConflit(IResourceUpdateConflits conflit);
	public void addAllConflit(List<IResourceUpdateConflits> conflits);
	public boolean isPerformed();
	public void changePerformedStatus(boolean newStatus);
	public void changeActiveStatus(boolean newStatus);
	public void addTermAdding(int newTerms);
	public void addSynonymsAdding(int newSynonyms);
	public void addClassesAdding(int newClasses);	
	public void addExternalIDs(int newExternalIDs);	
	public int getTermsAdding();
	public int getSynonymsAdding();
	public int getClassesAdding();
	public int getExternalIDs();
	
}
