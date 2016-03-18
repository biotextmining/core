package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryPropertiesId;

/**
 * Class to transform anote2 Query properties structures to daemon Query
 * properties structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class QueriesPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<QueryProperties> queryProperties) {
		Properties properties = new Properties();
		// if (queryProperties.size() > 0) {
		// properties = new Properties();
		for (QueryProperties queryProperty : queryProperties) {
			String key = queryProperty.getId().getQprPropKey();
			String value = queryProperty.getQprPropValue();
			properties.put(key, value);
			// }
		}
		return properties;
	}

	public static Set<QueryProperties> convertToDaemonStructure(Properties properties, Queries query) {

		Set<QueryProperties> queriesProperties = new HashSet<QueryProperties>(0);
		if (properties != null) {
			for (String key : properties.stringPropertyNames()) {
				QueryPropertiesId queriesPropertiesId = new QueryPropertiesId(query.getQuId(), key);
				String value = properties.getProperty(key);
				QueryProperties queriesPropertiesDaemon = new QueryProperties(queriesPropertiesId, query, value);
				queriesProperties.add(queriesPropertiesDaemon);
			}
		}

		return queriesProperties;
	}

}
