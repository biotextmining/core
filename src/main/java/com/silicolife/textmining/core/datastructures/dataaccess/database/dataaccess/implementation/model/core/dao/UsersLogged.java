package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

public interface UsersLogged {

	public AuthUsers getCurrentUserLogged();

	public void setCurrentUserLogged(AuthUsers currentUserLogged);
}
