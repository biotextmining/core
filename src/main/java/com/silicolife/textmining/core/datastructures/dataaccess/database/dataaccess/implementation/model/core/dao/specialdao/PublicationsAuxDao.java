package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;

public interface PublicationsAuxDao {

	public List<Publications> findPublicationsByQueryId(Long queryId);

	public List<Publications> findPublicationsByCorpusId(Long corpusId);
	
	public Long countPublicationsByCorpusId(Long corpusId);
	
	public List<Publications> findPublicationsByCorpusIdPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize);

	public Publications getPublicationFullText(Long publicationId);
	
	public List<Object[]> getPublicationBySource(Long sourceId);

	public List<Object> getQueryPublicationBySource(Long sourceId, Long queryId);

	public Long countPublicationsNotProcessedInProcess(Long corpusId, Long processId);
	
	public List<Publications> findPublicationsByCorpusIdAndProcessIdNotProcessedPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize);

	public List<Object> getCorpusPublicationBySource(Long sourceId, Long corpusId);

	public Long countCorpusPublicationsOutdatedProcess(Long corpusId, Long processId);

	public List<Publications> getCorpusPublicationsOutdatedProcessPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize);
	
	public Long countPublicationsByQueryId(Long queryId);

	public List<Publications> findPublicationsByQueryIdPaginated(Long queryId, Integer paginationIndex, Integer paginationSize,
			boolean asc, String sortBy);

	public List<Object[]> getPublicationIdBySourceTypeAndId(Long sourceId, String id);

	public Publications getPublicationBySourceTypeAndId(Long sourceId, String id);

	public List<Publications> findPublicationsByCorpusIdPaginatedWSort(Long corpusId, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public List<Publications> findAllPublicationsPaginated(Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy);

	public List<String> findAllDistinctColumnValuesFromPublicationsPaginated(String column, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public List<String> findAllDistinctColumnValuesFromPublications(String column);

	public Integer countAll();
}
