package com.silicolife.textmining.core.interfaces.core.annotation;

/**
 * Type of Log Annotations (Also associated with Database Table Annotation_log column type)
 * 
 * @author Hugo Costa
 *
 */
public enum AnnotationLogTypeEnum {
	ENTITYADD,
	ENTITYREMOVE,
	ENTITYUPDATE,
	RELATIONADD,
	RELATIONREMOVE,
	RELATIONUPDATE;
	
	public static boolean isEntityLog(AnnotationLogTypeEnum logType)
	{
		if(logType.equals(AnnotationLogTypeEnum.ENTITYADD) ||
				logType.equals(AnnotationLogTypeEnum.ENTITYREMOVE) ||
				logType.equals(AnnotationLogTypeEnum.ENTITYUPDATE))
		{
			return true;
		}
		return false;
	}

	public static boolean isRelationLog(AnnotationLogTypeEnum logType) {
		if(logType.equals(AnnotationLogTypeEnum.RELATIONADD) ||
				logType.equals(AnnotationLogTypeEnum.RELATIONUPDATE) ||
				logType.equals(AnnotationLogTypeEnum.RELATIONREMOVE))
		{
			return true;
		}
		return false;
	}
	
	public static AnnotationLogTypeEnum convertStringToEnum(String type)
	{
		if(AnnotationLogTypeEnum.ENTITYADD.toString().equals(type))
		{
			return AnnotationLogTypeEnum.ENTITYADD;
		}
		else if(AnnotationLogTypeEnum.ENTITYREMOVE.toString().equals(type))
		{
			return AnnotationLogTypeEnum.ENTITYREMOVE;

		}
		else if(AnnotationLogTypeEnum.ENTITYUPDATE.toString().equals(type))
		{
			return AnnotationLogTypeEnum.ENTITYUPDATE;
		}
		else if(AnnotationLogTypeEnum.RELATIONADD.toString().equals(type))
		{
			return AnnotationLogTypeEnum.RELATIONADD;
		}
		else if(AnnotationLogTypeEnum.RELATIONREMOVE.toString().equals(type))
		{
			return AnnotationLogTypeEnum.RELATIONREMOVE;
		}
		else if(AnnotationLogTypeEnum.RELATIONUPDATE.toString().equals(type))
		{
			return AnnotationLogTypeEnum.RELATIONUPDATE;
		}
		else
			return null;
	}

}
