package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.corpora;


public class AnnotationAccess {

//	private static PreparedStatement removeEntityAnnotationPS;
//	private static PreparedStatement insertEntityAnnotationPS;
//	private static PreparedStatement updateClassAnnotationPS;
//	private static PreparedStatement insertEventAnnot;
//	private static PreparedStatement insertAnnotSide;
//	private static PreparedStatement insertEventProperty;
//	private static PreparedStatement cleanRelationSide;
//	private static PreparedStatement cleanRelationProperties;
//
//
//	public static Map<Long, IEventAnnotation> getEvents(IAnnotatedDocument annotedDocument) throws DaemonException, DatabaseLoadDriverException, SQLException {
//		Map<Long, IEventAnnotation> eventsAnnot = new HashMap<Long, IEventAnnotation>();
//		Connection conn = Configuration.getDatabase().getConnection();
//		IEventAnnotation entAnnot;
//		PreparedStatement getAllAnnotationsPS;
//		getAllAnnotationsPS = conn.prepareStatement(QueriesAnnotatedDocument.selectEventAnnotations);
//		getAllAnnotationsPS.setInt(1, annotedDocument.getProcess().getID());
//		getAllAnnotationsPS.setInt(2, annotedDocument.getCorpus().getId());
//		getAllAnnotationsPS.setLong(3, annotedDocument.getId());
//		ResultSet rs = getAllAnnotationsPS.executeQuery();
//		long start, end, id;
//		String clue, classification;
//		while (rs.next()) {
//			id = rs.getLong(1);
//			start = rs.getLong(2);
//			end = rs.getLong(3);
//			clue = rs.getString(4);
//			classification = rs.getString(5);
//			entAnnot = new EventAnnotation((int) id, start, end, GlobalNames.re, new ArrayList<IEntityAnnotation>(), new ArrayList<IEntityAnnotation>(), clue, -1, classification, new EventProperties());
//			eventsAnnot.put(id, entAnnot);
//		}
//		rs.close();
//		getAllAnnotationsPS.close();
//		return eventsAnnot;
//	}
//
//
//
//	public static void addRelationsDB(IEventAnnotation event, IIEProcess process, IPublication document) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertEventAnnot == null || insertEventAnnot.isClosed())
//			insertEventAnnot = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertEventAnnotation);
//
//		Long nextAnnotation = GenerateRandomId.generateID();
//		insertEventAnnot.setInt(1, process.getID());
//		insertEventAnnot.setInt(2, process.getCorpus().getId());
//		insertEventAnnot.setLong(3, document.getId());
//		insertEventAnnot.setLong(4,event.getStartOffset());
//		insertEventAnnot.setLong(5,event.getEndOffset());
//		insertEventAnnot.setNull(6, 1);
//		insertEventAnnot.setNull(7, 1);
//		insertEventAnnot.setNull(8, 1);
//		insertEventAnnot.setNull(9, 1);
//		insertEventAnnot.setNull(10, 1);
//		insertEventAnnot.setNString(11, event.getEventClue());
//		insertEventAnnot.execute();
//		List<IEntityAnnotation> left = event.getEntitiesAtLeft();
//		List<IEntityAnnotation> right = event.getEntitiesAtRight();
//
//		insertentitiesAtLeft(nextAnnotation, left);
//		insertentitiesAtRight(nextAnnotation, right);
//		IEventProperties prop = event.getEventProperties();
//		insertRelationsProperties(nextAnnotation, prop);
//
//	}
//
//	public static void removeAnnotation(IAnnotation annotation) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (removeEntityAnnotationPS == null || removeEntityAnnotationPS.isClosed())
//			removeEntityAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeAnnotation);
//
//		removeEntityAnnotationPS.setInt(1, annotation.getID());
//		removeEntityAnnotationPS.execute();
//	}
//
//	public static void updateEntityAnnotations(List<IEntityAnnotation> list, int classID, IIEProcess process, IPublication document) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (updateClassAnnotationPS == null || updateClassAnnotationPS.isClosed())
//			updateClassAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.updateClassAnnotation);
//
//		updateClassAnnotationPS.setInt(4, process.getCorpus().getId());
//		updateClassAnnotationPS.setInt(5, process.getID());
//		updateClassAnnotationPS.setLong(6, document.getId());
//		updateClassAnnotationPS.setInt(1, classID);
//		for (IEntityAnnotation pos : list) {
//			updateClassAnnotationPS.setInt(2, (int) pos.getStartOffset());
//			updateClassAnnotationPS.setInt(3, (int) pos.getEndOffset());
//			updateClassAnnotationPS.execute();
//		}
//	}
//
//	public static void addEntityAnnotation(List<IEntityAnnotation> entities, IIEProcess process, IPublication document, IResourceElement element, String term, int classeID) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertEntityAnnotationPS == null || insertEntityAnnotationPS.isClosed())
//			insertEntityAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertEntityAnnotation);
//
//		insertEntityAnnotationPS.setInt(1, process.getID());
//		insertEntityAnnotationPS.setInt(2, process.getCorpus().getId());
//		insertEntityAnnotationPS.setLong(3, document.getId());
//
//		for (IEntityAnnotation ent : entities) {
//			insertEntityAnnotationPS.setInt(4, (int) ent.getStartOffset());
//			insertEntityAnnotationPS.setInt(5, (int) ent.getEndOffset());
//			insertEntityAnnotationPS.setNString(6, ent.getAnnotationValue());
//			if (ent.getResourceElementID() > 0) {
//				insertEntityAnnotationPS.setInt(7, ent.getResourceElementID());
//			} else {
//				insertEntityAnnotationPS.setNull(7, ent.getResourceElementID());
//			}
//			insertEntityAnnotationPS.setNString(8, NormalizationForm.getNormalizationForm(ent.getAnnotationValue()));
//			insertEntityAnnotationPS.setInt(9, ent.getClassAnnotationID());
//			insertEntityAnnotationPS.execute();
//		}
//		insertEntityAnnotationPS.close();
//	}
//
//	public static void editRelationsDB(IEventAnnotation eventToEdit) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		cleanRelationSide(eventToEdit);
//		clenaRelationProperties(eventToEdit);
//		List<IEntityAnnotation> left = eventToEdit.getEntitiesAtLeft();
//		List<IEntityAnnotation> right = eventToEdit.getEntitiesAtRight();
//		insertentitiesAtLeft((long) eventToEdit.getID(), left);
//		insertentitiesAtRight((long) eventToEdit.getID(), right);
//		IEventProperties prop = eventToEdit.getEventProperties();
//		insertRelationsProperties((long) eventToEdit.getID(), prop);
//	}
//
//	public static SortedSet<IRelationsType> getAllDefaultRelationsType() throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		SortedSet<IRelationsType> relationTypes = new TreeSet<IRelationsType>();
//		Set<Long> getLeftClassIDs = new HashSet<Long>();
//		Set<Long> getRightClassIDs = new HashSet<Long>();
//		PreparedStatement getSideClassID = Configuration.getDatabase().getConnection().prepareStatement(QueriesREToNetwork.getAllClassesSide);
//		getSideClassID.setString(1, "left");
//		ResultSet rs = getSideClassID.executeQuery();
//		while (rs.next()) {
//			getLeftClassIDs.add(rs.getLong(1));
//		}
//		getSideClassID.setString(1, "right");
//		rs = getSideClassID.executeQuery();
//		while (rs.next()) {
//			getRightClassIDs.add(rs.getLong(1));
//		}
//		rs.close();
//		getSideClassID.close();
//		for (Long leftClassID : getLeftClassIDs) {
//			for (Long rightClassID : getRightClassIDs) {
//				relationTypes.add(new RelationType((int) (long) leftClassID, (int) (long) rightClassID));
//			}
//		}
//		return relationTypes;
//	}
//
//	public static Set<String> getAllLemma() throws DaemonException, SQLException, DatabaseLoadDriverException {
//		Set<String> lemmas = new HashSet<String>();
//		PreparedStatement getLemmasPS;
//		getLemmasPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesREToNetwork.getAllLemmas);
//		ResultSet rs = getLemmasPS.executeQuery();
//		while (rs.next()) {
//			lemmas.add(rs.getString(1));
//		}
//		rs.close();
//		getLemmasPS.close();
//		return lemmas;
//	}
//
//	/*
//	 * -------- AUXILIAR METHODS TO USE ONLY IN THIS CLASS ---------
//	 */
//
//	private static void insertRelationsProperties(Long nextAnnotation, IEventProperties prop) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertEventProperty == null || insertEventProperty.isClosed())
//			insertEventProperty = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertAnnotationProperties);
//
//		insertEventProperty.setLong(1, nextAnnotation);
//		if (prop.getLemma() != null) {
//			insertEventProperty.setNString(2, GlobalNames.relationPropertyLemma);
//			insertEventProperty.setNString(3, prop.getLemma());
//			insertEventProperty.execute();
//		}
//		if (prop.getDirectionally() != null) {
//			insertEventProperty.setNString(2, GlobalNames.relationPropertyDirectionally);
//			insertEventProperty.setNString(3, String.valueOf(prop.getDirectionally().databaseValue()));
//			insertEventProperty.execute();
//		}
//		if (prop.getPolarity() != null) {
//			insertEventProperty.setNString(2, GlobalNames.relationPropertyPolarity);
//			insertEventProperty.setNString(3, String.valueOf(prop.getPolarity().databaseValue()));
//			insertEventProperty.execute();
//		}
//	}
//
//	private static void insertentitiesAtLeft(Long nextAnnotation, List<IEntityAnnotation> left) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertAnnotSide == null || insertAnnotSide.isClosed())
//			insertAnnotSide = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertAnnotationSide);
//
//		for (IEntityAnnotation annotation : left) {
//			insertAnnotSide.setLong(1, nextAnnotation);
//			insertAnnotSide.setInt(2, annotation.getID());
//			insertAnnotSide.setInt(3, 1);
//			insertAnnotSide.execute();
//		}
//	}
//
//	private static void insertentitiesAtRight(Long nextAnnotation, List<IEntityAnnotation> left) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertAnnotSide == null || insertAnnotSide.isClosed())
//			insertAnnotSide = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertAnnotationSide);
//
//		for (IEntityAnnotation annotation : left) {
//			insertAnnotSide.setLong(1, nextAnnotation);
//			insertAnnotSide.setInt(2, annotation.getID());
//			insertAnnotSide.setInt(3, 2);
//			insertAnnotSide.execute();
//		}
//	}
//
//	private static void clenaRelationProperties(IEventAnnotation eventToEdit) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (cleanRelationProperties == null || cleanRelationProperties.isClosed())
//			cleanRelationProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeAnnotationProperties);
//
//		cleanRelationProperties.setInt(1, eventToEdit.getID());
//		cleanRelationProperties.execute();
//	}
//
//	private static void cleanRelationSide(IEventAnnotation eventToEdit) throws DaemonException, SQLException, DatabaseLoadDriverException {
//		if (cleanRelationSide == null || cleanRelationSide.isClosed())
//			cleanRelationSide = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeRelationSide);
//
//		cleanRelationSide.setInt(1, eventToEdit.getID());
//		cleanRelationSide.execute();
//	}
}
