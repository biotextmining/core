package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public interface ILuceneResourceElementDataAccess {
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByTermUsingLucene(String term) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsBySynonymUsingLucene(String synonym) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByTermFromResourceUsingLucene(String term, IResource<IResourceElement> resource) throws ANoteException;

}
