package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ResourcesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourcesWrapper;
import com.silicolife.textmining.core.datastructures.resources.ResourcesLuceneFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

@Service
@Transactional(readOnly = true)
public class ResourcesLuceneServiceImpl implements IResourcesLuceneService {
	
	private ResourcesManagerDao resourcesManagerDao;
	private ResourcesLuceneManagerDao resourcesLuceneManagerDao;
	private UsersLogged user;
	private UsersManagerDao usersManagerDao;
	
	
	public ResourcesLuceneServiceImpl(ResourcesManagerDao resourcesManagerDao ,
			ResourcesLuceneManagerDao resourcesLuceneManagerDao, UsersLogged user, UsersManagerDao usersManagerDao ) {
		this.resourcesLuceneManagerDao = resourcesLuceneManagerDao;
		this.resourcesManagerDao = resourcesManagerDao;
		this.user = user;
		this.usersManagerDao = usersManagerDao;
	}
	
	/*
	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Resources> listResources = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findMixedByAttributesWAuth(eqSentenceOnField, eqMustSentenceOnField, permissionFields,idField );
			else
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findMixedByAttributesWKeywordsWAuth(eqSentenceOnField, eqMustSentenceOnField, permissionFields,idField );
		}
		else{
			if(searchProperties.isWholeWords())
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findNotExactByAttributesWAuth(eqSentenceOnField, permissionFields,idField );
			else
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findNotExactByAttributesWKeywordsWAuth(eqSentenceOnField, permissionFields,idField );
		}
		

		return listResources.size();
	}*/
	
	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Resources> listResources = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findMixedByAttributesPaginatedWAuth(eqSentenceOnField, eqMustSentenceOnField,permissionFields,idField, index, paginationSize);
			else
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findMixedByAttributesWKeywordsPaginatedWAuth(eqSentenceOnField, eqMustSentenceOnField,permissionFields,idField, index, paginationSize );
		}
		else{
			if(searchProperties.isWholeWords())
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findNotExactByAttributesPaginatedWAuth(eqSentenceOnField,permissionFields,idField, index, paginationSize );
			else
				listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findNotExactByAttributesWKeywordsPaginatedWAuth(eqSentenceOnField,permissionFields,idField, index, paginationSize);
		}
		
		List<IResource<IResourceElement>> listResources_ = new ArrayList<IResource<IResourceElement>>();
		for ( Resources resource : listResources) {
			IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
			listResources_.add(resource_);
		}

		return listResources_;
	}
	
	
	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy, String permission){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		permissionFields.put("audoPermission", permission);
		
		String sortField = ResourcesLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = ResourcesLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Resources> listResources = null;
		
		listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findForWebTableWPermissionsPaginated(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IResource<IResourceElement>> listResources_ = new ArrayList<IResource<IResourceElement>>();
		for ( Resources resource : listResources) {
			IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
			listResources_.add(resource_);
		}

		return listResources_;
		
	}
	
	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		
		String sortField = ResourcesLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = ResourcesLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Resources> listResources = null;
		
		listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findForWebTableWPermissionsPaginated(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IResource<IResourceElement>> listResources_ = new ArrayList<IResource<IResourceElement>>();
		for ( Resources resource : listResources) {
			IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
			listResources_.add(resource_);
		}

		return listResources_;
		
	}
	
	@Override
	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		String sortField = ResourcesLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = ResourcesLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Resources> listResources = null;
		
		listResources = resourcesLuceneManagerDao.getResourcesLuceneDao().findForWebTablePaginated(
				eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<IResource<IResourceElement>> listResources_ = new ArrayList<IResource<IResourceElement>>();
		for ( Resources resource : listResources) {
			IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
			listResources_.add(resource_);
		}

		return listResources_;
		
	}
	
	
	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		permissionFields.put("audoPermission", permission);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		return resourcesLuceneManagerDao.getResourcesLuceneDao().countForWebTableWPermissions(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	
	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resoId";
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		return resourcesLuceneManagerDao.getResourcesLuceneDao().countForWebTableWPermissions(
				eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords());
	}
	
	
	@Override
	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties){
		
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("resoActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(ResourcesLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		return resourcesLuceneManagerDao.getResourcesLuceneDao().countForWebTable(
				eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords());
	}
	
	
	
	@Override
	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
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
			return this.getActiveResourcesFromSearchPaginatedWSort(searchProperties,paginationIndex, paginationSize, asc, sortBy);
		else
			return this.getResourcesFromSearchPaginatedWAuthAndSort( searchProperties, paginationIndex, paginationSize, asc, sortBy, "owner");

	}
	
	@Override
	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties) {
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
			return this.countActiveResourcesFromSearch(searchProperties);
		else
			return this.countResourcesFromSearchWAuth( searchProperties ,"owner");

	}
	
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
	
}
