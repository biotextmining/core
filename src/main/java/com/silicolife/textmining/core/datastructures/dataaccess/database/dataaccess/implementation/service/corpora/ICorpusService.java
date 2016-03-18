package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
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
	
	
}
