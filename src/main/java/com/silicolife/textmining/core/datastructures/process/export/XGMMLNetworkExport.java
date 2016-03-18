package com.silicolife.textmining.core.datastructures.process.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.process.network.AtributesImpl;
import com.silicolife.textmining.core.datastructures.report.processes.io.export.NetworkExportReportImpl;
import com.silicolife.textmining.core.datastructures.textprocessing.XmlConversions;
import com.silicolife.textmining.core.interfaces.core.report.processes.ie.io.exporter.INetworkExportReport;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.INetworkExport;
import com.silicolife.textmining.core.interfaces.process.IE.network.IAtributes;
import com.silicolife.textmining.core.interfaces.process.IE.network.ICoordenates;
import com.silicolife.textmining.core.interfaces.process.IE.network.IEdge;
import com.silicolife.textmining.core.interfaces.process.IE.network.IGraphicsAtribute;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetwork;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;

public class XGMMLNetworkExport implements INetworkExport {

	private File file;
	private INetwork<INode, IEdge> network;
	private final static String fileExtention = "xgmml";
	
	

	public XGMMLNetworkExport(File file, INetwork<INode, IEdge> network) {
		this.file = file;
		this.network = network;
	}

	/**
	 * TO DO
	 * @throws IOException 
	 */
	public INetworkExportReport export() {
		INetworkExportReport report;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));		
			writeHeader(bw);
			writeNodes(bw);		
			writeEdges(bw);		
			writeFinalFile(bw);		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			report = new NetworkExportReportImpl(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.network.export"), 0, 0);
			report.setcancel();
			return report;
		}
		report = new NetworkExportReportImpl(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.network.export"), network.getNodes().size(), network.getEdges().size());
		return report;
	}

	private void writeFinalFile(BufferedWriter bw) throws IOException {
		bw.write("</graph>\n");
	}

	private void writeEdges(BufferedWriter bw) throws IOException {
		for(IEdge edge:network.getEdges())
		{
			writeEdge(bw,edge);
		}
	}

	private void writeEdge(BufferedWriter bw, IEdge edge) throws IOException {
		String prefix = "\t\t";
		String edgeToString = edgeMainInfoToString(edge);
		bw.write("\t<edge "+edgeToString+">\n");
		IGraphicsAtribute graphicsAtribute = edge.getGraphicsAtribute();
		if(edge.getLabel()!=null)
		{
			IAtributes attInteraction = new AtributesImpl("interaction", "interaction", XmlConversions.removeSpecialCharacteres(edge.getLabel()), "string", false, null);
			writeAtribulte(bw, prefix, attInteraction );
		}
		if(graphicsAtribute!=null)
		{
			edge.getAtributesList().add(new AtributesImpl(null, "Weight", String.valueOf(graphicsAtribute.getWith()), "integer", false, null));		
		}
		for(IAtributes att:edge.getAtributesList())
		{
			writeAtribulte(bw, prefix, att);
		}
		if(graphicsAtribute!=null)
			writeGraphicsAtribute(bw, prefix,graphicsAtribute);
		bw.write("\t</edge>\n");
	}

	private String edgeMainInfoToString(IEdge edge) {
		String result = new String();
		result = result + "id=\""+edge.getID()+"\" ";
		if(edge.getLabel()!=null)
			result = result + "label=\""+XmlConversions.removeSpecialCharacteres(edge.getLabel())+"\" ";
		if(edge.getSourceNode()!=null)
			result = result + "source=\""+edge.getSourceNode().getID()+"\" ";
		if(edge.getTargetNode()!=null)
			result = result + "target=\""+edge.getTargetNode().getID()+"\" ";
		if(edge.isDirected())
		{
			result = result + "cy:directed=\"1\" ";
		}
		else
		{
			result = result + "cy:directed=\"0\" ";
		}
		return result;
	}

	private void writeNodes(BufferedWriter bw) throws IOException {
		for(INode node:network.getNodes())
		{
			writeNode(bw,node);
		}
	}

	private void writeNode(BufferedWriter bw, INode node) throws IOException {
		String prefix = "\t\t";
		String nodeToString = nodeMainInfoToString(node);
		bw.write("\t<node "+nodeToString+">\n");
		IGraphicsAtribute graphicsAtribute = node.getGraphicsAtribute();
		if(graphicsAtribute!=null)
		{
			node.getAtributesList().add(new AtributesImpl(null, "Weight", String.valueOf(graphicsAtribute.getWith()), "integer", false, null));		
		}
		for(IAtributes att:node.getAtributesList())
		{
			writeAtribulte(bw, prefix, att);
		}	
		if(graphicsAtribute!=null)
			writeGraphicsAtribute(bw, prefix,graphicsAtribute);
		bw.write("\t</node>\n");		
	}

	private void writeGraphicsAtribute(BufferedWriter bw, String prefix,IGraphicsAtribute graphicsAtribute) throws IOException {
		String graphicsToString = nodeGraphicOptionsToString(graphicsAtribute);
		bw.write("\t\t<graphics "+graphicsToString+">\n");
		if(graphicsAtribute.getAtributes()!=null)
		{
			for(IAtributes att : graphicsAtribute.getAtributes())
			{
				writeAtribulte(bw, prefix, att);
			}
		}
		bw.write("\t\t</graphics>\n");
	}

	private String nodeGraphicOptionsToString(IGraphicsAtribute graphicsAtribute) {
		String result = new String();
		if(graphicsAtribute.getWith() > 0 && graphicsAtribute.useWeightsAttrinute())
			result = result + "width=\""+graphicsAtribute.getWith()+"\" ";
		if(graphicsAtribute.getFillColor()!=null)
			result = result + "fill=\""+XmlConversions.removeSpecialCharacteres(graphicsAtribute.getFillColor())+"\" ";
		if(graphicsAtribute.getOutlineColor()!=null)
			result = result + "outline=\""+XmlConversions.removeSpecialCharacteres(graphicsAtribute.getOutlineColor())+"\" ";
		if(graphicsAtribute.getCordenates()!=null)
		{
			ICoordenates coordenates = graphicsAtribute.getCordenates();
			result = result + "x=\""+coordenates.getX()+"\" ";
			result = result + "y=\""+coordenates.getY()+"\" ";
			result = result + "w=\""+coordenates.getWidth()+"\" ";
			result = result + "h=\""+coordenates.getHeight()+"\" ";
		}
		if(graphicsAtribute.getXGMMLPolygnos()!=null)
		{
			result = result + "type=\""+graphicsAtribute.getXGMMLPolygnos().toString()+"\" ";
		}
		return result;
	}

	private String nodeMainInfoToString(INode node) {
		String result = new String();
		result = result + "id=\""+node.getID()+"\" ";
		if(node.getLabel()!=null)
			result = result + "label=\""+XmlConversions.removeSpecialCharacteres(node.getLabel())+"\" ";
		if(node.getName()!=null)
			result = result + "name=\""+XmlConversions.removeSpecialCharacteres(node.getName())+"\" ";
		return result;
	}

	private void writeHeader(BufferedWriter bw) throws IOException {
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");	
		writeGraphFields(bw);
		writeGraphAtrinutes(bw);
	}

	private void writeGraphAtrinutes(BufferedWriter bw) throws IOException {
		writeMetaData(bw);
		writeNetworkGeneralAtrinutes(bw);
	}



	private void writeNetworkGeneralAtrinutes(BufferedWriter bw) throws IOException {
		String prefix = "\t";
		for(IAtributes att :network.getAtributesList())
		{
			writeAtribulte(bw,prefix,att);
		}
	}

	private void writeAtribulte(BufferedWriter bw, String prefix, IAtributes att) throws IOException {
		if(att.isListAtribute())
		{
			String attToString = simpleAtributeToString(att);		
			bw.write(prefix+"<att "+attToString+">\n");
			for(IAtributes atrb :att.getListAtributes())
			{
				if(atrb.isListAtribute())
					writeAtribulte(bw,prefix+"\t",atrb);
				else
				{
					String subattToString = simpleAtributeToString(atrb);		
					bw.write(prefix+"\t<att "+subattToString+"/>\n");
				}
			}
			bw.write(prefix+"</att>\n");
		}
		else
		{
			String attToString = simpleAtributeToString(att);		
			bw.write(prefix+"<att "+attToString+"/>\n");
		}
	}
	
	private String simpleAtributeToString(IAtributes att) {
		String result = new String();
		if(att.getLabel()!=null)
			result = result + "label=\""+XmlConversions.removeSpecialCharacteres(att.getLabel())+"\" ";
		if(att.getName()!=null)
			result = result + "name=\""+XmlConversions.removeSpecialCharacteres(att.getName())+"\" ";
		if(att.getValue()!=null)
			result = result + "value=\""+XmlConversions.removeSpecialCharacteres(att.getValue())+"\" ";
		if(att.getValueType()!=null)
			result = result + "type=\""+XmlConversions.removeSpecialCharacteres(att.getValueType())+"\" ";
		return result;
	}

	private void writeMetaData(BufferedWriter bw) throws IOException {
		bw.write("\t<att name=\"networkMetadata\">\n");
		bw.write("\t\t<rdf:RDF>\n");
        bw.write("\t\t\t<rdf:Description rdf:about=\"http://www.cytoscape.org/\">\n");
        if(network.getMetaData().getDate()!=null)
        	 bw.write("\t\t\t\t<dc:date>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getDate().toString())+"</dc:date>\n");
        if(network.getMetaData().getDescription()!=null)
        	bw.write("\t\t\t\t<dc:description>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getDescription())+"</dc:description>\n");
        if(network.getMetaData().getFormat()!=null)
        	bw.write("\t\t\t\t<dc:format>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getFormat())+"</dc:format>\n");
        if(network.getMetaData().getSource()!=null)
        	bw.write("\t\t\t\t<dc:source>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getSource())+"</dc:source>\n");
        if(network.getMetaData().getTitle()!=null)
        	bw.write("\t\t\t\t<dc:title>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getTitle())+"</dc:title>\n");
        if(network.getMetaData().getType()!=null)
        	bw.write("\t\t\t\t<dc:type>"+XmlConversions.removeSpecialCharacteres(network.getMetaData().getTitle())+"</dc:type>\n");
        bw.write("\t\t\t</rdf:Description>\n");
        bw.write("\t\t</rdf:RDF>\n");
		bw.write("\t</att>\n");
	}
	
	private void writeGraphFields(BufferedWriter bw) throws IOException {
		bw.write("<graph ");
		if(network.getID()!=null && !network.getID().equals(""))
			bw.write(" id=\""+XmlConversions.removeSpecialCharacteres(network.getID())+"\" ");
		if(network.getLabel()!=null && !network.getLabel().equals(""))
			bw.write(" label=\""+XmlConversions.removeSpecialCharacteres(network.getLabel())+"\" ");
		bw.write(" xmlns:dc=\"http://purl.org/dc/elements/1.1/\"");
		bw.write(" xmlns:nsl=\"http://www.w3.org/1999/xlink\"");
		bw.write(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		bw.write(" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"");
		bw.write(" xmlns:cy=\"http://www.cytoscape.org\"");
		bw.write(" xmlns=\"http://www.cs.rpi.edu/XGMML\"");			
		bw.write(">\n");
	}
	
	public File getFile() {
		return file;
	}


	public String getFileExtention() {
		return fileExtention;
	}

	public INetwork<? extends INode, ? extends IEdge> getNetwork() {
		return network;
	}
}
