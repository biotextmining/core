package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.layer.ResourceManagerReport;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIdsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelationTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelationsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.SynonymsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourceElementsRelationWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourcesElementExternalIdsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.ResourcesWrapper;
import com.silicolife.textmining.core.datastructures.report.resources.ResourceUpdateConflitsImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.datastructures.resources.content.ResourceClassContentImpl;
import com.silicolife.textmining.core.datastructures.resources.content.ResourceClassesContentImpl;
import com.silicolife.textmining.core.datastructures.resources.content.ResourceContentImpl;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;
import com.silicolife.textmining.core.interfaces.core.report.resources.ResourceUpdateConflitsType;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

@Service
@Transactional(readOnly = true)
public class ResourcesElementServiceImpl implements IResourcesElementService {

	private ResourcesManagerDao resourcesManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public ResourcesElementServiceImpl(ResourcesManagerDao resourcesManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.resourcesManagerDao = resourcesManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Transactional(readOnly = false)
	@Override
	public IResourceManagerReport addResourceElements(Long resourceID, List<IResourceElement> elements) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		for (IResourceElement elem : elements) {
			addResourceElement(report, resource, elem);
		}

		AuthUsers user = userLogged.getCurrentUserLogged();

		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources/resources_elements/synonyms/class/source/resource_element_external_ids", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return report;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElements(Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resActive", true);
		eqRestrictions.put("resources", resource);
		List<ResourceElements> resourcesElements = resourcesManagerDao.getResourcesElememtsDao().findByAttributes(eqRestrictions);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(Long resourceId, String termClass) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Classes klass = resourcesManagerDao.getClassesDao().findUniqueByAttribute("claName", termClass);
		if (klass == null)
			return elementSet;
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resActive", true);
		eqRestrictions.put("resources", resource);
		eqRestrictions.put("classes", klass);
		List<ResourceElements> resourcesElements = resourcesManagerDao.getResourcesElememtsDao().findByAttributes(eqRestrictions);

		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}

		return elementSet;

	}

	@Override
	public IResourceContent getResourceContent(Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Integer numberTerm = resourcesManagerDao.getResourcesAuxDao().findTermNumberByResource(resourceId);
		Integer numberSyn = resourcesManagerDao.getResourcesAuxDao().findSynNumberByResource(resourceId);

		ResourceClassesContentImpl classesContent = new ResourceClassesContentImpl();
		List<Classes> classes = resourcesManagerDao.getResourcesAuxDao().findClassesGroupByResourceId(resourceId);
		for (Classes klass : classes) {
			Integer synNumber = resourcesManagerDao.getResourcesAuxDao().findSynNumberByClass(klass.getClaId(), resourceId);
			Integer termNumber = resourcesManagerDao.getResourcesAuxDao().findTermNumberByClass(klass.getClaId(), resourceId);
			classesContent.addClassContent(klass.getClaId(), new ResourceClassContentImpl(termNumber, synNumber));
		}

		int externalIds = 0;
		IResourceContent content = new ResourceContentImpl(numberTerm, numberSyn, externalIds, classesContent);

		return content;
	}

	private void addResourceElement(IResourceManagerReport report, Resources resource, IResourceElement elem) {
		IAnoteClass klassStr = elem.getTermClass();
		Classes klass = null;
		if (klassStr != null) {
			klass = resourcesManagerDao.getClassesDao().findUniqueByAttribute("claName", klassStr.getName());
			if (klass == null) {
				klass = ClassesWrapper.convertToDaemonStructure(klassStr);
				resourcesManagerDao.getClassesDao().save(klass);
			}
			addResourceElement(report, resource, elem, klass);
		}
		else
		{
			addResourceElement(report, resource, elem, null);
		}
	}

	private void addResourceElement(IResourceManagerReport report, Resources resource, IResourceElement elem, Classes klass) {
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resources.resoId", resource.getResoId());
		eqRestrictions.put("resElement", elem.getTerm());
		eqRestrictions.put("resActive", true);

		boolean existResourceElement = resourcesManagerDao.getResourcesAuxDao().existResourceElementByResourceCaseSensitive(resource.getResoId(), elem.getTerm());

		if (existResourceElement) {
			List<ResourceElements> resourceElement = resourcesManagerDao.getResourcesElememtsDao().findByAttributesCaseSensitiveWithLimit(eqRestrictions, 1);
			updateResourceElement(report, resource, elem, klass, resourceElement.get(0));
		}else{
			boolean existSynonym = resourcesManagerDao.getResourcesAuxDao().existSynonymByResourceCaseSensitive(resource.getResoId(), elem.getTerm());

			if (existSynonym) {

				List<Synonyms> resourceElementSynonyms = resourcesManagerDao.getResourcesAuxDao().findSynonymsByResourceElementWithLimit(resource.getResoId(),
						elem.getTerm(), 1);

				Synonyms conflitElemnt = resourceElementSynonyms.get(0);
				long synonymclassID = conflitElemnt.getResourceElements().getClasses().getClaId();

				if (synonymclassID == klass.getClaId()) {

					IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveSynonyms, elem);
					report.addconflit(conflit);
					ResourceElements resourceElement = conflitElemnt.getResourceElements();
					addSynonyms(report, resource, elem, resourceElement);
					addExternalIds(report, elem, resourceElement);
				} else {

					IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.TermInDiferentClasses, elem, klass, conflitElemnt
							.getResourceElements().getClasses());
					report.addconflit(conflit);
				}

			} else {

				ResourceElements resElement = addResourceElements(report, resource, elem, klass);
				addSynonyms(report, resource, elem, resElement);
				addExternalIds(report, elem, resElement);
			}
		}
	}

	public Boolean existResourceElementByResourceCaseSensitive(Long resourceID, String name)
	{
		return resourcesManagerDao.getResourcesAuxDao().existResourceElementByResourceCaseSensitive(resourceID, name);
	}

	public Boolean existSynonymByResourceCaseSensitive(Long resourceID,String name)
	{
		return resourcesManagerDao.getResourcesAuxDao().existSynonymByResourceCaseSensitive(resourceID, name);
	}

	public Boolean existResourceElementAndSynonymsByResourceCaseSensitive(Long resourceID, String name)
	{
		return existResourceElementByResourceCaseSensitive(resourceID,name) && existSynonymByResourceCaseSensitive(resourceID,name);
	}

	private void updateResourceElement(IResourceManagerReport report, Resources resource, IResourceElement elem, Classes klass, ResourceElements resourceElement) {
		Classes conflitklass = null;
		Classes class_ = resourceElement.getClasses();
		if(class_==null || klass==null)
		{

		}
		else if (class_.getClaName().toLowerCase().equals(klass.getClaName().toLowerCase())) {
			addSynonyms(report, resource, elem, resourceElement);
			addExternalIds(report, elem, resourceElement);
		} else {
			conflitklass = class_;
		}
		if (conflitklass != null) {
			IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.TermInDiferentClasses, elem, klass, conflitklass);
			report.addconflit(conflit);
		} else {
			IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveTerm, elem);
			report.addconflit(conflit);
		}
	}

	private void addExternalIds(IResourceManagerReport report, IResourceElement elem, ResourceElements resElement) {

		List<IExternalID> externalIds = elem.getExtenalIDsImMemory();

		for (IExternalID externalId : externalIds) {
			Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findUniqueByAttribute("souDescription", externalId.getSource().getSource());

			if (source == null) {
				source = new Sources(externalId.getSource().getSourceID(), externalId.getSource().getSource());
				resourcesManagerDao.getResourcesElememtsSourcesDao().save(source);
			}

			ResourceElementExtenalIdsId externalIdId = new ResourceElementExtenalIdsId(resElement.getResId(), source.getSouId(), externalId.getExternalID());
			ResourceElementExtenalIds externalIdObj = resourcesManagerDao.getResourcesElememtsExtenalIDsDao().findById(externalIdId);
			if (externalIdObj == null || !externalIdObj.isReleActive()) {

				externalIdObj = new ResourceElementExtenalIds(externalIdId, resElement, source, true);
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().save(externalIdObj);
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().flushSession();
				report.addExternalID(1);
			} else {

				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlteradyHaveExternalID, elem, externalIdObj);
				report.addconflit(conflit);
			}
		}
	}

	private void addSynonyms(IResourceManagerReport report, Resources resource, IResourceElement elem, ResourceElements resElement) {

		List<String> synonyms = elem.getSynonyms();
		for (String syn : synonyms) {
			boolean resourceElementSyn = resourcesManagerDao.getResourcesAuxDao().existResourceElementByResourceCaseSensitive(resource.getResoId(), syn);
			boolean resourceElementSynonymsSyn = resourcesManagerDao.getResourcesAuxDao().existSynonymByResourceCaseSensitive(resource.getResoId(), syn);
			if (resourceElementSyn || resourceElementSynonymsSyn) {
				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveSynonyms, elem);
				report.addconflit(conflit);
			} else {
				SynonymsId id = new SynonymsId(resElement.getResId(), syn, true);
				Synonyms synObj = new Synonyms(id, resElement);
				resourcesManagerDao.getResourcesElememtsSynonymsDao().save(synObj);
				report.addSynonyms(1);
			}
		}
	}

	private ResourceElements addResourceElements(IResourceManagerReport report, Resources resource, IResourceElement elem, Classes klass) {

		ResourceElements newResourceElement = ResourceElementWrapper.convertToDaemonStructure(elem);
		newResourceElement.setResources(resource);
		if (klass != null)
			newResourceElement.setClasses(klass);

		resourcesManagerDao.getResourcesElememtsDao().save(newResourceElement);

		report.addTerms(1);

		return newResourceElement;
	}

	@Override
	public List<IExternalID> getResourceElementExternalIDs(Long resourceElementID) {
		ResourceElements resourceElement = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElementID);
		List<IExternalID> result = new ArrayList<IExternalID>();
		if (resourceElement == null)
			return result;
		Set<ResourceElementExtenalIds> externalIds = resourceElement.getResourceElementExtenalIdses();
		for (ResourceElementExtenalIds externalId : externalIds) {
			if (externalId.isReleActive()) {
				IExternalID externalIdObj_ = ResourcesElementExternalIdsWrapper.convertToAnoteStructure(externalId);
				result.add(externalIdObj_);
			}
		}
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public IResourceManagerReport addResourceElementsWithoutValidation(Long resourceID, List<IResourceElement> elements) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		for (IResourceElement elem : elements) {
			addResourceElementWithoutValidation(report, resource, elem);
			resourcesManagerDao.getResourceDao().flushSession();
		}

		AuthUsers user = userLogged.getCurrentUserLogged();

		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources/resources_elements/synonyms/class/source/resource_element_external_ids", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return report;
	}

	private void addResourceElementWithoutValidation(IResourceManagerReport report, Resources resource, IResourceElement elem) {
		IAnoteClass klassStr = elem.getTermClass();
		Classes klass = null;
		if (klassStr != null) {
			klass = resourcesManagerDao.getClassesDao().findUniqueByAttribute("claName", klassStr.getName());
			if (klass == null) {
				klass = ClassesWrapper.convertToDaemonStructure(klassStr);
				resourcesManagerDao.getClassesDao().save(klass);
			}
		}
		addResourceElementWithouValidation(report, resource, elem, klass);

	}

	private void addResourceElementWithouValidation(IResourceManagerReport report, Resources resource, IResourceElement elem, Classes klass) {

		ResourceElements resElement = addResourceElements(report, resource, elem, klass);
		addSynonymsWithoutValidation(report, resource, elem, resElement);
		addExternalIdsWithouValidation(report, elem, resElement);

	}

	private void addExternalIdsWithouValidation(IResourceManagerReport report, IResourceElement elem, ResourceElements resElement) {
		List<IExternalID> externalIds = elem.getExtenalIDsImMemory();
		for (IExternalID externalId : externalIds) {
			Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findUniqueByAttribute("souDescription", externalId.getSource().getSource());

			if (source == null) {
				source = new Sources(externalId.getSource().getSourceID(), externalId.getSource().getSource());
				resourcesManagerDao.getResourcesElememtsSourcesDao().save(source);
			}

			ResourceElementExtenalIdsId externalIdId = new ResourceElementExtenalIdsId(resElement.getResId(), source.getSouId(), externalId.getExternalID());
			ResourceElementExtenalIds externalIdObj = new ResourceElementExtenalIds(externalIdId, resElement, source, true);
			if (resourcesManagerDao.getResourcesElememtsExtenalIDsDao().findById(externalIdId) == null) {
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().save(externalIdObj);
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().flushSession();
				report.addExternalID(1);
			} else {
				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlteradyHaveExternalID, elem, externalIdObj);
				report.addconflit(conflit);
			}
		}
	}

	private void addSynonymsWithoutValidation(IResourceManagerReport report, Resources resource, IResourceElement elem, ResourceElements resElement) {

		List<String> synonyms = elem.getSynonyms();
		for (String syn : synonyms) {
			SynonymsId id = new SynonymsId(resElement.getResId(), syn, true);
			Synonyms synObj = new Synonyms(id, resElement);
			resourcesManagerDao.getResourcesElememtsSynonymsDao().save(synObj);
			report.addSynonyms(1);
		}

	}

	@Override
	public Set<IAnoteClass> getResourceClassContent(Long resourceId) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		Set<IAnoteClass> resourceContent = new HashSet<>();
		List<Classes> classes = resourcesManagerDao.getResourcesAuxDao().findClassesGroupByResourceId(resourceId);
		for (Classes klass : classes) {
			resourceContent.add(ClassesWrapper.convertToAnoteStructure(klass));
		}

		return resourceContent;
	}

	@Override
	public Boolean checkResourceElementExistsInResource(Long resourceId, String term) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions("default", "default");

		return resourcesManagerDao.getResourcesAuxDao().existResourceElementByResourceCaseSensitive(resourceId, term);
	}

	@Transactional(readOnly=false)
	@Override
	public Boolean removeResourceClass(Long resourceId, Long classID) throws ResourcesExceptions {

		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		Classes klass = resourcesManagerDao.getClassesDao().findById(classID);
		if (klass == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoClass, ExceptionsCodes.msgNoClass);

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resources", resource);
		eqRestrictions.put("classes", klass);
		List<ResourceElements> resourceElements = resourcesManagerDao.getResourcesElememtsDao().findByAttributesCaseSensitive(eqRestrictions);
		for (ResourceElements elem : resourceElements) {
			elem.setResActive(false);
			resourcesManagerDao.getResourcesElememtsDao().update(elem);
		}

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources_elements", null, "update resource Elements");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly=false)
	@Override
	public IResourceManagerReport addResourceElementSynonyms(Long resourceID, Long resourceElmentID, List<String> newSynonyms) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions("default", "default");
		for (String cadidateSynonym : newSynonyms) {
			Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
			eqRestrictions.put("resources", resource);
			eqRestrictions.put("resElement", cadidateSynonym);
			eqRestrictions.put("resActive", true);
			List<ResourceElements> resourceElement = resourcesManagerDao.getResourcesElememtsDao().findByAttributesCaseSensitive(eqRestrictions);
			List<Synonyms> resourceElementSynonyms = resourcesManagerDao.getResourcesAuxDao().findSynonymsByResourceElement(resource.getResoId(), cadidateSynonym);
			if (!resourceElement.isEmpty()) {
				IResourceElement elem = new ResourceElementImpl(cadidateSynonym, null, null, new ArrayList<String>(), 0, false);
				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveTerm, elem);
				report.addconflit(conflit);
			} else if (!resourceElementSynonyms.isEmpty()) {
				IResourceElement elem = new ResourceElementImpl(cadidateSynonym, null, null, new ArrayList<String>(), 0, false);
				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveSynonyms, elem);
				report.addconflit(conflit);
			} else {
				ResourceElements resourceElements = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
				if (resourceElements == null)
					throw new ResourcesExceptions("default", "default");
				SynonymsId id = new SynonymsId(resourceElements.getResId(), cadidateSynonym, true);
				Synonyms synonyms = new Synonyms(id, resourceElements);
				resourceElements.getSynonymses().add(synonyms);
				resourcesManagerDao.getResourcesElememtsSynonymsDao().save(synonyms);
			}
		}
		//
		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources_elements/synonyms", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return report;
	}

	@Transactional(readOnly=false)
	@Override
	public IResourceManagerReport addResourceElementExternalIDs(Long resourseID, Long resourceElmentID, List<IExternalID> externalIDs) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourseID);
		if (resource == null)
			throw new ResourcesExceptions("default", "default");
		ResourceElements resElement = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resElement == null)
			throw new ResourcesExceptions("default", "default");
		for (IExternalID externalId : externalIDs) {
			Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findUniqueByAttribute("souDescription", externalId.getSource().getSource());

			if (source == null) {
				source = new Sources(externalId.getSource().getSourceID(), externalId.getSource().getSource());
				resourcesManagerDao.getResourcesElememtsSourcesDao().save(source);
			}

			ResourceElementExtenalIdsId externalIdId = new ResourceElementExtenalIdsId(resElement.getResId(), source.getSouId(), externalId.getExternalID());
			ResourceElementExtenalIds externalIdObj = new ResourceElementExtenalIds(externalIdId, resElement, source, true);
			ResourceElementExtenalIds test = resourcesManagerDao.getResourcesElememtsExtenalIDsDao().findById(externalIdId);
			if (test == null || !test.isReleActive()) {
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().save(externalIdObj);
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().flushSession();
				report.addExternalID(1);
			} else {
				IResourceElement elem = ResourceElementWrapper.convertToAnoteStructure(resElement);
				IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlteradyHaveExternalID, elem, externalIdObj);
				report.addconflit(conflit);
			}
		}

		//
		AuthUsers user = userLogged.getCurrentUserLogged();

		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "source/resource_element_external_ids", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return report;
	}

	@Transactional(readOnly = false)
	@Override
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(elem.getId());
		ResourceElements resourceWithUpdateInformation = ResourceElementWrapper.convertToDaemonStructure(elem);
		if (resouResource == null)
			throw new ResourcesExceptions("default", "default");
		resouResource.setResElement(elem.getTerm());
		resouResource.setClasses(resourceWithUpdateInformation.getClasses());
		if(elem.getPriority()>0)
			resouResource.setResPriorety(elem.getPriority());
		resourcesManagerDao.getResourcesElememtsDao().update(resouResource);
		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources_elements", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return report;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean inactivateResourceElement(Long resourceElmentID) throws ResourcesExceptions {
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		resouResource.setResActive(false);
		resourcesManagerDao.getResourcesElememtsDao().update(resouResource);

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources_elements", null, "update resource element");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeResourceElementSynonyms(Long resourceElmentID) throws ResourcesExceptions {
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);
		for (Synonyms syn : resouResource.getSynonymses()) {

			resourcesManagerDao.getResourcesAuxDao().updateSynonym(syn.getId().getSynResourceElementId(), syn.getId().getSynSynonym(), false,
					syn.getId().getSynResourceElementId(), syn.getId().getSynSynonym(), syn.getId().isSynActive());

		}

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resources_elements/synonyms", null, "update synonym");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public IResourceManagerReport updateResourceElementSynonym(Long resourceID, Long resourceElmentID, String oldSynonym, String newSynonym) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();

		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);

		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		for (Synonyms syn : resouResource.getSynonymses()) {
			if (syn.getId().getSynSynonym().equals(oldSynonym)) {

				Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
				eqRestrictions.put("resources", resource);
				eqRestrictions.put("resElement", newSynonym);
				eqRestrictions.put("resActive", true);

				List<ResourceElements> resourceElement = resourcesManagerDao.getResourcesElememtsDao().findByAttributesCaseSensitive(eqRestrictions);
				List<Synonyms> resourceElementSynonyms = resourcesManagerDao.getResourcesAuxDao().findSynonymsByResourceElement(resource.getResoId(), newSynonym);
				if (!resourceElement.isEmpty()) {
					IResourceElement elem = new ResourceElementImpl(newSynonym, null, null, new ArrayList<String>(), 0, false);
					IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveTerm, elem);
					report.addconflit(conflit);
				} else if (!resourceElementSynonyms.isEmpty()) {
					IResourceElement elem = new ResourceElementImpl(newSynonym, null, null, new ArrayList<String>(), 0, false);
					IResourceUpdateConflits conflit = new ResourceUpdateConflitsImpl(ResourceUpdateConflitsType.AlreadyHaveSynonyms, elem);
					report.addconflit(conflit);
				} else {

					resourcesManagerDao.getResourcesAuxDao().updateSynonym(syn.getId().getSynResourceElementId(), newSynonym, syn.getId().isSynActive(),
							syn.getId().getSynResourceElementId(), syn.getId().getSynSynonym(), syn.getId().isSynActive());
				}
			}
		}

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "resource_element/synonyms", null, "update synonym");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return report;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeResourceElementSynonym(Long resourceElmentID, String synonym) throws ResourcesExceptions {
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);

		for (Synonyms syn : resouResource.getSynonymses()) {
			if (syn.getId().getSynSynonym().equals(synonym)) {
				resourcesManagerDao.getResourcesAuxDao().updateSynonym(syn.getId().getSynResourceElementId(), syn.getId().getSynSynonym(), false,
						syn.getId().getSynResourceElementId(), syn.getId().getSynSynonym(), syn.getId().isSynActive());
			}
		}

		AuthUsers user = userLogged.getCurrentUserLogged();

		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "synonyms", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeResourceElementExternalID(Long resourceElmentID, IExternalID extID) throws ResourcesExceptions {
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);

		for (ResourceElementExtenalIds resExt : resouResource.getResourceElementExtenalIdses()) {
			if (resExt.getSources().getSouDescription().equalsIgnoreCase(extID.getSource().getSource()) && resExt.getId().getReleExternalId().equals(extID.getExternalID())) {
				resExt.setReleActive(false);
				resourcesManagerDao.getResourcesElememtsExtenalIDsDao().update(resExt);

				AuthUsers user = userLogged.getCurrentUserLogged();
				/*
				 * log
				 */
				AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resource_element_external_ids", null, "update resource");
				usersManagerDao.getAuthUserLogsDao().save(log);
			}
		}

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean removeResourceElementAllExternalID(Long resourceElmentID) throws ResourcesExceptions {
		ResourceElements resouResource = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentID);
		if (resouResource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);

		for (ResourceElementExtenalIds resExt : resouResource.getResourceElementExtenalIdses()) {
			resExt.setReleActive(false);
			resourcesManagerDao.getResourcesElememtsExtenalIDsDao().update(resExt);
		}
		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "resource_element_external_ids", null, "update resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public int getResourceMaxPriorety(Long resourceID)throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		int maxInt = resourcesManagerDao.getResourcesAuxDao().getResourceMaxPriorety(resourceID);
		return maxInt;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addResourceElementsRelation(Long resourceElmentIDa,Long resourceElmentIDb, String relationType) throws ResourcesExceptions {
		ResourceElements resouElemntA = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentIDa);
		if (resouElemntA == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);		
		ResourceElements resouElemntB = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElmentIDb);
		if (resouElemntB == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);		

		ResourceElementRelationTypes type = resourcesManagerDao.getResourcesElememtsRelationTypeDao().findUniqueByAttribute("rerRelationType", relationType);
		if(type==null)
		{
			ResourceElementRelationTypes inserttype = new ResourceElementRelationTypes(GenerateRandomId.generateID(), relationType);
			resourcesManagerDao.getResourcesElememtsRelationTypeDao().save(inserttype);
			type = inserttype;
		}
		ResourceElementRelationsId id = new ResourceElementRelationsId(resourceElmentIDb, resourceElmentIDa);
		ResourceElementRelations idalreadyexist = resourcesManagerDao.getResourcesElememtsRelationsDao().findById(id);
		if(idalreadyexist==null)
		{
			ResourceElementRelations relations = new ResourceElementRelations(id , type, resouElemntA, resouElemntB);
			resourcesManagerDao.getResourcesElememtsRelationsDao().save(relations);
		}

		AuthUsers user = userLogged.getCurrentUserLogged();
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "resource_element_relations", null, "create relations");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;

	}

	@Override
	public List<IResourceElementsRelation> getResourceElementsRelations(Long resourceID) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceID);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		List<ResourceElementRelations> resourceElementsRElation = resourcesManagerDao.getResourcesAuxDao().getResourceElementsRelations(resourceID);
		List<IResourceElementsRelation> result = ResourceElementsRelationWrapper.convertToAnoteStructure(resourceElementsRElation);
		return result;
	}

	@Override
	public IResource<IResourceElement> getResourceFromResourceElement(Long resourceElementID) throws ResourcesExceptions {
		ResourceElements resouElemnt = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElementID);
		if (resouElemnt == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);	
		Resources resource = resouElemnt.getResources();
		IResource<IResourceElement> resourceResult = ResourcesWrapper.convertToAnoteStructure(resource);
		return resourceResult;
	}

	@Override
	public IResourceElement getResourceElemenByID(Long resourceElementID) throws ResourcesExceptions {
		ResourceElements resouElemnt = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElementID);
		if (resouElemnt == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);			
		return ResourceElementWrapper.convertToAnoteStructure(resouElemnt);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByName(Long resourceId, String name) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resActive", true);
		eqRestrictions.put("resources", resource);
		eqRestrictions.put("resElement", name);

		List<ResourceElements> resourcesElements = resourcesManagerDao.getResourcesElememtsDao().findByAttributes(eqRestrictions);

		List<Synonyms> synonymElements = resourcesManagerDao.getResourcesAuxDao().findSynonymsByResourceElement(resource.getResoId(), name);

		for(Synonyms syn :synonymElements){
			resourcesElements.add(syn.getResourceElements());
		}

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}

		return elementSet;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(Long resourceId, Integer index,
			Integer batchSize) throws ResourcesExceptions {
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);

		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("resActive", true);
		eqRestrictions.put("resources", resource);
		List<ResourceElements> resourcesElements = resourcesManagerDao.getResourcesElememtsDao().findByAttributesWithPagniation(eqRestrictions, index, batchSize);

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		for (ResourceElements resourceElement : resourcesElements) {
			IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
			elementSet.addElementResource(resourceElement_);
		}

		return elementSet;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID extID)
			throws ResourcesExceptions {
		Sources source = resourcesManagerDao.getResourcesElememtsSourcesDao().findUniqueByAttribute("souDescription", extID.getSource().getSource());

		if (source == null) {
			throw new ResourcesExceptions(ExceptionsCodes.codeNoSource, ExceptionsCodes.msgNoSource);
		}

		List<ResourceElementExtenalIds> resourceElementExtenalIds = resourcesManagerDao.getResourcesAuxDao().getResourceElementExternalIdBySourceAndExternalId(source.getSouId(), extID.getExternalID());

		IResourceElementSet<IResourceElement> elementSet = new ResourceElementSetImpl<IResourceElement>();

		for(ResourceElementExtenalIds resourceElementExtenalId : resourceElementExtenalIds){
			ResourceElements resourceElement = resourceElementExtenalId.getResourceElements();
			if(resourceElement.isResActive()){
				IResourceElement resourceElement_ = ResourceElementWrapper.convertToAnoteStructure(resourceElement);
				elementSet.addElementResource(resourceElement_);
			}
		}

		return elementSet;
	}

	@Override
	public IResourceManagerReport addResourceElementSynonymsWithoutValidation(long resourceId, long resourceElementID,List<String> synonyms) throws ResourcesExceptions {
		IResourceManagerReport report = new ResourceManagerReport();
		Resources resource = resourcesManagerDao.getResourceDao().findById(resourceId);
		if (resource == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResource, ExceptionsCodes.msgNoResource);
		ResourceElements resouElemnt = resourcesManagerDao.getResourcesElememtsDao().findById(resourceElementID);
		if (resouElemnt == null)
			throw new ResourcesExceptions(ExceptionsCodes.codeNoResourceElement, ExceptionsCodes.msgNoResourceElement);	

		for (String syn : synonyms) {
			SynonymsId id = new SynonymsId(resouElemnt.getResId(), syn, true);
			Synonyms synObj = new Synonyms(id, resouElemnt);
			resourcesManagerDao.getResourcesElememtsSynonymsDao().save(synObj);
			report.addSynonyms(1);
		}		
		return report;
	}
}