package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.hibernate.SessionFactory;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.GenericLuceneDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;

public class LuceneManagerDao {
	
	private SessionFactory sessionFactory;
	private ResourcesLuceneManagerDao resourcesLuceneManagerDao;
	
	public LuceneManagerDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.resourcesLuceneManagerDao = createResourcesLuceneDao();
	}
	
	public ResourcesLuceneManagerDao createResourcesLuceneDao(){
		IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao = new GenericLuceneDaoImpl<ResourceElements>(sessionFactory, ResourceElements.class);
		IGenericLuceneDao<Synonyms> resourcesElememtsSynonymsLuceneDao = new GenericLuceneDaoImpl<Synonyms>(sessionFactory, Synonyms.class);
		ResourcesLuceneManagerDao resourcesLuceneManagerDao = new ResourcesLuceneManagerDao(resourcesElememtsLuceneDao, resourcesElememtsSynonymsLuceneDao);
		return resourcesLuceneManagerDao;
	}

	public ResourcesLuceneManagerDao getResourcesLuceneManagerDao() {
		return resourcesLuceneManagerDao;
	}

}
