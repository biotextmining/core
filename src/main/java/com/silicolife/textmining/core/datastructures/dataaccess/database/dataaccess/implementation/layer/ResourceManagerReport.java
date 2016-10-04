package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.layer;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.report.resources.ResourceUpdateConflitsImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;

public class ResourceManagerReport implements IResourceManagerReport{
	
	private int termsAdded;
	private int synonymsAdded; 
	private int conflitNumber;
	private int externalIDAdded;
	@JsonDeserialize(contentAs = ResourceUpdateConflitsImpl.class)
	private List<IResourceUpdateConflits> conflits; 
	
	public ResourceManagerReport()
	{
		this.termsAdded = 0;
		this.synonymsAdded = 0;
		this.conflitNumber = 0;
		this.externalIDAdded = 0;
		this.conflits = new ArrayList<IResourceUpdateConflits>();
	}


	@Override
	public int getSynonymsAdded() {
		return synonymsAdded;
	}
	
	public void setSynonymsAdded(int synonymsNumber) {
		this.synonymsAdded = synonymsNumber;
	}
	
	public void setConflits(List<IResourceUpdateConflits> conflits) {
		this.conflits = conflits;
	}
	
	@Override
	public List<IResourceUpdateConflits> getConflits() {
		return conflits;
	}

	@Override
	public int getConflitNumber() {
		return conflitNumber;
	}

	public void setConflitNumber(int conflitNumber) {
		this.conflitNumber = conflitNumber;
	}

	@Override
	public int getExternalIDAdded() {
		return externalIDAdded;
	}

	public void setExternalIDAdded(int externalIDsNumber) {
		this.externalIDAdded = externalIDsNumber;
	}
	
	@Override
	public int getTermsAdded() {
		return termsAdded;
	}

	public void setTermsAdded(int termsAdded) {
		this.termsAdded = termsAdded;
	}

	@Override
	public void addTerms(int termNumbers) {
		this.termsAdded +=termNumbers;
	}

	@Override
	public void addSynonyms(int synonymsNumber) {
		this.synonymsAdded +=	synonymsNumber;	
	}


	@Override
	public void addExternalID(int externaIDNumber) {
		this.externalIDAdded += externaIDNumber;
	}


	@Override
	public boolean hasConflits() {
		return conflitNumber != 0;
	}

	@Override
	public void addconflit(IResourceUpdateConflits conflit) {
		conflits.add(conflit);
		conflitNumber++;				
	}
}
