package com.silicolife.textmining.core.datastructures.report.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;
import com.silicolife.textmining.core.interfaces.core.report.resources.ResourceUpdateConflitsType;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceUpdateConflitsImpl implements IResourceUpdateConflits{

	private ResourceUpdateConflitsType conflitType;
	private boolean solve;
	private String originalTerm;
	private String conflitTerm;
	
	
	private ResourceUpdateConflitsImpl(ResourceUpdateConflitsType conflitType)
	{
		this.conflitType=conflitType;
		this.solve = false;
	}
	
	/*
	 * already have term
	 */
	public ResourceUpdateConflitsImpl(ResourceUpdateConflitsType conflitType, IResourceElement element) {
		this(conflitType);
		this.originalTerm = element.getTerm();
	}

	
	/*
	 * TErm already has External Id
	 */
	public ResourceUpdateConflitsImpl(ResourceUpdateConflitsType conflitType,IResourceElement element, ResourceElementExtenalIds externalID) {
		this(conflitType);
		this.originalTerm = element.getTerm() + " -> "+externalID.getId().getReleExternalId() + " ("+externalID.getSources().getSouDescription() + ")";
	}
	
	/*
	 * TErm already has External Id
	 */
	public ResourceUpdateConflitsImpl(ResourceUpdateConflitsType conflitType,IResourceElement element, Classes original, Classes conflit) {
		this(conflitType);
		this.originalTerm = element.getTerm() + " ("+original.getClaName()+")";
		this.conflitTerm =  " AND ("+conflit.getClaName()+")";
	}

	public ResourceUpdateConflitsImpl(ResourceUpdateConflitsType alteradyhaveexternalid,IResourceElement element, IExternalID externalID) {
		this.originalTerm = element.getTerm() + " -> "+externalID.getExternalID() + " ("+externalID.getSource().getSource() + ")";
	}

	public ResourceUpdateConflitsImpl() {
		super();
	}

	@Override
	public String getOriginalTerm() {
		return originalTerm;
	}
	
	public void setOriginalTerm(String originalTerm) {
		this.originalTerm = originalTerm;
	}

	@Override
	public String getConflitTerm() {
		return conflitTerm;
	}
	

	public void setConflitTerm(String conflitTerm) {
		this.conflitTerm = conflitTerm;
	}	
	
	@Override
	public boolean isSolve() {
		return solve;
	}
	
	public void setSolve(boolean newStatus) {
		this.solve=newStatus;		
	}

	@Override
	public ResourceUpdateConflitsType getConflitType() {
		return conflitType;
	}

	public void setConflitType(ResourceUpdateConflitsType conflitType) {
		this.conflitType = conflitType;
	}
}
