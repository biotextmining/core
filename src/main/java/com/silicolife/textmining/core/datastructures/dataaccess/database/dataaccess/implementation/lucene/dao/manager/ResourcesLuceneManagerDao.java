package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;

@Repository
public class ResourcesLuceneManagerDao {
	
	private IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao;

	@Autowired
	public ResourcesLuceneManagerDao(IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao,
			IGenericLuceneDao<Synonyms> resourcesElememtsSynonymsLuceneDao){
		this.resourcesElememtsLuceneDao = resourcesElememtsLuceneDao;
	}

	public IGenericLuceneDao<ResourceElements> getResourcesElememtsLuceneDao() {
		return resourcesElememtsLuceneDao;
	}
	
}
