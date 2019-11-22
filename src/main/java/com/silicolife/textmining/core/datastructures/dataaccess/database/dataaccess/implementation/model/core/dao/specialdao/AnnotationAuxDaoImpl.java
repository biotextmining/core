package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSides;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationSideEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;

@Repository
public class AnnotationAuxDaoImpl implements AnnotationAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public AnnotationAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Override
//	public Integer getEntitieSize(Processes processs) {
//		Session session = sessionFactory.getCurrentSession();
//		Criteria c = session.createCriteria(Annotations.class, "annnotations");
//		c.add(Restrictions.eq("annnotations.processes", processs));
//		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.ner.name()));
//		c.add(Restrictions.eq("annnotations.annActive", true));
//		Number result = (Number) c.setProjection(Projections.rowCount()).uniqueResult();
//		if(result == null)
//			return 0;
//		Integer totalResult = result.intValue();
//
//		return totalResult;
//	}
//
//	@Override
//	public Integer getRelationSize(Processes processs) {
//		Session session = sessionFactory.getCurrentSession();
//		Criteria c = session.createCriteria(Annotations.class, "annnotations");
//		c.add(Restrictions.eq("annnotations.processes", processs));
//		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.re.name()));
//		c.add(Restrictions.eq("annnotations.annActive", true));
//		Number result = (Number) c.setProjection(Projections.rowCount()).uniqueResult();
//		if(result == null)
//			return 0;
//		Integer totalResult = result.intValue();
//
//		return totalResult;
//	}

	@Override
	public Map<Classes, Long> getProcessDocumentClassStatistics(Long processID, Long publicationID) {
		Session session = sessionFactory.getCurrentSession();

		GenericDao<Classes> gendao = new GenericDaoImpl<Classes>(sessionFactory, Classes.class);
		
		Map<String, String> alias = new HashMap<>();
		alias.put("annotationses", "anotation");
		
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		eqRestrictions.put("anotation.publications.pubId", publicationID);
		eqRestrictions.put("anotation.processes.proId", processID);
		eqRestrictions.put("anotation.annActive", true);
		
		List<String> groups = new ArrayList<>();
		groups.add("claId");
		
		Map<String, Long> data = gendao.countByAttributesWithAliasGroupedBy(alias, eqRestrictions, groups);
		
		Map<Classes, Long> map = new HashMap<>();
		for (String idstring : data.keySet()) {
			Classes class_ = (Classes) session.get(Classes.class, Long.valueOf(idstring));
			map.put(class_, data.get(idstring));
		}

		return map;	
	}

	@Override
	public Map<Classes, Long> getProcessProcessClassStatistics(Long processID) {
		Session session = sessionFactory.getCurrentSession();

		GenericDao<Classes> gendao = new GenericDaoImpl<Classes>(sessionFactory, Classes.class);
		
		Map<String, String> alias = new HashMap<>();
		alias.put("annotationses", "anotation");
		
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		eqRestrictions.put("anotation.processes.proId", processID);
		eqRestrictions.put("anotation.annActive", true);
		
		List<String> groups = new ArrayList<>();
		groups.add("claId");
		
		Map<String, Long> data = gendao.countByAttributesWithAliasGroupedBy(alias, eqRestrictions, groups);
		
		Map<Classes, Long> map = new HashMap<>();
		for (String idstring : data.keySet()) {
			Classes class_ = (Classes) session.get(Classes.class, Long.valueOf(idstring));
			map.put(class_, data.get(idstring));
		}
		return map;	
	}
	
	@Override
	public Map<ResourceElements, Long> countAnnotationsByResourceElementInProcess(Long processID){
		Session session = sessionFactory.getCurrentSession();
		
		GenericDao<Annotations> gendao = new GenericDaoImpl<Annotations>(sessionFactory, Annotations.class);
		
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		eqRestrictions.put("processes.proId", processID);
		eqRestrictions.put("annActive", true);
		
		List<String> groups = new ArrayList<>();
		groups.add("resourceElements.resId");
		
		Map<String, Long> data = gendao.countByAttributesGroupedBy(eqRestrictions, groups);
		
		Map<ResourceElements, Long> map = new HashMap<>();
		for (String idstring : data.keySet()) {
			if(Long.valueOf(idstring)!=null) {
				ResourceElements resourceElm = (ResourceElements) session.get(ResourceElements.class, Long.valueOf(idstring));
				map.put(resourceElm, data.get(idstring));
			}
		}
		return map;	
	}
	

	@Override
	public Map<ResourceElements, Long> countAnnotationsByResourceElementInDocument(Long documentId, Long processId) {
		Session session = sessionFactory.getCurrentSession();
		
		GenericDao<Annotations> gendao = new GenericDaoImpl<Annotations>(sessionFactory, Annotations.class);
		
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		eqRestrictions.put("publications.pubId", processId);
		eqRestrictions.put("processes.proId", processId);
		eqRestrictions.put("annActive", true);
		
		List<String> groups = new ArrayList<>();
		groups.add("resourceElements.resId");
		
		Map<String, Long> data = gendao.countByAttributesGroupedBy(eqRestrictions, groups);
		
		Map<ResourceElements, Long> map = new HashMap<>();
		for (String idstring : data.keySet()) {
			if(Long.valueOf(idstring)!=null) {
				ResourceElements resourceElm = (ResourceElements) session.get(ResourceElements.class, Long.valueOf(idstring));
				map.put(resourceElm, data.get(idstring));
			}
		}
		return map;	
	}

