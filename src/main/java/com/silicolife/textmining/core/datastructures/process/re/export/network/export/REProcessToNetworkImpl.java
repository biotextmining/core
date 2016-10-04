package com.silicolife.textmining.core.datastructures.process.re.export.network.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.silicolife.textmining.core.datastructures.annotation.ner.SimpleEntity;
import com.silicolife.textmining.core.datastructures.annotation.re.SimpleEvent;
import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentImpl;
import com.silicolife.textmining.core.datastructures.general.ClassPropertiesManagement;
import com.silicolife.textmining.core.datastructures.process.network.AtributesImpl;
import com.silicolife.textmining.core.datastructures.process.network.EdgeImpl;
import com.silicolife.textmining.core.datastructures.process.network.GraphicsAtributeImpl;
import com.silicolife.textmining.core.datastructures.process.network.NetworkImpl;
import com.silicolife.textmining.core.datastructures.process.network.NetworkMetaDataImpl;
import com.silicolife.textmining.core.datastructures.process.network.NodeImpl;
import com.silicolife.textmining.core.datastructures.process.re.RelationTypeImpl;
import com.silicolife.textmining.core.datastructures.process.re.export.configuration.CardinalityImpl;
import com.silicolife.textmining.core.datastructures.process.re.export.configuration.DocumentInformation;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.datastructures.utils.conf.OtherConfigurations;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.network.IAtributes;
import com.silicolife.textmining.core.interfaces.process.IE.network.IEdge;
import com.silicolife.textmining.core.interfaces.process.IE.network.IGraphicsAtribute;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetwork;
import com.silicolife.textmining.core.interfaces.process.IE.network.INetworkMetaData;
import com.silicolife.textmining.core.interfaces.process.IE.network.INode;
import com.silicolife.textmining.core.interfaces.process.IE.network.XGMMLPolygnos;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRESchemaToNetwork;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.ICardinality;
import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfiguration;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;

public class REProcessToNetworkImpl implements IRESchemaToNetwork{

	private boolean stop;
	private int nodeIDIncrement = 1;
	private int edgeIDIncrement = 1;
	private boolean directed;

		
	public REProcessToNetworkImpl()
	{
		stop = false;
	}
	
	public INetwork<INode, IEdge> getNetwork(IRESchema rePorcess,IREToNetworkConfiguration configurations) throws ANoteException, IOException, SQLException
	{
		nodeIDIncrement = 1;
		edgeIDIncrement = 1;
		directed = false;
		if(configurations!=null && configurations.getAdvanceConfigurations() != null && configurations.getAdvanceConfigurations().isDirectedNetwork())
			directed = true;
		TreeMap<SimpleEntity,INode> mappingEntityNode = new TreeMap<SimpleEntity, INode>();
		TreeMap<SimpleEvent,IEdge> mappingEdgeEvent = new TreeMap<SimpleEvent, IEdge>();
		TreeMap<IEdge,DocumentInformation> simpeEventDocumentsinformation = new TreeMap<IEdge, DocumentInformation>();
		Map<Long,String> docIDPmid = new HashMap<Long, String>();
		Map<Long,IResourceElement> termIDTerm = new HashMap<Long, IResourceElement>();
		Map<Long,List<String>> termIDSynonyms = new HashMap<Long, List<String>>();
		Map<Long,List<IExternalID>> externalIDs = new HashMap<Long, List<IExternalID>>();
		IDictionary dic = new DictionaryImpl(1, "", "",true);
		ICorpus corpus = rePorcess.getCorpus();
		IDocumentSet docs = corpus.getArticlesCorpus();
		Iterator<IPublication> itDocs =docs.iterator();
		int total = docs.size();
		int step = 0;
		long starttime = GregorianCalendar.getInstance().getTimeInMillis();
		while(itDocs.hasNext())
		{
			if(stop)
			{
				break;
			}
			IPublication doc = itDocs.next();
			IAnnotatedDocument annotDoc = processNodesDocument(mappingEntityNode,rePorcess, corpus,doc,configurations,dic, termIDTerm, termIDSynonyms, externalIDs);
			String otherID = new String();
			List<IPublicationExternalSourceLink> externalSources = annotDoc.getPublicationExternalIDSource();
			for(IPublicationExternalSourceLink externalSource:externalSources)
			{
				otherID = otherID + externalSource.getSourceInternalId();
			}
			docIDPmid.put(annotDoc.getId(), otherID);
			processEdgesDocument(mappingEntityNode,mappingEdgeEvent,simpeEventDocumentsinformation,rePorcess, corpus,doc,configurations);
			memoryAndProgressAndTime(step, total, starttime);
			step++;
		}
		if(configurations!=null && configurations.getAdvanceConfigurations()!=null && !configurations.getAdvanceConfigurations().allowLonelyNodes())
		{
			removeAloneNodes(mappingEntityNode);
		}
		if(configurations!=null && configurations.getAdvanceConfigurations()!=null && configurations.getAdvanceConfigurations().exportRelationsDetails())
		{
			IAtributes atribute;
			IAtributes st;
			for(IEdge edge : simpeEventDocumentsinformation.keySet())
			{
				DocumentInformation documentInfo = simpeEventDocumentsinformation.get(edge);
				List<IAtributes> sentences = new ArrayList<IAtributes>();
				for(long docID : documentInfo.getDocumentIDRelationSetences().keySet())
				{
					String pmid = "";
					if(docIDPmid.containsKey(docID))
					{
						pmid = "PMID: "+docIDPmid.get(docID) +" " ;
					}
					else
					{
						pmid = "ID: "+docID +" ";
					}

					for(String sentence : documentInfo.getDocumentIDRelationSetences().get(docID))
					{
						st = new AtributesImpl("", pmid + sentence, pmid + sentence, "string", false, null);
						sentences.add(st);
					}
				}
				atribute = new AtributesImpl(null, "Event Details (PMID - Sentences)", "Event Details (PMID - Sentences)", "list", true, sentences);
				edge.addEdgeAAtributes(atribute);
			}
		}
		Collection<INode> nodes = mappingEntityNode.values();
		Collection<IEdge> edges = mappingEdgeEvent.values();
		if(stop)
		{
			return null;
		}		
		return builtNetwork(directed,rePorcess, new ArrayList<INode>(nodes), new ArrayList<IEdge>(edges));
	}



