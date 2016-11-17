package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;

@Repository
public class ResourcesLuceneManagerDao {
	
	private IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao;

	@Autowired
	public ResourcesLuceneManagerDao(IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao){
		this.resourcesElememtsLuceneDao = resourcesElememtsLuceneDao;
	}

	public IGenericLuceneDao<ResourceElements> getResourcesElememtsLuceneDao() {
		return resourcesElememtsLuceneDao;
	}
	
}
