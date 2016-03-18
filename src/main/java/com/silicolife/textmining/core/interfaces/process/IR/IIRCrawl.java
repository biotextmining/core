package com.silicolife.textmining.core.interfaces.process.IR;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRCrawlingProcessReport;
import com.silicolife.textmining.core.interfaces.process.IR.exception.InternetConnectionProblemException;
import com.silicolife.textmining.core.interfaces.process.utils.ISimpleTimeLeft;

/**
 * Interface that implements a journal retrieval process
 * 
 * 
 * @author Hugo Costa
 *
 * @version 1.0 (17 Junho 2009)
 */
public interface IIRCrawl extends IIRProcess{
	
	/**
	 * Method that get pdf document on internet
	 * @param progress 
	 * 
	 * @param pmid Article pmid
	 * @param path Path where pdf are saved
	 * @return Pdf File
	 * 		  -- null if journal retrieval is not possible 
	 * @throws DatabaseLoadDriverException 
	 * @throws DaemonException 
	 * @throws InternetConnectionProblemException 
	 */
	public IIRCrawlingProcessReport getFullText(List<IPublication> pubs) throws ANoteException, InternetConnectionProblemException;

	public void setTimeProgress(ISimpleTimeLeft progress);

	public void setRange(Integer startRange, Integer endRAnge, Long startTime);
}
