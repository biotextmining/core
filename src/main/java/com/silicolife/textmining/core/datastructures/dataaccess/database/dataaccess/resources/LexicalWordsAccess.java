package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.resources;


public class LexicalWordsAccess {

//	public static HashMap<String, Long> getLexicalWordsAux(IResource<?> resource) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		HashMap<String, Long> lexicalWordDatabaseID = new HashMap<String, Long>();
//		{
//			PreparedStatement termInMemory = Configuration.getDatabase().getConnection().prepareStatement(QueriesResources.selectResourceElement);
//			termInMemory.setLong(1, (long) resource.getID());
//			ResultSet rs = termInMemory.executeQuery();
//			while (rs.next()) {
//				lexicalWordDatabaseID.put(rs.getString(1), (long) rs.getInt(2));
//			}
//			rs.close();
//			termInMemory.close();
//		}
//		return lexicalWordDatabaseID;
//	}
}
