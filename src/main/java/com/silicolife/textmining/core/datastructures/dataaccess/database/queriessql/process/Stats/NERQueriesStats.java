package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process.Stats;

public class NERQueriesStats {
	
	public final static String termDocumentFrequence = "SELECT publications_id,COUNT(element) FROM annotations "+
													   "WHERE processes_idprocesses=? AND corpus_idcorpus=? AND classes_idclasses=? AND element=? AND active=1 "+
													   "GROUP BY publications_id ";
	
	public final static String documentClassTermFrequence = "SELECT element,COUNT(element) FROM annotations "+
															"WHERE processes_idprocesses=? AND corpus_idcorpus=? AND classes_idclasses=? AND publications_id=? AND active=1 "+
															"GROUP BY element ";

	public final static String classNumberAnnotationsPublication = "Select classes_idclasses,COUNT(classes_idclasses) FROM annotations "+
									   							  "WHERE processes_idprocesses=? AND corpus_idcorpus=? AND publications_id=? AND type='ner' AND active=1 "+
									   							  "GROUP BY classes_idclasses ";
	
	public final static String  classNumberAnnotationsCorpus = "SELECT c.class,count(*) "+
														 "FROM annotations AS a JOIN classes AS c ON a.classes_idclasses=c.idclasses "+
														 "WHERE a.processes_idprocesses=? AND a.corpus_idcorpus=? AND active=1 "+
														 "GROUP BY c.class ";

	public final static String relationsAnnotations = "Select COUNT(*) FROM annotations AS a "+
													  "WHERE a.processes_idprocesses=? AND a.corpus_idcorpus=? AND a.publications_id=? AND type='re' AND active=1 ";
	
	public final static String relationsAllProcessAnnottions = "Select COUNT(*) FROM annotations AS a "+
														       "WHERE a.processes_idprocesses=?  AND type='re' AND active=1 ";
	
	public final static String entitiesAllProcessAnnottions = "Select COUNT(*) FROM annotations AS a "+
															   "WHERE a.processes_idprocesses=?  AND type='ner' AND active=1 ";
	
	
	public final static String processEntitiesClassStats = "SELECT cla.class,COUNT(*) FROM annotations AS annot "+
														   "JOIN classes AS cla ON cla.idclasses = annot.classes_idclasses "+
														   "WHERE type='ner' AND processes_idprocesses=? "+
														   "GROUP BY classes_idclasses ";

	public final static String processRelationCardinalities = "SELECT annot.idannotations,side.type,COUNT(*) FROM annotations_side AS side "+
															  "JOIN annotations AS annot ON side.idannotations=annot.idannotations "+
															  "WHERE annot.processes_idprocesses = ? AND annot.type='re' "+
															  "GROUP BY annot.idannotations,side.type "+
															  "ORDER BY annot.idannotations ";

	public final static String processRelationProperty = "SELECT property_value,COUNT(*) FROM annotations_properties AS prop "+
														 "JOIN annotations AS annot ON annot.idannotations=prop.idannotations "+
														 "WHERE annot.processes_idprocesses = ? AND prop.property_name=? "+
														 "GROUP BY property_value ";
	
	public final static String processRelationsLemmas = "SELECT property_value,COUNT(*) FROM annotations_properties AS prop "+
														"JOIN annotations AS annot ON annot.idannotations=prop.idannotations " +
														"WHERE property_name='lemma' AND annot.processes_idprocesses=? and annot.type='re' "+
														"GROUP BY property_value ";

	public final static String processRelationsTypes = "SELECT annot.idannotations,sub_annot.idannotations,side.type,sub_annot.classes_idclasses FROM annotations_side AS side "+
													   "JOIN annotations AS annot ON side.idannotations=annot.idannotations "+
													   "JOIN annotations AS sub_annot ON side.idannotations_sub=sub_annot.idannotations "+
													   "WHERE annot.processes_idprocesses = ? AND annot.type = 're' AND sub_annot.type = 'ner' " +
													   "ORDER BY annot.idannotations ";
	
}
