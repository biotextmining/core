package com.silicolife.textmining.core.datastructures.dataaccess.database.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import com.silicolife.textmining.core.datastructures.dataaccess.database.HelpDatabase;
import com.silicolife.textmining.core.datastructures.dataaccess.database.instances.MySQLDatabase;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class ConvertDatabase {
	
	private IDatabase databaseToConvert;
	private IDatabase target;
	private File tmpDirectory;
	private File anote2Directory;
	private int heapSize = 10;
	private String startLineInterpolation = "INSERT INTO %s VALUES ";
	private String endLine = ";\n";
	private Map<String,Integer> processesOriginProcessOriginID; 
	private Map<String,Integer> publicationSourceID;
	private Map<Integer,String> publicationSourceIDSource;
	private Map<String,Integer> queryTypeQueryTypeID;
	private File diricon;
	public static final String userID = "1";
	public static final String userName = "admin";
	public static final String userPwd = "0bd2e8ab6e1f46f6e6093b4e9385ee01fd410df8723a8ebdaff85425ab8b2946";
	public static final String userFullname = "admin";
	public static final String useremail = "admin@anote.com";
	public static final String userAcessLevelAdminID = "1";
	public static final String userAcessLevelAdminCode = "role_admin";
	public static final String userAcessLevelAdminDes = "admin access";
	public static final String usergroupID = "1";
	public static final String usergroupIDName = "Admin";


	public ConvertDatabase(IDatabase databaseToConvert,IDatabase target,File anote2Directory,File tmpDirectory)
	{
		this.databaseToConvert = databaseToConvert;
		this.target = target;
		this.tmpDirectory = tmpDirectory;
		this.anote2Directory = anote2Directory;
	}
	
	public void convertDatabase206to210() throws SQLException, IOException, ANoteException
	{
		processesOriginProcessOriginID = new HashMap<String, Integer>();
		publicationSourceID = new HashMap<String, Integer>();
		publicationSourceIDSource = new HashMap<>();
		queryTypeQueryTypeID = new HashMap<String, Integer>();
		// test if database is convertable
		int version = HelpDatabase.getDatabaseVersion(databaseToConvert);
		if(version!=5)
		{
			throw new ConvertDatabaseException("Source Database could not be converted");
		}
		// Test if target database does not exist
		if(HelpDatabase.testIfDatabaseExistes(target))
		{
			throw new ConvertDatabaseException("Target Database can not be created yet");
		}
		if(!tmpDirectory.isDirectory())
		{
			throw new ConvertDatabaseException("Temporary Directory can not be a File");
		}
		createSourceDatabaseTmpFiles(tmpDirectory,databaseToConvert);
		createDatabase(target);
		loadData(tmpDirectory,target);
		System.out.println("Database Converted");
	}

	private void loadData(File tmpDirectory2, IDatabase target) throws FileNotFoundException, SQLException, IOException {
		for(File file:tmpDirectory2.listFiles())
		{
			if(file.isFile() && file.getAbsolutePath().endsWith(".sql"))
			{
				System.out.println("Load "+file.getName());
				target.fillDataBaseTables(file.getAbsolutePath());
			}
		}
		// update Icons
		System.out.println("Update Icons ");
		updateIcons(diricon,target);
	}

	private void updateIcons(File tmpDirectory2, IDatabase target) throws SQLException, FileNotFoundException {
		PreparedStatement ps = target.getConnection().prepareStatement("UPDATE hyper_link_menus SET hyl_icon=? WHERE hyl_id=?");
		for(File file:tmpDirectory2.listFiles())
		{
			if(file.isFile() && file.getAbsolutePath().endsWith(".png"))
			{
				String fileName = file.getName();
				FileInputStream fIn = new FileInputStream(file);
				ps.setBlob(1, fIn);
				ps.setInt(2, Integer.valueOf(FilenameUtils.removeExtension(fileName)));
				ps.execute();
			}
		}
		ps.close();
	}

	private void createDatabase(IDatabase target) throws SQLException, FileNotFoundException, IOException {
		System.out.println("Create Database");
		target.createDataBase();
		System.out.println("Fill Database");
		target.fillDataBaseTables(GlobalOptions.mysqlDatabaseFileVersion2WithouInsert);
		System.out.println("Database Fill Complete");
	}

	private void createSourceDatabaseTmpFiles(File tmpDirectory, IDatabase databaseToConvert) throws SQLException, IOException {
		System.out.println("Annotations");
		createGenericFileAddNotesInTheEnd(tmpDirectory,databaseToConvert,"annotations","annotations");
		System.out.println("Annotation Log");
		createAnnotationLogFile(tmpDirectory,databaseToConvert,"annotations_log","annotation_logs");
		System.out.println("Annotation Properties");
		createGenericFile(tmpDirectory,databaseToConvert,"annotations_properties","annotation_properties");
		System.out.println("Annotation Sides");
		createGenericFile(tmpDirectory,databaseToConvert,"annotations_side","annotation_sides");
		System.out.println("Class Hierarchy");
		createGenericFile(tmpDirectory,databaseToConvert,"class_hierarchy","class_hierarchies");
		System.out.println("Classes");
		createClassColorFile(tmpDirectory,databaseToConvert,"classes","classes");
		System.out.println("Cluster Labels");
		createGenericFile(tmpDirectory,databaseToConvert,"clusters_labels","cluster_labels");
		System.out.println("Cluster Labels Documents");
		createGenericFile(tmpDirectory,databaseToConvert,"clusters_labels_documents","cluster_label_publications");
		System.out.println("Cluster Process");
		createGenericFile(tmpDirectory,databaseToConvert,"clusters_process","cluster_processes");	
		System.out.println("Cluster Process Has Clusters Labels");
		createGenericFile(tmpDirectory,databaseToConvert,"clusters_process_has_clusters_labels","cluster_process_has_labels");	
		System.out.println("Cluster Properties");
		createGenericFile(tmpDirectory,databaseToConvert,"clusters_properties","cluster_properties");	
		System.out.println("Corpus");
		createGenericFileAddNotesInTheEnd(tmpDirectory,databaseToConvert,"corpus","corpus");
		System.out.println("Corpus Has Process");
		createGenericFile(tmpDirectory,databaseToConvert,"corpus_has_processes","corpus_has_processes");
		System.out.println("Corpus Has Publications");
		createGenericFile(tmpDirectory,databaseToConvert,"corpus_has_publications","corpus_has_publications");
		System.out.println("Corpus Properties");
		createGenericFile(tmpDirectory,databaseToConvert,"corpus_properties","corpus_properties");
		System.out.println("Hypher Link Menu Source Association");
		createGenericFile(tmpDirectory,databaseToConvert,"hypher_link_menu_source_association","hyper_link_menu_source_association");
		System.out.println("Hypher Link Menu");
		createHyperLinkmenuFile(tmpDirectory,databaseToConvert,"hypher_links_menu","hyper_link_menus");
		System.out.println("Hypher Link Sub Menus");
		createGenericFile(tmpDirectory,databaseToConvert,"hypher_links_submenus","hyper_link_submenus");
		System.out.println("Process Origins");
		processesOriginProcessOriginID = createProcessesOriginMap();
		createProcessesOriginFile(tmpDirectory,"process_origin","process_origins",processesOriginProcessOriginID);
		System.out.println("Process Properties");
		createGenericFile(tmpDirectory,databaseToConvert,"process_properties","process_properties");
		System.out.println("Processes");
		createProcessFile(tmpDirectory,databaseToConvert,"processes","processes");
		System.out.println("Process Types");
		createGenericFile(tmpDirectory,databaseToConvert,"processes_type","process_types");
		System.out.println("Publication Fields");
		createGenericFile(tmpDirectory,databaseToConvert,"publication_fields","publication_fields");
		System.out.println("Publications");	
		createPublicationsFile(tmpDirectory,databaseToConvert,"publications","publications");
		System.out.println("Publication Sources");	
		createPublicationSourceMap();
		createPublicationsSourceFile(tmpDirectory,databaseToConvert,"publication_sources");
		System.out.println("Publications Has Publication Sources");	
		createPublicationsHasPublicationSourceFile(tmpDirectory,databaseToConvert,"publication_has_sources");
		System.out.println("Query Types");
		queryTypeQueryTypeID = createQueriesTypeMap();
		createQueriesTypeFile(tmpDirectory,databaseToConvert,"query_types");
		System.out.println("Queries");
		createQueriesFile(tmpDirectory,databaseToConvert,"queries");
		System.out.println("Query Has Cluesters Process");
		createGenericFile(tmpDirectory,databaseToConvert,"queries_has_clusters_process","query_has_cluster_processes");
		System.out.println("Queries Has Publications And Relevance");
		createQueriesHasPublicationAndRelevance(tmpDirectory,databaseToConvert,"queries_has_publications","query_has_publications");
		System.out.println("Query Properties");
		createGenericFile(tmpDirectory,databaseToConvert,"queries_properties","query_properties");
		System.out.println("Relation Type");
		createGenericFile(tmpDirectory,databaseToConvert,"relation_type","resource_element_relation_types");	
		System.out.println("Resource Elements");
		createResourceElmementsFile(tmpDirectory,databaseToConvert,"resource_elements","resource_elements");	
		System.out.println("Resource Elements External ID");
		createResourceElementsExternalIDFile(tmpDirectory,databaseToConvert,"resource_element_extenal_ids");
		System.out.println("Resource Elements Relation");
		createGenericFile(tmpDirectory,databaseToConvert,"resource_elements_relation","resource_element_relations");	
		System.out.println("Resources");
		createGenericFile(tmpDirectory,databaseToConvert,"resources","resources");	
		System.out.println("Resources Type");
		createGenericFile(tmpDirectory,databaseToConvert,"resources_type","resource_types");	
		System.out.println("Sources");
		createGenericFile(tmpDirectory,databaseToConvert,"sources","sources");	
		System.out.println("Synonyms");
		createGenericFile(tmpDirectory,databaseToConvert,"synonyms","synonyms");	
		System.out.println("Create User");
		createFileUser(tmpDirectory,"auth_users");
		System.out.println("Create User Groups");
		createFileUserGroups(tmpDirectory,"auth_groups");
		System.out.println("Create User Groups has User Access Levels");
		createFileUserGroupsHasUsersAccessLevel(tmpDirectory,"auth_group_has_roles");
		System.out.println("Create User Access Levels");
		createFileUserLevels(tmpDirectory,"auth_roles");
		System.out.println("Create Settings");
		createFileSettings(tmpDirectory,anote2Directory,"auth_user_settings");
		System.out.println("Versions");
		createFileVersion(tmpDirectory,anote2Directory,"versions");
		System.out.println("Files Created Done");
	}


	private void createFileVersion(File tmpDirectory2, File anote2Directory2,String newtable) throws IOException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		String[] obj = new String[3];
		obj[0] = new String("1");
		obj[1] = (new Date()).toString();
		obj[2] = "New 2.1 Database";
		objects.add(obj);
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();			
	}

	private void createClassColorFile(File tmpDirectory2,IDatabase databaseToConvert2, String oldtable, String newtable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT cl.idclasses,cl.class,color.color FROM classes AS cl "+
																				 "LEFT JOIN classes_colors AS color ON cl.idclasses=color.classes_idclasses");
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();				
	}

	private void createFileSettings(File tmpDirectory2, File anote2Directory, String newtable) throws IOException {
		Properties prop = findProperties(anote2Directory);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		for(String propName:prop.stringPropertyNames())
		{
			String[] obj = new String[3];
			obj[0] = userID;
			obj[1] = propName;
			obj[2] = prop.getProperty(propName);
			objects.add(obj);
		}
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();		
	}

	private Properties findProperties(File anote2Directory) throws IOException {
		Properties properties = new Properties();
		File conf = new File(anote2Directory.getAbsolutePath()+"/conf/settings");
		if(conf.exists() && conf.isDirectory())
		{
			List<File> files = new ArrayList<File>();
			listf(conf.getAbsolutePath(), files);
			for(File file :files)
			{
				FileInputStream in = new FileInputStream(file);
				Properties auxproperties = new Properties();
				auxproperties.load(in);
				properties.putAll(auxproperties);
			}
		}
		return properties;
	}
	
	public void listf(String directoryName, List<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() && file.getAbsolutePath().endsWith(".conf")) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}

	private void createFileUserGroupsHasUsersAccessLevel(File tmpDirectory,String newtable) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		String[] obj = new String[2];
		obj[0] = usergroupID;
		obj[1] = userAcessLevelAdminID;
		objects.add(obj);
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();		
	}

	private void createFileUserGroups(File tmpDirectory2, String newtable) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		String[] obj = new String[2];
		obj[0] = usergroupID;
		obj[1] = usergroupIDName;
		objects.add(obj);
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();
		
	}

	private void createFileUser(File tmpDirectory, String newtable) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		String[] obj = new String[10];
		obj[0] = userID;
		obj[1] = usergroupID;
		obj[2] = userName;
		obj[3] = userPwd;
		obj[4] = userFullname;
		obj[5] = useremail;
		obj[6] = "null";
		obj[7] = "null";
		obj[8] = "null";
		obj[9] = "null";
		objects.add(obj);
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();		
	}

	private void createFileUserLevels(File tmpDirectory, String newtable) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		String[] obj = new String[3];
		obj[0] = userAcessLevelAdminID;
		obj[1] = userAcessLevelAdminCode;
		obj[2] = userAcessLevelAdminDes;
		objects.add(obj);
		writeLines(writer,objects ,String.format(startLineInterpolation, newtable),endLine);	
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();			
	}

	private void createAnnotationLogFile(File tmpDirectory2,IDatabase databaseToConvert2, String oldtable, String newtable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum+1];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			object[colum] = ConvertDatabase.userID;
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();		
	}

	private void createQueriesHasPublicationAndRelevance(File tmpDirectory,IDatabase databaseToConvert, String tableName, String newTableName) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT  qp.queries_idqueries, qp.publications_id, docrel.relevance FROM queries_has_publications AS qp "+
																				  "LEFT JOIN document_relevance AS docrel ON qp.publications_id=docrel.publications_id AND qp.queries_idqueries=docrel.queries_idqueries ");
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newTableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newTableName);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newTableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newTableName),endLine);
		writeUnLockTable(writer,newTableName);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();
	}

	private void createResourceElementsExternalIDFile(File tmpDirectory2,IDatabase databaseToConvert2, String tableName) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT resource_elements_idresource_elements,sources_idsources,external_id,active FROM resource_elements_extenal_id ");
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+tableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,tableName);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
		writeUnLockTable(writer,tableName);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();
	}

	private void createResourceElmementsFile(File tmpDirectory2,IDatabase databaseToConvert2, String oldtable, String newtable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT relem.idresource_elements,relem.resources_idresources,relem.classes_idclasses,relem.element,prio.priorety,relem.active FROM resource_elements AS relem "+
				"LEFT OUTER JOIN resources_elements_prioreties AS prio ON prio.resource_elements_idresource_elements=relem.idresource_elements ");
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();
		ps.close();
		rs.close();
	}

	private void createQueriesFile(File tmpDirectory2,IDatabase databaseToConvert2, String tableName) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+tableName);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+tableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,tableName);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum+1];
			object[0] = rs.getString(1);
			boolean fromPubMEd = rs.getBoolean(10);
			if(fromPubMEd)
				object[1] = String.valueOf(queryTypeQueryTypeID.get("PUBMED"));
			else
				object[1] = String.valueOf(queryTypeQueryTypeID.get("Unknown"));
			object[2] = rs.getString(2);
			object[3] = rs.getString(3);
			object[4] = rs.getString(4);
			object[5] = "";
			object[6] = rs.getString(5);
			object[7] = rs.getString(7);
			object[8] = rs.getString(8);
			object[9] = rs.getString(9);
			object[10] =  "";
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
		writeUnLockTable(writer,tableName);
		writeFooter(writer);
		writer.close();
		ps.close();
		rs.close();
	}

	private void createQueriesTypeFile(File tmpDirectory2,IDatabase databaseToConvert2, String tableName) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+tableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,tableName);
		List<String[]> objects = new ArrayList<String[]>();
		for(String sourceID:queryTypeQueryTypeID.keySet())
		{
			String[] object = new String[2];
			object[0] = String.valueOf(queryTypeQueryTypeID.get(sourceID));
			object[1] = sourceID;
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
		writeUnLockTable(writer,tableName);
		writeFooter(writer);
		writer.close();	
		
	}

	private Map<String, Integer> createQueriesTypeMap() {
		Map<String, Integer> mapQueryTypeQueryTypeID = new HashMap<String, Integer>();
		mapQueryTypeQueryTypeID.put("PUBMED", 1);
		mapQueryTypeQueryTypeID.put("Unknown", 2);
		return mapQueryTypeQueryTypeID;
	}

	private void createPublicationsHasPublicationSourceFile(File tmpDirectory2,IDatabase databaseToConvert2, String tableName) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM publications");
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+tableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int publictionID = rs.getInt(1);
			String otherID = rs.getString(2);
			int publicationType = rs.getInt(16);
			if(otherID!=null && this.publicationSourceIDSource.containsKey(publicationType) && !otherID.isEmpty())
			{
				String[] data = new String[3];
				data[0] = String.valueOf(publictionID);
				data[1] = String.valueOf(publicationType);
				data[2] = otherID;
				objects.add(data);
			}
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
		writeUnLockTable(writer,tableName);
		writeFooter(writer);
		writer.close();
		ps.close();
		rs.close();
	}

	private void createPublicationsSourceFile(File tmpDirectory,IDatabase databaseToConvert2, String tableName) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+tableName+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,tableName);
		List<String[]> objects = new ArrayList<String[]>();
		for(Integer sourceID:publicationSourceIDSource.keySet())
		{
			String[] object = new String[2];
			object[0] = String.valueOf(sourceID);
			object[1] = publicationSourceIDSource.get(sourceID);
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, tableName),endLine);
		writeUnLockTable(writer,tableName);
		writeFooter(writer);
		writer.close();			
	}

	private void createPublicationSourceMap() throws SQLException {
		Map<String,String> alreadyKnownMaping = new HashMap<String, String>();
		alreadyKnownMaping.put("pmid", "PUBMED");
		alreadyKnownMaping.put("doi", "DOI");
		alreadyKnownMaping.put("epodoc", "EPODOC");
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM publications_id_type");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int id = rs.getInt(1);
			String name = rs.getString(2);
			if(alreadyKnownMaping.containsKey(name))
			{
				publicationSourceID.put(alreadyKnownMaping.get(name), id);
				publicationSourceIDSource.put(id, alreadyKnownMaping.get(name));
			}
		}
		ps.close();
		rs.close();
	}

	private void createPublicationsFile(File tmpDirectory2,IDatabase databaseToConvert2, String oldtable, String newtable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum-1;i++)
			{
				String data = rs.getString(i);
				if(i>2)
				{
					object[i-2] = data;
				}
				else
				{
					object[i-1] = data;
				}
			}
			object[14] = object[15];
			object[15] = "";
			object[16] = findRelativePath(object[0],rs.getString(2));
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();
	}

	private String findRelativePath(String publicationID, String publicationOtherID) {
		if(this.anote2Directory==null || !this.anote2Directory.exists() || this.anote2Directory.isFile())
		{
			return "NULL";
		}
		else
		{
			File docDirectory = new File(anote2Directory + "/Docs");
			if(docDirectory.exists() && docDirectory.isDirectory())
			{
				String idSequence = docDirectory.getAbsolutePath()+"/id"+publicationID+".pdf";
				File idSequencefile = new File(idSequence);
				if(idSequencefile.exists() && idSequencefile.isFile())
				{
					return idSequencefile.getName();
				}
				String idAndOtherIDSequence = docDirectory.getAbsolutePath()+"/id"+publicationID+"-"+publicationOtherID+".pdf";
				File idAndOtherIDSequencefile = new File(idAndOtherIDSequence);
				if(idAndOtherIDSequencefile.exists() && idAndOtherIDSequencefile.isFile())
				{
					return idAndOtherIDSequencefile.getName();
				}
				String otherIDSequence = docDirectory.getAbsolutePath()+"/"+publicationOtherID+".pdf";
				File otherIDSequencefile = new File(otherIDSequence);
				if(otherIDSequencefile.exists() && otherIDSequencefile.isFile())
				{
					return otherIDSequencefile.getName();
				}
			}
		}
		return "NULL";
	}

	private void createProcessFile(File tmpDirectory2,IDatabase databaseToConvert2, String oldtable, String newtable) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum+2];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				if(i<3)
				{
					object[i-1] = data;
				}
				else
				{
					object[i] = data;
				}
			}
			object[2] = String.valueOf(processesOriginProcessOriginID.get(object[3]));
			object[5] = object[4];
			object[4] = new String();
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();
	}

	private void createProcessesOriginFile(File tmpDirectory2,String oldtable, String newtable, Map<String, Integer> processesOriginProcessOriginID) throws FileNotFoundException, UnsupportedEncodingException {
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		List<String[]> objects = new ArrayList<String[]>();
		for(String keyOriginType:processesOriginProcessOriginID.keySet())
		{
			String[] object = new String[2];
			object[0] = String.valueOf(processesOriginProcessOriginID.get(keyOriginType));
			object[1] = keyOriginType;
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();			
	}

	private Map<String, Integer> createProcessesOriginMap() throws SQLException {
		Map<String, Integer> mapResult = new HashMap<String, Integer>();
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM anote2_db.processes GROUP BY name");
		ResultSet rs = ps.executeQuery();
		int i=1;
		while(rs.next())
		{
			String name = rs.getString(3);
			mapResult.put(name, i);
			i++;
		}
		return mapResult;
	}

	private void createHyperLinkmenuFile(File tmpDirectory,IDatabase databaseToConvert, String oldtable, String newtable) throws SQLException, IOException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		diricon = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable);
		if(!diricon.exists())
			diricon.mkdir();
		PrintWriter writer = new PrintWriter(annotationFile);
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		Set<Integer> blobColumns = new HashSet<>();
		blobColumns.add(4);
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			int id = rs.getInt(1);
			File icon = new File(diricon.getAbsolutePath()+"/"+id+".png");
			FileOutputStream outStream = new FileOutputStream(icon);

			for(int i=1;i<=colum;i++)
			{
				String data;
				if(blobColumns.contains(i))
				{
					Blob blob = rs.getBlob(i);
					InputStream blobLength = blob.getBinaryStream();
					int b = 0;
					while ((b = blobLength.read()) != -1)
					{
						outStream.write(b); // fos is the instance of FileOutputStream
					}
					data = null;
				}
				else
				{
					data = rs.getString(i);
				}
				object[i-1] = data;
			}
			outStream.close();
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();
		ps.close();
		rs.close();
		
	}

	private void createGenericFile(File tmpDirectory2,IDatabase databaseToConvert2,String oldtable,String newtable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newtable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
		writeUnLockTable(writer,newtable);
		writeFooter(writer);
		writer.close();	
		ps.close();
		rs.close();
	}

	private void createGenericFileAddNotesInTheEnd(File tmpDirectory, IDatabase databaseToConvert,String oldtableName,String newTable) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtableName);
		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newTable+".sql");
		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
		writeHeader(writer,this.target.getSchema());
		writeLockTable(writer,newTable);
		ResultSet rs = ps.executeQuery();
		List<String[]> objects = new ArrayList<String[]>();
		while(rs.next())
		{
			int colum = rs.getMetaData().getColumnCount();
			String[] object = new String[colum+1];
			for(int i=1;i<=colum;i++)
			{
				String data = rs.getString(i);
				object[i-1] = data;
			}
			// Add notes field
			object[colum] = "";
			objects.add(object);
			if(objects.size() == heapSize)
			{
				writeLines(writer,objects,String.format(startLineInterpolation, newTable),endLine);
				objects = new ArrayList<String[]>();
			}
		}
		writeLines(writer,objects,String.format(startLineInterpolation, newTable),endLine);
		writeUnLockTable(writer,newTable);
		writeFooter(writer);
		writer.close();
		ps.close();
		rs.close();
	}
	
