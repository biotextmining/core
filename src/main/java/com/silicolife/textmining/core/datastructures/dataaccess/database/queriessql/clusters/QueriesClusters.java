package com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.clusters;

public class QueriesClusters {

	public final static String insertCluster = "INSERT INTO clusters_process (description) VALUES(?) ";
	public final static String insertClusterProperties = "INSERT INTO clusters_properties (clusters_process_id,clusters_properties.key,clusters_properties.value) VALUES (?,?,?) ";
	public final static String insertClusterLabel = "INSERT INTO clusters_labels (name,score) VALUES (?,?) ";
	public final static String insertClusterLabelDocument = "INSERT INTO clusters_labels_documents (clusters_labels_id,publications_id,queries_idqueries) VALUES(?,?,?) ";
	public final static String insertQueryCluster = "INSERT INTO queries_has_clusters_process (clusters_process_id,queries_idqueries) VALUES (?,?) ";
	public final static String insertClusterLabelProcess = "INSERT INTO clusters_process_has_clusters_labels (clusters_labels_id,clusters_process_id) VALUES (?,?) ";
	public final static String selectQueryClusters = "SELECT clusters_process_id,description FROM queries_has_clusters_process AS qh "+
													 "JOIN clusters_process AS cl ON cl.id=qh.clusters_process_id "+
													 "WHERE queries_idqueries=? AND active=1 ";
	public final static String selectClusterProperties = "SELECT clusters_properties.key,clusters_properties.value FROM clusters_properties "+
														 "WHERE clusters_process_id=? ";
	public final static String selectClusterLabels = "SELECT cl.clusters_labels_id,cld.name,cld.score FROM clusters_process_has_clusters_labels AS cl "+
													 "JOIN clusters_labels AS cld ON cl.clusters_labels_id=cld.id "+
													 "WHERE cl.clusters_process_id=? "+
													 "ORDER BY  cld.score DESC ";
	public final static String selectClusterLabelsDocuments = "SELECT publications_id FROM clusters_labels_documents "+
															  "WHERE clusters_labels_id=? ";
	public final static String inativeClusterProcess = "UPDATE clusters_process "+
													   "SET active=0 "+
													   "WHERE id=? ";
	
	public final static String selectQueryClustersID = "SELECT * FROM clusters_process "+
													   "WHERE id=? AND active=1 ";
	

}
