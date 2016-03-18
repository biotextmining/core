	package com.silicolife.textmining.core.interfaces.core.document;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * This interface implements a interface for representing as Annotated Document for {@link IEProcessImpl}
 * 
 * @author Hugo Costa
 * 
 *
 */
public interface IAnnotatedDocument extends IPublication{
	
	/**
	 * Method that return all {@link IEntityAnnotation} for document
	 * 
	 * @return List<IEntityAnnotation>
	 * @throws DatabaseLoadDriverException 
	 * @throws DaemonException 
	 */
	public List<IEntityAnnotation> getEntitiesAnnotations() throws ANoteException;
	
	/**
	 * Method that return all {@link IEventAnnotation} annotation for document
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException 
	 * @throws DaemonException 
	 */
	public List<IEventAnnotation> getEventAnnotations() throws ANoteException;
	
	/**
	 * return {@link IProcess} associated
	 * @return
	 */
	public IIEProcess getProcess();

	
	/**
	 * Return {@link ICorpus} associated to {@link IAnnotatedDocument}
	 * @return
	 */
	public ICorpus getCorpus();
	
	/**
	 * Return Document list of {@link ISentence}
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException 
	 * @throws Exception 
	 */
	public List<ISentence> getSentencesText() throws ANoteException, IOException;
	
	/**
	 * Return document annotated text ( could be different from original text)
	 * @throws DatabaseLoadDriverException 
	 */
	public String getDocumentAnnotationText() throws ANoteException;
	
	/**
	 * Return document annotated text with html tags in {@link IEntityAnnotation} and {@link IEventAnnotation}
	 * @throws DatabaseLoadDriverException 
	 */
	public String getDocumentAnnotationTextHTML() throws ANoteException;
	
	/**
	 * return annotated Classes present in document
	 * 
	 * @return
	 * @throws ANoteException 
	 */
	public Set<IAnoteClass> getEntityAnnotatedClasses() throws ANoteException;
	
	/**
	 * Get Statistics
	 * 
	 * @return
	 * @throws ANoteException 
	 */
	public IAnnotatedDocumentStatistics getStatistics() throws ANoteException;
	
}
