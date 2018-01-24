package com.silicolife.textmining.core.interfaces.core.document;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

/**
 * Interface that implements a scientific document.
 * 
 * @author Hugo Costa
 *
 * @version 1.1 (14 Novembro 2009)
 */
public interface IPublication extends Serializable{

	/**
	 * This method return a ID for a document
	 * 
	 * @return
	 */
	public long getId();
		
	public void setId(long id);


	/**
	 * Method that return article title
	 * 
	 * @return article title -- null if don�t exit
	 */
	public String getTitle();

	/**
	 * Method that return article authors
	 * 
	 * @return article authors -- null if don�t exit
	 */
	public String getAuthors();
	
	/**
	 * Method that return document Main type( Publication, Book, Patent, ...)
	 * 
	 * @return article type -- null if don�t exit
	 */
	public String getType();

	/**
	 * Method that return document sub type or category ( Review, Journal Article, ...)
	 * 
	 * @return article category -- null if don�t exit
	 */
	public String getCategory();
	
	/**
	 * Method that return article date
	 * 
	 * @return article date -- null if don�t exit
	 */
	public String getYeardate();

	/**
	 * Method that return article date
	 * 
	 * @return article date -- null if don�t exit
	 */
	public String getFulldate();

	/**
	 * Method that return article publication status
	 * 
	 * @return article article publication status -- null if don�t exit
	 */
	public String getStatus();

	/**
	 * Method that return article journal
	 * 
	 * @return article journal -- null if don�t exit
	 */
	public String getJournal();

	/**
	 * Method that return article publication volume
	 * 
	 * @return article article publication volume -- null if don�t exit
	 */
	public String getVolume();

	public String getIssue();

	/**
	 * Method that return article publication volume pages
	 * 
	 * @return article article publication volume pages -- null if don�t exit
	 */
	public String getPages();

	/**
	 * Method that return abstract
	 * 
	 * @return article abstract -- null if don�t exit
	 */
	public String getAbstractSection();
	
	public void setAbstractSection(String newAbstract);

	/**
	 * Method that return article publication external Link
	 * 
	 * @return article article publication external link -- null if don�t exit
	 */
	public String getExternalLink();

	/**
	 * Method that set article publication external Link
	 * 
	 * @return article article publication external link -- null if don�t exit
	 * @throws DatabaseLoadDriverException
	 */
	public void setExternalLink(String externalLink);

	/**
	 * Method for find if publication have PDF
	 * 
	 * @return
	 */
	public boolean isFreeFullText();

	public void setFreeFullText(boolean newAvailableFreeFullText);
	
	/**
	 * Return Document Notes
	 * 
	 * @return
	 */
	public String getNotes();
	
	public void setNotes(String notes);


	/**
	 * Method That return publication external of article : PMID:12344 or/and
	 * DOI:ngfkgf/fdf or/and other
	 * 
	 * @return article otherID ( example pmid) -- null if don't exist
	 * @throws DatabaseLoadDriverException
	 */
	public List<IPublicationExternalSourceLink> getPublicationExternalIDSource();

	/**
	 * Get Publication Fields
	 * 
	 * @return
	 */
	public List<IPublicationField> getPublicationFields();

	/**
	 * Method that return publication Labels
	 * 
	 * @return
	 */
	public List<IPublicationLabel> getPublicationLabels();

	/**
	 * Method to Add PDF File to Document
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public void addPDFFile(File file) throws IOException;

	/**
	 * Method that return the relative path for Publication PDF start from
	 * Global Option Docs
	 * 
	 * @return
	 * 
	 */
	public String getRelativePath();
	
	
	public void setRelativePath(String relativePath);

	public void setFullTextContent(String fullTextContent);

	public String getFullTextContent();
	
	public Boolean isPDFAvailable();

	public String getSourceURL();
	
	public void setSourceURL(String sourceURL);

	public void setTitle(String title);

	public void setAuthors(String authors);

	public void setYeardate(String date);
	
	public void setPublicationExternalIDSource(List<IPublicationExternalSourceLink> publicationExternalIDSource);

	public void setPublicationFields(List<IPublicationField> publicationFields);

	public void setPublicationLabels(List<IPublicationLabel> publicationLabels);

	public void setIssue(String issue);
	
	public void setType(String type);
}
