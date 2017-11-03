package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public interface ILuceneCorpusDataAccess {

	public Integer countGetCorpusFrom(ISearchProperties searchProperties) throws ANoteException;

	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize)
			throws ANoteException;

}