//	@Override
//	public Integer getRelationSize(Long processID, Long publicationID) {
//		Session session = sessionFactory.getCurrentSession();
//		Criteria c = session.createCriteria(Annotations.class, "annnotations");
//		c.add(Restrictions.eq("annnotations.publications.pubId", publicationID));
//		c.add(Restrictions.eq("annnotations.processes.proId", processID));
//		c.add(Restrictions.eq("annnotations.annAnnotType", AnnotationType.re.name()));
//		c.add(Restrictions.eq("annnotations.annActive", true));
//		Number result = (Number) c.setProjection(Projections.rowCount()).uniqueResult();
//		if(result == null)
//			return 0;
//		Integer totalResult = result.intValue();
//
//		return totalResult;
//	}
	
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
	
	//Filter only tested with types ...
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<Long> resElemIds, IPublicationFilter pubFilter) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Annotations.class, "annnotations");
		c.add(Restrictions.in("resourceElements.resId", resElemIds));
//		c.add(Restrictions.eq("annActive", true)); //slowing the query... 
		ProjectionList projections = Projections.projectionList();
		addPublicationFilterToCriteria(pubFilter, c);
		projections.add(Projections.distinct(Projections.property("publications.pubId")), "pubId");
		c.setProjection(projections);
      return c.list();
	}

	private void addPublicationFilterToCriteria(IPublicationFilter pubFilter, Criteria c) {
		if(!pubFilter.getCategories().isEmpty())
			addPubCategoryDijunctionToCriteria(pubFilter, c);
		if(!pubFilter.getTypes().isEmpty())
			addPubTypeDijunctionToCriteria(pubFilter, c);
		if(!pubFilter.getStatus().isEmpty())
			addPubStatusDijunctionToCriteria(pubFilter, c);
		if(!pubFilter.getYears().isEmpty())
			addPubYearDijunctionToCriteria(pubFilter, c);	
	}

	private void addPubTypeDijunctionToCriteria(IPublicationFilter pubFilter, Criteria c) {
		DetachedCriteria subQuery;
		subQuery = DetachedCriteria.forClass(Publications.class, "publications");
		Disjunction disjunction = Restrictions.disjunction(); 
		addToPubTypeDijunction(pubFilter, disjunction);
		subQuery.add(disjunction);
		subQuery.setProjection(Projections.distinct(Projections.property("publications.pubId")));
		c.add(Subqueries.propertyIn("publications.pubId", subQuery));
	}
	
	private void addPubCategoryDijunctionToCriteria(IPublicationFilter pubFilter, Criteria c) {
		DetachedCriteria subQuery;
		subQuery = DetachedCriteria.forClass(Publications.class, "publications");
		Disjunction disjunction = Restrictions.disjunction(); 
		addToPubCategoryDijunction(pubFilter, disjunction);
		subQuery.add(disjunction);
		subQuery.setProjection(Projections.distinct(Projections.property("publications.pubId")));
		c.add(Subqueries.propertyIn("publications.pubId", subQuery));
	}
	
	private void addPubStatusDijunctionToCriteria(IPublicationFilter pubFilter, Criteria c) {
		DetachedCriteria subQuery;
		subQuery = DetachedCriteria.forClass(Publications.class, "publications");
		Disjunction disjunction = Restrictions.disjunction(); 
		addToPubStatusDijunction(pubFilter, disjunction);
		subQuery.add(disjunction);
		subQuery.setProjection(Projections.distinct(Projections.property("publications.pubId")));
		c.add(Subqueries.propertyIn("publications.pubId", subQuery));
	}
	
	private void addPubYearDijunctionToCriteria(IPublicationFilter pubFilter, Criteria c) {
		DetachedCriteria subQuery;
		subQuery = DetachedCriteria.forClass(Publications.class, "publications");
		Disjunction disjunction = Restrictions.disjunction(); 
		addToPubYearDijunction(pubFilter, disjunction);
		subQuery.add(disjunction);
		subQuery.setProjection(Projections.distinct(Projections.property("publications.pubId")));
		c.add(Subqueries.propertyIn("publications.pubId", subQuery));
	}

	private void addToPubTypeDijunction(IPublicationFilter pubFilter, Disjunction disjunction) {
		for( String type : pubFilter.getTypes()){
			Criterion criterion =  Restrictions.eq("publications.pubType", type);
			disjunction.add(criterion);		
		}
	}
	
	private void addToPubCategoryDijunction(IPublicationFilter pubFilter, Disjunction disjunction) {
		for( String category : pubFilter.getCategories()){
			Criterion criterion =  Restrictions.eq("publications.pubCategory", category);
			disjunction.add(criterion);		
		}
	}
	
	private void addToPubStatusDijunction(IPublicationFilter pubFilter, Disjunction disjunction) {
		for( String status : pubFilter.getStatus()){
			Criterion criterion =  Restrictions.eq("publications.pubStatus", status);
			disjunction.add(criterion);		
		}
	}
	
	private void addToPubYearDijunction(IPublicationFilter pubFilter, Disjunction disjunction) {
		for( Integer year : pubFilter.getYears()){
			Criterion criterion =  Restrictions.eq("publications.pubYeardate", year);
			disjunction.add(criterion);		
		}
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

	@Override
	public void removeAllProcessDocumentAnnotations(Processes processes, Publications publications) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE Annotations WHERE processes.proId = :ann_process_id AND publications.pubId = :ann_publication_id");
		query.setParameter("ann_process_id", processes.getProId());
		query.setParameter("ann_publication_id", publications.getPubId());
		query.executeUpdate();
	}

	@Override
	public Map<ResourceElements, Long> countDocumentsWithEntityAnnotationsByResourceElementInProcess(Long processId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Annotations.class);
		
		c.add(Restrictions.eq("processes.proId", processId));
		
		c.setProjection(Projections.projectionList()
		.add(Projections.groupProperty("resourceElements.resId"))
		.add(Projections.countDistinct("publications.pubId").as("publicationsCount")));


