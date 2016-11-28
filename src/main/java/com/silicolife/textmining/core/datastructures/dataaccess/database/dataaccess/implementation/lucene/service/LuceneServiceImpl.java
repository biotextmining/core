package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service;

import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;

@Service
@Transactional(readOnly = true)
public class LuceneServiceImpl implements ILuceneService{

	private SessionFactory sessionFactory;

	public LuceneServiceImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean rebuildLuceneIndex() {
		try {
			FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		
	}

}
