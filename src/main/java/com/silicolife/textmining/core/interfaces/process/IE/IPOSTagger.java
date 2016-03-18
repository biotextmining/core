package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentenceSintaxRepresentation;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;
import com.silicolife.textmining.core.interfaces.process.IE.re.clue.IVerbInfo;

public interface IPOSTagger {
	public List<IGenericPair<Long,Long>> getDocumentSentencelimits();
	public ISentenceSintaxRepresentation getSentenceSintaticLayer(Set<String> termionations, IGenericPair<Long, Long> setenceLimits,Long documentStartOffset) throws ANoteException;
	public SortedMap<Long, IVerbInfo> getVerbsPosition(List<IVerbInfo> verbsInfo);


}
