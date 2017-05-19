package com.silicolife.textmining.core.datastructures.dataaccess.database;

import com.silicolife.textmining.core.datastructures.dataaccess.database.instances.H2EmbeddedDatabase;
import com.silicolife.textmining.core.datastructures.dataaccess.database.instances.MySQLDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

/**
 * Class that contains some static methods to build {@link IDatabase}
 * 
 * @author Hugo Costa
 *
 */
public class DatabaseFactory {

	public static IDatabase createDatabase(DataBaseTypeEnum databatype, String host, String port, String schema, String user, String pwd) {
		if (databatype == DataBaseTypeEnum.MYSQL) {
			return new MySQLDatabase(host, port, schema, user, pwd);
		}else if(databatype == DataBaseTypeEnum.H2Embedded)
			return new H2EmbeddedDatabase(host, schema, user, pwd);
		// else if(databatype == DataBaseTypeEnum.HQSQL)
		// {
		// return new HQSQLDatabase(host, port, schema, user, pwd);
		//
		// }
		// else if(databatype == DataBaseTypeEnum.SQLITE)
		// {
		// return new SQLLiteDatabase(host, port, schema, user, pwd);
		// }
		return null;
	}

	public static DatabaseManager createDatabaseManager(String database) {
		String[] databaseFields = database.split("\\|");
		if (databaseFields != null && databaseFields.length == 7) {

			DataBaseTypeEnum databatype = DataBaseTypeEnum.convertStringInDataBaseTypeEnum(databaseFields[1]);
			String host = databaseFields[2];
			String port = databaseFields[3];
			String schema = databaseFields[4];
			String user = databaseFields[5];
			String pwd = new String();
			if (databatype == DataBaseTypeEnum.MYSQL) {
				if (databaseFields.length == 7) {
					pwd = databaseFields[6];
				} else if (databaseFields.length == 6) {
					pwd = "";
				}
				return new DatabaseManager(new MySQLDatabase(host, port, schema, user, pwd));
			}else if (databatype == DataBaseTypeEnum.H2Embedded){
				if (databaseFields.length == 7) {
					pwd = databaseFields[6];
				} else if (databaseFields.length == 6) {
					pwd = "";
				}
				return new DatabaseManager(new H2EmbeddedDatabase(host, schema, user, pwd));
			}
			// else if(databatype == DataBaseTypeEnum.HQSQL)
			// {
			// return new HQSQLDatabase(host, port, schema, user, pwd);
			//
			// }
			// else if(databatype == DataBaseTypeEnum.SQLITE)
			// {
			// return new SQLLiteDatabase(host, port, schema, user, pwd);
			// }
		}
		return null;
	}
}