//		c.addOrder(Order.desc("publicationsCount")); //only usable to retrieve the top
		@SuppressWarnings("unchecked")
		List<Object[]> result = c.list();
		
		Map<ResourceElements, Long> resultmap =  new HashMap<>();
		for(Object[] row :result) {
			if(Long.valueOf(row[0].toString())!=null) {
				ResourceElements resourceElm = (ResourceElements) session.get(ResourceElements.class, Long.valueOf(row[0].toString()));
				resultmap.put(resourceElm,  Long.valueOf(row[row.length-1].toString()));
			}
		}
			
		return resultmap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getPublicationsIdsWithEventsByResourceElements(List<Long> resourceElementIds) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(AnnotationSides.class);
		c.createAlias("annotationsByAsAnnotationSubId", "annot");
		c.add(Restrictions.in("annot.resourceElements.resId", resourceElementIds));
		c.setProjection(Projections.distinct(Projections.property("annot.publications.pubId")));
		return c.list();
	}

	@Override
	public Map<ImmutablePair<Classes, Classes>, Long> countPublicationsWithEventsByIAnoteClasses(Long processId) {
		
		String queryHQL = "select leftsubAnnotation.classes, rightsubAnnotation.classes, count(distinct annotation.publications.pubId) as pub FROM AnnotationSides as leftSide, AnnotationSides as rightSide"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationId as annotation"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationSubId as leftsubAnnotation"
				+ " INNER JOIN rightSide.annotationsByAsAnnotationSubId as rightsubAnnotation"
				+ " WHERE leftSide.id.asAnnotationId = rightSide.id.asAnnotationId"
//				+ " AND annotation.corpus.crpId = :corpusId"
				+ " AND annotation.processes.proId = :processId"
				+ " AND leftSide.asAnnotSideType = :leftEnum"
				+ " AND rightSide.asAnnotSideType = :rightEnum"
				+ " GROUP BY leftsubAnnotation.classes.claId, rightsubAnnotation.classes.claId ";
//				+ " ORDER BY count(distinct annotation.publications.pubId)"; //hashmap or treemap loses the order
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryHQL );
		query.setParameter("leftEnum", AnnotationSideEnum.left.toString());
		query.setParameter("rightEnum", AnnotationSideEnum.right.toString());
