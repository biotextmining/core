package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import java.util.EnumSet;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.ICardinality;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationRelationOptions;

public class REToNetworkConfigurationRelationOptionsImpl implements IREToNetworkConfigurationRelationOptions {

	private EnumSet<PolarityEnum> polaritiesAllowed;
	private EnumSet<DirectionallyEnum> directionallyAllowed;
	private SortedSet<ICardinality> getCardinalitiesAllowed;
	
	
	public REToNetworkConfigurationRelationOptionsImpl(
			EnumSet<PolarityEnum> polaritiesAllowed, EnumSet<DirectionallyEnum> directionallyAllowed,
			SortedSet<ICardinality> getCardinalitiesAllowed) {
		super();
		this.polaritiesAllowed = polaritiesAllowed;
		this.directionallyAllowed = directionallyAllowed;
		this.getCardinalitiesAllowed = getCardinalitiesAllowed;
	}

	public EnumSet<PolarityEnum> getPolaritiesAllowed() {
		return polaritiesAllowed;
	}

	public EnumSet<DirectionallyEnum> getDirectionallyAllowed() {
		return directionallyAllowed;
	}

	public SortedSet<ICardinality> getCardinalitiesAllowed() {
		return getCardinalitiesAllowed;
	}

}
