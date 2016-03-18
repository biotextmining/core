package com.silicolife.textmining.core.interfaces.process.IE.ner;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;

/**
 * Interface that represents a NER Process Configuration
 * 
 * @author Hugo Costa
 *
 */
public interface INERConfiguration extends IIEConfiguration{
	public String getName();
	public String getUniqueProcessID();
	public Map<String, String> getNERProperties();
	public void setConfiguration(Object obj);

}
