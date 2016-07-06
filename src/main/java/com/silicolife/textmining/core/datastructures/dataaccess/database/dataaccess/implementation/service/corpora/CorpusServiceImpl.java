package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ProcessesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasProcessesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.PublicationSources;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.RolesEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process.ProcessWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.publications.PublicationsWrapper;
import com.silicolife.textmining.core.datastructures.documents.DocumentSetImpl;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusStatisticsImpl;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * Service layer which implements all operations about corpus.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
@Service
@Transactional(readOnly = true)
public class CorpusServiceImpl implements ICorpusService {

	private CorpusManagerDao corpusManagerDao;
	private ProcessesManagerDao processesManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	private final static String corpusStr = ResourcesTypeUtils.corpus.getName();
	private final static String processStr = ResourcesTypeUtils.processes.getName();


	@Autowired
	public CorpusServiceImpl(CorpusManagerDao corpusManagerDao,ProcessesManagerDao processesManagerDao, UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.corpusManagerDao = corpusManagerDao;
		this.processesManagerDao=processesManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	public List<ICorpus> getAllCorpus() {

		AuthUsers user = userLogged.getCurrentUserLogged();
		/**
		 * get all corpus from a user
		 */

		List<Corpus> corpus = corpusManagerDao.getCorpusAuxDao().findQueriesByAttributes(user.getAuId(), corpusStr);

		List<ICorpus> corpus_ = new ArrayList<ICorpus>();
		for (Corpus corpusObj : corpus) {
			ICorpus corpusObj_ = CorpusWrapper.convertToAnoteStructure(corpusObj);
			corpus_.add(corpusObj_);
		}
		//		if (corpus_.size() == 0)
		//			return null;

		return corpus_;
	}

	@Override
	public ICorpus getCorpusByID(Long id) {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(id);
		if (corpus == null)
			return null;

		ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);

		return corpus_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createCorpus(ICorpus corpus_) {

		Corpus corpus = CorpusWrapper.convertToDaemonStructure(corpus_);

		/*
		 * save corpus
		 */
		corpusManagerDao.getCorpusDao().save(corpus);

		/*
		 * save corpus preferences
		 */
		Set<CorpusProperties> corpusProperties = corpus.getCorpusPropertieses();
		for (CorpusProperties corpusProperty : corpusProperties) {
			createCorpusProperties(corpusProperty);
		}

		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserDataObjectsId dataObjectUserId = new AuthUserDataObjectsId(user.getAuId(), corpus.getCrpId(), corpusStr);
		AuthUserDataObjects dataObjectUser = new AuthUserDataObjects(dataObjectUserId, user, "owner");
		usersManagerDao.getAuthUserDataObjectsDao().save(dataObjectUser);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "corpus/corpus_properties/auth_user_data_objects", null, "create corpus");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public boolean updateCorpus(ICorpus corpus_) throws CorpusException {

		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpus_.getId());
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		/*
		 * update corpus
		 */
		corpus.setCrpCorpusName(corpus_.getDescription());
		corpus.setCrpNotes(corpus_.getNotes());
		//		corpus.setCrpActive(corpus_.is);
		AuthUsers user = userLogged.getCurrentUserLogged();
		corpusManagerDao.getCorpusDao().update(corpus);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "update", "corpus", null, "update corpus");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public IDocumentSet getCorpusPublications(Long corpusId) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);

		IDocumentSet documentSet = new DocumentSetImpl();
		List<Publications> publications = corpusManagerDao.getPublicationsAuxDao().findPublicationsByCorpusId(corpusId);
		for (Publications publication : publications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			documentSet.addDocument(publication_.getId(), publication_);
		}

		//		if (documentSet.size() == 0)
		//			return null;

		return documentSet;
	}

	@Override
	public Long getCorpusPublicationsCount(Long corpusId) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);

		Long count = corpusManagerDao.getPublicationsAuxDao().countPublicationsByCorpusId(corpusId);
		return count;

	}

	@Override
	public IDocumentSet getCorpusPublicationsPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);

		IDocumentSet documentSet = new DocumentSetImpl();
		List<Publications> publications = corpusManagerDao.getPublicationsAuxDao().findPublicationsByCorpusIdPaginated(corpusId, paginationIndex, paginationSize);
		for (Publications publication : publications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			documentSet.addDocument(publication_.getId(), publication_);
		}

		//		if (documentSet.size() == 0)
		//			return null;

		return documentSet;
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(Long corpusId) throws CorpusException {

		AuthUsers user = userLogged.getCurrentUserLogged();

		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);

		List<IIEProcess> processes_ = new ArrayList<IIEProcess>();
		List<Processes> processes = corpusManagerDao.getCorpusAuxDao().findProcessesByCorpusId(corpusId,user.getAuId(), processStr);
		for (Processes process : processes) {
			IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
			processes_.add(process_);
		}
		//		if (processes_.size() == 0)
		//			return null;

		return processes_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean registerCorpusProcess(Long corpusId, Long processId) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processesManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new CorpusException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		CorpusHasProcesses list = corpusManagerDao.getCorpusHasProcessesDao().findUniqueByAttribute("processes.proId",processes.getProId());
		if(list!=null)
			throw new CorpusException(ExceptionsCodes.codeProcessAlreadyInOneCorpus, ExceptionsCodes.msgProcessAlreadyInOneCorpus);
		/*
		 * save processes
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		CorpusHasProcessesId id = new CorpusHasProcessesId(corpusId, processId);
		CorpusHasProcesses CorpusAsprocesses = new CorpusHasProcesses(id, corpus, processes);
		corpusManagerDao.getCorpusDao().save(CorpusAsprocesses);
		/*
		 * log
		 */
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "corpus_has_processes", null, "Association between corpus and process");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	/*
	 * private auxiliary methods to save corpus properties
	 */
	private void createCorpusProperties(CorpusProperties properties) {
		corpusManagerDao.getCorpusPropertiesDao().save(properties);
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean addCorpusPublication(Long corpusId, Long publicationID) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Publications publication = corpusManagerDao.getPublicationsDao().findById(publicationID);
		if (publication == null)
			throw new CorpusException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

		CorpusHasPublicationsId id = new CorpusHasPublicationsId(corpusId, publicationID);
		if(corpusManagerDao.getCorpusHasPublicationsDao().findById(id)!=null)
			throw new CorpusException(ExceptionsCodes.codeCorpusPublicationAlreadyExists, ExceptionsCodes.msgCorpusPublicationAlreadyExists);	

		CorpusHasPublications corpushaspublication = new CorpusHasPublications(id , corpus, publication);
		corpusManagerDao.getCorpusHasPublicationsDao().save(corpushaspublication);
		/*
		 * log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "corpus_has_publication", null, "Association between corpus and publication");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public ICorpusStatistics getCorpusStatistics(Long corpusId) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		AuthUsers user = userLogged.getCurrentUserLogged();
		Integer documents = corpusManagerDao.getCorpusAuxDao().getCorpusDocumentNumber(corpusId);
		Integer processes = corpusManagerDao.getCorpusAuxDao().getCorpusProcessesNumber(corpusId,user.getAuId(), processStr);
		ICorpusStatistics corpusstatistics = new CorpusStatisticsImpl(documents, processes);
		return corpusstatistics;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean inativateCorpus(Long corpusId) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserDataObjectsId id = new AuthUserDataObjectsId(user.getAuId(), corpusId, corpusStr);
		AuthUserDataObjects userObject = usersManagerDao.getAuthUserDataObjectsDao().findById(id);
		if (userObject.getAudoPermission().equals("owner")) {
			corpus.setCrpActive(false);
			corpusManagerDao.getCorpusDao().update(corpus);
		} else {
			usersManagerDao.getAuthUserDataObjectsDao().delete(userObject);

		}

		AuthUserLogs log = new AuthUserLogs(user, new Date(), "Remove/inactive", "corpus/users_has_data_object", null, "remove/inactive corpus");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;

	}

	@Override
	public List<ICorpus> getAllPrivilegesCorpusAdminAccess() {
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

		List<Corpus> listCorpus = null;
		if (isAdmin)
			listCorpus = corpusManagerDao.getCorpusDao().findAll();
		else
			listCorpus = corpusManagerDao.getCorpusAuxDao().findCorpusByAttributes(user.getAuId(), corpusStr, "owner");

		List<ICorpus> listCorpus_ = new ArrayList<ICorpus>();
		for (Corpus corpus : listCorpus) {
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);
			listCorpus_.add(corpus_);
		}

		return listCorpus_;
	}

	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(Long corpusId, Long processId,
			Integer paginationIndex, Integer paginationSize) throws CorpusException {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new CorpusException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);
		Processes processes = processesManagerDao.getProcessesDao().findById(processId);
		if (processes == null)
			throw new CorpusException(ExceptionsCodes.codeNoProcess, ExceptionsCodes.msgNoProcess);
		
		CorpusHasProcessesId corpusHasProcessid = new CorpusHasProcessesId(corpusId, processId);
		CorpusHasProcesses corpusHasProcess = corpusManagerDao.getCorpusHasProcessesDao().findById(corpusHasProcessid);
		if(corpusHasProcess==null)
			throw new CorpusException(ExceptionsCodes.codeProcessNotInCorpus, ExceptionsCodes.msgProcessNotInCorpus);
		
		IDocumentSet documentSet = new DocumentSetImpl();
		List<Publications> publications = corpusManagerDao.getPublicationsAuxDao().findPublicationsByCorpusIdAndProcessIdNotProcessedPaginated(corpusId, processId, paginationIndex, paginationSize);
		for (Publications publication : publications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			documentSet.addDocument(publication_.getId(), publication_);
		}

		//		if (documentSet.size() == 0)
		//			return null;

		return documentSet;
	}

	@Override
	public Set<String> getCorpusPublicationsExternalIDFromSource(Long corpusId, String source) {
		Set<String> response = new HashSet<String>();
		PublicationSources publicationSource = corpusManagerDao.getPublicationSourcesDao().findUniqueByAttribute("pssDescription", source);
		if (publicationSource == null) {
			return response;
		}
		
		List<Object> objects = corpusManagerDao.getPublicationsAuxDao().getCorpusPublicationBySource(publicationSource.getPssId(), corpusId);
		for (Object object : objects) {
			String publicationsExternalID = (String) object;
			response.add(publicationsExternalID);
		}
		
		return response;
	}

}
