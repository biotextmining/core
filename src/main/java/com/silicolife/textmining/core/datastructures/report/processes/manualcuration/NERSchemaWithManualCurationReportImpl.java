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
import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.INERSchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class NERSchemaWithManualCurationReportImpl extends ReportImpl implements INERSchemaWithManualCurationReport{



	private IIEProcess basedNERSchema;
	private IIEProcess nerSchemawithManualCuration;
	private int changes;
	private int fails;
	private Map<Long,SortedSet<IAnnotationLog>> missingbyID;
	private Map<Long,SortedSet<IAnnotationLog>> missingbyEntityNull;
	private Map<Long,SortedSet<IAnnotationLog>> missingbyNoMactingPOsition;
	private Map<Long,SortedSet<IAnnotationLog>> missingbyNoMactingByClass;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationRemovechanges;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationEditchanges;
	private Map<Long,SortedSet<IAnnotationLog>> missingConflitAnnotation;
	private Map<Long,SortedSet<IAnnotationLog>> annnotationAddedchanges;
	private Map<AnnotationLogTypeEnum,Integer> annotationTypeNumberOfOccurences;
	private Set<Long> documentIDsWithAnnotationChanged;
	private Set<Long> documentIDsWithMissingAnnotation;

	
	public NERSchemaWithManualCurationReportImpl(IIEProcess basedNERSchema,IIEProcess nerSchemawithManualCuration) {
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.ner.manualcuration.report.title"));
		this.basedNERSchema = basedNERSchema;
		this.nerSchemawithManualCuration = nerSchemawithManualCuration;
		this.missingbyID = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.missingbyEntityNull = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.missingbyNoMactingPOsition = new HashMap<>();
		this.missingbyNoMactingByClass = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationRemovechanges = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationEditchanges = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.missingConflitAnnotation = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annnotationAddedchanges = new HashMap<Long, SortedSet<IAnnotationLog>>();
		this.annotationTypeNumberOfOccurences = new HashMap<AnnotationLogTypeEnum, Integer>();
		this.documentIDsWithAnnotationChanged = new HashSet<Long>();
		this.documentIDsWithMissingAnnotation = new HashSet<Long>();
		this.changes = 0;
		this.fails = 0;
	}

	public IIEProcess getBasedNERSchema() {
		return basedNERSchema;
	}

	public IIEProcess getNERSchemaWithManualCurationAnnotations() {
		return nerSchemawithManualCuration;
	}

	private void addChange()
	{
		this.changes++;
	}
	
	private void addFail()
	{
		this.fails++;
	}

	public void addMissingAnnotationByID(IAnnotationLog annotationLog) {
		if(!this.missingbyID.containsKey(annotationLog.getDocumentID()))
			this.missingbyID.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.missingbyID.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	@Override
	public void addMissingAnnotationByEntityNull(IAnnotationLog annotationLog) {
		if(!this.missingbyEntityNull.containsKey(annotationLog.getDocumentID()))
			this.missingbyEntityNull.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.missingbyEntityNull.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	public int getChanges() {
		return changes;
	}

	@Override
	public void addMissingNoMatchingAnnotation(IAnnotationLog annotationLog) {
		if(!this.missingbyNoMactingPOsition.containsKey(annotationLog.getDocumentID()))
			this.missingbyNoMactingPOsition.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.missingbyNoMactingPOsition.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	@Override
	public void addNoMatchingAnnotationByClass(IAnnotationLog annotationLog) {
		if(!this.missingbyNoMactingByClass.containsKey(annotationLog.getDocumentID()))
			this.missingbyNoMactingByClass.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.missingbyNoMactingByClass.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	@Override
	public void addRemoveChanged(IAnnotationLog annotationLog) {
		if(!this.annnotationRemovechanges.containsKey(annotationLog.getDocumentID()))
			this.annnotationRemovechanges.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationRemovechanges.get(annotationLog.getDocumentID()).add(annotationLog);
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

	@Override
	public void addEditChanged(IAnnotationLog annotationLog) {
		if(!this.annnotationEditchanges.containsKey(annotationLog.getDocumentID()))
			this.annnotationEditchanges.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationEditchanges.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
		addAnnotationType(annotationLog);
		addChange();
	}

	@Override
	public void addMissingConflitAnnotation(IAnnotationLog annotationLog) {
		if(!this.missingConflitAnnotation.containsKey(annotationLog.getDocumentID()))
			this.missingConflitAnnotation.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.missingConflitAnnotation.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithMissingAnnotation.add(annotationLog.getDocumentID());
		addFail();
	}

	public Set<Long> getDocumentIDsWithAnnotationChanged() {
		return documentIDsWithAnnotationChanged;
	}

	public Set<Long> getDocumentIDsWithMissingAnnotation() {
		return documentIDsWithMissingAnnotation;
	}

	@Override
	public void addInsertChanged(IAnnotationLog annotationLog) {
		if(!this.annnotationAddedchanges.containsKey(annotationLog.getDocumentID()))
			this.annnotationAddedchanges.put(annotationLog.getDocumentID(), new TreeSet<IAnnotationLog>());
		this.annnotationAddedchanges.get(annotationLog.getDocumentID()).add(annotationLog);
		this.documentIDsWithAnnotationChanged.add(annotationLog.getDocumentID());
		addAnnotationType(annotationLog);
		addChange();
	}
	
	public IIEProcess getNerSchemawithManualCuration() {
		return nerSchemawithManualCuration;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyID() {
		return missingbyID;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyEntityNull() {
		return missingbyEntityNull;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyNoMactingPOsition() {
		return missingbyNoMactingPOsition;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyNoMactingByClass() {
		return missingbyNoMactingByClass;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationRemovechanges() {
		return annnotationRemovechanges;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationEditchanges() {
		return annnotationEditchanges;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getMissingConflitAnnotation() {
		return missingConflitAnnotation;
	}

	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationAddedchanges() {
		return annnotationAddedchanges;
	}

	public int getNumberOfFails() {
		return fails;
	}

	public Map<AnnotationLogTypeEnum, Integer> getAnnotationLogTypeNumberOFOccurences() {
		return annotationTypeNumberOfOccurences;
	}	
	
}
