package com.silicolife.textmining.core.datastructures.dataaccess.daemon;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.annotation.AnnotationAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.clustering.ClusteringAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.corpora.CorpusAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.general.ClassesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.hyperlinks.HyperlinkAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.LuceneAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.Corpus.LuceneCorpusAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.publications.LucenePublicationsAcessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.queries.LuceneQueriesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources.LuceneResourcesAcessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources.LuceneResourcesElementsAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.processes.ProcessesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.publicationmanager.PublicationsAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.publicationmanager.QueryAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.resources.ResourcesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.resources.ResourcesElementsAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.runserverprocesses.RunSercerProcessesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.user.UserAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.user.UserPrevilegesAccessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.RestClient;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLoggedImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

/**
 * All operations to access Daemon
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class DaemonAccess implements IDataAccess {

	private IDaemon daemonConfigurations;
	private UsersLogged userLogged;
	private RestClient webClient = null;
	private QueryAccessImpl queryAccessImpl;
	private PublicationsAccessImpl publicationsAccessimpl;
	private ClusteringAccessImpl clusteringAccessimpl;
	private CorpusAccessImpl corpusAccessImpl;
	private ProcessesAccessImpl processAccessImpl;
	private AnnotationAccessImpl annotationAccessImpl;
	private ClassesAccessImpl classesAccessImpl;
	private UserAccessImpl userAccessImpl;
	private UserPrevilegesAccessImpl userAccessPrivilegesImpl;
	private HyperlinkAccessImpl hyperLinkAccessImpl;
	private ResourcesAccessImpl resourcesAccessImpl;
	private ResourcesElementsAccessImpl resourcesElementAccessImpl;
	private RunSercerProcessesAccessImpl runServerProcessesImpl;
	private LuceneAccessImpl luceneAccessImpl;
	private LuceneResourcesElementsAccessImpl luceneResourcesElementsAccessImpl;
	private LuceneQueriesAccessImpl luceneQueriesAccessImpl;
	private LucenePublicationsAcessImpl lucenePublicationsAcessImpl;
	private LuceneCorpusAccessImpl luceneCorpusAcessImpl;
	private LuceneResourcesAcessImpl luceneResourcesAccessImpl;

	public DaemonAccess(IDaemon daemonConfigurations) {
		this.daemonConfigurations = daemonConfigurations;
		queryAccessImpl = new QueryAccessImpl();
		publicationsAccessimpl = new PublicationsAccessImpl();
		clusteringAccessimpl = new ClusteringAccessImpl();
		corpusAccessImpl = new CorpusAccessImpl();
		processAccessImpl = new ProcessesAccessImpl();
		annotationAccessImpl = new AnnotationAccessImpl();
		classesAccessImpl = new ClassesAccessImpl();
		hyperLinkAccessImpl = new HyperlinkAccessImpl();
		resourcesAccessImpl = new ResourcesAccessImpl();
		resourcesElementAccessImpl = new ResourcesElementsAccessImpl();
		userAccessImpl = new UserAccessImpl();
		userAccessPrivilegesImpl = new UserPrevilegesAccessImpl();
		userLogged = new UsersLoggedImpl();
		runServerProcessesImpl = new RunSercerProcessesAccessImpl();
		luceneAccessImpl = new LuceneAccessImpl();
		luceneResourcesElementsAccessImpl = new LuceneResourcesElementsAccessImpl();
		luceneQueriesAccessImpl = new LuceneQueriesAccessImpl();
		lucenePublicationsAcessImpl = new LucenePublicationsAcessImpl();
		luceneCorpusAcessImpl = new LuceneCorpusAccessImpl();
		luceneResourcesAccessImpl = new LuceneResourcesAcessImpl();
	}

	public IDaemon getDaemonConfigurations() {
		return daemonConfigurations;
	}

	/**
	 * Get web service client instance
	 * 
	 * @return
	 * @throws DaemonException
	 */
	private void startDaemonClient() throws ANoteException {
		if (this.webClient == null) {
			this.webClient = RestClient.getInstance(daemonConfigurations.getProtocol(), daemonConfigurations.getUrl(), daemonConfigurations.getPort(),
					daemonConfigurations.getWebPath());

			queryAccessImpl.setRestClient(webClient);
			publicationsAccessimpl.setRestClient(webClient);
			clusteringAccessimpl.setRestClient(webClient);
			corpusAccessImpl.setRestClient(webClient);
			userAccessImpl.setRestClient(webClient);
			userAccessPrivilegesImpl.setRestClient(webClient);
			processAccessImpl.setRestClient(webClient);
			annotationAccessImpl.setRestClient(webClient);
			classesAccessImpl.setRestClient(webClient);
			hyperLinkAccessImpl.setRestClient(webClient);
			resourcesAccessImpl.setRestClient(webClient);
			resourcesElementAccessImpl.setRestClient(webClient);
			runServerProcessesImpl.setRestClient(webClient);
			luceneAccessImpl.setRestClient(webClient);
			luceneResourcesElementsAccessImpl.setRestClient(webClient);
			luceneQueriesAccessImpl.setRestClient(webClient);
			luceneCorpusAcessImpl.setRestClient(webClient);
			luceneResourcesAccessImpl.setRestClient(webClient);
			initConnection();
		}
	}
	
	private void initConnection() throws ANoteException{
		Boolean connection = testConnection(daemonConfigurations);
		if(!connection){
			throw new DaemonException(new UnknownHostException());
		}
		
		ParameterizedTypeReference<DaemonResponse<String>> responseType = new ParameterizedTypeReference<DaemonResponse<String>>() {};
		
		ResponseEntity<DaemonResponse<String>> responde = webClient.get("getVersion", responseType);
		
		if(responde.getStatusCode()!=org.springframework.http.HttpStatus.OK){
			throw new DaemonException(new UnknownHostException());
		}
	}

	@Override
	public Boolean checkLogin(String username, String password) throws ANoteException {
		startDaemonClient();
		return userAccessImpl.checkLogin(username, password);
	}

	@Override
	public void login(String username, String password) throws ANoteException {
		startDaemonClient();
		IUser user = userAccessImpl.login(username, password);
		if (user != null) {
			this.userLogged.setCurrentUserLogged((AuthUsers) user);
		} else {
			this.userLogged.setCurrentUserLogged(null);
		}
	}

	@Override
	public void logout() throws ANoteException {
		userAccessImpl.logout();
		this.userLogged.setCurrentUserLogged(null);
	}

	@Override
	public void addUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource, PermissionsUtilsEnum privilege) throws ANoteException {
		userAccessPrivilegesImpl.addPrivileges(user.getAuId(), resourceId, resource.getName(), privilege.getName());
	}

	@Override
	public void updateUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource, PermissionsUtilsEnum privilege) throws ANoteException {
		userAccessPrivilegesImpl.updatePrivileges(user.getAuId(), resourceId, resource.getName(), privilege.getName());
	}

	@Override
	public void removeUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		userAccessPrivilegesImpl.deletePrivilegesForUser(user.getAuId(), resourceId, resource.getName());
	}

	@Override
	public void createUser(IUser user) throws ANoteException {
		userAccessImpl.createUser(user);

	}

	@Override
	public void updateUser(IUser user) throws ANoteException {
		userAccessImpl.updateUser(user);

	}

	@Override
	public void removeUser(IUser user) throws ANoteException {
		userAccessImpl.deleteUser(user.getAuId());
	}

	@Override
	public IUser getUserByEmail(String email) throws ANoteException {
		IUser user = userAccessImpl.getUserByEmail(email);
		return user;
	}

	@Override
	public IUser getUserByUsername(String username) throws ANoteException {
		IUser user = userAccessImpl.getUserByUsername(username);
		return user;
	}

	@Override
	public List<IGroup> getAllGroups() throws ANoteException {
		List<IGroup> groups = userAccessImpl.getAllGroups();
		return groups;
	}

	@Override
	public List<IUser> getAllUsers() throws ANoteException {
		List<IUser> users = userAccessImpl.getAllUsers();
		return users;
	}

	@Override
	public Set<String> getQueryPublicationsExternalIDFromSource(IQuery query, String source) throws ANoteException {
		Set<String> response = queryAccessImpl.getQueryPublicationsExternalIDFromSource(query.getId(), source);
		return response;
	}

	@Override
	public IUserDataObject getUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		IUserDataObject userDataObject = userAccessPrivilegesImpl.getUserDataObjectPrivilege(user.getAuId(), resourceId, resource.getName());
		return userDataObject;
	}

	@Override
	public List<IPublication> getQueryPublications(IQuery query) throws ANoteException {
		List<IPublication> response = queryAccessImpl.getQueryPublications(query.getId());
		return response;
	}

	@Override
	public void updateQueryDocumentRelevance(IPublication publication, IQuery query, IQueryPublicationRelevance relevance) throws ANoteException {
		queryAccessImpl.updateRelevance(publication.getId(), query.getId(), relevance.getRelevance().toString());
	}

	@Override
	public synchronized void addQueryPublications(IQuery query, Set<IPublication> publications) throws ANoteException {
		Set<Long> pubIds = new HashSet<Long>();
		for (IPublication pub : publications)
			pubIds.add(pub.getId());
		queryAccessImpl.addPublicationsToQuery(query.getId(), pubIds);
	}

	@Override
	public synchronized void createQuery(IQuery query) throws ANoteException {
		queryAccessImpl.createQuery(query);
	}

	@Override
	public synchronized void updateQuery(IQuery query) throws ANoteException {
		queryAccessImpl.updateQuery(query);
	}

	@Override
	public void inactiveQuery(IQuery query) throws ANoteException {

	}

	@Override
	public List<IQuery> getAllQueries() throws ANoteException {
		List<IQuery> response = queryAccessImpl.getAllQueries();
		return response;
	}

	@Override
	public List<IQuery> getPrivilegesAllQueriesAdminAccess() throws ANoteException {
		List<IQuery> response = queryAccessImpl.getAllPrivilegesQueriesAccessAdmin();
		return response;
	}

	@Override
	public IQuery getQueryByID(long queryID) throws ANoteException {
		IQuery response = queryAccessImpl.getQueryByID(queryID);
		return response;
	}

	@Override
	public void createClustering(IClusterProcess clustering) throws ANoteException {
		clusteringAccessimpl.createClustering(clustering);
	}

	@Override
	public List<IClusterProcess> getQueryClusters(IQuery query) throws ANoteException {
		List<IClusterProcess> clusterProcesses = clusteringAccessimpl.getClustersFromQuery(query.getId());
		return clusterProcesses;
	}

	@Override
	public IClusterProcess getClusterByID(long clusterID) throws ANoteException {
		IClusterProcess clusterProcess = clusteringAccessimpl.getClusteringByID(clusterID);
		return clusterProcess;
	}

	@Override
	public void inactivateCluster(IClusterProcess clustering) throws ANoteException {

	}

	public synchronized void addPublications(Set<IPublication> documents) throws ANoteException {
		publicationsAccessimpl.addPublications(documents);
	}

	@Override
	public IPublication getPublication(long documentID) throws ANoteException {
		IPublication publication = publicationsAccessimpl.getPublicationById(documentID);
		return publication;
	}

	@Override
	public synchronized void updatePublication(IPublication publication) throws ANoteException {
		publicationsAccessimpl.updatePublication(publication);
	}

	@Override
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws ANoteException {
		Map<String, Long> publicationsExternalIds = publicationsAccessimpl.getAllPublicationsExternalIDFromSource(source);
		return publicationsExternalIds;
	}

	@Override
	public String getPublicationFullText(IPublication publication) throws ANoteException {
		String fullText = publicationsAccessimpl.getPublicationFullText(publication.getId());
		return fullText;
	}

	@Override
	public void createCorpus(ICorpus corpus) throws ANoteException {
		 corpusAccessImpl.createCorpus(corpus);
	}

	@Override
	public void updateCorpus(ICorpus corpus) throws ANoteException {
		 corpusAccessImpl.updateCorpus(corpus);
	}
	
	@Override
	public void updateCorpusStatus(ICorpus corpus, boolean status) throws ANoteException {
		corpusAccessImpl.updateCorpusStatus(corpus, status);
		
	}

	@Override
	public void inactivateCorpus(ICorpus corpus) throws ANoteException {
		userAccessPrivilegesImpl.deletePrivileges(corpus.getId(), ResourcesTypeUtils.corpus.getName());
	}

	@Override
	public List<ICorpus> getAllCorpus() throws ANoteException {
		 List<ICorpus> corpus = corpusAccessImpl.getAllCorpus();
		 return corpus;
	}

	@Override
	public ICorpus getCorpusByID(long corpusId) throws ANoteException {
		ICorpus corpus = corpusAccessImpl.getCorpusById(corpusId);
		return corpus;
	}

	@Override
	public void registerCorpusProcess(ICorpus corpus, IIEProcess process) throws ANoteException {
		 corpusAccessImpl.registerCorpusProcess(corpus.getId(), process.getId());
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(ICorpus corpus) throws ANoteException {
		List<IIEProcess> processes = corpusAccessImpl.getCorpusProcesses(corpus.getId());
		return processes;
	}

	@Override
	public IDocumentSet getCorpusPublications(ICorpus corpus) throws ANoteException {
		IDocumentSet documentSet = corpusAccessImpl.getCorpusPublications(corpus.getId());
		 return documentSet;
	}

	@Override
	public Long getCorpusPublicationsCount(ICorpus corpus) throws ANoteException {
		Long count = corpusAccessImpl.getCorpusPublicationsCount(corpus.getId());
		return count;
	}

	@Override
	public IDocumentSet getCorpusPublicationsPaginated(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		IDocumentSet documentSet = corpusAccessImpl.getCorpusPublicationsPaginated(corpus.getId(), paginationIndex, paginationSize);
		return documentSet;
	}

	@Override
	public void createIEProcess(IIEProcess processe) throws ANoteException {
		processAccessImpl.createIEProcess(processe);
	}

	@Override
	public void updateIEProcess(IIEProcess process) throws ANoteException {
		processAccessImpl.updateIEProcess(process);
	}

	@Override
	public void inactivateProcess(IIEProcess process) throws ANoteException {
		userAccessPrivilegesImpl.deletePrivileges(process.getId(), ResourcesTypeUtils.processes.getName());
	}

	@Override
	public IIEProcess getProcessByID(long processID) throws ANoteException {
		return processAccessImpl.getProcessByID(processID);
	}

	@Override
	public List<IEventAnnotation> getAnnotatedDocumentEvents(IAnnotatedDocument annotedDocument) throws ANoteException {
		return annotationAccessImpl.getProcessDoumentAnnotationEvents(annotedDocument.getProcess().getId(), annotedDocument.getId());
	}
	
	@Override
	public Long countAnnotations(IIEProcess process, IResourceElement resourceElement) throws ANoteException {
		return annotationAccessImpl.countAnnotations(process.getId(), resourceElement.getId());
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntities(IAnnotatedDocument annotedDocument) throws ANoteException {
		return annotationAccessImpl.getProcessDoumentAnnotationEntities(annotedDocument.getProcess().getId(), annotedDocument.getId());
	}
	
	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntitiesFilteredByResourceElement(IIEProcess process,IPublication publication,IResourceElement resourceElement) throws ANoteException {
		return annotationAccessImpl.getProcessDoumentAnnotationEntitiesFilteredByResourceElement(process.getId(), publication.getId(), resourceElement.getId());
	}

	@Override
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(IIEProcess process,IPublication publication,ISentence sentence) throws ANoteException {
		return annotationAccessImpl.getProcessDoumentAnnotationEntitiesOfSentence(process.getId(), publication.getId(), sentence);
	}
	
	@Override
	public void addProcessDocumentEntitiesAnnotations(IIEProcess schema, IPublication document, List<IEntityAnnotation> entityAnnotations) throws ANoteException {
		annotationAccessImpl.addCorpusProcessDocumentEntityAnootations(schema.getCorpus().getId(), schema.getId(), document.getId(), entityAnnotations);
	}

	@Override
	public void addProcessDocumentEventAnnoations(IIEProcess process, IPublication document, List<IEventAnnotation> events) throws ANoteException {
		annotationAccessImpl.addCorpusProcessDocumentEventsAnootations(process.getCorpus().getId(), process.getId(), document.getId(), events);
	}

	@Override
	public void inactiveAnnotations(List<Long> annotation) throws ANoteException {
		annotationAccessImpl.inactiveAnnotations(annotation);
	}

	@Override
	public void updateEntityAnnotations(List<IEntityAnnotation> list) throws ANoteException {
		annotationAccessImpl.updateEntityAnnotations(list);
	}

	@Override
	public void updateEventsAnnotations(List<IEventAnnotation> list) throws ANoteException {
	}

	@Override
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(IIEProcess process, IPublication document) throws ANoteException {
		return annotationAccessImpl.getProcessDocumentLogs(process.getId(), document.getId());
	}

	@Override
	public SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws ANoteException {
		return annotationAccessImpl.getProcessLogs(process.getId());
	}


	@Override
	public List<IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws ANoteException {
		return annotationAccessImpl.getProcessDocumentAnnotationsAssociatedToLogs(ieProcess.getId());
	}

	@Override
	public void createResource(IResource<IResourceElement> resource) throws ANoteException {
		resourcesAccessImpl.createResource(resource);
	}

	@Override
	public void updateResource(IResource<IResourceElement> resource) throws ANoteException {
		resourcesAccessImpl.updateResource(resource);
	}

	@Override
	public void inativeResource(IResource<IResourceElement> resource) throws ANoteException {
	
	}

	@Override
	public List<IResource<IResourceElement>> getPrivilegesAllResourcesAdminAccess() throws ANoteException {
		return resourcesAccessImpl.getAllPrivilegesResourcesAdminAccess();
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesByType(String resourceType) throws ANoteException {
		return resourcesAccessImpl.getResourcesByType(resourceType);
	}

	@Override
	public IResource<IResourceElement> getResourceByID(long resourceId) throws ANoteException {
		return resourcesAccessImpl.getResourceById(resourceId);
	}

	@Override
	public Set<IAnoteClass> getResourceClassContent(IResource<IResourceElement> resource) throws ANoteException {
		return resourcesElementAccessImpl.getResourceClassContent(resource.getId());
	}

	@Override
	public boolean checkResourceHasElements(IResource<IResourceElement> resource) throws ANoteException {
		return false;
	}

	@Override
	public IResourceManagerReport addResourceElements(IResource<IResourceElement> resource, List<IResourceElement> elememts) throws ANoteException {
		return resourcesElementAccessImpl.addResourceElements(resource.getId(), elememts);
	}

	@Override
	public IResourceManagerReport addResourceElementExternalIds(IResource<IResourceElement> resource, IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {
		return resourcesElementAccessImpl.addResourceElementExternalIDs(resource.getId(), elem.getId(), externalIDs);
	}

	@Override
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {
		return resourcesElementAccessImpl.updateResourceElement(elem);
	}

	@Override
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {
		resourcesElementAccessImpl.inactivateResourceElement(elem.getId());
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElements(IResource<IResourceElement> resource) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElements(resource.getId());
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IResource<IResourceElement> resource, IAnoteClass Klass) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElementsByClass(resource.getId(), Klass.getName());
	}

	@Override
	public void removeResourceClass(IResource<IResourceElement> resource, long classID) throws ANoteException {
		resourcesElementAccessImpl.removeResourceClass(resource.getId(), classID);
	}

	@Override
	public IResourceElement getResourceElementByID(long resourceID) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElemenByID(resourceID);
	}

	@Override
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException {
		resourcesElementAccessImpl.removeResourceElementSynonyms(elem.getId());
	}

	@Override
	public void removeResourceElementSynonym(IResourceElement elem, String synonym) throws ANoteException {
		resourcesElementAccessImpl.removeResourceElementSynonym(elem.getId(), synonym);
	}

	@Override
	public void addResourceElementsRelation(IResourceElement a, IResourceElement b, String relationType) throws ANoteException {
		resourcesElementAccessImpl.addResourceElementsRelation(a.getId(), b.getId(), relationType);
	}

	@Override
	public IUser getUser() {
		return userLogged.getCurrentUserLogged();
	}

	@Override
	public void setUser(UsersLogged userLogged) throws ANoteException {
		if (this.userLogged.getCurrentUserLogged() == null) {
			login(userLogged.getCurrentUserLogged().getAuUsername(), userLogged.getCurrentUserLogged().getAuPassword());
		}
		else
		{
			login(userLogged.getCurrentUserLogged().getAuUsername(), userLogged.getCurrentUserLogged().getAuPassword());
		}
	}

	@Override
	public int getDatabaseVersion() throws ANoteException {
		return 0;
	}

	@Override
	public boolean addDatabaseVersion(int version, String commments) throws ANoteException {
		return false;
	}

	@Override
	public Set<String> getIEStatiticsAllLemmas() throws ANoteException {
		return null;
	}

	@Override
	public SortedSet<IRelationsType> getIEStatiticsAllDefaultRelationsType() throws ANoteException {
		return null;
	}

	@Override
	public Boolean hasPermissionRole(List<String> roles) throws ANoteException {
		Boolean hasPermission = userAccessImpl.hasPermissionRole(roles);
		return hasPermission;
	}

	@Override
	public Boolean hasPermission(IQuery query, List<String> permissions) throws ANoteException {
		Boolean hasPermission = userAccessPrivilegesImpl.hasPermission(query.getId(), ResourcesTypeUtils.queries.getName(), permissions);
		return hasPermission;
	}

	@Override
	public Boolean hasPermission(IResource<IResourceElement> resource, List<String> permissions) throws ANoteException {
		Boolean hasPermission = userAccessPrivilegesImpl.hasPermission(resource.getId(), ResourcesTypeUtils.resources.getName(), permissions);
		return hasPermission;
	}

	@Override
	public String toString() {
		return daemonConfigurations.toString();
	}

	@Override
	public List<IPublicationLabel> getAllLabels() throws ANoteException {
		List<IPublicationLabel> pubLabels = publicationsAccessimpl.getAllPublicationsLabels();
		return pubLabels;
	}

	@Override
	public void updatePublicationAvailableFreeFullText(IPublication pub) throws ANoteException {
		publicationsAccessimpl.updatePublicationAvailableFreeFullText(pub.getId(), pub.isFreeFullText());
	}

	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		List<IGenericPair<IUser, String>> usersAndPermissions = userAccessPrivilegesImpl.getUsersAndPrivilegers(resourceId, resource.getName());
		return usersAndPermissions;
	}

	@Override
	public void removeQuery(IQuery query) throws ANoteException {
		userAccessPrivilegesImpl.deletePrivileges(query.getId(), ResourcesTypeUtils.queries.getName());
	}

	@Override
	public void removeResource(IResource<IResourceElement> resource) throws ANoteException {
		userAccessPrivilegesImpl.deletePrivileges(resource.getId(), ResourcesTypeUtils.resources.getName());
	}

	@Override
	public int getResourceMaxPriority(IResource<IResourceElement> resource) throws ANoteException {
		return resourcesElementAccessImpl.getResourceMaxPriorety(resource.getId());
	}

	@Override
	public IResourceContent getResourceContent(IResource<IResourceElement> resource) throws ANoteException {
		return resourcesElementAccessImpl.getResourceContent(resource.getId());
	}

	@Override
	public List<IExternalID> getResourceElementExternalIds(IResourceElement resourceElementImpl) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElementExternalIDs(resourceElementImpl.getId());
	}

	@Override
	public void insertNewClass(IAnoteClass klass) throws ANoteException {
		classesAccessImpl.insertNewClass(klass);
	}

	@Override
	public Set<IAnoteClass> getClasses() throws ANoteException {
		return classesAccessImpl.getClasses();
	}

	@Override
	public boolean updateClassName(IAnoteClass oldclassName, String newCLass) throws ANoteException {
		return false;
	}

	@Override
	public IResourceManagerReport addResourceElementsWithouValidation(IResource<IResourceElement> resource, List<IResourceElement> element) throws ANoteException {
		return resourcesElementAccessImpl.addResourceElementsWithoutValidation(resource.getId(), element);
	}

	@Override
	public boolean checkResourceElementExistsInResource(IResource<IResourceElement> resource, String term) throws ANoteException {
		return resourcesElementAccessImpl.checkResourceElementExistsInResource(resource.getId(), term);
	}

	@Override
	public IResourceManagerReport addResourceElementSynomyns(IResource<IResourceElement> resource, IResourceElement elem, List<String> synonyms) throws ANoteException {
		return resourcesElementAccessImpl.addResourceElementSynonyms(resource.getId(), elem.getId(), synonyms);
	}

	@Override
	public IResourceManagerReport updateResourceElementSynonym(IResource<IResourceElement> resource, IResourceElement elem, String oldSynonym, String newSynonym) throws ANoteException {
		return resourcesElementAccessImpl.updateResourceElementSynonym(resource.getId(), elem.getId(), oldSynonym, newSynonym);
	}

	@Override
	public void removeResourceElementExternalID(IResourceElement element, IExternalID extID) throws ANoteException {
		resourcesElementAccessImpl.removeResourceElementExternalID(element.getId(), extID);
	}

	@Override
	public void removeResourceElementAllExternalID(IResourceElement element) throws ANoteException {
		resourcesElementAccessImpl.removeResourceElementAllExternalID(element.getId());
	}

	@Override
	public List<IResourceElementsRelation> getResourceElementsRelations(IResource<IResourceElement> resource) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElementsRelations(resource.getId());
	}

	@Override
	public void addCorpusPublication(ICorpus corpus, IPublication publication) throws ANoteException {
		corpusAccessImpl.addCorpusPublication(corpus.getId(), publication.getId());
	}

	@Override
	public ICorpusStatistics getCorpusStatistics(ICorpus corpus) throws ANoteException {
		return corpusAccessImpl.getCorpusStatistics(corpus.getId());
	}

	@Override
	public void updatePublicationFullTextContent(IPublication publication) throws ANoteException {
		publicationsAccessimpl.updatePublicationFullTextContent(publication.getId(), publication.getFullTextContent());
	}

	@Override
	public Boolean hasPermission(ICorpus corpus, List<String> permissions) throws ANoteException {
		Boolean hasPermission = userAccessPrivilegesImpl.hasPermission(corpus.getId(), ResourcesTypeUtils.corpus.getName(), permissions);
		return hasPermission;
	}

	@Override
	public void updateAnoteKlass(IAnoteClass klass) throws ANoteException {		
		classesAccessImpl.updateClass(klass);
	}

	@Override
	public IIEProcessStatistics getIEProcessStatistics(IIEProcess ieProcessImpl) throws ANoteException {
		return processAccessImpl.getIEProcessStatistics(ieProcessImpl.getId());
	}

	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(IAnnotatedDocument annotatedDocument) throws ANoteException {
		return annotationAccessImpl.getProcessDocumentStatistics(annotatedDocument.getId(), annotatedDocument.getProcess().getId());
	}

	@Override
	public IResource<IResourceElement> getResourceFromResourceElementByID(Long resourceElementID) throws ANoteException {
		return resourcesElementAccessImpl.getResourceFromResourceElement(resourceElementID);
	}

	@Override
	public Boolean hasPermission(IIEProcess obj, List<String> fullgrant) throws ANoteException {
		Boolean hasPermission = userAccessPrivilegesImpl.hasPermission(obj.getId(), ResourcesTypeUtils.processes.getName(), fullgrant);
		return hasPermission;
	}

	@Override
	public void addProcessDocumentLogs(List<IAnnotationLog> annotationLogs)throws ANoteException {
		annotationAccessImpl.addAnnotationLogs(annotationLogs);
	}

	@Override
	public long getResourceElementIDMathingByText(IResource<IResourceElement> resource, IResourceElement elemennt, String text, boolean casesensitive) throws ANoteException {
		return 0;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByName(IResource<IResourceElement> resource, String name)throws ANoteException {
		return resourcesElementAccessImpl.getResourceElementsByName(resource.getId(), name);
	}

	@Override
	public IHyperLinkMenuItem getHyperLinkMenuItemById(Long id)throws ANoteException {
		return hyperLinkAccessImpl.getHyperLinkMenuItemById(id);
	}

	@Override
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() throws ANoteException {
		return hyperLinkAccessImpl.getAllHyperLinkMenuItems();
	}

	@Override
	public void updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {		
		hyperLinkAccessImpl.updateSourcesHyperLinkMenuItem(hyperLinkMenuItem);
	}

	@Override
	public List<ISource> getAllSources() throws ANoteException {
		return hyperLinkAccessImpl.getAllSources();
	}

	@Override
	public List<ICorpus> getPrivilegesAllCorpusAdminAccess() throws ANoteException {
		return corpusAccessImpl.getAllPrivilegesCorpusAdminAccess();
	}

	@Override
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws ANoteException {
		return processAccessImpl.getPrivilegesAllProcessesAdminAccess();
	}

	@Override
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(ISource source) {
		return null;
	}

	@Override
	public void saveProperties(Properties properties) throws ANoteException{
		userAccessImpl.saveProperties(properties);
	}

	@Override
	public Properties loadProperties(Set<String> propertiesIdentifiers) throws ANoteException {
		return userAccessImpl.loadProperties(propertiesIdentifiers);
	}

	@Override
	public void addClusteringLabels(IClusterProcess cluster) throws ANoteException {
		clusteringAccessimpl.addClusteringLabels(cluster.getId(), cluster.getClusterLabels());
		
	}

	@Override
	public void registerQueryClustering(IQuery query, IClusterProcess clustering) throws ANoteException {
		clusteringAccessimpl.clusterProcessQueryRegistry(query.getId(), clustering.getId());
	}

	@Override
	public void updateQueryClusteringProcess(IClusterProcess clustering) throws ANoteException {
		clusteringAccessimpl.updateQueryClusteringProcess(clustering);
	}
	
	public static Boolean testConnection(IDaemon daemonCOnfiguration)
	{
		RestClient rc = null;
		try {
			rc = RestClient.newInstance(daemonCOnfiguration.getProtocol(), daemonCOnfiguration.getUrl(), daemonCOnfiguration.getPort(), daemonCOnfiguration.getWebPath());
			rc.getTemplate().setMaxTry(1);
			ParameterizedTypeReference<DaemonResponse<String>> responseType = new ParameterizedTypeReference<DaemonResponse<String>>() {
				
			};
			ResponseEntity<DaemonResponse<String>> responde = rc.get("getVersion", responseType);
			if(responde.getStatusCode()==org.springframework.http.HttpStatus.OK)
			{
				rc.getTemplate().setMaxTry(4);
				return true;	
			}
		} catch (DaemonException e) {
			rc.getTemplate().setMaxTry(4);
			return false;
		}
		rc.getTemplate().setMaxTry(4);
		return false;
	}

	@Override
	public Boolean runServerProcesses(String configurationUID,String configurationJson) throws ANoteException {
		return runServerProcessesImpl.runSercerProcesses(configurationUID,configurationJson);
		
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(
			IResource<IResourceElement> resource, Integer index, Integer pagination) throws ANoteException {
		return resourcesElementAccessImpl.getResourceElementsInBatchWithLimit(resource.getId(), index, pagination);
	}

	@Override
	public Set<String> getCorpusPublicationsExternalIDFromSource(ICorpus corpus, String source) throws ANoteException {
		return corpusAccessImpl.getCorpusPublicationsExternalIDFromSource(corpus.getId(), source);
	}

	@Override
	public Long countCorpusPublicationsNotProcessed(IIEProcess process) throws ANoteException {
		return corpusAccessImpl.countCorpusPublicationsNotProcessed(process.getCorpus().getId(), process.getId());
	}
	
	@Override
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {
		return corpusAccessImpl.getCorpusPublicationsNotProcessedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
	}
	

	@Override
	public ISentence getSentence(IEntityAnnotation entityAnnotation) throws ANoteException, IOException {
		long entityAnnotationId = entityAnnotation.getId();
		return annotationAccessImpl.getSentence(entityAnnotationId);
	}
	
	@Override
	public List<Long> getPublicationsIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements)
			resourceElementsIds.add(resourceElement.getId());
		return annotationAccessImpl.getPublicationsIdsByResourceElements(resourceElementsIds);
	}
	
	@Override
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(Set<IResourceElement> resourceElements, IPublicationFilter pubFilter) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements)
			resourceElementsIds.add(resourceElement.getId());
		return annotationAccessImpl.getPublicationsIdsByResourceElementsFilteredByPublicationFilter(resourceElementsIds, pubFilter);
	}
	
	@Override
	public Long countCorpusPublicationsOutdated(IIEProcess process) throws ANoteException {
		return corpusAccessImpl.countCorpusPublicationsOutdated(process.getCorpus().getId(), process.getId());
	}

	@Override
	public IDocumentSet getCorpusPublicationsOutdatedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {
		return corpusAccessImpl.getCorpusPublicationsOutdatedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
	}

	@Override
	public boolean rebuildLuceneIndex() throws ANoteException {
		return luceneAccessImpl.rebuildLuceneIndex();
	}

	@Override
	public IResourceManagerReport addResourceElementSynomynsWithoutValidation(IResource<IResourceElement> destiny,
			IResourceElement originalElem, List<String> synonymsToAdd) throws ANoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID externalId)
			throws ANoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByExactTerm(term);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(
			IResourceElementsFilter filter, String term) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByExactTerm(filter, term);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String term) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialTerm(term);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String term, int index,
			int paginationSize) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialTermPaginated(term, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(
			IResourceElementsFilter filter, String partialTerm) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialTerm(filter, partialTerm);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(
			IResourceElementsFilter filter, String partialTerm, int index, int paginationSize)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialTermPaginated(filter, partialTerm, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByExactSynonym(synonym);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(
			IResourceElementsFilter filter, String synonym) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByExactSynonym(filter, synonym);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String synonym)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialSynonym(synonym);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String synonym,
			int index, int paginationSize) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialSynonymPaginated(synonym, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(
			IResourceElementsFilter filter, String partialSynonym) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialSynonym(filter, partialSynonym);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(IResourceElementsFilter filter,
			String partialSynonym, int index, int paginationSize)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialSynonymPaginated(filter, partialSynonym, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalId(String externalId)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByExactExternalID(externalId);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalId(IResourceElementsFilter filter, String externalId) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByExactExternalID(filter, externalId);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalId(String partialExternalId)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialExternalID(partialExternalId);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalId(
			IResourceElementsFilter filter, String partialExternalId) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialExternalID(filter, partialExternalId);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdPaginated(
			String partialExternalId, int index, int paginationSize) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialExternalIDPaginated(partialExternalId, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIdPaginated(
			IResourceElementsFilter filter, String partialExternalId, int index, int paginationSize)
			throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredByPartialExternalIDPaginated(filter, partialExternalId, index, paginationSize);
	}

	@Override
	public void setUserLoggedOnServices(UsersLogged userLogged) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getResourceElementsCountByPartialTerm(String partialTerm) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsCountByPartialTerm(partialTerm);
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter,
			String partialTerm) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredCountByPartialTerm(filter, partialTerm);
	}

	@Override
	public Integer getResourceElementsCountByPartialSynonym(String partialSynonym) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsCountByPartialSynonym(partialSynonym);
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter,
			String partialSynonym) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredCountByPartialSynonym(filter, partialSynonym);
	}

	@Override
	public Integer getResourceElementsCountByPartialExternalID(String partialExternalId) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsCountByPartialExternalID(partialExternalId);
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialExternalID(
			IResourceElementsFilter filter, String partialExternalId) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsFilteredCountByPartialExternalID(filter, partialExternalId);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(
			String partialString, int index, int paginationSize) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsByPartialTermOrPartialSynonymPaginated(partialString, index, paginationSize);
	}

	@Override
	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString) throws ANoteException {
		return luceneResourcesElementsAccessImpl.getResourceElementsCountByPartialTermOrPartialSynonym(partialString);
	}

	@Override
	public List<Long> getProcessesIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements)
			resourceElementsIds.add(resourceElement.getId());
		return annotationAccessImpl.getProcessesIdsByResourceElements(resourceElementsIds);
	}

	@Override
	public List<IIEProcess> getProcessesByPublication(IPublication publication) throws ANoteException {
		return processAccessImpl.getProcessesByPublication(publication);
	}

	@Override
	public Set<ICorpus> getCorpusByPublication(IPublication publication) throws ANoteException {
		return corpusAccessImpl.getCorpusByPublicationId(publication.getId());
	}

	@Override
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws ANoteException {
		return annotationAccessImpl.getPublicationsIdsByAnnotationsFilter(filter);
	}

	@Override
	public Boolean removeAllProcessDocumentAnnotations(IIEProcess process, IPublication document) throws ANoteException {
		return annotationAccessImpl.removeAllProcessDocumentAnnotations(process.getId(),document.getId());	
	}

	@Override
	public Boolean autoupdate() throws ANoteException {
		return runServerProcessesImpl.autoupdate();
	}

	@Override
	public Long getNextHyperLinkMenuItemID() throws ANoteException {
		return hyperLinkAccessImpl.getNextHyperLinkMenuItemID();
	}

	@Override
	public void addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		hyperLinkAccessImpl.addHyperLinkMenuItem(hyperLinkMenuItem);
		
	}

	@Override
	public void removeHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		hyperLinkAccessImpl.removeHyperLinkMenuItem(hyperLinkMenuItem.getId());
		
	}

	@Override
	public void updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		hyperLinkAccessImpl.updateHyperLinkMenuItem(hyperLinkMenuItem);
		
	}

	public List<IQuery> getQueriesByName(String name) throws ANoteException {
		return luceneQueriesAccessImpl.getQueriesByName(name);
	}

	@Override
	public List<IQuery> getQueriesByOrganism(String organism) throws ANoteException {
		return luceneQueriesAccessImpl.getQueriesByOrganism(organism);
	}

	@Override
	public List<IQuery> getQueriesBykeywords(String keywords) throws ANoteException {
		return luceneQueriesAccessImpl.getQueriesBykeywords(keywords);
	}

	@Override
	public List<IQuery> getQueriesKeywordsByWildCard(String subKeyword) throws ANoteException {
		return luceneQueriesAccessImpl.getQueriesKeywordsByWildCard(subKeyword);
	}

	@Override
	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword) throws ANoteException {
		return luceneQueriesAccessImpl.getKeywordsOfQueriesByWildCard(subKeyword);
	}

	@Override
	public List<IPublication> getPublicationsByTitle(String title) throws ANoteException {
		return lucenePublicationsAcessImpl.getPublicationsByTitle(title);
	}
	
	@Override
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties)  throws ANoteException{
		return lucenePublicationsAcessImpl.getPublicationsFromSearch(searchProperties);
	}

	@Override
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index,
			int paginationSize) throws ANoteException {
		return lucenePublicationsAcessImpl.getPublicationsFromSearchPaginated(searchProperties, index, paginationSize);
	}

	@Override
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException {
		return lucenePublicationsAcessImpl.countGetPublicationsFromSearch(searchProperties);
	}

	@Override
	public Integer countGetCorpusFrom(ISearchProperties searchProperties) throws ANoteException {
		//luceneCorpusAcessImpl;
		return null;
	}

	@Override
	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize)
			throws ANoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties) throws ANoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAllPublications() throws ANoteException {
		// TODO Auto-generated method stub
		return this.publicationsAccessimpl.countAllPublications();
	}

	@Override
	public List<IDataProcessStatus> getAllDataProcessStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDataProcessStatus> getDataProcessStatusByUserAndDateRange(IUser user, Date startDateRange,
			Date endDateRange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDataProcessStatus(IDataProcessStatus dataprocessStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IResource<IResourceElement>> getAllPrivilegesResources() throws ANoteException {
		return this.resourcesAccessImpl.getAllPrivilegesResources();
	}


	
}