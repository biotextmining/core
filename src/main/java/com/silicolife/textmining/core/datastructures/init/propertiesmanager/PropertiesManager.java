package com.silicolife.textmining.core.datastructures.init.propertiesmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.settings.IPropertyNode;
import com.silicolife.textmining.core.interfaces.core.settings.exeption.PropertiesManagerException;

/**
 * This class manages the properties of OptFlux.
 * It as a list of property nodes (or categories). It is possible to 
 * register properties in the manager, allowing the addition of
 * new properties at any time.
 * 
 * @author Noronha
 *
 */
public class PropertiesManager {

	static PropertiesManager propManager = null;
	
	
	synchronized public static PropertiesManager getPManager(){
		if(propManager == null)
			propManager = new PropertiesManager();
		
		return propManager;
	}
	
	private HashMap<IPropertyNode, String> nodeFile;
	
	private PropertiesManager(){

		listOfPropertyNodes = new HashMap<String, IPropertyNode>();
		propFileToNodes = new HashMap<String, Set<String>>();
		nodeFile = new HashMap<IPropertyNode, String>();

	}
    /**
     * Maps the property nodes by their unique
     * tree node string.
     */
	private Map<String, IPropertyNode> listOfPropertyNodes;
	/**
	 * Maps the files to the given properties
	 */
	private HashMap<String, Set<String>> propFileToNodes;
	
	/**
	 * Static method to register properties node
	 * @param prop
	 * @param file
	 * @throws PropertiesManagerException 
	 * @throws ANoteException 
	 */
	public void registerNewProp(IPropertyNode prop, String file) throws PropertiesManagerException, ANoteException {
		registerProperty(prop, file);

	}
	
//	/**
//	 * Static method to load a file of properties
//	 * @param file
//	 * @return properties object
//	 */
//	public Properties load(String file){
//		return readPropertyFile(file);
//	}
	
	/**
	 * Static method to save all properties to their correspondent
	 * property files. While loading is made by node, saving is made
	 * by file.
	 * @throws PropertiesManagerException 
	 */
	public void saveNodes() throws PropertiesManagerException{
		for(String nodeTree : this.listOfPropertyNodes.keySet())
		{
			IPropertyNode node = this.listOfPropertyNodes.get(nodeTree);
			if(node.saveOnDataAccess())
			{
				Set<String> identifiers = node.getPropertiesIdentifiers();
				Properties properties = new Properties();
				for(String propertyID:identifiers)
				{
					String covert = node.convert(propertyID, node.getProperty(propertyID));
					properties.put(propertyID, covert);
				}
				try {
					InitConfiguration.getDataAccess().saveProperties(properties);
				} catch (ANoteException e) {
					throw new PropertiesManagerException(e.getMessage());
				}
			}
			else
			{
				String file = this.nodeFile.get(node);
				savePropertyFile(file);
			}
		}

	}
	
	/**
	 * Static method to save all properties to their correspondent
	 * property files. While loading is made by node, saving is made
	 * by file.
	 * @throws PropertiesManagerException 
	 */
	public void saveNodesWhenChangeDataAccess() throws PropertiesManagerException{
		for(String nodeTree : this.listOfPropertyNodes.keySet())
		{
			IPropertyNode node = this.listOfPropertyNodes.get(nodeTree);
			if(!node.saveOnDataAccess())
			{
				String file = this.nodeFile.get(node);
				savePropertyFile(file);
			}
		}

	}

	/**
	 * Method that gets the value of a property by his path.
	 * The path of the property is composed of the TreePath + the identifier
	 * of the property, separated by a '.' 
	 * Ex: Propeties.basic.font -> Properties.basic (treepath)  font (property identifier) 
	 * @param porpertypath
	 * @return value of the property
	 */
	public Object getProperty(String porpertypath){
		return getPManager().getPropertyValue(porpertypath);
	}
	
	
	/**
	 * Method to register property nodes.
	 * Checks if there already exists a tree node for that 
	 * property node, and if there isn't nodes manipulating 
	 * the same properties the one is being added.
	 * 
	 * @param prop - property node object 
	 * @param file - file where list of properties from node will be written
	 * @throws PropertiesManagerException 
	 * @throws ANoteException 
	 */
	private void registerProperty(IPropertyNode prop, String file) throws PropertiesManagerException, ANoteException{
			
		validateNode(prop);
		
		loadProperties(file, prop);
		
		listOfPropertyNodes.put(prop.getTreePath(), prop);
		
		Set<String> nodes = propFileToNodes.get(file);
		
		if(nodes == null) nodes = new HashSet<String>();
		nodes.add(prop.getTreePath());
		
		propFileToNodes.put(file,nodes);
		nodeFile.put(prop, file);
	}
	
