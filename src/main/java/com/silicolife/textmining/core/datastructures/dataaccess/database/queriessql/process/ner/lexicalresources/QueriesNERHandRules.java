package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process.ner.lexicalresources;

public class QueriesNERHandRules {

	public static final String findDictionaryTermCaseSensitive = "SELECT idresource_elements " +
																 "FROM resource_elements " +
																 "WHERE resources_idresources=? AND element LIKE binary (?) AND active=1 AND classes_idclasses=? ";
	
	public static final String findDictionaryTermSynonymCaseSensitive = "SELECT  idresource_elements,classes_idclasses "+
			 															"FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
			 															"WHERE resources_idresources=? AND synonym LIKE binary (?) AND t.active=1 AND s.active=1 AND t.classes_idclasses=? ";
	
	public static final String findDictionaryTerm = "SELECT idresource_elements " +
												    "FROM resource_elements " +
												    "WHERE resources_idresources=? AND element=? AND active=1 AND classes_idclasses=? ";
	
	public static final String findDictionaryTermSynonym = "SELECT  idresource_elements,classes_idclasses "+
														   "FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
														   "WHERE resources_idresources=? AND synonym=? AND t.active=1 AND s.active=1 AND t.classes_idclasses=? ";

}
