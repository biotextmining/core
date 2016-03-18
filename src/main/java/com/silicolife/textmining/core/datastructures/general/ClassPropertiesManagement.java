package com.silicolife.textmining.core.datastructures.general;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class ClassPropertiesManagement {
	
	private static Map<Long,IAnoteClass> classIDAnoteClass;
	private static Map<String,IAnoteClass> classNameAnoteClass;
	
	static{
		try {
			refreshClasses();
		} catch (ANoteException e){
			e.printStackTrace();
		}
	}

	private static void refreshClasses() throws ANoteException {
		Set<IAnoteClass> classes = InitConfiguration.getDataAccess().getClasses();
		classIDAnoteClass = new HashMap<Long, IAnoteClass>();
		classNameAnoteClass = new HashMap<>();
		for(IAnoteClass anoteKlass:classes)
		{
			classIDAnoteClass.put(anoteKlass.getId(), anoteKlass);
			classNameAnoteClass.put(anoteKlass.getName().toLowerCase(), anoteKlass);
		}
	}
	
	public static IAnoteClass getClassIDOrinsertIfNotExist(IAnoteClass klass) throws ANoteException
	{
		if(classNameAnoteClass.containsKey(klass.getName().toLowerCase()))
		{
			return classNameAnoteClass.get(klass.getName().toLowerCase());
		}
		else
		{
			refreshClasses();
			if(classNameAnoteClass.containsKey(klass.getName().toLowerCase()))
			{
				return classNameAnoteClass.get(klass.getName().toLowerCase());
			}else{
				InitConfiguration.getDataAccess().insertNewClass(klass);
				classIDAnoteClass.put(klass.getId(), klass);
				classNameAnoteClass.put(klass.getName().toLowerCase(), klass);
				return klass;
			}
		}
	}
	
	public static IAnoteClass getClassGivenClassID(long classID) throws ANoteException
	{
		if(classIDAnoteClass.containsKey(classID))
		{
			return classIDAnoteClass.get(classID);
		}
		else
		{
			refreshClasses();
			return classIDAnoteClass.get(classID);
		}
	}
	
	public static IAnoteClass getClassIDClassName(String classsName) throws ANoteException
	{
		if(classNameAnoteClass.containsKey(classsName.toLowerCase()))
		{
			return classNameAnoteClass.get(classsName.toLowerCase());
		}
		else
		{
			refreshClasses();
			return classNameAnoteClass.get(classsName.toLowerCase());
		}
	}

	public static void updateClassName(IAnoteClass klass, String newCLass) throws ANoteException {
		InitConfiguration.getDataAccess().updateClassName(klass,newCLass);
		refreshClasses();
	}

	public static Collection<IAnoteClass> getClasses()
	{
		return classNameAnoteClass.values();
	}

	public static boolean containsClass(String newCLass) throws ANoteException {
		return getClassIDClassName(newCLass)!=null;
	}
}
