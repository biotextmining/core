package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer.ILuceneCorpusDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer.ILucenePublicationsDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer.ILuceneQueriesDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer.ILuceneResourceElementDataAccess;

public interface ILuceneDataAcess extends ILuceneResourceElementDataAccess, ILuceneQueriesDataAccess, ILucenePublicationsDataAccess, ILuceneCorpusDataAccess{

	/**
	 * Rebuild lucene index. 
	 * The rebuild could be made in database already filled with data and when index clauses changed
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public boolean rebuildLuceneIndex() throws ANoteException;
	
}
