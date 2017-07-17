package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.io.Serializable;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PublicationFieldTypeEnum;

public interface IPublicationField extends Serializable{

	public String getName();

	public long getStart();

	public long getEnd();

	public PublicationFieldTypeEnum getFieldType();

}
