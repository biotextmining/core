package com.silicolife.textmining.core.interfaces.resource;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.ontologies.ITermBDID;

/**
 * Interface that implements a Resource element
 * 
 * @author Hugo Costa
 *
 */
public interface IResourceElement extends Cloneable,ITermBDID, Comparable<IResourceElement>{
	
	public String getTerm(); // biological name
	public void setTerm(String newTermName);
	public IAnoteClass getTermClass();

	public boolean isActive();
	/**
	 * Dictionary and Ontologies
	 * @throws ANoteException 
	 * 
	 */
	public List<IExternalID> getExtenalIDs() throws ANoteException;
	public List<IExternalID> getExternalIDsInMemory();

	public List<String> getSynonyms();

	/**
	 * Rules
	 * 
	 */
	public int getPriority();
	public void setPriority(int priority);
	public boolean hasExternalID(IExternalID ext);
	
	public void setTermClass(IAnoteClass klass);
	
	public void generateNewId();

}
