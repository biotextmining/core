package com.silicolife.textmining.core.datastructures.resources;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;

public class ResourceImpl extends Observable implements IResource<IResourceElement>{
	
	/**
	 * Logger
	 */
//	static Logger logger = Logger.getLogger(ResourceImpl.class.getName());
	
	private String name;
	private String info;
	private Long id;
	private boolean active;
	private String type;
	

	/**
	 * Constructor that start a prepared statement to insert a resource element
	 * 
	 * @param db
	 * @param id
	 * @param name
	 * @param info
	 */
	public ResourceImpl(long id,String name,String info,String type,boolean active)
	{
		this.id=id;
		if(name==null){this.name="";}
		else {this.name=name;}
		if(info==null) this.info="";
		else this.info=info;
		this.active = true;
		this.type=type;
	}
	
	public ResourceImpl(String name,String info,String type,boolean active)
	{
		this(GenerateRandomId.generateID(), name, info,type, active);
	}	


	public ResourceImpl() {
		super();
	}
	

	@Override
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@JsonIgnore
	public IResourceElementSet<IResourceElement> getResourceElementsByName(String name) throws ANoteException
	{
		return InitConfiguration.getDataAccess().getResourceElementsByName(this, name);
	}
	
	@JsonIgnore
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IAnoteClass klass) throws ANoteException {
		return InitConfiguration.getDataAccess().getResourceElementsByClass(this, klass);
	}
	
	/**
	 * Method that return a List of resource elements of a resource
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException 
	 */
	@JsonIgnore
	public IResourceElementSet<IResourceElement> getResourceElements() throws ANoteException {
		return InitConfiguration.getDataAccess().getResourceElements(this);
	}
	
	@JsonIgnore
	public Set<IAnoteClass> getResourceClassContent() throws ANoteException
	{		
		return InitConfiguration.getDataAccess().getResourceClassContent(this);
	}
	
	@JsonIgnore
	public IResourceElement getResourceElementByID(long termID) throws ANoteException
	{
		return InitConfiguration.getDataAccess().getResourceElementByID(termID);
	}
	
	@JsonIgnore
	@Override
	public synchronized IResourceContent getResourceContent() throws ANoteException{
		return InitConfiguration.getDataAccess().getResourceContent(this);
	}
	
	/**
	 * Method that add a Resource Element
	 * 
	 * @param elem - Resource Element
	 * @return true - if element had been correct inserted 
	 * false - otherwise
	 * @throws SQLException 
	 * @throws DatabaseLoadDriverException 
	 */
	public IResourceManagerReport addResourceElements(List<IResourceElement> elements) throws ANoteException
	{
		return InitConfiguration.getDataAccess().addResourceElements(this, elements);
	}
	
	/**
	 * Update Element
	 * 
	 * @param elem
	 * @return
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException 
	 */
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {
		return InitConfiguration.getDataAccess().updateResourceElement(elem);
	}
	
	public void inactivateElement(IResourceElement elem) throws ANoteException
	{
		InitConfiguration.getDataAccess().inactivateResourceElement(elem);
	}

	public void inactiveResourceElementElementsByClassID(long classID) throws ANoteException {
		InitConfiguration.getDataAccess().removeResourceClass(this, classID);
	}
	
	/**
	 * Method that remove a Resource Element 
	 * 
	 * @param elem
	 * @return
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException 
	 */
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {
		InitConfiguration.getDataAccess().inactivateResourceElement(elem);
	}
	
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException{
		InitConfiguration.getDataAccess().removeResourceElementSynonyms(elem);
	}
	
	public void removeResourceElementSynonym(IResourceElement elem,String synonym) throws ANoteException
	{
		InitConfiguration.getDataAccess().removeResourceElementSynonym(elem, synonym);
	}
	
	protected void memoryAndProgress(int step, int total) {
		System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
		Runtime.getRuntime().gc();
		System.out.println((Runtime.getRuntime().totalMemory()- Runtime.getRuntime().freeMemory())/(1024*1024) + " MB ");
	}
	
	public void notifyViewObservers(){
		setChanged();
		notifyObservers();
	}

	public static String convertClassesToResourceProperties(Set<Long> classes) {
		String classesStr = new String();
		for(Long classID:classes)
		{
			classesStr = classesStr.concat(classID+",");
		}
		if(classesStr.length() > 0)
			classesStr = classesStr.substring(0,classesStr.length()-1);
		return classesStr;
	}

	@Override
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void inactiveElementsByClassID(long classID) throws ANoteException {
		InitConfiguration.getDataAccess().removeResourceClass(this, classID);		
	}
	
	public void updateElement(IResourceElement element) throws ANoteException
	{
		InitConfiguration.getDataAccess().updateResourceElement(element);
	}
	
	public int compareTo(IResource<IResourceElement> resource)
	{
		if(this.getId()==resource.getId())
		{
			return 0;
		}
		else if(this.getId()<=resource.getId())
		{
			return -1;
		}
		return 1;
	}
	
	public boolean checkiftermalreadyexist(String term) throws ANoteException {
		return InitConfiguration.getDataAccess().checkResourceElementExistsInResource(this, term);
	}

	public IResourceManagerReport addExternalID(IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {
		return InitConfiguration.getDataAccess().addResourceElementExternalIds(this,elem, externalIDs);
	}
	
	public String toString()
	{
		String info = new String();
		info = getType() + " : " + getName() + " (ID :"+ getId() + " ) ";
		if(!getInfo().equals(""))
		{
			info = info + LanguageProperties.getLanguageStream("pt.uminho.anote2.general.notes")+": "+getInfo();
		}
		if(!isActive())
		{
			info = info + " ("+LanguageProperties.getLanguageStream("pt.uminho.anote2.general.inactive")+") ";
		}
		return info;
	}

	@JsonIgnore
	public boolean isFill() throws ANoteException {
		return InitConfiguration.getDataAccess().getResourceContent(this).getTermNumber()!=0;
	}
}
