package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.IGenericLuceneDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

@Repository
public class ProcessesLuceneManagerDao {
	
private IGenericLuceneDao<Processes> processesLuceneDao;
	
	@Autowired
	public ProcessesLuceneManagerDao(IGenericLuceneDao<Processes> processesLuceneDao){
		this.processesLuceneDao = processesLuceneDao;
	}

	public IGenericLuceneDao<Processes> getProcessesLuceneDao() {
		return processesLuceneDao;
	}

}
