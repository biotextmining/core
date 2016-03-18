package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.resources;


public class QueriesResources {
	
//	public static final String existElementInResource = "SELECT idresource_elements,classes_idclasses " +
//														"FROM resource_elements " +
//														"WHERE resources_idresources=? AND element LIKE binary (?) AND active=1"; 
//	
//	public static final String existElementSynonyms = "SELECT  idresource_elements,classes_idclasses "+
//			 										"FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//			 										"WHERE resources_idresources=? AND synonym LIKE binary (?) AND t.active=1 AND s.active=1 "; 
//	
//	public static final String getElementsSynonyms = "SELECT s.synonym,t.classes_idclasses,t.idresource_elements "+
//			   									"FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//			   									"WHERE resources_idresources=? AND s.active=1 AND t.active=1";
//	
//	public static final String insertElement = "INSERT INTO resource_elements (element,classes_idclasses,resources_idresources) "+
//											   "VALUES (?,?,?)";
//	
//	public final static String insertElementSynonyms = "INSERT INTO synonyms (resource_elements_idresource_elements,synonym) "+
//			  									 	   "VALUES (?,?)";
//	
//	public final static String insertElementExternalID = "INSERT INTO resource_elements_extenal_id (resource_elements_idresource_elements,resource_elements_resources_idresources,external_id,sources_idsources) "+
//														 "VALUES (?,?,?,?)";
//	
//	public static final String getClassesIDForClasseName = "SELECT idclasses FROM classes "+
//			   											   "WHERE class=?";
//
//	public static final String insertClass = "INSERT INTO classes (class) VALUES (?)";
//	
//	public static final String updateElement = "UPDATE resource_elements "+
//			   								   "SET element=? , classes_idclasses=? "+
//			   								   "WHERE idresource_elements=? AND active=1";
//	
//	public static final String updateResourceElement ="UPDATE resource_elements "+
//	 												 "SET element=?"+
//	 												 "WHERE idresource_elements=? AND resources_idresources=? AND classes_idclasses=? AND active=1 ";
//	
//	public static final String updateResourceElement2 ="UPDATE resource_elements "+
//												      "SET element=? , classes_idclasses=? "+
//												      "WHERE idresource_elements=? AND resources_idresources=? AND active=1 ";
//	
//	public static final String removeElement = "DELETE FROM resource_elements "+
//			   								   "WHERE idresource_elements=? AND active=1 ";
//	
//	public static final String selectElementClass = "SELECT idresource_elements,element " +
//												    "FROM resource_elements " +
//												    "WHERE resources_idresources=? AND classes_idclasses=? AND active=1 ";
//	
//	public static final String getClassIDForClass = "SELECT idclasses FROM classes WHERE class=?";
//	
//	public static final String selectAllClasses = "SELECT * FROM classes ";
//	
//	public static final String selectAllResourceElement = "SELECT * FROM resource_elements "+
//														  "WHERE resources_idresources=? AND active=1 "+
//														  "ORDER BY idresource_elements ";
//	
//	public static final String insertResourceClassContent = "INSERT IGNORE resources_content (resources_idresources,classes_idclasses) "+
//															"VALUES (?,?) ";
//	
//	public static final String selectResourceClassesContent = "SELECT classes_idclasses FROM resources_content "+
//			 												  "WHERE resources_idresources=? ";	
//	
////	public static final String selectElementByID = "SELECT * FROM resource_elements "+
////												   "WHERE idresource_elements=? AND active=1 ";
//	
//	public static final String selectElementByIDWihoutActiveFilter = "SELECT * FROM resource_elements "+
//												   "WHERE idresource_elements=? ";
//	
//	public static final String selectResourceElementAndClass="SELECT element,classes.class "+
//			 												 "FROM resource_elements AS q JOIN classes ON (q.classes_idclasses=classes.idclasses) "+
//			 												 "WHERE resources_idresources=? AND q.active=1  ";
//	
//	public static final String selectResourceElement ="SELECT element, idresource_elements "+
//			 										  "FROM resource_elements "+
//			 										  "WHERE resources_idresources=? AND active=1 ";
//	
//	public static final String selectElementExternalID = "SELECT * FROM resource_elements "+
//			 							 				 "JOIN resource_elements_extenal_id ON resource_elements_extenal_id.resource_elements_idresource_elements=resource_elements.idresource_elements "+
//			 							 				 "WHERE resources_idresources=? AND resource_elements.active=1";
//	
//	public static final String selectElementSynByID = "SELECT resource_elements_idresource_elements,synonym " +
//												"FROM synonyms " +
//												"WHERE resource_elements_idresource_elements=? AND active=1";
//	
//	public static final String selectSourcesIDByName = "SELECT idsources FROM sources "+
//													   "WHERE source_name=?";
//	
//	public static final String selectResourceTypes = "SELECT idresources_type FROM resources_type "+
//													 "WHERE type=? ";
//	
//	
//	public static final String insertSource = "INSERT INTO sources (source_name) " +
//											  "VALUES(?) ";
//	
//	public static final String insertResourceType = "INSERT INTO resources_type (type) VALUES(?) ";
//	
//	public static final String insertResource = "INSERT INTO resources (name,resources_type_idresources_type,note) "+
//												"VALUES (?,?,?)";
//	
//	public static final String inactiveResource = "UPDATE resources "+
//												  "SET active=0 "+
//												  "WHERE idresources=? ";
//	
//
//	public static final String selectAllresources = "SELECT res.id,type,name,notes "+
//													"FROM resources AS res JOIN resources_type ON (res.resources_type_id=resources_type.id) " +
//													"WHERE res.active=1 ";
//	
//	public static final String getResourcesInfo =	"SELECT idresources,type,name,note,active "+
//													"FROM resources AS q JOIN resources_type ON (q.resources_type_idresources_type=resources_type.idresources_type) " +
//													"WHERE idresources=? ";
//	
//	public static final String selectResourceFilterByType = "SELECT res.id,name,notes "+
//			   												"FROM resources AS res JOIN resources_type ON (res.resources_type_id=resources_type.id) "+
//			   												"WHERE resources_type.type=? AND res.active=1 "+
//			   												"ORDER BY res.id DESC ";
//	
//	public static final String selectResourceInformtaionByID = "SELECT idresources,name,note "+
//															   "FROM resources "+
//															   "WHERE idresources=? AND active=1 ";
//	
//	public static final String insertRelationType = "INSERT INTO relation_type (type)" +
//													" VALUES(?)";
//	
//	public static final String insertResourceElementRelation = "INSERT IGNORE resource_elements_relation (idresource_elements_a,idresource_elements_b,relation_type_idrelation_type) " +
//															   " VALUES(?,?,?)";
//
//	public static final String selectResourceElementCount = "SELECT COUNT(*) FROM resource_elements " +
//															"WHERE resources_idresources=? AND active=1 ";
//
//	public static final String selectRelationsOntology = "SELECT t.idresource_elements_a, t.idresource_elements_b "+
//														 "FROM resource_elements_relation t JOIN resource_elements s ON t.idresource_elements_a = s.idresource_elements "+
//														 "WHERE s.resources_idresources=? ";
//	
//	public static final String  selectResourceclassContent = "SELECT classes_idclasses,classes.class "+
//														     "FROM resources_content AS q JOIN classes ON (q.classes_idclasses=classes.idclasses) "+
//														     "WHERE resources_idresources=?";
	
//	public static String resourcesTotalTerms = "SELECT * "+
//											  "FROM "+GlobalTablesName.resourceTotalTerms+" "+
//											  "WHERE resources_idresources=? ";
//	
//	public static String resourcesTotalSynonyms = "SELECT * "+
//												  "FROM "+GlobalTablesName.resourceTotalSynonyms+" "+
//												  "WHERE resources_idresources=? ";
//	
//	public static String totalResourceClassTerms = "SELECT * "+
//												   "FROM "+GlobalTablesName.resourceTotalTermsClass+" "+
//												   "WHERE resources_idresources=? AND classes_idclasses=? ";
//	
//	public static String totalResourceClassSyn = "SELECT * "+
//												  "FROM "+GlobalTablesName.resourceTotalTermsSynonymsClass+" "+
//												  "WHERE resources_idresources=? AND classes_idclasses=? ";
	
//	public static final String totalResourceTerms = "SELECT COUNT(*) FROM resource_elements "+
//			"WHERE resources_idresources=? AND active=1 ";
//
//	public static final String totalResourceSyn = "SELECT COUNT(*) "+
//			"FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//			"WHERE resources_idresources=? AND t.active=1 AND s.active=1 ";
//
//	public static final String totalResourceClassTerms = "SELECT COUNT(*) FROM resource_elements "+
//			"WHERE resources_idresources=? AND classes_idclasses=? AND active=1 ";
//
//	public static final String totalResourceClassSyn = "SELECT COUNT(*) "+
//			"FROM resource_elements t JOIN synonyms s ON t.idresource_elements = s.resource_elements_idresource_elements "+
//			"WHERE resources_idresources=? AND classes_idclasses=? AND t.active=1 AND s.active=1";
//
//	public static final String updateClass = "UPDATE classes "+
//			"SET class=? "+
//			"WHERE idclasses=? "; 
//
//	public static final String updateSynonym = "UPDATE synonyms "+
//			"SET synonym=? "+
//			"WHERE resource_elements_idresource_elements=? AND synonym=? AND active=1 ";
//	
//	public static final String inactiveElement = "UPDATE resource_elements "+
//										   "SET active=0 "+
//										   "WHERE idresource_elements=?";
//	
//	public static final String inactiveAllClassElement = "UPDATE resource_elements "+
//			   											 "SET active=0 "+
//			   											 "WHERE resources_idresources=? AND classes_idclasses=? ";
//	
//	public static final String inactiveAllElements = "UPDATE resource_elements "+
//													 "SET active=0 "+
//													 "WHERE resources_idresources=? ";
//
//	public static final String removeElementSynonyms = "UPDATE synonyms "+
//			"SET active=0 "+
//			"WHERE resource_elements_idresource_elements=? AND active=1 ";
//
//	public static final String removeElementSynomym = "UPDATE synonyms "+
//			"SET active=0 "+
//			"WHERE resource_elements_idresource_elements=? AND synonym=? AND active=1 ";
//
//	public static final String selectRelationTypeID = "SELECT idrelation_type "+
//												"FROM relation_type "+
//												"WHERE type=? ";
//
//	public static final String existExternalIDForTem = "SELECT * FROM resource_elements_extenal_id "+
//													   "WHERE resource_elements_idresource_elements=? AND external_id=? AND sources_idsources=? ";
//	
//	public static final String removeClassContent = "DELETE FROM resources_content "+
//													"WHERE resources_idresources=? AND classes_idclasses=? ";
//
//	public static final String getResourceElementtype = "SELECT rt.type FROM resource_elements AS re "+
//												  "JOIN resources AS res ON re.resources_idresources=res.idresources "+
//												  "JOIN resources_type AS rt ON rt.idresources_type=res.resources_type_idresources_type "+
//												  "WHERE re.idresource_elements=? ";
//
//	public static final String getTermResources = "SELECT res.idresources,res.name,res.note,rt.type FROM resource_elements AS re "+
//											"JOIN resources AS res ON re.resources_idresources=res.idresources "+
//											"JOIN resources_type AS rt ON rt.idresources_type=res.resources_type_idresources_type "+
//											"WHERE re.idresource_elements=? " ;
//
//	public static final String findPartialMathingInDictionaries = "SELECT * FROM synonyms AS syn "+
//														    "JOIN resource_elements AS elem ON syn.resource_elements_idresource_elements=elem.idresource_elements "+
//														    "WHERE elem.resources_idresources=? AND elem.classes_idclasses = ? AND syn.synonym = BINARY ? ";
//	
//	public static final String findPartialMathingInDictionariesCaseAndNoWholeWord = "SELECT * FROM synonyms AS syn "+
//														    "JOIN resource_elements AS elem ON syn.resource_elements_idresource_elements=elem.idresource_elements "+
//														    "WHERE elem.resources_idresources=? AND elem.classes_idclasses = ? AND syn.synonym LIKE BINARY ? ";
// 
//	public static final String findPartialMathingInDictionariesNoMatchCaseAndNoWholeWord = "SELECT * FROM synonyms AS syn "+
//																					 "JOIN resource_elements AS elem ON syn.resource_elements_idresource_elements=elem.idresource_elements "+
//																					 "WHERE elem.resources_idresources=? AND elem.classes_idclasses = ? AND syn.synonym LIKE ? ";
//
//	public static final String getallSynonymsMerg ="SELECT syn.resource_elements_idresource_elements,syn.synonym FROM synonyms AS syn "+
//												   "JOIN resource_elements AS res ON res.idresource_elements=syn.resource_elements_idresource_elements "+
//												   "WHERE res.resources_idresources=? AND res.active=1 AND syn.active=1 ";
//
//	public static final String getallExternalIdsMerge = "SELECT res.idresource_elements,ext.external_id,ext.sources_idsources,sc.source_name FROM resource_elements_extenal_id AS ext "+
//													    "JOIN  resource_elements AS res ON res.idresource_elements = ext.resource_elements_idresource_elements "+
//													    "JOIN sources AS sc ON sc.idsources=ext.sources_idsources " +
//													    "WHERE res.resources_idresources=? AND res.active=1 AND ext.active=1";
//
//	public static final String renameResource = "UPDATE resources "+
//												"SET name=? "+
//												"WHERE idresources =? ";
//	
}
