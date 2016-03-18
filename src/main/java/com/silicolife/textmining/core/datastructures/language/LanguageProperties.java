package com.silicolife.textmining.core.datastructures.language;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;


public class LanguageProperties {
	

	
	private static Properties properties = null;
	
	public static synchronized String getLanguageStream(String uid) 
	{
		if(properties==null || properties.isEmpty())
		{
			properties = new Properties();
			FileInputStream in;
			try {
				File alternativeFile = new File(GlobalOptions.alternativelanguagePathFile);
				if(alternativeFile.exists())
				{
					in = new FileInputStream(alternativeFile);
					properties.load(in);
				}
				else
				{
					createFileIFNotExist();
					in = new FileInputStream(GlobalOptions.languagePathFile);
					properties.load(in);
				}
			} catch (FileNotFoundException e) {
				return "?";
			} catch (IOException e) {
				return "?";			}
		}
		if(properties.containsKey(uid))
		{
			return properties.getProperty(uid);
		}
		else
		{
			return "?";
		}
	}
	
	public static synchronized void registryLanguageStream(Properties prop) throws IOException
	{
		if(properties==null || properties.isEmpty())
		{
			createFileIFNotExist();
			properties = new Properties();
			FileInputStream in = new FileInputStream(GlobalOptions.languagePathFile);
			properties.load(in);
		}
		for(String porpName:prop.stringPropertyNames())
		{
			if(!properties.containsKey(porpName))
			{
				properties.put(porpName, prop.get(porpName));
			}
		}
		properties.store(new FileOutputStream(GlobalOptions.languagePathFile),null);
	}
	
	private static synchronized void createFileIFNotExist(){
		File directoryLanguage = new File(GlobalOptions.languageFilesDirectory);
		if(!directoryLanguage.exists())
			directoryLanguage.mkdir();
		File fileLanguage = new File(GlobalOptions.languagePathFile);
		if(!fileLanguage.exists())
		{
			try {
				fileLanguage.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
