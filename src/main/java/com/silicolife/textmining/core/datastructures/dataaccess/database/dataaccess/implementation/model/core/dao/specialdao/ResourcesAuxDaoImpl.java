package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;
import com.silicolife.textmining.core.datastructures.documents.query.QueryFieldsEnum;
import com.silicolife.textmining.core.datastructures.resources.ResourceFieldsEnum;

@Repository
public class ResourcesAuxDaoImpl implements ResourcesAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public ResourcesAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Resources> findResourcesByAttributes(Long userId, Long resourceTypeId, String privilegeResourceType) {
		Session session = sessionFactory.getCurrentSession();

//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a"
//				+ " INNER JOIN resources AS b ON a.audo_uid_resource = b.reso_id"
//				+ " WHERE audo_user_id = ? AND b.reso_resource_type_id = ? AND audo_type_resource = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, userId);
//		qry.setParameter(1, resourceTypeId);
//		qry.setParameter(2, privilegeResourceType);
//		qry.addEntity("queries", Resources.class);
		
		String hqlString = "select b from Resources as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.resoId "
				+ "where a.id.audoUserId = :userId and a.id.audoTypeResource = :privilegeResourceType and b.resourceTypes.rtyId = :resourceTypeId";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("userId", userId);
		qry.setParameter("resourceTypeId", resourceTypeId);
		qry.setParameter("privilegeResourceType", privilegeResourceType);
		@SuppressWarnings("unchecked")
		List<Resources> result = qry.list();

		return result;

	}
	
	@Override
	public List<Resources> findActiveResourcesByAttributes(Long userId, Long resourceTypeId, String privilegeResourceType) {
		Session session = sessionFactory.getCurrentSession();

//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a"
//				+ " INNER JOIN resources AS b ON a.audo_uid_resource = b.reso_id"
//				+ " WHERE audo_user_id = ? AND b.reso_resource_type_id = ? AND audo_type_resource = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, userId);
//		qry.setParameter(1, resourceTypeId);
//		qry.setParameter(2, privilegeResourceType);
//		qry.addEntity("queries", Resources.class);
		
		String hqlString = "select b from Resources as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.resoId AND b.resoActive=1 "
				+ "where a.id.audoUserId = :userId and a.id.audoTypeResource = :privilegeResourceType and b.resourceTypes.rtyId = :resourceTypeId";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("userId", userId);
		qry.setParameter("resourceTypeId", resourceTypeId);
		qry.setParameter("privilegeResourceType", privilegeResourceType);
		@SuppressWarnings("unchecked")
		List<Resources> result = qry.list();

		return result;

	}
	
	
	@Override
	public List<Resources> findResourcesByAttributesPaginated(Long userId, Long resourceTypeId, String privilegeResourceType,Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy){
		Session session = sessionFactory.getCurrentSession();

		String hqlString = "select b from Resources as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.resoId AND b.resoActive=1 "
				+ "where a.id.audoUserId = :userId and a.id.audoTypeResource = :privilegeResourceType and b.resourceTypes.rtyId = :resourceTypeId";
		
		if(!sortBy.equals("none")){
			String uniqueId = ResourceFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
			String ord = "DESC";
			if(asc){
				ord = "ASC";
			}
			hqlString = hqlString + " ORDER BY " + uniqueId + " " + ord;
		}
		
				
		Query qry = session.createQuery(hqlString);
		qry.setParameter("userId", userId);
		qry.setParameter("resourceTypeId", resourceTypeId);
		qry.setParameter("privilegeResourceType", privilegeResourceType);
		qry.setFirstResult(paginationIndex);
		qry.setMaxResults(paginationSize);
		qry.setFetchSize(paginationSize);
		@SuppressWarnings("unchecked")
		List<Resources> result = qry.list();

		return result;

	}

	@Override
	public List<Synonyms> findSynonymsByResourceElement(Long resourceElementId, String synonym) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Synonyms.class, "syn");
		c.createAlias("syn.resourceElements", "resourceElements");
		c.createAlias("resourceElements.resources", "resources");
		c.add(Restrictions.eq("resources.resoId", resourceElementId));
		// Added By Hugo
		c.add(Restrictions.eq("syn.id.synActive", true));
		c.add(Restrictions.eq("resourceElements.resActive", true));
		c.add(Restrictions.eq("syn.id.synSynonym", synonym));
