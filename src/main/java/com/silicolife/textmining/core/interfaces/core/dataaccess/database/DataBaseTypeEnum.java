package com.silicolife.textmining.core.interfaces.core.dataaccess.database;

public enum DataBaseTypeEnum {
	MYSQL;
//	HQSQL,
//	SQLITE;
	
	
	public static DataBaseTypeEnum convertStringInDataBaseTypeEnum(String databaseType)
	{
		if(databaseType.equals(DataBaseTypeEnum.MYSQL.toString()))
		{
			return DataBaseTypeEnum.MYSQL;
		}
//		else if(databaseType.equals(DataBaseTypeEnum.HQSQL.toString()))
//		{
//			return DataBaseTypeEnum.HQSQL;
//		}
//		else if(databaseType.equals(DataBaseTypeEnum.SQLITE.toString()))
//		{
//			return DataBaseTypeEnum.SQLITE;
//		}
		return null;
	}
}
