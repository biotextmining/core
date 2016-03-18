package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.process.IE.network.XGMMLPolygnos;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationAdvanceOptions;

public class REToNetworkConfigurationAdvanceOptionsImpl implements IREToNetworkConfigurationAdvanceOptions{

	private Set<String> lemmaAllowed;
	private boolean allowLonelyNodes;
	private boolean directed;
	private boolean entitiesRepresentedByprimaryNAme;
	private boolean ignoreClues;
	private boolean exportRelationsDetails;
	private boolean useGraphicWeights;

	private SortedSet<IRelationsType> relationTypesAllowed;	
	private Map<Long, XGMMLPolygnos> classXGMMLPolygnos;
	
	public REToNetworkConfigurationAdvanceOptionsImpl(Set<String> lemmaAllowed,boolean allowLonelyNodes,boolean directed,boolean entitiesRepresentedByprimaryNAme, boolean ignoreClues,
			SortedSet<IRelationsType> relationTypesAllowed,boolean exportRelationDetails,boolean useGraphicWeights,Map<Long, XGMMLPolygnos> classXGMMLPolygnos) {
		super();
		this.lemmaAllowed = lemmaAllowed;
		this.allowLonelyNodes = allowLonelyNodes;
		this.directed = directed;
		this.entitiesRepresentedByprimaryNAme = entitiesRepresentedByprimaryNAme;
		this.ignoreClues = ignoreClues;
		this.relationTypesAllowed = relationTypesAllowed;
		this.exportRelationsDetails = exportRelationDetails;
		this.useGraphicWeights = useGraphicWeights;
		this.classXGMMLPolygnos = classXGMMLPolygnos;
	}
	
	public Set<String> getLemmaVerbsAllow() {
		return lemmaAllowed;
	}

	public boolean allowLonelyNodes() {
		return allowLonelyNodes;
	}

	public SortedSet<IRelationsType> getRelationsType() {
		return relationTypesAllowed;
	}
	
	public boolean isDirectedNetwork() {
		return directed;
	}

	public boolean isEntitiesRepresentedByPrimaryName() {
		return entitiesRepresentedByprimaryNAme;
	}

	public boolean ignoreClues() {
		return ignoreClues;
	}
	

	public boolean exportRelationsDetails() {
		return exportRelationsDetails;
	}

	public Map<Long, XGMMLPolygnos> getXGMMLPolygnos() {
		return classXGMMLPolygnos;
	}

	public boolean useGraphicWeights() {
		return useGraphicWeights;
	}

}
