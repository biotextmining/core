package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public interface IResourcesElementLuceneService {
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByTerm(String term);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsBySynonym(String synonym);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByTerm(String term, Long resourceId) throws ResourcesExceptions;

}
