package com.silicolife.textmining.core.interfaces.resource.dictionary;

import java.io.IOException;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.dictionary.configuration.IDictionaryLoaderConfiguration;

public interface IDictionaryLoader {
	
	public void stop();
	public IDictionary getDictionary();
	public IResourceUpdateReport loadTerms(IDictionaryLoaderConfiguration configuration) throws ANoteException, IOException;


}
