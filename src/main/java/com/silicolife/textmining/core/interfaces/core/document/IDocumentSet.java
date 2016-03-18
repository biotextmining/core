package com.silicolife.textmining.core.interfaces.core.document;

import java.util.Map;



/**
 * This interface represent a Set of Documents
 * 
 * @author Hugo Costa
 * 
 * @version 1.0 (19 Junho 2009)
 *
 */
public interface IDocumentSet extends Iterable<IPublication>{
	
	/**
	 * Method that add a document
	 * 
	 * @param id - DocumentID
	 * @param document Document
	 */
	public void addDocument(long id,IPublication document);
	
	/**
	 * Method that remove a Document given the id
	 * 
	 * @param id
	 */
	public void removeDocument(long id);
	
	/**
	 * Method that return a Document whit id
	 * 
	 * @param id
	 * @return
	 */
	public IPublication getDocument(long id);
	
	public Map<Long,IPublication> getAllDocuments();
	
	public int size();
		
}
