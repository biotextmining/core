package com.silicolife.textmining.core.datastructures.dataaccess.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;

/**
 * Class that contains some static method to help databse Update
 * 
 * @author Hugo Costa
 *
 */
public class UpdateDatabaseHelp {
	
	/**
	 * Read Database version file and return last version
	 * 
	 * @return
	 */
	public static int readDatabaseFileDataBase()
	{
		ArrayList<String> elements = new ArrayList<String>();
		elements.add("Database-Version");
		ArrayList<String> data = getElementByXMLFile(GlobalOptions.mysqlDatabaseVersionFile,elements);
		if(data.get(0)==null || data.get(0).equals("null") || data.get(0).equals("false"))
		{
			return -1;
		}
		else
		{
			return Integer.valueOf(data.get(0));
		}
	}
	
	
	/** Method that read a Settings File the keys and return List of conf*/
	public static ArrayList<String> getElementByXMLFile(String settingsFilePath,ArrayList<String> keys){
		
		ArrayList<String> settings = new ArrayList<String>();
		File file = new File(settingsFilePath);
		if(file.length()<=0)
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
		for(String key:keys)
		{
			settings.add(p.getProperty(key));
		}
		return settings;
	}

}
