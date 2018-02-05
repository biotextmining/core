package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.documents.ProcessesFieldsEnum;

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
	public List<Processes> findProcessesByAttributesPaginated(long auId,String resourceType, String permission, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id ";
							
		
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
		
		sqlString = sqlString+"WHERE audo_user_id = ? AND audo_type_resource = ? AND pro_active=1 AND audo_permission = ? "+"ORDER BY "+ uniqueId+ord;
		}
		else{
			sqlString = sqlString +"WHERE audo_user_id = ? AND audo_type_resource = ? AND pro_active=1 AND audo_permission = ? ";
		}
		
		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, resourceType);
		qry.setParameter(2, permission);
		qry.addEntity("processes", Processes.class);
		
		@SuppressWarnings("unchecked")
		List<Processes> processes = qry.list();
		
		return processes;

	}
	
	@Override
	public List<Processes> findAllActiveProcessesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM processes as b ";
							
		
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
		
		sqlString = sqlString+"WHERE b.pro_active=1 "+"ORDER BY "+ uniqueId+ord;
		}
		else{
			sqlString = sqlString +"WHERE b.pro_active=1 ";
		}
		
		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.addEntity("processes", Processes.class);
		
		@SuppressWarnings("unchecked")
		List<Processes> processes = qry.list();
		
		return processes;

	}
	
	@Override
	public Integer countProcessesByAttributes(long auId,String resourceType, String permission) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT count(*) FROM auth_user_data_objects AS a " 
							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id ";
							
		

			sqlString = sqlString +"WHERE audo_user_id = ? AND audo_type_resource = ? AND pro_active=1 AND audo_permission = ? ";
		
		
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, resourceType);
		qry.setParameter(2, permission);
		//qry.addEntity("processes", Processes.class);
		
		
		return ((Number)qry.uniqueResult()).intValue();

	}
	
	@Override
	public Integer countAllActiveProcesses() {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT count(*) FROM processes as b ";
			sqlString = sqlString +"WHERE b.pro_active=1 ";
		

		SQLQuery qry = session.createSQLQuery(sqlString);
		//qry.addEntity("processes", Processes.class);	
		return ((Number)qry.uniqueResult()).intValue();

	}
	
	@Override
	public List<Processes> findAllProcessesPaginated(long auId,String resourceType, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
							+ "INNER JOIN processes as b ON a.audo_uid_resource = b.pro_id ";
							
		
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
		
		sqlString = sqlString+"WHERE audo_user_id = ? AND audo_type_resource = ? AND pro_active=1 "+"ORDER BY "+ uniqueId+ord;
		}
		else{
			sqlString = sqlString +"WHERE audo_user_id = ? AND audo_type_resource = ? AND pro_active=1 ";
		}
		
		sqlString = sqlString+" LIMIT "+paginationSize+" OFFSET "+ paginationIndex;
		
		
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, auId);
		qry.setParameter(1, resourceType);
		qry.addEntity("processes", Processes.class);
		
		@SuppressWarnings("unchecked")
		List<Processes> processes = qry.list();
		
		return processes;

	}
	
	@Override
	public List<Processes> findAllProcesses(long auId,String resourceType) {
		Session session = sessionFactory.getCurrentSession();
		
		String hqlString = "select b from Processes as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.proId "
				+ "where a.id.audoUserId = :auId and a.id.audoTypeResource = :resourceType  AND pro_active=1 ";
				
		
		Query qry = session.createQuery(hqlString);
		qry.setParameter("auId", auId);
		qry.setParameter("resourceType", resourceType);
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
