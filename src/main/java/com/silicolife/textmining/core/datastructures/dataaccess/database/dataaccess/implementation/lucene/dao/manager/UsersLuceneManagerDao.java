package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

@Repository
public class UsersLuceneManagerDao {
	
	private IGenericLuceneDao<AuthUsers> usersLuceneDao;
	
	@Autowired 
	UsersLuceneManagerDao(IGenericLuceneDao<AuthUsers> usersLuceneDao){
		this.usersLuceneDao = usersLuceneDao;
	}

	public IGenericLuceneDao<AuthUsers> getUsersLuceneDao() {
		return usersLuceneDao;
	}
		

}
