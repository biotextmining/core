package com.silicolife.textmining.core.interfaces.process.IE.network;

import java.util.List;

public interface INode {
	public int getID();
	public String getLabel();
	public String getName();
	public List<IAtributes> getAtributesList();
	public IGraphicsAtribute getGraphicsAtribute();
	public void increaseWeight();
	public boolean isNodeAlone();
	public void setNodeAlone(boolean newStatus);
}
