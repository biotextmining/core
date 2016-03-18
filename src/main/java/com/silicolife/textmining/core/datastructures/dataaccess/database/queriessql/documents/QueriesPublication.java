package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.documents;

public class QueriesPublication {
	
	/**
	 * Publication IndentifierTypeID
	 */
	public static final String selectPublicationIndetifierTypeID = "SELECT id_type FROM publications_id_type WHERE name=? ";	
	
	public static final String selectPublicationIDForPublicationOtherID = "SELECT id FROM publications WHERE other_id=? AND publications_id_type_id_type=? ";	
	
	public static final String insertIndetifierType = "INSERT INTO publications_id_type (name) VALUES (?)";
			
	/**
	 * General
	 */

	
	public static final String selectPublicationsResumeInfo="SELECT * " +
														"FROM publications ";
		
	public static final String updatePublicationSQL = "UPDATE publications " +  
													  "SET available_pdf=? " +   
													  "WHERE id=? ";

	
	public static final String selectPublicationsQueryExtra = "SELECT  publications.id , publications.other_id, " +
														"publications.title, publications.authors, publications.date, publications.status, " +
														"publications.journal, publications.volume, publications.issue, publications.pages, " +
														"publications.external_links, publications.abstract, publications.available_pdf " +
														"FROM queries_has_publications join publications ON publications_id=id " +
														"WHERE queries_idqueries=? ";
	
	public static final String selectPublication = "SELECT  publications.id , publications.other_id, " +
												   "publications.title, publications.authors, publications.date, publications.status, " +
												   "publications.journal, publications.volume, publications.issue, publications.pages, " +
												   "publications.external_links, publications.abstract, publications.available_pdf "+
												   "FROM publications "+
												   "WHERE publications.id=?";
	
	
	public static final String selectPublicationType = "SELECT name,id_type FROM publications_id_type AS q "+
													   "JOIN publications ON (q.id_type=publications_id_type_id_type) "+
													   "WHERE id=?";
	
	/**
	 * Queries
	 */
	public static final String insertQueryPublicationSQL = "INSERT INTO queries_has_publications VALUES(?,?) ";
	
	public static final String selectIDAndOtherIDForQuery = "SELECT publications.id,publications.other_id " +
														  "FROM queries_has_publications join publications ON publications_id=id " +
														  "WHERE queries_idqueries=?";
	
	public static final String selectAllPublicationQueryInf = "SELECT publications.id, publications.other_id, " +
															   "publications.title, publications.authors, publications.date, publications.status, " +
															   "publications.journal, publications.volume, publications.issue, publications.pages, " +
															   "publications.external_links, publications.abstract, publications.available_pdf, relevance " +
															   "FROM queries_has_publications AS q JOIN publications ON (q.publications_id=id) "+
															   "NATURAL LEFT JOIN document_relevance " +
															   "WHERE queries_idqueries=? ";

	


}
