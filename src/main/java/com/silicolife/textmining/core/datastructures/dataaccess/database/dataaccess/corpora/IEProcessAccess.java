package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.corpora;


public class IEProcessAccess {

//	public static IProcessType insertProcessType(IProcessType processType) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement prep = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.existProcessType);
//		prep.setNString(1, processType.getType());
//		ResultSet rs = prep.executeQuery();
//
//		if (rs.next()) {
//			int id = rs.getInt(1);
//			processType.setId(id);
//		} else {
//			PreparedStatement insType = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.insertProcessType);
//			insType.setInt(1, processType.getId());
//			insType.setNString(2, processType.getType());
//			insType.execute();
//			insType.close();
//		}
//		rs.close();
//		prep.close();
//
//		return processType;
//	}
//
//	public static IIEProcess insertProcess(IIEProcess process, IProcessType processType) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement prep = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.insertProcess);
//		prep.setLong(1, process.getID());
//		prep.setLong(2, processType.getId());
//		prep.setNString(3, process.getName());
//		prep.execute();
//		insertProcessProperties(process);
//		prep.close();
//		return process;
//	}
//
//	public static void insertProcessProperties(IIEProcess process) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement insertP;
//		insertP = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.insertProcessProperties);
//		for (String key : process.getProperties().stringPropertyNames()) {
//			String value = process.getProperties().get(key).toString();
//			insertP.setInt(1, process.getID());
//			insertP.setNString(2, key);
//			insertP.setNString(3, value);
//			insertP.execute();
//		}
//		insertP.close();
//	}
//
//	public static void updateProcess(IIEProcess process) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		PreparedStatement ps = Configuration.getDatabase().getConnection().prepareStatement(QueriesProcess.updateProcessName);
//		ps.setInt(2, process.getID());
//		ps.setString(1, process.getName());
//		ps.execute();
//		ps.close();
//	}
//
//	public static Set<Long> getAllProcessEntityAnnotatedClassesId(IIEProcess process) throws DaemonException, SQLException, DatabaseLoadDriverException {
//
//		Set<Long> result = new HashSet<Long>();
//		PreparedStatement ps = Configuration.getDatabase().getConnection().prepareStatement(QueriesIEProcess.getIEProcessClasses);
//		ps.setInt(1, process.getID());
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			result.add(rs.getLong(1));
//		}
//		rs.close();
//		ps.close();
//		return result;
//	}

}
