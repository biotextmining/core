package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.annotation.ManualCurationAnnotationsImpl;
import com.silicolife.textmining.core.datastructures.annotation.log.AnnotationLogImpl;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.re.EventAnnotationImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentStatisticsImpl;
import com.silicolife.textmining.core.datastructures.documents.PublicationFilterImpl;
import com.silicolife.textmining.core.datastructures.documents.structure.SentenceImpl;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

/**
 * Class which implements all annotation daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class AnnotationAccessImpl extends RestClientAccess {

	private static int maxEntityAnnotationsPerRequest = 1000;

	public AnnotationAccessImpl() {
		super();
	}


	/**
	 * Add corpus process document entity annotations
	 * 
	 * @param corpusId
	 * @param processId
	 * @param documentId
	 * @param entityAnnotations
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addCorpusProcessDocumentEntityAnootations(Long corpusId, Long processId, Long documentId, List<IEntityAnnotation> entityAnnotations) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		uriVariables.put("processId", processId);
		uriVariables.put("documentId", documentId);

		if(entityAnnotations.size() > maxEntityAnnotationsPerRequest)
		{
			return addEntitiesPage(entityAnnotations, responseType, uriVariables);
		}
		else
		{
			return addEntities(entityAnnotations, responseType, uriVariables);
		}
	}


	private Boolean addEntitiesPage(List<IEntityAnnotation> entityAnnotations,ParameterizedTypeReference<DaemonResponse<Boolean>> responseType, Map<String, Long> uriVariables) throws DaemonException{
		int listSize = entityAnnotations.size();
		int start,end;
		List<IEntityAnnotation> entityAnnotationsSubList;
		for(int i=0;i<listSize;i=i+maxEntityAnnotationsPerRequest)
		{
			start = i;
			end = i+maxEntityAnnotationsPerRequest;
			if(end > listSize)
				end = listSize;
			entityAnnotationsSubList = entityAnnotations.subList(start, end);
			ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("annotation/addCorpusProcessDocumentEntityAnootations", responseType, entityAnnotationsSubList, uriVariables);

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
			}
		}
		return true;
	}


	private Boolean addEntities(List<IEntityAnnotation> entityAnnotations,
			ParameterizedTypeReference<DaemonResponse<Boolean>> responseType, Map<String, Long> uriVariables)
			throws DaemonException {
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("annotation/addCorpusProcessDocumentEntityAnootations", responseType, entityAnnotations, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Get processes document statistics
	 * 
	 * @param publicationId
	 * @param processId
	 * @return
	 * @throws DaemonException
	 */
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(Long publicationId,  Long processId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<AnnotatedDocumentStatisticsImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<AnnotatedDocumentStatisticsImpl>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		uriVariables.put("processId", processId);

		ResponseEntity<DaemonResponse<AnnotatedDocumentStatisticsImpl>> response = webClient.get("annotation/getProcessDocumentStatistics", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IAnnotatedDocumentStatistics stats = response.getBody().getContent();
			return stats;
		}
	}


	/**
	 * Get process documents annotation entitites
	 * 
	 * @param publicationId
	 * @param processId
	 * @return
	 * @throws DaemonException
	 */
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntities(Long processId, Long publicationId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		uriVariables.put("processId", processId);

		ResponseEntity<DaemonResponse<List<EntityAnnotationImpl>>> response = webClient.get("annotation/getProcessDoumentAnnotationEntities", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			List<IEntityAnnotation> iEntityAnnotations = new ArrayList<IEntityAnnotation>();
			List<EntityAnnotationImpl> entityAnnotation = response.getBody().getContent();

			for (EntityAnnotationImpl entityAnnot : entityAnnotation) {
				iEntityAnnotations.add(entityAnnot);
			}

			return iEntityAnnotations;
		}	
	}
	
	/**
	 * Get process documents annotation entities filtered by resourceElement
	 * 
	 * 
	 * @param processId
	 * @param publicationId
	 * @param resourceId
	 * @return
	 * @throws DaemonException
	 */
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesFilteredByResourceElement(Long processId, Long publicationId, Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		uriVariables.put("processId", processId);
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<List<EntityAnnotationImpl>>> response = webClient.get("annotation/getProcessDoumentAnnotationEntitiesFilteredByResourceElement", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			List<IEntityAnnotation> iEntityAnnotations = new ArrayList<IEntityAnnotation>();
			List<EntityAnnotationImpl> entityAnnotation = response.getBody().getContent();

			for (EntityAnnotationImpl entityAnnot : entityAnnotation) {
				iEntityAnnotations.add(entityAnnot);
			}

			return iEntityAnnotations;
		}	
	}
	
	/**
	 * Gets process document annotations of a sentence
	 * 
	 * @param processId
	 * @param publicationId
	 * @param sentence
	 * @return
	 * @throws DaemonException
	 */
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(Long processId, Long publicationId, ISentence sentence) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<EntityAnnotationImpl>>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		uriVariables.put("processId", processId);

		ResponseEntity<DaemonResponse<List<EntityAnnotationImpl>>> response = webClient.post("annotation/getProcessDoumentAnnotationEntitiesOfSentence", responseType, sentence, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			List<IEntityAnnotation> iEntityAnnotations = new ArrayList<IEntityAnnotation>();
			List<EntityAnnotationImpl> entityAnnotation = response.getBody().getContent();

			for (EntityAnnotationImpl entityAnnot : entityAnnotation) {
				iEntityAnnotations.add(entityAnnot);
			}

			return iEntityAnnotations;
		}	
	}
	
	/**
	 * Get process documents log
	 * 
	 * @param processId
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */

	public SortedSet<IAnnotationLog> getProcessDocumentLogs(Long processId, Long publicationId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<SortedSet<AnnotationLogImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<SortedSet<AnnotationLogImpl>>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("processId", processId);
		uriVariables.put("publicationId", publicationId);

		ResponseEntity<DaemonResponse<SortedSet<AnnotationLogImpl>>> response = webClient.get("annotation/getProcessDocumentLogs", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			SortedSet<IAnnotationLog> iAnnotaionLogs = new TreeSet<IAnnotationLog>();
			SortedSet<AnnotationLogImpl> annotaionLogs = response.getBody().getContent();

			for (AnnotationLogImpl log : annotaionLogs) {
				iAnnotaionLogs.add(log);
			}

			return iAnnotaionLogs;
		}	
	}


	/**
	 * Add corpus processes documents events annotations
	 * 
	 * @param corpusId
	 * @param processId
	 * @param documentID
	 * @param events
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addCorpusProcessDocumentEventsAnootations(Long corpusId, Long processId, Long documentID, List<IEventAnnotation> events) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		uriVariables.put("processId", processId);
		uriVariables.put("documentId", documentID);


		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("annotation/addCorpusProcessDocumentEventsAnootations", responseType, events, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}	
	}


	/**
	 * Get processes Log
	 * 
	 * @param processId
	 * @return
	 * @throws DaemonException
	 */
	public SortedSet<IAnnotationLog> getProcessLogs(Long processId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<SortedSet<AnnotationLogImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<SortedSet<AnnotationLogImpl>>>() {};	
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("processId", processId);

		ResponseEntity<DaemonResponse<SortedSet<AnnotationLogImpl>>> response = webClient.get("annotation/getProcessLogs", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			SortedSet<IAnnotationLog> iAnnotaionLogs = new TreeSet<IAnnotationLog>();
			SortedSet<AnnotationLogImpl> annotaionLogs = response.getBody().getContent();

			for (AnnotationLogImpl log : annotaionLogs) {
				iAnnotaionLogs.add(log);
			}

			return iAnnotaionLogs;
		}	
	}


	/**
	 * Get process document annotation events
	 * 
	 * @param processId
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */
	public List<IEventAnnotation> getProcessDoumentAnnotationEvents(Long processId, Long publicationId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<EventAnnotationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<EventAnnotationImpl>>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("processId", processId);
		uriVariables.put("publicationId", publicationId);

		ResponseEntity<DaemonResponse<List<EventAnnotationImpl>>> response = webClient.get("annotation/getProcessDoumentAnnotationEvents", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			List<IEventAnnotation> iEventAnnotation = new ArrayList<IEventAnnotation>();
			List<EventAnnotationImpl> eventAnnotation = response.getBody().getContent();

			for (EventAnnotationImpl annot : eventAnnotation) {
				iEventAnnotation.add(annot);
			}

			return iEventAnnotation;
		}	
	}
	
	/**
	 * counts annotations
	 * 
	 * @param processId
	 * @param resourceElementId
	 * @return
	 * @throws DaemonException
	 */
	public Long countAnnotations(Long processId, Long resourceElementId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Long>> responseType = new ParameterizedTypeReference<DaemonResponse<Long>>() {};	
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("processId", processId);
		uriVariables.put("resourceElementId", resourceElementId);

		ResponseEntity<DaemonResponse<Long>> response = webClient.get("annotation/countAnnotations", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Long count = response.getBody().getContent();
			return count;
		}	
	}
	
	/**
	 * Get process document annotation association logs
	 * 
	 * 
	 * @param processId
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */
	public List<IAnnotation> getProcessDocumentAnnotationsAssociatedToLogs(Long processId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ManualCurationAnnotationsImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ManualCurationAnnotationsImpl>>() {};	
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("processId", processId);

		ResponseEntity<DaemonResponse<ManualCurationAnnotationsImpl>> response = webClient.get("annotation/getProcessDocumentAnnotationsAssociatedToLogs", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IManualCurationAnnotations manualCuration = response.getBody().getContent();

			return manualCuration.getAnnotations();
		}	
	}
	
	/**
	 * Gets the sentence an annotation is in
	 * 
	 * @param entityAnnotationId
	 * @return
	 * @throws DaemonException
	 */
	public ISentence getSentence(Long entityAnnotationId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<SentenceImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<SentenceImpl>>() {};	
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("entityAnnotationId", entityAnnotationId);

		ResponseEntity<DaemonResponse<SentenceImpl>> response = webClient.get("annotation/getSentence", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			ISentence sentence = response.getBody().getContent();

			return sentence;
		}	
	}

	/**
	 * Add annotations logs
	 * 
	 * 
	 * @param annotationLogs
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addAnnotationLogs(List<IAnnotationLog> annotationLogs) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("annotation/addAnnotationLogs", responseType, annotationLogs);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}	
	}


	/**
	 * Inactive annotations
	 * 
	 * @param annotation
	 * @return
	 * @throws DaemonException
	 */
	public Boolean inactiveAnnotations(List<Long> annotation) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("annotation/inactiveAnnotations", responseType, annotation);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}		
	}


	/**
	 * Update entity annotations
	 * 
	 * @param IEntityAnnotation
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateEntityAnnotations(List<IEntityAnnotation> IEntityAnnotation) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("annotation/updateEntityAnnotations", responseType, IEntityAnnotation);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Return publication with annotation based on resourceElementID given by any process
	 * 
	 * @param resourceElementID
	 * @return
	 * @throws DaemonException
	 */
	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resourceElementIds) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<Long>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<Long>>>() {};	
		ResponseEntity<DaemonResponse<List<Long>>> response = webClient.post("annotation/getPublicationsIdsByResourceElements", responseType, resourceElementIds);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return response.getBody().getContent();
		}	
	}
	
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<Long> resourceElementIds, IPublicationFilter pubFilter) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<Long>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<Long>>>() {};
		IGenericPair<Set<Long>, PublicationFilterImpl> pair = new GenericPairImpl<Set<Long>, PublicationFilterImpl>();
		pair.setX(resourceElementIds);
		pair.setY((PublicationFilterImpl) pubFilter);
		ResponseEntity<DaemonResponse<List<Long>>> response = webClient.post("annotation/getPublicationsIdsByResourceElementsFilteredByPublicationFilter", responseType, pair);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return response.getBody().getContent();
		}	
	}

	public List<Long> getProcessesIdsByResourceElements(Set<Long> resourceElementsIds) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<Long>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<Long>>>() {};	
		ResponseEntity<DaemonResponse<List<Long>>> response = webClient.post("annotation/getProcessesIdsByResourceElements", responseType, resourceElementsIds);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return response.getBody().getContent();
		}	
	}


	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<Long>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<Long>>>() {};

		ResponseEntity<DaemonResponse<List<Long>>> response = webClient.post("annotation/getPublicationsIdsByAnnotationsFilter", responseType, filter);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}


	public Boolean removeAllProcessDocumentAnnotations(Long processId, Long publicationId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		uriVariables.put("processId", processId);
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("annotation/removeAllProcessDocumentAnnotations", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} 
		else {
			return response.getBody().getContent();
		}	
	}

}