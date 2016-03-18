package com.silicolife.textmining.core.datastructures.dataaccess.daemon;

import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;

public class DaemonFactory {

	public static IDaemon createDaemonConfigurations(String daemonFieldsString) {
		String[] fieldsDaemon = daemonFieldsString.split("\\|");
		if (fieldsDaemon != null && fieldsDaemon.length == 5) {
			String protocol = fieldsDaemon[1];
			String url = fieldsDaemon[2];
			int port = Integer.parseInt(fieldsDaemon[4]);
			String webpath = fieldsDaemon[3];

			IDaemon daemonConfigurations = new Daemon(protocol, url, port, webpath);
			return daemonConfigurations;
		}
		return null;
	}

	public static IDaemon createDaemonConfigurations(String protocol, String url, int port, String rootPath) {
		IDaemon daemonConfigurations = new Daemon(protocol, url, port, rootPath);
		return daemonConfigurations;
	}
}
