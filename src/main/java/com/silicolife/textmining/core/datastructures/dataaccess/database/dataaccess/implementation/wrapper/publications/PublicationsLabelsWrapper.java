package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabelsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.documents.lables.PublicationLabelImpl;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;

/**
 * Class to transform anote2 Publications Labels structures to daemon
 * Publications Labels structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsLabelsWrapper {

	public static PublicationLabelImpl convertToAnoteStructure(PublicationLabels publicationsLabels) {
		Long labelId = publicationsLabels.getPlaId();
		String labelDesc = publicationsLabels.getPlaDescription();
		if (labelDesc == null)
			labelDesc = "";
		PublicationLabelImpl publicationsLabels_ = new PublicationLabelImpl(labelId, labelDesc);
		return publicationsLabels_;
	}

	public static PublicationHasLabels convertToDaemonStructure(IPublicationLabel publicationsLabels_, Publications publication) {
		Long labelId = publicationsLabels_.getId();
		String labelDesc = publicationsLabels_.getLabel();
		if (labelDesc.trim().equals(""))
			labelDesc = null;
		else if (labelDesc.length() > 255)
			labelDesc = labelDesc.substring(0, 255);

		PublicationLabels pubLabel = new PublicationLabels(labelId, labelDesc);
		PublicationHasLabelsId id = new PublicationHasLabelsId(publication.getPubId(), labelId);
		PublicationHasLabels pubHasPubLabels = new PublicationHasLabels(id, pubLabel, publication);
		return pubHasPubLabels;
	}
}
