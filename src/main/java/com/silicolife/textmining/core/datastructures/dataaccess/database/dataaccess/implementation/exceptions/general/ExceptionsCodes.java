package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general;

/**
 * Exceptions codes and messages used in anote2dameon exceptions.
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ExceptionsCodes {

	
	/*
	 * anote2 errors
	 */

	public static final String codeWrongCredentials = "wrongCredentials";
	public static final String msgWrongCredentials = "The username or password are wrong";
	
	public static final String codeNoUser = "noUser";
	public static final String msgNoUser = "User does not exist";
	
	public static final String codePrivilegeExist = "privilegeUserExist";
	public static final String msgPrivilegeExist = "Already exist user/privileges to this resource";
	
	public static final String codePrivilegeNoExist = "privilegeUserNoExist";
	public static final String msgPrivilegeNoExist = "No exist user/privileges to this resource";
	
	public static final String codeNoQuery = "noQuery";
	public static final String msgNoQuery = "Query does not exist";
	
	public static final String codeNoPublication = "noPublication";
	public static final String msgNoPublication = "Publication does not exist in daemon";
	
	public static final String codeNoCorpus = "noCorpus";
	public static final String msgNoCorpus = "Corpus does not exit";
	
	public static final String codeResourceAccessDenied = "resourceDenied";
	public static final String msgResourceAccessDenied = "You do not have access to that resource";
	
	public static final String codeNoResource = "noResource";
	public static final String msgNoResource = "Resource do not exist";
	
	public static final String codeNoClass = "noClass";
	public static final String msgNoClass = "Class do not exist";
	
	public static final String codeNoResourceElement = "noResourceElement";
	public static final String msgNoResourceElement = "Resource Element do not exist";
	
	public static final String codeCorpusPublicationAlreadyExists = "alreadycorpuspublication";
	public static final String msgCorpusPublicationAlreadyExists = "Publication already exist in Corpus";
	
	public static final String codeCorpusPublicationNotExists = "notexistscorpuspublication";
	public static final String msgCorpusPublicationNotExists = "Publication not exist in Corpus";
	
	public static final String codePublicationAlreadyFullText = "publicationalreadyhasfulltext";
	public static final String msgPublicationAlreadyFullText = "Publciation already contains full text";
	
	public static final String codeNoProcess = "noProcess";
	public static final String msgNoProcess = "Process does not exist";

	public static final String codeProcessAlreadyInOneCorpus = "ProcessAlreadyInOneCorpus";
	public static final String msgProcessAlreadyInOneCorpus = "Process Already in a Corpus";
	
	public static final String codeProcessNotInCorpus = "ProcessNotExistsForCorpus";
	public static final String msgProcessNotInCorpus = "Process not exists for a Corpus";

	public static final String codeClassExists = "ClassAlreadyExists";
	public static final String msgClassExists = "Class already exists";
	
	public static final String codeClassNameExists = "ClassNameAlreadyExists";
	public static final String msgClassNameExists = "Class with name already exists";
	
	public static final String codeNoNullClass = "nonullclass";
	public static final String msgNoNullClass = "Entity class can not be null";

	public static final String codeNoAnnotation = "noannotation";
	public static final String msgNoAnnotation = "Annotation do not exist";
	
	public static final String codeNoHyperLinkMenuItem = "nohyperlinkmenuitem";
	public static final String msgNoHyperLinkMenuItem = "Hyperlink menu item do not exist";
	
	public static final String codeHyperLinkMenuItemExists = "hyperlinkmenuitemExists";
	public static final String msgHyperLinkMenuItemExists = "Hyperlink menu item id already exists";
	
	public static final String codeNoSource = "nosource";
	public static final String msgNoSource= "Source does not exists";
	
	
	public static final String codeNoClusteringProcess = "codeNoClusteringProcess";
	public static final String msgNoClusteringProcess = "Clustering Process not exist";


	/*
	 * API codes
	 */
	
	public static final String codeRestClient = "restClient";
	public static final String accessDeniedCode = "accessDenied";
	public static final String generalCode = "general";
	public static final String parseJsonCode = "parseJson";
	public static final String nullPointerCode = "nullPointer";
	public static final String codeConstraint = "keysConstraint";
	public static final String codeWrongValue = "wrongTypeValue";
	public static final String codeHibernateAccess = "hibernateAccess";
	public static final String codeHibernateGeneral = "hibernateGeneral";


	
	
	
	
	

	
	
//	public static final String generalCode = "general";
//	public static final String parseJsonCode = "parseJson";
//	public static final String nullPointerCode = "nullPointer";
//	public static final String accessDeniedCode = "accessDenied";
//
//	
//	/**
//	 * Errors Hibernate/Database
//	 */
//	
//	public static final String codeConstraint = "code:key";
//	public static final String codeWrongValue = "wrongTypeValue";
//	public static final String codeGeneralHibernate = "c";
//	
//	
//	/**
//	 * Error codes
//	 */
//	
//
//	public static final String codeNoQuery = "noQuery";
//	public static final String codeNoUser = "noUser";
//	public static final String codeExistObject = "existObject";
//	public static final String codeNoPublication = "noPublication";
//	public static final String codeNoResourceType = "noResourceType";
//	public static final String codeQueryAccessDenied = "queryDenied";
//	public static final String codeQueryPublication = "noAssociationQueryPub";
//	public static final String codePublicationSource = "noPublicationSource";
//	public static final String codeWrongCredentials = "wrongCredentials";
//	public static final String codeResourceAccessDenied = "resourceDenied";
//	public static final String codeNoCluster = "noCluster";
//	public static final String codeNoCorpus = "noCorpus";
//	
//	
//
//	/**
//	 * Error Messages
//	 */
//
//	public static final String msgNoQuery = "Query does not exist in daemon";
//	public static final String msgExistObject = "Already exist user/privileges to this resource";
//	public static final String msgNoUser = "User does not exist";
//	public static final String msgNoPublication = "Publication does not exist in daemon";
//	public static final String msgNoResourceType = "Daemon resource type Queries does not exist";
//	public static final String msgQueryAccessDenied = "Query Access denied";
//	public static final String msgQueryPublication = "Association query publication does not exist";
//	public static final String msgPublicationSource = "Publication source does not exist";
//	public static final String msgWrongCredentials = "The username or password are wrong. :D";
//	public static final String msgResourceAccessDenied = "Puff... you do not access to that anote2daemon resource. :d";
//	public static final String msgNoCluster = "Cluster does not exist";
//	public static final String msgNoCorpus = "Corpus does not exit";

}
