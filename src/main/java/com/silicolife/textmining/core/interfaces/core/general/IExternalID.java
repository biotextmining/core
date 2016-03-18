package com.silicolife.textmining.core.interfaces.core.general;

import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

/**
 * Interface that represents an External ID
 * 
 * @author Hugo Costa
 *
 */
public interface IExternalID {
	
	/**
	 * Return externalID
	 * 
	 * @return
	 */
	public String getExternalID();
	
	
	/**
	 * Return Source Name ( Kegg, Chebi, Uniprot, ...)
	 * 
	 * @return
	 */
	public ISource getSource();

}
