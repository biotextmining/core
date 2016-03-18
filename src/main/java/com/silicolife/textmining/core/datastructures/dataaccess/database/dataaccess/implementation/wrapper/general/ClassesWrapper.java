package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class ClassesWrapper {

	public static IAnoteClass convertToAnoteStructure(Classes classes) {
		if(classes==null)
			return null;
		Long id = classes.getClaId();
		String name = classes.getClaName();
		if(name == null)
			name = new String();
		String color = classes.getClaColor();
		if(color == null)
			color = new String();
		
		IAnoteClass classes_ = new AnoteClass(id, name);
		classes_.setColor(color);
		return classes_;

	}

	public static Classes convertToDaemonStructure(IAnoteClass classes_) {
		if(classes_==null)
			return null;
		
		Long id = classes_.getId();
		String name = classes_.getName();
		String color = classes_.getColor();
		
		if(name.trim().equals(""))
			name = null;
		if(color!=null && color.trim().equals(""))
			color = Utils.randomAlphaNumColor(6);

		Classes classes = new Classes(id, name);
		classes.setClaColor(color);

		return classes;
	}
}
