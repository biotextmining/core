package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusPropertiesId;

/**
 * Class to transform anote2 Corpus Properties structures to daemon Corpus
 * Properties structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class CorpusPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<CorpusProperties> corpusProperties) {

		Properties properties = new Properties();
		for (CorpusProperties corpusProperty : corpusProperties) {
			String key = corpusProperty.getId().getCopPropKey();
			String value = corpusProperty.getCopPropValue();
			properties.put(key, value);
		}
		if (properties.size() == 0)
			return new Properties();

		return properties;

	}

	public static Set<CorpusProperties> convertToDaemonStructure(Properties properties, Corpus corpus) {

		Set<CorpusProperties> corpusProperties = new HashSet<CorpusProperties>();
		for (String key : properties.stringPropertyNames()) {
			CorpusPropertiesId corpusPropertiesId = new CorpusPropertiesId(corpus.getCrpId(), key);
			String value = properties.getProperty(key);
			CorpusProperties corpusPropertiesDaemon = new CorpusProperties(corpusPropertiesId, corpus, value);
			corpusProperties.add(corpusPropertiesDaemon);
		}

		return corpusProperties;
	}
}
