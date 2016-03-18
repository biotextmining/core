package com.silicolife.textmining.core.interfaces.resource.dictionary;

import java.sql.SQLException;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;

public interface IBiowareHouseLoader extends IDictionaryLoader {

	public IDatabase getBiowareHouseDB();

	public IResourceUpdateReport loadTermsFromBiowareHouse(Set<String> classForLoading, boolean synonyms) throws ANoteException, SQLException;
}
