package com.silicolife.textmining.core.datastructures.dataaccess.server;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering.IClusteringService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink.IHyperLinkService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.IPrivilegesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.ISystemService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users.IUserService;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class SpringInjectedServicesAccess implements ApplicationContextAware{

	private static IQueriesService queriesService = null;
	private static IPublicationsService publicationsService = null;
	private static IPrivilegesService privilegesService = null;
	private static IResourcesService resourcesService = null;
	private static IClassesService classesService = null;
	private static IResourcesElementService resourcesElementService = null;
	private static ICorpusService corpusService = null;
	private static IProcessesService processesService = null;
	private static IAnnotationService annotationService = null;
	private static IClusteringService clusteringService = null;
	private static IHyperLinkService hyperLinkService = null;
	private static ISystemService systemService = null;
	private static IUserService userService = null;
	private static UsersLogged usersLogged = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
		queriesService = applicationContext.getBean("queriesServiceImpl", IQueriesService.class);
		publicationsService = applicationContext.getBean("publicationsServiceImpl", IPublicationsService.class);
		privilegesService = applicationContext.getBean("privilegesServiceImpl", IPrivilegesService.class);
		resourcesService = applicationContext.getBean("resourcesServiceImpl", IResourcesService.class);
		classesService = applicationContext.getBean("classesServiceImpl", IClassesService.class);
		resourcesElementService = applicationContext.getBean("resourcesElementServiceImpl", IResourcesElementService.class);
		corpusService = applicationContext.getBean("corpusServiceImpl", ICorpusService.class);
		processesService = applicationContext.getBean("processesServiceImpl", IProcessesService.class);
		annotationService = applicationContext.getBean("annotationServiceImpl", IAnnotationService.class);
		clusteringService = applicationContext.getBean("clusteringServiceImpl", IClusteringService.class);
		hyperLinkService = applicationContext.getBean("hyperLinkServiceImpl", IHyperLinkService.class);
//		systemService = applicationContext.getBean("systemServiceImpl", ISystemService.class);
		userService = applicationContext.getBean("userServiceImpl", IUserService.class);
		usersLogged = applicationContext.getBean("usersLogged", UsersLogged.class);
				
		initServerAccess();
	}


	private void initServerAccess() {
		ServerAccess serverAccess = new ServerAccess();
		try {
			InitConfiguration.init(serverAccess, null, new Properties());
		} catch (ANoteException e) {
			e.printStackTrace();
		}
	}

	
	public static IQueriesService getQueriesService() {
		return queriesService;
	}

	public static IPublicationsService getPublicationsService() {
		return publicationsService;
	}

	public static IPrivilegesService getPrivilegesService() {
		return privilegesService;
	}

	public static IResourcesService getResourcesService() {
		return resourcesService;
	}

	public static IClassesService getClassesService() {
		return classesService;
	}

	public static IResourcesElementService getResourcesElementService() {
		return resourcesElementService;
	}

	public static ICorpusService getCorpusService() {
		return corpusService;
	}

	public static IProcessesService getProcessesService() {
		return processesService;
	}

	public static IAnnotationService getAnnotationService() {
		return annotationService;
	}

	public static IClusteringService getClusteringService() {
		return clusteringService;
	}

	public static IHyperLinkService getHyperLinkService() {
		return hyperLinkService;
	}

	public static ISystemService getSystemService() {
		return systemService;
	}

	public static IUserService getUserService() {
		return userService;
	}

	public static UsersLogged getUsersLogged() {
		return usersLogged;
	}

}
