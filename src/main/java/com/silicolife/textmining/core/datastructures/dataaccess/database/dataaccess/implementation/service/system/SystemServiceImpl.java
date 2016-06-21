package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.SystemManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Versions;

public class SystemServiceImpl implements ISystemService{
	
	private SystemManagerDao systemDao;
	
	@Autowired
	public SystemServiceImpl(SystemManagerDao systemDao) {
		this.systemDao = systemDao;

	}

	
	@Override
	@Transactional(readOnly = false)
	public int getDataversion() {
		List<Versions> versionList = systemDao.getVersionsDao().findAll();
		if(versionList.isEmpty())
			return 1;
		Versions version = versionList.get(0);
		for(Versions versionCadidate:versionList)
		{
			if(versionCadidate.getVerVersion()>version.getVerVersion())
			{
				version = versionCadidate;
			}
		}
		return (int) version.getVerVersion();
	}


	@Override
	public void addDatabaseVersion(int newversion, String commments) {
		Date currentDate = new Date();
		Versions version = new Versions(newversion,currentDate,commments);
		systemDao.getVersionsDao().save(version);
		
	}

}
