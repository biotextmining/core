package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * 
 * Interface to define all methods of Service layer about corpus
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface ICorpusService {
	/**
	 * Get all corpus
	 * 
	 * @return
	 */
	public List<ICorpus> getAllCorpus();
	
	/**
	 * Get Corpus by id
	 * 
	 * @param corpusId
	 * @return
	 */
	public ICorpus getCorpusByID(Long id);
	
	/**
	 * Create a new Corpus
	 * 
	 * @param corpus-
	 */
	public Boolean createCorpus(ICorpus corpus_);
	
	/**
	 * Update corpus
	 * 
	 * @param corpus_
	 * @return
	 * @throws CorpusException 
	 */
	public boolean updateCorpus(ICorpus corpus_) throws CorpusException;
	
	/**
	 * Get all publications from a corpus
	 * 
	 * @param corpusId
	 * @return
	 */
	public IDocumentSet getCorpusPublications(Long corpusId) throws CorpusException;
	
	/**
	 * Get all publications count from a corpus
	 * 
	 * @param corpusId
	 * @return
	 * @throws CorpusException
	 */
	public Long getCorpusPublicationsCount(Long corpusId) throws CorpusException;
	
	/**
	 * Get a page in database of publications from corpus 
	 * 
	 * @param corpusId
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws CorpusException
	 */
	public IDocumentSet getCorpusPublicationsPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize)throws CorpusException;
	
	/**
	 * Get processes from a corpus
	 * 
	 * @param corpusId
	 * @return
	 */
	public List<IIEProcess> getCorpusProcesses(Long corpusId) throws CorpusException;
	
	/**
	 * Register a corpus to a process
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 * @throws CorpusException 
	 */
	public Boolean registerCorpusProcess(Long corpusId, Long processId) throws CorpusException;

	
	/**
	 * Add Corpus Publication
	 * 
	 * @param corpusId
	 * @param publicationID
	 * @return
	 * @throws CorpusException
	 */
	public Boolean addCorpusPublication(Long corpusId, Long publicationID) throws CorpusException;

	/**
	 * Get Corpus statistics
	 * 
	 * @return
	 * @throws CorpusException 
	 */
	public ICorpusStatistics getCorpusStatistics(Long corpusId) throws CorpusException;

		/**
		 * Inativate Corpus
		 * 
		 * @param id
		 * @throws CorpusException 
		 */
	public Boolean inativateCorpus(Long corpusId) throws CorpusException;

	/**
	 * Get All Queries with admin permitions
	 * 
	 * @return
	 */
	public List<ICorpus> getAllPrivilegesCorpusAdminAccess();
	
	public void setUserLogged(UsersLogged userLogged);

	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize)throws CorpusException;
	
	public Set<String> getCorpusPublicationsExternalIDFromSource(Long corpusId, String source);

	public Long countCorpusPublicationsNotProcessed(Long corpusId, Long processId) throws CorpusException;

	public Set<ICorpus> getCorpusByPublicationId(Long publicationId) throws CorpusException;

	public Long countCorpusPublicationsOutdated(Long corpusId, Long processId) throws CorpusException;

	public IDocumentSet getCorpusPublicationsOutdatedPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize) throws CorpusException;

	public Integer countAllCorpus();

	public List<ICorpus> getAllCorpusPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public IDocumentSet getCorpusPublicationsPaginatedWSort(Long corpusId, Integer paginationIndex, Integer paginationSize,
			boolean asc, String sortBy) throws CorpusException;

	public List<IIEProcess> getCorpusProcessesPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize,
			boolean asc, String sortBy) throws CorpusException;

	public Integer countCorpusProcesses(Long corpusId) throws CorpusException;
}
