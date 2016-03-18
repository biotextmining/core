package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClassException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.ClassesWrapper;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

/**
 * Service layer which implements all operations about classes
 * 
 * 
 * @author Joel Azevedo costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class ClassesServiceImpl implements ClassesService {

	private ResourcesManagerDao resourcesManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public ClassesServiceImpl(ResourcesManagerDao resourcesManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.resourcesManagerDao = resourcesManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addClass(IAnoteClass anoteClass) throws ClassException {
		Classes klass = resourcesManagerDao.getClassesDao().findById(anoteClass.getId());
		if(klass!=null)
			throw new ClassException(ExceptionsCodes.codeClassExists, ExceptionsCodes.msgClassExists);
		klass = resourcesManagerDao.getClassesDao().findUniqueByAttribute("claName",anoteClass.getName());
		if(klass!=null)
			throw new ClassException(ExceptionsCodes.codeClassNameExists, ExceptionsCodes.msgClassNameExists);
		Classes newClass = ClassesWrapper.convertToDaemonStructure(anoteClass);
		if(newClass.getClaColor()==null || newClass.getClaColor().isEmpty())
			newClass.setClaColor(AnoteClass.getRandomColor());
		resourcesManagerDao.getClassesDao().save(newClass);
		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "classes", null, "create new classes");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return true;
	}

	@Override
	public Set<IAnoteClass> getClasses() {
		Set<IAnoteClass> results = new java.util.HashSet<>();

		List<Classes> classes = resourcesManagerDao.getClassesDao().findAll();
		for (Classes class_ : classes) {
			IAnoteClass anoteKlass = ClassesWrapper.convertToAnoteStructure(class_);
			results.add(anoteKlass);
		}

		return results;
	}

	@Override
	public IAnoteClass getClassById(Long id) {
		Classes classes = resourcesManagerDao.getClassesDao().findById(id);
		IAnoteClass klass = ClassesWrapper.convertToAnoteStructure(classes);
		return klass;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateClass(IAnoteClass klassAnote) throws ClassException {
		Classes klass = resourcesManagerDao.getClassesDao().findById(klassAnote.getId());
		if(klass==null)
			throw new ClassException(ExceptionsCodes.codeNoClass, ExceptionsCodes.msgNoClass);
		klass.setClaColor(klassAnote.getColor());
		resourcesManagerDao.getClassesDao().update(klass);
		
		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "classes", null, "color/name");
		usersManagerDao.getAuthUserLogsDao().save(log);
		
		return true;
		
	}

}
