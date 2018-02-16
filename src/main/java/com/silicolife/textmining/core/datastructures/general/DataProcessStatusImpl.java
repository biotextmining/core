package com.silicolife.textmining.core.datastructures.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

public class DataProcessStatusImpl implements IDataProcessStatus{
	
	private long id;
	private ProcessStatusResourceTypesEnum resourceType;
	private DataProcessStatusEnum status ;
	private String report;
	private float progress;
	private Date creationDate;
	private Date updateDate;
	
	public DataProcessStatusImpl () {
		super();
	}

	public DataProcessStatusImpl(long id, ProcessStatusResourceTypesEnum resourceType, DataProcessStatusEnum status,
			Date creationDate, Date updateDate) {
		super();
		this.id = id;
		this.resourceType = resourceType;
		this.status = status;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	public DataProcessStatusImpl(long id, ProcessStatusResourceTypesEnum resourceType, DataProcessStatusEnum status,
			String report, float progress, Date creationDate, Date updateDate) {
		super();
		this.id = id;
		this.resourceType = resourceType;
		this.status = status;
		this.report = report;
		this.progress = progress;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
	
	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public ProcessStatusResourceTypesEnum getResourceType() {
		return resourceType;
	}

	public void setResourceType(ProcessStatusResourceTypesEnum resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public DataProcessStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DataProcessStatusEnum status) {
		this.status = status;
	}

	@Override
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	@Override
	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	@Override
	public String toString() {
		return "DataProcessStatusImpl [id=" + id + ", resourceType=" + resourceType + ", status=" + status + ", report="
				+ report + ", progress=" + progress + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ "]";
	}
	
	
}
