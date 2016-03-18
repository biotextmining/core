package com.silicolife.textmining.core.datastructures.report;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.report.IReport;

public class ReportImpl implements IReport{
	
	private String title;
	private Properties properties;
	private long time;
	private boolean finishing = true;
	private String notes;
	
	
	public ReportImpl(String title)
	{
		this.title=title;
		this.properties = new Properties();
		this.time = 0;
	}
	
	public ReportImpl(String title, Properties properties)
	{
		this.title=title;
		this.properties = properties;
		this.time = 0;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isFinishing() {
		return finishing;
	}

	public void setFinishing(boolean finishing) {
		this.finishing = finishing;
	}

	public String getTitle() {
		return title;
	}

	public Properties getProperties() {
		return properties;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time=time;
	}

	public void setcancel() {
		this.finishing = false;
	}

	@Override
	public void setTitle(String newTitle) {
		this.title=newTitle;
	}

}
