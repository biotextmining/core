package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.ResourceElementsLuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementWrapper;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementsLuceneFieldsEnum;
import com.silicolife.textmining.core.datastructures.resources.ResourcesLuceneFieldsEnum;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;

@Service
@Transactional(readOnly = true)
public class ResourcesElementLuceneServiceImpl implements IResourcesElementLuceneService{

	private ResourceElementsLuceneManagerDao resourcesLuceneManagerDao;
	private ResourcesManagerDao resourcesManagerDao;
	private UsersLogged user;

	@Autowired
	public ResourcesElementLuceneServiceImpl(ResourceElementsLuceneManagerDao resourcesLuceneManagerDao,
			ResourcesManagerDao resourcesManagerDao, UsersLogged user){
		this.resourcesLuceneManagerDao = resourcesLuceneManagerDao;
		this.resourcesManagerDao = resourcesManagerDao;
		this.user = user;
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
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTermOrExactSynonymPaginated(
			String exactString, int index, int paginationSize) {

		Set<Map<String, String>> setEqSentenceOnFields = new HashSet<>(); ;
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synSynonym", exactString);
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		setEqSentenceOnFields.add(eqSentenceOnField);
		
		Map<String, String> eqSentenceOnFieldTerm = new HashMap<>();
		eqSentenceOnFieldTerm.put("resElement", exactString);
		eqSentenceOnFieldTerm.put("resActive", "true");
		
		setEqSentenceOnFields.add(eqSentenceOnFieldTerm);
		
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfExactByAttributesPaginated(setEqSentenceOnFields, index, paginationSize);

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
	public Integer getCountResourceElementsByExactTermOrExactSynonymPaginated(
			String exactString) {

		Set<Map<String, String>> setEqSentenceOnFields = new HashSet<>(); ;
		
		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synSynonym", exactString);
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		
		setEqSentenceOnFields.add(eqSentenceOnField);
		
		Map<String, String> eqSentenceOnFieldTerm = new HashMap<>();
		eqSentenceOnFieldTerm.put("resElement", exactString);
		eqSentenceOnFieldTerm.put("resActive", "true");
		
		setEqSentenceOnFields.add(eqSentenceOnFieldTerm);
		
		
		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfExactByAttributes(setEqSentenceOnFields);

		return resourcesElements.size();
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter, String term) throws ResourcesExceptions {
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("resElement", term);
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findSetOfExactByAttributes(setOfeqSentenceOnField);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(IResourceElementsFilter filter, String synonym) throws ResourcesExceptions {
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("synonymses.id.synSynonym", synonym);
			eqSentenceOnField.put("synonymses.id.synActive", "true");
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findSetOfExactByAttributes(setOfeqSentenceOnField);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(IResourceElementsFilter filter,
			String partialString) throws ResourcesExceptions {
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("keywordEdgeNGram_res_element");
			fields.add("tokenEdgeNGram_res_element");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}

		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(IResourceElementsFilter filter,
			String partialString) throws ResourcesExceptions {
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
			fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("synonymses.id.synActive", "true");
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField);


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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(IResourceElementsFilter filter,
			String partialString, int index, int paginationSize) throws ResourcesExceptions {
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("keywordEdgeNGram_res_element");
			fields.add("tokenEdgeNGram_res_element");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}

		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			 resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfMultiFieldSameAttributesAndSetOfExactByAttributesPaginated(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField, index, paginationSize);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(IResourceElementsFilter filter,
			String partialString, int index, int paginationSize) throws ResourcesExceptions {
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
			fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("synonymses.id.synActive", "true");
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			 resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfMultiFieldSameAttributesAndSetOfExactByAttributesPaginated(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField, index, paginationSize);


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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(IResourceElementsFilter filter, String externalId) throws ResourcesExceptions {
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();

		if(!filter.getResourceIds().isEmpty() && !filter.getSourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){
				for(Long sourceId : filter.getSourceIds()){
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
					setOfeqSentenceOnField.add(eqSentenceOnField);
				}
			}
		}else if(!filter.getResourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){
				Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
				if (resource == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
				eqSentenceOnField.put("resources", resourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive",  "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}else{
			for(Long sourceId : filter.getSourceIds()){	
				Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
				if(source == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", externalId);
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive",  "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			 resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findSetOfExactByAttributes(setOfeqSentenceOnField);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(IResourceElementsFilter filter,
			String partialString) throws ResourcesExceptions {

		Set<Map<String, String>> setOfStartSentenceOnField = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		if(!filter.getResourceIds().isEmpty() && !filter.getSourceIds().isEmpty()){

			for(Long resourceId:filter.getResourceIds()){
				for(Long sourceId:filter.getSourceIds()){

					Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
					if (resource == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

					Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
					if(source == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

					Map<String, String> startSentenceOnField = new HashMap<>();
					startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
					setOfStartSentenceOnField.add(startSentenceOnField);

					Map<String, String> eqSentenceOnField = new HashMap<>();
					eqSentenceOnField.put("resources", resourceId.toString());
					eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
					eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
					eqSentenceOnField.put("resActive", "true");
					setOfeqSentenceOnField.add(eqSentenceOnField);

				}
			}
		}else if(!filter.getResourceIds().isEmpty()){
			for(Long resourceId:filter.getResourceIds()){
				Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
				if (resource == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resources", resourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}else{
			for(Long sourceId:filter.getSourceIds()){

				Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
				if(source == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfStartingUsingWildcardAndSetOfExactByAttributes(setOfStartSentenceOnField, setOfeqSentenceOnField);

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(IResourceElementsFilter filter,
			String partialString, int index, int paginationSize)
					throws ResourcesExceptions {

		Set<Map<String, String>> setOfStartSentenceOnField = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		if(!filter.getResourceIds().isEmpty() && !filter.getSourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){
				for(Long sourceId : filter.getSourceIds()){
					Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
					if (resource == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

					Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
					if(source == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

					Map<String, String> startSentenceOnField = new HashMap<>();
					startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
					setOfStartSentenceOnField.add(startSentenceOnField);

					Map<String, String> eqSentenceOnField = new HashMap<>();
					eqSentenceOnField.put("resources", resourceId.toString());
					eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
					eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
					eqSentenceOnField.put("resActive", "true");
					setOfeqSentenceOnField.add(eqSentenceOnField);
				}
			}
		}else if(!filter.getResourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){

				Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
				if (resource == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resources", resourceId.toString());	
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}else{
			for(Long sourceId : filter.getSourceIds()){

				Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
				if(source == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}
		
		List<ResourceElements> resourcesElements = new ArrayList<>();
		if(!setOfeqSentenceOnField.isEmpty())
			resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfStartingUsingWildcardAndSetOfExactByAttributesPaginated(setOfStartSentenceOnField, setOfeqSentenceOnField, index, paginationSize);

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
	public Integer getResourceElementsCountByPartialTerm(String partialString) {

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
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialString)
			throws ResourcesExceptions {

		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("keywordEdgeNGram_res_element");
			fields.add("tokenEdgeNGram_res_element");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}
		
		if(setOfeqSentenceOnField.isEmpty())
			return 0;
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField);
	}

	@Override
	public Integer getResourceElementsCountByPartialSynonym(String partialString) {

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
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter, String partialString)
			throws ResourcesExceptions {

		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMaps = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>();
		for(Long resourceId : filter.getResourceIds()){
			Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
			if (resource == null)
				throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

			Set<String> fields = new HashSet<>();
			fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
			fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
			Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
			attributeForMultipleFieldsMap.put(partialString, fields);
			setOfAttributeForMultipleFieldsMaps.add(attributeForMultipleFieldsMap);

			Map<String, String> eqSentenceOnField = new HashMap<>();
			eqSentenceOnField.put("synonymses.id.synActive", "true");
			eqSentenceOnField.put("resources", resourceId.toString());
			eqSentenceOnField.put("resActive", "true");
			setOfeqSentenceOnField.add(eqSentenceOnField);
		}

		if(setOfeqSentenceOnField.isEmpty())
			return 0;
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMaps, setOfeqSentenceOnField);
	}

	@Override
	public Integer getResourceElementsCountByPartialExternalID(String partialString) {

		Map<String, String> startSentenceOnField = new HashMap<>();
		startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
		eqSentenceOnField.put("resActive", "true");

		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countStartingUsingWildcardAndExactByAttributes(startSentenceOnField, eqSentenceOnField);
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions {

		Set<Map<String, String>> setOfStartSentenceOnField = new HashSet<>();
		Set<Map<String, String>> setOfeqSentenceOnField = new HashSet<>(); 
		
		if(!filter.getResourceIds().isEmpty() && !filter.getSourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){
				for(Long sourceId: filter.getSourceIds()){
					Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
					if (resource == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

					Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
					if(source == null)
						throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

					Map<String, String> startSentenceOnField = new HashMap<>();
					startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
					setOfStartSentenceOnField.add(startSentenceOnField);

					Map<String, String> eqSentenceOnField = new HashMap<>();
					eqSentenceOnField.put("resources", resourceId.toString());
					eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());		
					eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
					eqSentenceOnField.put("resActive", "true");
					setOfeqSentenceOnField.add(eqSentenceOnField);
				}
			}
		} else if(!filter.getResourceIds().isEmpty()){
			for(Long resourceId : filter.getResourceIds()){
				Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
				if (resource == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resources", resourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}else{
			for(Long sourceId : filter.getSourceIds()){
				Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findById(sourceId);
				if(source == null)
					throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);

				Map<String, String> startSentenceOnField = new HashMap<>();
				startSentenceOnField.put("resourceElementExtenalIdses.id.releExternalId", partialString);
				setOfStartSentenceOnField.add(startSentenceOnField);

				Map<String, String> eqSentenceOnField = new HashMap<>();
				eqSentenceOnField.put("resourceElementExtenalIdses.id.releSourceId", sourceId.toString());
				eqSentenceOnField.put("resourceElementExtenalIdses.releActive", "true");
				eqSentenceOnField.put("resActive", "true");
				setOfeqSentenceOnField.add(eqSentenceOnField);
			}
		}
		
		if(setOfeqSentenceOnField.isEmpty())
			return 0;
		
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countSetOfStartingUsingWildcardAndSetOfExactByAttributes(setOfStartSentenceOnField, setOfeqSentenceOnField);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(
			String partialString, int index, int paginationSize) {

		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap = new HashSet<>();
		Set<Map<String, String>> setOfEqSentenceOnField = new HashSet<>();

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		setOfAttributeForMultipleFieldsMap.add(attributeForMultipleFieldsMap);

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resActive", "true");
		setOfEqSentenceOnField.add(eqSentenceOnField);

		fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		setOfAttributeForMultipleFieldsMap.add(attributeForMultipleFieldsMap);
		
		eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		setOfEqSentenceOnField.add(eqSentenceOnField);

		List<ResourceElements> resourcesElements = resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.findSetOfMultiFieldSameAttributesAndSetOfExactByAttributesPaginated(setOfAttributeForMultipleFieldsMap, setOfEqSentenceOnField, index, paginationSize);

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
	
	private Map<String,Map<String, String>> addToMap(Map<String,Map<String, String>> map, String active
			, String field, String value){
		if(map.containsKey(active)) {
			map.get(active).put(field, value);
		}else {
			Map<String, String> fields = new HashMap<>();
			fields.put(field, value);
			map.put(active, fields);
		}
		return map;
	}
	
	private Map<String,Set<Map<String, Set<String>>>> addToMap(Map<String,Set<Map<String, Set<String>>>> map
			,String active, Map<String, Set<String>> fields) {
		if(map.containsKey(active)) {
			map.get(active).add(fields);
		}else {
			Set<Map<String, Set<String>>> set = new HashSet<>();
			set.add(fields);
			map.put(active, set);
		}
		return map;
	}
	
	private Map<Map<String, String>, Set<Map<String, Set<String>>>> activesToMapSearch2(Map<String,Set<Map<String, Set<String>>>> uniqueActives){
		Map<Map<String, String>, Set<Map<String, Set<String>>>>uniques = new HashMap<>();
		for(String key : uniqueActives.keySet()) {
			Map<String, String> eqActive = new HashMap<>();
			eqActive.put(key, "true");
			uniques.put(eqActive, uniqueActives.get(key));
		}
		return uniques;
	}
	
	private Map<Map<String, String>, Map<String, String>> activesToMap2(Map<String,Map<String, String>> uniqueActives){
		Map<Map<String, String>, Map<String, String>> uniques = new HashMap<>();
		for(String key : uniqueActives.keySet()) {
			Map<String, String> eqActive = new HashMap<>();
			eqActive.put(key, "true");
			uniques.put(eqActive, uniqueActives.get(key));
		}
		return uniques;
	}
	
	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsPaginated(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy) {
		
		String partialString = searchProperties.getValue();
		
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap = new HashSet<>();
		Set<Map<String, String>> setOfEqSentenceOnField = new HashSet<>();
		Map<String,Set<Map<String, Set<String>>>> activesSearch = new HashMap<>();
		Map<Map<String, String>, Set<Map<String, Set<String>>>> uniquesSearch = new HashMap<>();
		Map<Map<String, String>, Map<String, String>> uniques = new HashMap<>();
		
		Map<String,Map<String, String>> uniqueActives = new HashMap<>();
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resources";
		eqMustSentenceOnField.put("resActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");
		
		String sortField = ResourceElementsLuceneFieldsEnum.getSortField(sortBy);
		
		SortField.Type sortType = ResourceElementsLuceneFieldsEnum.getSortType(sortBy);
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
		
		List<ResourceElements> resourcesElements =null;
		
		if(!searchProperties.isSugestions()) {
		if(searchProperties.getValue()!=null)
			for(String field : fields){
				if(ResourceElementsLuceneFieldsEnum.getActive(field)!=null) {
					uniqueActives = this.addToMap(uniqueActives, ResourceElementsLuceneFieldsEnum.getActive(field),
							ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, field), searchProperties.getValue());
				//eqMustSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getActive(field), "true");
				}
			else	
			eqSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
			}
		uniques = this.activesToMap2(uniqueActives);
		resourcesElements =  resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findForWebTableWPermissionsPaginated(
		eqSentenceOnField,uniques, eqMustSentenceOnField, permissionFields, idField, 
		eqFilters,searchProperties.isWholeWords(), index, paginationSize,
		sortField, sortType, asc);
		}
		else {
			if(searchProperties.getValue()!=null)
			for(String field : fields){
				Set<String> edgeFields = new HashSet<>();
				edgeFields.add(ResourceElementsLuceneFieldsEnum.getKeywordEdge(field));
				edgeFields.add(ResourceElementsLuceneFieldsEnum.getTokenEdge(field));
				Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
				attributeForMultipleFieldsMap.put(partialString, edgeFields);
				if(ResourceElementsLuceneFieldsEnum.getActive(field)!=null) {
					activesSearch = this.addToMap(activesSearch, ResourceElementsLuceneFieldsEnum.getActive(field), 
							attributeForMultipleFieldsMap);
				}
				else
				setOfAttributeForMultipleFieldsMap.add(attributeForMultipleFieldsMap);
			}
			uniquesSearch = this.activesToMapSearch2(activesSearch);
			resourcesElements =  resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().findForWebTableWPermissionsPaginated(
					setOfAttributeForMultipleFieldsMap,uniquesSearch, eqMustSentenceOnField, permissionFields, idField, 
			eqFilters,searchProperties.isWholeWords(), index, paginationSize,
			sortField, sortType, asc);
		}
		
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
	public Integer countResourceElements(ISearchProperties searchProperties){
String partialString = searchProperties.getValue();
		
		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap = new HashSet<>();
		Set<Map<String, String>> setOfEqSentenceOnField = new HashSet<>();
		Map<String,Set<Map<String, Set<String>>>> activesSearch = new HashMap<>();
		Map<Map<String, String>, Set<Map<String, Set<String>>>> uniquesSearch = new HashMap<>();
		Map<Map<String, String>, Map<String, String>> uniques = new HashMap<>();
		
		Map<String,Map<String, String>> uniqueActives = new HashMap<>();
		
		List<String> fields = searchProperties.getFields();
		Map<String, String> eqSentenceOnField = new HashMap<>();
		Map<String, String> eqMustSentenceOnField = new HashMap<>();
		String idField = "resources";
		eqMustSentenceOnField.put("resActive", "true");
		Map<String, String> restrictions = searchProperties.getRestrictions();
		Map<String, String> permissionFields = new HashMap<>();
		Map<String, List<String>> eqFilters = new HashMap<>();
		
		Map<String, List<String>> filters = searchProperties.getFilters();
		
		
		for(String key : filters.keySet()){
			eqFilters.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, key), filters.get(key));
		}
		
		permissionFields.put("id.Auth_audo_user_id", String.valueOf(user.getCurrentUserLogged().getAuId()));
		permissionFields.put("id.Auth_audo_type_resource", "resources");	
		
		for(String key : restrictions.keySet()){
			eqMustSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, key), restrictions.get(key));
		}
				

		if(!searchProperties.isSugestions()) {
		if(searchProperties.getValue()!=null)
			for(String field : fields){
				if(ResourceElementsLuceneFieldsEnum.getActive(field)!=null) {
					uniqueActives = this.addToMap(uniqueActives, ResourceElementsLuceneFieldsEnum.getActive(field),
							ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, field), searchProperties.getValue());
				//eqMustSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getActive(field), "true");
				}
			else	
			eqSentenceOnField.put(ResourceElementsLuceneFieldsEnum.getLuceneField(searchProperties, field),searchProperties.getValue());
			}
		uniques = this.activesToMap2(uniqueActives);
		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().countForWebTableWPermissions(
		eqSentenceOnField, uniques,eqMustSentenceOnField, permissionFields, idField, 
		eqFilters,searchProperties.isWholeWords());
		}
		else {
			if(searchProperties.getValue()!=null)
			for(String field : fields){
				Set<String> edgeFields = new HashSet<>();
				edgeFields.add(ResourceElementsLuceneFieldsEnum.getKeywordEdge(field));
				edgeFields.add(ResourceElementsLuceneFieldsEnum.getTokenEdge(field));
				Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
				attributeForMultipleFieldsMap.put(partialString, edgeFields);
				if(ResourceElementsLuceneFieldsEnum.getActive(field)!=null) {
					activesSearch = this.addToMap(activesSearch, ResourceElementsLuceneFieldsEnum.getActive(field), 
							attributeForMultipleFieldsMap);
				}
				else
				setOfAttributeForMultipleFieldsMap.add(attributeForMultipleFieldsMap);
			}
			uniquesSearch = this.activesToMapSearch2(activesSearch);
			return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao().countForWebTableWPermissions(
					setOfAttributeForMultipleFieldsMap,uniquesSearch, eqMustSentenceOnField, permissionFields, idField, 
			eqFilters,searchProperties.isWholeWords());
		}	
	}
	

	@Override
	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString) {

		Set<Map<String, Set<String>>> setOfAttributeForMultipleFieldsMap = new HashSet<>();
		Set<Map<String, String>> setOfEqSentenceOnField = new HashSet<>();

		Set<String> fields = new HashSet<>();
		fields.add("keywordEdgeNGram_res_element");
		fields.add("tokenEdgeNGram_res_element");
		Map<String, Set<String>> attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);
		setOfAttributeForMultipleFieldsMap.add(attributeForMultipleFieldsMap);

		Map<String, String> eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("resActive", "true");
		setOfEqSentenceOnField.add(eqSentenceOnField);

		fields = new HashSet<>();
		fields.add("synonymses.id.keywordEdgeNGram_syn_synonym");
		fields.add("synonymses.id.tokenEdgeNGram_syn_synonym");
		attributeForMultipleFieldsMap = new HashMap<>();
		attributeForMultipleFieldsMap.put(partialString, fields);

		eqSentenceOnField = new HashMap<>();
		eqSentenceOnField.put("synonymses.id.synActive", "true");
		eqSentenceOnField.put("resActive", "true");
		setOfEqSentenceOnField.add(eqSentenceOnField);

		return resourcesLuceneManagerDao.getResourcesElememtsLuceneDao()
				.countSetOfMultiFieldSameAttributesAndSetOfExactByAttributes(setOfAttributeForMultipleFieldsMap, setOfEqSentenceOnField);
	}

}
