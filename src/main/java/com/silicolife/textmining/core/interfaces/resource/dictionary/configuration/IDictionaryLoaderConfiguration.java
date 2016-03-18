package com.silicolife.textmining.core.interfaces.resource.dictionary.configuration;

import java.io.File;
import java.util.Properties;

import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;

public interface IDictionaryLoaderConfiguration {
	public File getFlatFile();
	public boolean loadExternalIDs();
	public Properties getProperties();
	public IDictionary getDictionary();
	public String getDictionaryLoaderUID();
}
