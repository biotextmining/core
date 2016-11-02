package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ResourcesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementWrapper;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

@Service
@Transactional(readOnly = true)
public class ResourcesElementLuceneServiceImpl implements IResourcesElementLuceneService{

	private ResourcesLuceneManagerDao resourcesLuceneManagerDao;
	private ResourcesManagerDao resourcesManagerDao;

	@Autowired
	public ResourcesElementLuceneServiceImpl(ResourcesLuceneManagerDao resourcesLuceneManagerDao, ResourcesManagerDao resourcesManagerDao){
		this.resourcesLuceneManagerDao = resourcesLuceneManagerDao;
		this.resourcesManagerDao = resourcesManagerDao;
	}

	@Override
	public IResourceElementSet<IResourceElement> getAllResourceElementsByTerm(String term) {
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findBySentenceOnField(term, "resElement");
		
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getAllResourceElementsBySynonym(String synonym) {
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findBySentenceOnField(synonym, "synonymses.id.synSynonym");
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}
		
		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByTerm(String term, Long resourceID) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put(term, "resElement");
		eqSentenceOnField.put(resourceID.toString(), "resources");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findByAttributes(eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}
		
		return elementSet;
	}
	
}
