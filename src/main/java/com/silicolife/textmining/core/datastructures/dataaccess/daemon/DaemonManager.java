package com.silicolife.textmining.core.datastructures.dataaccess.daemon;

import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;

public class DaemonManager {

	private IDaemon configurations;

	public DaemonManager(IDaemon configurations) {
		this.setDaemonConfigurations(configurations);
	}

	public IDaemon getDaemonConfigurations() {
		return configurations;
	}

	public void setDaemonConfigurations(IDaemon configurations) {
		this.configurations = configurations;
	}

	public String toString() {
		return configurations.toString();
	}
}
