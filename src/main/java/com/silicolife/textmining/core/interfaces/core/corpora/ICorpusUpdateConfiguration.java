package com.silicolife.textmining.core.interfaces.core.corpora;

import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

public interface ICorpusUpdateConfiguration extends IConfiguration{
	
	public ICorpus getCorpusToUpdate();
	
	public String getPublicationsDirectory();

}
