package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess;



public class AnnotatedDocumentDataBaseManagement {
			
//	private PreparedStatement removeEntityAnnotationPS;
//	private PreparedStatement insertEntityAnnotationPS;
//	private PreparedStatement updateClassAnnotationPS;
//	private PreparedStatement insertEventAnnot;
//	private PreparedStatement insertAnnotSide;
//	private PreparedStatement insertEventProperty;
//	private PreparedStatement cleanRelationSide;
//	private PreparedStatement cleanRelationProperties;
//	
//	public AnnotatedDocumentDataBaseManagement(IPublication document,IProcess process, ICorpus corpus) throws SQLException, DatabaseLoadDriverException
//	{
//		super(document,process,corpus);
//		initPS();
//	}
//	
//	public AnnotatedDocumentDataBaseManagement(IAnnotatedDocument annotatedDocument) throws SQLException, DatabaseLoadDriverException {
//		this(annotatedDocument, annotatedDocument.getProcess(), annotatedDocument.getCorpus());
//	}
//
//	private void initPS() throws SQLException, DatabaseLoadDriverException {
//		//Both
//		removeEntityAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeAnnotation);
//		// Entities
//		insertEntityAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertEntityAnnotation);
//		insertEntityAnnotationPS.setInt(1,getProcess().getID());
//		insertEntityAnnotationPS.setInt(2,getCorpus().getID());
//		insertEntityAnnotationPS.setLong(3,getID());
//		updateClassAnnotationPS = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.updateClassAnnotation);
//		updateClassAnnotationPS.setInt(4,getCorpus().getID());
//		updateClassAnnotationPS.setInt(5,getProcess().getID());
//		updateClassAnnotationPS.setLong(6,getID());
//		// relations
//		insertEventAnnot = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertEventAnnotation);
//		insertEventAnnot.setInt(1,getProcess().getID());
//		insertEventAnnot.setInt(2,getCorpus().getID());
//		insertEventAnnot.setLong(3,getID());
//		insertAnnotSide = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertAnnotationSide);
//		insertEventProperty = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.insertAnnotationProperties);
//		cleanRelationProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeAnnotationProperties);
//		cleanRelationSide = Configuration.getDatabase().getConnection().prepareStatement(QueriesAnnotatedDocument.removeRelationSide);
//	}
//
//	
//	public void addRelationsDB(IEventAnnotation event) throws SQLException, DatabaseLoadDriverException {
//		int nextAnnotation = HelpDatabase.getNextInsertTableID(DatabaseTablesName.annotation);
//		insertEventAnnot.setInt(4, (int) event.getStartOffset());
//		insertEventAnnot.setInt(5, (int) event.getEndOffset());
//		insertEventAnnot.setNull(6,1);
//		insertEventAnnot.setNull(7,1);
//		insertEventAnnot.setNull(8,1);
//		insertEventAnnot.setNull(9,1);
//		insertEventAnnot.setNull(10,1);
//		insertEventAnnot.setNString(11,event.getEventClue());
//		insertEventAnnot.execute();
//		List<IEntityAnnotation> left = event.getEntitiesAtLeft();
//		List<IEntityAnnotation> right = event.getEntitiesAtRight();
//		insertentitiesAtLeft(nextAnnotation,left);
//		insertentitiesAtRight(nextAnnotation,right);
//		IEventProperties prop = event.getEventProperties();
//		insertRelationsProperties(nextAnnotation,prop);
//	}
//	
//	private void insertRelationsProperties(int nextAnnotation, IEventProperties prop) throws SQLException {
//		insertEventProperty.setInt(1,nextAnnotation);
//		if(prop.getLemma()!=null)
//		{
//			insertEventProperty.setNString(2,GlobalNames.relationPropertyLemma);
//			insertEventProperty.setNString(3,prop.getLemma());
//			insertEventProperty.execute();
//		}
//		if(prop.getDirectionally()!=null)
//		{
//			insertEventProperty.setNString(2,GlobalNames.relationPropertyDirectionally);
//			insertEventProperty.setNString(3,String.valueOf(prop.getDirectionally().databaseValue()));
//			insertEventProperty.execute();
//		}
//		if(prop.getPolarity()!=null)
//		{
//			insertEventProperty.setNString(2,GlobalNames.relationPropertyPolarity);
//			insertEventProperty.setNString(3,String.valueOf(prop.getPolarity().databaseValue()));
//			insertEventProperty.execute();
//		}	
//	}
//
//	private void insertentitiesAtLeft(int nextAnnotation, List<IEntityAnnotation> left) throws SQLException {
//		for(IEntityAnnotation annotation:left)
//		{
//
//			insertAnnotSide.setInt(1,nextAnnotation);
//			insertAnnotSide.setInt(2,annotation.getID());
//			insertAnnotSide.setInt(3,1);
//			insertAnnotSide.execute();	
//		}
//	}
//	
//	private void insertentitiesAtRight(int nextAnnotation,List<IEntityAnnotation> left) throws SQLException {
//		for(IEntityAnnotation annotation:left)
//		{
//			insertAnnotSide.setInt(1,nextAnnotation);
//			insertAnnotSide.setInt(2,annotation.getID());
//			insertAnnotSide.setInt(3,2);
//			insertAnnotSide.execute();	
//		}
//		
//	}
//	public void removeAnnotation(int annotationID) throws SQLException
//	{
//		removeEntityAnnotationPS.setInt(1, annotationID);
//		removeEntityAnnotationPS.execute();
//	}
//	
//	public void addEntityAnnotation(AnnotationPosition pos,IEntityAnnotation elem) throws SQLException
//	{
//		insertEntityAnnotation(pos,elem.getResourceElementID(),elem.getAnnotationValue(),elem.getClassAnnotationID());	
//	}
//	
//	public void updateEntityAnnotations(List<AnnotationPosition> list, int classID) throws SQLException {
//		
//		updateClassAnnotationPS.setInt(1, classID);
//		for(AnnotationPosition pos:list)
//		{
//			updateClassAnnotationPS.setInt(2,pos.getStart());
//			updateClassAnnotationPS.setInt(3,pos.getEnd());
//			updateClassAnnotationPS.execute();
//		}
//	}	
//	
//	
//	private void insertEntityAnnotation(AnnotationPosition pos, int termID, String term,int classeID) throws SQLException {	
//		insertEntityAnnotationPS.setInt(4,pos.getStart());
//		insertEntityAnnotationPS.setInt(5,pos.getEnd());
//		insertEntityAnnotationPS.setNString(6,term);
//		if(termID==0)
//		{
//			insertEntityAnnotationPS.setNull(7,0);
//		}
//		else
//		{
//			insertEntityAnnotationPS.setInt(7,termID);
//		}
//		insertEntityAnnotationPS.setNString(8,NormalizationForm.getNormalizationForm(term));
//		insertEntityAnnotationPS.setInt(9,classeID);
//		insertEntityAnnotationPS.execute();
//	}
//
//	public void editRelationsDB(IEventAnnotation eventToEdit) throws SQLException {
//		cleanRelationSide(eventToEdit.getID());
//		clenaRelationProperties(eventToEdit.getID());
//		List<IEntityAnnotation> left = eventToEdit.getEntitiesAtLeft();
//		List<IEntityAnnotation> right = eventToEdit.getEntitiesAtRight();
//		insertentitiesAtLeft(eventToEdit.getID(),left);
//		insertentitiesAtRight(eventToEdit.getID(),right);
//		IEventProperties prop = eventToEdit.getEventProperties();
//		insertRelationsProperties(eventToEdit.getID(),prop);		
//	}
//
//	private void clenaRelationProperties(int relationID) throws SQLException {
//		cleanRelationProperties.setInt(1, relationID);
//		cleanRelationProperties.execute();
//	}
//
//	private void cleanRelationSide(int relationID) throws SQLException {
//		cleanRelationSide.setInt(1, relationID);
//		cleanRelationSide.execute();	
//	}


}
