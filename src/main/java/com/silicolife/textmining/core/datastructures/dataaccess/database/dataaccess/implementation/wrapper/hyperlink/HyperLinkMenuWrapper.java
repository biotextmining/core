package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.hyperlink;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenus;
import com.silicolife.textmining.core.datastructures.hyperlink.HyperLinkMenuItemImpl;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

public class HyperLinkMenuWrapper {
	
	public static IHyperLinkMenuItem convertToAnoteStructure(HyperLinkMenus hyperLinkMenuItem){
		int id = (int) hyperLinkMenuItem.getHylId();
		String name = hyperLinkMenuItem.getHylMenuName();
		byte[] icon = hyperLinkMenuItem.getHylIcon();
		String url = hyperLinkMenuItem.getHylHyperLinkMenu();
		Set<HyperLinkSubmenus> submenusIDs = hyperLinkMenuItem.getHyperLinkSubmenusesForHyliHyperLinkMenuId();
		List<IHyperLinkMenuItem> subItems = HyperLinkSubMenuItemWrapper.convertToAnoteStructure(submenusIDs);
		Integer level = Integer.valueOf(hyperLinkMenuItem.getHylMenuLevel());
		Set<HyperLinkMenuSourceAssociation> sourceAssociations = hyperLinkMenuItem.getHyperLinkMenuSourceAssociations();
		List<ISource> sourcesLinkage = HyperLinkMenuSourcesWrapper.convertToAnoteStructure(sourceAssociations);
		return new HyperLinkMenuItemImpl(id, name, icon, url, subItems, level, sourcesLinkage);
	}
	
	public static HyperLinkMenus convertToDaemonStructure(IHyperLinkMenuItem hyperLinkMenuItem){

		HyperLinkMenus hyperlink = new HyperLinkMenus();
		hyperlink.setHylId(hyperLinkMenuItem.getId());
		hyperlink.setHylMenuName(hyperLinkMenuItem.getMenuItemName());
		hyperlink.setHylIcon(hyperLinkMenuItem.getIcon());
		hyperlink.setHylHyperLinkMenu(hyperLinkMenuItem.getHyperLink());
		hyperlink.setHylMenuLevel(String.valueOf(hyperLinkMenuItem.getMenuLevel()));
		
		Set<HyperLinkSubmenus> hyperLinkSubmenusesForHyliHyperLinkSubmenuId = new HashSet<>();
		if(hyperLinkMenuItem.getSubitems() != null)
			hyperLinkSubmenusesForHyliHyperLinkSubmenuId = HyperLinkSubMenuItemWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSubitems(), hyperlink);
		
		Set<HyperLinkMenuSourceAssociation> hyperLinkMenuSourceAssociations = new HashSet<>();
		if(hyperLinkMenuItem.getSourcesLinkage() != null)
			hyperLinkMenuSourceAssociations = HyperLinkMenuSourcesWrapper.convertToDaemonStructure(hyperLinkMenuItem.getSourcesLinkage(), hyperlink);
		
		hyperlink.setHyperLinkSubmenusesForHyliHyperLinkSubmenuId(hyperLinkSubmenusesForHyliHyperLinkSubmenuId);
		hyperlink.setHyperLinkMenuSourceAssociations(hyperLinkMenuSourceAssociations);
		return hyperlink;
	}

}
