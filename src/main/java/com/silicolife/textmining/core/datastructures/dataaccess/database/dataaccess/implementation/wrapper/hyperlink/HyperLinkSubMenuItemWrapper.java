package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.hyperlink;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenusId;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

public class HyperLinkSubMenuItemWrapper {

	public static List<IHyperLinkMenuItem> convertToAnoteStructure(Set<HyperLinkSubmenus> submenusIDs){
		List<IHyperLinkMenuItem> submenus = new ArrayList<>();
		Iterator<HyperLinkSubmenus> itSubMenu = submenusIDs.iterator();
		while(itSubMenu.hasNext()){
			HyperLinkSubmenus subMenuId = itSubMenu.next();
			submenus.add(HyperLinkMenuWrapper.convertToAnoteStructure(subMenuId.getHyperLinkMenusByHyliHyperLinkSubmenuId()));
		}
		return submenus;
	}
	
	public static Set<HyperLinkSubmenus> convertToDaemonStructure(List<IHyperLinkMenuItem> submenus, HyperLinkMenus menu){
		Set<HyperLinkSubmenus> submenusSet = new HashSet<>();
		for(IHyperLinkMenuItem submenu : submenus){
			HyperLinkMenus convertedSubMenu = HyperLinkMenuWrapper.convertToDaemonStructure(submenu);
			HyperLinkSubmenusId id = new HyperLinkSubmenusId(menu.getHylId(), convertedSubMenu.getHylId());
			submenusSet.add(new HyperLinkSubmenus(id, menu, convertedSubMenu));
		}
		return submenusSet;
	}
}
