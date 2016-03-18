package com.silicolife.textmining.core.datastructures.report.processes.manualcuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.IRESchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class RESchemaWithManualCurationReportImpl extends ReportImpl implements IRESchemaWithManualCurationReport{



	private IIEProcess basedRESchema;
	private IIEProcess reSchemawithManualCuration;
	private int changes;
	private int fails;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationMissing;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationMissingAlreadyAnnotated;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationMissingByEntitiesMissing;

	private Map<Long,SortedSet<IAnnotationLog>> annnotationChanged;
	private Map<AnnotationLogTypeEnum,Integer> annotationTypeNumberOfOccurences;
	private Set<Long> documentIDsWithAnnotationChanged;
	private Set<Long> documentIDsWithMissingAnnotation;

	
	public RESchemaWithManualCurationReportImpl(IIEProcess basedRESchema,IIEProcess reSchemawithManualCuration) {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.re.manualcuration.report.title"));
		this.basedRESchema = basedRESchema;
		this.reSchemawithManualCuration = reSchemawithManualCuration;
		this.annnotationMissing = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationMissingAlreadyAnnotated = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationMissingByEntitiesMissing = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationChanged = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annotationTypeNumberOfOccurences = new HashMap<AnnotationLogTypeEnum, Integer>();
		this.documentIDsWithAnnotationChanged = new HashSet<Long>();
		this.documentIDsWithMissingAnnotation = new HashSet<Long>();
		this.changes = 0;
		this.fails = 0;
	}

	public IIEProcess getBasedRESchema() {
		return basedRESchema;
	}

	public IIEProcess getRESchemaWithManualCurationAnnotations() {
		return reSchemawithManualCuration;
	}

	private void addChange()
	{
		this.changes++;
	}
	
	private void addFail()
	{
		this.fails++;
	}

	@Override
	public void addMissingAnnotationAlreadyAnnotated(IAnnotationLog annotationLog) {
		if(!this.annnotationMissingAlreadyAnnotated.containsKey(annotationLog.getDocumentID()))
			this.annnotationMissingAlreadyAnnotated.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationMissingAlreadyAnnotated.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	@Override
	public void addMissingAnnotation(IAnnotationLog annotationLog) {
		if(!this.annnotationMissing.containsKey(annotationLog.getDocumentID()))
			this.annnotationMissing.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationMissing.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}
	
	@Override
	public void addMissingAnnotationByMissingEntities(IAnnotationLog annotationLog) {
		if(!this.annnotationMissingByEntitiesMissing.containsKey(annotationLog.getDocumentID()))
			this.annnotationMissingByEntitiesMissing.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationMissingByEntitiesMissing.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}
	
	@Override
	public void addEvent(IAnnotationLog annotationLog) {
		if(!this.annnotationChanged.containsKey(annotationLog.getDocumentID()))
			this.annnotationChanged.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationChanged.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
		addAnnotationType(annotationLog);
		addChange();		
	}
	
	@Override
	public void addChangedEvent(IAnnotationLog annotationLog) {
		if(!this.annnotationChanged.containsKey(annotationLog.getDocumentID()))
			this.annnotationChanged.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationChanged.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
		addAnnotationType(annotationLog);
		addChange();			
	}


	public int getChanges() {
		return changes;
	}
//
//	@Override
//	public void addMissingNoMatchingAnnotation(IAnnotationLog annotationLog) {
//		if(!this.missingbyNoMactingPOsition.containsKey(annotationLog.getDocumentID()))
//			this.missingbyNoMactingPOsition.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
//		this.missingbyNoMactingPOsition.get(annotationLog.getDocumentID()).add(annotationLog);
//		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
//		addFail();
//	}
//
//	@Override
//	public void addNoMatchingAnnotationByClass(IAnnotationLog annotationLog) {
//		if(!this.missingbyNoMactingByClass.containsKey(annotationLog.getDocumentID()))
//			this.missingbyNoMactingByClass.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
//		this.missingbyNoMactingByClass.get(annotationLog.getDocumentID()).add(annotationLog);
//		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
//		addFail();
//	}

	@Override
	public void addRemoveChanged(IAnnotationLog annotationLog) {
		if(!this.annnotationChanged.containsKey(annotationLog.getDocumentID()))
			this.annnotationChanged.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationChanged.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
		addAnnotationType(annotationLog);
		addChange();
	}

	private void addAnnotationType(IAnnotationLog annotationLog) {
		if(!this.annotationTypeNumberOfOccurences.containsKey(annotationLog.getType()))
		{
			this.annotationTypeNumberOfOccurences.put(annotationLog.getType(), 0);
		}
		int number = this.annotationTypeNumberOfOccurences.get(annotationLog.getType());
		this.annotationTypeNumberOfOccurences.put(annotationLog.getType(), ++number);
	}

//	@Override
//	public void addEditChanged(IAnnotationLog annotationLog) {
//		if(!this.annnotationEditchanges.containsKey(annotationLog.getDocumentID()))
//			this.annnotationEditchanges.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
//		this.annnotationEditchanges.get(annotationLog.getDocumentID()).add(annotationLog);
//		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
//		addAnnotationType(annotationLog);
//		addChange();
//	}
//
//	@Override
//	public void addMissingConflitAnnotation(IAnnotationLog annotationLog) {
//		if(!this.missingConflitAnnotation.containsKey(annotationLog.getDocumentID()))
//			this.missingConflitAnnotation.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
//		this.missingConflitAnnotation.get(annotationLog.getDocumentID()).add(annotationLog);
//		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
//		addFail();
//	}

	public Set<Long> getDocumentIDsWithAnnotationChanged() {
		return documentIDsWithAnnotationChanged;
	}

	public Set<Long> getDocumentIDsWithMissingAnnotation() {
		return documentIDsWithMissingAnnotation;
	}

//	@Override
//	public void addInsertChanged(IAnnotationLog annotationLog) {
//		if(!this.annnotationAddedchanges.containsKey(annotationLog.getDocumentID()))
//			this.annnotationAddedchanges.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
//		this.annnotationAddedchanges.get(annotationLog.getDocumentID()).add(annotationLog);
//		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
//		addAnnotationType(annotationLog);
//		addChange();
//	}
	
	public int getNumberOfFails() {
		return fails;
	}

	public Map<AnnotationLogTypeEnum, Integer> getAnnotationLogTypeNumberOFOccurences() {
		return annotationTypeNumberOfOccurences;
	}

	@Override
	public Map<Long, SortedSet<IAnnotationLog>> getEventsMissing() {
		return annnotationMissing;
	}

	@Override
	public Map<Long, SortedSet<IAnnotationLog>> getEventsChanged() {
		return annnotationChanged;
	}

	@Override
	public Map<Long, SortedSet<IAnnotationLog>> getEventsMissingAlreadyAnnotated() {
		return annnotationMissingAlreadyAnnotated;
	}

	@Override
	public Map<Long, SortedSet<IAnnotationLog>> getEventsMissingByEntitiesMissing() {
		return annnotationMissingByEntitiesMissing;
	}



	
	
	
}
