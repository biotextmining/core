package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.corpus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.CorpusLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusLuceneFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;


@Service
@Transactional(readOnly = true)
public class CorpusLuceneServiceImpl implements ICorpusLuceneService {
	
	private CorpusManagerDao corpusManagerDao;
	private CorpusLuceneManagerDao corpusLuceneManagerDao;
	
	public CorpusLuceneServiceImpl(CorpusManagerDao corpusManagerDao, CorpusLuceneManagerDao corpusLuceneManagerDao) {
		this.corpusManagerDao= corpusManagerDao;
		this.corpusLuceneManagerDao= corpusLuceneManagerDao;
	}
	
	
	@Override
	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ){
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		
		//eqMustSentenceOnField.put("Auth_audo_user_id","1" );
		
		//System.out.println(corpusLuceneManagerDao.getCorpusLuceneDao().findExactByAttributes(eqMustSentenceOnField));
		
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
	public void setUserLogged(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}
}
