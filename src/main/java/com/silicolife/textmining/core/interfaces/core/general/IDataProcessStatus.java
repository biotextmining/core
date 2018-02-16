package com.silicolife.textmining.core.interfaces.core.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;

public interface IDataProcessStatus {

	public Date getUpdateDate();

	public Date getCreationDate();

	public float getProgress();

	public String getReport();

	public DataProcessStatusEnum getStatus();

	public ProcessStatusResourceTypesEnum getResourceType();

	public long getId();

}
