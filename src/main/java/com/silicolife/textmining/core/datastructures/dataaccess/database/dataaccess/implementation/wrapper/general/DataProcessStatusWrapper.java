package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatusId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.datastructures.general.DataProcessStatusImpl;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

public class DataProcessStatusWrapper {

	public static IDataProcessStatus convertToAnoteStructure (DataProcessStatus dataProcessStatus) {
		if(dataProcessStatus ==null)
			return null;
		
		long id = dataProcessStatus.getId().getDpsDataObjectId();
		ProcessStatusResourceTypesEnum resourceType = ProcessStatusResourceTypesEnum.valueOf(dataProcessStatus.getId().getDpsTypeResource());
		DataProcessStatusEnum status = DataProcessStatusEnum.valueOf(dataProcessStatus.getDpsStatus());
		String report = dataProcessStatus.getDpsReport();
		float progress = dataProcessStatus.getDpsProgress();
		Date creationDate = dataProcessStatus.getDpsCreateDate();
		Date updateDate = dataProcessStatus.getDpsUpdateDate();
		
		IDataProcessStatus dataProcessStatus_ = new DataProcessStatusImpl(id, resourceType, status
				,report, progress, creationDate, updateDate);
		return dataProcessStatus_;
	}
	
	public static DataProcessStatus convertToDaemonStructure (IDataProcessStatus dataProcessStatus) {
		if(dataProcessStatus ==null)
			return null;
		
		long id = dataProcessStatus.getId();
		String resourceType = dataProcessStatus.getResourceType().name();
		DataProcessStatusId dataProcessStatusId = new DataProcessStatusId(id,resourceType);
		
		String status = dataProcessStatus.getStatus().name();
		String report = dataProcessStatus.getReport();
		float progress = dataProcessStatus.getProgress();
		Date creationDate = dataProcessStatus.getCreationDate();
		Date updateDate = dataProcessStatus.getUpdateDate();
		
		DataProcessStatus dataProcessStatus_ = new DataProcessStatus();
		return dataProcessStatus_;
	}
}
