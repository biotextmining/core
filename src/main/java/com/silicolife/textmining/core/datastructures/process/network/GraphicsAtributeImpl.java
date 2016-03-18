package com.silicolife.textmining.core.datastructures.process.network;

import java.util.List;

import com.silicolife.textmining.core.interfaces.process.IE.network.IAtributes;
import com.silicolife.textmining.core.interfaces.process.IE.network.ICoordenates;
import com.silicolife.textmining.core.interfaces.process.IE.network.IGraphicsAtribute;
import com.silicolife.textmining.core.interfaces.process.IE.network.XGMMLPolygnos;

public class GraphicsAtributeImpl implements IGraphicsAtribute{
	
	private int with;
	private String fillColor;
	private String outlineColor;
	private ICoordenates cordenates;
	private List<IAtributes> atributes;
	private XGMMLPolygnos polygnos;
	private boolean useWeightsAttrinute;
		
	public GraphicsAtributeImpl(int with, String fillColor, String outlineColor,XGMMLPolygnos polygnos, boolean useWeightsAttrinute,
			ICoordenates cordenates, List<IAtributes> atributes) {
		super();
		this.with = with;
		this.fillColor = fillColor;
		this.outlineColor = outlineColor;
		this.polygnos = polygnos;
		this.cordenates = cordenates;
		this.atributes = atributes;
		this.useWeightsAttrinute = useWeightsAttrinute;
	}
	
	
	public int getWith() {
		return with;
	}
	public String getFillColor() {
		return fillColor;
	}
	public String getOutlineColor() {
		return outlineColor;
	}
	public ICoordenates getCordenates() {
		return cordenates;
	}
	public List<IAtributes> getAtributes() {
		return atributes;
	}

	public void increseWith() {
		this.with++;
	}

	@Override
	public XGMMLPolygnos getXGMMLPolygnos() {
		return polygnos;
	}

	public boolean useWeightsAttrinute() {
		return useWeightsAttrinute;
	}
	
	

}
