package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;

/**
 * NER process Document-based 
 * The main idea for this interface is run NER in memory, not keep the results in database
 * 
 * 
 * @author Hugo Costa
 *
 */
public interface INERProcessByDocument {
	
	public List<IEntityAnnotation> executeDocument(IAnnotatedDocument annotatedDocument) throws ANoteException;
	public List<IEntityAnnotation> executeDocument(String textStream) throws ANoteException;

}
