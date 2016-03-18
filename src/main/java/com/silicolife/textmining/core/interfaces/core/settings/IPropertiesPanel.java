package com.silicolife.textmining.core.interfaces.core.settings;

import java.util.Map;

public interface IPropertiesPanel{

	public Map<String, Object> getProperties();
	public boolean haveChanged();
}
