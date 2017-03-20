package com.silicolife.textmining.core.datastructures.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.dataaccess.DataAccessManager;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.init.dataaccess.DataAccessDefaultSettings;
import com.silicolife.textmining.core.datastructures.init.propertiesmanager.PropertiesManager;
import com.silicolife.textmining.core.datastructures.utils.GenericPairC;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class InitConfiguration {
	
	private static Proxy proxy;
	private static IDataAccess dataAccess;
	private static Properties properties = new Properties();
	
	private static InitConfiguration initConfiguration = null;
	

	private InitConfiguration() throws ANoteException
	{
		DataAccessManager dataAccessManager = (DataAccessManager) PropertiesManager.getPManager().getProperty(DataAccessDefaultSettings.DATAACCESS_SELECTED);
		dataAccess = dataAccessManager.getCurrent();
	}
	
	public static void login(UsersLogged userlogged) throws ANoteException
	{
		DataAccessManager dataAccessManager = (DataAccessManager) PropertiesManager.getPManager().getProperty(DataAccessDefaultSettings.DATAACCESS_SELECTED);
		dataAccessManager.setUsersLogged(userlogged);
	}
	
	public static void login() throws ANoteException
	{
		UsersLogged user = (UsersLogged) PropertiesManager.getPManager().getProperty(DataAccessDefaultSettings.USER_CREDENTIALS);
		DataAccessManager dataAccessManager = (DataAccessManager) PropertiesManager.getPManager().getProperty(DataAccessDefaultSettings.DATAACCESS_SELECTED);
		dataAccessManager.setUsersLogged(user);
	}
	
	private InitConfiguration(IDataAccess dataAccess, Proxy proxy,Properties properties)  throws ANoteException
	{
		InitConfiguration.dataAccess=dataAccess;
		InitConfiguration.proxy=proxy;
		InitConfiguration.properties=properties;
	}

	/**
	 * Init Text Mining using Property manager files in conf/settings/global.conf
	 * 
	 * @return
	 * @throws ANoteException
	 */
	synchronized public static InitConfiguration init() throws ANoteException{
		if(initConfiguration == null)
			initConfiguration = new InitConfiguration();	
		return initConfiguration;
	}
	
	/**
	 * Init Text Mining using defined Idataaccess and IProxy and a Properties List
	 * 
	 * @return
	 * @throws ANoteException
	 */
	synchronized public static InitConfiguration init(IDataAccess dataAccess,Proxy proxy,Properties properties) throws ANoteException{
		if(initConfiguration == null)
			initConfiguration = new InitConfiguration(dataAccess,proxy,properties);	
		return initConfiguration;
	}
	
	/**
	 * Init Text Mining using defined Idataaccess and IProxy and a Properties List
	 * 
	 * @return
	 * @throws ANoteException
	 */
	synchronized public static InitConfiguration initForce(IDataAccess dataAccess,Proxy proxy,Properties properties) throws ANoteException{
		initConfiguration = new InitConfiguration(dataAccess,proxy,properties);	
		return initConfiguration;
	}

	/** Method that read a Settings File the keys and return List of conf */
	public static List<String> getElementByXMLFile(String settingsFilePath, ArrayList<String> keys) {

		List<String> settings = new ArrayList<String>();
		File file = new File(settingsFilePath);
		if (file.length() <= 0)
			return null;

		Properties p = new Properties();
		try {
			p.loadFromXML(new FileInputStream(file));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String key : keys) {
			settings.add(p.getProperty(key));
		}
		return settings;
	}

	public static void enablePluginManagerStartupGUI() throws IOException {
		InputStream fis = new FileInputStream(GlobalOptions.pluginManagerFile);
		Properties properties = new Properties();
		properties.load(fis);
		fis.close();
		properties.setProperty("plugins.start.gui", "true");
		FileOutputStream os = new FileOutputStream(GlobalOptions.pluginManagerFile);
		properties.store(os, null);
		os.flush();
		os.close();
	}

	/** Method that change xml properties */
	public static boolean setXmlProperties(String settingsFilePath, List<GenericPairC<String, String>> changes) {
		File file = new File(settingsFilePath);
		if (file.length() <= 0)
			return false;

		Properties p = new Properties();
		try {
			p.loadFromXML(new FileInputStream(file));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (GenericPairC<String, String> change : changes) {
			FileOutputStream out;
			try {
				out = new FileOutputStream(settingsFilePath);

				p.setProperty(change.getX(), change.getY());
				p.storeToXML(out, "");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public Object getProperty(String key)
	{
		if(properties==null || properties.get(key)==null)
		{
			return PropertiesManager.getPManager().getProperty(key);
		}
		else
		{	
			return properties.get(key);
		}
	}

	public synchronized static IDataAccess getDataAccess() {
		return dataAccess;
	}
	
	public static Proxy getProxy()
	{
		return InitConfiguration.proxy;
	}

	public static void setProxy(Proxy proxy) {
		InitConfiguration.proxy = proxy;
		
	}

	public static void addProperty(String key, String value) {
		properties.put(key, value);
	}
	
	public static String getPropertyValue(String key)
	{
		if(properties.isEmpty())
		{
			return null;
		}
		return properties.getProperty(key);
	}
	
	public static String getPropertyValueFromInitOrProperties(String key)
	{
		if(properties.isEmpty())
		{
			return getPropertyFromPropertiesSystem(key);
		}
		String result = properties.getProperty(key);
		if(result==null)
		{
			return getPropertyValue(key);
		}
		return result;
	}
	
	private static String getPropertyFromPropertiesSystem(String key)
	{
		if(PropertiesManager.getPManager()!=null)
		{
			return (String) PropertiesManager.getPManager().getProperty(key);
		}
		return null;
	}
}