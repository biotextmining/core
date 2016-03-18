package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ResourcesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelationTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;

@Repository
public class ResourcesManagerDao {

	private GenericDao<Classes> classesDao;
	private GenericDao<ResourceTypes> resourceTypeDao;
	private GenericDao<Resources> resourceDao;
	private ResourcesAuxDao resourcesAuxDao;
	private GenericDao<ResourceElements> resourcesElememtsDao;
	private GenericDao<Synonyms> resourcesElememtsSynonymsDao;
	private GenericDao<ResourceElementRelations> resourcesElememtsRelationsDao;
	private GenericDao<ResourceElementRelationTypes> resourcesElememtsRelationTypeDao;
	private GenericDao<ResourceElementExtenalIds> resourcesElememtsExtenalIDsDao;
	private GenericDao<Sources> resourcesElememtsSourcesDao;

	@Autowired
	public ResourcesManagerDao(
			GenericDao<Classes> classesDao,
			GenericDao<ResourceTypes> resourceTypeDao,
			GenericDao<Resources> resourceDao,
			ResourcesAuxDao resourcesAuxDao,
			GenericDao<ResourceElements> resourcesElememtsDao,
			GenericDao<Synonyms> resourcesElememtsSynonymsDao,
			GenericDao<ResourceElementRelations> resourcesElememtsRelationsDao,
			GenericDao<ResourceElementRelationTypes> resourcesElememtsRelationTypeDao,
			GenericDao<ResourceElementExtenalIds> resourcesElememtsExtenalIDsDao,
			GenericDao<Sources> resourcesElememtsSourcesDao) {
		super();
		this.classesDao = classesDao;
		this.resourceTypeDao = resourceTypeDao;
		this.resourceDao = resourceDao;
		this.resourcesAuxDao = resourcesAuxDao;
		this.resourcesElememtsDao = resourcesElememtsDao;
		this.resourcesElememtsSynonymsDao = resourcesElememtsSynonymsDao;
		this.resourcesElememtsRelationsDao = resourcesElememtsRelationsDao;
		this.resourcesElememtsRelationTypeDao = resourcesElememtsRelationTypeDao;
		this.resourcesElememtsExtenalIDsDao = resourcesElememtsExtenalIDsDao;
		this.resourcesElememtsSourcesDao = resourcesElememtsSourcesDao;
	}

	public GenericDao<ResourceElements> getResourcesElememtsDao() {
		return resourcesElememtsDao;
	}

	public GenericDao<Synonyms> getResourcesElememtsSynonymsDao() {
		return resourcesElememtsSynonymsDao;
	}

	public GenericDao<ResourceElementRelations> getResourcesElememtsRelationsDao() {
		return resourcesElememtsRelationsDao;
	}

	public GenericDao<ResourceElementRelationTypes> getResourcesElememtsRelationTypeDao() {
		return resourcesElememtsRelationTypeDao;
	}

	public GenericDao<ResourceElementExtenalIds> getResourcesElememtsExtenalIDsDao() {
		return resourcesElememtsExtenalIDsDao;
	}

	public GenericDao<Sources> getResourcesElememtsSourcesDao() {
		return resourcesElememtsSourcesDao;
	}

	public GenericDao<Classes> getClassesDao() {
		return classesDao;
	}

	public GenericDao<ResourceTypes> getResourceTypeDao() {
		return resourceTypeDao;
	}

	public GenericDao<Resources> getResourceDao() {
		return resourceDao;
	}

	public ResourcesAuxDao getResourcesAuxDao() {
		return resourcesAuxDao;
	}

}
