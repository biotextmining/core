package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IREConfiguration extends IIEConfiguration{
	
	public IIEProcess getIEProcess();
	public void setIEProcess(IIEProcess ieprocess);
	public String getUniqueProcessID();
	public Map<String, String> getREProperties();
	public boolean useManualCurationFromOtherProcess();
	public IIEProcess getManualCurationFromOtherProcess();

}
