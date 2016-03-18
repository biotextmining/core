package com.silicolife.textmining.core.interfaces.core.document.structure;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PublicationFieldTypeEnum;

public interface IPublicationField {

	public String getName();

	public long getStart();

	public long getEnd();

	public PublicationFieldTypeEnum getFieldType();

}
