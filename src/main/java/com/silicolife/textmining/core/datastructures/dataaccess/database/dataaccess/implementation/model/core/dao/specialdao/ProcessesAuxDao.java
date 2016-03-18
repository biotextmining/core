package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

public interface ProcessesAuxDao {

	List<Processes> findProcessesByAttributes(long auId, String processstr,String string);

}
