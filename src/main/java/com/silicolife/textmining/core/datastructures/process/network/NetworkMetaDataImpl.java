package com.silicolife.textmining.core.datastructures.process.network;

import java.util.Date;

import com.silicolife.textmining.core.interfaces.process.IE.network.INetworkMetaData;

public class NetworkMetaDataImpl implements INetworkMetaData{
	
	private String type;
	private String description;
	private Date date;
	private String title;
	private String source;
	private String format;
	
	public NetworkMetaDataImpl(String type, String description, Date date,
			String title, String source, String format) {
		super();
		this.type = type;
		this.description = description;
		this.date = date;
		this.title = title;
		this.source = source;
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public String getSource() {
		return source;
	}

	public String getFormat() {
		return format;
	}
}
