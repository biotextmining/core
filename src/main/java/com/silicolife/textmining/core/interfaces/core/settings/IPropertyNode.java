package com.silicolife.textmining.core.interfaces.core.settings;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.settings.exeption.PropertiesManagerException;

public interface IPropertyNode{
	
	String getTreePath();
	
	Set<String> getPropertiesIdentifiers();
	Map<String, Object> getDefaultProperties();

	void setProperties(Map<String, Object> props) throws PropertiesManagerException;
	Map<String, Object> getProperties();
	
	Object getProperty(String propName);
	IPropertiesPanel getPropertiesPanel();
	
	void populateProperties(Properties p);
	void populatePropertiesRestricted(Properties p);

	void loadProperties(Properties p);
	
	Object revert(String propId, String propValue);
	String convert(String propId, Object propValue);
	
	public boolean saveOnDataAccess();
	
}
