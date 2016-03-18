package com.silicolife.textmining.core.interfaces.process.IR;

import java.util.Properties;

/**
 * Class that implemets a Information Retrieval Search Configuration
 * 
 * @author Hugo Costa
 *
 */
public interface IIRSearchConfiguration {
	
	/**
	 * Return keywords
	 * 
	 * @return
	 */
	public String getKeywords();
	
	/**
	 * Return Organism
	 * @return
	 */
	public String getOrganism();
	
	
	/**
	 * Return Configuration properties
	 * 
	 * @return
	 */
	public Properties getProperties();
	
	/**
	 * Return the Query Name result for IR Process
	 * 
	 * @return
	 */
	public String getQueryName();
}
