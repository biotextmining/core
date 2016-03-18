package com.silicolife.textmining.core.interfaces.resource;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;

/**
 * This interface define a generic resource for Biomedical text mining
 * 
 * @author Hugo Costa
 *
 */
public interface IResource<T extends IResourceElement>{

	public String getName();
	public String getInfo();
	public String getType();
	public long getId();
	public boolean isActive();
	public IResourceElementSet<IResourceElement> getResourceElementsByName(String name) throws ANoteException;
	public IResourceManagerReport addResourceElements(List<IResourceElement> elemements) throws ANoteException;
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException;
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException;
	public void inactiveResourceElementElementsByClassID(long classID) throws ANoteException;
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IAnoteClass termClass) throws ANoteException;
	public IResourceElementSet<IResourceElement> getResourceElements() throws ANoteException;
	public Set<IAnoteClass> getResourceClassContent() throws ANoteException;
	public IResourceElement getResourceElementByID(long termID) throws ANoteException;
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException;
	public void removeResourceElementSynonym(IResourceElement elem,String synonym) throws ANoteException;
	public IResourceContent getResourceContent() throws ANoteException;
	public boolean isFill() throws ANoteException;

}
