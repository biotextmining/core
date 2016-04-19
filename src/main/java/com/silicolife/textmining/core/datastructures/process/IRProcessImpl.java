package com.silicolife.textmining.core.datastructures.process;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.process.IR.IIRProcess;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public abstract class IRProcessImpl implements IIRProcess{

	public IRProcessImpl() {
		super();
	}

	protected Map<String, Long> getAllPublicationExternalIdFromSource(String source )throws ANoteException {
		return  InitConfiguration.getDataAccess().getAllPublicationsExternalIDFromSource(source);
	}
	

	protected Set<String> getQueryPublicationIDWithGivenSource(IQuery query,String str) throws ANoteException {
		return InitConfiguration.getDataAccess().getQueryPublicationsExternalIDFromSource(query,str );
	}
	

	protected  void insertPublications(List<IPublication> documentsToInsert)throws ANoteException {
		InitConfiguration.getDataAccess().addPublications(documentsToInsert);
	}
	
	protected void insertQueryPublications(IQuery query,List<IPublication> publicationToAdd) throws ANoteException {
		InitConfiguration.getDataAccess().addQueryPublications(query, publicationToAdd);
	}
	

	protected void updateQueryOnDatabase(IQuery query) throws ANoteException {
		InitConfiguration.getDataAccess().updateQuery(query);
	}
}
