package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessPropertiesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

public class ProcessPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<ProcessProperties> processProperties) {

		Properties properties = new Properties();
		for (ProcessProperties processProperty : processProperties) {
			String key = processProperty.getId().getPprPropKey();
			String value = processProperty.getPprPropValue();
			properties.put(key, value);
		}
//		if (properties.size() == 0)
//			return null;

		return properties;
			
	}

	public static Set<ProcessProperties> convertToDaemonStructure(Properties properties, Processes process) {
		
		
		Set<ProcessProperties> processsesProperties = new HashSet<ProcessProperties>(0);
		if(properties==null)
			return processsesProperties;
		for (String key : properties.stringPropertyNames()) {
			ProcessPropertiesId id = new ProcessPropertiesId(process.getProId(), key);
			String value = properties.getProperty(key);
			ProcessProperties processProperties = new ProcessProperties(id, process, value);
			processsesProperties.add(processProperties);
		}

		return processsesProperties;

	}
	
}
