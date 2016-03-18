package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;

public interface QueriesAuxDao {

	public List<Queries> findQueriesByAttributes(Long id, String resourceType);

	public List<Queries> findQueriesByAttributes(Long id, String resourceType, String permission);

	public List<Map<Long, String>> findQueriesPublicationsRelevance(Long queryId);

}
