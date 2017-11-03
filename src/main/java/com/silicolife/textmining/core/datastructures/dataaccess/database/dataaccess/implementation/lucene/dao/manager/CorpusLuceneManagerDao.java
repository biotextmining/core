package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;

@Repository
public class CorpusLuceneManagerDao {
	
	private IGenericLuceneDao<Corpus> corpusLuceneDao;
	
	@Autowired
	CorpusLuceneManagerDao(IGenericLuceneDao<Corpus> corpusLuceneDao){
		this.corpusLuceneDao = corpusLuceneDao;
	}
	
	public IGenericLuceneDao<Corpus> getCorpusLuceneDao(){
		return this.corpusLuceneDao;
	}
}
