package com.silicolife.textmining.core.datastructures.dataaccess.database.instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.silicolife.textmining.core.datastructures.dataaccess.database.ADatabase;
import com.silicolife.textmining.core.datastructures.dataaccess.database.UpdateDatabaseHelp;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.FileHandling;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

/**
 * Class that represent a H2 Database 
 * 
 *
 */
public class H2EmbeddedDatabase extends ADatabase {
	

	public H2EmbeddedDatabase(String embeddedPath, String schema, String user, String pwd) {
		super(embeddedPath, null, schema, user, pwd, DataBaseTypeEnum.H2Embedded);
		this.setDriverClassName("org.h2.Driver");
		this.setDialectClassName("org.hibernate.dialect.H2Dialect");
	}

	public void openConnection() throws SQLException {
		if (!isLoadDriver()) {
			try {
				this.loadDriver();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setLoadDriver(true);
		}
		String url_db_connection = "jdbc:h2:file:" + this.getHost() + "/" + this.getSchema() + ";AUTO_RECONNECT=TRUE";
		this.setConnection(DriverManager.getConnection(url_db_connection, this.getUser(), this.getPwd()));
	}

	public Connection getNeWConnection() throws SQLException {
		if (!isLoadDriver()) {
			try {
				this.loadDriver();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setLoadDriver(true);
		}
		String url_db_connection = "jdbc:h2:file:" + this.getHost() + "/" + this.getSchema() + ";AUTO_RECONNECT=TRUE";
		return DriverManager.getConnection(url_db_connection, this.getUser(), this.getPwd());
	}

	@Override
	public boolean existDatabase() throws SQLException {
		IDatabase dbtest = new H2EmbeddedDatabase(getHost(), getSchema(), getUser(), getPwd());
		dbtest.openConnection();
		ResultSet result = null;
		Connection connect = dbtest.getConnection();
		result = connect.createStatement().executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + getSchema() + "'");
		while (result.next()) {
			if (result.getString(1).equals(getSchema())) {
				dbtest.closeConnection();
				return true;
			} else {
				return false;
			}
		}
		dbtest.closeConnection();
		return false;
	}

	public boolean createDataBase() throws SQLException {
		IDatabase dbtest = new H2EmbeddedDatabase(getHost(), getSchema(), getUser(), getPwd());
		dbtest.getConnection().close();
		return true;

	}

	public boolean createDataBase(String user, String password) throws SQLException {
		IDatabase dbtest = new H2EmbeddedDatabase(getHost(), getSchema(), user, password);
		dbtest.getConnection().close();
		return true;
	}

	//
	// public void fillDataBaseTables() throws DatabaseLoadDriverException,
	// SQLException, IOException {
	// FileReader fr = new FileReader(new
	// File(GlobalOptions.mysqlDatabaseFile));
	// BufferedReader br = new BufferedReader(fr);
	// StringBuffer stat = new StringBuffer();
	// String str = br.readLine();
	// Statement statement = getConnection().createStatement();
	// ;
	// while (str != null) {
	// if (str.startsWith("--")) {
	//
	// } else if (str.compareTo("") != 0) {
	// stat.append(" " + str);
	// if (str.contains(";")) {
	// statement.execute(stat.toString());
	// stat = new StringBuffer();
	// }
	// }
	// str = br.readLine();
	// }
	// }

	public void fillDataBaseTables(String schemaFile) throws SQLException, IOException {
		FileReader fr = new FileReader(new File(schemaFile));
		BufferedReader br = new BufferedReader(fr);
		StringBuffer stat = new StringBuffer();
		String str = br.readLine();
		Statement statement = getConnection().createStatement();
		;
		while (str != null) {
			if (str.startsWith("--")) {

			} else if (str.compareTo("") != 0) {
				stat.append(" " + str);
				if (str.contains(";")) {
					statement.execute(stat.toString());
					stat = new StringBuffer();
				}
			}
			str = br.readLine();
		}
		br.close();
	}

	private void updateDatabaseStepFrom(String filePath) throws SQLException, IOException {

		FileReader fr;
		fr = new FileReader(new File(filePath));
		BufferedReader br = new BufferedReader(fr);
		StringBuffer stat = new StringBuffer();
		String str = br.readLine();
		Statement statement;
		while (str != null) {
			if (str.startsWith("--")) {

			} else if (str.compareTo("") != 0) {
				stat.append(" " + str);
				if (str.contains(";")) {
					statement = getConnection().createStatement();
					statement.execute(stat.toString());
					stat = new StringBuffer();
				}
			}
			str = br.readLine();
		}
		br.close();
	}

	@Override
	public void updateDatabase(String updateFolder,String databaseversionfile) throws SQLException, ANoteException {
		int databaseVersion = InitConfiguration.getDataAccess().getDatabaseVersion();
		int newVersionID = UpdateDatabaseHelp.readDatabaseFileDataBase(databaseversionfile);
		boolean update = false;
		for (int i = databaseVersion + 1; i <= newVersionID; i++) {
			String path = updateFolder + "/" + GlobalOptions.h2DatbaseUpdateStartNameFile + i + GlobalOptions.mysqlDatbaseUpdateEndNameFile;
			String filePathComments = updateFolder + "/" + GlobalOptions.h2DatbaseUpdateStartNameFile + i + GlobalOptions.mysqlDatbaseUpdateEndNameInfoFile;
			String comments = "";
			try {
				comments = FileHandling.getFileContent(new File(filePathComments));
				updateDatabaseStepFrom(path);
				InitConfiguration.getDataAccess().addDatabaseVersion(i, comments);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			update = true;
		}
		if(update)
			InitConfiguration.getDataAccess().rebuildLuceneIndex();
	}

	@Override
	public boolean createDataBaseFromSqlScript(String filepath) throws SQLException {
		return false;
	}

	@Override
	public void shutDownDatabase() throws SQLException {
		super.closeConnection();

	}

	@Override
	public String getDataBaseURL() {
		return "jdbc:h2:file:" + this.getHost() + "/" +  this.getSchema() + ";AUTO_RECONNECT=TRUE";
	}

}
