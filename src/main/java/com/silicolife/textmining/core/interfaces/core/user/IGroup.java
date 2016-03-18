package com.silicolife.textmining.core.interfaces.core.user;

import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;

/**
 * 
 * @author Utilizador
 *
 */
public interface IGroup {
	/**
	 * 
	 * @return
	 */
	public Long getAgId();

	/**
	 * 
	 * @return
	 */
	public String getAgDescription();
	
	/**
	 * 
	 * @return
	 */
	public Set<AuthGroupHasRoles> getAuthGroupHasRoleses();
}
