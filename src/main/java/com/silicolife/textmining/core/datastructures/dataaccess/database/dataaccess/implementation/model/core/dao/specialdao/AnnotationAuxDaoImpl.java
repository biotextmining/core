package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;

@Repository
public class AnnotationAuxDaoImpl implements AnnotationAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public AnnotationAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getEntitieSize(Processes processs) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.eq("annnotations.processes", processs));
		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.ner.name()));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public Integer getRelationSize(Processes processs) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.eq("annnotations.processes", processs));
		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.re.name()));

		Integer totalResult = ((Number) c.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	public Map<Classes, Integer> getProcessDocumentClassStatistics(
			Long processID, Long publicationID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Classes.class, "classes");
		c.createAlias("classes.annotationses", "anotation");
		c.add(Restrictions.eq("anotation.publications.pubId", publicationID));
		c.add(Restrictions.eq("anotation.processes.proId", processID));
		c.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("claId"), "claId")
				.add(Projections.count("claId"), "classNumber"));

		@SuppressWarnings({"rawtypes", "unchecked"})
		List<Object[]> data = (List) c.list();
		Map<Classes, Integer> map = new HashMap<Classes, Integer>();
		for (Object[] object : data) {
			Long id = (Long) object[0];
			Integer count = Integer.valueOf(object[1].toString());
			Classes class_ = (Classes) session.get(Classes.class, id);
			map.put(class_, count);
		}

		return map;	
	}

	@Override
	public Map<Classes, Integer> getProcessProcessClassStatistics(Long processID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Classes.class, "classes");
		c.createAlias("classes.annotationses", "anotation");
		c.add(Restrictions.eq("anotation.processes.proId", processID));
		c.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("claId"), "claId")
				.add(Projections.count("claId"), "classNumber"));

		@SuppressWarnings({"rawtypes", "unchecked"})
		List<Object[]> data = (List) c.list();
		Map<Classes, Integer> map = new HashMap<Classes, Integer>();
		for (Object[] object : data) {
			Long id = (Long) object[0];
			Integer count = Integer.valueOf(object[1].toString());
			Classes class_ = (Classes) session.get(Classes.class, id);
			map.put(class_, count);
		}
		return map;	
	}

	@Override
	public Integer getRelationSize(Long processID, Long publicationID) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.eq("annnotations.publications.pubId", publicationID));
		c.add(Restrictions.eq("annnotations.processes.proId", processID));
		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.re.name()));
		Integer totalResult = ((Number) c.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();

		return totalResult;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resElemIds) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.in("resourceElements.resId", resElemIds));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.distinct(Projections.property("publications.pubId")), "pubId");
		c.setProjection(projections);
      return c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getProcessesIdsByResourceElements(Set<Long> resElemIds) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.in("resourceElements.resId", resElemIds));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.distinct(Projections.property("processes.proId")), "proId");
		c.setProjection(projections);
      return c.list();
	}
	
	/**
	 * 
	 * Method not used due performance issues. However is the way to get a full object form another table.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Publications> getPublicationsByResourceElements(Set<Long> resElemIds) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.createAlias("annnotations.publications", "publications");
		c.add(Restrictions.in("resourceElements.resId", resElemIds));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.distinct(Projections.property("publications.pubId")), "pubId");
		projections.add(Projections.property("publications.pubTitle"), "pubTitle");
		projections.add(Projections.property("publications.pubCategory"), "pubCategory");
		projections.add(Projections.property("publications.pubYeardate"), "pubYeardate");
		projections.add(Projections.property("publications.pubFulldate"), "pubFulldate");
		projections.add(Projections.property("publications.pubStatus"), "pubStatus");
		projections.add(Projections.property("publications.pubJournal"), "pubJournal");
		projections.add(Projections.property("publications.pubVolume"), "pubVolume");
		projections.add(Projections.property("publications.pubIssue"), "pubIssue");
		projections.add(Projections.property("publications.pubPages"), "pubPages");
		projections.add(Projections.property("publications.pubAbstract"), "pubAbstract");
		projections.add(Projections.property("publications.pubExternalLinks"), "pubExternalLinks");
		projections.add(Projections.property("publications.pubFreeFullText"), "pubFreeFullText");
		projections.add(Projections.property("publications.pubFullcontent"), "pubFullcontent");
		projections.add(Projections.property("publications.pubNotes"), "pubNotes");
//		projections.add(Projections.property("publications.publicationFieldses"), "publicationFieldses");
//		projections.add(Projections.property("publications.publicationHasLabelses"), "publicationHasLabelses");
//		projections.add(Projections.property("publications.publicationHasSourceses"), "publicationHasSourceses");
		c.setProjection(projections);
		c.setResultTransformer(Transformers.aliasToBean(Publications.class));
      return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		if(!filter.getResourceElementIds().isEmpty())
			c.add(Restrictions.in("resourceElements.resId", filter.getResourceElementIds()));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		for( Long classId : filter.getAnoteClassIds()){
			DetachedCriteria subq = DetachedCriteria.forClass(Annotations.class, "annnotations");
			subq.add(Restrictions.eq("classes.claId", classId));
			subq.setProjection(Projections.distinct(Projections.property("publications.pubId")));
			c.add(Subqueries.propertyIn("publications.pubId", subq));
		}
		projections.add(Projections.distinct(Projections.property("publications.pubId")), "pubId");
		c.setProjection(projections);
		return c.list();
	}
}
