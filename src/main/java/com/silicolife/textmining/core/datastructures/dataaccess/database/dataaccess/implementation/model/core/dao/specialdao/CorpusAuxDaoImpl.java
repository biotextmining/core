package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusFieldsEnum;
import com.silicolife.textmining.core.datastructures.documents.ProcessesFieldsEnum;

@Repository
public class CorpusAuxDaoImpl implements CorpusAuxDao {
	private SessionFactory sessionFactory;

	@Autowired
	public CorpusAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public Integer getCorpusDocumentNumber(Long corpusID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(CorpusHasPublications.class, "corpushaspublications");
		c.add(Restrictions.eq("corpushaspublications.corpus.crpId", corpusID));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public Integer getCorpusProcessesNumber(Long corpusId,Long userID,String str) {

		Session session = sessionFactory.getCurrentSession();
		
//		String sqlString = "SELECT COUNT(*) FROM auth_user_data_objects AS a "
//							+"INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id " 
//							+"INNER JOIN corpus_has_processes as c ON c.chp_process_id = b.pro_id " 
//							+"WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? ";
//		
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, userID);
//		qry.setParameter(1, str);
//		qry.setParameter(2, corpusId);
//		
		String hqlString = "select count(*) from AuthUserDataObjects as a "
			+ "inner join Processes as b on a.id.audoUidResource = b.proId "
			+ "inner join CorpusHasProcesses as c on c.id.chpProcessId = b.proId "
			+ "where a.id.audoUserId = :userId and a.id.audoTypeResource = :str and c.id.chpCorpusId = :corpusId";

		Query qry = session.createQuery(hqlString);
		qry.setParameter("userId", userID);
		qry.setParameter("str", str);
		qry.setParameter("corpusId", corpusId);

		return ((Number)qry.uniqueResult()).intValue();
	}

	@Override
	public List<Corpus> findQueriesByAttributes(Long auId, String corpusStr) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, auId);
//		qry.setParameter(1, corpusstr);
//		qry.addEntity("corpus", Corpus.class);
		
		String hqlString = "select b from Corpus as b  "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.crpId "
				+ "where a.id.audoUserId = :auId and a.id.audoTypeResource = :corpusStr";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("auId", auId);
		qry.setParameter("corpusStr", corpusStr);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}
	

	public List<Processes> findProcessesByCorpusId(Long corpusId,Long userId,String str) {

		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id "
//							+ "INNER JOIN corpus_has_processes as c ON c.chp_process_id = b.pro_id "
//							+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? ";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, userId);
//		qry.setParameter(1, str);
//		qry.setParameter(2, corpusId);
//		qry.addEntity("processes", Processes.class);
		
		String hqlString = "select b from Processes as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.proId "
				+ "inner join CorpusHasProcesses as c on c.id.chpProcessId = b.proId "
				+ "where a.id.audoUserId = :userId and a.id.audoTypeResource = :str and c.id.chpCorpusId = :corpusId";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("userId", userId);
		qry.setParameter("str", str);
		qry.setParameter("corpusId", corpusId);
		@SuppressWarnings("unchecked")
		List<Processes> processes = qry.list();

		return processes;
	}
	
	
	public Integer CountCorpusByAttributes(Long auId, String corpusstr) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id AND b.crp_active=1 "
				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, corpusstr);
		qry.addEntity("corpus", Corpus.class);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result.size();
	}
	
	@Override
	public List<Corpus> findQueriesByAttributesPaginated(Long auId, String corpusstr, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Session session = sessionFactory.getCurrentSession();
		
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id AND b.crp_active=1 ";
		
		if(!sortBy.equals("none")){
			if(sortBy.equals("processesNumber") | sortBy.equals("publicationsNumber")){
				String uniqueId = CorpusFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
				
				String ord = " DESC";
				if(asc){
					ord = " ASC";
				}
				sqlString = sqlString + "LEFT JOIN "+uniqueId+" as c ON b.crp_id = c.chp_corpus_id " + " WHERE audo_user_id = ? AND audo_type_resource = ? AND c.crp_active=1 "+ "GROUP BY b.crp_id ORDER BY COUNT(chp_corpus_id) " + ord;
			}
			else{
			String uniqueId = CorpusFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
			String ord = " DESC";
			if(asc){
				ord = " ASC";
			}
			sqlString = sqlString+ "WHERE audo_user_id = ? AND audo_type_resource = ? "+"ORDER BY "+ uniqueId+ord;
			}
		}
		else{
			sqlString = sqlString + "WHERE audo_user_id = ? AND audo_type_resource = ? ";
		}
		
		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
		
		
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, corpusstr);
		qry.addEntity("corpus", Corpus.class);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}




@Override
	public List<Processes> findProcessesByCorpusIdPaginated(Long corpusId,Long userID,String str, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {

		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id "
							+ "INNER JOIN corpus_has_processes as c ON c.chp_process_id = b.pro_id ";
							
		
		if(!sortBy.equals("none")){
		String uniqueId = ProcessesFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
		String ord = " DESC";
		if(asc){
			ord = " ASC";
		}
		
		if(sortBy.equals("processType") | sortBy.equals("processOrigin")){
			String tableName = "";
			String idAssociation = "";
			if(sortBy.equals("processType")) {
				tableName = "process_types";
				idAssociation = " as pt on pt.pt_id = b.pro_process_type_id ";
			}
			if(sortBy.equals("processOrigin")) {
				tableName = "process_origins";
				idAssociation = " as po on po.po_id = b.pro_process_origin_id ";
				}
			
			sqlString = sqlString + "INNER JOIN "+tableName+idAssociation;
		}
		
		sqlString = sqlString+"WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? "+"ORDER BY "+ uniqueId+ord;
		}
		else{
			sqlString = sqlString +"WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? ";
		}
		
		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, userID);
		qry.setParameter(1, str);
		qry.setParameter(2, corpusId);
		qry.addEntity("processes", Processes.class);
		
		@SuppressWarnings("unchecked")
		List<Processes> processes = qry.list();

		return processes;
	}

	@Override
	public List<Corpus> findCorpusByAttributes(long auId, String resourceType,String permission) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, auId);
//		qry.setParameter(1, resourceType);
//		qry.setParameter(2, permission);
//		qry.addEntity("queries", Corpus.class);
		
		String hqlString = "select b from Corpus as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.crpId "
				+ "where a.id.audoUserId = :auId and a.id.audoTypeResource = :resourceType and a.audoPermission = :permission";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("auId", auId);
		qry.setParameter("resourceType", resourceType);
		qry.setParameter("permission", permission);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}



	@Override
	public List<Corpus> findCorpusByPublicationId(Long publicationId) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT distinct c.* FROM corpus as c " +
//							"INNER JOIN corpus_has_publications as cp " +
//							"WHERE c.crp_id = cp.chp_corpus_id and cp.chp_publication_id = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, publicationId);
//		qry.addEntity(Corpus.class);
		
		String hqlString = "select distinct c from Corpus as c "
				+ "inner join CorpusHasPublications as cp "
				+ "where c.crpId = cp.id.chpCorpusId and cp.id.chpPublicationId = :publicationId";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("publicationId", publicationId);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}
}
