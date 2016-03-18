package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.corpora;


public class AnnotationsLogAccess {

//	private static final int enumTypeToDatabase = 1;
//	private static PreparedStatement addNewDocumentLog = null;
//
//	public static SortedSet<IAnnotationLog> getDocumentLogs(IIEProcess process, IPublication document) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		SortedSet<IAnnotationLog> annotationLogs = new TreeSet<IAnnotationLog>();
//		AnnotationLog log;
//		PreparedStatement getAllDocumentLogs = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectDocumentLogAnnotations);
//		getAllDocumentLogs.setInt(1, process.getCorpus().getId());
//		getAllDocumentLogs.setInt(2, process.getID());
//		getAllDocumentLogs.setLong(3, document.getId());
//		ResultSet rs = getAllDocumentLogs.executeQuery();
//		while (rs.next()) {
//			Date date = rs.getTimestamp(10);
//			AnnotationLogTypeEnum type = AnnotationLogTypeEnum.valueOf(rs.getString(6));
//			log = new AnnotationLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), type, rs.getString(7), rs.getString(8), rs.getString(9), date);
//			annotationLogs.add(log);
//		}
//		rs.close();
//		getAllDocumentLogs.close();
//		return annotationLogs;
//	}
//
//	public static SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		SortedSet<IAnnotationLog> annotationLogs = new TreeSet<IAnnotationLog>();
//		IAnnotationLog log;
//		PreparedStatement getAllDocumentLogs = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectProcessLogAnnotations);
//		getAllDocumentLogs.setInt(1, process.getCorpus().getId());
//		getAllDocumentLogs.setInt(2, process.getID());
//		ResultSet rs = getAllDocumentLogs.executeQuery();
//		while (rs.next()) {
//			Date date = rs.getTimestamp(10);
//			AnnotationLogTypeEnum type = AnnotationLogTypeEnum.valueOf(rs.getString(6));
//			log = new AnnotationLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), type, rs.getString(7), rs.getString(8), rs.getString(9), date);
//			annotationLogs.add(log);
//		}
//		rs.close();
//		getAllDocumentLogs.close();
//		return annotationLogs;
//	}
//
//	public static void addDocumentLog(IAnnotationLog annotationLog) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (addNewDocumentLog == null || addNewDocumentLog.isClosed()) {
//			addNewDocumentLog = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.addAnnotationLog);
//		}
//		if (annotationLog.getAnnotationLogID() <= 0) {
//			addNewDocumentLog.setNull(1, annotationLog.getAnnotationLogID());
//		} else {
//			addNewDocumentLog.setInt(1, annotationLog.getAnnotationLogID());
//		}
//		addNewDocumentLog.setInt(2, annotationLog.getCorpusID());
//		addNewDocumentLog.setInt(3, annotationLog.getProcessID());
//		addNewDocumentLog.setInt(4, annotationLog.getDocumentID());
//		// Enum starts with 0 and databse enum stars with 1
//		addNewDocumentLog.setInt(5, annotationLog.getType().ordinal() + enumTypeToDatabase);
//		addNewDocumentLog.setNString(6, annotationLog.getOldString());
//		addNewDocumentLog.setNString(7, annotationLog.getNewString());
//		addNewDocumentLog.setNString(8, annotationLog.getNotes());
//		// Change Date Java to SQL date
//		addNewDocumentLog.setNString(9, Utils.SimpleDataFormat.format(annotationLog.getDate()));
//		addNewDocumentLog.execute();
//	}
//
//	public static Map<Long, IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		Map<Long, IAnnotation> result = new HashMap<Long, IAnnotation>();
//		PreparedStatement getAnnotationIDAndType = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectAnnotationIDForAnnotaionLogs);
//		getAnnotationIDAndType.setInt(1, ieProcess.getCorpus().getId());
//		getAnnotationIDAndType.setInt(2, ieProcess.getID());
//		ResultSet rs = getAnnotationIDAndType.executeQuery();
//		List<Long> entitiesID = new ArrayList<Long>();
//		List<Long> eventsID = new ArrayList<Long>();
//		while (rs.next()) {
//			Long annotationID = rs.getLong(1);
//			String type = rs.getString(2);
//			if (type.equals("ner")) {
//				entitiesID.add(annotationID);
//			} else if (type.equals("re")) {
//				eventsID.add(annotationID);
//			}
//		}
//		rs.close();
//		getAnnotationIDAndType.close();
//		List<IEntityAnnotation> entities = getEntitiesAnnotations(entitiesID);
//		for (IEntityAnnotation ent : entities) {
//			result.put((long) ent.getID(), ent);
//		}
//		List<IEventAnnotation> events = getEvent(eventsID);
//		for (IEventAnnotation ev : events) {
//			result.put((long) ev.getID(), ev);
//		}
//		return result;
//	}
//
//	public static List<IEntityAnnotation> getEntitiesAnnotations(List<Long> entitiesID) throws SQLException, DatabaseLoadDriverException {
//
//		List<IEntityAnnotation> result = new ArrayList<IEntityAnnotation>();
//		PreparedStatement getAllAnnotationsPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.searchEntityAnnotationbyID);
//		int id, start, end, classID, resourceID;
//		String value, normValue;
//		ResultSet rs = null;
//		IEntityAnnotation entAnnot;
//		for (Long entID : entitiesID) {
//			getAllAnnotationsPS.setLong(1, entID);
//			rs = getAllAnnotationsPS.executeQuery();
//			if (rs.next()) {
//				id = rs.getInt(1);
//				start = rs.getInt(2);
//				end = rs.getInt(3);
//				value = rs.getString(4);
//				resourceID = rs.getInt(5);
//				normValue = rs.getString(6);
//				classID = rs.getInt(7);
//				entAnnot = new EntityAnnotation(id, start, end, classID, resourceID, value, normValue);
//				result.add(entAnnot);
//			}
//		}
//		if (rs != null)
//			rs.close();
//		getAllAnnotationsPS.close();
//		return result;
//	}
//
//	public static List<IEventAnnotation> getEvent(List<Long> eventsID) throws SQLException, DatabaseLoadDriverException {
//
//		List<IEventAnnotation> eventsAnnot = new ArrayList<>();
//		IEventAnnotation evAnnot;
//		PreparedStatement getAllAnnotationsPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.selectEventAnnotationsByID);
//		ResultSet rs = null;
//		Long id;
//		long start, end;
//		String clue, classification;
//		for (Long entID : eventsID) {
//			getAllAnnotationsPS.setLong(1, entID);
//			rs = getAllAnnotationsPS.executeQuery();
//			if (rs.next()) {
//				id = rs.getLong(1);
//				start = rs.getLong(2);
//				end = rs.getLong(3);
//				clue = rs.getString(4);
//				classification = rs.getString(5);
//				evAnnot = new EventAnnotation((int) (long) id, start, end, GlobalNames.re, new ArrayList<IEntityAnnotation>(), new ArrayList<IEntityAnnotation>(), clue, -1, classification, new EventProperties());
//				eventsAnnot.add(evAnnot);
//			}
//		}
//		if (rs != null)
//			rs.close();
//		getAllAnnotationsPS.close();
//		return eventsAnnot;
//	}
}
