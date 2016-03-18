package com.silicolife.textmining.core.interfaces.process.IE.network;

import java.util.List;

public interface INetwork<N extends INode,E extends IEdge> {
	public String getID();
	public String getLabel();
	public String getVersion();
	public INetworkMetaData getMetaData();
	public List<IAtributes> getAtributesList();
	public boolean isDirected();
	public List<INode> getNodes();
	public List<IEdge> getEdges();
}
