package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

@Repository
public class ProcessesAoxDaoImpl implements ProcessesAuxDao{
	
	private SessionFactory sessionFactory;

	@Autowired
	public ProcessesAoxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Processes> findProcessesByAttributes(long auId,String resourceType, String permission) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id "
//							+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, auId);
//		qry.setParameter(1, resourceType);
//		qry.setParameter(2, permission);
//		qry.addEntity("queries", Processes.class);
		
		String hqlString = "select b from Processes as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.proId "
				+ "where a.id.audoUserId = :auId and a.id.audoTypeResource = :resourceType and a.audoPermission = :permission";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("auId", auId);
		qry.setParameter("resourceType", resourceType);
		qry.setParameter("permission", permission);
		@SuppressWarnings("unchecked")
		List<Processes> result = qry.list();

		return result;
	}

	@Override
	public List<Processes> findProcessesByPublicationIds(Long publicationId) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT distinct p.* FROM corpus_has_publications_has_processes as cpp "+
//						   "INNER JOIN processes as p on cpp.chphp_processes_id = p.pro_id " +
//						   "WHERE cpp.chphp_publication_id = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, publicationId);
//		qry.addEntity(Processes.class);
		
		String hqlString = "select distinct p from Processes as p "
				+ "inner join CorpusHasPublicationsHasProcesses as cpp on cpp.id.chphpProcessesId = p.proId "
				+ "where cpp.id.chphpPublicationId = :publicationId";
		Query qry = session.createQuery(hqlString );
		qry.setParameter("publicationId", publicationId);
		@SuppressWarnings("unchecked")
		List<Processes> result = qry.list();

		return result;
	}


}
