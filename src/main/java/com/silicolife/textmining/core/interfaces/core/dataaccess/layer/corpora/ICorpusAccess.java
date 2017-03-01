package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora.ieprocess.IIEProcessAccess;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface ICorpusAccess extends IIEProcessAccess, IAnnotationAccess, IAnnotationsLogAccess,ICorpusPrivilegesAccess {

	/**
	 * Create a new corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void createCorpus(ICorpus corpus) throws ANoteException;

	/**
	 * Update Corpus information (like name)
	 * 
	 * @param corpus
	 * @return
	 */
	public void updateCorpus(ICorpus corpus) throws ANoteException;

	/**
	 * Remove corpus
	 * 
	 * @param corpus
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inactivateCorpus(ICorpus corpus) throws ANoteException;

	/**
	 * Find all corpus
	 * 
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<ICorpus> getAllCorpus() throws ANoteException;

	/**
	 * Get Corpus information By ID;
	 * 
	 * @param corpusID
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public ICorpus getCorpusByID(long corpusID) throws ANoteException;

	/**
	 * Register process in a Corpus
	 * 
	 * @param corpus
	 * @param process
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void registerCorpusProcess(ICorpus corpus, IIEProcess process) throws ANoteException;

	/**
	 * Get IEProcess associated to Corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IIEProcess> getCorpusProcesses(ICorpus corpus) throws ANoteException;

	/**
	 * Get DocumentSet present in a Corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IDocumentSet getCorpusPublications(ICorpus corpus) throws ANoteException;
	
	/**
	 * Get number of IPublications present in a Corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws ANoteException
	 */
	public Long getCorpusPublicationsCount(ICorpus corpus) throws ANoteException;
	
	/**
	 * Get DocumentSet present in a range of documents from a Corpus
	 * 
	 * @param corpus
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IDocumentSet getCorpusPublicationsPaginated(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException;
	
	/**
	 * Add Corpus Publication - If publication not exists will be create
	 * 
	 * @param corpus
	 * @param publication
	 * @throws ANoteException
	 */
	public void addCorpusPublication(ICorpus corpus,IPublication publication) throws ANoteException;
	
	/**
	 * Get Corpus Statistics
	 * 
	 * @param corpus
	 * @return
	 * @throws ANoteException 
	 */
	public ICorpusStatistics getCorpusStatistics(ICorpus corpus) throws ANoteException;
	
	
	/**
	 * 
	 * Count all unprocessed documents
	 * 
	 * @param process
	 * @return
	 */
	public Long countCorpusPublicationsNotProcessed(IIEProcess process) throws ANoteException;
	
	/**
	 * 
	 * Get all unprocessed documents 
	 * 
	 * @param corpus
	 * @param process
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException;
	
	/**
	 * 
	 * Count all outdated documents
	 * 
	 * @param process
	 * @return
	 * @throws ANoteException
	 */
	public Long countCorpusPublicationsOutdated(IIEProcess process) throws ANoteException;
	
	/**
	 * 
	 * Get all outdated documents from the process
	 * 
	 * @param process
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IDocumentSet getCorpusPublicationsOutdatedPaginated(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException;
	
	/**
	 * 
	 * Get all external ids with a specific source from corpus publications
	 * 
	 * @param corpus
	 * @param source
	 * @return
	 * @throws ANoteException
	 */
	public Set<String> getCorpusPublicationsExternalIDFromSource(ICorpus corpus, String source) throws ANoteException;
	
	public Set<ICorpus> getCorpusByPublication(IPublication publication) throws ANoteException;
}
