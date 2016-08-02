package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class UnprocessedPublicationsPaginatorImpl implements ICorpusPublicationPaginator{
	
	private Long publicationsCount;
	private Integer paginationSize;
	private Integer paginationIndex;
	private IIEProcess process;

	public UnprocessedPublicationsPaginatorImpl(IIEProcess process, Integer paginationSize) throws ANoteException{
		this.paginationSize = paginationSize;
		this.paginationIndex = 0;
		this.process =process;
	}

	
	public UnprocessedPublicationsPaginatorImpl(IIEProcess process) throws ANoteException{
		this(process, 1000);
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
		IDocumentSet documentSet = getCorpusPublicationsNotProcessedPaginatedOnDatabase(getProcess(), getPaginationIndex(), getPaginationSize());
		paginationIndex = getPaginationIndex() + getPaginationSize();
		return documentSet;
	}

	@Override
	public Long getPublicationsCount() throws ANoteException {
		if(publicationsCount == null){
			publicationsCount = getUnprocessdCorpusPublicationCountOnDatabase(getProcess());
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

	private IIEProcess getProcess() {
		return process;
	}
	
	protected Long getUnprocessdCorpusPublicationCountOnDatabase(IIEProcess process) throws ANoteException {
		return InitConfiguration.getDataAccess().countCorpusPublicationsNotProcessed(process);
	}
	
	protected IDocumentSet getCorpusPublicationsNotProcessedPaginatedOnDatabase(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		return InitConfiguration.getDataAccess().getCorpusPublicationsNotProcessedPaginated(process, paginationIndex, paginationSize);
	}
}
