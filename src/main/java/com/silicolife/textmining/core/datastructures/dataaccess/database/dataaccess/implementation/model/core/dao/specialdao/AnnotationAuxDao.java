package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public Map<ResourceElements, Long> countDocumentsWithAnnotationsByResourceElementInProcess(Long processId);
	
//	public  List<Publications> getPublicationsByResourceElements(Set<Long> resElemIds);
}
