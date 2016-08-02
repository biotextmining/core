package com.silicolife.textmining.core.interfaces.core.document;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public interface ICorpusPublicationPaginator {

	public boolean hasNextDocumentSetPage() throws ANoteException;

	public IDocumentSet nextDocumentSetPage()  throws ANoteException;

	public Long getPublicationsCount() throws ANoteException;

	public Integer getPaginationSize();

	public Integer getPaginationIndex();

}
