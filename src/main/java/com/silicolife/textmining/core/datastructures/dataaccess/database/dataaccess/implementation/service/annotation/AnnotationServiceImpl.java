package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.annotation.ManualCurationAnnotationsImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.AnnotationManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ProcessesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSides;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsHasProcessesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.annotation.AnnotationsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentStatisticsImpl;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.ProcessTypeEnum;

@Service
@Transactional(readOnly = true)
public class AnnotationServiceImpl implements IAnnotationService{
	

	
	private AnnotationManagerDao annotationManagerdao;
	private CorpusManagerDao corpusManagerDao;
	private ResourcesManagerDao resourceManagerDao;
	private UsersManagerDao usersManagerDao;
	private ProcessesManagerDao processManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public AnnotationServiceImpl(AnnotationManagerDao annotationManagerdao,ProcessesManagerDao processManagerDao,CorpusManagerDao corpusManagerDao,
			ResourcesManagerDao resourceManagerDao,UsersManagerDao usersManagerDao,UsersLogged userLogged) {
	
		this.annotationManagerdao=annotationManagerdao;
		this.corpusManagerDao=corpusManagerDao;
		this.resourceManagerDao=resourceManagerDao;
		this.usersManagerDao=usersManagerDao;
		this.processManagerDao = processManagerDao;
		this.userLogged=userLogged;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addCorpusProcessDocumentEntityAnootations(Long corpusId,Long processId, Long documentID,List<IEntityAnnotation> entityAnnotations) throws AnnotationException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(documentID);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		for(IEntityAnnotation entityAnnotation: entityAnnotations)
		{
			if(entityAnnotation.getClassAnnotation()==null)
			{
				throw new AnnotationException(ExceptionsCodes.codeNoNullClass, ExceptionsCodes.msgNoNullClass);
			}

			Annotations annot = AnnotationsWrapper.convertToDeamonStructure(entityAnnotation,corpus,processes,publications);
			Classes klass = resourceManagerDao.getClassesDao().findUniqueByAttribute("claName", entityAnnotation.getClassAnnotation().getName());
			if(klass==null)
			{
				resourceManagerDao.getClassesDao().save(annot.getClasses());
			}
			else
			{
				annot.setClasses(klass);
			}
			annotationManagerdao.getAnnotationsDao().save(annot);
			Set<AnnotationProperties> annotationPropertiess = annot.getAnnotationPropertieses();
			for (AnnotationProperties annotationProperty : annotationPropertiess) {
				annotationManagerdao.getAnnotationPropertiesDao().save(annotationProperty);
			}
		}
		
		/*
		 * Document in corpus is processed
		 */
		addDocumentInCorpusAsProcessed(corpusId, processId, documentID, processes);
		
		/*
		 * log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "annotations", null, "Add Annotations");
		usersManagerDao.getAuthUserLogsDao().save(log);
		
		return true;
	}

	private void addDocumentInCorpusAsProcessed(Long corpusId, Long processId, Long documentID, Processes processes)
			throws AnnotationException {
		CorpusHasPublicationsHasProcessesId corpusPublicationProcessAssociationId = new CorpusHasPublicationsHasProcessesId();
		corpusPublicationProcessAssociationId.setChphpCorpusId(corpusId);
		corpusPublicationProcessAssociationId.setChphpProcessesId(processId);
		corpusPublicationProcessAssociationId.setChphpPublicationId(documentID);
		CorpusHasPublicationsHasProcesses corpusPublicationProcessAssociation = corpusManagerDao.getCorpusHasPublicationsHasProcessesDao().findById(corpusPublicationProcessAssociationId);
		if(corpusPublicationProcessAssociation == null){
			corpusPublicationProcessAssociation = new CorpusHasPublicationsHasProcesses();
			corpusPublicationProcessAssociation.setId(corpusPublicationProcessAssociationId);
			corpusPublicationProcessAssociation.setProcesses(processes);
			
			CorpusHasPublicationsId id = new CorpusHasPublicationsId(corpusId, documentID);
			CorpusHasPublications corpusHasPublications = corpusManagerDao.getCorpusHasPublicationsDao().findById(id);
			if(corpusHasPublications==null){
				throw new AnnotationException(ExceptionsCodes.codeCorpusPublicationNotExists, ExceptionsCodes.msgCorpusPublicationNotExists);	
			}
			corpusPublicationProcessAssociation.setCorpusHasPublications(corpusHasPublications);
			corpusPublicationProcessAssociation.setChphpCreateDate(new Date());
			corpusPublicationProcessAssociation.setChphpUpdateDate(new Date());
			corpusManagerDao.getCorpusHasPublicationsHasProcessesDao().save(corpusPublicationProcessAssociation);
		}else{
			corpusPublicationProcessAssociation.setChphpUpdateDate(new Date());
			corpusManagerDao.getCorpusHasPublicationsHasProcessesDao().update(corpusPublicationProcessAssociation);
		}
	}

	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(Long documentID, Long processID) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(documentID);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		IAnnotatedDocumentStatistics statistics = new AnnotatedDocumentStatisticsImpl();
		Map<Classes, Integer> classStatistics = annotationManagerdao.getAnnotationAuxDao().getProcessDocumentClassStatistics(processes.getProId(),publications.getPubId());
		for(Classes klass:classStatistics.keySet())
		{
			int numberOFClassEntities = classStatistics.get(klass);
			IAnoteClass klassConver = ClassesWrapper.convertToAnoteStructure(klass);
			statistics.addClassStatistics(klassConver, numberOFClassEntities );
		}
		if(processes.getProcessTypes().getPtProcessType().equals(ProcessTypeEnum.RE.toString()))
		{
			int numberOfevents = annotationManagerdao.getAnnotationAuxDao().getRelationSize(processID, documentID);
			statistics.addEventNumber(numberOfevents );
		}
		return statistics;
	}

	@Override
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntities(Long publicationId, Long processID) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("publications", publications);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", AnnotationType.ner.name());
		List<Annotations> list = annotationManagerdao.getAnnotationsDao().findByAttributes(eqRestrictions);
		List<IEntityAnnotation> entitiesAnnotations = new ArrayList<IEntityAnnotation>();
		for(Annotations annot:list)
		{
			IEntityAnnotation entityAnnotation = AnnotationsWrapper.convertToANoteStructure(annot);
			entitiesAnnotations.add(entityAnnotation);
		}
		return entitiesAnnotations;
	}

	@Override
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(Long processID,Long publicationId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("publications", publications);
		List<AnnotationLogs> list = annotationManagerdao.getAnnotationLogsDao().findByAttributes(eqRestrictions);
		SortedSet<IAnnotationLog> result = new TreeSet<>();
		for(AnnotationLogs log:list)
		{
			IAnnotationLog logAnote = AnnotationsWrapper.convertToANoteStructure(log);
			result.add(logAnote);
		}
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addCorpusProcessDocumentEventsAnootations(Long corpusId,Long processId, Long documentID, List<IEventAnnotation> events) throws AnnotationException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(documentID);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		
		for(IEventAnnotation event:events)
		{
			Annotations annot = AnnotationsWrapper.convertToDeamonStructure(event,corpus,processes,publications);
			annotationManagerdao.getAnnotationsDao().save(annot);
			
			Set<AnnotationSides> annotSides = annot.getAnnotationSidesesForAsAnnotationId();
			for(AnnotationSides annotSide:annotSides)
			{
				Annotations ent = annotSide.getAnnotationsByAsAnnotationSubId();
				Annotations entExist = annotationManagerdao.getAnnotationsDao().findById(ent.getAnnId());
				if(entExist!=null)
				{
					annotSide.setAnnotationsByAsAnnotationSubId(entExist);
				}
				else
				{
					annotationManagerdao.getAnnotationsDao().save(ent);
				}
				annotationManagerdao.getAnnotationSidesDao().save(annotSide);

			}
	
			Set<AnnotationProperties> annotationPropertiess = annot.getAnnotationPropertieses();
			for (AnnotationProperties annotationProperty : annotationPropertiess) {
				annotationManagerdao.getAnnotationPropertiesDao().save(annotationProperty);
			}	
		}
		
		/*
		 * Document in corpus is processed
		 */
		addDocumentInCorpusAsProcessed(corpusId, processId, documentID, processes);
		
		/*
		 * log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "annotations", null, "Add Annotations Events");
		usersManagerDao.getAuthUserLogsDao().save(log);
		
		return true;
	}

	@Override
	public SortedSet<IAnnotationLog> getProcessLogs(Long processId)throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		List<AnnotationLogs> list = annotationManagerdao.getAnnotationLogsDao().findByAttributes(eqRestrictions);
		SortedSet<IAnnotationLog> result = new TreeSet<>();
		for(AnnotationLogs log:list)
		{
			IAnnotationLog logAnote = AnnotationsWrapper.convertToANoteStructure(log);
			result.add(logAnote);
		}
		return result;
	}

	@Override
	public List<IEventAnnotation> getProcessDoumentAnnotationEvents(Long processID, Long publicationId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("publications", publications);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", AnnotationType.re.name());
		List<Annotations> list = annotationManagerdao.getAnnotationsDao().findByAttributes(eqRestrictions);
		List<IEventAnnotation> eventAnnoationAnnotations = new ArrayList<IEventAnnotation>();
		Map<Long, IEntityAnnotation> mapEntityID = new HashMap<>();
		for(Annotations annot:list)
		{
			IEventAnnotation eventAnnotation = AnnotationsWrapper.convertToANoteStructureEvent(annot,mapEntityID);
			eventAnnoationAnnotations.add(eventAnnotation);
		}
		return eventAnnoationAnnotations;
	}

	@Override
	public IManualCurationAnnotations getProcessDocumentAnnotationsAssociatedToLogs(Long processID) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
	
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		List<AnnotationLogs> list = annotationManagerdao.getAnnotationLogsDao().findByAttributes(eqRestrictions);
	
		List<IEntityAnnotation> entities = new ArrayList<>();
		List<IEventAnnotation> events = new ArrayList<>();
		Map<Long, IEntityAnnotation> mapEntityID = new HashMap<>();
		IManualCurationAnnotations manualCurationAnnotations = new ManualCurationAnnotationsImpl();
		for(AnnotationLogs annotLog:list)
		{
			Hibernate.initialize(annotLog.getAnnotations());
			if(annotLog.getAnnotations()!=null)
			{
				Annotations annot = annotLog.getAnnotations();
				if(annot.getAnnAnnotType().equals(AnnotationType.re.name()))
				{
					IEventAnnotation eventAnnotation = AnnotationsWrapper.convertToANoteStructureEvent(annot,mapEntityID);
					events.add(eventAnnotation);
				}
				else if(annot.getAnnAnnotType().equals(AnnotationType.ner.name()))
				{
					if(!mapEntityID.containsKey(annot.getAnnId()))
					{
						IEntityAnnotation entityAnnot = AnnotationsWrapper.convertToANoteStructure(annot);
						mapEntityID.put(annot.getAnnId(), entityAnnot);
						entities.add(entityAnnot);
					}
				}
			}
		}
		manualCurationAnnotations.setEntityAnnotations(entities);
		manualCurationAnnotations.setEventAnootations(events);
		return manualCurationAnnotations;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Boolean addAnnotationLogs(List<IAnnotationLog> annotationLogs)
			throws AnnotationException {
		AuthUsers user = userLogged.getCurrentUserLogged();
		for(IAnnotationLog annotlog : annotationLogs){
			Corpus corpus = corpusManagerDao.getCorpusDao().findById(annotlog.getCorpusID());
			if (corpus == null)
				throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
			Processes processes = processManagerDao.getProcessesDao().findById(annotlog.getProcessID());
			if (processes == null)
				throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
			Publications publications = corpusManagerDao.getPublicationsDao().findById(annotlog.getDocumentID());
			if (publications == null)
				throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
			Annotations annotation = annotationManagerdao.getAnnotationsDao().findById(annotlog.getOriginalAnnotationID());
			AnnotationLogs log = AnnotationsWrapper.convertToDeamonStructure(annotlog, user, annotation, corpus, processes, publications);
			annotationManagerdao.getAnnotationLogsDao().save(log);
		}
		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean inactiveAnnotations(List<Long> annotations) throws AnnotationException {
		for(Long annotation : annotations){
			Annotations annot = annotationManagerdao.getAnnotationsDao().findById(annotation);
			if(annot == null){
				throw new AnnotationException(ExceptionsCodes.codeNoAnnotation, ExceptionsCodes.msgNoAnnotation);
			}
			annot.setAnnActive(false);
			annotationManagerdao.getAnnotationsDao().save(annot);
		}
		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateEntityAnnotations(List<IEntityAnnotation> annotations)
			throws AnnotationException {
		for(IEntityAnnotation annotation : annotations){
			Annotations annot = annotationManagerdao.getAnnotationsDao().findById(annotation.getId());
			if(annot == null){
				throw new AnnotationException(ExceptionsCodes.codeNoAnnotation, ExceptionsCodes.msgNoAnnotation);
			}
			if(annotation.getClassAnnotation()==null)
			{
				throw new AnnotationException(ExceptionsCodes.codeNoNullClass, ExceptionsCodes.msgNoNullClass);
			}
			annot = AnnotationsWrapper.convertToDeamonStructure(annotation, annot);
			annotationManagerdao.getAnnotationsDao().save(annot);
		}
		
		return true;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public List<IPublication> getPublicationByResourceElement(long resourceElementID) throws AnnotationException {
		ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementID);
		if (resourceElemen == null)
			throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);

		List<Publications> pubs = annotationManagerdao.getAnnotationAuxDao().getPublicationsByResourceElement(resourceElementID);
		
		Set<Publications> setpubs = new HashSet<>();
		setpubs.addAll(pubs);
		List<IPublication> publications = new ArrayList<>();
		for(Publications publication : setpubs)
			publications.add(PublicationsWrapper.convertToAnoteStructure(publication));

		return publications;
	}
}
