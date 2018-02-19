package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.GenericLuceneDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.PublicationsLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.expressionGrammar.expressionGrammar;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.PublicationsManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.queryGrammar.ParseException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.queryGrammar.queryGrammar;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneFields;
import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneIndexFields;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

@Service
@Transactional(readOnly = true)
public class PublicationsLuceneServiceImpl implements IPublicationsLuceneService {
	
	private PublicationsManagerDao publicationsManagerDao;
	private PublicationsLuceneManagerDao publicationsLuceneManagerDao;
	
	public PublicationsLuceneServiceImpl(PublicationsManagerDao publicationsManagerDao,PublicationsLuceneManagerDao publicationsLuceneManagerDao ){
		this.publicationsManagerDao = publicationsManagerDao;
		this.publicationsLuceneManagerDao = publicationsLuceneManagerDao;
	}
	
	@Override
	public List<IPublication> getPublicationsByTitle(String title) {
		Map<String, String> eqSentenceOnField = new HashMap<>();
		PublicationLuceneFields field = PublicationLuceneFields.title;
		eqSentenceOnField.put(field.getKNCS(), title);

		
		List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	
	public static List<String> databaseListFields(ISearchProperties searchProperties) {
		List<String> databaseFields = new ArrayList<>();
		List<String> fields = searchProperties.getFields();
		
			for(String field : fields){
				databaseFields.add(PublicationLuceneIndexFields.getLuceneField(searchProperties, field));
			}
		return 	databaseFields;
	}
	
	public static Map<String, String> putOnEqSentenceOnField(String value, List<String> fields){
		Map<String, String> eqSentenceOnField = new HashMap<String, String>();
		for(String field : fields){
			eqSentenceOnField.put(field, value);
		}
		return eqSentenceOnField;
	}

	@Override
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Publications> listPublications = null;
		if(eqMustSentenceOnField.size()>0){
			if(searchProperties.isWholeWords())
				listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);
			else{
				listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findMixedByAttributesWKeywords(eqSentenceOnField, eqMustSentenceOnField);
			}
			}
		else
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findNotExactByAttributes(eqSentenceOnField);


		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	//eqSentenceOnField.put("qhb_query_id", "694261800784300680");
	//eqSentenceOnField.put("qhb_query_id", "1878075771966414137");
	//Long id = (long) Long.valueOf("547193518995135916");
	
	//eqSentenceOnField.put("queryHasPublicationses.id.qhbQueryId", "547193518995135916");
	//eqMustSentenceOnField.put("queryHasPublicationses.id.qhbQueryId", "547193518995135916");
	
	//List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
	
	
	/*
	 * Method for 4 diff index types
	 * 
	 * @Override
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
			
		}
		
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		//eqSentenceOnField.put("qhb_query_id", "694261800784300680");
		//eqSentenceOnField.put("qhb_query_id", "1878075771966414137");
		//Long id = (long) Long.valueOf("547193518995135916");
		
		//eqSentenceOnField.put("queryHasPublicationses.id.qhbQueryId", "547193518995135916");
		//eqMustSentenceOnField.put("queryHasPublicationses.id.qhbQueryId", "547193518995135916");
		List<Publications> listPublications = null;
		if(eqMustSentenceOnField.size()>0)
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);
		else
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findNotExactByAttributes(eqSentenceOnField);

		//List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	 * 
	 */
	
	//Method using 2 different index types
	
