package com.silicolife.textmining.core.interfaces.process.IE.ner;

import javax.swing.JPanel;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.exception.NERConfigurationException;


public abstract class ANERConfigurationPane extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract void validateOptions() throws NERConfigurationException;
	public abstract INERConfiguration getConfiguration() throws NERConfigurationException, ANoteException;
	
}
