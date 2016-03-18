package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.documents;

/**
 * 
 * 
 * 
 * 
 * @author Hugo Costa
 * 
 * 
 *
 */

public class QueriesAnnotatedDocument {
	
	
	/**
	 * Entities Annotations
	 * 
	 */
	public final static String selectEntityAnnotations = "SELECT idannotations,start,end,element,resource_elements_id,normalization_form,classes_idclasses " +
													     "FROM annotations "+ 
													     "WHERE annotations.processes_idprocesses=? AND annotations.corpus_idcorpus=? AND annotations.publications_id=? AND type='ner' AND active=1 " +
													     "ORDER BY start ";
	
	public final static String insertEntityAnnotation = "INSERT INTO annotations (processes_idprocesses,corpus_idcorpus, publications_id,start,end,element,resource_elements_id,normalization_form,classes_idclasses,type) " +
			  											"VALUES (?,?,?,?,?,?,?,?,?,1)";
	
	public final static String searchEntityAnnotationbyID =  "SELECT idannotations,start,end,element,resource_elements_id,normalization_form,classes_idclasses " +
															 "FROM annotations "+ 
															 "WHERE  type='ner' AND idannotations = ?";
	
	/**
	 * Event Annotations
	 * 
	 */
	public final static String insertEventAnnotation = "INSERT INTO annotations (processes_idprocesses,corpus_idcorpus, publications_id,start,end,element,resource_elements_id,normalization_form,classes_idclasses,type,classification_re,clue) " +
													   "VALUES (?,?,?,?,?,?,?,?,?,2,?,?)";
	
	public final static String selectEventAnnotations = "SELECT idannotations,start,end,clue,classification_re FROM annotations "+
											  			"WHERE processes_idprocesses=? AND corpus_idcorpus=? AND publications_id=? AND type='re' AND active=1 ";
	
	public final static String selectEventAnnotationsByID = "SELECT idannotations,start,end,clue,classification_re FROM annotations "+
  														"WHERE type='re' AND idannotations = ? ";
	
	public final static String selectEventEntitiesAnnotations = "SELECT q.idannotations,q.idannotations_sub,q.type "+
																"FROM annotations_side AS q "+
																"JOIN annotations AS p ON p.idannotations=q.idannotations "+
																"WHERE p.processes_idprocesses=? AND p.corpus_idcorpus=? AND p.publications_id=? AND p.type='re' AND p.active=1  ";
	
	public final static String selectEventEntitiesAnnotationsByID = "SELECT q.idannotations,q.idannotations_sub,q.type "+
																	"FROM annotations_side AS q "+
																	"JOIN annotations AS p ON p.idannotations=q.idannotations "+
																	"WHERE p.type='re' AND p.idannotations=? ";
	
	public final static String selectEventProperties = "SELECT q.idannotations,q.property_name,q.property_value "+
													   "FROM annotations_properties AS q "+
													   "JOIN annotations AS p ON p.idannotations=q.idannotations "+
													   "WHERE p.processes_idprocesses=? AND p.corpus_idcorpus=? AND p.publications_id=? AND p.type='re' AND p.active=1 ";
	
	public final static String selectEventPropertiesByID = "SELECT q.idannotations,q.property_name,q.property_value "+
													   	   "FROM annotations_properties AS q "+
													   	   "JOIN annotations AS p ON p.idannotations=q.idannotations "+
													   	   "WHERE p.type='re' AND p.idannotations=? ";
	
	public final static String getEventInformation = "SELECT corpus_idcorpus, publications_id "+
											   "FROM annotations "+
											   "WHERE idannotations=? AND active=1 ";

	
	/**
	 *  General Annotations
	 */
	public final static String removeAnnotation = "UPDATE annotations "+
												  "SET active=0 "+
			 									  "WHERE idannotations=? ";


	public final static String updateClassAnnotation = "UPDATE annotations " +
													   "SET classes_idclasses=? , resource_elements_id=NULL "+
													   "WHERE start=? AND end=? AND corpus_idcorpus=? AND processes_idprocesses=? AND publications_id=? ";
	
	/**
	 * Annotation properties
	 */
	
	public final static String insertAnnotationProperties = "INSERT IGNORE INTO annotations_properties" +
														    " VALUES(?,?,?) ";
	
	public final static String removeAnnotaionProperties = "DELETE FROM annotations_properties" +
														   "WHERE idannotations=? AND property_name=? ";
	
	public final static String updateAnnotationProperties = "UPDATE annotations_properties "+
															"SET property_value=? " +
															"WHERE idannotations=? AND property_name=? ";
	
	public final static String removeAnnotationProperties = "DELETE FROM annotations_properties "+
															"WHERE idannotations=?";

	
	/**
	 * Annotations Side
	 */
	
	public final static String insertAnnotationSide = "INSERT IGNORE INTO annotations_side " +
													  "VALUES(?,?,?)"; // 1 - left 2 - Right
	
	public final static String removeAnnotationSide = "DELETE FROM annotations_side" +
													  "WHERE idannotation=? AND idannotation_sub=?";


	public final static String removeRelationSide = "DELETE FROM annotations_side " +
													"WHERE idannotations=?";


	/**
	 * Annotations Log
	 * 
	 */

	public final static String selectDocumentLogAnnotations = "SELECT * FROM annotations_log "+
													  "WHERE corpus_idcorpus=? AND processes_idprocesses=? AND publications_id=?";
	
	public final static String selectProcessLogAnnotations = "SELECT * FROM annotations_log "+
													  		 "WHERE corpus_idcorpus=? AND processes_idprocesses=? ";

	
	public final static String addAnnotationLog = "INSERT INTO annotations_log(annotations_idannotations,corpus_idcorpus,processes_idprocesses,publications_id,type,old,new,notes,date) "+
												  "VALUES(?,?,?,?,?,?,?,?,?) ";
	
	
	public final static String selectAnnotationIDForAnnotaionLogs = "SELECT annot.idannotations,annot.type FROM annotations_log AS log "+
																	"JOIN annotations AS annot ON log.annotations_idannotations=annot.idannotations "+
																	"WHERE log.corpus_idcorpus=? AND log.processes_idprocesses=? "+
																	"GROUP BY log.annotations_idannotations ";

}
