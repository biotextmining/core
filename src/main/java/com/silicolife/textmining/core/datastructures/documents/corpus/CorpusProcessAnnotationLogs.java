package com.silicolife.textmining.core.datastructures.documents.corpus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * Class that saves all annotation log ( and annotation changes) for a Process
 * 
 * @author Hugo Costa
 *
 */
public class CorpusProcessAnnotationLogs {
	
	private IIEProcess ieProcess;
	private SortedMap<Long,IAnnotationLog> annotationLogIDAnnotationLog;
	private Map<Long,SortedSet<IAnnotationLog>> publictionIDorderAnnotationLogsForEntities;
	private Map<Long,SortedSet<IAnnotationLog>> publictionIDorderAnnotationLogsForEvents;
	private Map<Long,IEntityAnnotation> entityAnnotationIDEntityAnnotation;
	private Map<Long,IEventAnnotation> eventRelationAnnotationIDEventAnnotation;
	private Map<AnnotationLogTypeEnum, Integer> annotationTypeNumberOfOccurrences;

	public CorpusProcessAnnotationLogs(IIEProcess ieProcess,boolean loadAnnotation) throws ANoteException
	{
		this.ieProcess = ieProcess;
		this.annotationLogIDAnnotationLog = new TreeMap<Long, IAnnotationLog>();
		this.publictionIDorderAnnotationLogsForEntities = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.publictionIDorderAnnotationLogsForEvents = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.entityAnnotationIDEntityAnnotation = new HashMap<Long, IEntityAnnotation>();
		this.eventRelationAnnotationIDEventAnnotation = new HashMap<Long, IEventAnnotation>();
		this.annotationTypeNumberOfOccurrences = new HashMap<AnnotationLogTypeEnum, Integer>();
		loadAnnotationsLogs(ieProcess);	
		if(loadAnnotation)
			loadAnnotationsWithinAnnotationLogs(ieProcess);
	}
	
	public IEntityAnnotation getEntityAnnotationByID(long entityID)
	{
		return entityAnnotationIDEntityAnnotation.get(entityID);
	}
	
	public int getManualCurationChanges()
	{
		return annotationLogIDAnnotationLog.size();
	}
	
	public SortedSet<IAnnotationLog> getDocumentNERLogAnnotation(long documentID)
	{
		SortedSet<IAnnotationLog> result = new TreeSet<IAnnotationLog>();
		if(! publictionIDorderAnnotationLogsForEntities.containsKey(documentID))
		{
			return result;
		}
		else
		{
			SortedSet<IAnnotationLog> docLogs = publictionIDorderAnnotationLogsForEntities.get(documentID);
			for(IAnnotationLog log:docLogs)
			{
				if(AnnotationLogTypeEnum.isEntityLog(log.getType()))
				{
					result.add(log);
				}
			}
			return result;		
		}
	}
	
	public SortedSet<IAnnotationLog> getDocumentRELogAnnotation(long documentID)
	{
		SortedSet<IAnnotationLog> result = new TreeSet<IAnnotationLog>();
		if(! publictionIDorderAnnotationLogsForEvents.containsKey(documentID))
		{
			return result;
		}
		else
		{
			SortedSet<IAnnotationLog> docLogs = publictionIDorderAnnotationLogsForEvents.get(documentID);
			for(IAnnotationLog log:docLogs)
			{
				if(AnnotationLogTypeEnum.isRelationLog(log.getType()))
				{
					result.add(log);
				}
			}
			return result;		
		}
	}

	public IIEProcess getIeProcess() {
		return ieProcess;
	}

	public Set<Long> getDocumentWithManualCurationEntities() {
		Set<Long> ids = new HashSet<Long>();
		for(long docID:publictionIDorderAnnotationLogsForEntities.keySet())
		{
			if(getDocumentNERLogAnnotation(docID).size()>0)
			{
				ids.add(docID);
			}
		}
		return ids;
	}
	
	public Set<Long> getDocumentWithManualCurationEvents() {
		Set<Long> ids = new HashSet<Long>();
		for(long docID:publictionIDorderAnnotationLogsForEvents.keySet())
		{
			if(getDocumentRELogAnnotation(docID).size()>0)
			{
				ids.add(docID);
			}
		}
		return ids;
	}
	//TODO: PROBLEM
	private void loadAnnotationsWithinAnnotationLogs(IIEProcess ieProcess) throws ANoteException {
		List<IAnnotation> annotationList = InitConfiguration.getDataAccess().getAnnotationsRelatedToAnnotationLogs(ieProcess);
		for(IAnnotation annot : annotationList)
		{
			if(annot instanceof IEntityAnnotation)
			{
				entityAnnotationIDEntityAnnotation.put(annot.getId(), (IEntityAnnotation)annot);
			}
			else if(annot instanceof IEventAnnotation)
			{
				eventRelationAnnotationIDEventAnnotation.put(annot.getId(), (IEventAnnotation)annot);
			}
		}	
	}


	private void loadAnnotationsLogs(IIEProcess ieProcess) throws ANoteException {
		SortedSet<IAnnotationLog> logs = InitConfiguration.getDataAccess().getSchemaLogs(ieProcess);
		for(IAnnotationLog log:logs)
		{
			annotationLogIDAnnotationLog.put(log.getAnnotationLogID(), log);
			if(log.getType() == AnnotationLogTypeEnum.ENTITYADD || log.getType() == AnnotationLogTypeEnum.ENTITYUPDATE || log.getType() == AnnotationLogTypeEnum.ENTITYREMOVE)
			{
				if(!publictionIDorderAnnotationLogsForEntities.containsKey(log.getDocumentID()))
					publictionIDorderAnnotationLogsForEntities.put(log.getDocumentID(), new TreeSet<IAnnotationLog>());
				publictionIDorderAnnotationLogsForEntities.get(log.getDocumentID()).add(log);
			}
			else
			{
				if(!publictionIDorderAnnotationLogsForEvents.containsKey(log.getDocumentID()))
					publictionIDorderAnnotationLogsForEvents.put(log.getDocumentID(), new TreeSet<IAnnotationLog>());
				publictionIDorderAnnotationLogsForEvents.get(log.getDocumentID()).add(log);
			}
			if(!annotationTypeNumberOfOccurrences.containsKey(log.getType()))
				annotationTypeNumberOfOccurrences.put(log.getType(), 0);
			int number = annotationTypeNumberOfOccurrences.get(log.getType());
			annotationTypeNumberOfOccurrences.put(log.getType(), ++number);
		}
	}

	public Map<AnnotationLogTypeEnum, Integer> getAnnotationTypeNumberOfOccurrences() {
		return annotationTypeNumberOfOccurrences;
	}
	
	public Map<Long, IAnnotationLog> getAnnotationLogIDAnnotationLog() {
		return annotationLogIDAnnotationLog;
	}
	
	public Map<Long, SortedSet<IAnnotationLog>> getPublictionIDorderAnnotationLogsForEntities() {
		return publictionIDorderAnnotationLogsForEntities;
	}
	
	public Map<Long, SortedSet<IAnnotationLog>> getPublictionIDorderAnnotationLogsForEvents() {
		return publictionIDorderAnnotationLogsForEvents;
	}
	
	public Map<Long, IEventAnnotation> getEventRelationAnnotationIDEventAnnotation() {
		return eventRelationAnnotationIDEventAnnotation;
	}

}
