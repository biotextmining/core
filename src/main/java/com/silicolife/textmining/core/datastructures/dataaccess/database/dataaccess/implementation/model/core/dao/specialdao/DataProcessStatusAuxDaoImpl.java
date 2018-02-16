package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DataProcessStatusAuxDaoImpl implements DataProcessStatusAuxDao{
	
	private SessionFactory sessionFactory;
	
	public DataProcessStatusAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
}
