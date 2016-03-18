package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.settings.IPropertiesPanel;
import com.silicolife.textmining.core.interfaces.process.IE.exception.REWrongConfigurationException;
import com.silicolife.textmining.core.interfaces.process.IE.re.AREConfigurationPane;
import com.silicolife.textmining.core.interfaces.process.IE.re.IREConfiguration;
import com.silicolife.textmining.core.interfaces.process.utils.ISimpleTimeLeft;

public interface IREUniqueProcess {
	
	/**
	 * Method that give a instance of RE process
	 * 
	 * @param configuration
	 * @return
	 * @throws REWrongConfigurationException
	 */
	public IREProcess gerateREProcess(IREConfiguration configuration) throws REWrongConfigurationException;
	
	/**
	 * Method that give a instance of RE process adapt for timeleft bar
	 * 
	 * @param configuration
	 * @return
	 * @throws REWrongConfigurationException
	 */
	public IREProcess gerateREProcess(IREConfiguration configuration,ISimpleTimeLeft progress) throws REWrongConfigurationException;
	
	
	/**
	 * Method that give a unique id for RE process
	 * 
	 * @return process Id
	 */
	public String getProcessUID();
	
	/**
	 * Method that return a Panel for configure RE Process
	 * 
	 * @return
	 */
	public AREConfigurationPane getConfigurationPane(IREConfiguration defaultSettings);
	
	/**
	 * Method that return default configuration for RE Unique Process
	 * 
	 * @return
	 */
	public IREConfiguration getDefaultConfiguration();
	
	/**
	 * Method that return IPropertiesPanel for Settings configuration
	 * 
	 * @param treepath
	 * @param properties
	 * @param defaultProperties
	 * @return
	 */
	public IPropertiesPanel getPropertiesPanel(String treepath, Map<String, Object> properties,Map<String, Object> defaultProperties);

	/**
	 * Method that return the NER Path (like RE.Relation)
	 * 
	 * @return
	 */
	public String getSettingPath();

	/**
	 * Change @Note2 setting in IREConfiguration
	 * 
	 * @param properties
	 * @return
	 */
	public IREConfiguration getConfiguration(Map<String, Object> propertiesStandard);
	
}
