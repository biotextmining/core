package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.hibernate.SessionFactory;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.AnnotationAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.AnnotationAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ClustersAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ClustersProcessAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.CorpusAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.CorpusAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.DataProcessStatusAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.DataProcessStatusAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.HyperLinkMenusAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.HyperLinkMenusAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ProcessesAoxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ProcessesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.PublicationsAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.QueriesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.QueriesAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ResourcesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ResourcesAuxDaoImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AnnotationSides;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Annotations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroups;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettings;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabelPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcessHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkSubmenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessOrigins;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationFields;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationHasSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIds;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelationTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElements;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Synonyms;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Versions;

public class ManagerDao {

	private QueriesManagerDao queriesManagerDao;
	private UsersManagerDao usersManagerDao;
	private ResourcesManagerDao resourcesManagerDao;
	private CorpusManagerDao corpusManagerDao;
	private ProcessesManagerDao processesManagerDao;
	private AnnotationManagerDao annotationManagerDao;
	private ClustersManagerDao clusterManagerDao;
	private HyperLinkMenuManagerDao hyperlinkMenuManagerDao;
	private SystemManagerDao systemManagerDao;
	private PublicationsManagerDao publicationsManagerDao;
	private DataProcessStatusManagerDao dataProcessStatusManagerDao;
	
	private SessionFactory sessionFactory;

