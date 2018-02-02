package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

public interface ProcessesAuxDao {

	List<Processes> findProcessesByAttributes(long auId, String processstr, String string);
	
	List<Processes> findProcessesByPublicationIds(Long publicationId);

	List<Processes> findAllProcessesPaginated(long auId, String resourceType, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	List<Processes> findAllProcesses(long auId, String resourceType);

	public Integer countAllActiveProcesses();

	public Integer countProcessesByAttributes(long auId, String resourceType, String permission);

	public List<Processes> findAllActiveProcessesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy);

	public List<Processes> findProcessesByAttributesPaginated(long auId, String resourceType, String permission,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

}
