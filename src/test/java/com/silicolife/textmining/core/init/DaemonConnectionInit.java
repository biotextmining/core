package com.silicolife.textmining.core.init;

import static org.junit.Assert.assertTrue;

import java.net.Proxy;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.DaemonAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.DaemonFactory;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDemonConnectionException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;


public class DaemonConnectionInit{
	
	public static void init(String protocol,String port,String url,String rootPath) throws InvalidDemonConnectionException, ANoteException
	{
		IDaemon daemonConfigurations = checkDeamonConnection(protocol, port,url,rootPath);
		Properties properties = new Properties();
		properties.put("Using-Title-In-Abstract", "true");
		properties.put("Free-Full-Text-Only", "true");
		Proxy proxy = null;
		DaemonAccess dataAccess = new DaemonAccess(daemonConfigurations);
		InitConfiguration.init(dataAccess,proxy,properties);
		dataAccess.login("admin", "admin");
		InitConfiguration.getDataAccess().checkLogin("admin", "admin");
		assertTrue(true);
	}
	
	
	public static IDaemon checkDeamonConnection(String protocol,String port,String url,String rootPath) throws InvalidDemonConnectionException
	{
		if (protocol.trim().equals("") || port.trim().equals("") || url.trim().equals("") || rootPath.trim().equals(""))
		{
			if(protocol.isEmpty())
			{
				throw new InvalidDemonConnectionException("Protocol can not be empty (HTTP or HTTPS by default)");
			}
			if(port.isEmpty())
			{
				throw new InvalidDemonConnectionException("Port can not be empty (8080 or 8443 by default)");
			}
			if(url.isEmpty())
			{
				throw new InvalidDemonConnectionException("Url can not be empty");
			}
			if(rootPath.isEmpty())
			{
				throw new InvalidDemonConnectionException("URoot path can not be empty (anote2daemon by default)");
			}
			return null;
		} 
		else{
			IDaemon daemonAdded = DaemonFactory.createDaemonConfigurations(protocol, url, Integer.parseInt(port), rootPath);
			if(DaemonAccess.testConnection(daemonAdded))
			{
				return daemonAdded;
			}
			else
				throw new InvalidDemonConnectionException("Daemon credential are wrong or Daemon is not reachable");
		}
	}

}
