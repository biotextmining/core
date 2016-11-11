package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IAnnotationAccess {
	
	/**
	 * Get all events in a publication
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IEventAnnotation> getAnnotatedDocumentEvents(IAnnotatedDocument annotedDocument) throws ANoteException;

	
	/**
	 * Get all entities in a publications
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IEntityAnnotation> getAnnotatedDocumentEntities(IAnnotatedDocument annotedDocument) throws ANoteException;
	
	
	/**
	 * Insert Entities Annotation in a document within a Process ( NER or RE
	 * process)
	 * 
	 * @param document
	 * @param schema
	 * @param entityAnnotations
	 */
	public void addProcessDocumentEntitiesAnnotations(IIEProcess schema,IPublication document,List<IEntityAnnotation> entityAnnotations) throws ANoteException;
	

	/**
	 * Add a relations
	 * 
	 * @param event
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void addProcessDocumentEventAnnoations(IIEProcess process,IPublication document, List<IEventAnnotation> events) throws ANoteException;

	/**
	 * Delete annotation
	 * 
	 * @param annotationID
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inactiveAnnotations(List<Long> annotation) throws ANoteException;

	/**
	 * Update entity annotations (Like Class)
	 * 
	 * @param list
	 * @param classId
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updateEntityAnnotations(List<IEntityAnnotation> list) throws ANoteException;


	/**
	 * Edit relations
	 * 
	 * @param eventToEdit
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updateEventsAnnotations(List<IEventAnnotation> list) throws ANoteException;
	
	/**
	 * Get Entities Annotations from a list of id's
	 * 
	 * @param entitiesID
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public Map<Long,IEntityAnnotation> getEntitiesAnnotationsByIDs(List<Long> entitiesIDs) throws ANoteException;

	/**
	 * Get Event annotations from a list of id's
	 * 
	 * @param eventsID
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public Map<Long, IEventAnnotation> getEventAnnotationsByIDs(List<Long> eventsIDs) throws ANoteException;
	
	/**
	 * get Process Document statistics
	 * 
	 * @param annotatedDocument
	 * @return
	 */
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(IAnnotatedDocument annotatedDocument) throws ANoteException;
	
	/**
	 * 
	 * 
	 * @param resourceElement
	 * @return
	 * @throws ANoteException
	 */
	public List<IPublication> getPublicationByResourceElement(IResourceElement resourceElement) throws ANoteException;


}
