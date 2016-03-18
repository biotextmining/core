package com.silicolife.textmining.core.interfaces.process.IE.network;

import java.util.List;


public interface IEdge extends Comparable<IEdge>{
	public String getID();
	public String getLabel();
	public INode getSourceNode();
	public INode getTargetNode();
	public List<IAtributes> getAtributesList();
	public void addEdgeAAtributes(IAtributes atribute);
	public IGraphicsAtribute getGraphicsAtribute();
	public void increaseWeight();
	public boolean isDirected();

}
