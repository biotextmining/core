package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class OutdatedAnnotatedPublicationsStackPaginatorImpl implements ICorpusPublicationPaginator{

	private Integer paginationSize;
	private IIEProcess process;
	private ICorpusPublicationPaginator resumePaginator;
	
	public OutdatedAnnotatedPublicationsStackPaginatorImpl(IIEProcess process, Integer paginationSize){
		this.paginationSize = paginationSize;
		this.process = process;
		this.resumePaginator = new UnprocessedPublicationsStackPaginatorImpl(process, paginationSize);
	}
	
	public OutdatedAnnotatedPublicationsStackPaginatorImpl(IIEProcess process){
		this(process, 1000);
	}
	
	@Override
	public boolean hasNextDocumentSetPage() throws ANoteException {
		if(getPublicationsCount()>0){
			return true;
		}
		return false;
	}

	@Override
	public IDocumentSet nextDocumentSetPage() throws ANoteException {
		IDocumentSet documentSet = getCorpusPublicationsOutdatedPaginatedOnDatabase(getProcess(), getPaginationIndex(), getPaginationSize());
		return documentSet;
	}

	@Override
	public Long getPublicationsCount() throws ANoteException {
		return getResumePaginator().getPublicationsCount() + getOutdatedCorpusPublicationCountOnDatabase(getProcess());
	}

	@Override
	public Integer getPaginationSize() {
		return paginationSize;
	}

	@Override
	public Integer getPaginationIndex() {
		return 0;
	}
	
	private IIEProcess getProcess() {
		return process;
	}
	
	private ICorpusPublicationPaginator getResumePaginator(){
		return resumePaginator;
	}

	protected Long getOutdatedCorpusPublicationCountOnDatabase(IIEProcess process) throws ANoteException {
		return InitConfiguration.getDataAccess().countCorpusPublicationsOutdated(process);
	}
	
	protected IDocumentSet getCorpusPublicationsOutdatedPaginatedOnDatabase(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		if(getResumePaginator().hasNextDocumentSetPage()){
			return getResumePaginator().nextDocumentSetPage();
		}
		return InitConfiguration.getDataAccess().getCorpusPublicationsOutdatedPaginated(process, paginationIndex, paginationSize);
	}
}
