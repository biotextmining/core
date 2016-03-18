package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.resources;


public class ResourceAccess {

//	public static HashMap<String, GenericPair<Long, Long>> findTermsTextOnly(IResource<IResourceElement> resource) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		HashMap<String, GenericPair<Long, Long>> terms = new HashMap<String, GenericPair<Long, Long>>();
//		PreparedStatement termInMemory = Configuration.getDatabase().getConnection().prepareStatement(QueriesResources.selectAllResourceElement);
//		termInMemory.setLong(1, resource.getID());
//		ResultSet rs = termInMemory.executeQuery();
//		while (rs.next()) {
//			terms.put(rs.getString(4), new GenericPair<Long, Long>(rs.getLong(3), rs.getLong(1)));
//		}
//		rs.close();
//		termInMemory.close();
//
//		return terms;
//	}
//
//	public static HashMap<String, GenericPair<Long, Long>> findSynTextOnly(IResource<IResourceElement> resource) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		HashMap<String, GenericPair<Long, Long>> synonyms = new HashMap<String, GenericPair<Long, Long>>();
//		PreparedStatement termInMemory = Configuration.getDatabase().getConnection().prepareStatement(QueriesResources.getElementsSynonyms);
//		termInMemory.setInt(1, resource.getID());
//		ResultSet rs = termInMemory.executeQuery();
//		while (rs.next()) {
//			synonyms.put(rs.getString(1), new GenericPair<Long, Long>(rs.getLong(2), rs.getLong(3)));
//		}
//		rs.close();
//		termInMemory.close();
//
//		return synonyms;
//	}
//
//	public static HashMap<String, GenericPair<Long, Long>> findExternalIDs(IResource<IResourceElement> resource) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		HashMap<String, GenericPair<Long, Long>> externalIDs = new HashMap<String, GenericPair<Long, Long>>();
//		PreparedStatement extIDMemory = Configuration.getDatabase().getConnection().prepareStatement(QueriesResources.selectElementExternalID);
//		extIDMemory.setInt(1, resource.getID());
//		ResultSet rs = extIDMemory.executeQuery();
//		while (rs.next()) {
//			externalIDs.put(rs.getString(8), new GenericPair<Long, Long>(rs.getLong(9), rs.getLong(1)));
//		}
//		rs.close();
//		extIDMemory.close();
//
//		return externalIDs;
//	}

}
