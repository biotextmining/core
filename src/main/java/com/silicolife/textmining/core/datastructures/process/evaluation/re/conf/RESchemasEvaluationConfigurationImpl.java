package com.silicolife.textmining.core.datastructures.process.evaluation.re.conf;

import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.re.eval.IRESchemasEvaluationConfiguration;

public class RESchemasEvaluationConfigurationImpl implements IRESchemasEvaluationConfiguration{
	
	private IRESchema goldenStandard;
	private IRESchema toCompate;
	private boolean allowClueOverlap;
	private boolean	allowsynonymsRange;
	
	public RESchemasEvaluationConfigurationImpl(IRESchema goldenStandard,IRESchema toCompate,boolean allowClueOverlap,boolean allowsynonymsRange)
	{
		this.goldenStandard=goldenStandard;
		this.toCompate=toCompate;
		this.allowClueOverlap=allowClueOverlap;
		this.allowsynonymsRange=allowsynonymsRange;
	}

	@Override
	public IRESchema getGoldStandard() {
		return goldenStandard;
	}

	@Override
	public IRESchema getToCompare() {
		return toCompate;
	}

	@Override
	public boolean allowClueOverlap() {
		return allowClueOverlap;
	}

	@Override
	public boolean allowsynonymsRange() {
		return allowsynonymsRange;
	}

}
