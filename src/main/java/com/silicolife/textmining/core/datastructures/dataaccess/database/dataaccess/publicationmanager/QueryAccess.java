package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.publicationmanager;


public class QueryAccess {

//	public static Set<String> getQueryPublicationsOtherIds(IQuery query) throws DaemonException, DatabaseLoadDriverException, SQLException {
//
//		Set<String> pmids = new HashSet<String>();
//		Connection connection = Configuration.getDatabase().getConnection();
//		PreparedStatement statement;
//		statement = connection.prepareStatement(QueriesPublication.selectIDAndOtherIDForQuery);
//		statement.setLong(1, query.getID());
//		ResultSet rs = statement.executeQuery();
//		while (rs.next()) {
//			String pmid = rs.getString(2);
//			pmids.add(pmid);
//		}
//		rs.close();
//		statement.close();
//		return pmids;
//	}

//	public static Set<Long> getQueryPublicationsIds(IQuery query) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		Set<Long> ids = new HashSet<Long>();
//		PreparedStatement prep = Configuration.getDatabase().getConnection().prepareStatement(QueriesPublication.selectIDAndOtherIDForQuery);
//		prep.setLong(1, query.getID());
//		ResultSet rs = prep.executeQuery();
//		while (rs.next()) {
//			Long id = rs.getLong(1);
//			ids.add(id);
//		}
//		rs.close();
//		prep.close();
//		return ids;
//	}

//	public static Map<Long, RelevanceTypeEnum> getQueryPublicationsRelevance(IDatabase db, IQuery query) throws DaemonException, SQLException, DatabaseLoadDriverException {
//		Map<Long, RelevanceTypeEnum> documentRelevance = new HashMap<Long, RelevanceTypeEnum>();
//		PreparedStatement selectQueryPublicationRelevancePS = db.getConnection().prepareStatement(QueriesDocument.selectPublicationRelevanceForQuerySQL);
//		selectQueryPublicationRelevancePS.setLong(1, query.getId());
//		ResultSet selectQueryPublicationRelevanceRS = selectQueryPublicationRelevancePS.executeQuery();
//		while (selectQueryPublicationRelevanceRS.next()) {
//			documentRelevance.put(selectQueryPublicationRelevanceRS.getLong(1), RelevanceTypeEnum.convertString(selectQueryPublicationRelevanceRS.getString(2)));
//		}
//		selectQueryPublicationRelevanceRS.close();
//		selectQueryPublicationRelevancePS.close();
//		return documentRelevance;
//	}
//
//	public static List<IPublication> getQueryPublications(IDatabase db,IQuery query) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		List<IPublication> publications = new ArrayList<IPublication>();
//		PreparedStatement selectQueryPublicationsPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesPublication.selectPublicationsQueryExtra);
//		selectQueryPublicationsPS.setLong(1, query.getId());
//		ResultSet selectQueryPublicationsRS = selectQueryPublicationsPS.executeQuery();
//		while (selectQueryPublicationsRS.next()) {
//			publications.add(new Publication(selectQueryPublicationsRS.getInt(1), selectQueryPublicationsRS.getString(2),// int
//																			// pmid
//					selectQueryPublicationsRS.getString(3),// String title
//					selectQueryPublicationsRS.getString(4),// String authors
//					selectQueryPublicationsRS.getString(5),// int date
//					selectQueryPublicationsRS.getString(6),// String status
//					selectQueryPublicationsRS.getString(7),// String journal
//					selectQueryPublicationsRS.getString(8),// String volume
//					selectQueryPublicationsRS.getString(9),// String issue
//					selectQueryPublicationsRS.getString(10),// String pages
//					selectQueryPublicationsRS.getString(11), // String externalLink
//					selectQueryPublicationsRS.getString(12),// String aBstract
//					selectQueryPublicationsRS.getBoolean(13)));
//		}
//		selectQueryPublicationsRS.close();
//		selectQueryPublicationsRS.close();
//		return publications;
//	}
//
////	public static void refreshQueryInfo(IQuery query) throws DaemonException, DatabaseLoadDriverException, SQLException {
////
////		PreparedStatement statement;
////		statement = GlobalOptions.database.getConnection().prepareStatement(QueriesIRProcess.updateQueryInfo);
////		statement.setInt(1, query.getPublicationsSize());
////		statement.setInt(2, query.getAvailableAbstracts());
////		statement.setString(3, Utils.currentTime());
////		statement.setLong(4, query.getID());
////		statement.execute();
////		statement.close();
////
////	}
//
//	public static void updateRelevance(IPublication publication, IQuery query, RelevanceTypeEnum relevance) throws DaemonException, DatabaseLoadDriverException, SQLException {
//
//		PreparedStatement selectRelevance = Configuration.getDatabase().getConnection().prepareStatement(QueriesDocument.selectRelevancePublication);
//		selectRelevance.setLong(1, publication.getId());
//		selectRelevance.setLong(2, query.getId());
//		ResultSet rsselectRelevance = selectRelevance.executeQuery();
//		if (!rsselectRelevance.next()) {
//			PreparedStatement insertrelevance = Configuration.getDatabase().getConnection().prepareStatement(QueriesDocument.insertRelevanceInDocInQuery);
//			insertrelevance.setLong(1, publication.getId());
//			insertrelevance.setLong(2, query.getId());
//			insertrelevance.setNString(3, RelevanceTypeEnum.none.toString());
//			insertrelevance.execute();
//			insertrelevance.close();
//		}
//		rsselectRelevance.close();
//		selectRelevance.close();
//		PreparedStatement updateRelevance = Configuration.getDatabase().getConnection().prepareStatement(QueriesDocument.updateRelevanceDocInQuery);
//		updateRelevance.setNString(1, relevance.toString());
//		updateRelevance.setLong(2, query.getId());
//		updateRelevance.setLong(3, publication.getId());
//		updateRelevance.execute();
//		updateRelevance.close();
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * @param query
//	 * @param publications
//	 * @throws SQLException
//	 * @throws DatabaseLoadDriverException
//	 */
//	public static void addQueryPublications(IDatabase db,IQuery query, List<IPublication> publications) throws SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement addQueryPublicationPS = db.getConnection().prepareStatement(QueriesPublication.insertQueryPublicationSQL);
//		addQueryPublicationPS.setLong(1, query.getId());
//		for (IPublication pub : publications) {
//			addQueryPublicationPS.setLong(2, pub.getId());
//			addQueryPublicationPS.execute();
//		}
//		addQueryPublicationPS.close();
//	}
//
//	/**
//	 * Insert a Query in Database
//	 * 
//	 * @param query
//	 * @throws SQLException
//	 * @throws DatabaseLoadDriverException
//	 */
//	public static void createQuery(IQuery query) throws SQLException, DatabaseLoadDriverException {
//		insertChangeQueryOrigin(query);
//		insertQuery(query);
//		insertQueryProperties(query);
//	}
//
//	private static void insertChangeQueryOrigin(IQuery query) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement checkOrigin = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.selectQuryOriginTypeSQL);
//		checkOrigin.setString(1, query.getQueryOriginType().getOrigin());
//		ResultSet checkOriginRS = checkOrigin.executeQuery();
//		// test if origin has in Database
//		if(checkOriginRS.next())
//		{
//			// if true define a original database id in table
//			query.getQueryOriginType().setID(checkOriginRS.getInt(1));
//		}
//		else
//		{
//			// otherwise insert into origin table
//			PreparedStatement insertOriginType = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.insertOriginTypeSQL);
//			insertOriginType.setLong(1, query.getQueryOriginType().getId());
//			insertOriginType.setString(2,  query.getQueryOriginType().getOrigin());
//			insertOriginType.execute();
//			insertOriginType.close();
//		}
//		checkOriginRS.close();
//		checkOriginRS.close();
//	}
//
//	private static void insertQuery(IQuery query) throws SQLException,
//			DatabaseLoadDriverException {
//		PreparedStatement statement = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.insertQuerySQL);
//		statement.setLong(1, query.getId());
//		statement.setLong(2, query.getQueryOriginType().getId());
//		statement.setNString(3, Utils.currentTime());
//		statement.setNString(4, query.getKeyWords());
//		statement.setNString(5, query.getOrganism());
//		statement.setNString(6, query.getCompleteQuery());
//		statement.setInt(7, query.getPublicationsSize());
//		statement.setInt(8, query.getAvailableAbstracts());
//		statement.setString(9, query.getName());
//		statement.setBoolean(10, true);
//		statement.setNString(11,query.getNotes());
//		statement.execute();
//		statement.close();
//	}
//	
//	private static void insertQueryProperties(IQuery query) throws SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement statement = Configuration.getDatabase().getConnection().prepareStatement(QueriesIRProcess.insertQueryPropertiesSQL);
//		Enumeration<Object> itObj = query.getProperties().keys();
//		while (itObj.hasMoreElements()) {
//			Object propertyNameObj = itObj.nextElement();
//			String propertyName = (String) propertyNameObj;
//			Object propertyValueObj = query.getProperties().getProperty(propertyName);
//			statement.setLong(1, query.getId());
//			statement.setNString(2, propertyName);
//			statement.setNString(3, (String) propertyValueObj);
//			statement.execute();
//		}
//		statement.close();
//	}
//
//	public static void inactiveQuery(IDatabase db, IQuery query) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement ps = db.getConnection().prepareStatement(QueriesIRProcess.inactiveQuery);
//		ps.setLong(1, query.getId());
//		ps.execute();
//		ps.close();
//	}
//
//	public static void updateQuery(IDatabase db, IQuery query) throws SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement ps = db.getConnection().prepareStatement(QueriesIRProcess.updateQuerySQL);
//		Date date = new Date(query.getDate().getTime());
//		ps.setDate(1, date);
//		ps.setInt(2, query.getPublicationsSize());
//		ps.setInt(3, query.getAvailableAbstracts());
//		ps.setNString(4, query.getName());
//		ps.setNString(5, query.getNotes());
//		ps.setLong(6, query.getId());
//		ps.execute();
//		ps.close();
//	}
//
////	public static void removeQuery(IQuery query) throws DaemonException, SQLException, DatabaseLoadDriverException {
////
////		PreparedStatement removeQuery = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.removeQuery);
////		PreparedStatement removePublicationsQuery = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.removeQueryPublicationLinking);
////		PreparedStatement removeQueryProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.removeQueryProperties);
////		removeQuery.setInt(1, query.getID());
////		removePublicationsQuery.setInt(1, query.getID());
////		removeQueryProperties.setInt(1, query.getID());
////		removePublicationsQuery.execute();
////		removePublicationsQuery.close();
////		removeQueryProperties.execute();
////		removeQueryProperties.close();
////		removeQuery.execute();
////		removeQuery.close();
////	}
//
//	public static IQuery getQueryByID(long queryID) throws DaemonException,SQLException, DatabaseLoadDriverException
//	{
//		IQuery query = null;
//		PreparedStatement selectQueryProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesIRProcess.selectQueryPropertiesSQL);
//		PreparedStatement selectQueryOrigin = Configuration.getDatabase().getConnection().prepareStatement(QueriesIRProcess.selectQueryOriginSQL);
//		PreparedStatement selectQuery = Configuration.getDatabase().getConnection().prepareStatement(QueriesIRProcess.selectQueryByIDSQL);
//		selectQuery.setLong(1, queryID);
//		ResultSet selectQueryRS = selectQuery.executeQuery();
//		if(selectQueryRS.next())
//		{
//			long queryTypeID = selectQueryRS.getLong(2);
//			Date date = selectQueryRS.getDate(3);
//			String keywords = selectQueryRS.getNString(4);
//			String organism = selectQueryRS.getNString(5);
//			String completeQuery = selectQueryRS.getNString(6);
//			int publicationsSize = selectQueryRS.getInt(7);
//			int availableAbstracts = selectQueryRS.getInt(8);
//			String name = selectQueryRS.getNString(9);
//			String notes = selectQueryRS.getNString(11);
//			Properties properties = getQueryProperties(selectQueryProperties,queryID);
//			IQueryOriginType queryType = getQueryType(selectQueryOrigin,queryTypeID);
//			query = new Query(queryID, queryType, date, keywords, organism, completeQuery, publicationsSize, availableAbstracts, name,notes, properties);
//		}
//		selectQueryRS.close();
//		selectQueryProperties.close();
//		selectQueryOrigin.close();
//		selectQuery.close();
//		return query;
//	}
//
//
//	public static List<IQuery> getAllQueries(IDatabase db) throws DaemonException, SQLException, DatabaseLoadDriverException {
//		List<IQuery> queries = new ArrayList<IQuery>();
//		PreparedStatement selectAllQueries = db.getConnection().prepareStatement(QueriesProcess.selectAllQueriesSQL);
//		PreparedStatement selectQueryProperties = db.getConnection().prepareStatement(QueriesIRProcess.selectQueryPropertiesSQL);
//		PreparedStatement selectQueryOrigin = db.getConnection().prepareStatement(QueriesIRProcess.selectQueryOriginSQL);
//		ResultSet selectAllQueriesRS = selectAllQueries.executeQuery();
//		while (selectAllQueriesRS.next()) {
//			long queryID = selectAllQueriesRS.getLong(1);
//			long queryTypeID = selectAllQueriesRS.getLong(2);
//			Date date = selectAllQueriesRS.getDate(3);
//			String keywords = selectAllQueriesRS.getNString(4);
//			String organism = selectAllQueriesRS.getNString(5);
//			String completeQuery = selectAllQueriesRS.getNString(6);
//			int publicationsSize = selectAllQueriesRS.getInt(7);
//			int availableAbstracts = selectAllQueriesRS.getInt(8);
//			String name = selectAllQueriesRS.getNString(9);
//			String notes = selectAllQueriesRS.getNString(11);
//			Properties properties = getQueryProperties(selectQueryProperties,queryID);
//			IQueryOriginType queryType = getQueryType(selectQueryOrigin,queryTypeID);
//			IQuery query = new Query(queryID, queryType, date, keywords, organism, completeQuery, publicationsSize, availableAbstracts, name,notes, properties);
//			queries.add(query);
//		}
//		selectAllQueriesRS.close();
//		selectAllQueries.close();
//		selectQueryProperties.close();
//		selectQueryOrigin.close();
//		return queries;
//
//	}
//
//	private static IQueryOriginType getQueryType(PreparedStatement selectQueryOrigin, long queryTypeID) throws SQLException {
//		IQueryOriginType queryType = null;
//		selectQueryOrigin.setLong(1, queryTypeID);
//		ResultSet selectQueryOriginRS = selectQueryOrigin.executeQuery();
//		if(selectQueryOriginRS.next())
//		{
//			queryType = new QueryOriginType(queryTypeID, selectQueryOriginRS.getNString(2));
//		}
//		return queryType;
//	}
//
//	private static Properties getQueryProperties(PreparedStatement selectQueryProperties, long queryID) throws SQLException {
//		Properties prop = new Properties();
//		selectQueryProperties.setLong(1,queryID);
//		ResultSet rs = selectQueryProperties.executeQuery();
//		while (rs.next()) {
//			prop.put(rs.getString(1), rs.getString(2));
//		}
//		rs.close();
//		return prop;
//	}
//	
//
//	public static void updatePublicationAvailability(IDatabase db,List<IPublication> publications, boolean availability) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement updatePublicationAvailabilityPS = db.getConnection().prepareStatement(QueriesPublication.updatePublicationPDFavailableSQL);
//		updatePublicationAvailabilityPS.setBoolean(1,availability);
//		for(IPublication publication:publications)
//		{
//			updatePublicationAvailabilityPS.setLong(2, publication.getId());
//			updatePublicationAvailabilityPS.execute();
//		}
//		updatePublicationAvailabilityPS.close();
//	}
//
//	public static Map<String, Integer> getAllPublicationsExternalIDFromSource(IDatabase db, String source) throws SQLException, DatabaseLoadDriverException {
//		Map<String, Integer>  mapPublicationExternalIDPublicationID = new HashMap<String, Integer>();
//		PreparedStatement publicationExternalIDPublicationID = db.getConnection().prepareStatement(QueriesIRProcess.selectPublicationsExternalIDForSource);
//		publicationExternalIDPublicationID.setString(1, source);
//		ResultSet publicationExternalIDPublicationIDRS = publicationExternalIDPublicationID.executeQuery();
//		while(publicationExternalIDPublicationIDRS.next())
//		{
//			String externaID = publicationExternalIDPublicationIDRS.getNString(1);
//			int publicationID = publicationExternalIDPublicationIDRS.getInt(2);
//			mapPublicationExternalIDPublicationID.put(externaID, publicationID);
//		}
//		return mapPublicationExternalIDPublicationID;
//	}
//
//	public static Set<String> getQueryPublicationsExternalIDFromSource(IDatabase db, IQuery query, String source) throws SQLException, DatabaseLoadDriverException {
//		Set<String> result = new HashSet<String>();
//		PreparedStatement queryPublicationsExternalIDFromSourcePS = db.getConnection().prepareStatement(QueriesIRProcess.selectQueryPublicationsExternalIDForSource);
//		queryPublicationsExternalIDFromSourcePS.setLong(1, query.getId());
//		queryPublicationsExternalIDFromSourcePS.setString(2, source);
//		ResultSet queryPublicationsExternalIDFromSourceRS = queryPublicationsExternalIDFromSourcePS.executeQuery();
//		while(queryPublicationsExternalIDFromSourceRS.next())
//		{
//			result.add(queryPublicationsExternalIDFromSourceRS.getNString(1));
//		}
//		return result;
//	}
//	
	
	
	
}
