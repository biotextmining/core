package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.datastructures.general.DataProcessStatusImpl;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public class DataProcessStatusWrapper {

	public static IDataProcessStatus convertToAnoteStructure (DataProcessStatus dataProcessStatus) {
		if(dataProcessStatus ==null)
			return null;

		int id = dataProcessStatus.getDpsId();
		long resourceDataObjectId = dataProcessStatus.getDpsDataObjectId();
		ProcessStatusResourceTypesEnum resourceType = ProcessStatusResourceTypesEnum.valueOf(dataProcessStatus.getDpsTypeResource());
		DataProcessStatusEnum status = DataProcessStatusEnum.valueOf(dataProcessStatus.getDpsStatus());
		String report = dataProcessStatus.getDpsReport();
		float progress = dataProcessStatus.getDpsProgress();
		Date creationDate = dataProcessStatus.getDpsCreateDate();
		Date updateDate = dataProcessStatus.getDpsUpdateDate();
		Date finishedDate = dataProcessStatus.getDpsFinishDate();
		AuthUsers user = dataProcessStatus.getAuthUsers();
		IDataProcessStatus dataProcessStatus_ = new DataProcessStatusImpl(id,resourceDataObjectId, resourceType, status
				,report, progress, creationDate, updateDate,finishedDate,user);
		return dataProcessStatus_;
	}

	public static DataProcessStatus convertToDaemonStructure (IDataProcessStatus dataProcessStatus_) {
		if(dataProcessStatus_ ==null)
			return null;
		DataProcessStatus dataProcessStatus = new DataProcessStatus();
		int id = dataProcessStatus_.getId();
		dataProcessStatus.setDpsId(id);
		long resourceObjectId = dataProcessStatus_.getResourceId();	
		dataProcessStatus.setDpsDataObjectId(resourceObjectId);
		String resourceType = dataProcessStatus_.getResourceType().name();
		dataProcessStatus.setDpsTypeResource(resourceType);
		String status = dataProcessStatus_.getStatus().name();
		dataProcessStatus.setDpsStatus(status);
		String report = dataProcessStatus_.getReport();
		dataProcessStatus.setDpsReport(report);
		float progress = dataProcessStatus_.getProgress();
		dataProcessStatus.setDpsProgress(progress);
		Date creationDate = dataProcessStatus_.getCreationDate();
		dataProcessStatus.setDpsCreateDate(creationDate);
		Date updateDate = dataProcessStatus_.getUpdateDate();
		dataProcessStatus.setDpsUpdateDate(updateDate);
		Date finishedDate = dataProcessStatus_.getFinishDate();
		dataProcessStatus.setDpsFinishDate(finishedDate);
		IUser user = dataProcessStatus_.getUser();
		AuthUsers authuser = null;
		if(user!=null)
		{
			authuser = new AuthUsers();
			authuser.setAuId(user.getAuId());
		}
		dataProcessStatus.setAuthUsers(authuser);
		return dataProcessStatus;
	}
}
