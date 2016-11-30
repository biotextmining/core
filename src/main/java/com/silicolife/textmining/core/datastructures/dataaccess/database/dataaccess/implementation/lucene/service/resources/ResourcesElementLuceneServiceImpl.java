package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ResourcesLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
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
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) {

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resElement", term);
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}
	
	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(
			String partialString) {

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}
		
		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym) {

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synSynonym", synonym);
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}
	

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(
			String partialString) {
		
		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(String term, Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resElement", term);
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(String synonym,
			Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synSynonym", synonym);
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(
			String partialString, Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}
		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(
			String partialString, Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
				

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(
			String partialString, int index, int paginationSize) {
		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributesPaginated(attributeForMultipleFieldsMap, eqSentenceOnField, index, paginationSize);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}
		
		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(
			String partialString, int index, int paginationSize) {
		
		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributesPaginated(attributeForMultipleFieldsMap, eqSentenceOnField, index, paginationSize);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(
			String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributesPaginated(attributeForMultipleFieldsMap, eqSentenceOnField, index, paginationSize);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(
			String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findMultiFieldSameAttributesAndExactByAttributesPaginated(attributeForMultipleFieldsMap, eqSentenceOnField, index, paginationSize);
				

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;

	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId) {
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive",  "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByExactExternalID(String externalId,
			Long sourceId) throws ResourcesExceptions {
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive",  "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalID(String externalId,
			Long resourceId) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive",  "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByExactExternalID(String externalId,
			Long sourceId, Long resourceId) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findExactByAttributes(eqSentenceOnField);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString) {
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalID(
			String partialString, Long sourceId) throws ResourcesExceptions {
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalID(
			String partialString, Long resourceId) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalID(
			String partialString, Long sourceId, Long resourceId) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(
			String partialString, int index, int paginationSize) {

		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributesPaginated(startSentenceOnField, eqSentenceOnField, index, paginationSize);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalIDPaginated(
			String partialString, Long sourceId, int index, int paginationSize) throws ResourcesExceptions {
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributesPaginated(startSentenceOnField, eqSentenceOnField, index, paginationSize);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIDPaginated(
			String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributesPaginated(startSentenceOnField, eqSentenceOnField, index, paginationSize);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(
			String partialString, Long sourceId, Long resourceId, int index, int paginationSize)
			throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findStartingUsingWildcardAndExactByAttributesPaginated(startSentenceOnField, eqSentenceOnField, index, paginationSize);
		
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();
		int priority = 0;
		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			resourceElement_.setPriority(priority);
			elementSet.addElementResource(resourceElement_);
			priority++;
		}

		return elementSet;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		
	}

	@Override
	public Integer getCountResourceElementsByPartialTerm(String partialString) {

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsFromResourceByPartialTerm(String partialString, Long resourceId)
			throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsByPartialSynonym(String partialString) {
		
		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsFromResourceByPartialSynonym(String partialString, Long resourceId)
			throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Set<String> fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resActive", "true");

		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countMultiFieldSameAttributesAndExactByAttributes(attributeForMultipleFieldsMap, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsByPartialExternalID(String partialString) {
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsFromSourceByPartialExternalID(String partialString, Long sourceId)
			throws ResourcesExceptions {

		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsFromResourceByPartialExternalID(String partialString, Long resourceId)
			throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
	}

	@Override
	public Integer getCountResourceElementsFromResourceAndSourceByPartialExternalID(String partialString,
			Long sourceId, Long resourceId) throws ResourcesExceptions {
		
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
		if(source == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		
		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resources", resourceId.toString());
		eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
	}

}
