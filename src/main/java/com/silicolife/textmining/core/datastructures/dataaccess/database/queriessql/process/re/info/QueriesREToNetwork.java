package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process.re.info;

public class QueriesREToNetwork {

	
	public static final String getAllLemmas = "SELECT property_value FROM annotations_properties "+
								 			  "WHERE property_name='lemma' " +
								 			  "GROUP BY property_value";
	
	public static final String getAllClassesSide = "SELECT classes_idclasses FROM annotations_side AS side "+
												   "JOIN annotations AS annot ON annot.idannotations=side.idannotations_sub " +
												   "WHERE side.type=? " +
												   "GROUP BY annot.classes_idclasses ";
}
