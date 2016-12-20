package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
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
		
		String sqlString = "SELECT COUNT(*) FROM auth_user_data_objects AS a "
							+"INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id " 
							+"INNER JOIN corpus_has_processes as c ON c.chp_process_id = b.pro_id " 
							+"WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? ";
		
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, userID);
		qry.setParameter(1, str);
		qry.setParameter(2, corpusId);
		
		return ((BigInteger)qry.uniqueResult()).intValue();
	}

	@Override
	public List<Corpus> findQueriesByAttributes(Long auId, String corpusstr) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id "
				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, corpusstr);
		qry.addEntity("corpus", Corpus.class);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}
	
	@Override
	public List<Processes> findProcessesByCorpusId(Long corpusId,Long userID,String str) {

		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id "
							+ "INNER JOIN corpus_has_processes as c ON c.chp_process_id = b.pro_id "
							+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND c.chp_corpus_id = ? ";
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
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN corpus as b ON a.audo_uid_resource = b.crp_id "
				+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, resourceType);
		qry.setParameter(2, permission);
		qry.addEntity("queries", Corpus.class);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}



	@Override
	public List<Corpus> findCorpusByPublicationId(Long publicationId) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT distinct c.* FROM textminingcarbontest.corpus as c " +
							"INNER JOIN corpus_has_publications as cp " +
							"WHERE c.crp_id = cp.chp_corpus_id and cp.chp_publication_id = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, publicationId);
		qry.addEntity(Corpus.class);
		@SuppressWarnings("unchecked")
		List<Corpus> result = qry.list();

		return result;
	}
}
