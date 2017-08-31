package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourcesWrapper;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

@Service
@Transactional(readOnly = true)
public class ResourcesServiceImpl implements IResourcesService {

	private ResourcesManagerDao resourcesManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	private final static String resourcesStr = ResourcesTypeUtils.resources.getName();

	@Autowired
	public ResourcesServiceImpl(ResourcesManagerDao resourcesManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.resourcesManagerDao = resourcesManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesByType(String type) throws ResourcesExceptions {

		ResourceTypes resourceType = resourcesManagerDao.getResourceTypeDao().findUniqueByAttribute("rtyResourceType", type);
		if (resourceType == null) {
			return new ArrayList<IResource<IResourceElement>>();
			// throw new ResourcesExceptions("default", "default");
		}

		List<IResource<IResourceElement>> resources_ = new ArrayList<IResource<IResourceElement>>();
		long userId = userLogged.getCurrentUserLogged().getAuId();
		List<Resources> resources = resourcesManagerDao.getResourcesAuxDao().findResourcesByAttributes(userId, resourceType.getRtyId(), ResourcesTypeUtils.resources.getName());

		for (Resources resource : resources) {
			if(resource.isResoActive())
			{
				IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
				resources_.add(resource_);
			}
		}
		return resources_;

	}

	@Override
	public IResource<IResourceElement> getResourcesById(Long id) {

		Resources resource = resourcesManagerDao.getResourceDao().findById(id);
		if (resource == null)
			return null;

		IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);

		return resource_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(IResource<IResourceElement> resource_) {

		AuthUsers user = userLogged.getCurrentUserLogged();

		Resources resources = ResourcesWrapper.convertToDaemonStructure(resource_);
		ResourceTypes resourceType = resourcesManagerDao.getResourceTypeDao().findUniqueByAttribute("rtyResourceType", resources.getResourceTypes().getRtyResourceType());
		if (resourceType == null) {
			resourcesManagerDao.getResourceTypeDao().save(resources.getResourceTypes());
		} else {
			resources.setResourceTypes(resourceType);
		}

		resourcesManagerDao.getResourceDao().save(resources);

		AuthUserDataObjectsId dataObjectUserId = new AuthUserDataObjectsId(user.getAuId(), resources.getResoId(), resourcesStr);
		AuthUserDataObjects dataObjectUser = new AuthUserDataObjects(dataObjectUserId, user, "owner");
		usersManagerDao.getAuthUserDataObjectsDao().save(dataObjectUser);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "resources/resource_types/auth_user_data_objects", null, "create resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(IResource<IResourceElement> resource_) throws ResourcesExceptions {

		Resources resource = resourcesManagerDao.getResourceDao().findById(resource_.getId());
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Resources resourcesNew = ResourcesWrapper.convertToDaemonStructure(resource_);
		ResourceTypes resourceType = resourcesManagerDao.getResourceTypeDao().findUniqueByAttribute("rtyResourceType", resourcesNew.getResourceTypes().getRtyResourceType());
		if (resourceType == null) {
			throw new ResourcesExceptions("default", "default");
		} else {
			resourcesNew.setResourceTypes(resourceType);
		}

		resourcesManagerDao.getResourceDao().evict(resource);
		resourcesManagerDao.getResourceDao().update(resourcesNew);

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public List<IResource<IResourceElement>> getAllPrivilegesResourcesAdminAccess() throws ResourcesExceptions {

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

		List<Resources> listResources = null;
		if (isAdmin)
			listResources = resourcesManagerDao.getResourceDao().findAll();
		else
			listResources = resourcesManagerDao.getResourcesAuxDao().findResourcesByAttributes(user.getAuId(), resourcesStr, "owner");

		List<IResource<IResourceElement>> listResources_ = new ArrayList<IResource<IResourceElement>>();
		for (Resources resource : listResources) {
			if(resource.isResoActive())
			{
				IResource<IResourceElement> resource_ = ResourcesWrapper.convertToAnoteStructure(resource);
				listResources_.add(resource_);
			}
		}

		return listResources_;
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}
}
