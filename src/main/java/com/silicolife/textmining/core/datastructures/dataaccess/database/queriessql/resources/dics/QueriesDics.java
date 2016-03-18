package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.resources.dics;

public class QueriesDics {
	
//	public static final String termSyn = "SELECT t.idresource_elements,s.synonym,t.classes_idclasses,external_id,sources_idsources "+
//			 							 "FROM resource_elements t "+
//			 							 "JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//			 							 "JOIN resource_elements_extenal_id ex ON ex.resource_elements_idresource_elements=t.idresource_elements "+
//			 							 "WHERE resources_idresources=?";
	
//	public static final String termSyn = "SELECT  idresource_elements,s.synonym,t.classes_idclasses "+
//										 "FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//										 "WHERE resources_idresources=?	AND s.active=1 AND t.active=1 ";
//
//	public static final String getTerms = "SELECT idresource_elements,element,classes_idclasses FROM resource_elements WHERE resources_idresources=? AND active=1 ";
//	
//	public static final String selectTermsClass = "SELECT idresource_elements,element " +
//												  "FROM resource_elements " +
//												  "WHERE resources_idresources=? AND classes_idclasses=? AND active=1";
//
//	public static final String selectSynTermsClass = "SELECT  idresource_elements,s.synonym "+
//													 "FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//													 "WHERE resources_idresources=? AND classes_idclasses=? AND t.active=1 AND s.active=1";
//	
//	
//	public static final String selectExternalIDandSource = "SELECT  external_id,s.idsources,s.source_name "+
//											               "FROM resource_elements_extenal_id t JOIN sources s ON t.sources_idsources = s.idsources "+
//											               "WHERE resource_elements_idresource_elements=? AND active=1 ";
//


}
