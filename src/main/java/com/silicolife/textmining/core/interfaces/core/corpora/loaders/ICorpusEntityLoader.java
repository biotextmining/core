package com.silicolife.textmining.core.interfaces.core.corpora.loaders;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;

public interface ICorpusEntityLoader extends ICorpusLoader{
	public Map<Long,IAnnotatedDocument> getDocumentEntityAnnotations();
}
