package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;

public interface IResourceManagerReport {

	public void addTerms(int termNumbers);

	public void addSynonyms(int synonymsNumber);

	public void addconflit(IResourceUpdateConflits conflit);

	public void addExternalID(int externaIDNumber);

	public int getExternalIDAdded();

	public int getSynonymsAdded();

	public int getTermsAdded();

	public int getConflitNumber();
	
	public List<IResourceUpdateConflits> getConflits();

	public boolean hasConflits();

}
