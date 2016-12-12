package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

public interface AnnotationAuxDao {

	public Integer getEntitieSize(Processes processs);

	public Integer getRelationSize(Processes processs);

	public Map<Classes, Integer> getProcessDocumentClassStatistics(Long processID, Long publicationID);
	
	public Map<Classes, Integer> getProcessProcessClassStatistics(Long processID);

	public Integer getRelationSize(Long processID, Long publicationID);

	public List<Long> getPublicationsIdsByResourceElements(Set<Long> resourceElementIds);
	
//	public  List<Publications> getPublicationsByResourceElements(Set<Long> resElemIds);
}
