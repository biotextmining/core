package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;

public interface IRelationModel {
	
	/**
	 * Method that return a list of char that possible ends a relation
	 * Examples . , :
	 * This endRelations are used in posTagger  
	 * 
	 * @return
	 */
	public Set<String> getRelationTerminations();
	
	/**
	 * This method receive a information about posTagging ( Sintatic LAyer) verbs,verb chunker,
	 * endRelations connectors.
	 * 
	 * and information about biological entities ( Semantic Layer ) and return
	 * all relations of the sentence
	 * @param sintaticLayer sintatic Annotations
	 * @param semanticLayer semantic annotations
	 * 
	 * @return List of relations
	 */
	public List<IEventAnnotation> extractSentenceRelation(IAnnotatedDocument document,List<IEntityAnnotation> semanticLayer,com.silicolife.textmining.core.interfaces.core.document.structure.ISentenceSintaxRepresentation sentenceRepresentation) throws ANoteException;
	
	public String getDescription();
	
	public String getImagePath();
	
	public Properties getProperties();
	
	public String getUID();

}
