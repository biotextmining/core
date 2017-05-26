package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface ILuceneQueriesDataAccess {
	
	public List<IQuery> getQueriesByName(String name) throws ANoteException;
	
	public List<IQuery> getQueriesByOrganism(String organism) throws ANoteException;
	
	public List<IQuery> getQueriesBykeywords(String keywords) throws ANoteException;
	
	public  List<IQuery> getQueriesKeywordsByWildCard(String subKeyword) throws ANoteException;
	
	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword) throws ANoteException;

}
