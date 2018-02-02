package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.users;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

public interface IUsersLuceneService {

	public void setUserLogged(UsersLogged userLogged);

	public List<IGenericPair<IUser, String>> getUsersAndPermissionsFromSearchPaginated(ISearchProperties searchProperties,
			Long resourceId, String resource, int index, int paginationSize, boolean asc, String sortBy);

	public Integer countUsersFromSearch(ISearchProperties searchProperties);

}
