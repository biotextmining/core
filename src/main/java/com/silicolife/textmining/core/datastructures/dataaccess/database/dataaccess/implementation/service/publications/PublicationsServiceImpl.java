package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.PublicationsManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.queryGrammar.ParseException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.queryGrammar.queryGrammar;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsFieldsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsLabelsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsSourceWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.documents.PublicationFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

/**
 * Service layer which implements all operations about publications
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 * 
 */
@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements IPublicationsService {

	private PublicationsManagerDao publicationsManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public PublicationsServiceImpl(PublicationsManagerDao publicationsManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.publicationsManagerDao = publicationsManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	public IPublication getById(Long id) {
		Publications publication = publicationsManagerDao.getPublicationsDao().findById(id);
		if (publication == null)
			return null;

		IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
		return publication_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(IPublication publication_) {
		Publications publication = publicationsManagerDao.getPublicationsDao().findById(publication_.getId());
		publication.setPubExternalLinks(publication_.getExternalLink());
		publication.setPubNotes(publication_.getNotes());
		publication.setPubRelativePath(publication_.getRelativePath());
		publicationsManagerDao.getPublicationsDao().update(publication);

		List<IPublicationLabel> pubLabels_ = publication_.getPublicationLabels();
		Set<PublicationHasLabels> publicationsLabels = publication.getPublicationHasLabelses();

		for (PublicationHasLabels pubLabel : publicationsLabels) {
			IPublicationLabel pubLabel_ = PublicationsLabelsWrapper.convertToAnoteStructure(pubLabel.getPublicationLabels());
			if (!pubLabels_.contains(pubLabel_))
				publicationsManagerDao.getPublicationHasLabelsDao().delete(pubLabel);
		}

		if (pubLabels_ != null) {
			for (IPublicationLabel label_ : pubLabels_) {
				PublicationHasLabels pubHasPubLabel = PublicationsLabelsWrapper.convertToDaemonStructure(label_, publication);
				createLabels(pubHasPubLabel);
			}
		}

		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "publications", null, "update publication");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(Set<IPublication> publications_) {
		for (IPublication publication_ : publications_) {
			/*
			 * save publication
			 */
			Publications publication = PublicationsWrapper.convertToDaemonStructure(publication_);
			publicationsManagerDao.getPublicationsDao().save(publication);
			/*
			 * save publications associations
			 */
			List<IPublicationExternalSourceLink> pubSources_ = publication_.getPublicationExternalIDSource();
			List<IPublicationField> pubFiels_ = publication_.getPublicationFields();
			List<IPublicationLabel> pubLabels_ = publication_.getPublicationLabels();
			if (pubSources_ != null) {
				for (IPublicationExternalSourceLink source_ : pubSources_) {
					PublicationHasSources pubHasPubSource = PublicationsSourceWrapper.convertToDaemonStructure(source_, publication);
					createSource(pubHasPubSource);
				}
			}
			if (pubFiels_ != null) {
				for (IPublicationField field_ : pubFiels_) {
					PublicationFields pubFields = PublicationsFieldsWrapper.convertToDaemonStructure(field_, publication);
					createFields(pubFields);
				}
			}
			if (pubLabels_ != null) {
				for (IPublicationLabel label_ : pubLabels_) {
					PublicationHasLabels pubHasPubLabel = PublicationsLabelsWrapper.convertToDaemonStructure(label_, publication);
					createLabels(pubHasPubLabel);
				}
			}
		}

		if (publications_.size() > 0) {
			/*
			 * Log
			 */
			AuthUsers user = userLogged.getCurrentUserLogged();
			AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "publications/publication_labels/publication_sources/publication_fields", null, "create publication");
			usersManagerDao.getAuthUserLogsDao().save(log);
		}
		return true;
	}

	@Override
	public Map<String, Long> getAllPublicationsIdFromSource(String source) {
		PublicationSources publicationSource = publicationsManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", source);
		Map<String, Long> response = new HashMap<String, Long>();

		if (publicationSource == null) {
			return response;
		}

		List<Object[]> objects = publicationsManagerDao.getPublicationsAuxDao().getPublicationBySource(publicationSource.getPssId());
		for (Object[] object : objects) {
			String pubSourceId = (String) object[1];
			Long pubId = (Long) object[0];
			response.put(pubSourceId, pubId);
		}

		return response;
	}
	
	@Override
	public List<IPublication> getAllPublicationsPaginated(int paginationIndex, int paginationSize,boolean asc, String sortBy) {
		List<Publications> listPublications = publicationsManagerDao.getPublicationsAuxDao().findAllPublicationsPaginated(paginationIndex, paginationSize, asc, sortBy);
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	
	//Only works if the column type is String
	@Override
	public List<String> findAllDistinctColumnValuesFromPublicationsPaginated(String column, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		return publicationsManagerDao.getPublicationsAuxDao().findAllDistinctColumnValuesFromPublicationsPaginated(PublicationFieldsEnum.valueOf(column).getUniqueIdentifier(), paginationIndex, paginationSize, asc, sortBy);
	}
	
	@Override
	public Integer countAllDistinctColumnValuesFromPublications(String column) {
		return publicationsManagerDao.getPublicationsAuxDao().findAllDistinctColumnValuesFromPublications(PublicationFieldsEnum.valueOf(column).getUniqueIdentifier()).size();
	}
	
	
	@Override
	public Integer countAllPublications() {
		return publicationsManagerDao.getPublicationsDao().findAll().size();
	}
	
	@Override
	public Long getPublicationIdFromSourceId(String source, String sourceId) {
		
		PublicationSources publicationSource = publicationsManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", source);

		if (publicationSource == null) {
			return null;
		}
		
		 publicationsManagerDao.getPublicationsAuxDao().getPublicationBySourceTypeAndId(publicationSource.getPssId(), sourceId);
		
		List<Object[]> objects = publicationsManagerDao.getPublicationsAuxDao().getPublicationIdBySourceTypeAndId(publicationSource.getPssId(), sourceId);
		
		if(objects==null)
			return null;
		else return (Long)objects.get(0)[0];
		
	}
	
	@Override
	public IPublication getPublicationFromSourceId(String source, String sourceId) {
		
		PublicationSources publicationSource = publicationsManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", source);

		if (publicationSource == null) {
			return null;
		}
		
		 Publications publication = publicationsManagerDao.getPublicationsAuxDao().getPublicationBySourceTypeAndId(publicationSource.getPssId(), sourceId);
		
		 IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			return publication_;
		
	}
	
	@Override
	public List<IPublication> getPublicationsFromResourcesQuery(String query, IAnnotationService annotationService){
		Set<Long > result = null;
		List<IPublication> publications = new ArrayList<IPublication>();
		try {
			InputStream is = new ByteArrayInputStream( query.getBytes() );
		    queryGrammar parser = new queryGrammar(is);
		    
			try {
				result = parser.parseGrammar(query, annotationService);
				for(Long id : result) {
				Publications publication = publicationsManagerDao.getPublicationsDao().findById(id);
				if (publication == null)
					return null;

				IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
				publications.add(publication_);
				}
			} catch (AnnotationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publications;
	}
	
	

	@Override
	public List<IPublicationLabel> getAllPublicationLabels() {
		List<PublicationLabels> allLabels = publicationsManagerDao.getPublicationLabelsDao().findAll();
		List<IPublicationLabel> pubLabels_ = new ArrayList<IPublicationLabel>();

		for (PublicationLabels label : allLabels) {
			IPublicationLabel pubLabel_ = PublicationsLabelsWrapper.convertToAnoteStructure(label);
			pubLabels_.add(pubLabel_);
		}

		return pubLabels_;
	}

	@Override
	public String getFullText(Long id) throws ANoteException {
		Publications publications = publicationsManagerDao.getPublicationsAuxDao().getPublicationFullText(id);
		if (publications == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

		String content = publications.getPubFullcontent();

		return content;
	}

	/*
	 * private methods
	 */
	private void createSource(PublicationHasSources pubHasPubSource) {
		PublicationSources pubSource = pubHasPubSource.getPublicationSources();
		String sourceDesc = pubSource.getPssDescription();
		PublicationSources publicationSource = publicationsManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", sourceDesc);
		if (publicationSource == null) {
			publicationsManagerDao.getPublicationSourcesDao().save(pubSource);
			publicationsManagerDao.getPublicationSourcesDao().save(pubHasPubSource);
		} else {
			pubHasPubSource.getId().setPhpsPublicationSourceId(publicationSource.getPssId());
			pubHasPubSource.setPublicationSources(publicationSource);
			PublicationHasSources pubHasSource = publicationsManagerDao.getPublicationHasSourcesDao().findById(pubHasPubSource.getId());
			if (pubHasSource == null)
				publicationsManagerDao.getPublicationSourcesDao().save(pubHasPubSource);

		}

		// } else {
		//
		// pubHasPubSource.getId().setPhpsPublicationSourceId(publicationSource.getPssId());
		// pubHasPubSource.setPublicationSources(publicationSource);
		// PublicationHasSources pubHasSource =
		// publicationsManagerDao.getPublicationHasSourcesDao().findById(pubHasPubSource.getId());
		// if (pubHasSource == null) {
		// publicationsManagerDao.getPublicationSourcesDao().save(pubHasPubSource);
		// }
		// }

	}

	private void createFields(PublicationFields pubField) {
		publicationsManagerDao.getPublicationFieldsDao().save(pubField);
	}

	private void createLabels(PublicationHasLabels pubHasPubLabels) {

		PublicationLabels pubLabels = pubHasPubLabels.getPublicationLabels();
		String labelDesc = pubLabels.getPlaDescription();

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("plaDescription", labelDesc);
		List<PublicationLabels> pubLabelList = publicationsManagerDao.getPublicationLabelsDao().findByAttributes(eqRestrictions);

		if (pubLabelList.size() == 0) {

			publicationsManagerDao.getPublicationLabelsDao().save(pubLabels);
			publicationsManagerDao.getPublicationHasLabelsDao().save(pubHasPubLabels);

		} else {
			PublicationLabels pubLabel = pubLabelList.get(0);
			pubHasPubLabels.getId().setPhpPublicationLabelId(pubLabel.getPlaId());
			pubHasPubLabels.setPublicationLabels(pubLabel);
			PublicationHasLabels pubHasLabels = publicationsManagerDao.getPublicationHasLabelsDao().findById(pubHasPubLabels.getId());
			if (pubHasLabels == null) {
				publicationsManagerDao.getPublicationHasLabelsDao().save(pubHasPubLabels);
			}
		}
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updatePublicationAvailableFreeFullText(long pubid, boolean freeFullText) throws PublicationManagerException {
		Publications pub = publicationsManagerDao.getPublicationsDao().findById(pubid);
		if (pub == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		pub.setPubFreeFullText(freeFullText);
		publicationsManagerDao.getPublicationsDao().update(pub);
		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "publications", null, "update publication");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updatePublicationAFullTextContent(Long publicationID,String fullTextContent) throws PublicationManagerException {
		Publications pub = publicationsManagerDao.getPublicationsDao().findById(publicationID);
		if (pub == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		String fullTextContentInDB = pub.getPubFullcontent();
		if(fullTextContentInDB!=null && !fullTextContentInDB.isEmpty())
			throw new PublicationManagerException(ExceptionsCodes.codePublicationAlreadyFullText, ExceptionsCodes.msgPublicationAlreadyFullText);
		pub.setPubFullcontent(fullTextContent);
		publicationsManagerDao.getPublicationsDao().update(pub);

		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "publications", null, "update publication full text");
		usersManagerDao.getAuthUserLogsDao().save(log);
		
		return true;
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}
}
