package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public interface IPublicationsLuceneService {

	public List<IPublication> getPublicationsByTitle(String title);

	public void setUserLogged(UsersLogged userLogged);

	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties);

	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index,
			int paginationSize);

	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties);

	public List<IPublication> getPublicationsFromSearchPaginatedWSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy);
	

}
