package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublicationsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.queries.QueriesWrapperSpeedUp;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

/**
 * Service layer which implement all operations about Queries
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class QueriesServiceImpl implements IQueriesService {

	private QueriesManagerDao queriesManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	private final static String queries = ResourcesTypeUtils.queries.getName();

	@Autowired
	public QueriesServiceImpl(QueriesManagerDao queriesManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.queriesManagerDao = queriesManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	public IQuery getById(Long id) {
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			return null;

		IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
		return query_;
	}

	@Override
	public List<IPublication> getQueryPublications(Long id) throws PublicationManagerException{
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		List<Publications> listPublications = queriesManagerDao.getPublicationsAuxDao().findPublicationsByQueryId(id);
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications publication : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	@Override
	public List<IPublication> getQueryPublicationsPaginated(Long queryId, Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) throws PublicationManagerException {
		Queries query = queriesManagerDao.getQueriesDao().findById(queryId);
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);
		List<Publications> listPublications = queriesManagerDao.getPublicationsAuxDao().findPublicationsByQueryIdPaginated(queryId, paginationIndex, paginationSize, asc, sortBy);
		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications publication : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}
	
	@Override
	public Long getQueryPublicationsCount(Long queryId) throws PublicationManagerException {
		Queries query = queriesManagerDao.getQueriesDao().findById(queryId);
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Long count = queriesManagerDao.getPublicationsAuxDao().countPublicationsByQueryId(queryId);
		return count;

	}
	
	
	
	

	@Override
	public List<IQuery> getAllPrivilegesQueriesAdminAccess() {
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}

		List<Queries> listQueries = null;
		if (isAdmin)
			listQueries = queriesManagerDao.getQueriesDao().findAll();
		else
			listQueries = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributes(user.getAuId(), queries, "owner");

		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			if(query.isQuActive())
			{
				IQuery query_ = QueriesWrapperSpeedUp.convertToAnoteStructure(query);
				listQueries_.add(query_);
			}
		}

		return listQueries_;
	}
	
	@Override
	public List<IQuery> getAllPrivilegesQueriesAdminAccessPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}

		List<Queries> listQueries = null;
		if (isAdmin)
			listQueries = queriesManagerDao.getQueriesAuxDao().findAllQueriesPaginated(paginationIndex, paginationSize, asc, sortBy);
		else
			listQueries = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributesPaginated(user.getAuId(), queries, "owner", paginationIndex, paginationSize, asc, sortBy);

		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			if(query.isQuActive())
			{
				IQuery query_ = QueriesWrapperSpeedUp.convertToAnoteStructure(query);
				listQueries_.add(query_);
			}
		}

		return listQueries_;
	}
	
	@Override
	public Integer countAllPrivilegesQueriesAdminAccess() {
		Boolean isAdmin = false;
		String roleAdmin = RolesEnum.role_admin.toString();
		AuthUsers user = userLogged.getCurrentUserLogged();
		usersManagerDao.getAuthUsersDao().refresh(user);

		Set<AuthGroupHasRoles> groupHasRoles = user.getAuthGroups().getAuthGroupHasRoleses();
		for (AuthGroupHasRoles groupHasRole : groupHasRoles) {
			String role = groupHasRole.getAuthRoles().getArRoleCode();
			if (role.equals(roleAdmin)) {
				isAdmin = true;
				break;
			}
		}

		List<Queries> listQueries = null;
		if (isAdmin)
			return queriesManagerDao.getQueriesAuxDao().countActiveQueries();
		else
			return queriesManagerDao.getQueriesAuxDao().countActiveQueriesByAttributes(user.getAuId(), queries, "owner");

	}

	@Override
	public List<IQuery> getAllQueries() {

		AuthUsers user = userLogged.getCurrentUserLogged();
		/**
		 * get all queries from a user
		 */
		List<Queries> listQueries = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributes(user.getAuId(), queries);

		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			if(query.isQuActive())
			{
				IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
				listQueries_.add(query_);
			}
		}

		return listQueries_;
	}
	
	@Override
	public Integer countAllQueries(){
		AuthUsers user = userLogged.getCurrentUserLogged();
		return queriesManagerDao.getQueriesAuxDao().countQueriesByAttributes(user.getAuId(), queries);
	}
	
	@Override
	public Integer countAllActiveQueries(){
		AuthUsers user = userLogged.getCurrentUserLogged();
		return queriesManagerDao.getQueriesAuxDao().countActiveQueriesByAttributes(user.getAuId(), queries);
	}
	
	
	@Override
	public List<IQuery> getAllQueriesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy) {

		AuthUsers user = userLogged.getCurrentUserLogged();
		/**
		 * get all queries from a user
		 */
		List<Queries> listQueries = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributesPaginated(user.getAuId(), queries, paginationIndex, paginationSize, asc, sortBy);

		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			if(query.isQuActive())
			{
				IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
				listQueries_.add(query_);
			}
		}

		return listQueries_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(IQuery query_) {

		AuthUsers user = userLogged.getCurrentUserLogged();
		Queries query = QueriesWrapper.convertToDaemonStructure(query_);
		QueryTypes queryType = query.getQueryTypes();

		// -- create queryType if not exist
		if (queryType != null) {

			QueryTypes queryTypeDb = queriesManagerDao.getQueryTypes().findUniqueByAttribute("qtDescription", queryType.getQtDescription());
			if (queryTypeDb == null)
				queriesManagerDao.getQueryTypes().save(queryType);
			else
				query.setQueryTypes(queryTypeDb);
		}

		queriesManagerDao.getQueriesDao().save(query);
		// Save Query Properties 
		Set<QueryProperties> queryProperties = query.getQueryPropertieses();
		for(QueryProperties queryProperty:queryProperties)
		{
			queriesManagerDao.getQueryProperties().save(queryProperty);
		}
		
		AuthUserDataObjectsId dataObjectUserId = new AuthUserDataObjectsId(user.getAuId(), query.getQuId(), queries);
		AuthUserDataObjects dataObjectUser = new AuthUserDataObjects(dataObjectUserId, user, "owner");
		usersManagerDao.getAuthUserDataObjectsDao().save(dataObjectUser);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "queries/query_types/auth_user_data_objects", null, "create query");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(IQuery query_) throws PublicationManagerException{
		Queries query = queriesManagerDao.getQueriesDao().findById(query_.getId());
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		AuthUsers user = userLogged.getCurrentUserLogged();
		Queries queryNew = QueriesWrapper.convertToDaemonStructure(query_);

		query.setQuQueryDate(queryNew.getQuQueryDate());
		query.setQuMatchingPublications(queryNew.getQuMatchingPublications());
		query.setQuAvailableAbstracts(queryNew.getQuAvailableAbstracts());
		query.setQuQueryName(queryNew.getQuQueryName());
		query.setQuActive(queryNew.isQuActive());
		query.setQuNotes(queryNew.getQuNotes());

		queriesManagerDao.getQueriesDao().update(query);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "queries", null, "update query");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addPublicationsToQuery(Long id, Set<Long> publicationsIds) throws PublicationManagerException{

		boolean isSaved = false;
		AuthUsers user = userLogged.getCurrentUserLogged();
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		for (Long publicationId : publicationsIds) {
			QueryHasPublicationsId queryHasPubId = new QueryHasPublicationsId(id, publicationId);
			QueryHasPublications queryHasPubExist = queriesManagerDao.getQueryHasPublicationsDao().findById(queryHasPubId);
			if (queryHasPubExist == null) {
				isSaved = true;
				QueryHasPublications queryHasPub = new QueryHasPublications(queryHasPubId, null, null);
				queriesManagerDao.getQueryHasPublicationsDao().save(queryHasPub);
			}
		}
		/*
		 * log
		 */
		if (isSaved) {
			AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "queries_has_publications", null, "associate publications to a query");
			usersManagerDao.getAuthUserLogsDao().save(log);
		}

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance) {

		AuthUsers user = userLogged.getCurrentUserLogged();
		QueryHasPublicationsId queriesPubId = new QueryHasPublicationsId(queryId, publicationId);
		QueryHasPublications queriesPub = new QueryHasPublications(queriesPubId, null, null);

		queriesPub.setQhbRelevance(relevance);
		queriesManagerDao.getQueryHasPublicationsDao().update(queriesPub);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "queries_has_publications", null, "update relevance");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public Map<Long, RelevanceTypeEnum> getQueryPublicationsRelevance(Long queryId) throws PublicationManagerException{
		Queries query = queriesManagerDao.getQueriesDao().findById(queryId);
		if (query == null)
			throw new PublicationManagerException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Set<QueryHasPublications> queriesHasPub = query.getQueryHasPublicationses();
		Map<Long, RelevanceTypeEnum> map = new HashMap<Long, RelevanceTypeEnum>();
		for (QueryHasPublications queryHasPub : queriesHasPub) {
			Long pubId = queryHasPub.getId().getQhbPublicationId();
			map.put(pubId, RelevanceTypeEnum.convertString(queryHasPub.getQhbRelevance()));
		}

		return map;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean inactiveQuery(Long queryId) {

		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserDataObjectsId id = new AuthUserDataObjectsId(user.getAuId(), queryId, queries);
		AuthUserDataObjects userObject = usersManagerDao.getAuthUserDataObjectsDao().findById(id);
		if (userObject.getAudoPermission().equals("owner")) {
			Queries query = queriesManagerDao.getQueriesDao().findById(queryId);
			query.setQuActive(false);
			queriesManagerDao.getQueriesDao().update(query);
		} else {
			usersManagerDao.getAuthUserDataObjectsDao().delete(userObject);
		}

		AuthUserLogs log = new AuthUserLogs(user, new Date(), "delete", "queries/users_has_data_object", null, "remove/inactive query");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public Set<String> getQueryPublicationsExternalIDFromSource(Long queryId, String source) {

		Set<String> response = new HashSet<String>();

		PublicationSources publicationSource = queriesManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", source);
		if (publicationSource == null) {
			// throw new
			// PublicationManagerException(ExceptionsCodes.codePublicationSource,
			// ExceptionsCodes.msgPublicationSource);
			return response;
		}

		List<Object> objects = queriesManagerDao.getPublicationsAuxDao().getQueryPublicationBySource(publicationSource.getPssId(), queryId);
		for (Object object : objects) {
			String publicationsExternalID = (String) object;
			response.add(publicationsExternalID);
		}

		return response;
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}
}
