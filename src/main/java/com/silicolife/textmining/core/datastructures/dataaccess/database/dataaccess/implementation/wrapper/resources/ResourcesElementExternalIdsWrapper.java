package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.general.ExternalIDImpl;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public class ResourcesElementExternalIdsWrapper {

	public static IExternalID convertToAnoteStructure(ResourceElementExtenalIds externslIds) {
		String externalId = externslIds.getId().getReleExternalId();
		ISource source = SourcesWrapper.convertToAnoteStructure(externslIds.getSources());
		ExternalIDImpl externalImpl = new ExternalIDImpl(externalId, source);
		return externalImpl;
	}
}
