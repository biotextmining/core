
package com.silicolife.textmining.core.interfaces.process.IE.ner;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.process.utils.IEvaluation;

public interface INERSchemaEvaluation extends IEvaluation{
	List<IEntityAnnotation> getHitEntities();
	List<IEntityAnnotation> getMissingEntitiesInGoldStandard();
	List<IEntityAnnotation> getAditionalgEntitiesInStandard();
	INERClassEvaluation getClassTypeScores();
}
