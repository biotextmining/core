package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.analysis.AnnotatedDocumentStatisticsImpl;
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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process.ProcessWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementWrapper;
import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentImpl;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.ProcessTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

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
				resourceManagerDao.getClassesDao().saveOrUpdate(annot.getClasses());
			}
			else
			{
				annot.setClasses(klass);
			}
			annotationManagerdao.getAnnotationsDao().saveOrUpdate(annot);
			Set<AnnotationProperties> annotationPropertiess = annot.getAnnotationPropertieses();
			for (AnnotationProperties annotationProperty : annotationPropertiess) {
				annotationManagerdao.getAnnotationPropertiesDao().saveOrUpdate(annotationProperty);
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
			corpusPublicationProcessAssociation.setChphpProcessesVersion(processes.getProVersion());
			corpusManagerDao.getCorpusHasPublicationsHasProcessesDao().save(corpusPublicationProcessAssociation);
		}else{
			corpusPublicationProcessAssociation.setChphpUpdateDate(new Date());
			corpusPublicationProcessAssociation.setChphpProcessesVersion(processes.getProVersion());
			corpusManagerDao.getCorpusHasPublicationsHasProcessesDao().update(corpusPublicationProcessAssociation);
		}
	}

	
	public Map<IAnoteClass, Long> countAnnotationsByClassInDocument(Long documentID, Long processID) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(documentID);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		
		Map<Classes, Long> annotationsWithClass = annotationManagerdao.getAnnotationAuxDao().getProcessDocumentClassStatistics(processes.getProId(),publications.getPubId());
		
		Map<IAnoteClass, Long> mapAnoteClassNumberOFOccurrences = new HashMap<>();
		for(Classes klass:annotationsWithClass.keySet()){
			IAnoteClass anoteKlass = ClassesWrapper.convertToAnoteStructure(klass);
			mapAnoteClassNumberOFOccurrences.put(anoteKlass, annotationsWithClass.get(klass));
		}
		return mapAnoteClassNumberOFOccurrences;
	}
	
	//TODO: remove from service the Statistics datastructures, since it does not improve speed for multiple accesses (it should be a process!)
	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(Long documentID, Long processID) throws AnnotationException {
		Map<IAnoteClass, Long> classStatistics = countAnnotationsByClassInDocument(documentID, processID);

		IAnnotatedDocumentStatistics statistics = new AnnotatedDocumentStatisticsImpl();

		for(IAnoteClass klass:classStatistics.keySet()) 
			statistics.addClassStatistics(klass,  classStatistics.get(klass));
		
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if(processes.getProcessTypes().getPtProcessType().equals(ProcessTypeEnum.RE.toString()))
		{
			Long numberOfevents = countPublicationAnnotationsByAnnotionType(processID, documentID, AnnotationType.re.name());
			statistics.setEventsCount(numberOfevents);
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
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(Long publicationId, Long processID, ISentence sentence) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		Map<String, Serializable> greater = new HashMap<String, Serializable>();
		Map<String, Serializable> less = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("publications", publications);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", AnnotationType.ner.name());
		greater.put("annAnnotStart", sentence.getStartOffset());
		less.put("annAnnotEnd", sentence.getEndOffset());
		List<Annotations> list = annotationManagerdao.getAnnotationsDao().findByAttributesBetween(eqRestrictions, greater, less);
		List<IEntityAnnotation> entitiesAnnotations = new ArrayList<IEntityAnnotation>();
		for(Annotations annot:list)
		{
			IEntityAnnotation entityAnnotation = AnnotationsWrapper.convertToANoteStructure(annot);
			entitiesAnnotations.add(entityAnnotation);
		}
		return entitiesAnnotations;
	}
	
	@Override
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesFilteredByResourceElement(Long publicationId, Long processID, Long resourceId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		ResourceElements resourceElements = resourceManagerDao.getResourcesElememtsDao().findById(resourceId);
		if(resourceElements == null)
			throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("publications", publications);
		eqRestrictions.put("resourceElements", resourceElements);
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
	
	
	/* 
	 * Returs the sentence that an annotation is in. It expects that the 
	 */
	@Override
	public ISentence getSentence(Long entityAnnotationId) throws ANoteException, IOException {
		/*Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);*/
		Annotations annotations = annotationManagerdao.getAnnotationsDao().findById(entityAnnotationId);
		if(annotations == null)
			throw new AnnotationException(ExceptionsCodes.codeNoAnnotation, ExceptionsCodes.msgNoAnnotation);
		Processes processes = annotations.getProcesses();
		Publications publications = annotations.getPublications();
		
		IEntityAnnotation entityAnnot = AnnotationsWrapper.convertToANoteStructure(annotations);
		IPublication publication = PublicationsWrapper.convertToAnoteStructure(publications);
		IIEProcess process = ProcessWrapper.convertToAnoteStructure(processes);
		ICorpus corpus = process.getCorpus();

		IAnnotatedDocument annotatedDoc = new AnnotatedDocumentImpl(publication, process, corpus);
		List<ISentence> sentences = annotatedDoc.getSentencesText();
		for(ISentence sentence : sentences) {
			if(sentence.getStartOffset() <= entityAnnot.getStartOffset() && sentence.getEndOffset() >= entityAnnot.getEndOffset()) {
				return sentence;
			}
		}
		return null;
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
	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resourceElementIds) throws AnnotationException {
		for(Long resourceElementId : resourceElementIds){
			ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
			if (resourceElemen == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}

		return annotationManagerdao.getAnnotationAuxDao().getPublicationsIdsByResourceElements(resourceElementIds);
	}
	
	@Override
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<Long> resourceElementIds, IPublicationFilter pubFilter) throws AnnotationException {
		for(Long resourceElementId : resourceElementIds){
			ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
			if (resourceElemen == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}

		return annotationManagerdao.getAnnotationAuxDao().getPublicationsIdsByResourceElementsFilteredByPublicationFilter(resourceElementIds, pubFilter);
	}
	
	public List<Long> getProcessesIdsByResourceElements(Set<Long> resourceElementIds) throws AnnotationException{
		for(Long resourceElementId : resourceElementIds){
			ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
			if (resourceElemen == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}
		return annotationManagerdao.getAnnotationAuxDao().getProcessesIdsByResourceElements(resourceElementIds);
	}
	
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws AnnotationException{
		for(Long resourceElementId : filter.getResourceElementIds()){
			ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
			if (resourceElemen == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}
		for(Long klassId : filter.getAnoteClassIds()){
			Classes klass = resourceManagerDao.getClassesDao().findById(klassId);
			if(klass == null)
				throw new AnnotationException(ExceptionsCodes.codeNoClass, ExceptionsCodes.msgNoClass);
		}
		return annotationManagerdao.getAnnotationAuxDao().getPublicationsIdsByAnnotationsFilter(filter);
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean removeAllProcessDocumentAnnotations(Long processID, Long publicationId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processID);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		Publications publications = corpusManagerDao.getPublicationsDao().findById(publicationId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		annotationManagerdao.getAnnotationAuxDao().removeAllProcessDocumentAnnotations(processes,publications);
		return true;
	}
	
	@Override
	public Long countAnnotationsByResourceElement(Long processId, Long resourceElementId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		ResourceElements resourceElements = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
		if (resourceElements == null)
			throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes", processes);
		eqRestrictions.put("resourceElements", resourceElements);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", AnnotationType.ner.name());
		return annotationManagerdao.getAnnotationsDao().countByAttributes(eqRestrictions);
	}
	
	@Override
	public Long countAnnotationsByAnnotionType(Long processId, String annotationType) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("processes.proId", processId);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", annotationType);
		return annotationManagerdao.getAnnotationsDao().countByAttributes(eqRestrictions);
	}
	
	@Override
	public Long countPublicationAnnotationsByAnnotionType(Long processId, Long publicationID, String annotationType) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("publications.pubId", publicationID);
		eqRestrictions.put("processes.proId", processId);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", annotationType);
		return annotationManagerdao.getAnnotationsDao().countByAttributes(eqRestrictions);
	}

	@Override
	public Map<IAnoteClass, Long> countEntityAnnotationsByClassInProcess(Long processId) throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<Classes, Long> annotationsWithClass = annotationManagerdao.getAnnotationAuxDao().getProcessProcessClassStatistics(processId);
		
		Map<IAnoteClass, Long> mapAnoteClassNumberOFOccurrences = new HashMap<>();
		for(Classes klass:annotationsWithClass.keySet()){
			IAnoteClass anoteKlass = ClassesWrapper.convertToAnoteStructure(klass);
			mapAnoteClassNumberOFOccurrences.put(anoteKlass, annotationsWithClass.get(klass));
		}
		return mapAnoteClassNumberOFOccurrences;
	}

	@Override
	public Map<IResourceElement, Long> countEntityAnnotationsByResourceElementInProcess(Long processId)
			throws AnnotationException {
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ResourceElements, Long> annotationsByresourceCount = annotationManagerdao.getAnnotationAuxDao().countAnnotationsByResourceElementInProcess(processId);
		
		Map<IResourceElement, Long> mapAnnotationsByresourceCount = new HashMap<>();
		for(ResourceElements resElm : annotationsByresourceCount.keySet()) {
			IResourceElement anoteResElm = ResourceElementWrapper.convertToAnoteStructure(resElm);
			mapAnnotationsByresourceCount.put(anoteResElm, annotationsByresourceCount.get(resElm));
		}
		return mapAnnotationsByresourceCount;
	}

	@Override
	public Map<IResourceElement, Long> countEntityAnnotationsByResourceElementInDocument(Long documentId, Long processId)
			throws AnnotationException {
		
		Publications publications = corpusManagerDao.getPublicationsDao().findById(documentId);
		if (publications == null)
			throw new AnnotationException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ResourceElements, Long> annotationsByresourceCount = annotationManagerdao.getAnnotationAuxDao().countAnnotationsByResourceElementInDocument(documentId,processId);
		
		Map<IResourceElement, Long> mapAnnotationsByresourceCount = new HashMap<>();
		for(ResourceElements resElm : annotationsByresourceCount.keySet()) {
			IResourceElement anoteResElm = ResourceElementWrapper.convertToAnoteStructure(resElm);
			mapAnnotationsByresourceCount.put(anoteResElm, annotationsByresourceCount.get(resElm));
		}
		return mapAnnotationsByresourceCount;
	}

	@Override
	public Long countDocumentsWithResourceElementByAnnotationTypeInProcess(Long resourceElementId, Long processId, String annotationType) throws AnnotationException {
		ResourceElements resourceElemen = resourceManagerDao.getResourcesElememtsDao().findById(resourceElementId);
		if (resourceElemen == null)
			throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		eqRestrictions.put("processes.proId", processId);
		eqRestrictions.put("resourceElements.resId", resourceElementId);
		eqRestrictions.put("annActive", true);
		eqRestrictions.put("annAnnotType", annotationType);
		List<String> distinctBy = new ArrayList<>();
		distinctBy.add("publications.pubId");
		Long count = annotationManagerdao.getAnnotationsDao().countByAttributesDistinctBy(eqRestrictions, distinctBy);
		
		return count;
	}

	@Override
	public Map<IResourceElement, Long> countDocumentsWithEntityAnnotationsByResourceElementInProcess(Long processId)
			throws AnnotationException {

		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ResourceElements, Long> annotationsByresourceCount = annotationManagerdao.getAnnotationAuxDao().countDocumentsWithEntityAnnotationsByResourceElementInProcess(processId);
		
		Map<IResourceElement, Long> mapAnnotationsByresourceCount = new HashMap<>();
		for(ResourceElements resElm : annotationsByresourceCount.keySet()) {
			IResourceElement anoteResElm = ResourceElementWrapper.convertToAnoteStructure(resElm);
			mapAnnotationsByresourceCount.put(anoteResElm, annotationsByresourceCount.get(resElm));
		}
		return mapAnnotationsByresourceCount;
	}

	@Override
	public Long countPublicationsWithEventsByResourceElements(List<Long> resourceElementIds)
			throws AnnotationException {
		
		for(Long resourceId : resourceElementIds) {
			ResourceElements resourceElements = resourceManagerDao.getResourcesElememtsDao().findById(resourceId);
			if(resourceElements == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}
		
		Map<String, String> alias = new HashMap<>();
		alias.put("annotationsByAsAnnotationSubId", "annot");
		Map<String, Serializable> eqRestrictions = new HashMap<>();
		Map<String, Serializable> inRestrictions = new HashMap<>();
		inRestrictions.put("annot.resourceElements.resId", (Serializable) resourceElementIds);
		List<String> distinctBy = new ArrayList<>();
		distinctBy.add("annot.publications.pubId");
		
		return annotationManagerdao.getAnnotationSidesDao().countByAttributesInListsWithAliasDistinctBy(alias, eqRestrictions, inRestrictions, distinctBy);

	}

	@Override
	public List<Long> getPublicationsIdsWithEventsByResourceElements(List<Long> resourceElementIds)
			throws AnnotationException {
		for(Long resourceId : resourceElementIds) {
			ResourceElements resourceElements = resourceManagerDao.getResourcesElememtsDao().findById(resourceId);
			if(resourceElements == null)
				throw new AnnotationException(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		}
		
		return annotationManagerdao.getAnnotationAuxDao().getPublicationsIdsWithEventsByResourceElements(resourceElementIds);
	}

	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countPublicationsWithEventsByIAnoteClasses(
			Long processId) throws AnnotationException {
//		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
//		if (corpus == null)
//			throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ImmutablePair<Classes, Classes> , Long> publicationsWithClass = annotationManagerdao.getAnnotationAuxDao().countPublicationsWithEventsByIAnoteClasses(processId);
		
		Map<ImmutablePair<IAnoteClass, IAnoteClass> , Long> publicationsWithAnoteClass = new HashMap<>();
		for(ImmutablePair<Classes, Classes> klass:publicationsWithClass.keySet()){
			
			IAnoteClass anoteKlassLeft = ClassesWrapper.convertToAnoteStructure(klass.getLeft());
			IAnoteClass anoteKlassRight = ClassesWrapper.convertToAnoteStructure(klass.getRight());
			ImmutablePair<IAnoteClass, IAnoteClass> klasspair = new ImmutablePair<IAnoteClass, IAnoteClass>(anoteKlassLeft, anoteKlassRight);
			publicationsWithAnoteClass.put(klasspair, publicationsWithClass.get(klass));
		}
		
		return publicationsWithAnoteClass;
	}

	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countEventAnnotationsByClassInProcess(
			Long processId) throws AnnotationException {
//		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
//		if (corpus == null)
//			throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ImmutablePair<Classes, Classes> , Long> publicationsWithClass = annotationManagerdao.getAnnotationAuxDao().countEventAnnotationsByClassInProcess(processId);
		
		Map<ImmutablePair<IAnoteClass, IAnoteClass> , Long> publicationsWithAnoteClass = new HashMap<>();
		for(ImmutablePair<Classes, Classes> klass:publicationsWithClass.keySet()){
			
			IAnoteClass anoteKlassLeft = ClassesWrapper.convertToAnoteStructure(klass.getLeft());
			IAnoteClass anoteKlassRight = ClassesWrapper.convertToAnoteStructure(klass.getRight());
			ImmutablePair<IAnoteClass, IAnoteClass> klasspair = new ImmutablePair<IAnoteClass, IAnoteClass>(anoteKlassLeft, anoteKlassRight);
			publicationsWithAnoteClass.put(klasspair, publicationsWithClass.get(klass));
		}
		
		return publicationsWithAnoteClass;
	}

	@Override
	public List<Long> getPublicationsIdsByEventResourceElements(Long processId,
			Set<String> resElemIds) throws AnnotationException {
//		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
//		if (corpus == null)
//			throw new AnnotationException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		return annotationManagerdao.getAnnotationAuxDao().getPublicationsIdsByEventResourceElements(processId, resElemIds);
	}

	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countDocumentsWithEventsByResourceElemnts(
			Long processId) throws AnnotationException {
		
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ImmutablePair<ResourceElements, ResourceElements> , Long> publicationsWithResElem = annotationManagerdao.getAnnotationAuxDao().countDocumentsWithEventsByResourceElemnts(processId);
		
		Map<ImmutablePair<IResourceElement, IResourceElement> , Long> publicationsWithEventResElem = new HashMap<>();
		for(ImmutablePair<ResourceElements, ResourceElements> resElem:publicationsWithResElem.keySet()){
			
			IResourceElement anoteResElemLeft = ResourceElementWrapper.convertToAnoteStructure(resElem.getLeft());
			IResourceElement anoteResElemRight = ResourceElementWrapper.convertToAnoteStructure(resElem.getRight());
			ImmutablePair<IResourceElement, IResourceElement> resElempair = new ImmutablePair<IResourceElement, IResourceElement>(anoteResElemLeft, anoteResElemRight);
			publicationsWithEventResElem.put(resElempair, publicationsWithResElem.get(resElem));
		}
		
		return publicationsWithEventResElem;
	}

	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countEventsByResourceElemnts(Long processId)
			throws AnnotationException {
		
		Processes processes = processManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new AnnotationException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		Map<ImmutablePair<ResourceElements, ResourceElements> , Long> annotationsWithResElem = annotationManagerdao.getAnnotationAuxDao().countEventsByResourceElemnts(processId);
		
		Map<ImmutablePair<IResourceElement, IResourceElement> , Long> eventsWithResElem = new HashMap<>();
		for(ImmutablePair<ResourceElements, ResourceElements> resElem:annotationsWithResElem.keySet()){
			
			IResourceElement anoteResElemLeft = ResourceElementWrapper.convertToAnoteStructure(resElem.getLeft());
			IResourceElement anoteResElemRight = ResourceElementWrapper.convertToAnoteStructure(resElem.getRight());
			ImmutablePair<IResourceElement, IResourceElement> resElempair = new ImmutablePair<IResourceElement, IResourceElement>(anoteResElemLeft, anoteResElemRight);
			eventsWithResElem.put(resElempair, annotationsWithResElem.get(resElem));
		}
		
		return eventsWithResElem;
	}
	
}
