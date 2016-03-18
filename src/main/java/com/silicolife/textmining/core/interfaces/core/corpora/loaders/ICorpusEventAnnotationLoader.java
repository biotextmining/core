package com.silicolife.textmining.core.interfaces.core.corpora.loaders;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;


public interface ICorpusEventAnnotationLoader extends ICorpusEntityLoader{
	public Map<Long,IAnnotatedDocument> getDocumentEventAnnotations();
}
