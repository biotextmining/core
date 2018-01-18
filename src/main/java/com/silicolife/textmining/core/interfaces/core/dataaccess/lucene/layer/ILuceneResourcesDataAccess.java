package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public interface ILuceneResourcesDataAccess {
	
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties) throws ANoteException;

}
