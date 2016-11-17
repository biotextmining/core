package com.silicolife.textmining.core.interfaces.core.dataaccess;

import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.IGeneralDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.IUpdateAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora.ICorpusAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.hyperlink.IHyperLinkMenuLayer;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.publicationmanager.IPublicationManagerAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.runserverprocesses.IRunServerProcesses;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.user.IUserPrivilegesAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.ILuceneDataAcess;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public interface IDataAccess extends IPublicationManagerAccess, IGeneralDataAccess, ICorpusAccess, IResourceAccess, IUpdateAccess, IUserPrivilegesAccess, IHyperLinkMenuLayer,IRunServerProcesses, ILuceneDataAcess {

	/**
	 * Method that give the user
	 * 
	 * @return
	 */
	public IUser getUser();

	public void setUser(UsersLogged userLogged) throws ANoteException;

	public void login(String username, String password) throws ANoteException;
	
	public Boolean checkLogin(String username, String password) throws ANoteException;

	public void logout() throws ANoteException;

	public void saveProperties(Properties properties) throws ANoteException;

	public Properties loadProperties(Set<String> propertiesIdentifiers) throws ANoteException;





}
