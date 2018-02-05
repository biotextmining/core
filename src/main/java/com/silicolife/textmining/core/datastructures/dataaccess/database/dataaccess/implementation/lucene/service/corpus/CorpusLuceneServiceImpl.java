package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.corpus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.CorpusLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusLuceneFieldsEnum;
import com.silicolife.textmining.core.datastructures.process.ProcessLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;


@Service
@Transactional(readOnly = true)
public class CorpusLuceneServiceImpl implements ICorpusLuceneService {
	
	private CorpusManagerDao corpusManagerDao;
	private CorpusLuceneManagerDao corpusLuceneManagerDao;
	private UsersLogged user;
	private UsersManagerDao usersManagerDao;
	
	public CorpusLuceneServiceImpl(CorpusManagerDao corpusManagerDao, CorpusLuceneManagerDao corpusLuceneManagerDao
			, UsersLogged user, UsersManagerDao usersManagerDao) {
		this.corpusManagerDao= corpusManagerDao;
		this.corpusLuceneManagerDao= corpusLuceneManagerDao;
		this.user = user;
		this.usersManagerDao = usersManagerDao;
	}
	
	
	@Override
	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();

		eqMustSentenceOnField.put("crpActive", "true");
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Corpus> listCorpus = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributesPaginated(eqSentenceOnField, eqMustSentenceOnField, index, paginationSize);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributesWKeywordsPaginated(eqSentenceOnField, eqMustSentenceOnField, index, paginationSize);
		}
		else{
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributesPaginated(eqSentenceOnField, index, paginationSize);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributesWKeywordsPaginated(eqSentenceOnField, index, paginationSize);
		}
		
		List<ICorpus> listCorpus_ = new ArrayList<ICorpus>();
		for (Corpus corpus : listCorpus) {
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);
			listCorpus_.add(corpus_);
		}

		return listCorpus_;
	}
	
	@Override
	public Integer countCorpusFromSearch(ISearchProperties searchProperties){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Corpus> listCorpus = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributesWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		}
		else{
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributes(eqSentenceOnField);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributesWKeywords(eqSentenceOnField);
		}
		
		
		return listCorpus.size();
	}
	
	@Override
	public List<ICorpus> getCorpusFromSearchWPrivileges(ISearchProperties searchProperties){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
	
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "corpus");
		//System.out.println(corpusLuceneManagerDao.getCorpusLuceneDao().findExactByAttributes(eqMustSentenceOnField));
		
		
		List<AuthUserDataObjects> l= corpusLuceneManagerDao.getCorpusLuceneDao().findExactByAttributesForAuth(permissionFields);
		ArrayList<String> ids = new ArrayList<String>();
		for(AuthUserDataObjects a : l) {
			 ids.add(String.valueOf(a.getId().getAudoUidResource()));
		}
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Corpus> listCorpus = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findMixedByAttributesWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		}
		else{
			if(searchProperties.isWholeWords())
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributes(eqSentenceOnField);
			else
				listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findNotExactByAttributesWKeywords(eqSentenceOnField);
		}
		
		List<ICorpus> listCorpus_ = new ArrayList<ICorpus>();
		for (Corpus corpus : listCorpus) {
			String id = String.valueOf(corpus.getCrpId());
			if(ids.contains(id)) {
				ids.remove(id);
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);
			listCorpus_.add(corpus_);
			}
		}
		

		return listCorpus_;
	
	}
	
	@Override
	public List<ICorpus> getCorpusFromSearchWPrivilegesPaginated(ISearchProperties searchProperties, String permission , int index, int paginationSize, boolean asc, String sortBy){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "crpId";
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
	
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "corpus");
		permissionFields.put("audoPermission", permission);
		//System.out.println(corpusLuceneManagerDao.getCorpusLuceneDao().findExactByAttributes(eqMustSentenceOnField));
		
		String sortField = CorpusLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = CorpusLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : filters.keySet()){
			eqFilters.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Corpus> listCorpus = null;
		
		listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findForWebTableWPermissionsPaginated(eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<ICorpus> listCorpus_ = new ArrayList<ICorpus>();
		for (Corpus corpus : listCorpus) {
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);
			listCorpus_.add(corpus_);
		}

		return listCorpus_;
	
	}
	
	@Override
	public Integer countCorpusFromSearchWPrivileges(ISearchProperties searchProperties, String permission){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "crpId";
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
	
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "corpus");
		permissionFields.put("audoPermission", permission);
		//System.out.println(corpusLuceneManagerDao.getCorpusLuceneDao().findExactByAttributes(eqMustSentenceOnField));
		
		for(String key : filters.keySet()){
			eqFilters.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		
		return corpusLuceneManagerDao.getCorpusLuceneDao().countForWebTableWPermissions(eqSentenceOnField, eqMustSentenceOnField, permissionFields, idField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	
	@Override
	public List<ICorpus> getActiveCorpusFromSearchPaginated(ISearchProperties searchProperties , int index, int paginationSize, boolean asc, String sortBy){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
	
		String sortField = CorpusLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = CorpusLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : filters.keySet()){
			eqFilters.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Corpus> listCorpus = null;
		
		listCorpus = corpusLuceneManagerDao.getCorpusLuceneDao().findForWebTablePaginated(eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords(), index, paginationSize,
				sortField, sortType, asc);
		
		List<ICorpus> listCorpus_ = new ArrayList<ICorpus>();
		for (Corpus corpus : listCorpus) {
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);
			listCorpus_.add(corpus_);
		}

		return listCorpus_;
	
	}
	
	@Override
	public Integer countActiveCorpusFromSearch(ISearchProperties searchProperties){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("crpActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		for(String key : filters.keySet()){
			eqFilters.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(CorpusLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		
		return corpusLuceneManagerDao.getCorpusLuceneDao().countForWebTable(eqSentenceOnField, eqMustSentenceOnField, 
				eqFilters,searchProperties.isWholeWords());
		
	}
	
	
	@Override
	public List<ICorpus> getPrivilegesCorpusAdminAccessFromSearchPaginated(ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
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
			return this.getActiveCorpusFromSearchPaginated(searchProperties,paginationIndex, paginationSize, asc, sortBy);
		else
			return this.getCorpusFromSearchWPrivilegesPaginated(searchProperties,"owner",paginationIndex, paginationSize, asc, sortBy);

	}
	
	@Override
	public Integer countPrivilegesCorpusAdminAccessFromSearch(ISearchProperties searchProperties) {
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
			return this.countActiveCorpusFromSearch(searchProperties);
		else
			return this.countCorpusFromSearchWPrivileges(searchProperties,"owner");

	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		user = userLogged;
		// TODO Auto-generated method stub
		
	}
}
