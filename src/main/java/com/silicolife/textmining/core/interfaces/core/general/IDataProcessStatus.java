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

}