//		c.add(Restrictions.sqlRestriction("syn_synonym" + " = ? collate utf8_bin", synonym, new StringType()));

		@SuppressWarnings("unchecked")
		List<Synonyms> synonims = c.list();

		return synonims;
	}
	
	@Override
	public List<Synonyms> findSynonymsByResourceElementWithLimit(Long resourceId, String synonym, int maxLimit) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Synonyms.class, "syn");
		c.createAlias("syn.resourceElements", "resourceElements");
		c.createAlias("resourceElements.resources", "resources");
		c.add(Restrictions.eq("resources.resoId", resourceId));
		// Added By Hugo
		c.add(Restrictions.eq("syn.id.synActive", true));
		c.add(Restrictions.eq("resourceElements.resActive", true));
		c.add(Restrictions.eq("syn.id.synSynonym", synonym));
//		c.add(Restrictions.sqlRestriction("syn_synonym" + " = ? collate utf8_bin", synonym, new StringType()));
		c.setMaxResults(maxLimit);
		c.setFetchSize(maxLimit);
//		c.setFirstResult(rowStart);
		
		@SuppressWarnings("unchecked")
		List<Synonyms> synonims = c.list();

		return synonims;
	}

	@Override
	public Integer findTermNumberByResource(Long resourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElements.class, "resourceElements");
		c.add(Restrictions.eq("resourceElements.resources.resoId", resourceId));
		// Added By Hugo
		c.add(Restrictions.eq("resourceElements.resActive", true));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public Integer findSynNumberByResource(Long resourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Synonyms.class, "syn");
		c.createAlias("syn.resourceElements", "resourceElements");
		c.add(Restrictions.eq("resourceElements.resources.resoId", resourceId));
		// Added By Hugo
		c.add(Restrictions.eq("resourceElements.resActive", true));
		c.add(Restrictions.eq("syn.id.synActive", true));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		return totalResult;

	}

	@Override
	public Integer findSynNumberByClass(Long classId, Long resourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Synonyms.class, "syn");
		c.createAlias("syn.resourceElements", "resourceElements");
		c.createAlias("resourceElements.classes", "classes");
		c.add(Restrictions.eq("classes.claId", classId));
		c.add(Restrictions.eq("resourceElements.resources.resoId", resourceId));
		// Added By Hugo
		c.add(Restrictions.eq("resourceElements.resActive", true));
		c.add(Restrictions.eq("syn.id.synActive", true));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public Integer findTermNumberByClass(Long classId, Long resourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElements.class, "resourceElements");
		c.createAlias("resourceElements.classes", "classes");
		c.add(Restrictions.eq("classes.claId", classId));
		c.add(Restrictions.eq("resourceElements.resources.resoId", resourceId));
		// Added By Hugo
		c.add(Restrictions.eq("resourceElements.resActive", true));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public List<Classes> findClassesGroupByResourceId(Long resourceId) {

		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM resource_elements AS a " 
//				+ "INNER JOIN classes as b ON a.res_class_id = b.cla_id "
//				+ "WHERE a.res_resource_id = ? AND res_active = ? GROUP BY b.cla_id";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, resourceId);
//		qry.setParameter(1, true);
//		qry.addEntity("classes", Classes.class);

		Query qry = session.createQuery("select b FROM ResourceElements AS a "
				+ "inner join Classes as b on a.classes.claId = b.claId  "
				+ "inner join Resources as c on a.resources.resoId = c.resoId "
				+ "where c.resoId = :resourceId and c.resoActive = :resoActive group by b.claId");
		qry.setParameter("resourceId", resourceId);
		qry.setParameter("resoActive", true);
		@SuppressWarnings("unchecked")
		List<Classes> klass = qry.list();

		return klass;
	}

	@Override
	public void updateSynonym(Long newResourceId, String newSynName, boolean newActive, Long oldResourceId, String oldSynName, boolean oldActive) {
		Session session = sessionFactory.getCurrentSession();
		
		
//		String sqlString = "UPDATE synonyms SET " 
//							+ "syn_resource_element_id = ?, " 
//							+ "syn_synonym = ?, " 
//							+ "syn_active = ? "
//							+ "WHERE syn_resource_element_id = ? AND "
//							+ "syn_synonym = ? AND " + "syn_active = ? ";
//
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, newResourceId);
//		qry.setParameter(1, newSynName);
//		qry.setParameter(2, newActive);
//		qry.setParameter(3, oldResourceId);
//		qry.setParameter(4, oldSynName);
//		qry.setParameter(5, oldActive);

		String hqlString = "update Synonyms set "
				+ "id.synResourceElementId = :newResourceId "
				+ "id.synSynonym = :newSynName "
				+ "id.synActive = :newActive "
				+ "where id.synResourceElementId = :oldResourceId and id.synSynonym = :oldSynName and id.synActive = :oldActive";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("newResourceId", newResourceId);
		qry.setParameter("newSynName", newSynName);
		qry.setParameter("newActive", newActive);
		qry.setParameter("oldResourceId", oldResourceId);
		qry.setParameter("oldSynName", oldSynName);
		qry.setParameter("oldActive", oldActive);
		qry.executeUpdate();
	}

	@Override
	public List<Resources> findResourcesByAttributes(Long id, String resourceType, String permission) {
		Session session = sessionFactory.getCurrentSession();
//		String sqlString = "SELECT b.* FROM auth_user_data_objects AS a " 
//				+ "INNER JOIN resources as b ON a.audo_uid_resource = b.reso_id "
//				+ "WHERE audo_user_id = ? AND audo_type_resource = ? AND audo_permission = ?";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, id);
//		qry.setParameter(1, resourceType);
//		qry.setParameter(2, permission);
//		qry.addEntity("resources", Resources.class);
		
		String hqlString = "select b form Resources as b "
				+ "inner join AuthUserDataObjects as a on a.id.audoUidResource = b.resoId "
				+ "where a.id.audoUserId = :id and a.id.audoTypeResource = :resourceType and a.audoPermission = :premission";
		Query qry = session.createQuery(hqlString);
		qry.setParameter("id", id);
		qry.setParameter("resourceType", resourceType);
		qry.setParameter("permission", permission);
		@SuppressWarnings("unchecked")
		List<Resources> result = qry.list();

		return result;
	}

	@Override
	public boolean existSynonymByResourceCaseSensitive(Long resourceId, String synonym) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Synonyms.class, "syn");
		c.createAlias("syn.resourceElements", "resourceElements");
		c.createAlias("resourceElements.resources", "resources");
		c.add(Restrictions.eq("resources.resoId", resourceId));
		c.add(Restrictions.eq("syn.id.synActive", true));
		c.add(Restrictions.eq("resourceElements.resActive", true));
		c.add(Restrictions.eq("syn.id.synSynonym",synonym));
