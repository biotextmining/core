package com.silicolife.textmining.core.datastructures.process;

import java.util.Observable;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.process.ner.ResourcesToNerAnote;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;
import com.silicolife.textmining.core.interfaces.process.IProcessType;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

public class IEProcessImpl extends Observable implements IIEProcess {

	private long id;
	private String name;
	private String notes;
	private Properties properties;
	@JsonDeserialize(as = CorpusImpl.class)
	private ICorpus corpus;
	@JsonDeserialize(as = ProcessTypeImpl.class)
	private IProcessType processType;
	@JsonDeserialize(as = ProcessOriginImpl.class)
	private IProcessOrigin processOrigin;
	
	public IEProcessImpl() {
		super();
	}

	public IEProcessImpl(long id, ICorpus corpus, String name, String notes, IProcessType processType, IProcessOrigin processOrigin, Properties properties) {
		this.id = id;
		this.name = name;
		this.processType = processType;
		this.processOrigin = processOrigin;
		this.properties = properties;
		this.corpus = corpus;
	}

	public IEProcessImpl(ICorpus corpus, String description, String notes, IProcessType type, IProcessOrigin origin, Properties properties) {
		this(GenerateRandomId.generateID(), corpus, description, notes, type, origin, properties);
	}

	@JsonGetter("id")
	@Override
	public long getID() {
		return this.id;
	}
	
	@JsonSetter("id")
	public void setID(long id) {
		 this.id = id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		return this.properties;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public ICorpus getCorpus() {
		return corpus;
	}

	@Override
	public void setCorpus(ICorpus corpus) {
		this.corpus = corpus;
	}

	@JsonGetter("processType")
	@Override
	public IProcessType getType() {
		return processType;
	}

	@JsonSetter("processType")
	public void setType(IProcessType processType) {
		this.processType = processType;
	}

	@Override
	public IProcessOrigin getProcessOrigin() {
		return processOrigin;
	}

	public void setProcessOrigin(IProcessOrigin processOrigin) {
		this.processOrigin = processOrigin;
	}
	
	@JsonIgnore
	@Override
	public void stop() {
	}
	
	@JsonIgnore
	@Override
	public IIEProcessStatistics getStatistics() throws ANoteException {
		return InitConfiguration.getDataAccess().getIEProcessStatistics(this);
	}

	@JsonIgnore
	public int compareTo(IEProcessImpl process) {
		if (this.id == process.getID()) {
			return 0;
		} else if (this.id < process.getID()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	@JsonIgnore
	public String toString() {
		String all = new String();
		all = getName() + "(" + getID()+ ") ";
		return all;
	}

	@JsonIgnore
	protected void memoryAndProgress(int step, int total) {
		System.out.println((GlobalOptions.decimalformat.format((double) step / (double) total * 100)) + " %...");
		System.gc();
		System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");
	}

	@JsonIgnore
	protected void memoryAndProgressAndTime(int step, int total, long startTime) {
		System.out.println((GlobalOptions.decimalformat.format((double) step / (double) total * 100)) + " %...");
		System.gc();
		System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");
	}
	
	@JsonIgnore
	protected static Properties transformResourcesToOrderMapInProperties(ResourcesToNerAnote resources) {
		Properties prop = new Properties();
		for (int i = 0; i < resources.getList().size(); i++) {
			Set<Long> selected = resources.getList().get(i).getSelectedClassesID();
			long id = resources.getList().get(i).getResource().getId();
			{
				prop.put(String.valueOf(id), ResourceImpl.convertClassesToResourceProperties(selected));
			}
		}
		return prop;
	}
}
