package com.silicolife.textmining.core.interfaces.process.IE.network;

import java.util.List;


public interface IGraphicsAtribute {
	public int getWith();
	public void increseWith();
	public String getFillColor();
	public String getOutlineColor();
	public XGMMLPolygnos getXGMMLPolygnos();
	public ICoordenates getCordenates();
	public List<IAtributes> getAtributes();
	public boolean useWeightsAttrinute();
}
