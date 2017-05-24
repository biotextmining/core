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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapper;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.QueriesLuceneManagerDao;

@Service
@Transactional(readOnly = true)
public class QueriesLuceneServiceImpl implements IQueriesLuceneService {
	
	private QueriesManagerDao queriesManagerDao;
	private QueriesLuceneManagerDao queriesLuceneManagerDao;
	
	

	public QueriesLuceneServiceImpl(
			QueriesLuceneManagerDao queriesLuceneManagerDao, QueriesManagerDao queriesManagerDao) {
		this.queriesManagerDao = queriesManagerDao;
		this.queriesLuceneManagerDao = queriesLuceneManagerDao;
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