	public ManagerDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		queriesManagerDao = createQueriesDao();
		usersManagerDao = createUsersDao();
		resourcesManagerDao = createResourcesDao();
		corpusManagerDao = createCorpusManagerDao();
		processesManagerDao = createProcessManagerDao();
		annotationManagerDao = createAnnotationAuxDao();
		clusterManagerDao = createClusterManagerdao();
		hyperlinkMenuManagerDao = createHyperLinkMenusDao();
		systemManagerDao = createSystemMangerDao();
		publicationsManagerDao = createPublicationsDao();
		dataProcessStatusManagerDao = createDataProcessStatusManagerDao();
	}

	

	private HyperLinkMenuManagerDao createHyperLinkMenusDao() {
		GenericDao<HyperLinkMenus> hyperLinkMenusDao = new GenericDaoImpl<HyperLinkMenus>(sessionFactory, HyperLinkMenus.class);
		GenericDao<HyperLinkSubmenus> hyperLinkSubmenusDao = new GenericDaoImpl<HyperLinkSubmenus>(sessionFactory, HyperLinkSubmenus.class);
		GenericDao<HyperLinkMenuSourceAssociation> hyperLinkMenuSourceAssociationDao = new GenericDaoImpl<HyperLinkMenuSourceAssociation>(sessionFactory, HyperLinkMenuSourceAssociation.class);
		GenericDao<Sources> sourcesDao = new GenericDaoImpl<Sources>(sessionFactory, Sources.class);
		HyperLinkMenusAuxDao hyperLinkMenusAuxDao= new HyperLinkMenusAuxDaoImpl(sessionFactory);
		return new HyperLinkMenuManagerDao(hyperLinkMenusDao, hyperLinkSubmenusDao, hyperLinkMenuSourceAssociationDao, sourcesDao, hyperLinkMenusAuxDao);
	}

	
	private ClustersManagerDao createClusterManagerdao() {
		GenericDao<ClusterProperties> clusterPropertiesDao = new GenericDaoImpl<ClusterProperties>(sessionFactory, ClusterProperties.class);
		ClustersAuxDao clustersAuxDao = new ClustersProcessAuxDaoImpl(sessionFactory);
		GenericDao<ClusterLabels> clusterLabelsDao = new GenericDaoImpl<ClusterLabels>(sessionFactory, ClusterLabels.class);
		GenericDao<ClusterProcesses> clusterProcessesDao = new GenericDaoImpl<ClusterProcesses>(sessionFactory, ClusterProcesses.class);
		GenericDao<ClusterProcessHasLabels> clusterProcessHasLabelsDao = new GenericDaoImpl<ClusterProcessHasLabels>(sessionFactory, ClusterProcessHasLabels.class);
		GenericDao<ClusterLabelPublications> clusterLabelPublicationDao = new GenericDaoImpl<ClusterLabelPublications>(sessionFactory, ClusterLabelPublications.class);

		ClustersManagerDao clusterManagerDao = new ClustersManagerDao(clusterProcessesDao, clusterLabelsDao, clusterPropertiesDao, clusterProcessHasLabelsDao,clusterLabelPublicationDao, clustersAuxDao);
		return clusterManagerDao;
	}

	private AnnotationManagerDao createAnnotationAuxDao() {
		if(processesManagerDao==null)
		{
			processesManagerDao = createProcessManagerDao();
		}
		GenericDao<AnnotationLogs> annotationLogsDao = new GenericDaoImpl<AnnotationLogs>(sessionFactory, AnnotationLogs.class);
		GenericDao<AnnotationSides> annotationSidesDao  = new GenericDaoImpl<AnnotationSides>(sessionFactory, AnnotationSides.class);
		GenericDao<AnnotationProperties> annotationPropertiesDao = new GenericDaoImpl<AnnotationProperties>(sessionFactory, AnnotationProperties.class);
		GenericDao<Annotations> annotationsDao = new GenericDaoImpl<Annotations>(sessionFactory, Annotations.class);;
		AnnotationAuxDao annotationAuxDao = new AnnotationAuxDaoImpl(sessionFactory);
		AnnotationManagerDao annotationManagerDao = new AnnotationManagerDao(annotationsDao, annotationPropertiesDao, annotationSidesDao, annotationLogsDao, annotationAuxDao );
		return annotationManagerDao;
	}

	public AnnotationManagerDao getAnnotationManagerDao() {
		return annotationManagerDao;
	}

	private ProcessesManagerDao createProcessManagerDao() {
		
		GenericDao<Processes> processesDao = new GenericDaoImpl<Processes>(sessionFactory, Processes.class);
		GenericDao<ProcessTypes> processTypesDao = new GenericDaoImpl<ProcessTypes>(sessionFactory, ProcessTypes.class);
		GenericDao<ProcessOrigins> processOriginsDao = new GenericDaoImpl<ProcessOrigins>(sessionFactory, ProcessOrigins.class);
		GenericDao<ProcessProperties> processPropertiesDao = new GenericDaoImpl<ProcessProperties>(sessionFactory, ProcessProperties.class);
		ProcessesAuxDao processAuxDao = new ProcessesAoxDaoImpl(sessionFactory);
		ProcessesManagerDao processesManagerDao = new ProcessesManagerDao(processesDao,
				processTypesDao,
				processOriginsDao,
				processPropertiesDao,
				processAuxDao);
		return processesManagerDao;
	}

	private CorpusManagerDao createCorpusManagerDao() {
		GenericDao<Corpus> corpusDao = new GenericDaoImpl<Corpus>(sessionFactory, Corpus.class);
		GenericDao<CorpusHasPublications> corpusHasPublicationsDao = new GenericDaoImpl<CorpusHasPublications>(sessionFactory, CorpusHasPublications.class);
		GenericDao<CorpusProperties> corpusPropertiesDao = new GenericDaoImpl<CorpusProperties>(sessionFactory, CorpusProperties.class);
		GenericDao<CorpusHasProcesses> corpusHasProcessesDao = new GenericDaoImpl<CorpusHasProcesses>(sessionFactory, CorpusHasProcesses.class);
		CorpusAuxDao corpusAuxDao = new CorpusAuxDaoImpl(sessionFactory);
		GenericDao<CorpusHasPublicationsHasProcesses> corpusHasPublicationsHasProcessesDao = new GenericDaoImpl<CorpusHasPublicationsHasProcesses>(sessionFactory, CorpusHasPublicationsHasProcesses.class);
		if(queriesManagerDao==null)
		{
			queriesManagerDao = createQueriesDao();
		}
		CorpusManagerDao corpusMAnagerDao = new CorpusManagerDao(
				queriesManagerDao.getPublicationSourcesDao(),
				queriesManagerDao.getPublicationHasSourcesDao(),
				queriesManagerDao.getPublicationsDao(),
				queriesManagerDao.getPublicationFieldsDao(),
				queriesManagerDao.getPublicationLabelsDao(),
				queriesManagerDao.getPublicationHasLabelsDao(),
				queriesManagerDao.getPublicationsAuxDao(),
				corpusDao,
				corpusPropertiesDao,
				corpusHasProcessesDao,
				corpusHasPublicationsDao,
				corpusAuxDao,
				corpusHasPublicationsHasProcessesDao);
		return corpusMAnagerDao;
	}

	public CorpusManagerDao getCorpusManagerDao() {
		return corpusManagerDao;
	}
	
	public PublicationsManagerDao createPublicationsDao(){
		GenericDao<PublicationSources> publicationSourcesDao = new GenericDaoImpl<PublicationSources>(sessionFactory, PublicationSources.class);;
		GenericDao<PublicationHasSources> publicationsHasPublicationsSourceDao = new GenericDaoImpl<PublicationHasSources>(sessionFactory, PublicationHasSources.class);
		GenericDao<Publications> publicationsDao = new GenericDaoImpl<Publications>(sessionFactory, Publications.class);
		GenericDao<PublicationFields> publicationsFieldsDao = new GenericDaoImpl<PublicationFields>(sessionFactory, PublicationFields.class);
		GenericDao<PublicationLabels> publicationsLabelsDao = new GenericDaoImpl<PublicationLabels>(sessionFactory, PublicationLabels.class);
		GenericDao<PublicationHasLabels> publicationsHasPublicationLabelsDao = new GenericDaoImpl<PublicationHasLabels>(sessionFactory, PublicationHasLabels.class);
		PublicationsAuxDao publicationsAuxDao = new PublicationsAuxDaoImpl(sessionFactory);
		PublicationsManagerDao publicationsManagerDao = new PublicationsManagerDao(publicationSourcesDao, publicationsHasPublicationsSourceDao, publicationsDao, publicationsFieldsDao, publicationsLabelsDao, publicationsHasPublicationLabelsDao, publicationsAuxDao);
		
		return publicationsManagerDao;
	}
	
	public PublicationsManagerDao getPublicationsManagerDao(){
		return publicationsManagerDao;
	}

	public QueriesManagerDao createQueriesDao() {
		GenericDao<PublicationSources> publicationsSourceDao = new GenericDaoImpl<PublicationSources>(sessionFactory, PublicationSources.class);
		GenericDao<PublicationHasSources> publicationsHasPublicationsSourceDao = new GenericDaoImpl<PublicationHasSources>(sessionFactory, PublicationHasSources.class);
		GenericDao<Publications> publicationsDao = new GenericDaoImpl<Publications>(sessionFactory, Publications.class);
		GenericDao<PublicationFields> publicationsFieldsDao = new GenericDaoImpl<PublicationFields>(sessionFactory, PublicationFields.class);
		GenericDao<PublicationLabels> publicationsLabelsDao = new GenericDaoImpl<PublicationLabels>(sessionFactory, PublicationLabels.class);
		GenericDao<PublicationHasLabels> publicationsHasPublicationLabelsDao = new GenericDaoImpl<PublicationHasLabels>(sessionFactory, PublicationHasLabels.class);
		PublicationsAuxDao publicationsAuxDao = new PublicationsAuxDaoImpl(sessionFactory);
		GenericDao<Queries> queriesDao = new GenericDaoImpl<Queries>(sessionFactory, Queries.class);
		GenericDao<QueryTypes> queriesTypeDao = new GenericDaoImpl<QueryTypes>(sessionFactory, QueryTypes.class);
		GenericDao<QueryHasPublications> queriesHasPublicationsDao = new GenericDaoImpl<QueryHasPublications>(sessionFactory, QueryHasPublications.class);
		GenericDao<QueryHasClusterProcesses> queriesHasClustersProcessDao = new GenericDaoImpl<QueryHasClusterProcesses>(sessionFactory, QueryHasClusterProcesses.class);
		QueriesAuxDao queriesAuxDao = new QueriesAuxDaoImpl(sessionFactory);
		GenericDao<QueryProperties> queryProperties = new GenericDaoImpl<QueryProperties>(sessionFactory, QueryProperties.class);
		
		QueriesManagerDao queriesManagerDao = new QueriesManagerDao(publicationsSourceDao, publicationsHasPublicationsSourceDao, publicationsDao, publicationsFieldsDao,
				publicationsLabelsDao, publicationsHasPublicationLabelsDao, publicationsAuxDao, queriesDao, queriesTypeDao, queriesHasPublicationsDao,
				queriesHasClustersProcessDao, queriesAuxDao, queryProperties);

		return queriesManagerDao;

	}

	public UsersManagerDao createUsersDao() {
		GenericDao<AuthUsers> authUsersDao = new GenericDaoImpl<AuthUsers>(sessionFactory, AuthUsers.class);
		GenericDao<AuthUserLogs> authUserLogsDao = new GenericDaoImpl<AuthUserLogs>(sessionFactory, AuthUserLogs.class);
		GenericDao<AuthUserDataObjects> authUserDataObjectsDao = new GenericDaoImpl<AuthUserDataObjects>(sessionFactory, AuthUserDataObjects.class);
		GenericDao<AuthGroups> authGroups = new GenericDaoImpl<AuthGroups>(sessionFactory, AuthGroups.class);
		GenericDao<AuthUserSettings> authSettings = new GenericDaoImpl<AuthUserSettings>(sessionFactory, AuthUserSettings.class);

		UsersManagerDao usersManagerDao = new UsersManagerDao(authUsersDao, authUserLogsDao, authUserDataObjectsDao, authGroups,authSettings);

		return usersManagerDao;

	}

	public ResourcesManagerDao createResourcesDao() {
		GenericDao<ResourceTypes> resourceTypeDao = new GenericDaoImpl<ResourceTypes>(sessionFactory, ResourceTypes.class);
		GenericDao<Resources> resourceDao = new GenericDaoImpl<Resources>(sessionFactory, Resources.class);
		GenericDao<Classes> classesDao = new GenericDaoImpl<Classes>(sessionFactory, Classes.class);
		ResourcesAuxDao resourcesAuxDao = new ResourcesAuxDaoImpl(sessionFactory);
		GenericDao<ResourceElements> resourcesElememtsDao = new GenericDaoImpl<ResourceElements>(sessionFactory, ResourceElements.class);
		GenericDao<Synonyms> resourcesElememtsSynonymsDao = new GenericDaoImpl<Synonyms>(sessionFactory, Synonyms.class);
		GenericDao<ResourceElementRelations> resourcesElememtsRelationsDao = new GenericDaoImpl<ResourceElementRelations>(sessionFactory, ResourceElementRelations.class);
		GenericDao<ResourceElementRelationTypes> resourcesElememtsRelationTypeDao = new GenericDaoImpl<ResourceElementRelationTypes>(sessionFactory, ResourceElementRelationTypes.class);
		GenericDao<ResourceElementExtenalIds> resourcesElememtsExtenalIDsDao = new GenericDaoImpl<ResourceElementExtenalIds>(sessionFactory, ResourceElementExtenalIds.class);
		GenericDao<Sources> resourcesElememtsSourcesDao = new GenericDaoImpl<Sources>(sessionFactory, Sources.class);
		ResourcesManagerDao resourcesManagerDao = new ResourcesManagerDao(classesDao, resourceTypeDao, resourceDao,
				resourcesAuxDao, resourcesElememtsDao, resourcesElememtsSynonymsDao, resourcesElememtsRelationsDao,
				resourcesElememtsRelationTypeDao, resourcesElememtsExtenalIDsDao, resourcesElememtsSourcesDao);
		return resourcesManagerDao;

	}
	
	public DataProcessStatusManagerDao createDataProcessStatusManagerDao() {
		GenericDao<DataProcessStatus> dataProcessStatusDao = new GenericDaoImpl<DataProcessStatus>(sessionFactory, DataProcessStatus.class);
		DataProcessStatusAuxDao dataProcessStatusAuxDao = new DataProcessStatusAuxDaoImpl(sessionFactory);
		DataProcessStatusManagerDao dataProcessStatusManagerDao = new DataProcessStatusManagerDao(dataProcessStatusDao, dataProcessStatusAuxDao);
		return dataProcessStatusManagerDao;
	}
	
	private SystemManagerDao createSystemMangerDao() {
		GenericDao<Versions> versionsDao = new GenericDaoImpl<Versions>(sessionFactory, Versions.class);
		return new SystemManagerDao(versionsDao);
	}

	public QueriesManagerDao getQueriesManagerDao() {
		return queriesManagerDao;
	}

	public UsersManagerDao getUsersManagerDao() {
		return usersManagerDao;
	}

	public ResourcesManagerDao getResourcesManagerDao() {
		return resourcesManagerDao;
	}

	public ProcessesManagerDao getProcessServiceDao() {
		return processesManagerDao;
	}

	public ClustersManagerDao getClusterManagerDao() {
		return clusterManagerDao;
	}

	public HyperLinkMenuManagerDao getHyperLinkMenuDao() {
		return hyperlinkMenuManagerDao;
	}


	public SystemManagerDao getSystemServiceDao() {
		return systemManagerDao;
	}

	public DataProcessStatusManagerDao getDataProcessStatusManagerDao() {
		return dataProcessStatusManagerDao;
	}

}
