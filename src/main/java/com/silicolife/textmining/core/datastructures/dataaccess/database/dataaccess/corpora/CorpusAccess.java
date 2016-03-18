package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.corpora;


public class CorpusAccess {

//	public static ICorpus createCorpus(ICorpus corpus) throws DaemonException, DatabaseLoadDriverException, SQLException {
//
//		PreparedStatement insertCorpus = Configuration.getDatabase().getConnection().prepareStatement(QueriesCorpora.insertCorpus);
//		insertCorpus.setLong(1, corpus.getId());
//		insertCorpus.setNString(2, corpus.getDescription());
//		insertCorpus.execute();
//		insertCorpus.close();
//		PreparedStatement insertCorpusProperties = Configuration.getDatabase().getConnection().prepareStatement(QueriesCorpora.insertCorpusProperties);
//		insertCorpusProperties.setInt(1, corpus.getId());
//		for (String pro : corpus.getProperties().stringPropertyNames()) {
//			String value = corpus.getProperties().getProperty(pro);
//			insertCorpusProperties.setNString(2, pro);
//			insertCorpusProperties.setNString(3, value);
//			insertCorpusProperties.execute();
//		}
//		insertCorpusProperties.close();
//
//		return corpus;
//	}
//
//	public void removeCorpus(ICorpus corpus) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement ps = Configuration.getDatabase().getConnection().prepareStatement(QueriesCorpora.inactiveCorpus);
//		ps.setInt(1, corpus.getId());
//		ps.execute();
//		ps.close();
//	}
	
}
