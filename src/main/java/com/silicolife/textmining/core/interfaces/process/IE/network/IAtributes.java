package com.silicolife.textmining.core.interfaces.process.IE.network;

import java.util.List;

public interface IAtributes {
	public String getLabel();
	public String getName();
	public String getValue();
	public String getValueType();
	public boolean isListAtribute();
	public List<IAtributes> getListAtributes();
}
