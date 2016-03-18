package com.silicolife.textmining.core.interfaces.process.IR;


import com.silicolife.textmining.core.datastructures.exceptions.PubmedException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchProcessReport;
import com.silicolife.textmining.core.interfaces.process.IR.exception.InternetConnectionProblemException;


/**
 * This interface implements a Information retrieval process that search in online library archives 
 * detailed information for scientific articles
 * 
 * @author Hugo Costa
 *
 * @version 1.0 ( 17 junho 2009)
 */
public interface IIRSearch extends IIRProcess{
	
	/**
	 * Method that return 
	 * 
	 * @return
	 */
	public IQuery getQuery();
	
	/**
	 * Method that return the number of query result publication
	 * 
	 * @param query
	 * @return Number of publications mathing for query
	 * @throws InternetConnectionProblemException 
	 */
	public int getExpectedQueryResults(String query) throws InternetConnectionProblemException;
	
	/**
	 * Search publication online library archives and save details on database
	 * 
	 * @param keywords - The keywords of search
	 * @param organism - Search direct to an Organism 
	 * @param properties - List of Query properties that can be used to specify search
	 * 
	 * @return IRSearch Report - if process success
	 * 		   -- false othewise
	 * @throws PubmedException 
	 * @throws DatabaseLoadDriverException 
	 * @throws InternetConnectionProblemException 
	 * @throws DaemonException 
	 */
	public IIRSearchProcessReport search(IIRSearchConfiguration query) throws ANoteException, InternetConnectionProblemException;
}


