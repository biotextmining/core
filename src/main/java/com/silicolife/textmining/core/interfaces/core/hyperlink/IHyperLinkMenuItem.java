package com.silicolife.textmining.core.interfaces.core.hyperlink;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public interface IHyperLinkMenuItem {

	public long getId();
	public String getMenuItemName();
	public String getHyperLink();
	public byte[] getIcon();
	public List<IHyperLinkMenuItem> getSubitems();
	public List<ISource> getSourcesLinkage();
	public int getMenuLevel();
	public boolean haveSubMenuItems();
	
}
