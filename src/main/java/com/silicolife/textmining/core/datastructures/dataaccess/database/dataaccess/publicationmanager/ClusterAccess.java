package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.publicationmanager;


public class ClusterAccess {

//	public static PreparedStatement insertLabel = null;
//	public static PreparedStatement insertLabelDocument = null;
//	public static PreparedStatement insertLabelProcess = null;
//
//	public static IClustering clusterProcessRegistry(IClustering clustering) throws SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement insertCluster = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertCluster);
//		insertCluster.setLong(1, clustering.getId());
//		insertCluster.setString(2, clustering.getLabel());
//		insertCluster.execute();
//		insertCluster.close();
//
//		return clustering;
//	}
//
//	public static void clusterProcessPropertiesRegistry(IClustering clustering, Properties properties) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (properties != null) {
//			PreparedStatement insertClusterProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterProperties);
//			insertClusterProperties.setLong(1, clustering.getId());
//			for (Object prop : properties.keySet()) {
//				insertClusterProperties.setString(2, prop.toString());
//				insertClusterProperties.setString(3, properties.get(prop).toString());
//				insertClusterProperties.execute();
//			}
//			insertClusterProperties.close();
//		}
//	}
//
//	public static void clusterProcessLabelsRegistry(IQuery query, IClustering clustering, List<Cluster> clusters) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		List<ILabelCluster> labelClustersArray = new ArrayList<ILabelCluster>();
//
//		for (Cluster cl : clusters) {
//			Double score = cl.getScore();
//			String label = cl.getLabel();
//
//			ILabelCluster clusterLabel = new ClusterLabel(-1, label, score, null);
//			clusterLabel = clusterLabelRegistry(clusterLabel);
//			labelClustersArray.add(clusterLabel);
//
//			clusterLabelProcessRegistry(clustering);
//
//			clusterLabelDocumentsRegistry(query, clusterLabel, cl);
//
//		}
//
//		clustering.setClusterLabels(labelClustersArray);
//		clusterLabelProcessRegistry(clustering);
//
//		insertLabel.close();
//		insertLabelDocument.close();
//		insertLabelProcess.close();
//	}
//
//	public static void clusterLabelProcessRegistry(IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertLabelProcess == null || insertLabelProcess.isClosed())
//			insertLabelProcess = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabelProcess);
//
//		List<ILabelCluster> labels = clustering.getClustersLabels();
//
//		insertLabelProcess.setInt(2, clustering.getId());
//		for (ILabelCluster label : labels) {
//			insertLabelProcess.setInt(1, label.getId());
//			insertLabelProcess.execute();
//		}
//	}
//
//	public static void clusterLabelDocumentsRegistry(IQuery query, ILabelCluster label, Cluster cl) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertLabelDocument == null || insertLabelDocument.isClosed())
//			insertLabelDocument = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabelDocument);
//
//		insertLabelDocument.setLong(3, query.getId());
//		for (Document doc : cl.getDocuments()) {
//			int docID = Integer.parseInt(doc.getContentUrl());
//			insertLabelDocument.setLong(1, label.getId());
//			insertLabelDocument.setInt(2, docID);
//			insertLabelDocument.execute();
//		}
//
//	}
//
//	public static ILabelCluster clusterLabelRegistry(ILabelCluster label) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		if (insertLabel == null || insertLabel.isClosed())
//			insertLabel = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertClusterLabel);
//
//		insertLabel.setLong(1, label.getId());
//		insertLabel.setString(2, label.getLabelName());
//
//		if (label.getScore() == null)
//			insertLabel.setDouble(3, 0);
//		else
//			insertLabel.setDouble(4, label.getScore());
//
//		insertLabel.execute();
//
//		return label;
//	}
//
//	public static void clusterProcessQueryRegistry(IQuery query, IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement insertClusterRegistryInQuery = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.insertQueryCluster);
//		insertClusterRegistryInQuery.setInt(1, clustering.getId());
//		insertClusterRegistryInQuery.setLong(2, query.getId());
//		insertClusterRegistryInQuery.execute();
//		insertClusterRegistryInQuery.close();
//	}
//
//	public static List<IClustering> getClustersForQuery(IQuery query) throws DaemonException, DatabaseLoadDriverException, SQLException {
//
//		List<IClustering> listClusters = new ArrayList<IClustering>();
//		PreparedStatement getQueryClusters;
//		getQueryClusters = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectQueryClusters);
//
//		getQueryClusters.setLong(1, query.getId());
//		ResultSet rs = getQueryClusters.executeQuery();
//		while (rs.next()) {
//			int clusterProcessID = rs.getInt(1);
//			String description = rs.getString(2);
//			IClustering clustering = new ClusterProcess(clusterProcessID, null, description, null);
//			clustering.setPropeties(getClusterproperties(clustering));
//			clustering.setClusterLabels(getClusterLabels(clustering));
//			listClusters.add(clustering);
//		}
//		rs.close();
//		getQueryClusters.close();
//		return listClusters;
//	}
//
//	public static IClustering getClusteringByID(IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement getQueryClusterID = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectQueryClustersID);
//
//		getQueryClusterID.setInt(1, clustering.getId());
//		ResultSet rs = getQueryClusterID.executeQuery();
//		if (rs.next()) {
//			String description = rs.getString(2);
//
//			Properties prop = getClusterproperties(clustering);
//			List<ILabelCluster> clusterLabels = getClusterLabels(clustering);
//			clustering.setClusterLabels(clusterLabels);
//			clustering.setName(description);
//			clustering.setPropeties(prop);
//		}
//		rs.close();
//		getQueryClusterID.close();
//		return clustering;
//
//	}
//
//	public static void removeClusterProcess(IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//		PreparedStatement insertCluster = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.inativeClusterProcess);
//		insertCluster.setInt(1, clustering.getId());
//		insertCluster.execute();
//		insertCluster.close();
//	}
//
//	public static Properties getClusterproperties(IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement getClusterProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterProperties);
//
//		Properties properties = new Properties();
//		getClusterProperties.setInt(1, clustering.getId());
//		ResultSet rs = getClusterProperties.executeQuery();
//		while (rs.next()) {
//			String key = rs.getString(1);
//			String value = rs.getString(2);
//			properties.put(key, value);
//		}
//		rs.close();
//		getClusterProperties.close();
//		return properties;
//
//	}
//
//	public static List<Integer> getLabelDocuments(ILabelCluster label) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement getClusterLabelsDocuments = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabelsDocuments);
//
//		List<Integer> documentsIDs = new ArrayList<Integer>();
//		getClusterLabelsDocuments.setInt(1, label.getId());
//		ResultSet rs = getClusterLabelsDocuments.executeQuery();
//		while (rs.next()) {
//			int docID = rs.getInt(1);
//			documentsIDs.add(docID);
//		}
//		rs.close();
//		getClusterLabelsDocuments.close();
//
//		return documentsIDs;
//	}
//
//	public static List<ILabelCluster> getClusterLabels(IClustering clustering) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement getClusterLabels = Configuration.getDatabase().getConnection().prepareStatement(QueriesClusters.selectClusterLabels);
//
//		List<ILabelCluster> clusterLabels = new ArrayList<ILabelCluster>();
//		getClusterLabels.setInt(1, clustering.getId());
//		ResultSet rs = getClusterLabels.executeQuery();
//		while (rs.next()) {
//			int labelID = rs.getInt(1);
//			String labelName = rs.getString(2);
//			Double score = rs.getDouble(3);
//			ILabelCluster label = new ClusterLabel(labelID, labelName, score, null);
//			label.setLabelDocumentsID(getLabelDocuments(label));
//			clusterLabels.add(label);
//		}
//		rs.close();
//		getClusterLabels.close();
//		return clusterLabels;
//
//	}

}
