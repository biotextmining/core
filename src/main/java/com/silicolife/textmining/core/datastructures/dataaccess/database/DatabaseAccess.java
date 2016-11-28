package com.silicolife.textmining.core.datastructures.dataaccess.database;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.dao.manager.LuceneManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.ILuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.LuceneServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources.IResourcesElementLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources.ResourcesElementLuceneServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLoggedImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.AnnotationServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering.ClusteringServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering.IClusteringService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.CorpusServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink.HyperLinkServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink.IHyperLinkService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.ProcessesServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.PublicationsServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.QueriesServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ClassesServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ResourcesElementServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ResourcesServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.IPrivilegesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.ISystemService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.PrivilegesServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.SystemServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users.IUserService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users.UserServiceImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.dataaccess.DataAccessDefaultSettings;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
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
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class DatabaseAccess implements IDataAccess {
	
	public static String driverMyslq = "org.mariadb.jdbc.Driver";


	private IDatabase db;
	private SessionFactory sessionFactory;
	private Configuration configuration;
	private ServiceRegistry servReg;
	private ManagerDao managerDao;
	private IQueriesService queriesService;
	private IPublicationsService publicationsService;
	private IPrivilegesService privilegesService;
	private IResourcesService resourcesService;
	private IClassesService classesService;
	private IResourcesElementService resourcesElementService;
	private ICorpusService corpusService;
	private IProcessesService processService;
	private IAnnotationService annotationService;
	private IClusteringService clusteringService;
	private IHyperLinkService hyperLinkService;
	private ISystemService systemService;
	
	private LuceneManagerDao luceneManagerDao;
	private ILuceneService luceneService;
	private IResourcesElementLuceneService luceneResourcesElementService;

	private IUserService userService;
	private UsersLogged userLogged = new UsersLoggedImpl();
	private boolean alreadyConfigurate = false;

	private String hibernateFilePath;

	public DatabaseAccess(IDatabase db,String hibernateFilePath) {
		this.db = db;
		this.hibernateFilePath=hibernateFilePath;
		this.alreadyConfigurate = false;
	}

	private void startHiberdate() {
		if (!alreadyConfigurate) {
			setHibernateConfiguration(db);
			alreadyConfigurate = true;
		}
	}
	
	public void setUserLoggedOnServices(UsersLogged userLogged)
	{

	}

	private void setHibernateConfiguration(IDatabase db) {
		File file = new File(hibernateFilePath);

		configuration = new Configuration().configure(file);

		configuration.setProperty("hibernate.connection.driver_class", driverMyslq);
		configuration.setProperty("hibernate.connection.url", db.getDataBaseURL());
		configuration.setProperty("hibernate.connection.username", db.getUser());
		configuration.setProperty("hibernate.connection.password", db.getPwd());
		configuration.setProperty("hibernate.default_catalog", db.getSchema());
		configuration.setProperty("hibernate.search.default.directory_provider", "filesystem");
		if(configuration.getProperty("hibernate.search.default.indexBase")==null)
		{
			String luceneIndexBaseDir = InitConfiguration.getPropertyValueFromInitOrProperties(DataAccessDefaultSettings.LUCENEINDEXBASEDIRECTORY).toString();
			String luceneIndex = luceneIndexBaseDir + "/" +db.getSchema();
			configuration.setProperty("hibernate.search.default.indexBase", luceneIndex);
		}
		else
		{
			String luceneIndex = configuration.getProperty("hibernate.search.default.indexBase") + "/" +db.getSchema();
			configuration.setProperty("hibernate.search.default.indexBase", luceneIndex);
		}

		servReg = new StandardServiceRegistryBuilder().configure(file).applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(servReg);


		userLogged = new UsersLoggedImpl();
		managerDao = new ManagerDao(sessionFactory);
		queriesService = new QueriesServiceImpl(managerDao.getQueriesManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		publicationsService = new PublicationsServiceImpl(managerDao.getQueriesManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		privilegesService = new PrivilegesServiceImpl(managerDao.getUsersManagerDao(), userLogged);
		resourcesService = new ResourcesServiceImpl(managerDao.getResourcesManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		resourcesElementService = new ResourcesElementServiceImpl(managerDao.getResourcesManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		classesService = new ClassesServiceImpl(managerDao.getResourcesManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		userService = new UserServiceImpl(managerDao.getUsersManagerDao(), userLogged);
		corpusService = new CorpusServiceImpl(managerDao.getCorpusManagerDao(),managerDao.getProcessServiceDao(), managerDao.getUsersManagerDao(), userLogged);
		processService = new ProcessesServiceImpl(managerDao.getProcessServiceDao(),managerDao.getAnnotationManagerDao(), managerDao.getUsersManagerDao(), userLogged);
		annotationService = new AnnotationServiceImpl(managerDao.getAnnotationManagerDao(),managerDao.getProcessServiceDao(),managerDao.getCorpusManagerDao(),
				managerDao.getResourcesManagerDao(),managerDao.getUsersManagerDao(), userLogged);
		hyperLinkService = new HyperLinkServiceImpl(managerDao.getHyperLinkMenuDao(), userLogged);

		clusteringService = new ClusteringServiceImpl(managerDao.getClusterManagerDao(), managerDao.getUsersManagerDao(), managerDao.getQueriesManagerDao(), userLogged);
		systemService = new SystemServiceImpl(managerDao.getSystemServiceDao());
		
		luceneManagerDao = new LuceneManagerDao(sessionFactory);
		luceneService = new LuceneServiceImpl(sessionFactory);
		luceneResourcesElementService = new ResourcesElementLuceneServiceImpl(luceneManagerDao.getResourcesLuceneManagerDao(), managerDao.getResourcesManagerDao());
	}

	@Override
	public void login(String username, String password) throws ANoteException {
		startHiberdate();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IUser user = userService.login(username, password);
			sessionFactory.getCurrentSession().getTransaction().commit();
			userLogged.setCurrentUserLogged((AuthUsers) user);
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Boolean checkLogin(String username, String password) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean exist = userService.checkLogin(username, password);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return exist;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void logout() throws ANoteException {
		this.userLogged.setCurrentUserLogged(null);
	}

	@Override
	public void addUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource, PermissionsUtilsEnum privilege) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.addPrivilege(user.getAuId(), resourceId, resource.getName(), privilege.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource, PermissionsUtilsEnum privilege) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.updatePrivilege(user.getAuId(), resourceId, resource.getName(), privilege.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.removePrivilege(user.getAuId(), resourceId, resource.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void createUser(IUser user) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			userService.createUser(user);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateUser(IUser user) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			userService.updateUser(user);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeUser(IUser user) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			userService.removeUser(user.getAuId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IUser getUserByEmail(String email) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IUser user = userService.getByEmail(email);
			sessionFactory.getCurrentSession().getTransaction().commit();
			if (user != null)
				user.setAuPassword(null);
			return user;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IUser getUserByUsername(String username) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IUser user = userService.getByUsername(username);
			sessionFactory.getCurrentSession().getTransaction().commit();
			if (user != null)
				user.setAuPassword(null);
			return user;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IGroup> getAllGroups() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IGroup> groups = userService.getAllGroups();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return groups;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IUser> getAllUsers() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<AuthUsers> authUsers = userService.getAllUsers();
			sessionFactory.getCurrentSession().getTransaction().commit();
			List<IUser> users = new ArrayList<IUser>();
			for (AuthUsers user : authUsers) {
				user.setAuPassword(null);
				users.add(user);
			}
			return users;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Set<String> getQueryPublicationsExternalIDFromSource(IQuery query, String source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Set<String> sourceImnternalIds = queriesService.getQueryPublicationsExternalIDFromSource(query.getId(), source);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return sourceImnternalIds;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IPublication> getQueryPublications(IQuery query) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IPublication> publications = queriesService.getQueryPublications(query.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return publications;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public synchronized void addQueryPublications(IQuery query, Set<IPublication> publications) throws ANoteException {
		Set<Long> publicationsIds = new HashSet<Long>();
		for (IPublication pub : publications)
			publicationsIds.add(pub.getId());

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			queriesService.addPublicationsToQuery(query.getId(), publicationsIds);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public synchronized void createQuery(IQuery query) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			queriesService.create(query);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public synchronized void updateQuery(IQuery query) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			queriesService.update(query);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inactiveQuery(IQuery query) throws ANoteException {
	}

	@Override
	public List<IQuery> getAllQueries() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IQuery> queries = queriesService.getAllQueries();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return queries;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IQuery> getPrivilegesAllQueriesAdminAccess() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IQuery> queries = queriesService.getAllPrivilegesQueriesAdminAccess();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return queries;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IQuery getQueryByID(long queryID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IQuery query = queriesService.getById(queryID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return query;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void createClustering(IClusterProcess clustering) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			clusteringService.createClustering(clustering);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public synchronized void addPublications(Set<IPublication> documents) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			publicationsService.create(documents);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IPublication getPublication(long documentID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IPublication publication = publicationsService.getById(documentID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return publication;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public synchronized void updatePublication(IPublication publication) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			publicationsService.update(publication);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Map<String, Long> allExternalIds = publicationsService.getAllPublicationsIdFromSource(source);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return allExternalIds;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public String getPublicationFullText(IPublication publication) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			String fullText = publicationsService.getFullText(publication.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return fullText;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}


	@Override
	public Set<IAnoteClass> getClasses() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Set<IAnoteClass> classes = classesService.getClasses();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return classes;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void createCorpus(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			corpusService.createCorpus(corpus);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	public void updateCorpus(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			corpusService.updateCorpus(corpus);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inactivateCorpus(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();		
			privilegesService.removePrivilegeLoggedUser(corpus.getId(), ResourcesTypeUtils.corpus.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<ICorpus> getAllCorpus() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<ICorpus> corpus = corpusService.getAllCorpus();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpus;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public ICorpus getCorpusByID(long corpusID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			ICorpus corpus = corpusService.getCorpusByID(corpusID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpus;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void registerCorpusProcess(ICorpus corpus, IIEProcess process) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			corpusService.registerCorpusProcess(corpus.getId(), process.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IIEProcess> corpusprocesses = corpusService.getCorpusProcesses(corpus.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpusprocesses;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IDocumentSet getCorpusPublications(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IDocumentSet corpusDocumentSet = corpusService.getCorpusPublications(corpus.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpusDocumentSet;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Long getCorpusPublicationsCount(ICorpus corpus) throws ANoteException{
		try{
			sessionFactory.getCurrentSession().beginTransaction();
			Long documentsCount = corpusService.getCorpusPublicationsCount(corpus.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return documentsCount;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IDocumentSet getCorpusPublicationsPaginated(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException{
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IDocumentSet corpusDocumentSet = corpusService.getCorpusPublicationsPaginated(corpus.getId(), paginationIndex, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpusDocumentSet;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void createIEProcess(IIEProcess processe) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			processService.createIEProcess(processe);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateIEProcess(IIEProcess process) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			processService.updateIEProcess(process);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inactivateProcess(IIEProcess process) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.removePrivilegeLoggedUser(process.getId(), ResourcesTypeUtils.processes.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IIEProcess getProcessByID(long processID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IIEProcess ieprocess = processService.getProcessByID(processID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return ieprocess;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IEventAnnotation> getAnnotatedDocumentEvents(IAnnotatedDocument annotedDocument) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IEventAnnotation> events = annotationService.getProcessDoumentAnnotationEvents(annotedDocument.getProcess().getId(),annotedDocument.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return events;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntities(IAnnotatedDocument annotedDocument) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IEntityAnnotation> entities = annotationService.getProcessDoumentAnnotationEntities(annotedDocument.getId(),annotedDocument.getProcess().getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return entities;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addProcessDocumentEntitiesAnnotations(IIEProcess schema, IPublication document, List<IEntityAnnotation> entityAnnotations) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			annotationService.addCorpusProcessDocumentEntityAnootations(schema.getCorpus().getId(), schema.getId(), document.getId(), entityAnnotations);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addProcessDocumentEventAnnoations(IIEProcess process, IPublication document, List<IEventAnnotation> events) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			annotationService.addCorpusProcessDocumentEventsAnootations(process.getCorpus().getId(), process.getId(), document.getId(), events);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inactiveAnnotations(List<Long> annotation) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			annotationService.inactiveAnnotations(annotation);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateEntityAnnotations(List<IEntityAnnotation> list) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			annotationService.updateEntityAnnotations(list);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public void updateEventsAnnotations(List<IEventAnnotation> list) throws ANoteException {

	}

	@Override
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(IIEProcess process, IPublication document) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			SortedSet<IAnnotationLog> sortedLog = annotationService.getProcessDocumentLogs(process.getId(), document.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return sortedLog;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			SortedSet<IAnnotationLog> sortedLog = annotationService.getProcessLogs(process.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return sortedLog;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}


	@Override
	public List<IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IManualCurationAnnotations manualCurationAnnoattions = annotationService.getProcessDocumentAnnotationsAssociatedToLogs(ieProcess.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return manualCurationAnnoattions.getAnnotations();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void createResource(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesService.create(resource);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateResource(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesService.update(resource);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inativeResource(IResource<IResourceElement> resource) throws ANoteException {

	}

	@Override
	public void removeResource(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.removePrivilegeLoggedUser(resource.getId(), ResourcesTypeUtils.resources.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IResource<IResourceElement>> getPrivilegesAllResourcesAdminAccess() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IResource<IResourceElement>> resources = resourcesService.getAllPrivilegesResourcesAdminAccess();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resources;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesByType(String type) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IResource<IResourceElement>> resources = resourcesService.getResourcesByType(type);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resources;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResource<IResourceElement> getResourceByID(long resourceID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResource<IResourceElement> resource = resourcesService.getResourcesById(resourceID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resource;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Set<IAnoteClass> getResourceClassContent(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Set<IAnoteClass> resourceContent = resourcesElementService.getResourceClassContent(resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceContent;

		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public boolean checkResourceHasElements(IResource<IResourceElement> resource) throws ANoteException {

		return false;
	}

	@Override
	public IResourceManagerReport addResourceElements(IResource<IResourceElement> resource, List<IResourceElement> elem) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.addResourceElements(resource.getId(), elem);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceManagerReport addResourceElementSynomyns(IResource<IResourceElement> resource,IResourceElement elem, List<String> synonyms) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.addResourceElementSynonyms(resource.getId(),elem.getId(),synonyms);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceManagerReport addResourceElementExternalIds(IResource<IResourceElement> resource,IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.addResourceElementExternalIDs(resource.getId(),elem.getId(),externalIDs);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.updateResourceElement(elem);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.inactivateResourceElement(elem.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElements(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> resourceElement = resourcesElementService.getResourceElements(resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceElement;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IResource<IResourceElement> resource, IAnoteClass klaAnoteClass) throws ANoteException {

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> resourceElement = resourcesElementService.getResourceElementsByClass(resource.getId(), klaAnoteClass.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceElement;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeResourceClass(IResource<IResourceElement> resource, long classID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.removeResourceClass(resource.getId(), classID);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElement getResourceElementByID(long resourceID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElement resourceElement = resourcesElementService.getResourceElemenByID(resourceID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceElement;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.removeResourceElementSynonyms(elem.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeResourceElementSynonym(IResourceElement elem, String synonym) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.removeResourceElementSynonym(elem.getId(),synonym);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addResourceElementsRelation(IResourceElement a, IResourceElement b, String relationType) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.addResourceElementsRelation(a.getId(),b.getId(),relationType);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IUser getUser() {
		return userLogged.getCurrentUserLogged();
	}

	@Override
	public void updateQueryDocumentRelevance(IPublication publication, IQuery query, IQueryPublicationRelevance relevance) throws ANoteException {

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			if (relevance.getRelevance() == RelevanceTypeEnum.none) {
				queriesService.updateRelevance(query.getId(), publication.getId(), null);
			} else {
				queriesService.updateRelevance(query.getId(), publication.getId(), relevance.getRelevance().name());
			}
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addClusteringLabels(IClusterProcess clusterProcess) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			clusteringService.addClusteringLabels(clusterProcess.getId(), clusterProcess.getClusterLabels());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IClusterProcess> getQueryClusters(IQuery query) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IClusterProcess> clusters = clusteringService.getClustersFromQuery(query.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return clusters;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IClusterProcess getClusterByID(long clusterID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IClusterProcess clustering = clusteringService.getClusteringById(clusterID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return clustering;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void inactivateCluster(IClusterProcess clustering) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			clusteringService.inactivateClustering(clustering.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public int getDatabaseVersion() throws ANoteException {
		startHiberdate();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			int version = systemService.getDataversion();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return version;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public boolean addDatabaseVersion(int version, String commments) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			systemService.addDatabaseVersion(version,commments);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
		return true;
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
	public List<IPublicationLabel> getAllLabels() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IPublicationLabel> allPubLabels = publicationsService.getAllPublicationLabels();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return allPubLabels;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updatePublicationAvailableFreeFullText(IPublication pub) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			publicationsService.updatePublicationAvailableFreeFullText(pub.getId(), pub.isFreeFullText());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IUserDataObject getUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IUserDataObject object = privilegesService.getPrivilege(user.getAuId(), resourceId, resource.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return object;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Boolean hasPermissionRole(List<String> roles) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean hasRoles = userService.hasPermissionRole(roles);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return hasRoles;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Boolean hasPermission(IQuery query, List<String> permission) throws ANoteException {

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean hasRoles = privilegesService.hasPermission(query.getId(), ResourcesTypeUtils.queries.getName(), permission);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return hasRoles;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Boolean hasPermission(IResource<IResourceElement> resource, List<String> permission) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean hasRoles = privilegesService.hasPermission(resource.getId(), ResourcesTypeUtils.resources.getName(), permission);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return hasRoles;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	public IDatabase getDatabase() {
		return db;
	}

	@Override
	public void setUser(UsersLogged userLogged) throws ANoteException {
		if (this.userLogged.getCurrentUserLogged() == null) {
			login(userLogged.getCurrentUserLogged().getAuUsername(), userLogged.getCurrentUserLogged().getAuPassword());
		}
	}

	@Override
	public String toString() {
		return db.toString();
	}

	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, ResourcesTypeUtils resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IGenericPair<IUser, String>> usersAndPermissions = privilegesService.getUsersAndPermissions(resourceId, resource.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return usersAndPermissions;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	public boolean isDatabaseOutOfDate(String file) {
		try {
			return UpdateDatabaseHelp.readDatabaseFileDataBase(file) > this.getDatabaseVersion();
		} catch (ANoteException e) {
			return false;
		}
	}

	public boolean isFill() throws ANoteException, SQLException {
		return getDatabase().isfill();
	}

	@Override
	public void removeQuery(IQuery query) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			privilegesService.removePrivilegeLoggedUser(query.getId(), ResourcesTypeUtils.queries.getName());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public int getResourceMaxPriority(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			int maxPriority = resourcesElementService.getResourceMaxPriorety(resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return maxPriority;

		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceContent getResourceContent(IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceContent resourceContent = resourcesElementService.getResourceContent(resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceContent;

		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IExternalID> getResourceElementExternalIds(IResourceElement resourceElement)throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IExternalID> extenrlaIDs = resourcesElementService.getResourceElementExternalIDs(resourceElement.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return extenrlaIDs;

		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void insertNewClass(IAnoteClass klass) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			classesService.addClass(klass);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public boolean updateClassName(IAnoteClass oldclassName, String newCLass) throws ANoteException {
		return false;
	}

	@Override
	public IResourceManagerReport addResourceElementsWithouValidation(IResource<IResourceElement> resource, List<IResourceElement> element)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.addResourceElementsWithoutValidation(resource.getId(), element);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public boolean checkResourceElementExistsInResource(IResource<IResourceElement> resource,String term) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean exist = resourcesElementService.checkResourceElementExistsInResource(resource.getId(), term);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return exist;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceManagerReport updateResourceElementSynonym(IResource<IResourceElement> resource,IResourceElement elem, String oldSynonym, String newSynonym)  throws ANoteException{
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.updateResourceElementSynonym(resource.getId(),elem.getId(), oldSynonym,newSynonym);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void removeResourceElementExternalID(IResourceElement element,IExternalID extID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.removeResourceElementExternalID(element.getId(),extID);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}		
	}

	@Override
	public void removeResourceElementAllExternalID(IResourceElement element)throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			resourcesElementService.removeResourceElementAllExternalID(element.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public List<IResourceElementsRelation> getResourceElementsRelations(
			IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IResourceElementsRelation> relationList = resourcesElementService.getResourceElementsRelations(resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return relationList;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addCorpusPublication(ICorpus corpus, IPublication publication)throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			corpusService.addCorpusPublication(corpus.getId(),publication.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public ICorpusStatistics getCorpusStatistics(ICorpus corpus) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			ICorpusStatistics corpusStstistics = corpusService.getCorpusStatistics(corpus.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpusStstistics;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updatePublicationFullTextContent(IPublication publication) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			publicationsService.updatePublicationAFullTextContent(publication.getId(), publication.getFullTextContent());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}		
	}

	@Override
	public Boolean hasPermission(ICorpus corpus, List<String> permission) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean hasRoles = privilegesService.hasPermission(corpus.getId(), ResourcesTypeUtils.corpus.getName(), permission);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return hasRoles;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateAnoteKlass(IAnoteClass klass) throws ANoteException{
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			classesService.updateClass(klass);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public IIEProcessStatistics getIEProcessStatistics(IIEProcess ieProcessImpl) throws ANoteException{	
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IIEProcessStatistics statistcs = processService.getProcessStatistics(ieProcessImpl.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return statistcs;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(
			IAnnotatedDocument annotatedDocument) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IAnnotatedDocumentStatistics statistcs = annotationService.getProcessDocumentStatistics(annotatedDocument.getId(),annotatedDocument.getProcess().getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return statistcs;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResource<IResourceElement> getResourceFromResourceElementByID(Long resourceElementID) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResource<IResourceElement> resource = resourcesElementService.getResourceFromResourceElement(resourceElementID);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resource;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Boolean hasPermission(IIEProcess process, List<String> permission) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Boolean hasRoles = privilegesService.hasPermission(process.getId(), ResourcesTypeUtils.processes.getName(), permission);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return hasRoles;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void addProcessDocumentLogs(List<IAnnotationLog> annotationLogs)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			annotationService.addAnnotationLogs(annotationLogs);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public long getResourceElementIDMathingByText(IResource<IResourceElement> resource, IResourceElement elemennt, String text, boolean casesensitive) throws ANoteException {
		return 0;
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByName(IResource<IResourceElement> resource, String name)
			throws ANoteException {
		IResourceElementSet<IResourceElement> result = new ResourceElementSetImpl<>();
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			result = resourcesElementService.getResourceElementsByName(resource.getId(), name);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return result;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IHyperLinkMenuItem getHyperLinkMenuItemById(Long id)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IHyperLinkMenuItem menuItem = hyperLinkService.getHyperLinkMenuItemByID(id);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return menuItem;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems()
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IHyperLinkMenuItem> menuItems = hyperLinkService.getAllHyperLinkMenuItems();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return menuItems;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			hyperLinkService.updateSourcesHyperLinkMenuItem(hyperLinkMenuItem);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public List<ISource> getAllSources() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<ISource> sources = hyperLinkService.getAllSources();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return sources;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<ICorpus> getPrivilegesAllCorpusAdminAccess() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<ICorpus> corpus = corpusService.getAllPrivilegesCorpusAdminAccess();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return corpus;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IIEProcess> processes = processService.getPrivilegesAllProcessesAdminAccess();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return processes;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(ISource source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IHyperLinkMenuItem> menuItems = hyperLinkService.getHyperLinkMenuItemsForSource(source.getSourceID());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return menuItems;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public void saveProperties(Properties properties) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			userService.saveProperties(properties);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}		
	}

	@Override
	public Properties loadProperties(Set<String> propertiesIdentifiers)throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Properties prop = userService.loadProperties(propertiesIdentifiers);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return prop;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}	
	}

	@Override
	public void registerQueryClustering(IQuery query, IClusterProcess clustering) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			clusteringService.registerQueryClustering(query.getId(),clustering.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public void updateQueryClusteringProcess(IClusterProcess clustering) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			clusteringService.updateQueryClusteringProcess(clustering);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}

	}

	@Override
	public Boolean runServerProcesses(String klass,String configuration) throws ANoteException {
		throw new ANoteException("Method not available in Database Access");

	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(
			IResource<IResourceElement> resource, Integer index, Integer pagination) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> resourceElement = resourcesElementService.getResourceElementsInBatchWithLimit(resource.getId(), index, pagination);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resourceElement;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IDocumentSet documents = corpusService.getCorpusPublicationsNotProcessedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return documents;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Set<String> getCorpusPublicationsExternalIDFromSource(ICorpus corpus, String source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Set<String> interalIDs = corpusService.getCorpusPublicationsExternalIDFromSource(corpus.getId(), source);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return interalIDs;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public Long countCorpusPublicationsNotProcessed(IIEProcess process) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Long count = corpusService.countCorpusPublicationsNotProcessed(process.getCorpus().getId(), process.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return count;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}
	

	@Override
	public IResourceManagerReport addResourceElementSynomynsWithoutValidation(IResource<IResourceElement> resource,
			IResourceElement elem, List<String> synonyms) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceManagerReport report = resourcesElementService.addResourceElementSynonymsWithoutValidation(resource.getId(),elem.getId(),synonyms);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return report;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}		
	}
	
	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID externalId)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> resoruceelements = resourcesElementService.getResourceElementsByExternalID(externalId);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return resoruceelements;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public List<IPublication> getPublicationByResourceElement(IResourceElement resourceElement) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			List<IPublication> publications = annotationService.getPublicationByResourceElement(resourceElement.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return publications;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public boolean rebuildLuceneIndex() throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			boolean result = luceneService.rebuildLuceneIndex();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return result;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByExactTerm(term);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByExactSynonym(synonym);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(IResource<IResourceElement> resource,String term) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsFromResourceByExactTerm(term, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(
			String partialterm) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByPartialTerm(partialterm);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String partialSynonym) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByPartialSynonym(partialSynonym);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(
			IResource<IResourceElement> resource,String synonym) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsFromResourceByExactSynonym(synonym, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(IResource<IResourceElement> resource,String partialTerm) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsFromResourceByPartialTerm(partialTerm, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(
			IResource<IResourceElement> resource,String partialSynonym) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsFromResourceByPartialSynonym(partialSynonym, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(
			String partialTerm, int index, int paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByPartialTermPaginated(partialTerm, index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(
			String partialSynonym, int index, int paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService.getResourceElementsByPartialSynonymPaginated(partialSynonym, index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(
			IResource<IResourceElement> resource,String partialTerm, int index, int paginationSize)
			throws ANoteException {
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceByPartialTermPaginated(partialTerm, resource.getId(), index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(
			String partialSynonym, IResource<IResourceElement> resource, int index, int paginationSize)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceByPartialSynonymPaginated(partialSynonym, resource.getId(), index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalId(String externalId)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsByExactExternalID(externalId);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalId(
			String externalId, IResource<IResourceElement> resource) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceByExactExternalID(externalId, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalId(String partialExternalId)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsByPartialExternalID(partialExternalId);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalId(
			IResource<IResourceElement> resource,String partialExternalId) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceByPartialExternalID(partialExternalId, resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdPaginated(
			String partialExternalId, int index, int paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsByPartialExternalIDPaginated(partialExternalId, index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdPaginated(
			IResource<IResourceElement> resource,String partialExternalId,  int index, int paginationSize)
			throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceByPartialExternalIDPaginated(partialExternalId, resource.getId(), index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalIdFromSource(
			String externalId, ISource source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromSourceByExactExternalID(externalId, source.getSourceID());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalIdAndSource(
			IResource<IResourceElement> resource, ISource source,String externalId) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceAndSourceByExactExternalID(externalId, source.getSourceID(), resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalId(
			String partialExternalId, ISource source) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromSourceByPartialExternalID(partialExternalId, source.getSourceID());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdAndSource(
			IResource<IResourceElement> resource, ISource source,String partialExternalId) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceAndSourceByPartialExternalID(partialExternalId, source.getSourceID(), resource.getId());
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdFromSourcePaginated(
			ISource source,String partialExternalId,int index, int paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromSourceByPartialExternalIDPaginated(partialExternalId, source.getSourceID(), index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdAndSourcePaginated(
			IResource<IResourceElement> resource, ISource source,String partialExternalId,  int index,
			int paginationSize) throws ANoteException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			IResourceElementSet<IResourceElement> elemnts = luceneResourcesElementService
					.getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(partialExternalId, source.getSourceID(), resource.getId(), index, paginationSize);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return elemnts;
		} catch (RuntimeException e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw new ANoteException(e);
		}
	}
}