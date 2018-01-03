package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;

/**
 * RE process Document-based 
 * The main idea for this interface is running RE in memory, not keep the results in database 
 * 
 * @author Hugo Costa
 *
 */
public interface IREProcessByDocument {
	
	public List<IEventAnnotation> executeDocument(IAnnotatedDocument annotationDoc) throws ANoteException;

}
