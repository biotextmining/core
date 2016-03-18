package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess;

import java.sql.PreparedStatement;

public class AnnotationLogManagement {
	
	private static final int  enumTypeToDatabase = 1;
	private static PreparedStatement addNewDocumentLog = null;

	
//	public static SortedSet<IAnnotationLog> getDocumentLogsInAnProcessAndCorpus(int corpusID,int processID, int documentID) throws SQLException, DatabaseLoadDriverException
//	{
//		SortedSet<IAnnotationLog> annotationLogs = new TreeSet<IAnnotationLog>();
//		AnnotationLog log;
//		PreparedStatement getAllDocumentLogs = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectDocumentLogAnnotations);
//		getAllDocumentLogs.setInt(1, corpusID);
//		getAllDocumentLogs.setInt(2, processID);
//		getAllDocumentLogs.setInt(3, documentID);
//		ResultSet rs = getAllDocumentLogs.executeQuery();
//		while(rs.next())
//		{
////			Date date = rs.getTimestamp(10);
////			AnnotationLogTypeEnum type = AnnotationLogTypeEnum.valueOf(rs.getString(6));
////			log = new AnnotationLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),type ,
////					rs.getString(7), rs.getString(8), rs.getString(9),date);
////			annotationLogs.add(log);
//		}
//		rs.close();
//		getAllDocumentLogs.close();
//		return annotationLogs;
//	}
	
//	public static SortedSet<IAnnotationLog> getLogsInAnProcessAndCorpus(long corpusID,long processID) throws SQLException, DatabaseLoadDriverException
//	{
//		SortedSet<IAnnotationLog> annotationLogs = new TreeSet<IAnnotationLog>();
//		IAnnotationLog log;
//		PreparedStatement getAllDocumentLogs = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectProcessLogAnnotations);
//		getAllDocumentLogs.setInt(1, corpusID);
//		getAllDocumentLogs.setInt(2, processID);
//		ResultSet rs = getAllDocumentLogs.executeQuery();
//		while(rs.next())
//		{
//			Date date = rs.getTimestamp(10);
//			AnnotationLogTypeEnum type = AnnotationLogTypeEnum.valueOf(rs.getString(6));
//			log = new AnnotationLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),type ,
//					rs.getString(7), rs.getString(8), rs.getString(9),date);
//			annotationLogs.add(log);
//		}
//		rs.close();
//		getAllDocumentLogs.close();
//		return annotationLogs;
//	}
	
//	public static void addDocumentLog(int corpusID,int originalAnnotationID, int processID,int documentID, AnnotationLogTypeEnum type, String oldString,
//			String newString, String notes, Date date) throws SQLException, DatabaseLoadDriverException
//	{
//		if(addNewDocumentLog==null || addNewDocumentLog.isClosed())
//		{
//			initPrepareStatment();
//		}
//		if(originalAnnotationID<=0)
//		{
//			addNewDocumentLog.setNull(1, originalAnnotationID);
//		}
//		else
//		{
//			addNewDocumentLog.setInt(1, originalAnnotationID);
//		}
//		addNewDocumentLog.setInt(2, corpusID);
//		addNewDocumentLog.setInt(3, processID);
//		addNewDocumentLog.setInt(4, documentID);
//		// Enum starts with 0 and databse enum stars with 1
//		addNewDocumentLog.setInt(5, type.ordinal()+enumTypeToDatabase);
//		addNewDocumentLog.setNString(6, oldString);
//		addNewDocumentLog.setNString(7, newString);
//		addNewDocumentLog.setNString(8, notes);
//		// Change Date Java to SQL date
//		addNewDocumentLog.setNString(9,Utils.SimpleDataFormat.format(date));
//		addNewDocumentLog.execute();
//	}
	
//	private static void initPrepareStatment() throws SQLException, DatabaseLoadDriverException {
//		addNewDocumentLog = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.addAnnotationLog)	;
//	}
//
//	public static void addDocumentLog(IAnnotationLog annotationLog) throws SQLException, DatabaseLoadDriverException
//	{
//		addDocumentLog(annotationLog.getCorpusID(),annotationLog.getOriginalAnnotationID(), annotationLog.getProcessID(), annotationLog.getDocumentID(), annotationLog.getType(), annotationLog.getOldString(),
//				annotationLog.getNewString(), annotationLog.getNotes(), annotationLog.getDate());
//	}

//	public static Map<Integer, IAnnotation> getAnnotationsRelatedToAnnotationLogs(ICorpus corpus,IIEProcess ieProcess) throws SQLException, DatabaseLoadDriverException {
//		Map<Integer, IAnnotation> result = new HashMap<Integer, IAnnotation>();
//		PreparedStatement getAnnotationIDAndType = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectAnnotationIDForAnnotaionLogs);
//		getAnnotationIDAndType.setInt(1, corpus.getID());
//		getAnnotationIDAndType.setInt(2, ieProcess.getID());
//		ResultSet rs = getAnnotationIDAndType.executeQuery();
//		List<Integer> entitiesID = new ArrayList<Integer>();
//		List<Integer> eventsID = new ArrayList<Integer>();
//		while(rs.next())
//		{
//			int annotationID = rs.getInt(1);
//			String type = rs.getString(2);
//			if(type.equals("ner"))
//			{
//				entitiesID.add(annotationID);
//			}
//			else if(type.equals("re"))
//			{
//				eventsID.add(annotationID);
//			}
//		}
//		rs.close();
//		getAnnotationIDAndType.close();
//		List<IEntityAnnotation> entities = getEntitiesAnnotations(entitiesID);
//		for(IEntityAnnotation ent:entities)
//		{
//			result.put(ent.getID(), ent);
//		}
//		List<IEventAnnotation> events = getEventAnnotations(eventsID);
//		for(IEventAnnotation ev:events)
//		{
//			result.put(ev.getID(), ev);
//		}
//		return result;
//	}

//	private static List<IEventAnnotation> getEventAnnotations(
//			List<Integer> eventsID) throws SQLException, DatabaseLoadDriverException {
//		Map<Integer, IEventAnnotation> events = getEvent(eventsID);		
//		getEventEntities(events);
//		getEventProperties(events);
//		return new ArrayList<>(events.values());
//	}
//	
//	private static void getEventProperties(Map<Integer, IEventAnnotation> events) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement getAllAnnotationsPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectEventPropertiesByID);
//		ResultSet rs = null;
//		String key,value;
//		for(IEventAnnotation ev:events.values())
//		{
//			getAllAnnotationsPS.setInt(1, ev.getID());
//			rs = getAllAnnotationsPS.executeQuery();
//			while(rs.next())
//			{
//				key = rs.getString(2);
//				value = rs.getString(3);
//				ev.addEventProperty(key, value);
//			}
//		}
//		if(rs!=null)
//			rs.close();
//		getAllAnnotationsPS.close();
//	}
	
//	private static void getEventEntities(Map<Integer, IEventAnnotation> events) throws SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement getAllAnnotationsPS =  Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectEventEntitiesAnnotationsByID);
//		PreparedStatement getAllEntitiesAnnotationsPS =  Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.searchEntityAnnotationbyID);
//		ResultSet rs = null;
//		int entityID;
//		String lor;
//		IEntityAnnotation entAnnot;
//		for(IEventAnnotation ev:events.values())
//		{
//			getAllAnnotationsPS.setInt(1, ev.getID());
//			rs = getAllAnnotationsPS.executeQuery();
//			while(rs.next())
//			{
//				entityID = rs.getInt(2);
//				lor = rs.getString(3);
//				entAnnot = getEntity(getAllEntitiesAnnotationsPS, entityID);
//				if(entAnnot!=null)
//				{
//					if(lor.equals("left"))
//					{
//						ev.addEntityAtLeft(entAnnot);
//					}
//					else
//					{
//						ev.addEntityAtRight(entAnnot);
//					}
//				}
//			}
//		}
//		if(rs!=null)
//			rs.close();
//		getAllAnnotationsPS.close();
//		getAllEntitiesAnnotationsPS.close();
//
//	}

//	private static EntityAnnotation getEntity(PreparedStatement getAllAnnotationsPS,
//			int entityID) throws SQLException {
//		int start;
//		int end;
//		int classID;
//		int resourceID;
//		String value;
//		String normValue;
//		int id;
//		getAllAnnotationsPS.setInt(1, entityID);
//		ResultSet rs = getAllAnnotationsPS.executeQuery();
//		if(rs.next())
//		{
//			id = rs.getInt(1);
//			start = rs.getInt(2);
//			end = rs.getInt(3);
//			value = rs.getString(4);
//			resourceID = rs.getInt(5);
//			normValue = rs.getString(6);
//			classID = rs.getInt(7);
//			rs.close();
//			return new EntityAnnotation(id, start, end, classID,resourceID, value,normValue);
//		}
//		return null;
//	}
//
//	private static Map<Integer, IEventAnnotation> getEvent(List<Integer> eventsID) throws SQLException,
//			DatabaseLoadDriverException {
//		Map<Integer, IEventAnnotation> eventsAnnot = new HashMap<Integer, IEventAnnotation>();
//		IEventAnnotation entAnnot;	
//		PreparedStatement getAllAnnotationsPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectEventAnnotationsByID);
//		ResultSet rs = null;
//		int id;
//		long start,end;
//		String clue,classification;
//		for(Integer entID:eventsID)
//		{
//			getAllAnnotationsPS.setInt(1, entID);
//			rs = getAllAnnotationsPS.executeQuery();
//			if(rs.next())
//			{
//				id = rs.getInt(1);
//				start = rs.getLong(2);
//				end = rs.getLong(3);
//				clue = rs.getString(4);
//				classification = rs.getString(5);
//				entAnnot = new EventAnnotation(id, start, end,GlobalNames.re,new ArrayList<IEntityAnnotation>(),new ArrayList<IEntityAnnotation>(), clue,-1,classification,new EventProperties());
//				eventsAnnot.put(id, entAnnot);
//			}
//		}
//		if(rs!=null)
//			rs.close();
//		getAllAnnotationsPS.close();
//		return eventsAnnot;
//	}
//
//	private static List<IEntityAnnotation> getEntitiesAnnotations(List<Integer> entitiesID) throws SQLException, DatabaseLoadDriverException {
//		List<IEntityAnnotation> result = new ArrayList<IEntityAnnotation>();
//		PreparedStatement getAllAnnotationsPS =  Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.searchEntityAnnotationbyID);
//		int id,start,end,classID,resourceID;
//		String value,normValue;
//		ResultSet rs = null;
//		IEntityAnnotation entAnnot;
//		for(Integer entID : entitiesID)
//		{
//			getAllAnnotationsPS.setInt(1, entID);
//			rs = getAllAnnotationsPS.executeQuery();
//			if(rs.next())
//			{
//				id = rs.getInt(1);
//				start = rs.getInt(2);
//				end = rs.getInt(3);
//				value = rs.getString(4);
//				resourceID = rs.getInt(5);
//				normValue = rs.getString(6);
//				classID = rs.getInt(7);
//				entAnnot = new EntityAnnotation(id, start, end, classID,resourceID, value,normValue);
//				result.add(entAnnot);
//			}
//		}
//		if(rs!=null)
//			rs.close();
//		getAllAnnotationsPS.close();
//		return result;
//	}


}
