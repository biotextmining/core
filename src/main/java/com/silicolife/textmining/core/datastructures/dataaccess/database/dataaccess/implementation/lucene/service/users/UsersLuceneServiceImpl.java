package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.UsersLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneIndexFields;
import com.silicolife.textmining.core.datastructures.general.UsersLuceneFields;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

@Service
@Transactional(readOnly = true)
public class UsersLuceneServiceImpl implements IUsersLuceneService {
	
	private UsersManagerDao usersManagerDao;
	private UsersLuceneManagerDao usersLuceneManagerDao;
	private UsersLogged user;
	
	public UsersLuceneServiceImpl(UsersManagerDao usersManagerDao, UsersLuceneManagerDao usersLuceneManagerDao,
			UsersLogged user) {
		this.usersManagerDao = usersManagerDao;
		this.usersLuceneManagerDao = usersLuceneManagerDao;
		this.user = user;
	}
	
	
	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissionsFromSearchPaginated(ISearchProperties searchProperties,Long resourceId, String resource, int index, int paginationSize,boolean asc, String sortBy ) {

		List<IGenericPair<IUser, String>> list = new ArrayList<IGenericPair<IUser, String>>();
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		String sortField = UsersLuceneFields.getSortField(sortBy);
		
		SortField.Type sortType = UsersLuceneFields.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(UsersLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}

		for(String key : filters.keySet()){
			eqFilters.put(UsersLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		if(searchProperties.getValue()!=null) {
		for(String field : fields){
			eqSentenceOnField.put(UsersLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		}
		
		List<AuthUsers> searchUsers = usersLuceneManagerDao.getUsersLuceneDao().findForWebTablePaginated(eqSentenceOnField, eqMustSentenceOnField, eqFilters,searchProperties.isWholeWords(), index, paginationSize, sortField, sortType, asc);
		for (AuthUsers user : searchUsers) {
			AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(user.getAuId(), resourceId, resource);
			AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
			if (dataObject != null) {
				list.add(new GenericPairImpl<IUser, String>(user, dataObject.getAudoPermission()));
			} else {
				list.add(new GenericPairImpl<IUser, String>(user, null));
			}
		}
		return list;
	}
	
	@Override
	public Integer countUsersFromSearch(ISearchProperties searchProperties) {

		List<IGenericPair<IUser, String>> list = new ArrayList<IGenericPair<IUser, String>>();
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(UsersLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}

		for(String key : filters.keySet()){
			eqFilters.put(UsersLuceneFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		if(searchProperties.getValue()!=null) {
		for(String field : fields){
			eqSentenceOnField.put(UsersLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		}
		
		return usersLuceneManagerDao.getUsersLuceneDao().countForWebTable(eqSentenceOnField, eqMustSentenceOnField, eqFilters, searchProperties.isWholeWords());
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
	
}
