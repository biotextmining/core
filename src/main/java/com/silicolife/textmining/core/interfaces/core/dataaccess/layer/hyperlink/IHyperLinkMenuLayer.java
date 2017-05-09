package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.hyperlink;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

public interface IHyperLinkMenuLayer {

	public IHyperLinkMenuItem getHyperLinkMenuItemById(Long id) throws ANoteException;
	
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() throws ANoteException;
	
	public void updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException;
	
	public List<ISource> getAllSources() throws ANoteException;
	
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(ISource source) throws ANoteException;

	public Long getNextHyperLinkMenuItemID() throws ANoteException;
	
	public void addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException;
	
	public void removeHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException;

	public void updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException;
}