//	private void createGenericFileAddRemoveLastField(File tmpDirectory, IDatabase databaseToConvert,String oldtable, String newtable) throws SQLException, DatabaseLoadDriverException, FileNotFoundException, UnsupportedEncodingException {
//		PreparedStatement ps = databaseToConvert.getConnection().prepareStatement("SELECT * FROM "+oldtable);
//		File annotationFile = new File(tmpDirectory.getAbsoluteFile()+"/"+newtable+".sql");
//		PrintWriter writer = new PrintWriter(annotationFile, "UTF-8");
//		writeHeader(writer,this.target.getSchema());
//		writeLockTable(writer,newtable);
//		ResultSet rs = ps.executeQuery();
//		List<String[]> objects = new ArrayList<String[]>();
//		while(rs.next())
//		{
//			int colum = rs.getMetaData().getColumnCount();
//			String[] object = new String[colum-1];
//			for(int i=1;i<=colum-1;i++)
//			{
//				String data = rs.getString(i);
//				object[i-1] = data;
//			}
//			// Add notes field
//			objects.add(object);
//			if(objects.size() == heapSize)
//			{
//				writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
//				objects = new ArrayList<String[]>();
//			}
//		}
//		writeLines(writer,objects,String.format(startLineInterpolation, newtable),endLine);
//		writeUnLockTable(writer,newtable);
//		writeFooter(writer);
//		writer.close();
//		ps.close();
//		rs.close();
//	}

	private void writeLines(PrintWriter writer, List<String[]> objects, String startLine, String endLine) {
		if(objects.size() >0)
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append(startLine);
			for(int i=0;i<objects.size();i++)
			{
				buffer.append(writeLine(objects.get(i)));
				if(i!=objects.size()-1)
				{
					buffer.append(",");
				}
			}
			buffer.append(endLine); 
			writer.append(buffer);
		}
	}

	private String writeLine(String[] obj) {
		String result = new String() + "(";
		for(int i=0;i<obj.length;i++)
		{
			String datafield = obj[i];
			if(datafield == null)
				datafield = "NULL";
			else if(!Utils.isIntNumber(datafield))
				datafield = "'"+datafield.replaceAll("\\\\'","\\'").replaceAll("\'", "\\\\'").replaceAll("\n", "\\\\n").replaceAll("\\r", "")+"'";
			result = result +datafield + ",";
		}
		result = result.substring(0, result.length()-1);
		return result + ")";
	}

	private void writeUnLockTable(PrintWriter writer, String table) {
		
		String unlock = "/*!40000 ALTER TABLE "+table+" ENABLE KEYS */;\n"+
					    "UNLOCK TABLES;\n\n";
		writer.append(unlock);

	}

	private void writeLockTable(PrintWriter writer, String table) {
		String lockTAble = " LOCK TABLES "+table+ " WRITE;\n"+
						   "/*!40000 ALTER TABLE `"+table+"` DISABLE KEYS */;\n\n";
		writer.append(lockTAble);
	}

	private void writeFooter(PrintWriter writer) {
		String footer = "/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;\n"+
					    "/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;\n"+
					    "/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;\n"+
						"/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;\n"+
					    "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n"+
					    "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n"+
						"/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n"+
						"/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;\n\n";
		writer.append(footer);
	}

	private void writeHeader(PrintWriter writer,String database) {
		String header = "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n"+
						"/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n"+
						"/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n"+
						"/*!40101 SET NAMES utf8 */;\n"+
						"/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;\n"+
						"/*!40103 SET TIME_ZONE='+00:00' */;\n"+
						"/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;\n"+
						"/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\n"+
						"/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\n"+
						"/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;\n\n";
		writer.append(header);
	}
	
	public static void main(String[] args) {
		String user = "jcosta";
		String host ="192.168.1.2";
		String port = "3306";
		String schema1 = "syngenta_24nov_cur_rr";
		String schema2 = "anote2_23_march_15";
		String pwd = "admin";
		IDatabase databaseToConvert = new MySQLDatabase(host , port , schema1, user , pwd );
		IDatabase target = new MySQLDatabase(host, port, schema2, user, pwd);
		File tmpDirectory = new File("tmp/");
		tmpDirectory.mkdir();
		File docDirectory = new File("");
		ConvertDatabase convert = new ConvertDatabase(databaseToConvert, target, docDirectory,tmpDirectory);
		try {
			convert.convertDatabase206to210();
		} catch (SQLException | ANoteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
