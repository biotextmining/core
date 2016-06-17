package com.silicolife.textmining.core.datastructures.utils.conf;

import java.text.DecimalFormat;

import com.silicolife.textmining.core.interfaces.core.configuration.IProxy;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class GlobalOptions {
	
	public static int searchYearStarting = 1900;

	public static String defaulQuerytName = "[KEYWORDS]:[ORGANISM]:[Date]";
	
	public static final String anote2Version = "2.1";
	
	public static final String mysqlDatabase = "anote_db";
	
	public static final String configurationFile = "conf/settings.conf";
	
	public static final String hibernateConfigurationFile = "conf/hibernate.cfg.xml";
	
	public static final String sessionFile = "conf/sessionfile.ssf";
	
	public static final String savetmpsettings = "conf/settings/settings_tmp.conf";
	
	public static final String language = "en";
	
	public static final String languageFilesDirectory = "conf/language/";

	public static final String languagePathFile = languageFilesDirectory + "/language_"+language+".lang";
	
	public static final String alternativelanguagePathFile = "src/main/resources" + "/language_"+language+".lang";
		
	public static final String mysqlDatabaseFile = "./conf/database/anote2databasescript.sql";
		
	public static final String mysqlDatabaseFileVersion2WithouInsert = "./conf/database/anote2_version2_wi_db.sql";
	
	public static final String mysqlDatabaseVersionFile = "./conf/database/anote2_db_version.xml";
	
	public static final String mysqlDatbaseUpdateStartNameFile = "anote2_db_update_";
	
	public static final String mysqlDatbaseUpdateEndNameFile = ".sql";
	
	public static final String mysqlDatbaseUpdateEndNameInfoFile = ".info";
	
	public static final String mysqlDatbaseUpdateFolder = "./conf/database";	
	
	public static final String wikiGeneralLink = "http://darwin.di.uminho.pt/anote2/wiki/index.php/";
	
	public static final String anoteprojectLLink = "http://anote-project.org";
	
	public static final String checkForUpdatesURL = "http://sourceforge.net/projects/anote2/files/";
	
	public static final String saveDocDirectotyDefault = "Docs/";
		
	public static final String rootName = "Anote2";
	
	public static final String publicationManagerName = "publicationmanager";
	
	public static final String publicationManagerQuery = "query";
	
	public static final String resourcesName = "resources";
	
	public static final String resourcesDictionariesName = "dictionaries";
		
	public static final String resourcesLookupTablesName = "lookuptables";
		
	public static final String resourcesRulesResourceName = "rules";
	
	public static final String resourcesOntologiesName = "ontologies";
		
	public static final String corporaName = "corpora";
	
	public static final String corporacorpusName = "corpus";
	
	public static final String corporaProcessName = "process";
	
	public static final String corporaAnnotatedDocument = "annotateddoc";

	public static final String resourcesLexicalWordsSet = "lexicalwordsset";
	
	public final static int superWidth = 1024;
	
	public final static int superHeight = 680;
	
	public final static int generalWidth= 800;
	
	public final static int generalHeight= 600;
	
	public final static int smallWidth = 600;
	
	public final static int smallHeight = 400;

	public static Integer threadsNumber = null;
	
	public final static DecimalFormat decimalformat = new DecimalFormat("0.00");

	public final static String otherConfigurationFile = "conf/othersettings.xml";	
	
	public final static String pluginManagerFile = "conf/pluginmanager.conf";
	
	public static String verbColor = null;
	
	public static String verbColorBackground = null;
	
	public static String tmpDocs = "Docs/tmp/";

	public static Boolean freeFullTextOnly = null;
	
	public static IDatabase database = null;

	public static IProxy proxy = null;

	public static Boolean dictionaryViewSearchForSynonyms = null;

	public static String highlightColor = null;

	public static Boolean usingTitleInAbstract = null;
	
	public static String currentdirectory = null;

	public static IDataAccess dataAcess = null;

	public static String tranformationTextFile = "src/main/resources/tranformationTextFile.prop";

	public static Integer getDoublePrecision() {
		return 2;
	}

}
