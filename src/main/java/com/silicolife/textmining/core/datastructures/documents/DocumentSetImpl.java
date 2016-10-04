package com.silicolife.textmining.core.datastructures.documents;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.MapPublicationsDeserialize;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;

/**
 * Class that represent a Set of Documents
 * 
 * @author Hugo Costa
 *
 */
public class DocumentSetImpl implements Serializable, IDocumentSet{
	
	
	private static final long serialVersionUID = 2L;
	
	/**
	 * Instance class that contain a information for each article
	 */
	@JsonDeserialize(using = MapPublicationsDeserialize.class)
	private Map<Long,IPublication> allDocuments; //id -> document

	/**
	 * Constructors
	 */
	public DocumentSetImpl() 
	{
		this.allDocuments=new HashMap<Long, IPublication>();
	}
	
	public DocumentSetImpl(IDocumentSet docs) 
	{
		this.allDocuments=new HashMap<Long, IPublication>();
		for(IPublication doc:docs)
		{
			this.allDocuments.put(doc.getId(), doc);
		}
	}
	
	@Override
	public Map<Long, IPublication> getAllDocuments() {
		return allDocuments;
	}	
	
	public void setAllDocuments(Map<Long, IPublication> allDocuments) {
		this.allDocuments = allDocuments;
	}

	/**
	 * Method that adds a document */
	@JsonIgnore
	public void addDocument(long publicationID,IPublication document)
	{
		this.allDocuments.put(publicationID,document);
	}
	
	/**
	 * Method that removes a document */
	@JsonIgnore
	public void removeDocument(long publicationID)
	{
		this.allDocuments.remove(publicationID);
	}
	
	/**
	 * Method that removes a document */
	@JsonIgnore
	public IPublication getDocument(long publicationID)
	{
		return this.allDocuments.get(publicationID);
	}
	
	@JsonIgnore
	public Iterator<IPublication> iterator() {
		return this.allDocuments.values().iterator();
	}
	
	@JsonIgnore
	public int size() {
		return this.allDocuments.size();
	}	
}
