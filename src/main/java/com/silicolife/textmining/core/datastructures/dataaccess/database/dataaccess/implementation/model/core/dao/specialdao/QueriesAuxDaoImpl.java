package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;

@Repository
public class QueriesAuxDaoImpl implements QueriesAuxDao {
	private SessionFactory sessionFactory;

	@Autowired
	public QueriesAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Queries> findQueriesByAttributes(Long id, String resourceType) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, id);
		qry.setParameter(1, resourceType);
		qry.addEntity("queries", Queries.class);
		@SuppressWarnings("unchecked")
		List<Queries> result = qry.list();

		return result;
	}
	
	
	@Override
	public List<Queries> findQueriesByAttributes(Long id, String resourceType, String permission) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
				+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, id);
		qry.setParameter(1, resourceType);
		qry.setParameter(2, permission);
		qry.addEntity("queries", Queries.class);
		@SuppressWarnings("unchecked")
		List<Queries> result = qry.list();

		return result;
	}

	@Override
	public List<Map<Long, String>> findQueriesPublicationsRelevance(Long queryId) {

		return null;

	}
}
