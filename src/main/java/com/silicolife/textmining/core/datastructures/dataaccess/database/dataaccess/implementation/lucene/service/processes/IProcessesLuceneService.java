package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.processes;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IProcessesLuceneService {

	public void setUserLogged(UsersLogged userLogged);

	public List<IIEProcess> getProcessesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy);


	public Integer countProcessesFromSearchWAuth(ISearchProperties searchProperties);

	public List<IIEProcess> getProcessesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, String permission,
			int index, int paginationSize, boolean asc, String sortBy);

	public Integer countProcessesFromSearchWAuth(ISearchProperties searchProperties, String permission);

	public List<IIEProcess> getActiveProcessesFromSearchPaginatedWSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy);

	public Integer countActiveProcessesFromSearch(ISearchProperties searchProperties);

	public List<IIEProcess> getPrivilegesProcessesAdminAccessFromSearchPaginated(ISearchProperties searchProperties,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countPrivilegesProcessesAdminAccessFromSearch(ISearchProperties searchProperties);

}
