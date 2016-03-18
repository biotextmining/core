package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFieldsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PublicationFieldTypeEnum;
import com.silicolife.textmining.core.datastructures.documents.structure.PublicationFieldImpl;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

/**
 * Class to transform anote2 Publications Fields structures to daemon
 * Publications Fields structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsFieldsWrapper {

	public static PublicationFieldImpl convertToAnoteStructure(PublicationFields publicationsFields) {
		Long start = publicationsFields.getPflFieldStart();
		Long end = publicationsFields.getPflFieldEnd();
		String field = publicationsFields.getId().getPflField();
		if (field == null)
			field = "";
		PublicationFieldTypeEnum fieldTypeEnum = PublicationFieldTypeEnum.valueOf(publicationsFields.getPflText());
		PublicationFieldImpl publicationsFields_ = new PublicationFieldImpl(start, end, field, fieldTypeEnum);
		return publicationsFields_;
	}

	public static PublicationFields convertToDaemonStructure(IPublicationField publicationsFields_, Publications publication) {
		Long start = publicationsFields_.getStart();
		Long end = publicationsFields_.getEnd();
		String name = publicationsFields_.getName();
		String fieldType = publicationsFields_.getFieldType().toString();
		
		if (name.trim().equals(""))
			name = null;
		PublicationFieldsId id = new PublicationFieldsId(name, publication.getPubId());
		PublicationFields publicationField = new PublicationFields(id, publication, start, end, fieldType);
		return publicationField;
	}
}
