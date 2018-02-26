package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.Date;
import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

public interface DataProcessStatusAuxDao {

	public List<DataProcessStatus> findDataProcessStatusForUser(Long userId);

	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRange(Long userId, Date from, Date to);

	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSort(Long userId, Date from, Date to,
			boolean asc, String sortBy);

	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortPaginated(Long userId, Date from, Date to,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public List<DataProcessStatus> findDataProcessStatusForUserWithSortPaginated(Long userId, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public List<DataProcessStatus> findDataProcessStatusWithSortPaginated(Integer paginationIndex, Integer paginationSize,
			boolean asc, String sortBy);

	public Integer countDataProcessStatus();

	public Integer countDataProcessStatusforUser(Long userId);

	public List<DataProcessStatus> findDataProcessStatusWithSortPaginated(ProcessStatusResourceTypesEnum type,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public List<DataProcessStatus> findDataProcessStatusForUserWithSortPaginated(ProcessStatusResourceTypesEnum type,
			Long userId, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countDataProcessStatus(ProcessStatusResourceTypesEnum type);

	public Integer countDataProcessStatusforUser(Long userId, ProcessStatusResourceTypesEnum type);

	public Integer countRunningOrStartDataProcessStatusforUser(Long userId);

	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortByTypePaginated(
			ProcessStatusResourceTypesEnum type, Long userId, Date from, Date to, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public List<DataProcessStatus> findDataProcessStatusForUserWithDateRangeAndSortByType(ProcessStatusResourceTypesEnum type,
			Long userId, Date from, Date to, boolean asc, String sortBy);

}
