package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public interface ICorpusPrivilegesAccess {
	
	public List<ICorpus> getPrivilegesAllCorpusAdminAccess() throws ANoteException;

}
