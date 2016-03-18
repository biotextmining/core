package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.corpora;

public class QueriesCorpora {
	
	public static final String insertCorpus = "INSERT INTO corpus (name) VALUES(?)";
	
	public static final String inactiveCorpus = "UPDATE corpus "+
											    "SET active=0 "+
											    "WHERE idcorpus=? ";
	
	public static final String insertCorpusProperties = "INSERT INTO corpus_properties " +
														"VALUES(?,?,?)";
	
	public static final String selectCorpora = "SELECT * FROM corpus "+
											   "WHERE active=1 "+
											   "ORDER BY idcorpus DESC ";
	
	public static final String selectCorpusProperties  = "SELECT * FROM corpus_properties " +
														 "WHERE corpus_idcorpus=?";
	
	public static final String selectProcesses = "SELECT idprocesses,name,type "+
												 "FROM processes t JOIN processes_type s ON t.processes_type_idprocesses_type = s.idprocesses_type " +
												 "WHERE active=1 ";
	
	public static final String selectCorpusProcesses = "SELECT idprocesses, name, type "+
											"FROM processes p "+
											"JOIN corpus_has_processes c ON p.idprocesses = c.processes_idprocesses "+
											"JOIN processes_type t ON p.processes_type_idprocesses_type = t.idprocesses_type "+
											"WHERE c.corpus_idcorpus=? AND p.active=1 ";
	
	public static final String selectCountCorpusProcesses = "SELECT COUNT(*) "+
															"FROM processes p "+
															"JOIN corpus_has_processes c ON p.idprocesses = c.processes_idprocesses "+
															"WHERE c.corpus_idcorpus=? AND p.active=1 ";
	
	public static final String selectProcessInfoByID = "SELECT idprocesses, name, type "+
													   "FROM processes p "+
													   "JOIN processes_type t ON p.processes_type_idprocesses_type = t.idprocesses_type "+
													   "WHERE idprocesses = ? AND active=1";

	public static final String selectProcessProperties = "SELECT property_name,property_value FROM process_properties " +
														 "WHERE processes_idprocesses=? ";
	
	public static final String insertCorpusPublication = "INSERT INTO corpus_has_publications (corpus_idcorpus,publications_id) " +
														 "VALUES(?,?)";
	
	public static final String insertCorpusProcess = "INSERT INTO corpus_has_processes(corpus_idcorpus,processes_idprocesses) VALUES (?,?) ";
	
	public static final String selectCorpusPublications = "SELECT publications.id, publications.other_id,publications.title, publications.authors, publications.date, publications.status, "+
														  "publications.journal, publications.volume, publications.issue, publications.pages,publications.external_links, publications.abstract, publications.available_pdf "+
														  "FROM corpus_has_publications AS q JOIN publications ON (q.publications_id=id) "+
														  "WHERE q.corpus_idcorpus=? ";

	public static final String selectCorporaInformationByID = "SELECT * FROM corpus "+
															  "WHERE idcorpus=? AND active=1";

	public static final String selectPublicationsbyID = "SELECT id, other_id,title,authors, date, status,journal, volume, issue, pages,external_links, abstract, available_pdf "+
														"FROM publications "+
														"WHERE id=? ";
	
	public static final String corpusSize = "SELECT COUNT(*) FROM corpus_has_publications "+
											"WHERE corpus_idcorpus=? ";

	public static final String updateCorpusName = "UPDATE corpus "+
											      "SET name=? "+
											      "WHERE idcorpus=? ";
	



}