//		query.setParameter("corpusId", corpusId);
		query.setParameter("processId", processId);
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>)query.list();
		
		Map<ImmutablePair<Classes, Classes>, Long> resultmap = new HashMap<>();
		
		for(Object[] res : result) {
			ImmutablePair<Classes, Classes> classesPair = new ImmutablePair<>((Classes)res[0], (Classes)res[1]);
			resultmap.put(classesPair, (Long)res[2]);
		}
		
		return resultmap;
		
	}

	@Override
	public Map<ImmutablePair<Classes, Classes>, Long> countEventAnnotationsByClassInProcess(Long processId) {
		String queryHQL = "select leftsubAnnotation.classes, rightsubAnnotation.classes, count(distinct annotation.annId) as pub FROM AnnotationSides as leftSide, AnnotationSides as rightSide"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationId as annotation"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationSubId as leftsubAnnotation"
				+ " INNER JOIN rightSide.annotationsByAsAnnotationSubId as rightsubAnnotation"
				+ " WHERE leftSide.id.asAnnotationId = rightSide.id.asAnnotationId"
//				+ " AND annotation.corpus.crpId = :corpusId"
				+ " AND annotation.processes.proId = :processId"
				+ " AND leftSide.asAnnotSideType = :leftEnum"
				+ " AND rightSide.asAnnotSideType = :rightEnum"
				+ " GROUP BY leftsubAnnotation.classes.claId, rightsubAnnotation.classes.claId ";
//				+ " ORDER BY count(distinct annotation.annId) desc"; //hashmap or treemap loses the order
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryHQL );
		query.setParameter("leftEnum", AnnotationSideEnum.left.toString());
		query.setParameter("rightEnum", AnnotationSideEnum.right.toString());
