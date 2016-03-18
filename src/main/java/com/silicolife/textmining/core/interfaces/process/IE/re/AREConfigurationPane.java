package com.silicolife.textmining.core.interfaces.process.IE.re;

import javax.swing.JPanel;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.exception.REConfigurationException;

public abstract class AREConfigurationPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract void validateOptions() throws REConfigurationException;
	public abstract IREConfiguration getConfiguration() throws REConfigurationException, ANoteException;

}
