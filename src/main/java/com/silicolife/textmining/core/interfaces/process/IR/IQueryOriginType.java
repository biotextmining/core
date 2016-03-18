package com.silicolife.textmining.core.interfaces.process.IR;

/**
 * Interface that represent Query type origin) ( like Pubmed Search, Cluster Results or others)
 * 
 * @author Hugo Costa
 *
 */
public interface IQueryOriginType {

	/**
	 * 
	 * @return QueryTypeID in database
	 */
	public long getId();
		
	/**
	 * Get Type (Pubmed Search, OPS Search, Cluster Generating)
	 * 
	 * @return
	 */
	public String getOrigin();
}
