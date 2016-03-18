package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.util.Properties;


/**
 * Class that represents a Token
 * 
 * @author Hugo Costa
 *
 */
public interface IToken {
	
	public long getStartOffset();
	public long getEndOffset();
	public String getText();
	public String getUID();
	public Properties getProperties();
	public void addProperty(String key,Object value);

}
