package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.documents.PublicationFieldsEnum;;

@Repository
public class PublicationsAuxDaoImpl implements PublicationsAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByQueryId(Long queryId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.setFetchMode("pub.queryHasPublicationses", FetchMode.JOIN);
		c.createAlias("pub.queryHasPublicationses", "queriesHasPub");
		c.add(Restrictions.eq("queriesHasPub.id.qhbQueryId", queryId));

		List<Publications> publications = c.list();

		return publications;
	}
	
	@Override
	public Long countPublicationsByQueryId(Long queryId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.setFetchMode("pub.queryHasPublicationses", FetchMode.JOIN);
		c.createAlias("pub.queryHasPublicationses", "queriesHasPub");
		c.add(Restrictions.eq("queriesHasPub.id.qhbQueryId", queryId));
		
		c.setProjection(Projections.rowCount());
		Long count = (Long) c.uniqueResult();
		return count;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByQueryIdPaginated(Long queryId, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.setFetchMode("pub.queryHasPublicationses", FetchMode.JOIN);
		c.createAlias("pub.queryHasPublicationses", "queriesHasPub");
		c.add(Restrictions.eq("queriesHasPub.id.qhbQueryId", queryId));
		//Order order = new Order(sortBy,asc);
		//c.createAlias("pub."+sortBy, "sortValue");
		if(!sortBy.equals("none")){
		String uniqueId = PublicationFieldsEnum.valueOf(sortBy).getUniqueIdentifier();
		String sortAlias = "pub."+uniqueId;
		
		if(asc)
			c.addOrder(Order.asc(sortAlias));
		else
			c.addOrder(Order.desc(sortAlias));
		}
		c.setFirstResult(paginationIndex);
		c.setMaxResults(paginationSize);
		c.setFetchSize(paginationSize);
		
		List<Publications> publications = c.list();

		return publications;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByCorpusId(Long corpusId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("pub.corpusHasPublicationses", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));

		List<Publications> publications = c.list();

		return publications;
	}
	
	@Override
	public Long countPublicationsByCorpusId(Long corpusId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("pub.corpusHasPublicationses", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.setProjection(Projections.rowCount());
		Long count = (Long) c.uniqueResult();
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByCorpusIdPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("pub.corpusHasPublicationses", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.setFirstResult(paginationIndex);
		c.setMaxResults(paginationSize);
		c.setFetchSize(paginationSize);
		
		List<Publications> publications = c.list();

		return publications;
	}
	
	@Override
	public Long countPublicationsNotProcessedInProcess(Long corpusId, Long processId){
		DetachedCriteria subQuery = DetachedCriteria.forClass(CorpusHasPublicationsHasProcesses.class, "docsinprocess");
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpCorpusId", corpusId));
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpProcessesId", processId));
		subQuery.add(Restrictions.eqProperty("docsinprocess.id.chphpPublicationId", "pub.pubId"));
		subQuery.setProjection(Projections.property("docsinprocess.id.chphpPublicationId"));
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("corpusHasPub", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.add(Subqueries.notExists(subQuery));
		
		c.setProjection(Projections.rowCount());
		Long count = (Long) c.uniqueResult();
		return count;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByCorpusIdAndProcessIdNotProcessedPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize) {

		DetachedCriteria subQuery = DetachedCriteria.forClass(CorpusHasPublicationsHasProcesses.class, "docsinprocess");
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpCorpusId", corpusId));
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpProcessesId", processId));
		subQuery.add(Restrictions.eqProperty("docsinprocess.id.chphpPublicationId", "pub.pubId"));
		subQuery.setProjection(Projections.property("docsinprocess.id.chphpPublicationId"));
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("corpusHasPub", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.add(Subqueries.notExists(subQuery));
		c.setFirstResult(paginationIndex);
		c.setMaxResults(paginationSize);
		c.setFetchSize(paginationSize);

		List<Publications> publications = c.list();

		return publications;
	}
	
	@Override
	public Long countCorpusPublicationsOutdatedProcess(Long corpusId, Long processId){
		
		DetachedCriteria subQuery = DetachedCriteria.forClass(CorpusHasPublicationsHasProcesses.class, "docsinprocess");
		subQuery.createAlias("docsinprocess.processes", "process");
		subQuery.setFetchMode("process", FetchMode.JOIN);
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpCorpusId", corpusId));
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpProcessesId", processId));
		subQuery.add(Restrictions.neProperty("process.proVersion", "docsinprocess.chphpProcessesVersion"));
		subQuery.add(Restrictions.eqProperty("docsinprocess.id.chphpPublicationId", "pub.pubId"));
		subQuery.setProjection(Projections.property("docsinprocess.id.chphpPublicationId"));
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("corpusHasPub", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.add(Subqueries.exists(subQuery));
		c.setProjection(Projections.rowCount());
		Long count = (Long) c.uniqueResult();
		return count;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> getCorpusPublicationsOutdatedProcessPaginated(Long corpusId, Long processId, Integer paginationIndex, Integer paginationSize){
		
		DetachedCriteria subQuery = DetachedCriteria.forClass(CorpusHasPublicationsHasProcesses.class, "docsinprocess");
		subQuery.createAlias("docsinprocess.processes", "process");
		subQuery.setFetchMode("process", FetchMode.JOIN);
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpCorpusId", corpusId));
		subQuery.add(Restrictions.eq("docsinprocess.id.chphpProcessesId", processId));
		subQuery.add(Restrictions.neProperty("process.proVersion", "docsinprocess.chphpProcessesVersion"));
		subQuery.add(Restrictions.eqProperty("docsinprocess.id.chphpPublicationId", "pub.pubId"));
		subQuery.setProjection(Projections.property("docsinprocess.id.chphpPublicationId"));
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("corpusHasPub", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.chpCorpusId", corpusId));
		c.add(Subqueries.exists(subQuery));
		c.setFirstResult(paginationIndex);
		c.setMaxResults(paginationSize);
		c.setFetchSize(paginationSize);

		List<Publications> publications = c.list();

		return publications;
	}
	

	@Override
	public Publications getPublicationFullText(Long publicationId) {

		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT pub_id, pub_fullcontent FROM publications WHERE pub_id = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, publicationId);
		qry.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("rawtypes")
		Map data = (Map) qry.uniqueResult();
		Publications publication = null;
		if (data != null) {
			Long id = ((BigInteger) data.get("pub_id")).longValue();
			String content = (String) data.get("pub_fullcontent");
			publication = new Publications(id);
			publication.setPubFullcontent(content);
		}

		return publication;
	}
	@Override
	public List<Object[]> getPublicationBySource(Long sourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(PublicationHasSources.class, "pubHasSource");
		c.createAlias("pubHasSource.publications", "publications");
		c.setFetchMode("pubHasSource.publications", FetchMode.JOIN);
		c.add(Restrictions.eq("pubHasSource.id.phpsPublicationSourceId", sourceId));
		c.setProjection(Projections.projectionList().add(Projections.property("publications.pubId"), "pubId")
				.add(Projections.property("pubHasSource.id.phpsPublicationSourceInternalId"), "internalId"));

		@SuppressWarnings("unchecked")
		List<Object[]> response = c.list();

		return response;
	}
	
	@Override
	public List<Object[]> getPublicationIdBySourceTypeAndId(Long sourceId, String id){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(PublicationHasSources.class, "pubHasSource");
		c.createAlias("pubHasSource.publications", "publications");
		c.setFetchMode("pubHasSource.publications", FetchMode.JOIN);
		c.add(Restrictions.eq("pubHasSource.id.phpsPublicationSourceId", sourceId));
		c.add(Restrictions.eq("pubHasSource.id.phpsPublicationSourceInternalId", id));
		c.setProjection(Projections.projectionList().add(Projections.property("publications.pubId"), "pubId")
				.add(Projections.property("pubHasSource.id.phpsPublicationSourceInternalId"), "internalId"));

		@SuppressWarnings("unchecked")
		List<Object[]> response = c.list();
		
		return response;
	}
	
	@Override
	public Publications getPublicationBySourceTypeAndId(Long sourceId, String id){
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.publicationHasSourceses", "publicationHasSourceses");
		c.setFetchMode("pub.publicationHasSourceses", FetchMode.JOIN);
		c.createAlias("publicationHasSourceses.publications", "publications");
		c.setFetchMode("publicationHasSourceses.publications", FetchMode.JOIN);
		c.add(Restrictions.eq("publicationHasSourceses.id.phpsPublicationSourceId", sourceId));
		c.add(Restrictions.eq("publicationHasSourceses.id.phpsPublicationSourceInternalId", id));
		/*c.setProjection(Projections.projectionList().add(Projections.property("publications.pubId"), "pubId")
				.add(Projections.property("pubHasSource.id.phpsPublicationSourceInternalId"), "internalId"));*/

		@SuppressWarnings("unchecked")
		List<Publications> publications = c.list();
		//List<Object[]> response = c.list();
		//System.out.println(response);
		return publications.get(0);
	}
	
	
	
	@Override
	public List<Object> getQueryPublicationBySource(Long sourceId,Long queryId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Queries.class, "que");
		c.createAlias("que.queryHasPublicationses", "pubHasPubs");
		c.setFetchMode("que.queryHasPublicationses", FetchMode.JOIN);
		c.createAlias("pubHasPubs.publications", "pub");
		c.setFetchMode("pubHasPubs.publications", FetchMode.JOIN);
		c.createAlias("pub.publicationHasSourceses", "pubExternalIds");
		c.setFetchMode("pub.publicationHasSourceses", FetchMode.JOIN);
		c.add(Restrictions.eq("que.id", queryId));
		c.add(Restrictions.eq("pubExternalIds.id.phpsPublicationSourceId", sourceId));
		c.setProjection(Projections.projectionList().add(Projections.property("pubExternalIds.id.phpsPublicationSourceInternalId"), "internalId"));
		@SuppressWarnings("unchecked")
		List<Object> response = c.list();
		return response;
	}

	@Override
	public List<Object> getCorpusPublicationBySource(Long sourceId, Long corpusId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Corpus.class, "corp");
		c.createAlias("corp.corpusHasPublicationses", "corpHasPubs");
		c.setFetchMode("corp.corpusHasPublicationses", FetchMode.JOIN);
		c.createAlias("corpHasPubs.publications", "pub");
		c.setFetchMode("corpHasPubs.publications", FetchMode.JOIN);
		c.createAlias("pub.publicationHasSourceses", "pubExternalIds");
		c.setFetchMode("pub.publicationHasSourceses", FetchMode.JOIN);
		c.add(Restrictions.eq("corp.id", corpusId));
		c.add(Restrictions.eq("pubExternalIds.id.phpsPublicationSourceId", sourceId));
		c.setProjection(Projections.projectionList().add(Projections.property("pubExternalIds.id.phpsPublicationSourceInternalId"), "internalId"));
		@SuppressWarnings("unchecked")
		List<Object> response = c.list();
		return response;
	}


}
