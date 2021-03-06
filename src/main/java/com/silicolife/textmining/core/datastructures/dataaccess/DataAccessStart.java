package com.silicolife.textmining.core.datastructures.dataaccess;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.analysis.IIEProcessStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
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
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class DataAccessStart implements IDataAccess {

	public DataAccessStart() {

	}

	@Override
	public List<IPublication> getQueryPublications(IQuery query) throws ANoteException {

		return null;
	}

	@Override
	public void updateQueryDocumentRelevance(IPublication publication, IQuery query,
			IQueryPublicationRelevance iQueryPublicationRelevance) throws ANoteException {

		
	}

	@Override
	public void addQueryPublications(IQuery query, Set<IPublication> publications) throws ANoteException {

		
	}

	@Override
	public void createQuery(IQuery query) throws ANoteException {

		
	}

	@Override
	public void updateQuery(IQuery query) throws ANoteException {

		
	}

	@Override
	public void inactiveQuery(IQuery query) throws ANoteException {

		
	}

	@Override
	public void removeQuery(IQuery query) throws ANoteException {

		
	}

	@Override
	public List<IQuery> getAllQueries() throws ANoteException {

		return null;
	}

	@Override
	public IQuery getQueryByID(long queryID) throws ANoteException {

		return null;
	}

	@Override
	public Set<String> getQueryPublicationsExternalIDFromSource(IQuery query, String source) throws ANoteException {

		return null;
	}

	@Override
	public List<IQuery> getPrivilegesAllQueriesAdminAccess() throws ANoteException {

		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getPrivilegesAllResourcesAdminAccess() throws ANoteException {

		return null;
	}

	@Override
	public void createClustering(IClusterProcess clustering) throws ANoteException {

		
	}

	@Override
	public void addClusteringLabels(IClusterProcess cluster) throws ANoteException {

		
	}

	@Override
	public List<IClusterProcess> getQueryClusters(IQuery query) throws ANoteException {

		return null;
	}

	@Override
	public IClusterProcess getClusterByID(long clusterID) throws ANoteException {

		return null;
	}

	@Override
	public void inactivateCluster(IClusterProcess clustering) throws ANoteException {

		
	}

	@Override
	public void registerQueryClustering(IQuery query, IClusterProcess clustering) throws ANoteException {

		
	}

	@Override
	public void updateQueryClusteringProcess(IClusterProcess clustering) throws ANoteException {

		
	}

	@Override
	public void addPublications(Set<IPublication> documents) throws ANoteException {

		
	}

	@Override
	public IPublication getPublication(long documentID) throws ANoteException {

		return null;
	}

	@Override
	public void updatePublication(IPublication publication) throws ANoteException {

		
	}

	@Override
	public void updatePublicationAvailableFreeFullText(IPublication pub) throws ANoteException {

		
	}

	@Override
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws ANoteException {

		return null;
	}

	@Override
	public String getPublicationFullText(IPublication publication) throws ANoteException {

		return null;
	}

	@Override
	public void insertNewClass(IAnoteClass klass) throws ANoteException {

		
	}

	@Override
	public Set<IAnoteClass> getClasses() throws ANoteException {

		return null;
	}

	@Override
	public boolean updateClassName(IAnoteClass oldclassName, String newCLass) throws ANoteException {

		return false;
	}

	@Override
	public void updateAnoteKlass(IAnoteClass klass) throws ANoteException {

		
	}

	@Override
	public List<IPublicationLabel> getAllLabels() throws ANoteException {

		return null;
	}

	@Override
	public void updatePublicationFullTextContent(IPublication publication) throws ANoteException {

		
	}

	@Override
	public void createCorpus(ICorpus corpus) throws ANoteException {

		
	}

	@Override
	public void updateCorpus(ICorpus corpus) throws ANoteException {

		
	}
	
	@Override
	public void updateCorpusStatus(ICorpus corpus, boolean status) throws ANoteException {
		
	}

	@Override
	public void inactivateCorpus(ICorpus corpus) throws ANoteException {

		
	}

	@Override
	public List<ICorpus> getAllCorpus() throws ANoteException {

		return null;
	}

	@Override
	public ICorpus getCorpusByID(long corpusID) throws ANoteException {

		return null;
	}

	@Override
	public void registerCorpusProcess(ICorpus corpus, IIEProcess process) throws ANoteException {

		
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(ICorpus corpus) throws ANoteException {

		return null;
	}

	@Override
	public IDocumentSet getCorpusPublications(ICorpus corpus) throws ANoteException {

		return null;
	}

	@Override
	public Long getCorpusPublicationsCount(ICorpus corpus) throws ANoteException {

		return null;
	}

	@Override
	public IDocumentSet getCorpusPublicationsPaginated(ICorpus corpus, Integer paginationIndex, Integer paginationSize)
			throws ANoteException {

		return null;
	}

	@Override
	public void addCorpusPublication(ICorpus corpus, IPublication publication) throws ANoteException {

		
	}

	@Override
	public ICorpusStatistics getCorpusStatistics(ICorpus corpus) throws ANoteException {

		return null;
	}

	@Override
	public Long countCorpusPublicationsNotProcessed(IIEProcess process) throws ANoteException {

		return null;
	}

	@Override
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public Set<String> getCorpusPublicationsExternalIDFromSource(ICorpus corpus, String source) throws ANoteException {

		return null;
	}

	@Override
	public void createIEProcess(IIEProcess processe) throws ANoteException {

		
	}

	@Override
	public void updateIEProcess(IIEProcess process) throws ANoteException {

		
	}

	@Override
	public void inactivateProcess(IIEProcess process) throws ANoteException {

		
	}

	@Override
	public IIEProcess getProcessByID(long processID) throws ANoteException {

		return null;
	}

	@Override
	public IIEProcessStatistics getIEProcessStatistics(IIEProcess ieProcessImpl) throws ANoteException {

		return null;
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
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws ANoteException {

		return null;
	}

	@Override
	public List<IEventAnnotation> getAnnotatedDocumentEvents(IAnnotatedDocument annotedDocument) throws ANoteException {

		return null;
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntities(IAnnotatedDocument annotedDocument)
			throws ANoteException {

		return null;
	}

	@Override
	public void addProcessDocumentEntitiesAnnotations(IIEProcess process, IPublication document,
			List<IEntityAnnotation> entityAnnotations) throws ANoteException {

		
	}

	@Override
	public void addProcessDocumentEventAnnoations(IIEProcess process, IPublication document,
			List<IEventAnnotation> events) throws ANoteException {

		
	}

	@Override
	public void inactiveAnnotations(List<Long> annotation) throws ANoteException {

		
	}

	@Override
	public void updateEntityAnnotations(List<IEntityAnnotation> list) throws ANoteException {

		
	}

	@Override
	public void updateEventsAnnotations(List<IEventAnnotation> list) throws ANoteException {

		
	}

	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(IAnnotatedDocument annotatedDocument)
			throws ANoteException {

		return null;
	}

	@Override
	public List<Long> getPublicationsIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {

		return null;
	}

	@Override
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(IIEProcess process, IPublication document)
			throws ANoteException {

		return null;
	}

	@Override
	public SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws ANoteException {

		return null;
	}

	@Override
	public void addProcessDocumentLogs(List<IAnnotationLog> annotationLogs) throws ANoteException {

		
	}

	@Override
	public List<IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws ANoteException {

		return null;
	}

	@Override
	public List<ICorpus> getPrivilegesAllCorpusAdminAccess() throws ANoteException {

		return null;
	}

	@Override
	public void createResource(IResource<IResourceElement> resource) throws ANoteException {

		
	}

	@Override
	public void updateResource(IResource<IResourceElement> resource) throws ANoteException {

		
	}

	@Override
	public void inativeResource(IResource<IResourceElement> resource) throws ANoteException {

		
	}

	@Override
	public void removeResource(IResource<IResourceElement> resource) throws ANoteException {

		
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesByType(String type) throws ANoteException {

		return null;
	}

	@Override
	public IResource<IResourceElement> getResourceByID(long resourceID) throws ANoteException {

		return null;
	}

	@Override
	public Set<IAnoteClass> getResourceClassContent(IResource<IResourceElement> resource) throws ANoteException {

		return null;
	}

	@Override
	public boolean checkResourceHasElements(IResource<IResourceElement> resource) throws ANoteException {

		return false;
	}

	@Override
	public IResourceContent getResourceContent(IResource<IResourceElement> resource) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport addResourceElements(IResource<IResourceElement> resource,
			List<IResourceElement> element) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport addResourceElementsWithouValidation(IResource<IResourceElement> resource,
			List<IResourceElement> element) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport addResourceElementSynomyns(IResource<IResourceElement> resource,
			IResourceElement elem, List<String> synonyms) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport addResourceElementSynomynsWithoutValidation(IResource<IResourceElement> destiny,
			IResourceElement originalElem, List<String> synonymsToAdd) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport addResourceElementExternalIds(IResource<IResourceElement> resource,
			IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {

		return null;
	}

	@Override
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {

		return null;
	}

	@Override
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {

		
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElements(IResource<IResourceElement> resource)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(
			IResource<IResourceElement> resource, Integer index, Integer pagination) throws ANoteException {

		return null;
	}

	@Override
	public List<IResourceElementsRelation> getResourceElementsRelations(IResource<IResourceElement> resource)
			throws ANoteException {

		return null;
	}

	@Override
	public void removeResourceClass(IResource<IResourceElement> resource, long classID) throws ANoteException {

		
	}

	@Override
	public long getResourceElementIDMathingByText(IResource<IResourceElement> resource, IResourceElement elemennt,
			String text, boolean casesensitive) throws ANoteException {

		return 0;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByName(IResource<IResourceElement> resource,
			String name) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElement getResourceElementByID(long resourceID) throws ANoteException {

		return null;
	}

	@Override
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException {

		
	}

	@Override
	public void removeResourceElementSynonym(IResourceElement elem, String synonym) throws ANoteException {

		
	}

	@Override
	public int getResourceMaxPriority(IResource<IResourceElement> resource) throws ANoteException {

		return 0;
	}

	@Override
	public void addResourceElementsRelation(IResourceElement a, IResourceElement b, String relationType)
			throws ANoteException {

		
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IResource<IResourceElement> resource,
			IAnoteClass klass) throws ANoteException {

		return null;
	}

	@Override
	public List<IExternalID> getResourceElementExternalIds(IResourceElement resourceElementImpl) throws ANoteException {

		return null;
	}

	@Override
	public boolean checkResourceElementExistsInResource(IResource<IResourceElement> resource, String term)
			throws ANoteException {

		return false;
	}

	@Override
	public IResourceManagerReport updateResourceElementSynonym(IResource<IResourceElement> resource,
			IResourceElement elem, String oldSynonym, String newSynonym) throws ANoteException {

		return null;
	}

	@Override
	public void removeResourceElementExternalID(IResourceElement element, IExternalID extID) throws ANoteException {

		
	}

	@Override
	public void removeResourceElementAllExternalID(IResourceElement element) throws ANoteException {

		
	}

	@Override
	public IResource<IResourceElement> getResourceFromResourceElementByID(Long resourceElementID)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID externalId)
			throws ANoteException {

		return null;
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
	public void createUser(IUser user) throws ANoteException {

		
	}

	@Override
	public IUser getUserByEmail(String email) throws ANoteException {

		return null;
	}

	@Override
	public IUser getUserByUsername(String username) throws ANoteException {

		return null;
	}

	@Override
	public void updateUser(IUser user) throws ANoteException {

		
	}

	@Override
	public void removeUser(IUser user) throws ANoteException {

		
	}

	@Override
	public List<IGroup> getAllGroups() throws ANoteException {

		return null;
	}

	@Override
	public List<IUser> getAllUsers() throws ANoteException {

		return null;
	}

	@Override
	public IUserDataObject getUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {

		return null;
	}

	@Override
	public Boolean hasPermissionRole(List<String> roles) throws ANoteException {

		return null;
	}

	@Override
	public Boolean hasPermission(IQuery query, List<String> permission) throws ANoteException {

		return null;
	}

	@Override
	public Boolean hasPermission(IResource<IResourceElement> resource, List<String> permission) throws ANoteException {

		return null;
	}

	@Override
	public Boolean hasPermission(ICorpus corpus, List<String> ownergrant) throws ANoteException {

		return null;
	}

	@Override
	public Boolean hasPermission(IIEProcess obj, List<String> fullgrant) throws ANoteException {

		return null;
	}

	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {

		return null;
	}

	@Override
	public void addUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource,
			PermissionsUtilsEnum privilege) throws ANoteException {

		
	}

	@Override
	public void updateUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils Resource,
			PermissionsUtilsEnum privilege) throws ANoteException {

		
	}

	@Override
	public void removeUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {

		
	}

	@Override
	public IHyperLinkMenuItem getHyperLinkMenuItemById(Long id) throws ANoteException {

		return null;
	}

	@Override
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() throws ANoteException {

		return null;
	}

	@Override
	public void updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {

		
	}

	@Override
	public List<ISource> getAllSources() throws ANoteException {

		return null;
	}

	@Override
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(ISource source) throws ANoteException {

		return null;
	}

	@Override
	public Boolean runServerProcesses(String klass, String configuration) throws ANoteException {

		return null;
	}

	@Override
	public boolean rebuildLuceneIndex() throws ANoteException {

		return false;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter,
			String term) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String term) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String term, int index,
			int paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(
			IResourceElementsFilter filter, String partialTerm) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(
			IResourceElementsFilter filter, String partialTerm, int index, int paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(
			IResourceElementsFilter filter, String synonym) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String synonym)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String synonym, int index,
			int paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(
			IResourceElementsFilter filter, String partialSynonym) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(
			IResourceElementsFilter filter, String partialSynonym, int index, int paginationSize)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalId(String externalId)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalId(
			IResourceElementsFilter filter, String externalId) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalId(String partialExternalId)
			throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalId(
			IResourceElementsFilter filter, String partialExternalId) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdPaginated(
			String partialExternalId, int index, int paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIdPaginated(
			IResourceElementsFilter filter, String partialExternalId, int index, int paginationSize)
			throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsCountByPartialTerm(String partialTerm) throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsCountByPartialSynonym(String partialSynonym) throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialTerm)
			throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter,
			String partialSynonym) throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsCountByPartialExternalID(String partialExternalId) throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter,
			String partialExternalId) throws ANoteException {

		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(
			String partialString, int index, int paginationSize) throws ANoteException {

		return null;
	}

	@Override
	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString) throws ANoteException {

		return null;
	}

	@Override
	public IUser getUser() {

		return null;
	}

	@Override
	public void setUserLoggedOnServices(UsersLogged userLogged) {

		
	}

	@Override
	public void setUser(UsersLogged userLogged) throws ANoteException {

		
	}

	@Override
	public void login(String username, String password) throws ANoteException {

		
	}

	@Override
	public Boolean checkLogin(String username, String password) throws ANoteException {

		return null;
	}

	@Override
	public void logout() throws ANoteException {

		
	}

	@Override
	public void saveProperties(Properties properties) throws ANoteException {

		
	}

	@Override
	public Properties loadProperties(Set<String> propertiesIdentifiers) throws ANoteException {

		return null;
	}

	@Override
	public List<Long> getProcessesIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {

		return null;
	}

	@Override
	public List<IIEProcess> getProcessesByPublication(IPublication publication) throws ANoteException {

		return null;
	}

	@Override
	public Set<ICorpus> getCorpusByPublication(IPublication publication) throws ANoteException {

		return null;
	}

	@Override
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws ANoteException {

		return null;
	}

	@Override
	public Boolean removeAllProcessDocumentAnnotations(IIEProcess process, IPublication document) throws ANoteException {
		return null;
	}

	@Override
	public Long countCorpusPublicationsOutdated(IIEProcess process) throws ANoteException {
		return null;
	}

	@Override
	public IDocumentSet getCorpusPublicationsOutdatedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {
		return null;
	}

	@Override
	public Boolean autoupdate() throws ANoteException {
		return null;
	}

	@Override
	public Long getNextHyperLinkMenuItemID() throws ANoteException {
		return null;
	}

	@Override
	public void addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		
	}

	@Override
	public void removeHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		
	}

	@Override
	public void updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
	}
	
	public List<IQuery> getQueriesByName(String name) {
		return null;
	}

	@Override
	public List<IQuery> getQueriesByOrganism(String organism) throws ANoteException {
		
		return null;
	}

	@Override
	public List<IQuery> getQueriesBykeywords(String keywords) throws ANoteException {
		
		return null;
	}

	@Override
	public List<IQuery> getQueriesKeywordsByWildCard(String subKeyword) throws ANoteException {
		
		return null;
	}

	@Override
	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword) throws ANoteException {
		
		return null;
	}

	@Override
	public List<IPublication> getPublicationsByTitle(String title) throws ANoteException {
		
		return null;
	}
	
	@Override
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties)  throws ANoteException{
		
		return null;
	}

	@Override
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index,
			int paginationSize) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countGetCorpusFrom(ISearchProperties searchProperties) throws ANoteException {
		
		return null;
	}

	@Override
	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize)
			throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countAllPublications() throws ANoteException {
		
		return null;
	}

	@Override
	public void addDataProcessStatus(IDataProcessStatus dataprocessStatus) {
		
		
	}

	@Override
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) {
		
		
	}

	@Override
	public List<IDataProcessStatus> getAllDataProcessStatus() {
		
		return null;
	}

	@Override
	public List<IDataProcessStatus> getDataProcessStatusByUserAndDateRange(IUser user, Date startDateRange,
			Date endDateRange) {
		
		return null;
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntitiesFilteredByResourceElement(IIEProcess process,
			IPublication publication, IResourceElement resourceElement) throws ANoteException {
		
		return null;
	}

	@Override
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(
			Set<IResourceElement> resourceElements, IPublicationFilter pubFilter) throws ANoteException {
		
		return null;
	}

	@Override
	public ISentence getSentence(IEntityAnnotation entityAnnotation) throws ANoteException, IOException {
		return null;
	}

	@Override
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(IIEProcess process,
			IPublication publication, ISentence sentence) throws ANoteException {
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getAllPrivilegesResources() throws ANoteException {
		return null;
	}
	
	@Override
	public Long countAnnotationsByResourceElement(IIEProcess process, IResourceElement resourceElement) throws ANoteException {
		return null;
	}

	@Override
	public List<IPublication> getPublicationsFromSearchPaginatedWSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy) throws ANoteException {
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties,
			int index, int paginationSize) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission)
			throws ANoteException {
		
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy)
			throws ANoteException {
		
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy)
			throws ANoteException {
		
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy,
			String permission) throws ANoteException {
		
		return null;
	}

	@Override
	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(
			ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties)
			throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId)
			throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(
			IResourceElementsFilter filter, String externalId) throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString)
			throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(
			IResourceElementsFilter filter, String partialString) throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString,
			int index, int paginationSize) throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(
			IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTermOrExactSynonymPaginated(
			String exactString, int index, int paginationSize) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer getCountResourceElementsByExactTermOrExactSynonymPaginated(String exactString)
			throws ANoteException {
		
		return null;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsPaginated(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy) throws ANoteException {
		
		return null;
	}

	@Override
	public Integer countResourceElements(ISearchProperties searchProperties) throws ANoteException {
		
		return null;
	}

	@Override
	public List<IPublication> getQueryPublicationsPaginated(IQuery query, Integer paginationIndex,
			Integer paginationSize, Boolean asc, String sortBy) throws ANoteException {

		return null;
	}

	@Override
	public Long countQueryPublications(IQuery query) throws ANoteException {

		return null;
	}

	@Override
	public List<Long> getCorpusPublicationsFromExternalID(ICorpus corpus, String source, String InternalSourceId)
			throws ANoteException {

		return null;
	}

	@Override
	public Long countAnnotationsByAnnotationType(IIEProcess process, AnnotationType annotType) throws ANoteException {

		return null;
	}

	@Override
	public Map<IAnoteClass, Long> countEntityAnnotationsByClassInProcess(IIEProcess process) throws ANoteException {
		
		return null;
	}

	@Override
	public Map<IResourceElement, Long> countEntityAnnotationsByResourceElementInProcess(IIEProcess process)
			throws ANoteException {

		return null;
	}

	@Override
	public Map<IResourceElement, Long> countAnnotationsByResourceElementInDocument(IAnnotatedDocument document)
			throws ANoteException {

		return null;
	}
	
	@Override
	public Map<IResourceElement, Long> countDocumentsWithEntityAnnotationsByResourceElementInProcess(IIEProcess process)
			throws ANoteException {

		return null;
	}

	@Override
	public Long countPublicationsWithEventsByResourceElements(List<IResourceElement> resourceElements)
			throws ANoteException {

		return null;
	}

	@Override
	public List<Long> getPublicationsIdsWithEventsByResourceElements(List<IResourceElement> resourceElements)
			throws ANoteException {

		return null;
	}

	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countPublicationsWithEventsByIAnoteClasses(
			IIEProcess process) throws ANoteException {

		return null;
	}

	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countEventAnnotationsByClassInProcess(IIEProcess process)
			throws ANoteException {

		return null;
	}

	@Override
	public Long countDocumentsWithResourceElementByAnnotationTypeInProcess(IResourceElement resourceElement,
			IIEProcess process, AnnotationType annotType) throws ANoteException {
		
		return null;
	}

	@Override
	public List<Long> getPublicationsIdsByEventResourceElements(IIEProcess process,
			Set<ImmutablePair<IResourceElement, IResourceElement>> resElemIds) throws ANoteException {

		return null;
	}

	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countDocumentsWithEventsByResourceElemnts(
			IIEProcess process) throws ANoteException {

		return null;
	}

	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countEventsByResourceElemnts(IIEProcess process)
			throws ANoteException {

		return null;
	}


}
