package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;

public interface QueriesAuxDao {

	public List<Queries> findQueriesByAttributes(Long id, String resourceType);

	public List<Queries> findQueriesByAttributes(Long id, String resourceType, String permission);

	public List<Map<Long, String>> findQueriesPublicationsRelevance(Long queryId);

	public List<Queries> findQueriesByAttributesPaginated(Long id, String resourceType, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public List<Queries> findQueriesByAttributesPaginated(Long id, String resourceType, String permission,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);
	
	public Integer countQueriesByAttributes(Long id, String resourceType);

	public Integer countActiveQueriesByAttributes(Long id, String resourceType);
	
	public Integer countActiveQueriesByAttributes(Long id, String resourceType, String permission);

	public List<Queries> findAllQueriesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countActiveQueries();





}
