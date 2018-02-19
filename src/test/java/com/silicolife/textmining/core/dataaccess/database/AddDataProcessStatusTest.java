package com.silicolife.textmining.core.dataaccess.database;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.DataProcessStatusEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.datastructures.general.DataProcessStatusImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.init.DatabaseConnectionInit;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public class AddDataProcessStatusTest {

	//@Test
	public void add() throws InvalidDatabaseAccess, ANoteException {
		DatabaseConnectionInit.init(DataBaseTypeEnum.H2Embedded,"./","3306","todelete","root","admin");
		
		DataProcessStatusEnum dpsStatus = DataProcessStatusEnum.start;
		ProcessStatusResourceTypesEnum resourcesType = ProcessStatusResourceTypesEnum.corpus;
		long uid = 1L;
		IUser authUsers = new AuthUsers(uid ,null,null,null,null,null,null,null);
		
		IDataProcessStatus dataprocessStatus = new DataProcessStatusImpl(0,12345678L, resourcesType, dpsStatus, null,
				0L, new Date(), new Date(), null, authUsers );
		InitConfiguration.getDataAccess().addDataProcessStatus(dataprocessStatus);
	}
	
//	@Test
	public void update() throws InvalidDatabaseAccess, ANoteException {
		DatabaseConnectionInit.init(DataBaseTypeEnum.H2Embedded,"./","3306","todelete","root","admin");
		
		DataProcessStatusEnum dpsStatus = DataProcessStatusEnum.running;
		ProcessStatusResourceTypesEnum resourcesType = ProcessStatusResourceTypesEnum.resources;
		long uid = 1L;
		IUser authUsers = new AuthUsers(uid ,null,null,null,null,null,null,null);
		int id = 1;
		IDataProcessStatus dataprocessStatus = new DataProcessStatusImpl(id,12345678L, resourcesType, dpsStatus, null,
				0L, new Date(), new Date(), null, authUsers );
		InitConfiguration.getDataAccess().updateDataProcessStatus(dataprocessStatus);
	}
	
	@Test
	public void getAllProcess() throws InvalidDatabaseAccess, ANoteException {
		DatabaseConnectionInit.init(DataBaseTypeEnum.H2Embedded,"./","3306","todelete","root","admin");
		
		List<IDataProcessStatus> processesStatus = InitConfiguration.getDataAccess().getAllDataProcessStatus();
		for(IDataProcessStatus processStatus:processesStatus)
			System.out.println(processStatus.toString());
	}


}
