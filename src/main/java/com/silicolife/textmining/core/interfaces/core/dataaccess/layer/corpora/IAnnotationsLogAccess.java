package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora;

import java.util.List;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IAnnotationsLogAccess {

	/**
	 * Get Annotation Logs for a document in a Corpus for a schema ( example
	 * NERShema or RESchema)
	 * 
	 * @param corpus
	 * @param process
	 * @param document
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(IIEProcess process, IPublication document) throws ANoteException;

	/**
	 * Get Annotation Logs for schema (example NERShema or RESchema) in a Corpus
	 * 
	 * @param process
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws ANoteException;

	/**
	 * Add a AnnotationLog to Document in a Schema
	 * 
	 * @param annotationLog
	 * @throws DatabaseLoadDriverException
	 */
	public void addProcessDocumentLogs(List<IAnnotationLog> annotationLogs) throws ANoteException;

	/**
	 * Get all annotation (IEntiyyAnnotation and IEventAnnotation) associated
	 * with Annotation log in the schema
	 * 
	 * @param ieProcess
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public List<IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws ANoteException;

}
