package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;

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


}
