package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.DataProcessStatusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.CorpusServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

@Service
@Transactional(readOnly = true)
public class DataProcessStatusServiceImpl implements IDataProcessStatusService {
	
	final static Logger creationLogger = LoggerFactory.getLogger(DataProcessStatus.class);
	
	private DataProcessStatusManagerDao dataProcessStatusManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;
	
	@Autowired
	public DataProcessStatusServiceImpl(DataProcessStatusManagerDao dataProcessStatusManagerDao,
			UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		super();
		this.dataProcessStatusManagerDao = dataProcessStatusManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}



}
