package com.silicolife.textmining.core.interfaces.process.IR;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.process.IConfiguration;

/**
 * Class that implemets a Information Retrieval Search Configuration
 * 
 * @author Hugo Costa
 *
 */
public interface IIRSearchConfiguration extends IConfiguration{
		
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
