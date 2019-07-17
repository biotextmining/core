package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;

public interface AnnotationAuxDao {

	//public Integer getEntitieSize(Processes processs);

	//public Integer getRelationSize(Processes processs);

	public Map<Classes, Long> getProcessDocumentClassStatistics(Long processID, Long publicationID);
	
	public Map<Classes, Long> getProcessProcessClassStatistics(Long processID);

	//public Integer getRelationSize(Long processID, Long publicationID);

	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resourceElementIds);
	
	public List<Long> getProcessesIdsByResourceElements(Set<Long> resourceElementIds);
	
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter);

	public void removeAllProcessDocumentAnnotations(Processes processes, Publications publications);

	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<Long> resElemIds, IPublicationFilter pubFilter);

	public Map<ResourceElements, Long> countAnnotationsByResourceElementInProcess(Long processID);

	public Map<ResourceElements, Long> countAnnotationsByResourceElementInDocument(Long documentId, Long processId);

	public Map<ResourceElements, Long> countDocumentsWithEntityAnnotationsByResourceElementInProcess(Long processId);

	public List<Long> getPublicationsIdsWithEventsByResourceElements(List<Long> resourceElementIds);

	public Map<ImmutablePair<Classes, Classes>, Long> countPublicationsWithEventsByIAnoteClasses(Long processId);

	public Map<ImmutablePair<Classes, Classes>, Long> countEventAnnotationsByClassInProcess(Long processId);
	
	public List<Long> getPublicationsIdsByEventResourceElements(Long processId, Set<String> resElemIds);
//	public  List<Publications> getPublicationsByResourceElements(Set<Long> resElemIds);

	public Map<ImmutablePair<ResourceElements, ResourceElements>, Long> countDocumentsWithEventsByResourceElemnts(Long processId);

	public Map<ImmutablePair<ResourceElements, ResourceElements>, Long> countEventsByResourceElemnts(Long processId);
}