//		query.setParameter("corpusId", corpusId);
		query.setParameter("processId", processId);
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>)query.list();
		
		Map<ImmutablePair<Classes, Classes>, Long> resultmap = new HashMap<>();
		
		for(Object[] res : result) {
			ImmutablePair<Classes, Classes> classesPair = new ImmutablePair<>((Classes)res[0], (Classes)res[1]);
			resultmap.put(classesPair, (Long)res[2]);
		}
		return resultmap;
	}
	
	@Override
	public List<Long> getPublicationsIdsByEventResourceElements(Long processId, Set<String> resElemIds) {
		
		String queryHQL = "select distinct annotation.publications.pubId as pub FROM AnnotationSides as leftSide, AnnotationSides as rightSide"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationId as annotation"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationSubId as leftsubAnnotation"
				+ " INNER JOIN rightSide.annotationsByAsAnnotationSubId as rightsubAnnotation"
				+ " WHERE leftSide.id.asAnnotationId = rightSide.id.asAnnotationId"
//				+ " AND annotation.corpus.crpId = :corpusId"
				+ " AND annotation.processes.proId = :processId"
				+ " AND concat(leftsubAnnotation.resourceElements.resId, '-', rightsubAnnotation.resourceElements.resId) in (:ids) ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryHQL);
		query.setParameter("ids", resElemIds);
//		query.setParameter("corpusId", corpusId);
		query.setParameter("processId", processId);
		
      return query.list();
	}

	@Override
	public Map<ImmutablePair<ResourceElements, ResourceElements>, Long> countDocumentsWithEventsByResourceElemnts(
			Long processId) {
		String queryHQL = "select leftsubAnnotation.resourceElements, rightsubAnnotation.resourceElements, count(distinct annotation.publications.pubId) as pub FROM AnnotationSides as leftSide, AnnotationSides as rightSide"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationId as annotation"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationSubId as leftsubAnnotation"
				+ " INNER JOIN rightSide.annotationsByAsAnnotationSubId as rightsubAnnotation"
				+ " WHERE leftSide.id.asAnnotationId = rightSide.id.asAnnotationId"
//				+ " AND annotation.corpus.crpId = :corpusId"
				+ " AND annotation.processes.proId = :processId"
				+ " AND leftSide.asAnnotSideType = :leftEnum"
				+ " AND rightSide.asAnnotSideType = :rightEnum"
				+ " GROUP BY leftsubAnnotation.resourceElements.resId, rightsubAnnotation.resourceElements.resId ";
//				+ " ORDER BY count(distinct annotation.publications.pubId) desc"; //hashmap or treemap loses the order
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryHQL );
		query.setParameter("leftEnum", AnnotationSideEnum.left.toString());
		query.setParameter("rightEnum", AnnotationSideEnum.right.toString());
//		query.setParameter("corpusId", corpusId);
		query.setParameter("processId", processId);
		query.setFirstResult(0); // TODO: pagination and filtering
		query.setFetchSize(200);
		query.setMaxResults(200);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>)query.list();
		
		Map<ImmutablePair<ResourceElements, ResourceElements>, Long> resultmap = new HashMap<>();
		
		for(Object[] res : result) {
			ImmutablePair<ResourceElements, ResourceElements> classesPair = new ImmutablePair<>((ResourceElements)res[0], (ResourceElements)res[1]);
			resultmap.put(classesPair, (Long)res[2]);
		}
		
		return resultmap;
	}

	@Override
	public Map<ImmutablePair<ResourceElements, ResourceElements>, Long> countEventsByResourceElemnts(Long processId) {
		String queryHQL = "select leftsubAnnotation.resourceElements, rightsubAnnotation.resourceElements, count(distinct annotation.annId) as pub FROM AnnotationSides as leftSide, AnnotationSides as rightSide"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationId as annotation"
				+ " INNER JOIN leftSide.annotationsByAsAnnotationSubId as leftsubAnnotation"
				+ " INNER JOIN rightSide.annotationsByAsAnnotationSubId as rightsubAnnotation"
				+ " WHERE leftSide.id.asAnnotationId = rightSide.id.asAnnotationId"
//				+ " AND annotation.corpus.crpId = :corpusId"
				+ " AND annotation.processes.proId = :processId"
				+ " AND leftSide.asAnnotSideType = :leftEnum"
				+ " AND rightSide.asAnnotSideType = :rightEnum"
				+ " GROUP BY leftsubAnnotation.resourceElements.resId, rightsubAnnotation.resourceElements.resId ";
//				+ " ORDER BY count(distinct annotation.annId) desc";//hashmap or treemap loses the order
		
		
		Query query = sessionFactory.getCurrentSession().createQuery(queryHQL );
		query.setParameter("leftEnum", AnnotationSideEnum.left.toString());
		query.setParameter("rightEnum", AnnotationSideEnum.right.toString());
//		query.setParameter("corpusId", corpusId);
		query.setParameter("processId", processId);
		query.setFirstResult(0); // TODO: pagination and filtering
		query.setFetchSize(200);
		query.setMaxResults(200);
		
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>)query.list();
		
		Map<ImmutablePair<ResourceElements, ResourceElements>, Long> resultmap = new HashMap<>();
		
		for(Object[] res : result) {
			ImmutablePair<ResourceElements, ResourceElements> classesPair = new ImmutablePair<>((ResourceElements)res[0], (ResourceElements)res[1]);
			resultmap.put(classesPair, (Long)res[2]);
		}
		return resultmap;
	}

	
}
