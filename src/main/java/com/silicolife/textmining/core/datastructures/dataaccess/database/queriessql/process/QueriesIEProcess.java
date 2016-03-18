package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.process;

public class QueriesIEProcess {

	public static final String selectAnnotationFilterByOffset = "SELECT idannotations,start,end,element,resource_elements_id,normalization_form,classes_idclasses " +
			"FROM annotations "+ 
			"WHERE annotations.processes_idprocesses=? AND annotations.corpus_idcorpus=? AND annotations.publications_id=? AND type='ner' AND start >=? AND start<=? AND active=1 ";
	

	/**
	 * get IEProces Classes
	 */
	public static final String getIEProcessClasses = "SELECT classes_idclasses FROM annotations "+
													 "WHERE processes_idprocesses=? AND active=1 "+
													 "GROUP BY classes_idclasses ";


}
