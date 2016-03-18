package com.silicolife.textmining.core.datastructures.process.network;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.process.IE.network.IAtributes;
import com.silicolife.textmining.core.interfaces.process.IE.network.ICoordenates;
import com.silicolife.textmining.core.interfaces.process.IE.network.IEdge;
import com.silicolife.textmining.core.interfaces.process.IE.network.IGraphicsAtribute;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetwork;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetworkMetaData;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;

public class NetworkImpl implements INetwork<INode, IEdge>{
	
	private String id;
	private String label;
	private String version;
	private INetworkMetaData metaData;
	private List<IAtributes> atributesList;
	private boolean directed;
	private List<INode> nodes;
	private List<IEdge> edges;
	
	
	public NetworkImpl(String id,List<INode> nodes,List<IEdge> edges, String label, String version,boolean directed,
			INetworkMetaData metaData, List<IAtributes> atributesList) {
		super();
		this.id = id;
		this.nodes = nodes;
		this.edges = edges;
		this.label = label;
		this.directed = directed;
		this.version = version;
		this.metaData = metaData;
		this.atributesList = atributesList;
	}
	
	public String getID() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public String getVersion() {
		return version;
	}
	public INetworkMetaData getMetaData() {
		return metaData;
	}
	public List<IAtributes> getAtributesList() {
		return atributesList;
	}

	public boolean isDirected() {
		return directed;
	}

	public List<INode> getNodes() {
		return nodes;
	}

	public List<IEdge> getEdges() {
		return edges;
	}
	
