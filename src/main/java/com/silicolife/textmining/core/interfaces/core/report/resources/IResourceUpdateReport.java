package com.silicolife.textmining.core.interfaces.core.report.resources;

import java.io.File;



public interface IResourceUpdateReport extends IResourceReport{
	public String getFile();
	public void updateFile(File file);
	public String getDataFont();
	public IResourceUpdateReport clone();
}
