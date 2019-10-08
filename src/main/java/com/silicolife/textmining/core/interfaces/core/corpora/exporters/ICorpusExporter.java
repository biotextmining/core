package com.silicolife.textmining.core.interfaces.core.corpora.exporters;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public interface ICorpusExporter {

	public boolean exportCorpus(ICorpus corpus) throws ANoteException;
	public void stop();
	
}
