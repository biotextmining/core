package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceElementWrapper {

	public static ResourceElements convertToDaemonStructure(IResourceElement elem) {
		if(elem==null)
			return null;
		String term = elem.getTerm();
		if (term.trim().equals(""))
			term = null;

		ResourceElements resourceElements = new ResourceElements();
		resourceElements.setResId(elem.getId());
		if (elem.getTermClass() != null)
			resourceElements.setClasses(ClassesWrapper.convertToDaemonStructure(elem.getTermClass()));
		resourceElements.setResActive(elem.isActive());
		resourceElements.setResElement(term);
		resourceElements.setResPriorety(elem.getPriority());
		return resourceElements;
	}

	public static IResourceElement convertToAnoteStructure(ResourceElements resourceElement) {

		long id = resourceElement.getResId();
		String element = resourceElement.getResElement();
		if (element == null)
			element = new String();

		Integer priority = resourceElement.getResPriorety();
		boolean active = resourceElement.isResActive();

		Classes klass = resourceElement.getClasses();
		IAnoteClass klass_ = null;
		if (klass != null)
			klass_ = ClassesWrapper.convertToAnoteStructure(klass);

//		Set<ResourceElementExtenalIds> externalIds = resourceElement.getResourceElementExtenalIdses();
//		List<IExternalID> externalIds_ = new ArrayList<IExternalID>();
//		for (ResourceElementExtenalIds externalId : externalIds) {
//			IExternalID externalIdObj_ = ResourcesElementExternalIdsWrapper.convertToAnoteStructure(externalId);
//			externalIds_.add(externalIdObj_);
//		}

		Set<Synonyms> synonyms = resourceElement.getSynonymses();
		List<String> synonyms_ = new ArrayList<String>();
		for (Synonyms synonym : synonyms) {
			if(synonym.getId().isSynActive())
				synonyms_.add(synonym.getId().getSynSynonym());
		}

		ResourceElementImpl resourceElement_ = new ResourceElementImpl(id, element, klass_, null, synonyms_, priority, active);
		return resourceElement_;
	}
}
