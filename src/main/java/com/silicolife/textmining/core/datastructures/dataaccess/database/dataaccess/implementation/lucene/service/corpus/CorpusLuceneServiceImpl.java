package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.corpus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.index.Term;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.CorpusLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusLuceneFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;


@Service
@Transactional(readOnly = true)
public class CorpusLuceneServiceImpl implements ICorpusLuceneService {
	
	private CorpusManagerDao corpusManagerDao;
	private CorpusLuceneManagerDao corpusLuceneManagerDao;
	private UsersLogged user;
	
	public CorpusLuceneServiceImpl(CorpusManagerDao corpusManagerDao, CorpusLuceneManagerDao corpusLuceneManagerDao, UsersLogged user) {
		this.corpusManagerDao= corpusManagerDao;
		this.corpusLuceneManagerDao= corpusLuceneManagerDao;
		this.user = user;
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
	public void setUserLogged(UsersLogged userLogged) {
		user = userLogged;
		// TODO Auto-generated method stub
		
	}
}