	@Override
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ){
		
		return this.getPublicationsFromSearchPaginatedWSort(searchProperties, index, paginationSize, true, "none");
	}
	
	@Override
	public List<IPublication> getPublicationsFromSearchPaginatedWSort(ISearchProperties searchProperties, int index, int paginationSize,boolean asc, String sortBy ){
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		String sortField = PublicationLuceneIndexFields.getSortField(sortBy);
		
		SortField.Type sortType = PublicationLuceneIndexFields.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}

		for(String key : filters.keySet()){
			eqFilters.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		if(searchProperties.getValue()!=null) {
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		}
		
		List<Publications> listPublications = null;
		if(searchProperties.getValue()!="" && searchProperties.isExpression() && searchProperties.getValue()!=null) {
			Query q = getQueryFromExpression(searchProperties);
			if(q != null)
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findForWebTableWExpressionPaginated(q, eqMustSentenceOnField, eqFilters,searchProperties.isWholeWords(), index, paginationSize, sortField, sortType, asc);
		}
		else
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findForWebTablePaginated(eqSentenceOnField, eqMustSentenceOnField, eqFilters,searchProperties.isWholeWords(), index, paginationSize, sortField, sortType, asc);
		if(listPublications != null) {
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
		}else
			return null;
	}
	
	
	
	public Query getQueryFromExpression(ISearchProperties searchProperties){
				Query result = null;
				//System.out.println(searchProperties.getValue());
				InputStream is = new ByteArrayInputStream( searchProperties.getValue().getBytes() );
				expressionGrammar<Publications> parser = new expressionGrammar<Publications>(is);
				parser.setFields(PublicationsLuceneServiceImpl.databaseListFields(searchProperties));
				parser.setQb(publicationsLuceneManagerDao.getPublicationsLuceneDao().createQueryBuilder());
				parser.setGenericDao(publicationsLuceneManagerDao.getPublicationsLuceneDao());
				 try {
				result=	parser.parseExpression();
				} catch (com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.expressionGrammar.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return result;
		
	}
	
	/*
	
	//Method using 4 different index types
	
	@Override
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		List<Publications> listPublications = null;
		if(eqMustSentenceOnField.size()>0)
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findMixedByAttributesPaginated(eqSentenceOnField, eqMustSentenceOnField, index, paginationSize);
		else
			listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findNotExactByAttributesPaginated(eqSentenceOnField, index, paginationSize);
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	*/
	
	@Override
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties){
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}

		for(String key : filters.keySet()){
			eqFilters.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		if(searchProperties.getValue()!=null) {
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneIndexFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		}
		
		
		if(searchProperties.getValue()!="" && searchProperties.isExpression() && searchProperties.getValue()!=null) {
			Query q = getQueryFromExpression(searchProperties);
			if(q != null)
			return publicationsLuceneManagerDao.getPublicationsLuceneDao().countForWebTableWExpression(q, eqMustSentenceOnField, eqFilters,searchProperties.isWholeWords());
			else return 0;
		}
		else
			return publicationsLuceneManagerDao.getPublicationsLuceneDao().countForWebTable(eqSentenceOnField, eqMustSentenceOnField, eqFilters, searchProperties.isWholeWords());

	}
	/*
	 * Method for 4 index
	 * 
	 * @Override
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties){
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		
		Map<String, String> restrictions = searchProperties.getRestrictions();
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		for(String field : fields){
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
		}
		
		if(eqMustSentenceOnField.size()>0)
			return publicationsLuceneManagerDao.getPublicationsLuceneDao().countMixedByAttributes(eqSentenceOnField, eqMustSentenceOnField);	
		else
			return publicationsLuceneManagerDao.getPublicationsLuceneDao().countNotExactByAttributes(eqSentenceOnField);
			
	}
	 * 
	 */
	
	/*
	  HashSet<Publications> pubs = new HashSet<Publications>();
		ArrayList<String> fields = searchProperties.getFields();
		for(String field : fields){
			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
			List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
			for(Publications p: listPublications)
				pubs.add(p);
		}
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Object pub : pubs.toArray()) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure((Publications) pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	 * (non-Javadoc)
	 * @see com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications.IPublicationsLuceneService#setUserLogged(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged)
	 */
	
	
	/*
	 * 	Map<String, HashSet<String>> eqSentenceOnField = new HashMap<>();
		ArrayList<String> fields = searchProperties.getFields();
		for(String field : fields){
		HashSet<String> fieldsSet = new HashSet<String>();
		fieldsSet.add(field);
		eqSentenceOnField.put(searchProperties.getValue(), fieldsSet);
		}
		
		publicationsLuceneManagerDao.getPublicationsLuceneDao().findMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField)
		List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findMultiFieldSameAttributesAndExactByAttributes(searchProperties.getValue(), fieldsSet);
		/*HashSet<Publications> pubs = new HashSet<Publications>();
		for(String field : searchProperties.getFields()){
			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
			List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
			pubs.addAll(listPublications);
		}/
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : pubs) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	 * 
	 * (non-Javadoc)
	 * @see com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications.IPublicationsLuceneService#setUserLogged(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged)
	 */
	
	
	/*
	 * 
	 * 	HashSet<Publications> pubs = new HashSet<Publications>();
		for(String field : searchProperties.getFields()){
			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put(PublicationLuceneFields.getLuceneField(searchProperties, field),searchProperties.getValue());
			List<Publications> listPublications = publicationsLuceneManagerDao.getPublicationsLuceneDao().findExactByAttributes(eqSentenceOnField);
			pubs.addAll(listPublications);
		}
		
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications pub : pubs) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(pub);
			listPublications_.add(publication_);
		}

		return listPublications_;
	 * 
	 * (non-Javadoc)
	 * @see com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications.IPublicationsLuceneService#setUserLogged(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged)
	 */
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
	
}
