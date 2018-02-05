package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.processes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ProcessesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.QueriesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ProcessesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process.ProcessWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapper;
import com.silicolife.textmining.core.datastructures.documents.query.QueriesLuceneFields;
import com.silicolife.textmining.core.datastructures.process.ProcessLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

@Service
@Transactional(readOnly = true)
public class ProcessesLuceneServiceImpl implements IProcessesLuceneService{
	
	private ProcessesManagerDao processesManagerDao;
	private ProcessesLuceneManagerDao processesLuceneManagerDao;
	private UsersLogged user;
	private UsersManagerDao usersManagerDao;
	
	

	public ProcessesLuceneServiceImpl(
			ProcessesLuceneManagerDao processesLuceneManagerDao, ProcessesManagerDao processesManagerDao, 
			UsersLogged user, UsersManagerDao usersManagerDao) {
		this.processesManagerDao = processesManagerDao;
		this.processesLuceneManagerDao = processesLuceneManagerDao;
		this.user = user;
		this.usersManagerDao = usersManagerDao;
	}
	
	@Override
	public List<IIEProcess>  getProcessesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "proId";
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "processes");
		
		String sortField = ProcessLuceneFields.getSortField(sortBy);
		
		SortField.Type sortType = ProcessLuceneFields.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Processes> listProcesses = null;
		
		listProcesses = processesLuceneManagerDao.getProcessesLuceneDao().findForWebTableWPermissionsPaginated(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
		}
		return listProcesses_;
		
	}
	
	@Override
	public List<IIEProcess>  getProcessesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, String permission, int index, int paginationSize, boolean asc, String sortBy){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "proId";
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "processes");
		permissionFields.put("audoPermission", permission);
		
		String sortField = ProcessLuceneFields.getSortField(sortBy);
		
		SortField.Type sortType = ProcessLuceneFields.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Processes> listProcesses = null;
		
		listProcesses = processesLuceneManagerDao.getProcessesLuceneDao().findForWebTableWPermissionsPaginated(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
		}
		return listProcesses_;
		
	}
	
	@Override
	public Integer countProcessesFromSearchWAuth(ISearchProperties searchProperties){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "proId";
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "processes");
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		
		return processesLuceneManagerDao.getProcessesLuceneDao().countForWebTableWPermissions(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	@Override
	public Integer countProcessesFromSearchWAuth(ISearchProperties searchProperties, String permission){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "proId";
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "processes");
		permissionFields.put("audoPermission", permission);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		
		return processesLuceneManagerDao.getProcessesLuceneDao().countForWebTableWPermissions(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	
	@Override
	public List<IIEProcess>  getActiveProcessesFromSearchPaginatedWSort(ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		String sortField = ProcessLuceneFields.getSortField(sortBy);
		
		SortField.Type sortType = ProcessLuceneFields.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Processes> listProcesses = null;
		
		listProcesses = processesLuceneManagerDao.getProcessesLuceneDao().findForWebTablePaginated(
				eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IIEProcess> listProcesses_ = new ArrayList<IIEProcess>();
		for (Processes process : listProcesses) {
			
				IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
				listProcesses_.add(process_);
		}
		return listProcesses_;
		
	}
	
	@Override
	public Integer  countActiveProcessesFromSearch(ISearchProperties searchProperties){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("proActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ProcessLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}

		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ProcessLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		return processesLuceneManagerDao.getProcessesLuceneDao().countForWebTable(
				eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	
	@Override
	public List<IIEProcess> getPrivilegesProcessesAdminAccessFromSearchPaginated(ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = this.user.getCurrentUserLogged();
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
			return this.getActiveProcessesFromSearchPaginatedWSort(searchProperties,paginationIndex, paginationSize, asc, sortBy);
		else
			return this.getProcessesFromSearchPaginatedWAuthAndSort(searchProperties,"owner",paginationIndex, paginationSize, asc, sortBy);

	}
	
	@Override
	public Integer countPrivilegesProcessesAdminAccessFromSearch(ISearchProperties searchProperties) {
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = this.user.getCurrentUserLogged();
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
			return this.countActiveProcessesFromSearch(searchProperties);
		else
			return this.countProcessesFromSearchWAuth(searchProperties,"owner");

	}
	
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
}
