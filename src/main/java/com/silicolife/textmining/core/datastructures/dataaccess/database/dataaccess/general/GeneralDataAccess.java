package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.general;


public class GeneralDataAccess {
	
//	private static PreparedStatement addPublication;
//	private static PreparedStatement addPublicationLabels;
//	private static PreparedStatement addPublicationExternaIDSources;
//	private static PreparedStatement addPublicationFields;
//	
//	private static void initPS() throws SQLException, DatabaseLoadDriverException {
//		if(addPublication == null || addPublication.isClosed())
//			addPublication = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.insertPublicationSQL);
//		if(addPublicationLabels == null || addPublicationLabels.isClosed())
//			addPublicationLabels = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.insertPublicationLabelsSQL);
//		if(addPublicationExternaIDSources == null || addPublicationExternaIDSources.isClosed())
//			addPublicationExternaIDSources = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.insertPublicationExternalIDSource);
//		if(addPublicationFields == null || addPublicationFields.isClosed())
//			addPublicationFields = Configuration.getDatabase().getConnection().prepareStatement(QueriesGeneral.insertPublicationFileds);
//	}
//	
//	public static void addDocuments(List<IPublication> documents) throws DatabaseLoadDriverException, SQLException
//	{
//		initPS();
//		for(IPublication pub : documents)
//		{
//			insertPublication(pub);
//			insertPublicationLabels(pub);
//		}
//		addPublication.executeBatch();
//		addPublication.clearBatch();
//		addPublicationLabels.executeBatch();
//		addPublicationLabels.clearBatch();
//		
//	}
//
//	private static void insertPublicationLabels(IPublication pub) throws SQLException {
//		if(pub.getPublicationLabels() == null ||!pub.getPublicationLabels().isEmpty())
//		{
//			addPublicationLabels.clearParameters();
//		}
//		
//	}
//
//	private static void insertPublication(IPublication pub) throws SQLException {
//		addPublication.clearParameters();
//		addPublication.setLong(1, pub.getId());
//		if(pub.getTitle()!=null)
//			addPublication.setString(2, pub.getTitle());
//		if(pub.getAuthors()!=null)
//			addPublication.setString(3, pub.getAuthors());
//		if(pub.getType()!=null)
//			addPublication.setString(4, pub.getType());
//		if(pub.getYearDate()!=null)
//			addPublication.setString(5, pub.getYearDate());
//		if( pub.getFullDate()!=null)
//			addPublication.setString(6, pub.getFullDate());
//		if( pub.getStatus()!=null)
//			addPublication.setString(7, pub.getStatus());
//		if( pub.getJournal()!=null)
//			addPublication.setString(8, pub.getJournal());
//		if( pub.getVolume()!=null)
//			addPublication.setString(9, pub.getVolume());
//		if( pub.getIssue()!=null)
//			addPublication.setString(10, pub.getIssue());
//		if(pub.getPages()!=null)
//			addPublication.setString(11, pub.getPages());
//		if(pub.getAbstractSection()!=null)
//			addPublication.setString(12, pub.getAbstractSection());
//		if(pub.getExternalLink()!=null)
//			addPublication.setString(13, pub.getExternalLink());		
//		addPublication.setBoolean(14, pub.getAvailableFreeFullTExt());
//		if(pub.getFullText()!=null)
//			addPublication.setString(15, pub.getFullText());
//		if( pub.getNotes()!=null)
//			addPublication.setString(16, pub.getNotes());
//		if( pub.getRelativePath()!=null)
//			addPublication.setString(17, pub.getRelativePath());
//		addPublication.addBatch();
//	}



}
