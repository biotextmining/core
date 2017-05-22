package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.documents.query.QueryFieldsEnum;

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
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, id);
//		qry.setParameter(1, resourceType);
//		qry.addEntity("queries", Queries.class);
		
		String hqlString = "select b from Queries as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.quId "
				+ "where a.id.audoUserId = :id and a.id.audoTypeResource = :resourceType";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("id", id);
		qry.setParameter("resourceType", resourceType);
		@SuppressWarnings("unchecked")
		List<Queries> result = qry.list();

		return result;
	}
	
	@Override
	public Integer countQueriesByAttributes(Long id, String resourceType) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " + "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, id);
//		qry.setParameter(1, resourceType);
//		qry.addEntity("queries", Queries.class);
		
		String hqlString = "select count(*) from Queries as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.quId "
				+ "where a.id.audoUserId = :id and a.id.audoTypeResource = :resourceType";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("id", id);
		qry.setParameter("resourceType", resourceType);
		return ((BigInteger)qry.uniqueResult()).intValue();
	}
	
	
	
	@Override
	public List<Queries> findQueriesByAttributesPaginated(Long id, String resourceType, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ? ";
//	
//		if(!sortBy.equals("none")){
//			String uniqueId = QueryFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
//			String ord = " DESC";
//			if(asc){
//				ord = " ASC";
//			}
//			sqlString = sqlString+"ORDER BY "+ uniqueId+ord;
//		}
//		
//		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
//		
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, id);
//		qry.setParameter(1, resourceType);
//		qry.addEntity("queries", Queries.class);
		
		String hqlString = "select b from Queries as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.quId "
				+ "where a.id.audoUserId = :id and a.id.audoTypeResource = :resourceType";
		
		if(!sortBy.equals("none")){
			String uniqueId = QueryFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
			String ord = "DESC";
			if(asc){
				ord = "ASC";
			}
			hqlString = hqlString + " ORDER BY " + uniqueId + " " + ord;
		}
		
		Query qry = session.createQuery(hqlString);
		qry.setParameter("id", id);
		qry.setParameter("resourceType", resourceType);
		
		qry.setFirstResult(paginationIndex);
		qry.setMaxResults(paginationSize);
		qry.setFetchSize(paginationSize);

		@SuppressWarnings("unchecked")
		List<Queries> result = qry.list();

		return result;
	}
	
	
	@Override
	public List<Queries> findQueriesByAttributes(Long id, String resourceType, String permission) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN queries as b ON a.audo_uid_resource = b.qu_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, id);
//		qry.setParameter(1, resourceType);
//		qry.setParameter(2, permission);
//		qry.addEntity("queries", Queries.class);
		
		String hqlString = "select b from Queries as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.quId "
				+ "where a.id.audoUserId = :id and a.id.audoTypeResource = :resourceType and a.audoPermission = :permission";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("id", id);
		qry.setParameter("resourceType", resourceType);
		qry.setParameter("permission", permission);
		
		@SuppressWarnings("unchecked")
		List<Queries> result = qry.list();

		return result;
	}

	@Override
	public List<Map<Long, String>> findQueriesPublicationsRelevance(Long queryId) {

		return null;

	}
}
