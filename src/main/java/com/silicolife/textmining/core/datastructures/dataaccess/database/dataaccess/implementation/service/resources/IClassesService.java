package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClassException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

/**
 * Interface to define all methods of Service Layer classes
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface IClassesService {

	/**
	 * Create new class
	 * 
	 * @param classes
	 * @return
	 * @throws ClassException 
	 */
	public Boolean addClass(IAnoteClass klass) throws ClassException;

	/**
	 * get map of all classes
	 * 
	 * @return
	 */
	public Set<IAnoteClass> getClasses();

	/**
	 * get classe by id
	 * 
	 * @param id
	 * @return
	 */
	public IAnoteClass getClassById(Long id);

	/**
	 * Update Klass ( color or name)
	 * 
	 * @param klass
	 * @throws ClassException 
	 */
	public Boolean updateClass(IAnoteClass klass) throws ClassException;

	public void setUserLogged(UsersLogged userLogged);
}
