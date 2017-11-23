package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;

@Repository
public class ResourcesLuceneManagerDao {
	private IGenericLuceneDao<Resources> resourcesLuceneDao;

	@Autowired
	public ResourcesLuceneManagerDao(IGenericLuceneDao<Resources> resourcesLuceneDao){
		this.resourcesLuceneDao = resourcesLuceneDao;
	}

	public IGenericLuceneDao<Resources> getResourcesLuceneDao() {
		return resourcesLuceneDao;
	}
}
