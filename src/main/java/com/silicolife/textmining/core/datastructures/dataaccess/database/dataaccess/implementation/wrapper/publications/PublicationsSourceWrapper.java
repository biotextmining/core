package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSourcesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.documents.PublicationExternalSourceLinkImpl;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;

/**
 * Class to transform anote2 Publications Source structures to daemon
 * Publications Source structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsSourceWrapper {

	public static PublicationExternalSourceLinkImpl convertToAnoteStructure(PublicationHasSources pubHasPubSource) {
		Long sourceId = pubHasPubSource.getId().getPhpsPublicationSourceId();
		String sourceInternalId = pubHasPubSource.getId().getPhpsPublicationSourceInternalId();
		String sourceDesc = pubHasPubSource.getPublicationSources().getPssDescription();
		if (sourceDesc == null)
			sourceDesc = "";
		if (sourceInternalId == null)
			sourceInternalId = "";
		PublicationExternalSourceLinkImpl pubExternal_ = new PublicationExternalSourceLinkImpl(sourceInternalId, sourceId, sourceDesc);
		return pubExternal_;
	}

	public static PublicationHasSources convertToDaemonStructure(IPublicationExternalSourceLink pubExternal_, Publications publication) {
		Long sourceId = pubExternal_.getSourceId();
		String sourceInternalId = pubExternal_.getSourceInternalId();
		String sourceDesc = pubExternal_.getSource();
		if (sourceDesc.trim().equals(""))
			sourceDesc = null;
		if (sourceInternalId.trim().equals(""))
			sourceInternalId = null;
		PublicationSources pubSource = new PublicationSources(sourceId, sourceDesc);
		PublicationHasSourcesId id = new PublicationHasSourcesId(sourceId, sourceInternalId, publication.getPubId());
		PublicationHasSources pubHasPubSource = new PublicationHasSources(id, pubSource, publication);

		return pubHasPubSource;
	}

}
