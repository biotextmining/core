package com.silicolife.textmining.core.datastructures.annotation.properties;

/**
 * A singleton class that manager class colors 
 * 
 * @author Hugo Costa
 *
 */
public class AnnotationColors {
	
//	private static Map<Long,AnnotationColorStyleProperty> tasks;
//	public static String defaultStyle = "";
//	
//	private static AnnotationColors _instance= null;
//	
//	
//	protected AnnotationColors() throws ANoteException
//	{
//		synchronized (AnnotationColors.class){
//			if (_instance == null) {
//					AnnotationColors.tasks = getClassColors();
//				_instance = this;
//			} else {
//				throw new RuntimeException("This is a singleton.");
//			}
//		}
//	}
//	
//	
//	/**
//	 * Creates the singleton instance.
//	 * @throws SQLException 
//	 * @throws ANoteException 
//	 * @throws DatabaseLoadDriverException 
//	 */
//	private static synchronized void createInstance() throws ANoteException {
//
//		if (_instance == null) {
//			_instance = new AnnotationColors();
//		}	
//	}
//
//	/**
//	 * Gives access to the Workbench instance
//	 * @return
//	 * @throws SQLException 
//	 * @throws ANoteException 
//	 * @throws DatabaseLoadDriverException 
//	 */
//	protected static AnnotationColors getInstance() throws ANoteException {
//		if (_instance == null) {
//			AnnotationColors.createInstance();
//		}
//		return _instance;
//	}
//	
//	public static AnnotationColors getInstanceWithoutAIBench() throws ANoteException {
//		if (_instance == null) {
//			AnnotationColors.createInstance();
//		}
//		return _instance;
//	}
//	
//	private static Map<Long, AnnotationColorStyleProperty> getClassColors() throws ANoteException
//	{
//		Map<Long, AnnotationColorStyleProperty> classProperties = new HashMap<Long, AnnotationColorStyleProperty>();
//		String color,classe;
//		for(Long classID:ClassProperties.getIDsSet())
//		{
//			color = getColorForClass(classID);
//			classe = ClassProperties.getClassNameGivenClassID(classID);
//			classProperties.put(classID,new AnnotationColorStyleProperty(classe,color,""));
//		}
//		return classProperties;
//	}
//	
//	private static String getColorForClass(long classID) throws ANoteException
//	{
//		String color = getDefaultColor();
//		{
////			PreparedStatement getcolorPS = GlobalOptions.database.getConnection().prepareStatement(QueriesGeneral.selectClassColor);
////			getcolorPS.setLong(1, classID);
////			ResultSet rs = getcolorPS.executeQuery();
////			if(rs.next())
////			{
////				color =  rs.getString(1);
////			}
////			else
////			{
////				color = insertDatabseColor(classID);	
////			}
////			rs.close();
////			getcolorPS.close();
//		}
//		return color;
//	}
//	
////	protected static String insertDatabseColor(long classID)throws SQLException {
////		String color = Utils.randomAlphaNumColor(6);
////		PreparedStatement putcolor = GlobalOptions.database.getConnection().prepareStatement(QueriesGeneral.insertClassColor);
////		putcolor.setLong(1, classID);
////		putcolor.setNString(2, color);
////		putcolor.execute();
////		putcolor.close();
////		return color;
////	}
//	
//	protected static AnnotationColorStyleProperty getClassColor(long classID) throws SQLException, ANoteException
//	{
//		if(getTasks().containsKey(classID))
//		{
//			return getTasks().get(classID);
//		}
//		else
//		{
//			String color = getDefaultColor();
//			try {
//				color = insertDatabseColor(classID);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			String className = ClassProperties.getClassIDClass().get(classID);
//			AnnotationColorStyleProperty col = new AnnotationColorStyleProperty(className,color, defaultStyle);
//			getTasks().put(classID,col);
//			return col;
//		}
//	}	
//
//	public static AnnotationColorStyleProperty getClassColor2(long classID) throws ANoteException, SQLException
//	{
//		return getClassColor(classID);
//	} 
//
//	protected static void updateColor(int classID,String newColor) throws SQLException, ANoteException {
//		PreparedStatement updatecolorPS = GlobalOptions.database.getConnection().prepareStatement(QueriesGeneral.updateColor);
//		updatecolorPS.setNString(1, newColor);
//		updatecolorPS.setInt(2, classID);
//		updatecolorPS.execute();
//		updatecolorPS.close();
//		getTasks().get(classID).setColor(newColor);
//	}
//
//	public static String getDefaultColor() {
//		return defaultColor;
//	}
//
//	protected static Map<Long,AnnotationColorStyleProperty> getTasks() throws ANoteException, SQLException {
//		if(tasks==null)
//		{
//			tasks = getClassColors();
//		}
//		return tasks;
//	}
//	


}
