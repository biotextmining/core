package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.process.IE.IIEConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IREConfiguration extends IIEConfiguration{
	
	public IIEProcess getEntityBasedProcess();
	public void setEntityBasedProcess(IIEProcess ieprocess);
	public String getUniqueProcessID();
	public Map<String, String> getREProperties();
	public boolean isUseManualCurationFromOtherProcess();
	public IIEProcess getManualCurationFromOtherProcess();

}
