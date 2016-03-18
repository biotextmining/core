package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.publicationmanager;


public class PublicationManagerAccess {


//
//	public static IPublication getPublicationByID(int documentID) throws SQLException, DatabaseLoadDriverException
//	{
//		PreparedStatement findPub = Configuration.getDatabase().getConnection().prepareStatement(QueriesCorpora.selectPublicationsbyID);
//		findPub.setInt(1,documentID);
//		ResultSet rs = findPub.executeQuery();
//		IPublication publication = null;
//		if(rs.next())
//		{
//			publication =  new 	Publication(rs.getInt(1), 
//					rs.getString(2),//int pmid
//					rs.getString(3),//String title
//					rs.getString(4),//String authors
//					rs.getString(5),//int date
//					rs.getString(6),//String status
//					rs.getString(7),//String journal
//					rs.getString(8),//String volume
//					rs.getString(9),//String issue
//					rs.getString(10),//String pages
//					rs.getString(11),
//					rs.getString(12),//String aBstract
//					rs.getBoolean(13));
//		}
//		rs.close();
//		findPub.close();
//		return publication;
//	}
}
