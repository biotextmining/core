package com.silicolife.textmining.core.datastructures.hyperlink;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.general.SourceImpl;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;

public class HyperLinkMenuItemImpl implements IHyperLinkMenuItem{

	public long id;
	public int menuLevel;
	public String menuItemName;
	public byte[] icon;
	public String hyperLink;
	@JsonDeserialize(contentAs = HyperLinkMenuItemImpl.class)
	public List<IHyperLinkMenuItem> subitems;
	@JsonDeserialize(contentAs = SourceImpl.class)
	public List<ISource> sourcesLinkage;
	
	public HyperLinkMenuItemImpl(long id,String menuItemName,byte[] icon,String hyperLink,List<IHyperLinkMenuItem> subItems,int lmenuLevel,List<ISource> sourcesLinkage)
	{
		this.id = id;
		this.menuItemName = menuItemName;
		this.icon = icon;
		this.hyperLink = hyperLink;
		this.subitems = subItems;
		this.menuLevel = lmenuLevel;
		this.sourcesLinkage = sourcesLinkage;
	}
	
	public HyperLinkMenuItemImpl() {
		super();
	}

	@Override
	public long getId() {
		return id;
	}
	@Override
	public int getMenuLevel() {
		return menuLevel;
	}
	@Override
	public String getMenuItemName() {
		return menuItemName;
	}
	@Override
	public byte[] getIcon() {
		return icon;
	}
	@Override
	public String getHyperLink() {
		return hyperLink;
	}
	@Override
	public List<IHyperLinkMenuItem> getSubitems() {
		return subitems;
	}
	@Override
	public List<ISource> getSourcesLinkage() {
		return sourcesLinkage;
	}
	
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setLevel(int level) {
		this.menuLevel = level;
	}

	public void setName(String name) {
		this.menuItemName = name;
	}

	public void setUrl(String url) {
		this.hyperLink = url;
	}

	public void setSubitems(List<IHyperLinkMenuItem> subitems) {
		this.subitems = subitems;
	}

	public void setSourcesLinkage(List<ISource> sourcesLinkage) {
		this.sourcesLinkage = sourcesLinkage;
	}
	
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}

	@Override
	public boolean haveSubMenuItems()
	{
		return (subitems!=null && subitems.size()>0);
	}
	
	public String toString()
	{
		String toString = new String();
		if(getMenuItemName()!=null && getMenuItemName().length()>0)
			toString = getMenuItemName() + " ( " + getId() + ") ";
		else
			toString = String.valueOf(getId());
		return toString;
	}
}
