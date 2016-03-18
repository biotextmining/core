package com.silicolife.textmining.core.datastructures.documents.structure;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PublicationFieldTypeEnum;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPublicationField;

public class PublicationFieldImpl implements IPublicationField {

	private long start;
	private long end;
	private String name;
	private PublicationFieldTypeEnum fieldType;

	public PublicationFieldImpl() {
	}

	public PublicationFieldImpl(long start, long end, String name, PublicationFieldTypeEnum fieldType) {
		this.start = start;
		this.end = end;
		this.name = name;
		this.fieldType = fieldType;
	}

	@Override
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	@Override
	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@Override
	public PublicationFieldTypeEnum getFieldType() {
		return fieldType;
	}

	@JsonGetter("fieldType")
	public String getFieldTypeEnumString() {
		return fieldType.toString();
	}

	@JsonIgnore
	public void setFieldType(PublicationFieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}

	@JsonSetter("fieldType")
	public void setFieldTypeEnumString(String fieldType) {
		this.fieldType = PublicationFieldTypeEnum.valueOf(fieldType);
	}
}
