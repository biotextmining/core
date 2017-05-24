package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public interface ILucenePublicationsDataAccess {

	public List<IPublication> getPublicationsByTitle(String title) throws ANoteException;

	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException;
	
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ) throws ANoteException;
	
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException;
}
