package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.HyperLinkMenuException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

public interface IHyperLinkService {

	public IHyperLinkMenuItem getHyperLinkMenuItemByID(Long id) throws HyperLinkMenuException;

	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems();

	public Boolean updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws HyperLinkMenuException;
	
	/**
	 * Get All Sources
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<ISource> getAllSources() throws HyperLinkMenuException;

	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(long sourceID) throws HyperLinkMenuException;
	
	public Long getNextHyperLinkMenusItemID() throws HyperLinkMenuException;
	
	public void setUserLogged(UsersLogged userLogged);

	public Boolean addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws HyperLinkMenuException;

	public Boolean removeHyperLinkMenuItem(long menuId) throws HyperLinkMenuException;

	public Boolean updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws HyperLinkMenuException;
}
