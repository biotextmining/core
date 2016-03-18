package com.silicolife.textmining.core.interfaces.process.IE;

import java.sql.SQLException;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.settings.IPropertiesPanel;
import com.silicolife.textmining.core.interfaces.process.IE.exception.NERWrongConfigurationException;
import com.silicolife.textmining.core.interfaces.process.IE.ner.ANERConfigurationPane;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;
import com.silicolife.textmining.core.interfaces.process.utils.ISimpleTimeLeft;

/**
 * For Manager Workflows
 * 
 * @author Hugo Costa
 *
 */
public interface INERUniqueProcess{
	
	/**
	 * Method that give a instance of NER process
	 * 
	 * 
	 * @param configuration
	 * @return
	 * @throws NERWrongConfigurationException
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 */
	public INERProcess gerateNERProcess(INERConfiguration configuration) throws NERWrongConfigurationException, ANoteException;
	
	/**
	 * Method that give a instance of NER process adapt for timeleft bar
	 * 
	 * 
	 * @param configuration
	 * @return
	 * @throws NERWrongConfigurationException
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 */
	public INERProcess gerateNERProcess(INERConfiguration configuration,ISimpleTimeLeft progress) throws NERWrongConfigurationException, ANoteException;
	
	/**
	 * Method that give a unique id for NER process
	 * 
	 * @return process Id
	 */
	public String getProcessUID();
	
	
	/**
	 * Method that return a Panel for configure NER Process
	 * 
	 * @param defaultSettings 
	 * 		if null -> No default settings
	 * @return
	 */
	public ANERConfigurationPane getConfigutaionPane(INERConfiguration defaultSettings);
	
	/**
	 * Method that return default configuration for NER Process
	 * 
	 * @return
	 */
	public INERConfiguration getDefaultConfiguration();
	
	/**
	 * Change @Note2 setting in INERConfiguration
	 * 
	 * @param properties
	 * @return
	 */
	public INERConfiguration getConfiguration(Map<String,Object> properties);
	
	/**
	 * Method that return the NER Path (like NER.linnaeus)
	 * 
	 * @return
	 */
	public String getSettingPath();
	
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
	 * Methot that return if NER Process is Based in Lexical Resources ( Dicionaries , Ontologies, etc)
	 * 
	 * @return
	 */
	public boolean isLexicalResourcesBased();	
		
}
