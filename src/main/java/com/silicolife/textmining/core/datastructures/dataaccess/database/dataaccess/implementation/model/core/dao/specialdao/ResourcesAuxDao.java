package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;

public interface ResourcesAuxDao {

	public List<Resources> findResourcesByAttributes(Long userId, Long resourceTypeId, String privilegeResourceType);

	public List<Synonyms> findSynonymsByResourceElement(Long resourceId, String elementTerm);

	public Integer findTermNumberByResource(Long resourceId);

	public Integer findSynNumberByResource(Long resourceId);

	public Integer findSynNumberByClass(Long classId, Long resourceId);

	public Integer findTermNumberByClass(Long classId, Long resourceId);

	public List<Classes> findClassesGroupByResourceId(Long resourceId);

	public void updateSynonym(Long newResourceId, String newSynName, boolean newActive, Long oldResourceId, String oldSynName, boolean oldActive);

	public List<Resources> findResourcesByAttributes(Long id, String resourceType, String permission);

	public boolean existSynonymByResourceCaseSensitive(Long resourceId, String synonym);

	public boolean existResourceElementByResourceCaseSensitive(Long resourceId, String synonym);

	public List<Synonyms> findSynonymsByResourceElementWithLimit(Long resourceId, String synonym, int maxLimit);
	
	public Integer getResourceMaxPriorety(Long resourceId);

	public List<ResourceElementRelations> getResourceElementsRelations(Long resourceID);
	
 	public List<ResourceElementExtenalIds> getResourceElementExternalIdBySourceAndExternalId(Long sourceId, String externalId);

	public List<Resources> findResourcesByAttributesPaginated(Long userId, Long resourceTypeId, String privilegeResourceType,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public List<Resources> findActiveResourcesByAttributes(Long userId, Long resourceTypeId, String privilegeResourceType);

	public List<Resources> findResourcesByAttributesPaginated(Long userId, String resourceType, String permission,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public List<Resources> findActiveResourcesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy);

	public Integer countResourcesByAttributes(Long id, String resourceType, String permission);

	public Integer countActiveResources();

	public List<Resources> findResourcesByAttributesWithAnyPermission(Long id, String resourceType);
}
