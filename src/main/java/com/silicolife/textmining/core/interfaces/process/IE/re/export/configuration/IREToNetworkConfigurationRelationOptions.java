package com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration;

import java.util.EnumSet;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;

public interface IREToNetworkConfigurationRelationOptions {
	public EnumSet<PolarityEnum> getPolaritiesAllowed();
	public EnumSet<DirectionallyEnum> getDirectionallyAllowed();
	public SortedSet<ICardinality> getCardinalitiesAllowed();
}
