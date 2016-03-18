package com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.process.IE.network.XGMMLPolygnos;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;


public interface IREToNetworkConfigurationAdvanceOptions {
	public Set<String> getLemmaVerbsAllow();
	public boolean allowLonelyNodes();
	public boolean isDirectedNetwork();
	public boolean isEntitiesRepresentedByPrimaryName();
	public boolean ignoreClues();
	public SortedSet<IRelationsType> getRelationsType();
	public boolean exportRelationsDetails();
	public boolean useGraphicWeights();
	public Map<Long,XGMMLPolygnos> getXGMMLPolygnos();
}
