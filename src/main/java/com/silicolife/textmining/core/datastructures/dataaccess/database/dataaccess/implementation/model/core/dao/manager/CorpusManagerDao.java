package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.CorpusAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;

@Repository
public class CorpusManagerDao extends PublicationsManagerDao {

	private GenericDao<Corpus> corpusDao;
	private GenericDao<CorpusProperties> corpusPropertiesDao;
	private GenericDao<CorpusHasProcesses> corpusHasProcessesDao;
	private GenericDao<CorpusHasPublications> corpusHasPublicationsDao;
	private CorpusAuxDao corpusAuxDao;

	@Autowired
	public CorpusManagerDao(GenericDao<PublicationSources> publicationSourcesDao, GenericDao<PublicationHasSources> publicationHasSourcesDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationFields> publicationFieldsDao, GenericDao<PublicationLabels> publicationLabelsDao,
			GenericDao<PublicationHasLabels> publicationHasLabelsDao, PublicationsAuxDao publicationsAuxDao, GenericDao<Corpus> corpusDao,
			GenericDao<CorpusProperties> corpusPropertiesDao, GenericDao<CorpusHasProcesses> corpusHasProcessesDao, GenericDao<CorpusHasPublications> corpusHasPublicationsDao,
			CorpusAuxDao corpusAuxDao) {
		super(publicationSourcesDao, publicationHasSourcesDao, publicationsDao, publicationFieldsDao, publicationLabelsDao, publicationHasLabelsDao, publicationsAuxDao);
		this.corpusDao = corpusDao;
		this.corpusPropertiesDao = corpusPropertiesDao;
		this.corpusHasProcessesDao = corpusHasProcessesDao;
		this.corpusHasPublicationsDao = corpusHasPublicationsDao;
		this.corpusAuxDao = corpusAuxDao;
	}

	public GenericDao<Corpus> getCorpusDao() {
		return corpusDao;
	}

	public GenericDao<CorpusProperties> getCorpusPropertiesDao() {
		return corpusPropertiesDao;
	}

	public GenericDao<CorpusHasProcesses> getCorpusHasProcessesDao() {
		return corpusHasProcessesDao;
	}

	public GenericDao<CorpusHasPublications> getCorpusHasPublicationsDao() {
		return corpusHasPublicationsDao;
	}

	public CorpusAuxDao getCorpusAuxDao() {
		return corpusAuxDao;
	}
}
