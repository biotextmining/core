package com.silicolife.textmining.core.interfaces.core.document.corpus;

import java.util.List;
import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface ICorpus {

	public long getId();

	public String getDescription();
	
	public String getNotes();

//	public void addDocument(IPublication doc) throws ANoteException;

//	public void registerProcess(IIEProcess ieProcess) throws ANoteException;

	public IDocumentSet getArticlesCorpus() throws ANoteException;

	public List<IIEProcess> getIEProcesses() throws ANoteException;

	public List<IIEProcess> getIEProcessesFilterByType(String type) throws ANoteException;

	public ICorpusStatistics getCorpusStatistics() throws ANoteException;

	public Properties getProperties();
	
	public CorpusTextType getCorpusTextType();

}
