package com.silicolife.textmining.core.interfaces.core.configuration;

import java.io.File;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public interface ISaveModule {
	public boolean saveFile(File file) throws ANoteException;
	public void loadFile(File file) throws Exception;
	public String getName();
}