//		c.add(Restrictions.sqlRestriction("syn_synonym" + " = ? collate utf8_bin", synonym, new StringType()));
		c.setProjection(Projections.rowCount());

		Object returned = c.uniqueResult();
		if ((long) returned > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean existResourceElementByResourceCaseSensitive(Long resourceId, String term) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElements.class, "resourceElement");
		c.createAlias("resourceElement.resources", "resources");
		c.add(Restrictions.eq("resources.resoId", resourceId));
		c.add(Restrictions.eq("resourceElement.resActive", true));
		c.add(Restrictions.eq("resourceElement.resElement", term));
//		c.add(Restrictions.sqlRestriction("res_element = ? collate utf8_bin", term, new StringType()));

		c.setProjection(Projections.rowCount());

		Object returned = c.uniqueResult();
		if ((long) returned > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Integer getResourceMaxPriorety(Long resourceId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElements.class, "resourceElement");
		c.createAlias("resourceElement.resources", "resources");
		c.add(Restrictions.eq("resources.resoId", resourceId));
		c.add(Restrictions.eq("resourceElement.resActive", true));
		c.setProjection(Projections.max("resourceElement.resPriorety"));

		Object returned = c.uniqueResult();
		if(returned==null)
			return 0;
		return (Integer) returned;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceElementRelations> getResourceElementsRelations(
			Long resourceID) {
		List<ResourceElementRelations> result;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElementRelations.class, "resourceElementRelations");
		c.createAlias("resourceElementRelations.resourceElementsByRelResourceElementIdA", "resourcesElementA");
		c.add(Restrictions.eq("resourcesElementA.resources.resoId", resourceID));	
		result = c.list();
		return result;
	}

	@Override
	public List<ResourceElementExtenalIds> getResourceElementExternalIdBySourceAndExternalId(Long sourceId,
			String externalId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ResourceElementExtenalIds.class, "externalID");
		c.add(Restrictions.eq("externalID.id.releExternalId", externalId));
