package com.silicolife.textmining.core.datastructures.general;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public class SourceImpl implements ISource{
	
	private long sourceID;
	private String source;
	
	public SourceImpl(long sourceID,String source)
	{
		this.sourceID = sourceID;
		this.source = source;
	}
	
	public SourceImpl(String source)
	{
		this(GenerateRandomId.generateID(),source);
	}
	
	public SourceImpl() {
		super();
	}
	
	@Override
	public long getSourceID() {
		return sourceID;
	}

	@Override
	public String getSource() {
		return source;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	public String toString()
	{
		return getSource();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + (int) (sourceID ^ (sourceID >>> 32));
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
		SourceImpl other = (SourceImpl) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (sourceID != other.sourceID)
			return false;
		return true;
	}

}
