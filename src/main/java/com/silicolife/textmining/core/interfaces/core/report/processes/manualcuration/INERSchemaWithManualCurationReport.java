package com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface INERSchemaWithManualCurationReport extends IReport{
	
	public IIEProcess getBasedNERSchema();
	public IIEProcess getNERSchemaWithManualCurationAnnotations();
	public int getChanges();
	public int getNumberOfFails();
	public void addMissingAnnotationByID(IAnnotationLog annotationLog);
	public void addMissingAnnotationByEntityNull(IAnnotationLog annotationLog);
	public void addMissingNoMatchingAnnotation(IAnnotationLog annotationLog);
	public void addNoMatchingAnnotationByClass(IAnnotationLog annotationLog);
	public void addRemoveChanged(IAnnotationLog annotationLog);
	public void addEditChanged(IAnnotationLog annotationLog);
	public void addMissingConflitAnnotation(IAnnotationLog annotationLog);
	public void addInsertChanged(IAnnotationLog annotationLog);
	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyID();
	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyEntityNull();
	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyNoMactingPOsition();
	public Map<Long,SortedSet<IAnnotationLog>> getMissingbyNoMactingByClass();
	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationRemovechanges();
	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationEditchanges();
	public Map<Long,SortedSet<IAnnotationLog>> getMissingConflitAnnotation();
	public Map<Long,SortedSet<IAnnotationLog>> getAnnnotationAddedchanges();
	public Map<AnnotationLogTypeEnum,Integer> getAnnotationLogTypeNumberOFOccurences();
	public Set<Long> getDocumentIDsWithAnnotationChanged();
	public Set<Long> getDocumentIDsWithMissingAnnotation();

}
