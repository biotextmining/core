package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;


@Repository
public class PublicationsLuceneManagerDao {
	private IGenericLuceneDao<Publications> publicationsLuceneDao;
	
	@Autowired
	PublicationsLuceneManagerDao(IGenericLuceneDao<Publications> publicationsLuceneDao){
		this.publicationsLuceneDao = publicationsLuceneDao;
	}
	
	public IGenericLuceneDao<Publications> getPublicationsLuceneDao(){
		return publicationsLuceneDao;
	}
}