//		c.add(Restrictions.sqlRestriction("rele_external_id" + " = ? collate utf8_bin", externalId, new StringType()));
		c.add(Restrictions.eq("externalID.id.releSourceId", sourceId));
		c.add(Restrictions.eq("externalID.releActive", true));
		@SuppressWarnings("unchecked")
		List<ResourceElementExtenalIds> externalIds = c.list();
		return externalIds;
	}

//	@Override
//	public boolean existSynonymByResourceCaseSensitiveNative(Long resourceId, String synonym) {
//
//		String sqlString = "SELECT COUNT(*) FROM synonyms as a " + "INNER JOIN resource_elements as b ON a.syn_resource_element_id = b.res_id "
//				+ "INNER JOIN resources as c ON b.res_resource_id = c.reso_id "
//				+ "WHERE a.syn_active = ? and b.res_active = ? and c.reso_id = ? and a.syn_synonym = ? collate utf8_bin;";
//		SQLQuery qry = sessionFactory.getCurrentSession().createSQLQuery(sqlString);
//		qry.setParameter(0, true);
//		qry.setParameter(1, true);
//		qry.setParameter(2, resourceId);
//		qry.setParameter(3, synonym);
//
//		Object returned = qry.uniqueResult();
//
//		if (((Number) returned).compareTo(BigInteger.ZERO) > 0)  {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public boolean existResourceElementByResourceCaseSensitiveNative(Long resourceId, String term) {
//
//		String sqlString = "SELECT COUNT(*) FROM resource_elements as b " + "INNER JOIN resources as c ON b.res_resource_id = c.reso_id "
//				+ "WHERE b.res_active = ? and c.reso_id = ? and b.res_element = ? collate utf8_bin;";
//		SQLQuery qry = sessionFactory.getCurrentSession().createSQLQuery(sqlString);
//		qry.setParameter(0, true);
//		qry.setParameter(1, resourceId);
//		qry.setParameter(2, term);
//
//		Object returned = qry.uniqueResult();
//
//		if (((Number) returned).compareTo(BigInteger.ZERO) > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public List<Synonyms> findSynonymsByResourceElementWithLimitNative(Long resourceId, String synonym, int limit) {
//		Session session = sessionFactory.getCurrentSession();
//
//		String sqlString = "SELECT a.* FROM synonyms as a " + "INNER JOIN resource_elements as b ON a.syn_resource_element_id = b.res_id "
//				+ "INNER JOIN resources as c ON b.res_resource_id = c.reso_id "
//				+ "WHERE a.syn_active = ? and b.res_active = ? and c.reso_id = ? and a.syn_synonym = ? collate utf8_bin LIMIT ?;";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, true);
//		qry.setParameter(1, true);
//		qry.setParameter(2, resourceId);
//		qry.setParameter(3, synonym);
//		qry.setParameter(4, limit);
//
//		qry.addEntity("synonyms", Synonyms.class);
//		@SuppressWarnings("unchecked")
//		List<Synonyms> result = qry.list();
//
//		return result;
//	}
//
//	@Override
//	public List<ResourceElements> findResourceElementsByResourceElementWithLimitNative(Long resourceId, String term, int limit) {
//		Session session = sessionFactory.getCurrentSession();
//
//		String sqlString = "SELECT b.* FROM resource_elements as b " + "INNER JOIN resources as c ON b.res_resource_id = c.reso_id "
//				+ "WHERE b.res_active = ? and c.reso_id = ? and b.res_element = ? collate utf8_bin LIMIT ?;";
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, true);
//		qry.setParameter(1, resourceId);
//		qry.setParameter(2, term);
//		qry.setParameter(3, limit);
//		qry.addEntity("ResourceElements", ResourceElements.class);
//
//		@SuppressWarnings("unchecked")
//		List<ResourceElements> result = qry.list();
//
//		return result;
//	}
//	
//	@Override
//	public void insertResourceElement(long resourceElementId, long resourceId, Long classId, String term, Integer priority, boolean active) {
//		Session session = sessionFactory.getCurrentSession();
//
//		String sqlString = "INSERT INTO resource_elements(res_id, res_resource_id,"
//				+ " res_class_id, res_element, res_priorety, res_active)VALUES(?, ?, ?, ?, ?, ?)";
//		
//		SQLQuery qry = session.createSQLQuery(sqlString);
//		qry.setParameter(0, resourceElementId);
//		qry.setParameter(1, resourceId);
//		qry.setParameter(2, classId);
//		qry.setParameter(3, term);
//		qry.setParameter(4, priority);
//		qry.setParameter(5, active);
//
//		qry.executeUpdate();
//	}

}
