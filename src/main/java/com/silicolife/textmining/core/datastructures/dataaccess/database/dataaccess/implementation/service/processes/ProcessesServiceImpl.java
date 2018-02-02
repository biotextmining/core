package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ProcessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.AnnotationManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ProcessesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessOrigins;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process.ProcessWrapper;
import com.silicolife.textmining.core.datastructures.process.IEProcessStatisticsImpl;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

@Service
@Transactional(readOnly = true)
public class ProcessesServiceImpl implements IProcessesService {

	private ProcessesManagerDao processesManagerDao;
	private AnnotationManagerDao annotationsManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;
	
	private final static String processStr = ResourcesTypeUtils.processes.getName();


	@Autowired
	public ProcessesServiceImpl(ProcessesManagerDao processesManagerDao,AnnotationManagerDao annotationsManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.processesManagerDao = processesManagerDao;
		this.annotationsManagerDao=annotationsManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createIEProcess(IIEProcess processes_) {
		
		AuthUsers user = userLogged.getCurrentUserLogged();

		
		Processes processes = ProcessWrapper.convertToDaemonStructure(processes_);
		/*
		 * create processes dependencies
		 */
		ProcessTypes processesType = processes.getProcessTypes();

		ProcessTypes processType = processesManagerDao.getProcessTypesDao().findUniqueByAttribute("ptProcessType", processesType.getPtProcessType());
		if (processType == null)
			processesManagerDao.getProcessTypesDao().save(processesType);
		else
			processes.setProcessTypes(processType);
		
		ProcessOrigins processesOrigin = processes.getProcessOrigins();
		ProcessOrigins processOrig = processesManagerDao.getProcessOriginsDao().findUniqueByAttribute("poDescription", processesOrigin.getPoDescription());
		if (processOrig == null)
			processesManagerDao.getProcessTypesDao().save(processesOrigin);
		else
			processes.setProcessOrigins(processOrig);
		
		processesManagerDao.getProcessesDao().save(processes);

		Set<ProcessProperties> processesPropertiess = processes.getProcessPropertieses();
		for (ProcessProperties processesProperty : processesPropertiess) {
			processesManagerDao.getProcessPropertiesDao().save(processesProperty);
		}
		
		
		AuthUserDataObjectsId dataObjectUserId = new AuthUserDataObjectsId(user.getAuId(), processes.getProId(), processStr);
		AuthUserDataObjects dataObjectUser = new AuthUserDataObjects(dataObjectUserId, user, PermissionsUtilsEnum.owner.getName());
		usersManagerDao.getAuthUserDataObjectsDao().save(dataObjectUser);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "processes/process_origins/process_types/process_properties", null, "create processes");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateIEProcess(IIEProcess processes_) throws ProcessException {
		Processes processs = processesManagerDao.getProcessesDao().findById(processes_.getId());
		if (processs == null)
			throw new ProcessException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		processs.setProName(processes_.getName());
		processs.setProNotes(processes_.getNotes());
		processs.setProVersion(processes_.getVersion());
		processs.setProUpdateDate(processes_.getUpdateDate());
		processesManagerDao.getProcessesDao().update(processs);
		/*
		 * log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "processes", null, "update processes");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public IIEProcess getProcessByID(Long processID) throws ProcessException {
		Processes processs = processesManagerDao.getProcessesDao().findById(processID);
		if (processs == null)
			throw new ProcessException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		IIEProcess processes_ = ProcessWrapper.convertToAnoteStructure(processs);
		return processes_;
	}

	@Override
	public IIEProcessStatistics getProcessStatistics(long processID) throws ProcessException {
		Processes processs = processesManagerDao.getProcessesDao().findById(processID);
		if (processs == null)
			throw new ProcessException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		int entitiesSize = annotationsManagerDao.getAnnotationAuxDao().getEntitieSize(processs);
		int relationsSize = annotationsManagerDao.getAnnotationAuxDao().getRelationSize(processs);
		IIEProcessStatistics statistics= new IEProcessStatisticsImpl(entitiesSize, relationsSize);
		Map<Classes, Integer> mapCLassNumberOfOcurrences = annotationsManagerDao.getAnnotationAuxDao().getProcessProcessClassStatistics(processID);
		Map<IAnoteClass, Integer> mapAnoteClassNumberOFOccurrences = new HashMap<IAnoteClass, Integer>();
		for(Classes klass:mapCLassNumberOfOcurrences.keySet())
		{
			IAnoteClass anoteKlass = ClassesWrapper.convertToAnoteStructure(klass);
			mapAnoteClassNumberOFOccurrences.put(anoteKlass, mapCLassNumberOfOcurrences.get(klass));
		}
		statistics.setClassesNumberOfOcurrences(mapAnoteClassNumberOFOccurrences);
		return statistics;
	}
	
	@Override
	public Integer countAllProcesses() {
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);
		
		List<Processes> listProcesses = null;
		listProcesses = processesManagerDao.getProcessesAuxDao().findAllProcesses(user.getAuId(), processStr);
		
		return listProcesses.size();
	}
	
	@Override
	public List<IIEProcess> getAllProcessesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);
		
		List<Processes> listProcesses = null;
		listProcesses = processesManagerDao.getProcessesAuxDao().findAllProcessesPaginated(user.getAuId(), processStr, paginationIndex, paginationSize, asc, sortBy);
		
		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
		}
		return listProcesses_;
	}
	
	@Override
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccessPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
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

		List<Processes> listProcesses = null;
		if (isAdmin)
			listProcesses = processesManagerDao.getProcessesAuxDao().findAllActiveProcessesPaginated(paginationIndex, paginationSize, asc, sortBy);
		else
			listProcesses = processesManagerDao.getProcessesAuxDao().findProcessesByAttributesPaginated(user.getAuId(), processStr, PermissionsUtilsEnum.owner.getName(),paginationIndex, paginationSize, asc, sortBy);

		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			if(process.isProActive())
			{
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
			}
		}
		return listProcesses_;
	}
	
	@Override
	public Integer countPrivilegesAllProcessesAdminAccess() {
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
			 return processesManagerDao.getProcessesAuxDao().countAllActiveProcesses();
		else
			return processesManagerDao.getProcessesAuxDao().countProcessesByAttributes(user.getAuId(), processStr, PermissionsUtilsEnum.owner.getName());
	}

	@Override
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() {
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

		List<Processes> listProcesses = null;
		if (isAdmin)
			listProcesses = processesManagerDao.getProcessesDao().findAll();
		else
			listProcesses = processesManagerDao.getProcessesAuxDao().findProcessesByAttributes(user.getAuId(), processStr, PermissionsUtilsEnum.owner.getName());

		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			if(process.isProActive())
			{
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
			}
		}
		return listProcesses_;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public List<IIEProcess> getProcessesByPublicationId(Long publicationId) throws ProcessException  {

		List<Processes> listProcesses = processesManagerDao.getProcessesAuxDao().findProcessesByPublicationIds(publicationId);
		
		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			if(process.isProActive())
			{
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
			}
		}

		return listProcesses_;
	}
	
}
