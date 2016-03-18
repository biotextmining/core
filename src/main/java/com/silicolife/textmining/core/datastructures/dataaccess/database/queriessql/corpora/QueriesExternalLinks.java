package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.corpora;

public class QueriesExternalLinks {

//	public static final String getprimaryMenu = "SELECT id,menu_name,icon,hypher_link_menu,menu_level FROM hypher_links_menu "+
//										  "WHERE menu_level=1 ";
//	
//	public static final String getprimaryMenuSubMEnus = "SELECT sm.hypher_links_submenu_id,menu_name,icon,hypher_link_menu,menu_level FROM hypher_links_submenus sm "+
//												  "JOIN hypher_links_menu me ON me.id=sm.hypher_links_submenu_id "+
//												  "WHERE sm.hypher_links_menu_id=?";
//	
//	public static final String getMenuLinkageSources = "SELECT sources_idsources FROM hypher_link_menu_source_association "+
//												 "WHERE hypher_links_menu_id=? ";
//
//	public static final String updateMenuItem = "UPDATE hypher_links_menu "+
//										  "SET `menu_name` = ? , `hypher_link_menu` = ? , icon=? "+
//										  "WHERE id=? ";
//	
//	public static final String updateMenuItemWithouIcon = "UPDATE hypher_links_menu "+
//			  											  "SET `menu_name` = ? , `hypher_link_menu` = ? "+
//			  											  "WHERE id=? ";
//;
//	
//	public static final String superExternalIDCurator = "SELECT external_id,sources_idsources "+
//														"FROM resource_elements_extenal_id "+
//														"JOIN resource_elements AS p ON (p.idresource_elements=resource_elements_extenal_id.resource_elements_idresource_elements) "+
//														"JOIN annotations AS q ON (p.idresource_elements=q.resource_elements_id) "+
//														"WHERE q.processes_idprocesses=? AND q.corpus_idcorpus=? AND q.publications_id=? AND q.start=? AND q.end=? ";
//
//	public static final String removeMenuItemSourceLinkage = "DELETE FROM hypher_link_menu_source_association "+
//															 "WHERE hypher_links_menu_id=? AND  sources_idsources=? ";
//
//	public static final String updateMenuItemSourceLinkage = "INSERT INTO hypher_link_menu_source_association VALUES(?,?) ";
//
//	public static final String addMenuLink = "INSERT INTO hypher_links_menu (menu_name,hypher_link_menu,icon,menu_level) VALUES(?,?,?,?) ";
//
//	public static final String addSubMenuItemToMenu = "INSERT INTO hypher_links_submenus VALUES(?,?) ";
//
//	public static final String removeAllSourceLinkages = "DELETE FROM hypher_link_menu_source_association WHERE hypher_links_menu_id=? ";
//
//	public static final String removeAllMenuLinkages = "DELETE FROM hypher_links_submenus WHERE hypher_links_menu_id=?  OR hypher_links_submenu_id=? ";
//
//	public static final String removeMenuLink = "DELETE FROM hypher_links_menu WHERE id=? ";
//
//	public static final String getLinkoutsForSource = "SELECT hypher_link_menu FROM hypher_link_menu_source_association AS hl "+
//													  "JOIN hypher_links_menu AS men ON men.id=hl.hypher_links_menu_id "+
//													  "WHERE sources_idsources=? ";
	
	
}