	public static INetwork<INode, IEdge> getNetWorkExample()
	{
		String id = "Sample Network Test";
		String label = id;
		String version = "1.0";
		INetworkMetaData metaData = new NetworkMetaDataImpl("Biomedical Entities Interaction", "Sample Network whit interactions",GregorianCalendar.getInstance().getTime(),
				id, "@Note2 Sample Network", "XGMML");
		List<IAtributes> atributesList = new ArrayList<IAtributes>();
		atributesList.add(new AtributesImpl(null, "backgroundColor", "#9999ff", null, false, null));
		boolean directed = true;
		List<INode> nodes = new ArrayList<INode>();	
		// Node 1
		int nodeID = 1;
		String nodeLabel = "relA";
		String nodeName = "relA";
		List<IAtributes> nodeAtributesList = new ArrayList<IAtributes>();
		List<IAtributes> externalIDs = new ArrayList<IAtributes>();
		externalIDs.add(new AtributesImpl(null, "Kegg", "Kegg_Value", "string", false, null));
		externalIDs.add(new AtributesImpl(null, "BioCyc", "BioCyc_Value", "string", false, null));
		nodeAtributesList.add(new AtributesImpl("External IDs", "External IDs", null, "list", true, externalIDs ));
		nodeAtributesList.add(new AtributesImpl("Class", "Class", "Gene", "string", false, null ));
		ICoordenates cordenates = null;
		List<IAtributes> atributes = new ArrayList<IAtributes>();
		List<IAtributes> listAtributes = new ArrayList<IAtributes>();
		listAtributes.add(new AtributesImpl(null, "nodeTransparency", "1.0", null, false, null));
		listAtributes.add(new AtributesImpl(null, "nodeLabelFont", "Default-0-12", null, false, null));
//		listAtributes.add(new Atributes(null, "borderLineType", "solid", null, false, null));
		atributes.add(new AtributesImpl(null, "cytoscapeNodeGraphicsAttributes", null, null, true, listAtributes ));
		IGraphicsAtribute nodeGraphicsAtribute = new GraphicsAtributeImpl(2, "#0000FF", "#000000",null,false, cordenates, atributes);
		nodes.add(new NodeImpl(nodeID, nodeLabel, nodeName, nodeAtributesList, nodeGraphicsAtribute));
		// Node 2
		int nodeID2 = 2;
		String nodeLabel2 = "RelA";
		String nodeName2 = "RelA";
		IGraphicsAtribute nodeGraphicsAtribute2 = new GraphicsAtributeImpl(1, "#00FF00", "#000000",null,false, cordenates, atributes);
		List<IAtributes> nodeAtributesList2 = new ArrayList<IAtributes>();
		nodeAtributesList2.add(new AtributesImpl("Class", "Class", "Protein", "string", false, null ));
		nodes.add(new NodeImpl(nodeID2, nodeLabel2, nodeName2, nodeAtributesList2, nodeGraphicsAtribute2));
		// Node 3
		int nodeID3 = 3;
		String nodeLabel3 = "RelB";
		String nodeName3 = "RelB";
		IGraphicsAtribute nodeGraphicsAtribute3 = new GraphicsAtributeImpl(2, "#00FF00", "#000000",null,false, cordenates, atributes);
		List<IAtributes> nodeAtributesList3 = new ArrayList<IAtributes>();
		nodeAtributesList3.add(new AtributesImpl("Class", "Class", "Protein", "string", false, null ));
		nodes.add(new NodeImpl(nodeID3, nodeLabel3, nodeName3, nodeAtributesList3, nodeGraphicsAtribute3));
		// Node 4
		int nodeID4 = 4;
		String nodeLabel4 = "ppGpp";
		String nodeName4 = "ppGpp";
		List<IAtributes> nodeAtributesList4 = new ArrayList<IAtributes>();
		List<IAtributes> externalIDs4 = new ArrayList<IAtributes>();
		externalIDs4.add(new AtributesImpl(null, "Kegg", "Kegg_Value_ppgpp", "string", false, null));
		nodeAtributesList4.add(new AtributesImpl("External IDs", "External IDs", null, "list", true, externalIDs ));
		nodeAtributesList4.add(new AtributesImpl("Class", "Class", "Compound", "string", false, null ));
		List<IAtributes> atributes4 = new ArrayList<IAtributes>();
		atributes4.add(new AtributesImpl(null, "cytoscapeNodeGraphicsAttributes", null, null, true, listAtributes ));
		IGraphicsAtribute nodeGraphicsAtribute4 = new GraphicsAtributeImpl(4, "#663300F", "#000000",null,false, cordenates, atributes4);
		nodes.add(new NodeImpl(nodeID4, nodeLabel4, nodeName4, nodeAtributesList4, nodeGraphicsAtribute4));
		//node 5
		int nodeID5 = 5;
		String nodeLabel5 = "atpase";
		String nodeName5 = "atpase";
		IGraphicsAtribute nodeGraphicsAtribute5 = new GraphicsAtributeImpl(3, "#FF0000", "#000000",null,false, cordenates, atributes);
		List<IAtributes> nodeAtributesList5 = new ArrayList<IAtributes>();
		nodeAtributesList5.add(new AtributesImpl("Class", "Class", "Enzyme", "string", false, null));
		nodes.add(new NodeImpl(nodeID5, nodeLabel5, nodeName5, nodeAtributesList5, nodeGraphicsAtribute5));
		//Node 6
		int nodeID6 = 6;
		String nodeLabel6 = "fumate";
		String nodeName6 = "fumate";
		IGraphicsAtribute nodeGraphicsAtribute6 = new GraphicsAtributeImpl(1, "#663300F", "#000000",null,false, cordenates, atributes);
		List<IAtributes> nodeAtributesList6 = new ArrayList<IAtributes>();
		nodeAtributesList6.add(new AtributesImpl("Class", "Class", "Enzyme", "string", false, null));
		nodes.add(new NodeImpl(nodeID6, nodeLabel6, nodeName6, nodeAtributesList6, nodeGraphicsAtribute6));
		// Edges
		List<IEdge> edges = new ArrayList<IEdge>();
		// Edge1 Node 1 -> Node 2
		String edgeid = "1 -> 2";
		String edgeLabel = "Activate";
		INode sourceNode = nodes.get(0);
		INode targetNode = nodes.get(1);
		List<IAtributes> edgeSublistAtributes = new ArrayList<IAtributes>();
		List<IAtributes> edgeatributes = new ArrayList<IAtributes>();
		edgeSublistAtributes.add(new AtributesImpl(null, "sourceArrow", "0", null, false, null));
		edgeSublistAtributes.add(new AtributesImpl(null, "targetArrow", "0", null, false, null));
		edgeSublistAtributes.add(new AtributesImpl(null, "edgeLabelFont", "sansserif.italic-0-10", null, false, null));
		edgeSublistAtributes.add(new AtributesImpl(null, "edgeLineType", "LINE_2", null, false, null));
		edgeSublistAtributes.add(new AtributesImpl(null, "sourceArrowColor", "#000000", null, false, null));
		edgeSublistAtributes.add(new AtributesImpl(null, "targetArrowColor", "#000000", null, false, null));
		edgeatributes.add(new AtributesImpl(null, "cytoscapeEdgeGraphicsAttributes", null, null, true, edgeSublistAtributes ));
		IGraphicsAtribute edgegraphicsAtribute = new GraphicsAtributeImpl(3, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList = new ArrayList<IAtributes>();
		edgeAtributesList.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "activate", "string", false, null));
		edges.add(new EdgeImpl(edgeid, edgeLabel, sourceNode, targetNode,directed, edgeAtributesList, edgegraphicsAtribute));
		// Edge 2 Node 2 -> 3
		String edgeid2 = "2 -> 3";
		String edgeLabel2 = "Binding";
		INode sourceNode2 = nodes.get(1);
		INode targetNode2 = nodes.get(2);
		IGraphicsAtribute edgegraphicsAtribute2 = new GraphicsAtributeImpl(2, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList2 = new ArrayList<IAtributes>();
		edgeAtributesList2.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "bind", "string", false, null));
		edges.add(new EdgeImpl(edgeid2, edgeLabel2, sourceNode2, targetNode2,directed, edgeAtributesList2, edgegraphicsAtribute2));
		// Edge 3 Node 3 -> 2	
		String edgeid3 = "3 -> 2";
		String edgeLabel3 = "Binding";
		INode sourceNode3 = nodes.get(2);
		INode targetNode3 = nodes.get(1);
		IGraphicsAtribute edgegraphicsAtribute3 = new GraphicsAtributeImpl(5, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList3 = new ArrayList<IAtributes>();
		edgeAtributesList3.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "bind", "string", false, null));
		edges.add(new EdgeImpl(edgeid3, edgeLabel3, sourceNode3, targetNode3,directed, edgeAtributesList3, edgegraphicsAtribute3));
		// Edge 4 Node 3 -> 4	
		String edgeid4 = "3 -> 4";
		String edgeLabel4 = "Inducing";
		INode sourceNode4 = nodes.get(2);
		INode targetNode4 = nodes.get(3);
		IGraphicsAtribute edgegraphicsAtribute4 = new GraphicsAtributeImpl(7, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList4 = new ArrayList<IAtributes>();
		edgeAtributesList4.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "induce", "string", false, null));
		edges.add(new EdgeImpl(edgeid4, edgeLabel4, sourceNode4, targetNode4,directed, edgeAtributesList4, edgegraphicsAtribute4));
		// Edge 5 Node 3 -> 5	
		String edgeid5 = "3 -> 5";
		String edgeLabel5 = "Produce";
		INode sourceNode5 = nodes.get(2);
		INode targetNode5 = nodes.get(4);
		IGraphicsAtribute edgegraphicsAtribute5 = new GraphicsAtributeImpl(2, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList5 = new ArrayList<IAtributes>();
		edgeAtributesList5.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "produce", "string", false, null));
		edges.add(new EdgeImpl(edgeid5, edgeLabel5, sourceNode5, targetNode5,directed, edgeAtributesList5, edgegraphicsAtribute5));
		// Edge 6 Node 5 -> 3	
		String edgeid6 = "5 -> 3";
		String edgeLabel6 = "inhibits";
		INode sourceNode6 = nodes.get(4);
		INode targetNode6 = nodes.get(2);
		IGraphicsAtribute edgegraphicsAtribute6 = new GraphicsAtributeImpl(1, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList6 = new ArrayList<IAtributes>();
		edgeAtributesList6.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "inhibit", "string", false, null));
		edges.add(new EdgeImpl(edgeid6, edgeLabel6, sourceNode6, targetNode6,directed, edgeAtributesList6, edgegraphicsAtribute6));
		// Edge 7 Node 3 -> 5 (2)	
		String edgeid7 = "3 -> 5 (2)";
		String edgeLabel7 = "Inducing";
		INode sourceNode7 = nodes.get(2);
		INode targetNode7 = nodes.get(4);
		IGraphicsAtribute edgegraphicsAtribute7 = new GraphicsAtributeImpl(3, "#0000FF", null,null,false, cordenates, edgeatributes);
		List<IAtributes> edgeAtributesList7 = new ArrayList<IAtributes>();
		edgeAtributesList7.add(new AtributesImpl(null, GlobalNames.relationPropertyLemma, "induce", "string", false, null));
		edges.add(new EdgeImpl(edgeid7, edgeLabel7, sourceNode7, targetNode7,directed, edgeAtributesList7, edgegraphicsAtribute7));
		
		INetwork<INode, IEdge> network = new NetworkImpl(id, nodes, edges, label, version, directed, metaData, atributesList);
		return network;
	}

}
