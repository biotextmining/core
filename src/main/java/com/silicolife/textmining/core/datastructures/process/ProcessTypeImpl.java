package com.silicolife.textmining.core.datastructures.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.process.IProcessType;

public class ProcessTypeImpl implements IProcessType {

	private long id;
	private String type;

	public ProcessTypeImpl() {
		super();
	}

	public ProcessTypeImpl(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public static IProcessType getNERProcessType()
	{
		return new ProcessTypeImpl(GenerateRandomId.generateID(), GlobalNames.ner);
	}
	
	@JsonIgnore
	public static IProcessType getREProcessType()
	{
		return new ProcessTypeImpl(GenerateRandomId.generateID(), GlobalNames.re);
	}

}
