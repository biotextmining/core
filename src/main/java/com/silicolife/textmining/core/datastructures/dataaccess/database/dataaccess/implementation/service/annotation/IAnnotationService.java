package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

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

	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resourceElementIds) throws AnnotationException;
	
	public List<Long> getProcessesIdsByResourceElements(Set<Long> resourceElementIds) throws AnnotationException;

	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws AnnotationException;

	public Boolean removeAllProcessDocumentAnnotations(Long processID, Long publicationId)  throws AnnotationException;

	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesFilteredByResourceElement(Long publicationId,
			Long processID, Long resourceId) throws AnnotationException;

	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<Long> resourceElementIds,
			IPublicationFilter pubFilter) throws AnnotationException;

	//public ISentence getSentence(Long processID, Long publicationId, Long entityAnnotationId) throws AnnotationException, ANoteException, IOException;

	public ISentence getSentence(Long entityAnnotationId) throws ANoteException, IOException;

	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(Long publicationId, Long processID,
			ISentence sentence) throws AnnotationException;

	public Long countAnnotationsByResourceElement(Long processId, Long resourceElementId) throws AnnotationException;
	
	public Long countAnnotationsByAnnotionType(Long processId, String annotationType) throws AnnotationException;
	
	public Map<IAnoteClass, Long> countAnnotationsByClassInProcess(Long processId) throws AnnotationException;

	public Long countPublicationAnnotationsByAnnotionType(Long processId, Long publicationID, String annotationType) throws AnnotationException;
	
	public Map<IResourceElement, Long> countAnnotationsByResourceElementInProcess(Long processId) throws AnnotationException;

	public Map<IResourceElement, Long> countAnnotationsByResourceElementInDocument(Long documentId, Long processId) throws AnnotationException;

	public Long countDocumentsWithResourceElementInProcess(Long resourceElementId, Long processId) throws AnnotationException;
	
	public Map<IResourceElement, Long> countDocumentsWithAnnotationsByResourceElementInProcess(Long processId) throws AnnotationException;
	
}
