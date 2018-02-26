package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.DataProcessStatusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.DataProcessStatusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.DataProcessStatusWrapper;
import com.silicolife.textmining.core.datastructures.general.DataProcessStatusFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

@Service
@Transactional(readOnly = true)
public class DataProcessStatusServiceImpl implements IDataProcessStatusService {
	
	final static Logger creationLogger = LoggerFactory.getLogger(DataProcessStatus.class);
	
	private DataProcessStatusManagerDao dataProcessStatusManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;
	
	//Constant in days that is used to decreased to today's date.
	private static final int  SIGDATERANGE = 7;
	
	@Autowired
	public DataProcessStatusServiceImpl(DataProcessStatusManagerDao dataProcessStatusManagerDao,
			UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		super();
		this.dataProcessStatusManagerDao = dataProcessStatusManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void addDataProcessStatus(IDataProcessStatus dataProcessStatus_) {	
		AuthUsers user = this.userLogged.getCurrentUserLogged();
		DataProcessStatus dataProcessStatus = DataProcessStatusWrapper.convertToDaemonStructure(dataProcessStatus_);
		dataProcessStatus.setAuthUsers(user);
		this.dataProcessStatusManagerDao.getDataProcessStatusDao().save(dataProcessStatus);
		dataProcessStatus_.setId(dataProcessStatus.getDpsId());
	}

	@Transactional(readOnly = false)
	@Override
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws DataProcessStatusException {
		DataProcessStatus dataProcess = this.dataProcessStatusManagerDao.getDataProcessStatusDao().findById(dataProcessStatus.getId());
		if(dataProcess==null)
			throw new DataProcessStatusException(ExceptionsCodes.codeNoDataProcessStatus,ExceptionsCodes.msgNoDataProcessStatus);
		dataProcess.setDpsFinishDate(dataProcessStatus.getFinishDate());
		dataProcess.setDpsUpdateDate(dataProcessStatus.getUpdateDate());
		dataProcess.setDpsProgress(dataProcessStatus.getProgress());
		dataProcess.setDpsReport(dataProcessStatus.getReport());
		dataProcess.setDpsStatus(dataProcessStatus.getStatus().name());
		this.dataProcessStatusManagerDao.getDataProcessStatusDao().saveOrUpdate(dataProcess);		
	}

	@Override
	public List<IDataProcessStatus> getAllDataProcessStatus() {
		List<IDataProcessStatus> out = new ArrayList<>();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusDao().findAll();
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public IDataProcessStatus getDataProcessStatusById(int id) {
		DataProcessStatus data = this.dataProcessStatusManagerDao.getDataProcessStatusDao().findById(id);
		
		return DataProcessStatusWrapper.convertToAnoteStructure(data);
	}
	
	
	@Override
	public List<IDataProcessStatus> getUserDataProcessStatus() {
		List<IDataProcessStatus> out = new ArrayList<>();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUser(userId);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getUserRecentDataProcessStatus() {
		List<IDataProcessStatus> out = new ArrayList<>();
		Date to = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SIGDATERANGE);
		Date from = cal.getTime();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithDateRange(userId, from, to);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getUserRecentDataProcessStatusSorted() {
		List<IDataProcessStatus> out = new ArrayList<>();
		Date to = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SIGDATERANGE);
		Date from = cal.getTime();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		boolean asc = false;
		String sortBy = DataProcessStatusFieldsEnum.id.toString();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithDateRangeAndSort(userId, from, to, asc, sortBy);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedOfType(ProcessStatusResourceTypesEnum type) {
		List<IDataProcessStatus> out = new ArrayList<>();
		Date to = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SIGDATERANGE);
		Date from = cal.getTime();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		boolean asc = false;
		String sortBy = DataProcessStatusFieldsEnum.id.toString();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithDateRangeAndSortByType(type,userId, from, to, asc, sortBy);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedOfTypeWLimit(ProcessStatusResourceTypesEnum type,Integer paginationSize) {
		List<IDataProcessStatus> out = new ArrayList<>();
		Date to = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SIGDATERANGE);
		Date from = cal.getTime();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		boolean asc = false;
		String sortBy = DataProcessStatusFieldsEnum.id.toString();
		Integer paginationIndex = 0;
		
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithDateRangeAndSortByTypePaginated(type,userId, from, to, paginationIndex, paginationSize, asc, sortBy);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedWLimit(Integer paginationSize) {
		List<IDataProcessStatus> out = new ArrayList<>();
		Date to = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -SIGDATERANGE);
		Date from = cal.getTime();
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		boolean asc = false;
		String sortBy = DataProcessStatusFieldsEnum.id.toString();
		Integer paginationIndex = 0;
		
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithDateRangeAndSortPaginated(userId, from, to, paginationIndex, paginationSize, asc, sortBy);
		
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}
	
	@Override
	public List<IDataProcessStatus> getAdminPrivilegesProcessStatusByTypeSortedPaginated(
			ProcessStatusResourceTypesEnum type,Integer paginationIndex,Integer paginationSize, boolean asc, String sortBy) {
		if(DataProcessStatusFieldsEnum.valueOf(sortBy).equals(DataProcessStatusFieldsEnum.none)) {
			sortBy = DataProcessStatusFieldsEnum.id.toString();
		}
		
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}
		
		List<DataProcessStatus> dataProcessesStatusInDatabase = null;
		if (isAdmin)
			dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusWithSortPaginated(type, paginationIndex, paginationSize, asc, sortBy);
		else {
			Long userId = user.getAuId();
			dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithSortPaginated(type, userId, paginationIndex, paginationSize, asc, sortBy);
		}
		
		List<IDataProcessStatus> out = new ArrayList<>();
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
		
	}
	
	@Override
	public Integer countAdminPrivilegesProcessStatusByType(
			ProcessStatusResourceTypesEnum type) {
		
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}
		
		if (isAdmin)
			return this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().countDataProcessStatus(type);
		else {
			Long userId = user.getAuId();
			return this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().countDataProcessStatusforUser(userId, type);
		}
		
		
	}
	
	@Override
	public List<IDataProcessStatus> getAdminPrivilegesProcessStatusSortedPaginated(Integer paginationIndex,Integer paginationSize, boolean asc, String sortBy) {
		if(DataProcessStatusFieldsEnum.valueOf(sortBy).equals(DataProcessStatusFieldsEnum.none)) {
			sortBy = DataProcessStatusFieldsEnum.id.toString();
		}
		
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}
		
		List<DataProcessStatus> dataProcessesStatusInDatabase = null;
		if (isAdmin)
			dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusWithSortPaginated( paginationIndex, paginationSize, asc, sortBy);
		else {
			Long userId = user.getAuId();
			dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().findDataProcessStatusForUserWithSortPaginated( userId, paginationIndex, paginationSize, asc, sortBy);
		}
		
		List<IDataProcessStatus> out = new ArrayList<>();
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
		
	}
	
	@Override
	public Integer countAdminPrivilegesProcessStatus() {
		
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}
		
		if (isAdmin)
			return this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().countDataProcessStatus();
		else {
			Long userId = user.getAuId();
			return this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().countDataProcessStatusforUser(userId);
		}
		
		
	}
	
	@Override
	public Integer countRunningOrStartDataProcessStatusforUser() {
		Long userId = this.userLogged.getCurrentUserLogged().getAuId();
		return this.dataProcessStatusManagerDao.getDataProcessStatusAuxDao().countRunningOrStartDataProcessStatusforUser(userId);
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}


}
