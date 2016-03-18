package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessOrigins;
import com.silicolife.textmining.core.datastructures.process.ProcessOriginImpl;
import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;

/**
 * Class to transform anote2 process origin structures to daemon process origin
 * structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessOriginWrapper {

	public static IProcessOrigin convertToAnoteStructure(ProcessOrigins processesOrigin) {
		Long id = processesOrigin.getPoId();
		String name = processesOrigin.getPoDescription();
		IProcessOrigin processOrigin_ = new ProcessOriginImpl(id, name);
		return processOrigin_;
	}

	public static ProcessOrigins convertToDaemonStructure(IProcessOrigin processOrigin_) {
		Long id = processOrigin_.getId();
		String name = processOrigin_.getOrigin();
		ProcessOrigins processesOrigin = new ProcessOrigins(id, name);
		return processesOrigin;
	}
}
