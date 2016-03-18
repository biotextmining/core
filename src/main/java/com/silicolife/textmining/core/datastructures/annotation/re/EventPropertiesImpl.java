package com.silicolife.textmining.core.datastructures.annotation.re;

import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.IEventProperties;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;

/**
 * 
 * @author Hugo Costa
 *
 */
public class EventPropertiesImpl implements IEventProperties{
	
	private Properties properties;
	private static String lemma = "lemma";
	private static String polarity = "polarity";
	private static String directionally = "directionally";	
	
	public EventPropertiesImpl()
	{
		this.properties = new Properties();
		this.properties.put(lemma, new String());
		this.properties.put(polarity,PolarityEnum.Unknown.name());
		this.properties.put(directionally,DirectionallyEnum.Unknown.name());
	}

	public EventPropertiesImpl(Properties properties)
	{
		this.properties=properties;
	}
	
	
	@Override
	public Properties getProperties() {
		return properties;
	}
	
	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
	@JsonIgnore
	public void setGeneralProperty(String name,String value)
	{
		properties.put(name, value);
	}
	
	@JsonIgnore
	public String getGeneralProperties(String key)
	{
		return properties.getProperty(key);
	}

	@JsonIgnore
	public String getLemma() {
		return this.properties.getProperty(lemma);
	}
	
	@JsonIgnore
	public void setLemma(String lemmaStr) {
		this.properties.put(lemma, lemmaStr);
	}
	
	@JsonIgnore
	public PolarityEnum getPolarity() {
		if(!this.properties.containsKey(polarity))
			return PolarityEnum.Unknown;
		return PolarityEnum.convertStringToEnum(this.properties.getProperty(polarity));
	}
	
	@JsonIgnore
	public void setPolarity(PolarityEnum polaritySTr) {
		this.properties.put(polarity, polaritySTr.name());;
	}
	
	@JsonIgnore
	public DirectionallyEnum getDirectionally() {
		if(!this.properties.containsKey(directionally))
			return DirectionallyEnum.Unknown;
		return DirectionallyEnum.convertStringToEnum(this.properties.getProperty(directionally));
	}
	
	@JsonIgnore
	public void setDirectionally(DirectionallyEnum directionallyEnum) {
		this.properties.put(directionally, directionallyEnum.name());
	}
	
}
