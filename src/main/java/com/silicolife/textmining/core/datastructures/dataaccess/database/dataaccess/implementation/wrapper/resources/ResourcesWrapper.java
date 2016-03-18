package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.lookuptable.LookupTableImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.datastructures.resources.rule.RuleSetImpl;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;

public class ResourcesWrapper {

	// -- Return interface because its here in wrapper where decide which object
	// return
	public static IResource<IResourceElement> convertToAnoteStructure(Resources resource) {
		long id = resource.getResoId();
		String name = resource.getResoResourceName();
		if (name == null)
			name = "";
		String notes = resource.getResoNotes();
		if (notes == null)
			notes = "";
		String type = resource.getResourceTypes().getRtyResourceType();
		ResourcesTypeEnum resourceTypeEnum = ResourcesTypeEnum.valueOf(type);

		IResource<IResourceElement> resource_ = null;

		switch (resourceTypeEnum) {

		case dictionary:
			resource_ = new DictionaryImpl(id, name, notes, true);
			break;
		case lookuptable:
			resource_ = new LookupTableImpl(id, name, notes, true);
			break;
		case rule:
			resource_ = new RuleSetImpl(id, name, notes, true);
			break;
		case ontology:
			resource_ = new OntologyImpl(id, name, notes, true);
			break;
		case lexicalwords:
			resource_ = new LexicalWordsImpl(id, name, notes, true);
			break;
		default:
			break;

		}

		return resource_;

	}

	public static Resources convertToDaemonStructure(IResource<IResourceElement> resource_) {
		long id = resource_.getId();
		String name = resource_.getName();
		String notes = resource_.getInfo();
		String type = resource_.getType();

		if (name.trim().equals(""))
			name = null;
		if (notes.trim().equals(""))
			notes = null;
		if (type != null && type.trim().equals(""))
			type = null;

		ResourceTypes resourceTypes = new ResourceTypes(GenerateRandomId.generateID(), type);

		Resources resources = new Resources(id, resourceTypes, name, true);
		resources.setResoNotes(notes);

		return resources;
	}
}
