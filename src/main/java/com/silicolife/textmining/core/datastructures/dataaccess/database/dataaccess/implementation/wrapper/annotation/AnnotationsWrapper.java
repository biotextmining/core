package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.annotation.log.AnnotationLogImpl;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.re.EventAnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.re.EventPropertiesImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationLogsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSides;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSidesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementWrapper;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationSideEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.re.IEventProperties;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class AnnotationsWrapper {

	public static Annotations convertToDeamonStructure(IEntityAnnotation entityAnnotation, Corpus corpus, Processes processes, Publications publications) {

		Annotations annotation = new Annotations();
		long annId = entityAnnotation.getId();
		Classes classes =  ClassesWrapper.convertToDaemonStructure(entityAnnotation.getClassAnnotation());
		Long annAnnotStart = entityAnnotation.getStartOffset();
		Long annAnnotEnd = entityAnnotation.getEndOffset();
		String annElement = entityAnnotation.getAnnotationValue();
		String annNormalizationForm = entityAnnotation.getAnnotationValueNormalization();
		String annAnnotType = entityAnnotation.getAnnotationType();
		Long annStartSentenceOffset = entityAnnotation.getSentenceStartOffset();
		Long annEndSentenceOffset = entityAnnotation.getSentenceEndOffset();
		String annNotes = entityAnnotation.getNotes();
		Set<AnnotationProperties> annotationPropertieses = AnnotationPropertiesWrapper.convertToDaemonStructure(entityAnnotation.getProperties(), annotation);
		annotation.setAnnId(annId);
		annotation.setClasses(classes);
		ResourceElements resourceElements = ResourceElementWrapper.convertToDaemonStructure(entityAnnotation.getResourceElement());
		annotation.setResourceElements(resourceElements );
		annotation.setAnnAnnotStart(annAnnotStart);
		annotation.setAnnAnnotEnd(annAnnotEnd);
		annotation.setAnnElement(annElement);
		annotation.setAnnNormalizationForm(annNormalizationForm);
		annotation.setAnnAnnotType(annAnnotType);
		annotation.setAnnActive(true);
		annotation.setAnnStartSentenceOffset(annStartSentenceOffset);
		annotation.setAnnEndSentenceOffset(annEndSentenceOffset);
		annotation.setAnnNotes(annNotes);
		annotation.setAnnotationPropertieses(annotationPropertieses);
		annotation.setCorpus(corpus);
		annotation.setProcesses(processes);
		annotation.setPublications(publications);
		annotation.setAnnActive(entityAnnotation.isActive());
		return annotation;
	}
	
	public static Annotations convertToDeamonStructure(IEntityAnnotation annotation, Annotations annot) {
			Set<AnnotationProperties> annotationPropertieses = AnnotationPropertiesWrapper.convertToDaemonStructure(annotation.getProperties(), annot);
			ResourceElements resourceElements = ResourceElementWrapper.convertToDaemonStructure(annotation.getResourceElement());
			Classes classes =  ClassesWrapper.convertToDaemonStructure(annotation.getClassAnnotation());
			annot.setClasses(classes);
			annot.setResourceElements(resourceElements );
			annot.setAnnAnnotStart(annotation.getStartOffset());
			annot.setAnnAnnotEnd(annotation.getEndOffset());
			annot.setAnnElement(annotation.getAnnotationValue());
			annot.setAnnNormalizationForm(annotation.getAnnotationValueNormalization());
			annot.setAnnAnnotType(annotation.getAnnotationType());
			annot.setAnnActive(true);
			annot.setAnnStartSentenceOffset(annotation.getSentenceStartOffset());
			annot.setAnnEndSentenceOffset(annotation.getSentenceEndOffset());
			annot.setAnnNotes(annotation.getNotes());
			annot.setAnnotationPropertieses(annotationPropertieses);
			annot.setAnnActive(annotation.isActive());
			return annot;
		}

	public static IEntityAnnotation convertToANoteStructure(Annotations annot) {
		long id = annot.getAnnId();
		long start = annot.getAnnAnnotStart();
		long end = annot.getAnnAnnotEnd();
		IAnoteClass klass = null;
		if(annot.getClasses()!=null)
			klass = ClassesWrapper.convertToAnoteStructure(annot.getClasses());
		IResourceElement resourceElement = null;
		if(annot.getResourceElements()!=null)
			resourceElement = ResourceElementWrapper.convertToAnoteStructure(annot.getResourceElements());
		String value = annot.getAnnElement();
		String annotationValueNormalized = annot.getAnnNormalizationForm();
		Properties properties = AnnotationPropertiesWrapper.convertToAnoteStructure(annot.getAnnotationPropertieses());
		boolean isactive = annot.isAnnActive();
		IEntityAnnotation entityAnnotaion = new EntityAnnotationImpl(id, start, end, klass, resourceElement, value, annotationValueNormalized, properties,isactive );
		return entityAnnotaion;
	}

	public static IAnnotationLog convertToANoteStructure(AnnotationLogs log) {
		long annotationLogID = log.getId().getAloId();
		long originalAnnotationID = -1;
		if(log.getAnnotations()!=null)
			originalAnnotationID = log.getAnnotations().getAnnId();
		long corpusID = log.getCorpus().getCrpId();
		long processID = log.getProcesses().getProId();	
		long documentID = log.getPublications().getPubId();
		AnnotationLogTypeEnum type = convertToANoteStructure(log.getAloAnnotLogType());
		String oldString = log.getAloAnnotOld();;
		String newString = log.getAloAnnotNew();	
		String notes = log.getAloNotes();		
		Date date = log.getAloDate();
		String user = log.getAuthUsers().getAuUsername();
		IAnnotationLog annotationLog = new AnnotationLogImpl(annotationLogID, originalAnnotationID, corpusID, processID , documentID , type , oldString , newString , notes , date , user);
		return annotationLog;
	}

	private static AnnotationLogTypeEnum convertToANoteStructure(String aloAnnotLogType) {
		return AnnotationLogTypeEnum.convertStringToEnum(aloAnnotLogType);
	}
	
	public static AnnotationLogs convertToDeamonStructure(IAnnotationLog log, AuthUsers user, Annotations annotation, Corpus corpus, Processes processes, Publications publications) {
		AnnotationLogs annotlog = new AnnotationLogs();
		AnnotationLogsId logid = new AnnotationLogsId(log.getAnnotationLogID(), user.getAuId());
		annotlog.setId(logid);
		annotlog.setCorpus(corpus);
		annotlog.setProcesses(processes);
		annotlog.setPublications(publications);
		annotlog.setAnnotations(annotation);
		annotlog.setAloAnnotLogType(log.getType().toString());
		annotlog.setAloAnnotOld(log.getOldString());
		annotlog.setAloAnnotNew(log.getNewString());
		annotlog.setAloNotes(log.getNotes());
		annotlog.setAloDate(log.getDate());
		return annotlog;
	}
	

	public static Annotations convertToDeamonStructure(IEventAnnotation event,Corpus corpus, Processes processes, Publications publications) {
		Annotations annotation = new Annotations();
		long annId = event.getId();
		annotation.setAnnId(annId);
		Long annAnnotStart = event.getStartOffset();
		Long annAnnotEnd = event.getEndOffset();
		String annClue = event.getEventClue();
		String annAnnotType = event.getAnnotationType();
		Long annStartSentenceOffset = event.getSentenceStartOffset();
		Long annEndSentenceOffset = event.getSentenceEndOffset();
		String annNotes = event.getNotes();
		Set<AnnotationProperties> annotationPropertieses = AnnotationPropertiesWrapper.convertToDaemonStructure(event.getProperties(), annotation);
		annotation.setAnnAnnotStart(annAnnotStart);
		annotation.setAnnAnnotEnd(annAnnotEnd);
		annotation.setAnnClue(annClue);
		annotation.setAnnAnnotType(annAnnotType);
		annotation.setAnnActive(true);
		annotation.setAnnStartSentenceOffset(annStartSentenceOffset);
		annotation.setAnnEndSentenceOffset(annEndSentenceOffset);
		annotation.setAnnNotes(annNotes);
		annotation.setAnnotationPropertieses(annotationPropertieses);
		annotation.setCorpus(corpus);
		annotation.setProcesses(processes);
		annotation.setPublications(publications);
		Set<AnnotationSides> annotationSidesesForAsAnnotationId = convertToDaemonStructure(corpus,processes,publications,annotation,event.getEntitiesAtLeft(),event.getEntitiesAtRight());
		annotation.setAnnotationSidesesForAsAnnotationId(annotationSidesesForAsAnnotationId);
		return annotation;
	}

	private static Set<AnnotationSides> convertToDaemonStructure(Corpus corpus, Processes processes, Publications publications, Annotations eventAnnotation,List<IEntityAnnotation> entitiesAtLeft,List<IEntityAnnotation> entitiesAtRight) {
		Set<AnnotationSides> annoationsSide = new HashSet<>();
		getAnnotationSide(corpus, processes, publications, eventAnnotation,entitiesAtLeft, annoationsSide,AnnotationSideEnum.left.toString());
		getAnnotationSide(corpus, processes, publications, eventAnnotation,entitiesAtRight, annoationsSide,AnnotationSideEnum.right.toString());
		return annoationsSide;
	}

	private static void getAnnotationSide(Corpus corpus, Processes processes,Publications publications, Annotations eventAnnotation,
			List<IEntityAnnotation> entities,Set<AnnotationSides> annoationsSide,String asAnnotSideType) {
		for(IEntityAnnotation ent:entities)
		{
			AnnotationSidesId id = new AnnotationSidesId(eventAnnotation.getAnnId(), ent.getId());
			Annotations annotationsByAsAnnotationId = eventAnnotation;		
			Annotations annotationsByAsAnnotationSubId = convertToDeamonStructure(ent, corpus, processes, publications);
			AnnotationSides entLefet = new AnnotationSides(id, annotationsByAsAnnotationId, annotationsByAsAnnotationSubId, asAnnotSideType);
			annoationsSide.add(entLefet);
		}
	}

	public static IEventAnnotation convertToANoteStructureEvent(Annotations annot, Map<Long, IEntityAnnotation> mapEntityID) {
		long id = annot.getAnnId();
		long start = annot.getAnnAnnotStart();
		long end = annot.getAnnAnnotEnd();
		String type = annot.getAnnAnnotType();
		String clue = annot.getAnnClue();
		long ontologicalClassID = -1;
		String ontologicalClass = annot.getAnnClassificationRe();
		Properties properties = AnnotationPropertiesWrapper.convertToAnoteStructure(annot.getAnnotationPropertieses());
		IEventProperties eventProperties = new EventPropertiesImpl(properties);
		boolean active = annot.isAnnActive();
		List<IEntityAnnotation> left = new ArrayList<IEntityAnnotation>();
		List<IEntityAnnotation> right = new ArrayList<IEntityAnnotation>();
		Set<AnnotationSides> setAnnoationSides = annot.getAnnotationSidesesForAsAnnotationId();
		for(AnnotationSides annotSide:setAnnoationSides)
		{
			convertToAnoteStructure(annotSide,left,right,mapEntityID);
		}
		IEventAnnotation event = new EventAnnotationImpl(id, start, end, type , left , right , clue, ontologicalClassID, ontologicalClass , eventProperties,active );
		return event;
	}

	private static void convertToAnoteStructure(AnnotationSides annotSide,List<IEntityAnnotation> left, List<IEntityAnnotation> right,Map<Long, IEntityAnnotation> mapEntityID) {
		Annotations annotEntity = annotSide.getAnnotationsByAsAnnotationSubId();
		if(!mapEntityID.containsKey(annotEntity.getAnnId()))
		{
			IEntityAnnotation ent = convertToANoteStructure(annotEntity);
			mapEntityID.put(annotEntity.getAnnId(), ent );
		}
		if(annotSide.getAsAnnotSideType().equals(AnnotationSideEnum.left.toString()))
		{
			left.add(mapEntityID.get(annotEntity.getAnnId()));
		}
		else if(annotSide.getAsAnnotSideType().equals(AnnotationSideEnum.right.toString()))
		{
			right.add(mapEntityID.get(annotEntity.getAnnId()));

		}
	}


}
