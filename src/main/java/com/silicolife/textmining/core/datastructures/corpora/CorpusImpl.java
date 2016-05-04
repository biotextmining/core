package com.silicolife.textmining.core.datastructures.corpora;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class CorpusImpl extends Observable implements ICorpus {

	private long id;
	private String description;
	private String notes;
	private Properties properties;
	protected IDocumentSet documentSet;
	private List<IIEProcess> processesList;

	public CorpusImpl() {
	}
	
	public CorpusImpl(String description, String notes, Properties properties) {
		this.id = GenerateRandomId.generateID();
		this.description = description;
		this.notes = notes;
		this.properties = properties;
		this.documentSet = null;
		this.processesList=null;
	}

	public CorpusImpl(long id, String description, String notes, Properties properties) {
		this.id = id;
		this.description = description;
		this.notes = notes;
		this.properties = properties;
		this.documentSet = null;
		this.processesList=null;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@JsonIgnore
	public List<IIEProcess> getProcessesList() {
		return processesList;
	}

	@JsonIgnore
	public void setProcessesList(List<IIEProcess> processesList) {
		this.processesList = processesList;
	}

//	@JsonIgnore
//	@Override
//	public void addDocument(IPublication doc) throws ANoteException {
//	}

	@JsonIgnore
	@Override
	public void registerProcess(IIEProcess ieProcess) throws ANoteException {
		InitConfiguration.getDataAccess().registerCorpusProcess(this, ieProcess);
	}

	@JsonIgnore
	@Override
	public synchronized IDocumentSet getArticlesCorpus() throws ANoteException {
		if(documentSet==null)
		{
			documentSet = InitConfiguration.getDataAccess().getCorpusPublications(this);
		}
		return documentSet;
	}

	@JsonIgnore
	@Override
	public synchronized List<IIEProcess> getIEProcesses() throws ANoteException {
		if(processesList==null)
		{
			processesList = InitConfiguration.getDataAccess().getCorpusProcesses(this);
		}
		return processesList;
	}

	@JsonIgnore
	@Override
	public ICorpusStatistics getCorpusStatistics() throws ANoteException {
		return InitConfiguration.getDataAccess().getCorpusStatistics(this);
	}
	
	@JsonIgnore
	@Override
	public CorpusTextType getCorpusTextType() {
		return null;
	}
	
	public void freememory() {
		this.processesList=null;
		this.documentSet=null;
	}
	
	@JsonIgnore
	@Override
	public List<IIEProcess> getIEProcessesFilterByType(String type) throws ANoteException {
		List<IIEProcess> result = new ArrayList<IIEProcess>();
		for(IIEProcess process:getIEProcesses())
		{
			if(process.getType().getType().equals(type))
			{
				Boolean hasPermission = InitConfiguration.getDataAccess().hasPermission(process, Permissions.getWritegrant());
				if (hasPermission) {
					result.add(process);
				}
			}
		}
		return result;
	}
}
