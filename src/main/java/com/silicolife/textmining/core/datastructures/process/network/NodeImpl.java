package com.silicolife.textmining.core.datastructures.process.network;

import java.util.List;

import com.silicolife.textmining.core.interfaces.process.IE.network.IAtributes;
import com.silicolife.textmining.core.interfaces.process.IE.network.IGraphicsAtribute;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;

public class NodeImpl implements INode{
	private int id;
	private String label;
	private String name;
	private List<IAtributes> atributesList;
	private IGraphicsAtribute graphicsAtribute;
	private boolean nodeAlone;
	

	public NodeImpl(int id, String label, String name,
			List<IAtributes> atributesList, IGraphicsAtribute graphicsAtribute) {
		super();
		this.id = id;
		this.label = label;
		this.name = name;
		this.atributesList = atributesList;
		this.graphicsAtribute = graphicsAtribute;
		this.nodeAlone = true;
	}
	
	
	public int getID() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public String getName() {
		return name;
	}
	public List<IAtributes> getAtributesList() {
		return atributesList;
	}
	public IGraphicsAtribute getGraphicsAtribute() {
		return graphicsAtribute;
	}


	public void increaseWeight() {
		graphicsAtribute.increseWith();
	}

	public boolean isNodeAlone() {
		return nodeAlone;
	}

	public void setNodeAlone(boolean newStatus) {
		this.nodeAlone = newStatus;
	}
	
	
}
