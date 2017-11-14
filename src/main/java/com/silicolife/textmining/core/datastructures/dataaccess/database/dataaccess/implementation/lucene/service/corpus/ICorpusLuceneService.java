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

}
