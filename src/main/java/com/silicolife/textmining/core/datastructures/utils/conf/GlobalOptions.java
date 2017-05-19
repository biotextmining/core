package com.silicolife.textmining.core.datastructures.utils.conf;

import java.text.DecimalFormat;

public class GlobalOptions {
		
	// Configurations
	public static final String hibernateConfigurationFile = "conf/hibernate.cfg.xml";
	public static final String language = "en";
	public static final String languageFilesDirectory = "conf/language/";
	public static final String languagePathFile = languageFilesDirectory + "/language_"+language+".lang";
	public static final String alternativelanguagePathFile = "src/main/resources" + "/language_"+language+".lang";
	public static final String h2DatbaseUpdateStartNameFile = "h2_anote2_db_update_";
	public static final String mysqlDatbaseUpdateStartNameFile = "anote2_db_update_";
	public static final String mysqlDatbaseUpdateEndNameFile = ".sql";
	public static final String mysqlDatbaseUpdateEndNameInfoFile = ".info";
	public static String defaulQuerytName = "[KEYWORDS]:[ORGANISM]:[Date]";
	public static int searchYearStarting = 1900;
	public final static DecimalFormat decimalformat = new DecimalFormat("0.00");
	public static Integer threadsNumber = null;
	public final static String otherConfigurationFile = "conf/othersettings.xml";	
	public final static String pluginManagerFile = "conf/pluginmanager.conf";
	public static String verbColor = null;
	public static String verbColorBackground = null;
	public static Boolean freeFullTextOnly = null;
	public static Boolean dictionaryViewSearchForSynonyms = null;
	public static String highlightColor = null;
	public static Boolean usingTitleInAbstract = null;
	public static String tranformationTextFile = "src/main/resources/tranformationTextFile.prop";
	public static String LUCENEINDEXBASEDIRECTORY = "conf/database/luceneindex";

	public static Integer getDoublePrecision() {
		return 2;
	}

}
