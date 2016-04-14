package com.silicolife.textmining.core.datastructures.process.evaluation.ner.conf;

import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;
import com.silicolife.textmining.core.interfaces.process.IE.ner.eval.INERSchemasEvaluationConfiguration;

public class NERSchemasEvaluationConfigurationImpl implements INERSchemasEvaluationConfiguration{
	
	private INERSchema goldStandard;
	private INERSchema toCompare;
	private boolean ignoreClasses;
	
	
	public NERSchemasEvaluationConfigurationImpl(INERSchema goldStandard,INERSchema toCompare,Boolean ignoreClasses) {
		this.goldStandard=goldStandard;
		this.toCompare=toCompare;
		this.ignoreClasses=ignoreClasses;
	}

	@Override
	public INERSchema getGoldStandard() {
		return goldStandard;
	}

	@Override
	public INERSchema getToCompare() {
		return toCompare;
	}

	@Override
	public boolean isIgnoreClasses() {
		return ignoreClasses;
	}
	

}
