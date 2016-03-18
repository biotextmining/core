package com.silicolife.textmining.core.interfaces.process.IE.re;

import com.silicolife.textmining.core.interfaces.process.IE.IIECSVColumns;

public interface IRECSVColumns extends IIECSVColumns{
	public int getLeftEntitiesColumn();
	public int getRightEntitiesColumn();
	public int getSentenceColumn();
	public int getLeftEntitiesExternalIDs();
	public int getRightEntitiesExternalIDs();
}
