package com.silicolife.textmining.core.datastructures.dataaccess.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.silicolife.textmining.core.datastructures.dataaccess.database.instances.StartDatabase;
import com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.QueriesGeneral;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

/**
 * Class that contains some Help method usefull 
 * 
 * @author Hugo Costa
 *
 */
public class HelpDatabase {
//	
//	/**
//	 * Return nextID in databse for @param tableName
//	 * 
//	 * @param db
//	 * @param tableName
//	 * @return
//	 * @throws DatabaseLoadDriverException 
//	 * @throws SQLException 
//	 */
//	public static int getNextInsertTableID(String tableName) throws DatabaseLoadDriverException, SQLException
//	{
//			PreparedStatement showTableInfoPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.getTableInformation);
//			showTableInfoPS.setString(1,tableName);
//			ResultSet rs = showTableInfoPS.executeQuery();		
//			if(rs.next())
//			{
//				int id = rs.getInt(11);
//				rs.close();
//				showTableInfoPS.close();
//				return id;
//			}
//			else
//			{
//				rs.close();
//				showTableInfoPS.close();
//				return -2;
//			}
//	}
	
	
//	/**
//	 * Return all available Sources
//	 * 
//	 * @return
//	 * @throws SQLException
//	 * @throws DatabaseLoadDriverException
//	 */
//	public static List<Source> getAllAvailableSources() throws SQLException, DatabaseLoadDriverException
//	{
//		List<Source> sources = new ArrayList<Source>();
//		PreparedStatement getAllSources = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.getAllSources);
//		ResultSet rs = getAllSources.executeQuery();
//		while(rs.next())
//		{
//			sources.add(new Source(rs.getInt(1), rs.getString(2)));
//		}
//		rs.close();
//		getAllSources.close();
//		return sources;
//	}

//	/**
//	 * Update Menu Item 
//	 * 
//	 * @param item
//	 * @throws SQLException
//	 * @throws InterruptedException
//	 * @throws IOException
//	 * @throws DatabaseLoadDriverException
//	 */
//	public static void updateMenuItem(MenuItem item) throws SQLException, InterruptedException, IOException, DatabaseLoadDriverException {
//		PreparedStatement updateItem = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.updateMenuItem);	
//		PreparedStatement updateItemWithouIcon = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.updateMenuItemWithouIcon);	
//		ImageIcon imageIcon = item.getIcon();
//		if(imageIcon!=null && !(new File(imageIcon.toString()).exists()))
//		{
//			updateItemWithouIcon.setString(1,item.getName());
//			updateItemWithouIcon.setString(2,item.getUrl());
//			updateItemWithouIcon.setInt(3,item.getId());
//			updateItemWithouIcon.executeUpdate();	
//			updateItemWithouIcon.close();
//		}
//		else
//		{
//			updateItem.setString(1,item.getName());
//			updateItem.setString(2,item.getUrl());
//			if(imageIcon!=null)
//			{
//				File file = new File(imageIcon.toString());
//				FileInputStream  fis = new FileInputStream (file);
//				updateItem.setBinaryStream(3, fis, (int) file.length());
//			}
//			else
//			{
//				updateItem.setNull(3, 1);
//			}
//			updateItem.setInt(4,item.getId());
//			updateItem.executeUpdate();	
//			updateItem.close();
//		}
//		
//	}

//	public static void removeMenuItemSourceLinkage(MenuItem item, List<Source> sources) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement removeItemSourceLinkage = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.removeMenuItemSourceLinkage);
//		removeItemSourceLinkage.setInt(1, item.getId());
//		for(Source source:sources)
//		{
//			removeItemSourceLinkage.setInt(2, source.getSourceID());
//			removeItemSourceLinkage.execute();
//		}
//		removeItemSourceLinkage.close();
//	}
//
//	public static void addMenuItemSourceLinkage(MenuItem item, List<Source> sources) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement addItemSourceLinkage = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.updateMenuItemSourceLinkage);
//		addItemSourceLinkage.setInt(1, item.getId());
//		for(Source source:sources)
//		{
//			addItemSourceLinkage.setInt(2, source.getSourceID());
//			addItemSourceLinkage.execute();
//		}
//		addItemSourceLinkage.close();
//	}
//
//	/**
//	 *  Missing treat icon;
//	 * @throws DatabaseLoadDriverException 
//	 */
//	public static void addMenuLink( MenuItem newItem) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement addMenuItem = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.addMenuLink);
//		addMenuItem.setString(1, newItem.getName());
//		addMenuItem.setString(2, newItem.getUrl());
//		addMenuItem.setNull(3, 0);
//		addMenuItem.setInt(4,newItem.getLevel());
//		addMenuItem.execute();
//		addMenuItem.close();
//	}
//
//	public static void addMenuSubMenuLinkage(MenuItem menuItem, MenuItem newItem) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement addItemSourceLinkage = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.addSubMenuItemToMenu);
//		addItemSourceLinkage.setInt(1, menuItem.getId());
//		addItemSourceLinkage.setInt(2, newItem.getId());
//		addItemSourceLinkage.execute();	
//		addItemSourceLinkage.close();
//	}
//
//	public static void removeMenuLink(MenuItem menuItem) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement removeAllSourceLinkage = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.removeAllSourceLinkages);
//		removeAllSourceLinkage.setInt(1, menuItem.getId());
//		removeAllSourceLinkage.execute();
//		removeAllSourceLinkage.close();
//		PreparedStatement removeAllMenuLinkages = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.removeAllMenuLinkages);
//		removeAllMenuLinkages.setInt(1, menuItem.getId());
//		removeAllMenuLinkages.setInt(2, menuItem.getId());
//		removeAllMenuLinkages.execute();
//		removeAllMenuLinkages.close();
//		PreparedStatement removeMenu = Configuration.getDatabase().getConnection().prepareStatement(QueriesExternalLinks.removeMenuLink);
//		removeMenu.setInt(1, menuItem.getId());
//		removeMenu.execute();
//		removeMenu.close();
//	}
	
	public static int getDatabaseVersion(IDatabase db) throws SQLException, ANoteException
	{
			int result = -1;
			if(db instanceof StartDatabase)
				return -1;
			if(db==null)
				return -1;
			PreparedStatement versionPS = db.getConnection().prepareStatement(QueriesGeneral.getDatabaseVersion);
			ResultSet rs = versionPS.executeQuery();
			if(rs.next())
			{
				result = rs.getInt(1);
			}
			else
			{
				result = -1;
			}
			rs.close();
			versionPS.close();
			return result;
		}
//
//	public static void insertNewVersionRegestry(int i,String comments) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement newVersion = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.insertNewVersion);
//		newVersion.setInt(1,i);
//		newVersion.setNString(2,Utils.currentTime());
//		newVersion.setNString(3, comments);
//		newVersion.execute();
//		newVersion.close();
//	}

	public static boolean testIfDatabaseExistes(IDatabase databse) {
		try{

	        ResultSet resultSet = databse.getConnection().getMetaData().getCatalogs();

	        while (resultSet.next()) {

	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(databse.getSchema())){
	                return true;
	            }
	        }
	        resultSet.close();

	    }
	    catch(Exception e){
	    }
	    return false;
	}
	
	
}
