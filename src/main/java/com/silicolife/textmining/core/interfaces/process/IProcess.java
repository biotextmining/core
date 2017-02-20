	package com.silicolife.textmining.core.interfaces.process;

import java.util.Date;

/**
 * This interface define each Biomedical text mining operation that can change or create a Corpus
 * 
 * 
 * @author Hugo Costa
 *
 * @version 1.0 (16 de Junho 2009)
 */

public interface IProcess {
	
	public IProcessType getType();
	public IProcessOrigin getProcessOrigin();
	public long getId();
	public void stop();
	public Date getCreateDate();
	public Date getUpdateDate();

}
