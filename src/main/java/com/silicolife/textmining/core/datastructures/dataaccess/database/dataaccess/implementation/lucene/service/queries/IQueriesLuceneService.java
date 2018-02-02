package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.queries;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface IQueriesLuceneService {

	public List<IQuery> getQueriesByName(String name);

	public List<IQuery> getQueriesByOrganism(String organism);

	public List<IQuery> getQueriesBykeywords(String keywords);

	public List<IQuery> getQueriesKeywordsByWildCard(String subKeyword);

	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword);
	
	public void setUserLogged(UsersLogged userLogged);

	public List<IQuery> getQueriesBykeywordsPaginated(String keywords, int index, int paginationSize);

	public List<IQuery> getQueriesByOrganismPaginated(String organism, int index, int paginationSize);

	public Integer countQueriesByOrganism(String organism);

	public Integer countQueriesBykeywords(String keywords);

	public List<IQuery> getQueriesFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize);

	public Integer countQueriesFromSearch(ISearchProperties searchProperties);

	public List<IQuery> getQueriesFromSearchPaginatedWAuth(ISearchProperties searchProperties, int index, int paginationSize);

	public Integer countQueriesFromSearchWAuth(ISearchProperties searchProperties);

	public List<IQuery> getQueriesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy);

	public List<IQuery> getQueriesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy, String permission);

	public Integer countActiveQueriesFromSearch(ISearchProperties searchProperties);

	public List<IQuery> getQueriesFromSearchPaginatedWSort(ISearchProperties searchProperties, int index, int paginationSize,
			boolean asc, String sortBy);

	public Integer countQueriesFromSearchWAuth(ISearchProperties searchProperties, String permission);

	public List<IQuery> getPrivilegesQueriesAdminAccessFromSearchPaginated(ISearchProperties searchProperties,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countPrivilegesQueriesAdminAccessFromSearch(ISearchProperties searchProperties);
	
}
