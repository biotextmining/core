package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

public interface IIEConfiguration extends IConfiguration{
	
	public ICorpus getCorpus();
	public void setCorpus(ICorpus corpus);
	public String getNotes();
	public void setNotes(String notes);
	public String getName();
	public Properties getProperties();
	
}
