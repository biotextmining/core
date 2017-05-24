package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;

@Repository
public class QueriesLuceneManagerDao {
	
	private IGenericLuceneDao<Queries> queriesLuceneDao;
	
	@Autowired
	public QueriesLuceneManagerDao(IGenericLuceneDao<Queries> queriesLuceneDao){
		this.queriesLuceneDao = queriesLuceneDao;
	}

	public IGenericLuceneDao<Queries> getQueriesLuceneDao() {
		return queriesLuceneDao;
	}

}
