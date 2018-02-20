package com.silicolife.textmining.core.interfaces.core.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public interface IDataProcessStatus {
	
	public int getId();

	public long getResourceId(); 
	
	public ProcessStatusResourceTypesEnum getResourceType();
	
	public DataProcessStatusEnum getStatus();

	public String getReport();
	
	public float getProgress();	
	
	public Date getCreationDate();
	
	public Date getUpdateDate();

	public Date getFinishDate();
	
	public IUser getUser();

	public void setId(int id);

	public void setUpdateDate(Date updateDate);

	public void setProgress(float progress);

	public void setReport(String report);

	public void setStatus(DataProcessStatusEnum status);

	public void setResourceType(ProcessStatusResourceTypesEnum resourceType);

	public void setFinishedDate(Date finishedDate);

	public Date getFinishedDate();

	public long getResourceObjectId();

}
