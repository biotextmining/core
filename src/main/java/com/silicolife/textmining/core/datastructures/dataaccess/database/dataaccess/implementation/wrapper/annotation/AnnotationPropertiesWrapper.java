package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.annotation;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationPropertiesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;

public class AnnotationPropertiesWrapper {
	
	public static Properties convertToAnoteStructure(Set<AnnotationProperties> annotationProperties) {

		Properties properties = new Properties();
		for (AnnotationProperties annotationProperty : annotationProperties) {
			String key = annotationProperty.getId().getAnpPropKey();
			String value = annotationProperty.getAnpPropValue();
			properties.put(key, value);
		}
//		if (properties.size() == 0)
//			return null;

		return properties;
			
	}

	public static Set<AnnotationProperties> convertToDaemonStructure(Properties properties, Annotations annotation) {
		Set<AnnotationProperties> annotationProperties = new HashSet<AnnotationProperties>(0);

		if(properties==null)
			return annotationProperties;
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			AnnotationPropertiesId annotationPropertyID = new AnnotationPropertiesId(key, annotation.getAnnId()); 
			AnnotationProperties annotationProperty = new AnnotationProperties(annotationPropertyID,annotation,value);
			annotationProperties.add(annotationProperty);
		}
		return annotationProperties;

	}

}
