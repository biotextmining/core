package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.layer;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.report.resources.ResourceUpdateConflitsImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;

public class ResourceManagerReport implements IResourceManagerReport{
	
	private int termNumber;
	private int synonymsNumber; 
	private int conflitNumber;
	private int externalIDNumber;
	@JsonDeserialize(contentAs = ResourceUpdateConflitsImpl.class)
	private List<IResourceUpdateConflits> conflits; 
	
	public ResourceManagerReport()
	{
		this.termNumber = 0;
		this.synonymsNumber = 0;
		this.conflitNumber = 0;
		this.externalIDNumber = 0;
		this.conflits = new ArrayList<IResourceUpdateConflits>();
	}


	@JsonGetter("synonymsNumber")
	@Override
	public int getSynonymsAdded() {
		return synonymsNumber;
	}
	
	@JsonSetter("synonymsNumber")
	public void setSynonymsAdded(int synonymsNumber) {
		this.synonymsNumber = synonymsNumber;
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

	@JsonGetter("externalIDNumber")
	@Override
	public int getExternalIDAdded() {
		return externalIDNumber;
	}

	@JsonSetter("externalIDNumber")
	public void setExternalIDAdded(int externalIDsNumber) {
		this.externalIDNumber = externalIDsNumber;
	}
	
	@JsonGetter("termNumber")
	@Override
	public int getTermsAdded() {
		return termNumber;
	}

	@JsonSetter("termNumber")
	public void setTermsAdded(int termNumber) {
		this.termNumber = termNumber;
	}

	@Override
	public void addTerms(int termNumbers) {
		this.termNumber +=termNumbers;
	}

	@Override
	public void addSynonyms(int synonymsNumber) {
		this.synonymsNumber +=	synonymsNumber;	
	}


	@Override
	public void addExternalID(int externaIDNumber) {
		this.externalIDNumber += externaIDNumber;
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
