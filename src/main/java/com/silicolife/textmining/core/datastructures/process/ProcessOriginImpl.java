package com.silicolife.textmining.core.datastructures.process;

import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;

public class ProcessOriginImpl implements IProcessOrigin {

	private long id;
	private String origin;

	public ProcessOriginImpl(long id, String origin) {
		super();
		this.id = id;
		this.origin = origin;
	}
	
	public ProcessOriginImpl() {
		super();
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
