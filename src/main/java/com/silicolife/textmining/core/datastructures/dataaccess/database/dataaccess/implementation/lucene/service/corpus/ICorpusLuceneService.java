package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.corpus;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public interface ICorpusLuceneService {

	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize);

	public Integer countCorpusFromSearch(ISearchProperties searchProperties);

	void setUserLogged(UsersLogged userLogged);

	public List<ICorpus> getCorpusFromSearchWPrivileges(ISearchProperties searchProperties);

	public List<ICorpus> getCorpusFromSearchWPrivilegesPaginated(ISearchProperties searchProperties, String permission,
			int index, int paginationSize, boolean asc, String sortBy);

	public Integer countCorpusFromSearchWPrivileges(ISearchProperties searchProperties, String permission);

	public Integer countActiveCorpusFromSearch(ISearchProperties searchProperties);

	public List<ICorpus> getActiveCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize,
			boolean asc, String sortBy);

	public Integer countPrivilegesCorpusAdminAccessFromSearch(ISearchProperties searchProperties);

	public List<ICorpus> getPrivilegesCorpusAdminAccessFromSearchPaginated(ISearchProperties searchProperties,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

}
