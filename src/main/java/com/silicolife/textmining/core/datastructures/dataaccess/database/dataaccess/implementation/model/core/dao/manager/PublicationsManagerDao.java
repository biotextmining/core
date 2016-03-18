package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;

/**
 * Class to handler with Publications object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class PublicationsManagerDao {

	private GenericDao<PublicationSources> publicationSourcesDao;
	private GenericDao<PublicationHasSources> publicationHasSourcesDao;
	private GenericDao<Publications> publicationsDao;
	private GenericDao<PublicationFields> publicationFieldsDao;
	private GenericDao<PublicationLabels> publicationLabelsDao;
	private GenericDao<PublicationHasLabels> publicationHasLabelsDao;
	private PublicationsAuxDao publicationsAuxDao;

	@Autowired
	public PublicationsManagerDao(GenericDao<PublicationSources> publicationSourcesDao, GenericDao<PublicationHasSources> publicationHasSourcesDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationFields> publicationFieldsDao, GenericDao<PublicationLabels> publicationLabelsDao,
			GenericDao<PublicationHasLabels> publicationHasLabelsDao, PublicationsAuxDao publicationsAuxDao) {
		this.publicationSourcesDao = publicationSourcesDao;
		this.publicationHasSourcesDao = publicationHasSourcesDao;
		this.publicationsDao = publicationsDao;
		this.publicationFieldsDao = publicationFieldsDao;
		this.publicationsAuxDao = publicationsAuxDao;
		this.publicationLabelsDao = publicationLabelsDao;
		this.publicationHasLabelsDao = publicationHasLabelsDao;
	}

	public GenericDao<PublicationSources> getPublicationSourcesDao() {
		return publicationSourcesDao;
	}

	public GenericDao<PublicationHasSources> getPublicationHasSourcesDao() {
		return publicationHasSourcesDao;
	}

	public GenericDao<Publications> getPublicationsDao() {
		return publicationsDao;
	}

	public GenericDao<PublicationFields> getPublicationFieldsDao() {
		return publicationFieldsDao;
	}

	public GenericDao<PublicationLabels> getPublicationLabelsDao() {
		return publicationLabelsDao;
	}

	public GenericDao<PublicationHasLabels> getPublicationHasLabelsDao() {
		return publicationHasLabelsDao;
	}

	public PublicationsAuxDao getPublicationsAuxDao() {
		return publicationsAuxDao;
	}

}
