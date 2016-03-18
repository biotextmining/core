package com.silicolife.textmining.core.datastructures.documents.query;

import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;

public class QueryPublicationRelevanceImpl implements IQueryPublicationRelevance{

	
	private RelevanceTypeEnum relevance;
	
	public QueryPublicationRelevanceImpl()
	{
		this.relevance = RelevanceTypeEnum.none;
	}
	
	
	public QueryPublicationRelevanceImpl(RelevanceTypeEnum relevance)
	{
		this.relevance = relevance;

	}
	
	@Override
	public RelevanceTypeEnum getRelevance() {
		return relevance;
	}

	@Override
	public void setRelevance(RelevanceTypeEnum newRelevance) {
		this.relevance = newRelevance;
	}

}
