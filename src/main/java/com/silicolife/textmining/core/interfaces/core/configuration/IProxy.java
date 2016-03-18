package com.silicolife.textmining.core.interfaces.core.configuration;




/**
 * This interface represent a HttpProxy for correct access to internet services 
 * 
 * @author Hugo Costa
 * 
 * @version 1.0 (16 Junho 2009)
 *
 */
public interface IProxy{
	
	public static String withoutProxy = "withoutproxy";
	/**
	 * Method that return the ProxyHost
	 * 
	 * @return ProxyHost
	 * -- Null if not exist
	 */
	public String getProxyHost();
	/**
	 * Method that return the ProxyPort
	 * 		-- Null if not exist
	 * 
	 * @return ProxyPort
	 * -- Null if not exist
	 */
	public String getProxyPort();
	
	/**
	 * Method for setting virtual machine Proxy
	 */
	public void setProxyToSystem();
	
	/**
	 *  Test if proxy is enable
	 * @return
	 */
	public boolean isEnable();
	
	
	public String type();
	
	
}
