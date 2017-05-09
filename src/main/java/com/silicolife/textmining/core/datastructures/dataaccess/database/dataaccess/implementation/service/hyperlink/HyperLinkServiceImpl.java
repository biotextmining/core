package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenus;
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
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public Long getNextHyperLinkMenusItemID() throws HyperLinkMenuException{
		return hyperLinkMenuDao.getHyperLinkMenusAuxDao().getNextHyperLinkMenusItemID();
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws HyperLinkMenuException {
		HyperLinkMenus hyperLinkMenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(hyperLinkMenuItem.getId());
		if(hyperLinkMenu != null)
			throw new HyperLinkMenuException(ExceptionsCodes.codeHyperLinkMenuItemExists, ExceptionsCodes.msgHyperLinkMenuItemExists);
		
		HyperLinkMenus hyperLinkMenuItem_ = HyperLinkMenuWrapper.convertToDaemonStructure(hyperLinkMenuItem);
		hyperLinkMenuDao.getHyperLinkMenusDao().save(hyperLinkMenuItem_);
		
//		Set<HyperLinkMenuSourceAssociation> sources = HyperLinkMenuSourcesWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSourcesLinkage(), hyperLinkMenuItem_);
		for(HyperLinkMenuSourceAssociation association : hyperLinkMenuItem_.getHyperLinkMenuSourceAssociations())
			hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().save(association);
//		
//		Set<HyperLinkSubmenus> submenus = HyperLinkSubMenuItemWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSubitems(), hyperLinkMenuItem_);
		for(HyperLinkSubmenus submenu : hyperLinkMenuItem_.getHyperLinkSubmenusesForHyliHyperLinkSubmenuId())
			hyperLinkMenuDao.getHyperLinkSubmenusDao().save(submenu);
		
		return true;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Boolean updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws HyperLinkMenuException {
		HyperLinkMenus hyperLinkMenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(hyperLinkMenuItem.getId());
		if(hyperLinkMenu == null)
			throw new HyperLinkMenuException(ExceptionsCodes.codeNoHyperLinkMenuItem, ExceptionsCodes.msgNoHyperLinkMenuItem);
		hyperLinkMenuDao.getHyperLinkMenusDao().evict(hyperLinkMenu);
		
		HyperLinkMenus hyperLinkMenuItem_ = HyperLinkMenuWrapper.convertToDaemonStructure(hyperLinkMenuItem);
		
		hyperLinkMenuDao.getHyperLinkMenusDao().update(hyperLinkMenuItem_);

//		Set<HyperLinkMenuSourceAssociation> sources = HyperLinkMenuSourcesWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSourcesLinkage(), hyperLinkMenuItem_);
		for(HyperLinkMenuSourceAssociation association : hyperLinkMenuItem_.getHyperLinkMenuSourceAssociations())
			hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().saveOrUpdate(association);
		
//		Set<HyperLinkSubmenus> submenus = HyperLinkSubMenuItemWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSubitems(), hyperLinkMenuItem_);
		for(HyperLinkSubmenus submenu : hyperLinkMenuItem_.getHyperLinkSubmenusesForHyliHyperLinkSubmenuId())
			hyperLinkMenuDao.getHyperLinkSubmenusDao().saveOrUpdate(submenu);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeHyperLinkMenuItem(long menuId) throws HyperLinkMenuException {
		HyperLinkMenus hyperLinkMenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(menuId);
		if(hyperLinkMenu == null)
			throw new HyperLinkMenuException(ExceptionsCodes.codeNoHyperLinkMenuItem, ExceptionsCodes.msgNoHyperLinkMenuItem);
		
		deleteMenuAndSubMenus(hyperLinkMenu);
		
		return true;
	}

	private void deleteMenuAndSubMenus(HyperLinkMenus hyperLinkMenu) {
		for(HyperLinkMenuSourceAssociation association : hyperLinkMenu.getHyperLinkMenuSourceAssociations())
			hyperLinkMenuDao.getHyperLinkMenuSourceAssociationDao().delete(association);
		
		Set<Long> subMenuIdsToDelete = new HashSet<>();
		
		for(HyperLinkSubmenus submenuassociation : hyperLinkMenu.getHyperLinkSubmenusesForHyliHyperLinkMenuId()){
			hyperLinkMenuDao.getHyperLinkSubmenusDao().delete(submenuassociation);
			subMenuIdsToDelete.add(submenuassociation.getId().getHyliHyperLinkSubmenuId());
		}
		
		for(HyperLinkSubmenus submenuassociation : hyperLinkMenu.getHyperLinkSubmenusesForHyliHyperLinkSubmenuId()){
			hyperLinkMenuDao.getHyperLinkSubmenusDao().delete(submenuassociation);
			subMenuIdsToDelete.add(submenuassociation.getId().getHyliHyperLinkSubmenuId());
		}
		
		hyperLinkMenuDao.getHyperLinkMenusDao().delete(hyperLinkMenu);
		
		for(Long subMenuIdToDelete : subMenuIdsToDelete){
			HyperLinkMenus submenu = hyperLinkMenuDao.getHyperLinkMenusDao().findById(subMenuIdToDelete);
			if(submenu != null)
				deleteMenuAndSubMenus(submenu);
		}
	}
	
}
