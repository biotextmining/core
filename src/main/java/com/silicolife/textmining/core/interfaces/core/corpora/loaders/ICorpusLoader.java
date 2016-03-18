package com.silicolife.textmining.core.interfaces.core.corpora.loaders;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;

public interface ICorpusLoader {
	public List<IPublication> processFile(File fileOrDirectory,Properties properties) throws ANoteException, IOException;
	public boolean validateFile(File firectory);
	public void stop();
}
