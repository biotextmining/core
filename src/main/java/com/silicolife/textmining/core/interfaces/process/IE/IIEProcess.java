package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.analysis.IIEProcessStatistics;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IProcess;

/**
 * Interface that represents a Information Extration Process in Biomedical Text Miming
 * 
 * @author Hugo Costa
 *
 */
public interface IIEProcess extends IProcess{
	
	/**
	 * Return Process Name
	 * 
	 * @return
	 */
	public String getName();
	
	public void setName(String name);
	
	/**
	 * Return Properties
	 * 
	 * @return
	 */
	public Properties getProperties();
	
	public void setProperties(Properties properties);
	
	/**
	 * Return {@link ICorpus} associated to Process
	 * 
	 * @return
	 */
	public ICorpus getCorpus();
	
	public void setCorpus(ICorpus corpus);
	
	public String getNotes();
	
	public int getVersion();
	
	public void setVersion(int version);
	
	public IIEProcessStatistics getStatistics() throws ANoteException;
}
