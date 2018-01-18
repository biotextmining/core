package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.hibernate.SessionFactory;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.GenericLuceneDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;

public class LuceneManagerDao {
	
	private SessionFactory sessionFactory;
	private ResourceElementsLuceneManagerDao resourceElementsLuceneManagerDao;
	private QueriesLuceneManagerDao queriesLuceneManagerDao;
	private PublicationsLuceneManagerDao publicationsLuceneManagerDao;
	private CorpusLuceneManagerDao corpusLuceneManagerDao;
	private ResourcesLuceneManagerDao resourcesLuceneManagerDao;
	
	public LuceneManagerDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.resourceElementsLuceneManagerDao = createResourceElementsLuceneDao();
		this.queriesLuceneManagerDao = createQueriesLuceneDao();
		this.publicationsLuceneManagerDao = createPublicationsLuceneDao();
		this.corpusLuceneManagerDao = createCorpusLuceneDao();
		this.resourcesLuceneManagerDao = createResourcesLuceneDao();
	}
	
	public ResourceElementsLuceneManagerDao createResourceElementsLuceneDao(){
		IGenericLuceneDao<ResourceElements> resourcesElememtsLuceneDao = new GenericLuceneDaoImpl<ResourceElements>(sessionFactory, ResourceElements.class);
		ResourceElementsLuceneManagerDao resourcesLuceneManagerDao = new ResourceElementsLuceneManagerDao(resourcesElememtsLuceneDao);
		return resourcesLuceneManagerDao;
	}
	
	public ResourcesLuceneManagerDao createResourcesLuceneDao(){
		IGenericLuceneDao<Resources> resourcesLuceneDao = new GenericLuceneDaoImpl<Resources>(sessionFactory, Resources.class);
		ResourcesLuceneManagerDao resourcesLuceneManagerDao = new ResourcesLuceneManagerDao(resourcesLuceneDao);
		return resourcesLuceneManagerDao;
	}
	
	
	public QueriesLuceneManagerDao createQueriesLuceneDao(){
		IGenericLuceneDao<Queries> queriesLuceneDao = new GenericLuceneDaoImpl<Queries>(sessionFactory, Queries.class);
		QueriesLuceneManagerDao queriesLuceneManagerDao = new QueriesLuceneManagerDao(queriesLuceneDao);
		return queriesLuceneManagerDao;
	}
	
	public PublicationsLuceneManagerDao createPublicationsLuceneDao(){
		IGenericLuceneDao<Publications> publicationsLuceneDao = new GenericLuceneDaoImpl<Publications>(sessionFactory, Publications.class);
		PublicationsLuceneManagerDao publicationsLuceneManagerDao = new PublicationsLuceneManagerDao(publicationsLuceneDao);
		return publicationsLuceneManagerDao;
	}
	
	public CorpusLuceneManagerDao createCorpusLuceneDao(){
		IGenericLuceneDao<Corpus> corpusLuceneDao = new GenericLuceneDaoImpl<Corpus>(sessionFactory, Corpus.class);
		CorpusLuceneManagerDao corpusLuceneManagerDao = new CorpusLuceneManagerDao(corpusLuceneDao);
		return corpusLuceneManagerDao;
	}
	
	
	public ResourceElementsLuceneManagerDao getResourceElementsLuceneManagerDao() {
		return resourceElementsLuceneManagerDao;
	}
	
	public ResourcesLuceneManagerDao getResourcesLuceneManagerDao() {
		return resourcesLuceneManagerDao;
	}
	
	public QueriesLuceneManagerDao getQueriesLuceneManagerDao(){
		return queriesLuceneManagerDao;
	}
	
	public PublicationsLuceneManagerDao getPublicationsLuceneManagerDao(){
		return publicationsLuceneManagerDao;
	}
	
	public CorpusLuceneManagerDao getCorpusLuceneManagerDao() {
		return corpusLuceneManagerDao;
	}

}
