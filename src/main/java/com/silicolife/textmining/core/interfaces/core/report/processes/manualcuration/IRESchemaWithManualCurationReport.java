package com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.report.IReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IRESchemaWithManualCurationReport extends IReport{
	
	public IIEProcess getBasedRESchema();
	public IIEProcess getRESchemaWithManualCurationAnnotations();
	public int getChanges();
	public int getNumberOfFails();
	public Map<AnnotationLogTypeEnum,Integer> getAnnotationLogTypeNumberOFOccurences();
	public void addRemoveChanged(IAnnotationLog eventToRemove);
	public void addMissingAnnotation(IAnnotationLog eventToRemove);
	public Set<Long> getDocumentIDsWithAnnotationChanged();
	public Set<Long> getDocumentIDsWithMissingAnnotation();
	public Map<Long,SortedSet<IAnnotationLog>> getEventsMissing();
	public Map<Long,SortedSet<IAnnotationLog>> getEventsMissingAlreadyAnnotated();
	public Map<Long,SortedSet<IAnnotationLog>> getEventsMissingByEntitiesMissing();
	public Map<Long,SortedSet<IAnnotationLog>> getEventsChanged();
	public void addMissingAnnotationAlreadyAnnotated(IAnnotationLog annotationLog);
	public void addMissingAnnotationByMissingEntities(IAnnotationLog annotationLog);
	public void addEvent(IAnnotationLog eventToAdd);
	public void addChangedEvent(IAnnotationLog annotationLog);
}
