package com.silicolife.textmining.core.interfaces.core.corpora;

import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

/**
 * Interface to define parameter to create Corpus
 * 
 * @author Hugo Costa
 *
 */
public interface ICorpusCreateConfiguration extends IConfiguration{
	
	/**
	 * Method that return corpus name
	 * 
	 * @return
	 */
	public String getCorpusName();
	
	/**
	 * 
	 * @return
	 */
	public String getCorpusNotes();
	
	/**
	 * Method that return set of {@link IPublication} for add  to new {@link ICorpus}
	 * 
	 * @return
	 */
	public Set<IPublication> getDocuments();
	
	/**
	 * Method that return set of {@link IPublication} for add  to new {@link ICorpus}
	 * 
	 * @return
	 */
	public Set<Long> getDocumentsIDs();
	
	
	public void setDocuments(Set<IPublication> publictions);
		
	/**
	 * Method that return {@link CorpusTextType}
	 * 
	 * @return
	 */
	public CorpusTextType getCorpusTextType();
	
	/**
	 * Method that return is during the {@link CorpusImpl} creation executes a Journal Retrieval
	 * 
	 * @return
	 */
	public boolean isProcessJournalRetrievalBeforeNeeded();
	
	public Properties getProperties();
	
}
