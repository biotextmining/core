package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process;

public class QueriesIRProcess {
	
	/**
	 * Utils
	 */	
	
	public static final String insertQueryPropertiesSQL = "INSERT INTO queries_properties  VALUES(?,?,?) ";

	
	public static final String inactiveQuery = "UPDATE queries " +  
												 "SET active=0 " +   
												 "WHERE idqueries=? ";
	
	
	public static final String selectQueryPropertiesSQL = "SELECT * " +
													   "FROM queries_properties " +
													   "WHERE queries_id=? ";
	
	public static final String selectQueryByIDSQL = "SELECT *" +
													"FROM queries "+
													"WHERE id=? AND active=1";
	
	/**
	 * News queries
	 */
	public static final String updateQuerySQL = "UPDATE queries "+ 
												"SET date = ?, matching_publications = ?, "+
												"available_abstracts = ?, name = ?, notes = ? " +
												"WHERE idqueries = ?";


	public static final String selectQueryOriginSQL = "SELECT id,description FROM queries_type WHERE id=?";
	
	public static final String selectPublicationsExternalIDForSource = "SELECT pubHasSource.publications_source_internal_id,pubHasSource.publications_id FROM publications_has_publications_source AS pubHasSource "+
																	   "JOIN publications_source AS pSource ON pSource.id=pubHasSource.publications_source_id "+
																	   "WHERE pSource.description=? ";


	public static final String selectQueryPublicationsExternalIDForSource = "SELECT pubHasSource.publications_source_internal_id FROM publications_has_publications_source AS pubHasSource "+
																			"JOIN publications_source AS pSource ON pSource.id=pubHasSource.publications_source_id "+
																			"JOIN queries_has_publications AS queryHasPub ON queryHasPub.publications_id=pubHasSource.publications_id "+
																			"WHERE queryHasPub.queries_id=? AND pSource.description=? ";

}
