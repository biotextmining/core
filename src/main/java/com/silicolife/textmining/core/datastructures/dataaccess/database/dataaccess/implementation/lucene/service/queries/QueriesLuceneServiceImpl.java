package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapper;
import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneIndexFields;
import com.silicolife.textmining.core.datastructures.documents.query.QueriesLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.QueriesLuceneManagerDao;

@Service
@Transactional(readOnly = true)
public class QueriesLuceneServiceImpl implements IQueriesLuceneService {
	
	private QueriesManagerDao queriesManagerDao;
	private QueriesLuceneManagerDao queriesLuceneManagerDao;
	private UsersLogged user;
	
	

	public QueriesLuceneServiceImpl(
			QueriesLuceneManagerDao queriesLuceneManagerDao, QueriesManagerDao queriesManagerDao, UsersLogged user) {
		this.queriesManagerDao = queriesManagerDao;
		this.queriesLuceneManagerDao = queriesLuceneManagerDao;
		this.user = user;
	}



	@Override
	public List<IQuery> getQueriesByName(String name) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quQueryName", name);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public List<IQuery> getQueriesByOrganism(String organism) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quOrganism", organism);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public Integer countQueriesByOrganism(String organism) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quOrganism", organism);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributes(eqSentenceOnField);
		

		return listQueries.size();
	}
	
	@Override
	public List<IQuery> getQueriesByOrganismPaginated(String organism, int index, int paginationSize) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quOrganism", organism);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributesPaginated(eqSentenceOnField, index,paginationSize);
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	
	@Override
	public List<IQuery> getQueriesFromSearchPaginatedWAuth(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "quId";
		eqMustSentenceOnField.put("quActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "queries");
		
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Queries> listQueries = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesPaginatedWAuth(eqSentenceOnField, eqMustSentenceOnField,permissionFields,idField, index, paginationSize);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesWKeywordsPaginatedWAuth(eqSentenceOnField, eqMustSentenceOnField,permissionFields,idField, index, paginationSize);
		}
		else{
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesPaginatedWAuth(eqSentenceOnField,permissionFields,idField, index, paginationSize);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesWKeywordsPaginatedWAuth(eqSentenceOnField,permissionFields,idField, index, paginationSize);
		}
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public List<IQuery> getQueriesFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("quActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Queries> listQueries = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesPaginated(eqSentenceOnField, eqMustSentenceOnField, index, paginationSize);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesWKeywordsPaginated(eqSentenceOnField, eqMustSentenceOnField, index, paginationSize);
		}
		else{
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesPaginated(eqSentenceOnField, index, paginationSize);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesWKeywordsPaginated(eqSentenceOnField, index, paginationSize);
		}
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public Integer countQueriesFromSearch(ISearchProperties searchProperties ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		eqMustSentenceOnField.put("quActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Queries> listQueries = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesWKeywords(eqSentenceOnField, eqMustSentenceOnField);
		}
		else{
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributes(eqSentenceOnField);
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesWKeywords(eqSentenceOnField);
		}
		

		return listQueries.size();
	}
	
	
	@Override
	public Integer countQueriesFromSearchWAuth(ISearchProperties searchProperties ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "quId";
		eqMustSentenceOnField.put("quActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "queries");
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(QueriesLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Queries> listQueries = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesWAuth(eqSentenceOnField, eqMustSentenceOnField, permissionFields,idField );
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findMixedByAttributesWKeywordsWAuth(eqSentenceOnField, eqMustSentenceOnField, permissionFields,idField );
		}
		else{
			if(searchProperties.isWholeWords())
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesWAuth(eqSentenceOnField, permissionFields,idField );
			else
				listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findNotExactByAttributesWKeywordsWAuth(eqSentenceOnField, permissionFields,idField );
		}
		

		return listQueries.size();
	}
	
	@Override
	public List<IQuery> getQueriesBykeywords(String keywords) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quKeywords", keywords);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public Integer countQueriesBykeywords(String keywords) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quKeywords", keywords);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		return listQueries.size();
	}
	
	@Override
	public List<IQuery> getQueriesBykeywordsPaginated(String keywords, int index, int paginationSize) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quKeywords", keywords);
		eqSentenceOnField.put("quActive", "true");
		
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findExactByAttributesPaginated(eqSentenceOnField, index,paginationSize);
		
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}
	
	@Override
	public  List<IQuery> getQueriesKeywordsByWildCard(String subKeyword){
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quActive", "true");
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("quKeywords", subKeyword);
		List<Queries> listQueries = queriesLuceneManagerDao.getQueriesLuceneDao().findStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;	
	}
	
	
	@Override
	public  List<String> getKeywordsOfQueriesByWildCard(String subKeyword){
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("quActive", "true");
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("quKeywords", subKeyword);
		List<String> projections = new ArrayList<String>();
		projections.add("quKeywords");
		Set<Object[]> keywords = new HashSet<Object[]> (queriesLuceneManagerDao.getQueriesLuceneDao().findStartingUsingWildcardAndExactByAttributesWithProjection(startSentenceOnField, eqSentenceOnField, projections));
		List<String> keywords_ = new ArrayList<String>();
		for ( Object[] st : keywords )
			keywords_.add((String) st[0]);
		return keywords_;
	}



	@Override
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}

}
