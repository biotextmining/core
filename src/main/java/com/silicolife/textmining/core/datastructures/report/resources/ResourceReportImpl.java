package com.silicolife.textmining.core.datastructures.report.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceReport;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceReportImpl extends ReportImpl implements IResourceReport{

	private boolean active;
	private int termsAdding;
	private int synonymsAdding;
	private int classesAdding;
	private int externalIDs;
	private IResource<IResourceElement> destinyresource;
	private List<IResourceUpdateConflits> conflits;
	private int getConflitsNumber;
	private boolean performed;

	
	public ResourceReportImpl(String title,IResource<IResourceElement> destinyresource) {
		super(title);
		this.active = true;
		this.getConflitsNumber = 0;
		this.destinyresource = destinyresource;
		this.performed = true;
		this.conflits = new ArrayList<IResourceUpdateConflits>();

	}
	
	public ResourceReportImpl(String title,Properties properties)
	{
		super(title,properties);
		this.active=false;
	}

	public boolean isActive() {
		return active;
	}

	public void changeActiveStatus(boolean newStatus) {
		this.active=newStatus;
	}

	public int getTermsAdding() {
		return termsAdding;
	}

	public int getSynonymsAdding() {
		return synonymsAdding;
	}

	public int getClassesAdding() {
		return classesAdding;
	}

	public void addTermAdding(int newTerms) {
		this.termsAdding+=newTerms;
	}

	public void addSynonymsAdding(int newSynonyms) {
		this.synonymsAdding+=newSynonyms;
	}

	public void addClassesAdding(int newClasses) {
		this.classesAdding+=newClasses;		
	}

	public void addExternalIDs(int newExternalIDs) {
		this.externalIDs+=newExternalIDs;
	}

	public int getExternalIDs() {
		return externalIDs;
	}
	
	public IResource<IResourceElement> getResourceDestination() {
		return destinyresource;
	}

	public List<IResourceUpdateConflits> getConflicts() {
		return conflits;
	}
	
	public void addConflit(IResourceUpdateConflits conflit) {
		conflits.add(conflit);
		getConflitsNumber ++;
	}
	
	public void addAllConflit(List<IResourceUpdateConflits> conflits) {
		this.conflits.addAll(conflits);
		getConflitsNumber += conflits.size();
	}

	public boolean isPerformed() {
		return performed;
	}

	public void changePerformedStatus(boolean newStatus) {
		this.performed = newStatus;
	}

	public int getNumberConflits() {
		return getConflitsNumber;
	}

	public void addConflitNumber(int conflits) {
		this.getConflitsNumber +=conflits;
	}

	@Override
	public void setResourceDestination(IResource<IResourceElement> destinyresource) {
		this.destinyresource=destinyresource;
	}
}
