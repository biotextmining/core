package com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.configuration;

import java.io.File;
import java.util.Properties;

import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;
import com.silicolife.textmining.core.interfaces.resource.dictionary.configuration.IDictionaryLoaderConfiguration;

public class DictionaryLoaderConfigurationImpl implements IDictionaryLoaderConfiguration{
	
	
	private File file;
	private Properties properties;
	private boolean loadExtendalIDds;
	private String loaderUID;
	private IDictionary dictionary;

	public DictionaryLoaderConfigurationImpl(String loaderUID,IDictionary dictionary,File file,Properties properties,boolean loadExtendalIDds)
	{
		this.loaderUID=loaderUID;
		this.dictionary=dictionary;
		this.file = file;
		this.properties=properties;
		this.loadExtendalIDds=loadExtendalIDds;
	}

	@Override
	public File getFlatFile() {
		return file;
	}

	@Override
	public boolean loadExternalIDs() {
		return loadExtendalIDds;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public IDictionary getDictionary() {
		return dictionary;
	}

	@Override
	public String getDictionaryLoaderUID() {
		return loaderUID;
	}

}
