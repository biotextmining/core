package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.HyperLinkMenuException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.HyperLinkMenuManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociationId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.hyperlink.HyperLinkMenuWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.SourcesWrapper;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

/**
 * Service layer which implements all operations about Hyperlink menu.
 * 
 *
 */
@Service
@Transactional(readOnly = true)
public class HyperLinkServiceImpl implements IHyperLinkService{
	
	private HyperLinkMenuManagerDao hyperLinkMenuDao;
	@SuppressWarnings("unused")
	private UsersLogged userLogged;

	@Autowired
	public HyperLinkServiceImpl(HyperLinkMenuManagerDao hyperLinkMenuDao, UsersLogged userLogged){
		this.hyperLinkMenuDao = hyperLinkMenuDao;
		this.userLogged = userLogged;	
	}

	@Override
	public IHyperLinkMenuItem getHyperLinkMenuItemByID(Long id) throws HyperLinkMenuException {
		HyperLinkMenus hyperLinkMenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(id);
		if(hyperLinkMenu == null){
			throw new HyperLinkMenuException(ExceptionsCodes.codeNoHyperLinkMenuItem, ExceptionsCodes.msgNoHyperLinkMenuItem);
		}
		IHyperLinkMenuItem hyperLinkMenuItem = HyperLinkMenuWrapper.convertToAnoteStructure(hyperLinkMenu);
		return hyperLinkMenuItem;
	}

	@Override
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() {
		List<IHyperLinkMenuItem> hyperLinkMenuItems= new ArrayList<IHyperLinkMenuItem>();
		List<HyperLinkMenus> hyperLinkMenus = hyperLinkMenuDao.getHyperLinkMenusDao().findAll();
		for(HyperLinkMenus hyperLinkMenu : hyperLinkMenus){
			hyperLinkMenuItems.add(HyperLinkMenuWrapper.convertToAnoteStructure(hyperLinkMenu));
		}
		return hyperLinkMenuItems;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem)
			throws HyperLinkMenuException {
		HyperLinkMenus hyperLinkMenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(hyperLinkMenuItem.getId());
		if(hyperLinkMenu == null){
			throw new HyperLinkMenuException(ExceptionsCodes.codeNoHyperLinkMenuItem, ExceptionsCodes.msgNoHyperLinkMenuItem);
		}
		//remove all source associations 
		for(HyperLinkMenuSourceAssociation associationToDelete : hyperLinkMenu.getHyperLinkMenuSourceAssociations()){
			hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().delete(associationToDelete);
		}
		//save all source associations
		for(ISource source : hyperLinkMenuItem.getSourcesLinkage()){
			Sources sources = SourcesWrapper.convertToDaemonStructure(source);
			HyperLinkMenuSourceAssociationId hyplmenuToSourceID = new HyperLinkMenuSourceAssociationId(hyperLinkMenu.getHylId(), sources.getSouId());
			hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().save(new HyperLinkMenuSourceAssociation(hyplmenuToSourceID, hyperLinkMenu, sources));
		}

		return true;
	}

	@Override
	public List<ISource> getAllSources() throws HyperLinkMenuException {
		List<ISource> result = new ArrayList<>();
		List<Sources> sources = hyperLinkMenuDao.getSourcesDao().findAll();
		for(Sources source:sources){
			result.add(SourcesWrapper.convertToAnoteStructure(source));
		}
		return result;
	}

	@Override
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(long sourceID) throws HyperLinkMenuException {
		Sources source = hyperLinkMenuDao.getSourcesDao().findById(sourceID);
		if(source==null)
			throw new HyperLinkMenuException(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);	
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("sources", source);
		List<HyperLinkMenuSourceAssociation> list = hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().findByAttributes(eqRestrictions);		
		List<IHyperLinkMenuItem> hyperLinkMenuItems= new ArrayList<IHyperLinkMenuItem>();
		for(HyperLinkMenuSourceAssociation assoc : list){
			HyperLinkMenus hyperLinkMenu = assoc.getHyperLinkMenus();
			hyperLinkMenuItems.add(HyperLinkMenuWrapper.convertToAnoteStructure(hyperLinkMenu));
		}
		return hyperLinkMenuItems;
	}
	
}
