package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public class CorpusPublicationPaginatorImpl implements ICorpusPublicationPaginator{
	
	private Long publicationsCount;
	private Integer paginationSize;
	private Integer paginationIndex;
	private ICorpus corpus;

	public CorpusPublicationPaginatorImpl(ICorpus corpus, Integer paginationSize) throws ANoteException{
		this.paginationSize = paginationSize;
		this.paginationIndex = 0;
		this.corpus =corpus;
	}

	
	public CorpusPublicationPaginatorImpl(ICorpus corpus) throws ANoteException{
		this(corpus, 1000);
	}

	@Override
	public boolean hasNextDocumentSetPage() throws ANoteException {
		if(getPaginationIndex()<getPublicationsCount()){
			return true;
		}
		return false;
	}

	@Override
	public IDocumentSet nextDocumentSetPage() throws ANoteException{
		IDocumentSet documentSet = getCorpusPublicationsPaginatedOnDatabase(getCorpus(), getPaginationIndex(), getPaginationSize());
		paginationIndex = getPaginationIndex() + getPaginationSize();
		return documentSet;
	}



	@Override
	public Long getPublicationsCount() throws ANoteException {
		if(publicationsCount == null){
			publicationsCount = getCorpusPublicationCountOnDatabase(getCorpus());
		}
		return publicationsCount;
	}

	@Override
	public Integer getPaginationSize() {
		return paginationSize;
	}

	@Override
	public Integer getPaginationIndex() {
		return paginationIndex;
	}

	@Override
	public ICorpus getCorpus() {
		return corpus;
	}
	
	protected Long getCorpusPublicationCountOnDatabase(ICorpus corpus) throws ANoteException {
		return InitConfiguration.getDataAccess().getCorpusPublicationsCount(corpus);
	}
	
	protected IDocumentSet getCorpusPublicationsPaginatedOnDatabase(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		return InitConfiguration.getDataAccess().getCorpusPublicationsPaginated(corpus, paginationIndex, paginationSize);
	}
}
