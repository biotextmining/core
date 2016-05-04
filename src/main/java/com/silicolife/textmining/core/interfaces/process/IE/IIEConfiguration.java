package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

public interface IIEConfiguration extends IConfiguration{
	
	public ICorpus getCorpus();
	public void setCorpus(ICorpus corpus);
	public String getProcessNotes();
	public void setProcessNotes(String notes);
	public String getProcessName();
	public Properties getProperties();
	
}
