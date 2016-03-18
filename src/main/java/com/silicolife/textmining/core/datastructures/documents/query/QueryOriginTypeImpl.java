package com.silicolife.textmining.core.datastructures.documents.query;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.process.IR.IQueryOriginType;

public class QueryOriginTypeImpl implements IQueryOriginType {

	private long id;
	private String origin;

	public QueryOriginTypeImpl() {

	}

	public QueryOriginTypeImpl(long id, String origin) {
		this.id = id;
		this.origin = origin;
	}

	public QueryOriginTypeImpl(String origin) {
		this(GenerateRandomId.generateID(), origin);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
