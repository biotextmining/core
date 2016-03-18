package com.silicolife.textmining.core.datastructures.dataaccess.daemon;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;

public class DaemonArchive {

	private List<IDaemon> daemonArchive;
	private static String separator = "\t";

	public DaemonArchive() {
		daemonArchive = new ArrayList<IDaemon>();
	}

	public DaemonArchive(String daemonsString) {
		daemonArchive = new ArrayList<IDaemon>();
		String[] daemonSplitString = daemonsString.split(separator);
		for (String daemonSpecificSplit : daemonSplitString) {
			addDaemonToArchive(DaemonFactory.createDaemonConfigurations(daemonSpecificSplit));
		}
	}

	public void addDaemonToArchive(IDaemon daemonConfigurations) {
		if (notExistDaemonInArchive(daemonConfigurations))
			this.daemonArchive.add(daemonConfigurations);
	}

	private boolean notExistDaemonInArchive(IDaemon daemonConfigurations) {
		if (daemonConfigurations == null) {
			return false;
		}
		if (daemonArchive.contains(daemonConfigurations)) {
			return false;
		}
		return true;
	}

	public List<IDaemon> getDaemonArchive() {
		return daemonArchive;
	}

	public void removeDaemon(int daemonIndex) {
		if (daemonArchive.size() > daemonIndex)
			daemonArchive.remove(daemonIndex);
	}

	public boolean equals(Object obj) {
		if (obj instanceof List) {
			@SuppressWarnings("rawtypes")
			List<?> objList = (List) obj;
			if (daemonArchive.size() != objList.size())
				return false;
			for (int i = 0; i < objList.size(); i++) {
				Object objInside = objList.get(i);
				if (objInside instanceof IDaemon) {
					IDaemon daemon = (IDaemon) objInside;
					if (!daemon.equals(daemonArchive.get(i))) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (IDaemon daemonConfigurations : daemonArchive) {
			str.append(daemonConfigurations.toString());
			str.append(separator);
		}
		if (str.length() > 0)
			return str.toString().substring(0, str.length() - 1);
		else
			return "";
	}

}
