package com.silicolife.textmining.core.datastructures.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public class ExternalIDImpl implements IExternalID{

	private String externalID;
	@JsonDeserialize(as = SourceImpl.class)
	private ISource source;
	
	public ExternalIDImpl(String externalID,ISource source)
	{
		this.externalID=externalID;
		this.source=source;
	}

	public ExternalIDImpl() {
		super();
	}
	
	@Override
	public String getExternalID() {
		return externalID;
	}

	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}

	@Override
	public ISource getSource() {
		return source;
	}
	
	public void setSource(ISource source) {
		this.source = source;
	}

	public String toString()
	{
		String result = getExternalID();
		if(getSource() != null)
			result = result + " ( "+getSource().getSource() + " )";
		return result;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((externalID == null) ? 0 : externalID.hashCode());
		result = prime * result + ((source == null) ? 0 : source.getSource().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExternalIDImpl other = (ExternalIDImpl) obj;
		if (externalID == null) {
			if (other.externalID != null)
				return false;
		} else if (!externalID.equals(other.externalID))
			return false;
		if (source == null) {
			if (other.source.getSource() != null)
				return false;
		} else if (!source.getSource().equals(other.source.getSource()))
			return false;
		return true;
	}
}
