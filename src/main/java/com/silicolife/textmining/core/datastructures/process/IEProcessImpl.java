package com.silicolife.textmining.core.datastructures.process;

import java.util.Date;
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
	private IProcessType type;
	@JsonDeserialize(as = ProcessOriginImpl.class)
	private IProcessOrigin processOrigin;
	private int version;
	private Date createDate;
	private Date updateDate;

	public IEProcessImpl() {
		super();
	}

	public IEProcessImpl(long id, ICorpus corpus, String name, String notes, IProcessType processType, IProcessOrigin processOrigin,int version, Properties properties, Date createDate,Date updateDate) {
		this.id = id;
		this.name = name;
		this.type = processType;
		this.processOrigin = processOrigin;
		this.properties = properties;
		this.corpus = corpus;
		this.version=version;
		this.createDate=createDate;
		this.updateDate=updateDate;
	}

	public IEProcessImpl(ICorpus corpus, String description, String notes, IProcessType type, IProcessOrigin origin, Properties properties) {
		this(GenerateRandomId.generateID(), corpus, description, notes, type, origin,1, properties,new Date(),new Date());
	}

	@Override
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
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
	@JsonDeserialize(as = ProcessTypeImpl.class)
	@Override
	public IProcessType getType() {
		return type;
	}

	@JsonSetter("processType")
	public void setType(IProcessType processType) {
		this.type = processType;
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
		if (this.id == process.getId()) {
			return 0;
		} else if (this.id < process.getId()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	@JsonIgnore
	public String toString() {
		String all = new String();
		all = getName() + "(" + getId()+ ") ";
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IEProcessImpl other = (IEProcessImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getVersion() {
		return version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
