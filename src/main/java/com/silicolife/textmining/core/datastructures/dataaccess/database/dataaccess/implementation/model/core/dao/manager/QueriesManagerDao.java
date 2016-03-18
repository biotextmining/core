package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.QueriesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryTypes;

/**
 * Class to handler with Queries object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class QueriesManagerDao extends PublicationsManagerDao {

	private GenericDao<Queries> queriesDao;
	private GenericDao<QueryTypes> queryTypesDao;
	private GenericDao<QueryHasPublications> queryHasPublicationsDao;
	private GenericDao<QueryHasClusterProcesses> queryHasClusterProcessesDao;
	private GenericDao<QueryProperties> queryPropertiesDao;
	private QueriesAuxDao queriesAuxDao;

	@Autowired
	public QueriesManagerDao(GenericDao<PublicationSources> publicationSourcesDao, GenericDao<PublicationHasSources> publicationHasSourcesDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationFields> publicationFieldsDao, GenericDao<PublicationLabels> publicationLabelsDao,
			GenericDao<PublicationHasLabels> publicationHasLabelsDao, PublicationsAuxDao publicationsAuxDao, GenericDao<Queries> queriesDao, GenericDao<QueryTypes> queryTypesDao,
			GenericDao<QueryHasPublications> queryHasPublicationsDao, GenericDao<QueryHasClusterProcesses> queryHasClusterProcessesDao, QueriesAuxDao queriesAuxDao,
			GenericDao<QueryProperties> queryPropertiesDao) {
		super(publicationSourcesDao, publicationHasSourcesDao, publicationsDao, publicationFieldsDao, publicationLabelsDao, publicationHasLabelsDao, publicationsAuxDao);
		this.queriesDao = queriesDao;
		this.queryTypesDao = queryTypesDao;
		this.queryHasPublicationsDao = queryHasPublicationsDao;
		this.queryHasClusterProcessesDao = queryHasClusterProcessesDao;
		this.queriesAuxDao = queriesAuxDao;
		this.queryPropertiesDao = queryPropertiesDao;
	}

	public GenericDao<Queries> getQueriesDao() {
		return queriesDao;
	}

	public GenericDao<QueryTypes> getQueryTypes() {
		return queryTypesDao;
	}

	public GenericDao<QueryHasPublications> getQueryHasPublicationsDao() {
		return queryHasPublicationsDao;
	}

	public GenericDao<QueryHasClusterProcesses> getQueryHasClusterProcessesDao() {
		return queryHasClusterProcessesDao;
	}

	public QueriesAuxDao getQueriesAuxDao() {
		return queriesAuxDao;
	}

	public GenericDao<QueryProperties> getQueryProperties() {
		return queryPropertiesDao;
	}
}