	private void removeAloneNodes(TreeMap<SimpleEntity, INode> mappingEntityNode) {
		List<SimpleEntity> toRemove = new ArrayList<SimpleEntity>();
		for(SimpleEntity simpleEnt:mappingEntityNode.keySet())
		{
			INode node = mappingEntityNode.get(simpleEnt);
			if(node.isNodeAlone())
			{
				toRemove.add(simpleEnt);
			}
		}
		for(SimpleEntity entremove:toRemove)
			mappingEntityNode.remove(entremove);
	}

	private void processEdgesDocument(TreeMap<SimpleEntity, INode> mappingEntityNode,TreeMap<SimpleEvent, IEdge> mappingEdgeEvent,
			TreeMap<IEdge, DocumentInformation> simpeEventDocumentsinformation, IRESchema rePorcess, ICorpus corpus, IPublication doc,
			IREToNetworkConfiguration configurations) throws ANoteException, IOException {
		IAnnotatedDocument annotDOc = new AnnotatedDocumentImpl(doc,rePorcess, corpus);
		List<IEventAnnotation> list = annotDOc.getEventAnnotations();
		List<IEntityAnnotation> entitiesAtLeft;
		List<IEntityAnnotation>entitiesAtRight;
		SimpleEntity entSimpleLeft;
		SimpleEntity entSimpleRight;
		SimpleEvent simpleEvent;
		IEdge edge;
		boolean writeWithAtribute = false;
		boolean isDirected = false;
		if(configurations!= null && configurations.getAdvanceConfigurations() != null)
		{
			writeWithAtribute = configurations.getAdvanceConfigurations().useGraphicWeights();
			isDirected = configurations.getAdvanceConfigurations().isDirectedNetwork();
		}
		for(IEventAnnotation event:list)
		{
			if(eventValidadion(event,mappingEntityNode,configurations))
			{
				entitiesAtLeft = event.getEntitiesAtLeft();
				entitiesAtRight = event.getEntitiesAtRight();
				for(IEntityAnnotation entLeft:entitiesAtLeft)
				{
					entSimpleLeft = new SimpleEntity(entLeft.getAnnotationValue(), entLeft.getClassAnnotation().getId(), entLeft.getResourceElement());
					if(mappingEntityNode.containsKey(entSimpleLeft))
					{
						for(IEntityAnnotation entRight:entitiesAtRight)
						{
							entSimpleRight = new SimpleEntity(entRight.getAnnotationValue(), entRight.getClassAnnotation().getId(), entRight.getResourceElement());
							
							if(mappingEntityNode.containsKey(entSimpleRight))
							{
								String clue = event.getEventProperties().getLemma();
								if(configurations!=null && configurations.getAdvanceConfigurations() != null && configurations.getAdvanceConfigurations().ignoreClues())
									clue = new String();
								if(event.getEventProperties().getDirectionally() == DirectionallyEnum.RightToLeft)
								{
									simpleEvent = new SimpleEvent(clue,entSimpleRight,entSimpleLeft,directed);
								}
								else 
								{
									simpleEvent = new SimpleEvent(clue,entSimpleLeft,entSimpleRight,directed);
								}
								if(validateSimpleEvent(simpleEvent,configurations))
								{
									String sentence = getSentence(annotDOc,entLeft.getStartOffset(),entRight.getEndOffset());
									if(mappingEdgeEvent.containsKey(simpleEvent))
									{
										mappingEdgeEvent.get(simpleEvent).increaseWeight();
										IEdge edgeAux = mappingEdgeEvent.get(simpleEvent);
										simpeEventDocumentsinformation.get(edgeAux).update(annotDOc.getId(), sentence);
									}
									else
									{
										IGraphicsAtribute graphicsAtribute = new GraphicsAtributeImpl(1, OtherConfigurations.getVerbColor(), OtherConfigurations.getVerbColorBackGround(),null,writeWithAtribute, null, null);
										edge = new EdgeImpl(String.valueOf(edgeIDIncrement), simpleEvent.getClue(),mappingEntityNode.get(simpleEvent.getSourceEntity()) , mappingEntityNode.get(simpleEvent.getTargetEntity()),isDirected, new ArrayList<IAtributes>(), graphicsAtribute);
										mappingEdgeEvent.put(simpleEvent, edge);
										simpeEventDocumentsinformation.put(edge, new DocumentInformation());
										simpeEventDocumentsinformation.get(edge).update(annotDOc.getId(), sentence);
										if(simpleEvent.getSourceEntity().compareTo(simpleEvent.getTargetEntity()) != 0)
										{
											mappingEntityNode.get(simpleEvent.getSourceEntity()).setNodeAlone(false);
											mappingEntityNode.get(simpleEvent.getTargetEntity()).setNodeAlone(false);
										}
										edgeIDIncrement++;
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	private String getSentence(IAnnotatedDocument annotDOc, long startOffset,long endOffset) throws ANoteException, IOException {
		List<ISentence> sentences = annotDOc.getSentencesText();
		ISentence sentenceInit = findSentence(sentences,(int)startOffset);	
		ISentence sentenceEnd = findSentence(sentences,(int)endOffset);
		return annotDOc.getDocumentAnnotationText().substring((int)sentenceInit.getStartOffset(),(int)sentenceEnd.getEndOffset());
	}
	
	private ISentence findSentence(List<ISentence> sentences, int offset) {
		for(ISentence set:sentences)
		{
			if(set.getStartOffset() <= offset && offset <= set.getEndOffset())
			{
				return set;
			}
		}
		return null;
	}

	private boolean validateSimpleEvent(SimpleEvent simpleEvent,IREToNetworkConfiguration configurations) {
		IRelationsType type = new RelationTypeImpl(simpleEvent.getSourceEntity().getClassID(), simpleEvent.getTargetEntity().getClassID());
		if(configurations!=null && configurations.getAdvanceConfigurations() !=null &&
				configurations.getAdvanceConfigurations().getRelationsType()!=null && configurations.getAdvanceConfigurations().getRelationsType().size()>0
				&& !configurations.getAdvanceConfigurations().getRelationsType().contains(type))
		{
			return false;
		}
		return true;
	}

	private boolean eventValidadion(IEventAnnotation event, TreeMap<SimpleEntity, INode> mappingEntityNode, IREToNetworkConfiguration configurations) {

		if(event.getEntitiesAtLeft().size() == 0 || event.getEntitiesAtRight().size() == 0)
		{
			return false;
		}
		String lemma = event.getEventProperties().getLemma();
		if(lemma==null)
			lemma = new String();
		if(configurations != null && configurations.getAdvanceConfigurations()!= null && configurations.getAdvanceConfigurations().getLemmaVerbsAllow()!=null &&
				configurations.getAdvanceConfigurations().getLemmaVerbsAllow().size()>0 && !configurations.getAdvanceConfigurations().getLemmaVerbsAllow().contains(lemma))
		{
			return false;
		}
		int leftEntities = getCardinallity(event.getEntitiesAtLeft());
		int rigtEntities = getCardinallity(event.getEntitiesAtRight());
		ICardinality cardidality = new CardinalityImpl(leftEntities, rigtEntities);
		configurations.getRelationConfigurationOptions().getCardinalitiesAllowed().contains(cardidality);

		if(configurations!= null && configurations.getRelationConfigurationOptions()!=null &&
				configurations.getRelationConfigurationOptions().getCardinalitiesAllowed() !=null && 
				configurations.getRelationConfigurationOptions().getCardinalitiesAllowed().size() > 0 &&
				!configurations.getRelationConfigurationOptions().getCardinalitiesAllowed().contains(cardidality))
		{
			return false;
		}
		PolarityEnum polarity = event.getEventProperties().getPolarity();
		if(configurations!= null && configurations.getRelationConfigurationOptions()!=null 
				&& configurations.getRelationConfigurationOptions().getPolaritiesAllowed()!=null
				&& configurations.getRelationConfigurationOptions().getPolaritiesAllowed().size() > 0
				&& !configurations.getRelationConfigurationOptions().getPolaritiesAllowed().contains(polarity))
		{
			return false;
		}
		DirectionallyEnum directionally = event.getEventProperties().getDirectionally();
		if(configurations!= null && configurations.getRelationConfigurationOptions()!=null 
				&& configurations.getRelationConfigurationOptions().getDirectionallyAllowed()!=null
				&& configurations.getRelationConfigurationOptions().getDirectionallyAllowed().size() > 0
				&& !configurations.getRelationConfigurationOptions().getDirectionallyAllowed().contains(directionally))
		{
			return false;
		}
		return true;
	}

	private int getCardinallity(List<IEntityAnnotation> entities) {
		if(entities.size()==1)
		{
			return 1;
		}
		else if(entities.size()>1)
		{
			return 2;
		}
		return -1;
	}

	private IAnnotatedDocument processNodesDocument(TreeMap<SimpleEntity, INode> mappingEntityNode, IRESchema rePorcess,ICorpus corpus, IPublication doc, IREToNetworkConfiguration configurations,IDictionary dic,
			Map<Long, IResourceElement> termIDTerm,Map<Long, List<String>> termIDSynonyms,Map<Long, List<IExternalID>> externalIDs) throws ANoteException, SQLException {
		IAnnotatedDocument annotDOc = new AnnotatedDocumentImpl(doc,rePorcess, corpus);
		List<IEntityAnnotation> entities = annotDOc.getEntitiesAnnotations();
		boolean writeWithAtribute = false;
		if(configurations!= null && configurations.getAdvanceConfigurations() != null)
			writeWithAtribute = configurations.getAdvanceConfigurations().useGraphicWeights();
		for(IEntityAnnotation entity:entities)
		{
			SimpleEntity simplEnt = new SimpleEntity(entity.getAnnotationValue(),entity.getClassAnnotation().getId(),entity.getResourceElement());
			if(configurations!=null && configurations.getEntityConfigutrationOptions()!=null && !configurations.getEntityConfigutrationOptions().getClassIdsAllowed().contains(simplEnt.getClassID()))
			{
				/**
				 * Entity Filter (Not added Node)
				 */

			}
			else if(mappingEntityNode.containsKey(simplEnt))
			{
				mappingEntityNode.get(simplEnt).increaseWeight();
			}
			else
			{
				IAnoteClass klass = ClassPropertiesManagement.getClassGivenClassID(simplEnt.getClassID());
				XGMMLPolygnos nodeType = XGMMLPolygnos.geDefault();
				if(configurations!=null && configurations.getAdvanceConfigurations() !=null && configurations.getAdvanceConfigurations().getXGMMLPolygnos()!=null &&
						configurations.getAdvanceConfigurations().getXGMMLPolygnos().containsKey(simplEnt.getClassID()))
				{
					nodeType = configurations.getAdvanceConfigurations().getXGMMLPolygnos().get(simplEnt.getClassID());
				}
				IGraphicsAtribute graphicsAtribute = new GraphicsAtributeImpl(1,klass.getColor(), "#000000",nodeType,writeWithAtribute,null, null);
				List<IAtributes> atributesList = new ArrayList<IAtributes>();
				String classe = klass.getName();
				atributesList.add(new AtributesImpl(null, "class", classe, "string", false, null));
				if(entity.getResourceElement() != null)
				{
					entityResourceInfo(configurations, dic, entity, simplEnt,atributesList,termIDTerm,termIDSynonyms,externalIDs);
				}
				INode node = new NodeImpl(nodeIDIncrement, simplEnt.getName(), simplEnt.getName(), atributesList , graphicsAtribute);
				mappingEntityNode.put(simplEnt,node);
				nodeIDIncrement ++;
			}
		}
		return annotDOc;
	}

	private void entityResourceInfo(IREToNetworkConfiguration configurations,
			IDictionary dic, IEntityAnnotation entity, SimpleEntity simplEnt,
			List<IAtributes> atributesList,Map<Long,IResourceElement> termIDTerm,
			Map<Long,List<String>> termIDSynonyms,
			Map<Long,List<IExternalID>> externalIDs) throws ANoteException {
		if(!termIDSynonyms.containsKey(simplEnt.getResourceElement()))
		{
			termIDSynonyms.put(simplEnt.getResourceElement().getId(), dic.getResourceElementByID(simplEnt.getResourceElement().getId()).getSynonyms());
		}
		if(!termIDTerm.containsKey(simplEnt.getResourceElement()))
		{
			termIDTerm.put(simplEnt.getResourceElement().getId(), dic.getResourceElementByID(simplEnt.getResourceElement().getId()));
		}
		if(!externalIDs.containsKey(simplEnt.getResourceElement().getId()))
		{
			List<IExternalID> externalIDsList = dic.getResourceElementByID(simplEnt.getResourceElement().getId()).getExtenalIDs();
			externalIDs.put(simplEnt.getResourceElement().getId(),externalIDsList);
		}
		IResourceElement elemTerm = termIDTerm.get(simplEnt.getResourceElement().getId());
		if(configurations!=null && configurations.getAdvanceConfigurations() != null && configurations.getAdvanceConfigurations().isEntitiesRepresentedByPrimaryName())
		{
			elemTerm = new ResourceElementImpl(elemTerm.getId(),simplEnt.getName(),elemTerm.getTermClass(),elemTerm.getExtenalIDs(),
					elemTerm.getSynonyms(),elemTerm.getPriority(),elemTerm.isActive());
			simplEnt.setName(elemTerm.getTerm());
		}	
		putSynonyms(entity, simplEnt, atributesList, termIDSynonyms.get(simplEnt.getResourceElement().getId()), elemTerm);
		putExternalIDs(atributesList, externalIDs.get(simplEnt.getResourceElement().getId()));
	}

	private void putSynonyms(IEntityAnnotation entity, SimpleEntity simplEnt,
			List<IAtributes> atributesList,
			List<String> list,
			IResourceElement elemTerm) {
		if(list.size()>0)
		{
			List<IAtributes> synonyms = new ArrayList<IAtributes>();
			for(String syn :list)
			{
				if(!syn.equals(entity.getAnnotationValue()))
					synonyms.add(new AtributesImpl("", syn, syn, "string", false, null));
			}
			if(elemTerm!=null && !elemTerm.getTerm().equals(simplEnt.getName()))
				synonyms.add(new AtributesImpl("", elemTerm.getTerm(), elemTerm.getTerm(), "string", false, null));
			atributesList.add(new AtributesImpl(null, "synonyms", "synonyms", "list", true, synonyms));
		}
	}

	private void putExternalIDs(List<IAtributes> atributesList,List<IExternalID> externalIDSource) {
		if(externalIDSource.size()>0)
		{
			List<IAtributes> extIdsAtrinutes = new ArrayList<IAtributes>();
			for(IExternalID extID :externalIDSource )
			{
				extIdsAtrinutes.add(new AtributesImpl(null,extID.getSource().getSource(), extID.getExternalID() + " ( "+extID.getSource()+" )", "string", false, null));
			}
			atributesList.add(new AtributesImpl(null, "External (Database) Ids", "External (Database) Ids", "list", true, extIdsAtrinutes ));
		}
	}


	
	private static INetwork<INode, IEdge> builtNetwork(boolean directed, IRESchema rePorcess,List<INode> nodes, List<IEdge> edges) {	
		String label = "ID : "+rePorcess.getId() + " ( "+rePorcess.getType() + " ) ";
		INetworkMetaData metaData = new NetworkMetaDataImpl("@Note2 - RE Schema", "",GregorianCalendar.getInstance().getTime(), "@Note2 - RE Schema "+label,"@Note2 - RE Schema", ".xgmml");
		String id = label;
		String version = "1.0";
		List<IAtributes> atributesList = new ArrayList<IAtributes>();
		return new NetworkImpl(id, nodes, edges, label, version, directed, metaData, atributesList);
	}
	
	protected void memoryAndProgressAndTime(int step, int total,long startTime) {
		System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
		Runtime.getRuntime().gc();
		System.out.println((Runtime.getRuntime().totalMemory()- Runtime.getRuntime().freeMemory())/(1024*1024) + " MB ");
	}

	public void setcancel() {
		stop = true;
	}

}
