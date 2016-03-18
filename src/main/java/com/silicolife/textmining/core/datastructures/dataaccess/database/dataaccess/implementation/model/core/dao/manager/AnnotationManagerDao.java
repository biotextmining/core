package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.AnnotationAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSides;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;

@Repository
public class AnnotationManagerDao {
	
	private GenericDao<Annotations> annotationsDao;
	private GenericDao<AnnotationProperties> annotationPropertiesDao;
	private GenericDao<AnnotationSides> annotationSidesDao;
	private GenericDao<AnnotationLogs> annotationLogsDao;
	private AnnotationAuxDao annotationAuxDao;

	@Autowired
	public AnnotationManagerDao(GenericDao<Annotations> annotationsDao, GenericDao<AnnotationProperties> annotationPropertiesDao, GenericDao<AnnotationSides> annotationSidesDao,
			GenericDao<AnnotationLogs> annotationLogsDao, AnnotationAuxDao annotationAuxDao) {
		this.annotationsDao = annotationsDao;
		this.annotationPropertiesDao = annotationPropertiesDao;
		this.annotationSidesDao = annotationSidesDao;
		this.annotationLogsDao = annotationLogsDao;
		this.annotationAuxDao = annotationAuxDao;
	}

	public GenericDao<Annotations> getAnnotationsDao() {
		return annotationsDao;
	}

	public GenericDao<AnnotationProperties> getAnnotationPropertiesDao() {
		return annotationPropertiesDao;
	}

	public GenericDao<AnnotationSides> getAnnotationSidesDao() {
		return annotationSidesDao;
	}

	public GenericDao<AnnotationLogs> getAnnotationLogsDao() {
		return annotationLogsDao;
	}

	public AnnotationAuxDao getAnnotationAuxDao() {
		return annotationAuxDao;
	}

}
