package com.silicolife.textmining.core.datastructures.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public class DataProcessStatusImpl implements IDataProcessStatus{
	
	private int id;
	private long resourceObjectId;
	private ProcessStatusResourceTypesEnum resourceType;
	private DataProcessStatusEnum status ;
	private String report;
	private float progress;
	private Date creationDate;
	private Date updateDate;
	private Date finishedDate;
	private IUser user;
	
	public DataProcessStatusImpl () {
		super();
	}

	public DataProcessStatusImpl(int id,long resourceObjectId, ProcessStatusResourceTypesEnum resourceType, DataProcessStatusEnum status,
			String report,float progress,Date creationDate, Date updateDate,Date finishedDate,IUser user) {
		super();
		this.id = id;
		this.resourceObjectId=resourceObjectId;
		this.resourceType = resourceType;
		this.status = status;
		this.report=report;
		this.progress=progress;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.finishedDate=finishedDate;
		this.user=user;
	}

	public int getId() {
		return id;
	}

	public long getResourceId() {
		return resourceObjectId;
	}

	public ProcessStatusResourceTypesEnum getResourceType() {
		return resourceType;
	}

	public DataProcessStatusEnum getStatus() {
		return status;
	}

	public String getReport() {
		return report;
	}

	public float getProgress() {
		return progress;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public Date getFinishDate() {
		return finishedDate;
	}

	public IUser getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "DataProcessStatusImpl [id=" + id + ", resourceObjectId=" + resourceObjectId + ", resourceType="
				+ resourceType + ", status=" + status + ", report=" + report + ", progress=" + progress
				+ ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", finishedDate=" + finishedDate
				+ ", user=" + user + "]";
	}	
}
