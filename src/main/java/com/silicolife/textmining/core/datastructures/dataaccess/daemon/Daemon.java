package com.silicolife.textmining.core.datastructures.dataaccess.daemon;

import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;

public class Daemon implements IDaemon {

	private String protocol;
	private String url;
	private String webPath;
	private int port;
//	private IUser user;
	public final String daemonSplitFields = "|";

	public Daemon(String protocol, String url, int port, String webPath) {
		this.protocol = protocol;
		this.url = url;
		this.port = port;
		this.webPath = webPath;
	}

	public Daemon() {
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getWebPath() {
		return webPath;
	}

//	@Override
//	public IUser getUser() {
//		return user;
//	}

	@Override
	public String toString() {

		StringBuffer str = new StringBuffer();
		str.append("daemon" + daemonSplitFields);
		str.append(protocol + daemonSplitFields);
		str.append(url + daemonSplitFields);
		str.append(webPath + daemonSplitFields);
		str.append(port + daemonSplitFields);
	//	str.append(user.getAuUsername() + daemonSplitFields);
	//	str.append(user.getAuPassword());
		return str.toString();
	}
}
