package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Publications> getPublicationsByResourceElement(Long resElemId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.eq("resourceElements.resId", resElemId));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.property("annnotations.publications.pubId"), "pubId");
		c.setProjection(projections);
		c.setResultTransformer(Transformers.aliasToBean(Publications.class));
      return c.list();
	}
}
