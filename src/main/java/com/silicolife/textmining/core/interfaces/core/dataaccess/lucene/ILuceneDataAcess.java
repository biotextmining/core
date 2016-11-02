package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer.ILuceneResourceElementDataAccess;

public interface ILuceneDataAcess extends ILuceneResourceElementDataAccess{

	public boolean rebuildLuceneIndex() throws ANoteException;
	
}
