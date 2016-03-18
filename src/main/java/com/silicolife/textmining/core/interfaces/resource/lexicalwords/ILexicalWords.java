package com.silicolife.textmining.core.interfaces.resource.lexicalwords;

import java.io.IOException;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface ILexicalWords extends IResource<IResourceElement> {

	public boolean exportCSVFile(String csvFile) throws IOException, ANoteException;

	public Map<String, IResourceElement> getLexicalWords() throws ANoteException;

}
