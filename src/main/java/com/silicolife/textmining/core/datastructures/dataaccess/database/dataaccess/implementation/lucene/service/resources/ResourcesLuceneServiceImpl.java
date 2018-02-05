package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ResourcesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourcesWrapper;
import com.silicolife.textmining.core.datastructures.documents.query.QueriesLuceneFields;
import com.silicolife.textmining.core.datastructures.resources.ResourcesLuceneFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
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
	}
	
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
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
	
}
