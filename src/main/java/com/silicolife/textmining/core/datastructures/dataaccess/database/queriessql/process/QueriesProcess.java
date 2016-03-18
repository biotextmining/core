package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process;


public class QueriesProcess {
	
	/**
	 * General
	 */
	public final static String existProcessType = "SELECT * " +
			   									  "FROM processes_type " +
			   									  "WHERE type=? ";
	
	public final static String insertProcessType = "INSERT INTO processes_type (type) VALUES(?) ";
	
	public final static String insertProcess = "INSERT INTO processes (processes_type_idprocesses_type,name) " +
			   								   "VALUES (?,?)";
	
	public final static String inactivateProcess = "UPDATE processes "+
													"SET active=0 "+
													"WHERE idprocesses = ? ";
	
	/**
	 * Process Properties
	 */
	
	public final static String insertProcessProperties = "INSERT INTO process_properties (processes_idprocesses,property_name,property_value) " +
			   											 "VALUES (?,?,?)";
	
	public static final String insertQuerySQL = "INSERT INTO queries VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	
	public static final String removeQuery = "DELETE FROM queries " +
											 "WHERE idqueries=? ";
	
	public static final String removeQueryProperties = "DELETE FROM queries_properties " +
													    "WHERE queries_idqueries=? ";	
	
	public static final String removeQueryPublicationLinking = "DELETE FROM queries_has_publications " +
															   "WHERE queries_idqueries=? ";
	
	
	public static final String selectAllQueriesSQL = "SELECT * " +
													 "FROM queries "+
													 "WHERE active=1 "; 
	
	/**
	 * Specific Queries 
	 * 
	 */
	
	public static final String getProcessDescription = "SELECT name FROM processes "+
													   "WHERE idprocesses=?";

	
	public static final String removeAllTerms = "DELETE FROM annotations "+
		    									"WHERE processes_idprocesses=? ";

	public static final String removeProcess = "DELETE FROM processes "+
											   "WHERE idprocesses=? ";

	public static final String removeAllTermsProperties = "DELETE IGNORE annotations_properties,annotations FROM annotations_properties INNER JOIN annotations "+
														  "WHERE annotations_properties.idannotations=annotations.idannotations AND annotations.processes_idprocesses=? ";

	public static final String removeProcessProperties = "DELETE FROM process_properties "+
														 "WHERE processes_idprocesses=? ";

	public static final String removeProcessFromCorpus = "DELETE FROM corpus_has_processes "+
														 "WHERE processes_idprocesses=? ";

	public static final String removeAllAnnotationSides = "DELETE IGNORE annotations_side,annotations FROM annotations_side INNER JOIN annotations "+
														  "WHERE annotations_side.idannotations=annotations.idannotations AND annotations.processes_idprocesses=? ";

	public static final String updateProcessName = "UPDATE processes "+
												   "SET name=? "+
												   "WHERE idprocesses = ? ";

	public static final String selectQuryOriginTypeSQL = "SELECT id FROM queries_type WHERE description=? ";

	public static final String insertOriginTypeSQL = "INSERT INTO queries_type VALUES(?,?) ";
	
}
