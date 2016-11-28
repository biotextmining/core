package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;

public interface ILuceneService {
	
	public boolean rebuildLuceneIndex();
	
	public void setUserLogged(UsersLogged userLogged);

}
