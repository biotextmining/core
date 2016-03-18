package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;

@Repository
public class HyperLinkMenuManagerDao {

	private GenericDao<HyperLinkMenus> hyperLinkMenusDao;
	private GenericDao<HyperLinkSubmenus> hyperLinkSubmenusDao;
	private GenericDao<HyperLinkMenuSourceAssociation> hyperLinkMenuSourceAssociationDao;
	private GenericDao<Sources> sourcesDao;

	
	@Autowired
	public HyperLinkMenuManagerDao(GenericDao<HyperLinkMenus> hyperLinkMenusDao, GenericDao<HyperLinkSubmenus> hyperLinkSubmenusDao, 
			GenericDao<HyperLinkMenuSourceAssociation> hyperLinkMenuSourceAssociationDao, GenericDao<Sources> sourcesDao){
		super();
		this.hyperLinkMenusDao = hyperLinkMenusDao;
		this.hyperLinkSubmenusDao= hyperLinkSubmenusDao;
		this.hyperLinkMenuSourceAssociationDao = hyperLinkMenuSourceAssociationDao;
		this.sourcesDao = sourcesDao;
	}


	public GenericDao<HyperLinkMenus> getHyperLinkMenusDao() {
		return hyperLinkMenusDao;
	}


	public GenericDao<HyperLinkSubmenus> getHyperLinkSubmenusDao() {
		return hyperLinkSubmenusDao;
	}


	public GenericDao<HyperLinkMenuSourceAssociation> getHyperLinkMenuSourceAssociationDao() {
		return hyperLinkMenuSourceAssociationDao;
	}


	public GenericDao<Sources> getSourcesDao() {
		return sourcesDao;
	}
}
