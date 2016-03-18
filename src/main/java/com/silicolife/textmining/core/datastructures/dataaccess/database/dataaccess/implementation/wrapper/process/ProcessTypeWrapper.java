package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessTypes;
import com.silicolife.textmining.core.datastructures.process.ProcessTypeImpl;
import com.silicolife.textmining.core.interfaces.process.IProcessType;

/**
 * Class to transform anote2 Process type structures to daemon Process Type
 * structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessTypeWrapper {

	public static IProcessType convertToAnoteStructure(ProcessTypes processesType) {
		Long id = processesType.getPtId();
		String name = processesType.getPtProcessType();
		IProcessType processType_ = new ProcessTypeImpl(id, name);
		return processType_;
	}

	public static ProcessTypes convertToDaemonStructure(IProcessType processType_) {
		Long id = processType_.getId();
		String name = processType_.getType();
		ProcessTypes processesType = new ProcessTypes(id, name);
		return processesType;
	}
}
