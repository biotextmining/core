package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.utils.IEvaluation;

public interface IRESchemaEvaluation extends IEvaluation{
	
	public List<IEventAnnotation> getSameInBothSchemas();
	public List<IEventAnnotation> getJustInGoldenStandard();
	public List<IEventAnnotation> getJustInToCompare();
	public List<IEventAnnotation> getFailByEntitiesMissing();
	
	public Map<Long, IEventAnnotation> getDocumentSameInBothSchemas();
	public Map<Long, IEventAnnotation> getDocumentjustInGoldenStandard();
	public Map<Long, IEventAnnotation> getDocumentjustInToCompare();
	public Map<Long, IEventAnnotation> getDocumentfailByEntitiesMissing();
	public IREClassTypesEvaluation getScoresofEachRelationType();

}