	private Properties loadProperties(String file, IPropertyNode node)  throws PropertiesManagerException, ANoteException{
		
		if(node.saveOnDataAccess())
		{
			Properties propdata = new Properties();
			node.loadProperties(InitConfiguration.getDataAccess().loadProperties(node.getPropertiesIdentifiers()));
			return propdata;
		}
		else
		{
			return loadAndValidatePropertiesFromFile(file, node);
		}
	}

	private Properties loadAndValidatePropertiesFromFile(String file,
			IPropertyNode node) throws PropertiesManagerException {
		Properties propdata = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			propdata.load(in);
		} catch (FileNotFoundException e) {
			 File f = new File(file);
			 f.getParentFile().mkdirs();
			 try {
				f.createNewFile();
			} catch (IOException e1) {
				throw new PropertiesManagerException("Failed to create property file.");
			}
		} catch (IOException e) {
			throw new PropertiesManagerException("Failed to create property file.");
		}
		node.loadProperties(propdata);
		return propdata;
	}

	private void validateNode(IPropertyNode node) throws PropertiesManagerException{
		
		if(this.listOfPropertyNodes.keySet().contains(node.getTreePath()))
			throw new PropertiesManagerException("Invalid property node: the Manager already contains that node: " + node.getTreePath());
		
		Set<String> propertyList = node.getPropertiesIdentifiers();
		for(String s : propertyList){
			if(!(s.matches("^" + node.getTreePath() + "\\.\\w+$")))
			{
				throw new PropertiesManagerException("Invalid property node: the property " + s + " is not a valid property name.");
			}
		}
		
		if(node.getPropertiesPanel() == null)
			throw new PropertiesManagerException("Invalid property node: the property node doesn't have a property panel");
	}
	

	/**
	 * 
	 * @param prop tree identifier
	 * @return property node
	 */
	private IPropertyNode getPropertyNode(String prop){
		return listOfPropertyNodes.get(prop);
	}
	
	/**
	 * 
	 * @param proppath
	 * @return
	 */
	private Object getPropertyValue(String proppath){

		String[] path = proppath.split("\\.");
		String node =path[0];
		for(int i = 1; i<path.length-1;i++){
			node += "." + path[i];
		}
		
		IPropertyNode pnode = this.getPropertyNode(node);
		if(pnode != null)
			return pnode.getProperty(proppath);
		else
			return null;
	}
	/**
	 * This function saves the property file.
	 * First it gets all the property nodes associated with a file
	 * and creates a Properties object, adding all the properties
	 * from each node to it. Then it writes the file.
	 * @param  file property file 
	 */
	private void savePropertyFile(String file){
		
		Properties p = new Properties();
		Set<String> propsFromFile = this.propFileToNodes.get(file);
				
		for(String node : propsFromFile){
			listOfPropertyNodes.get(node).populateProperties(p);
		}

		/**
		 * Write property in file.
		 */
		try {
			p.store(new FileOutputStream(file),null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Loads the properties from a file.
	 * @param file
	 * @return properties from file
//	 */
	private Properties readPropertyFile(String file){
		
		Properties prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	public Map<String, IPropertyNode> getNodes(){
		return this.listOfPropertyNodes;
	}

	public void importProperties(String file) {
		// TODO Auto-generated method stub
		Properties p = readPropertyFile(file);
		for(String s : listOfPropertyNodes.keySet()){
			listOfPropertyNodes.get(s).loadProperties(p);
		}
	}

	public void exportProperties(String file) {
		
		Properties p = new Properties();
		
		for(String s : listOfPropertyNodes.keySet())
			listOfPropertyNodes.get(s).populatePropertiesRestricted(p);
		try {
			p.store(new FileOutputStream(file),null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
