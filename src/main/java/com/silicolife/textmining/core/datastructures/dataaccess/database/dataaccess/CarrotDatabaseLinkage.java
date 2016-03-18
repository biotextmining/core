package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess;


public class CarrotDatabaseLinkage {
	
	
//
//	public static int clusterProcessRegistry(String string) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement insertCluster = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertCluster);
//		insertCluster.setString(1, string);
//		insertCluster.execute();
//		insertCluster.close();
//		return HelpDatabase.getNextInsertTableID(GlobalTablesName.cluster)-1;
//	}
//
//	public static void clusterProcessPropertiesRegistry(int clusterID,Properties properties) throws SQLException, DatabaseLoadDriverException {
//		if(properties!=null)
//		{
//			PreparedStatement insertClusterProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterProperties);
//			insertClusterProperties.setInt(1, clusterID);
//			for(Object prop:properties.keySet())
//			{
//				insertClusterProperties.setString(2, prop.toString());
//				insertClusterProperties.setString(3, properties.get(prop).toString());
//				insertClusterProperties.execute();
//			}
//			insertClusterProperties.close();
//		}
//	}
//
//	public static void clusterProcessLabelsRegistry(long queryID, int clusterID,List<Cluster> clusters) throws SQLException, DatabaseLoadDriverException {
//		int labelID = HelpDatabase.getNextInsertTableID( GlobalTablesName.clusterLabels);
//		PreparedStatement insertLabel = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabel);
//		PreparedStatement insertLabelDocument = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabelDocument);
//		PreparedStatement insertLabelProcess = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabelProcess);
//
//		insertLabelDocument.setLong(3, queryID);
//		for(Cluster cl:clusters)
//		{
//			Double score = cl.getScore();
//			String label = cl.getLabel();
//			clusterLabelRegistry(insertLabel,label,score);
//			clusterLabelProcessRegistry(insertLabelProcess,labelID,clusterID);
//			clusterLabelDocumentsRegistry(insertLabelDocument,labelID,cl);
//			labelID++;
//		}
//		insertLabel.close();
//		insertLabelDocument.close();
//		insertLabelProcess.close();
//	}
//
//	private static void clusterLabelProcessRegistry(PreparedStatement insertLabelProcess, int labelID, int clusterID) throws SQLException {
//		insertLabelProcess.setInt(1, labelID);
//		insertLabelProcess.setInt(2, clusterID);
//		insertLabelProcess.execute();
//	}
//
//	private static void clusterLabelDocumentsRegistry(PreparedStatement insertLabelDocument, int labelID, Cluster cl) throws SQLException {
//		for(Document doc : cl.getDocuments())
//		{
//			int docID = Integer.parseInt(doc.getContentUrl());
//			insertLabelDocument.setInt(1, labelID);
//			insertLabelDocument.setInt(2, docID);
//			insertLabelDocument.execute();
//		}
//		
//	}
//
//	private static void clusterLabelRegistry(PreparedStatement insertLabel,String label, Double score) throws SQLException {
//		insertLabel.setString(1,label);
//		if(score==null)
//			insertLabel.setDouble(2,0);
//		else
//			insertLabel.setDouble(2,score);
//		insertLabel.execute();
//		
//	}
//
//	public static void clusterProcessQueryRegistry(long queryID, int clusterID) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement insertClusterRegistryInQuery = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertQueryCluster);
//		insertClusterRegistryInQuery.setInt(1, clusterID);
//		insertClusterRegistryInQuery.setLong(2, queryID);
//		insertClusterRegistryInQuery.execute();
//		insertClusterRegistryInQuery.close();
//	}
//	
//	public static List<IClusterProcess> getClustersForQuery(Query query) throws DatabaseLoadDriverException, SQLException
//	{
//		List<IClusterProcess> listClusters = new ArrayList<IClusterProcess>();
//		PreparedStatement getQueryClusters;
//		getQueryClusters = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectQueryClusters);
//		PreparedStatement getClusterProperties =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterProperties);
//		PreparedStatement getClusterLabels =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabels);
//		PreparedStatement getClusterLabelsDocuments =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabelsDocuments);
//		getQueryClusters.setLong(1, query.getId());
//		ResultSet rs = getQueryClusters.executeQuery();
//		while(rs.next())
//		{
//			int clusterProcessID = rs.getInt(1);
//			String description = rs.getString(2);
//			Properties prop = getClusterproperties(getClusterProperties,clusterProcessID);
//			List<IClusterLabel> clusterLabels = getClusterLabels(clusterProcessID,getClusterLabels,getClusterLabelsDocuments);
//			IClusterProcess cluster = new ClusterProcess(clusterProcessID, prop, description, clusterLabels);
//			listClusters.add(cluster);
//		}
//		rs.close();
//		getQueryClusters.close();
//		getClusterLabels.close();
//		getClusterLabelsDocuments.close();
//		return listClusters;
//	}
//	
//	public static IClusterProcess getClusteringByID(int clusteringID) throws SQLException, DatabaseLoadDriverException
//	{
//		PreparedStatement getQueryClusterID =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectQueryClustersID);
//		PreparedStatement getClusterProperties =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterProperties);
//		PreparedStatement getClusterLabels =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabels);
//		PreparedStatement getClusterLabelsDocuments =  Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabelsDocuments);
//		getQueryClusterID.setInt(1, clusteringID);
//		IClusterProcess cluster = null;
//		ResultSet rs = getQueryClusterID.executeQuery();
//		if(rs.next())
//		{
//			String description = rs.getString(2);
//			Properties prop = getClusterproperties(getClusterProperties,clusteringID);
//			List<IClusterLabel> clusterLabels = getClusterLabels(clusteringID,getClusterLabels,getClusterLabelsDocuments);
//			cluster = new ClusterProcess(clusteringID, prop, description, clusterLabels);
//		}
//		rs.close();
//		getQueryClusterID.close();
//		getClusterProperties.close();
//		getClusterLabels.close();
//		getClusterLabelsDocuments.close();
//		return cluster;
//	}
//
//	private static List<IClusterLabel> getClusterLabels(int clusterID,PreparedStatement getClusterLabels,PreparedStatement getClusterLabelsDocuments) throws SQLException {
//		List<IClusterLabel> clusterLabels = new ArrayList<IClusterLabel>();
//		getClusterLabels.setInt(1, clusterID);
//		ResultSet rs = getClusterLabels.executeQuery();
//		while(rs.next())
//		{
//			int labelID = rs.getInt(1);
//			String labelName = rs.getString(2);
//			Double score = rs.getDouble(3);
//			List<Integer> documentsIDs = getLabelDocuments(getClusterLabelsDocuments,labelID);
//			IClusterLabel	 label = new ClusterLabel(labelID, labelName, score, documentsIDs);
//			clusterLabels.add(label);
//		}
//		rs.close();
//		return clusterLabels;
//	}
//
//	private static List<Integer> getLabelDocuments(PreparedStatement getClusterLabelsDocuments, int labelID) throws SQLException {
//		List<Integer> documentsIDs = new ArrayList<Integer>();
//		getClusterLabelsDocuments.setInt(1, labelID);
//		ResultSet rs = getClusterLabelsDocuments.executeQuery();
//		while(rs.next())
//		{
//			int docID = rs.getInt(1);
//			documentsIDs.add(docID);
//		}
//		rs.close();
//		return documentsIDs;
//	}
//
//	private static Properties getClusterproperties(PreparedStatement getClusterProperties, int clusterID) throws SQLException {
//		Properties properties = new Properties();
//		getClusterProperties.setInt(1, clusterID);
//		ResultSet rs = getClusterProperties.executeQuery();
//		while(rs.next())
//		{
//			String key = rs.getString(1);
//			String value = rs.getString(2);
//			properties.put(key, value);
//		}
//		rs.close();
//		return properties;
//	}
//
//	public static void removeClusterProcess(IClusterProcess cluster) throws SQLException, DatabaseLoadDriverException {
//		PreparedStatement insertCluster = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.inativeClusterProcess);
//		insertCluster.setInt(1, cluster.getId());
//		insertCluster.execute();
//		insertCluster.close();
//	}
//
//	public static IQuery createQuery(Query query,IClusterProcess clusters, List<IClusterLabel> labels) throws SQLException, DatabaseLoadDriverException, DaemonException {
//		List<IPublication> pubs = new ArrayList<IPublication>();
//		Map<Long,IPublication> pubsMap = new HashMap<Long, IPublication>();
//		int availableAbstracts = 0;
//		Properties properties = new Properties();
//		properties.put("font", "Query Generates by Clustering results in Query "+query.getId()+" and Clustering "+clusters.getId());
//		String labelsSTR = new String();
//		for(IPublication pub :query.getPublications())
//		{
//			pubsMap.put(pub.getId(), pub);
//		}
//		for(IClusterLabel label:labels)
//		{
//			labelsSTR = labelsSTR + label.getName() + ", ";
//			for(long docID : label.getLabelDocumentsID())
//			{
//				if(pubs.contains(docID))
//				{
//					IPublication pub = pubsMap.get(docID);
//					pubs.add(pub);
//					pubsMap.remove(docID);
//					if(!pub.getAbstractSection().isEmpty())
//						availableAbstracts++;
//				}
//			}
//		}
//		properties.put("lables", labelsSTR.substring(0, labelsSTR.length()-2));
//		Date date = new Date();
//		String name = "Query Generates by Clustering results in Query "+query.getId()+" and Clustering "+clusters.getId();	
//		IQueryOriginType quetyOriginType = new QueryOriginType("CarrotClustering");
//		IQuery queryToCreate = new Query(quetyOriginType, date, "", "", "", pubs.size(), availableAbstracts, name, "", properties);
//		Configuration.getDataAccess().createQuery(queryToCreate);
//		Configuration.getDataAccess().addQueryPublications(queryToCreate, pubs);
//		return queryToCreate;
//	}
	

}
