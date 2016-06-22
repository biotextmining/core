package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation;

import java.util.List;
import java.util.SortedSet;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;

public interface IAnnotationService {
	
	public Boolean addCorpusProcessDocumentEntityAnootations(Long corpusId,Long processId,Long documentID,List<IEntityAnnotation> entityAnnotations) throws AnnotationException;

	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(Long publicationId ,Long processID) throws AnnotationException;

	public List<IEntityAnnotation> getProcessDoumentAnnotationEntities(Long publicationId,Long processID) throws AnnotationException;

	public SortedSet<IAnnotationLog> getProcessDocumentLogs(Long processID, Long publicationId) throws AnnotationException;

	public Boolean addCorpusProcessDocumentEventsAnootations(Long corpusId, Long processId,Long documentID, List<IEventAnnotation> events) throws AnnotationException;

	public SortedSet<IAnnotationLog> getProcessLogs(Long processId) throws AnnotationException;

	public List<IEventAnnotation> getProcessDoumentAnnotationEvents(Long processID, Long publicationId) throws AnnotationException;

	public IManualCurationAnnotations getProcessDocumentAnnotationsAssociatedToLogs(Long processID) throws AnnotationException;

	public Boolean addAnnotationLogs(List<IAnnotationLog> annotationLogs) throws AnnotationException;

	public Boolean inactiveAnnotations(List<Long> annotation) throws AnnotationException;

	public Boolean updateEntityAnnotations(List<IEntityAnnotation> list) throws AnnotationException;
	
	public void setUserLogged(UsersLogged userLogged);

}
