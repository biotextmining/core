package com.silicolife.textmining.core.interfaces.core.report.processes.ir;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.report.IReport;


public interface IIRCrawlingProcessReport extends IReport{
	public int getDocumentsRetrieval();
	public Set<IPublication> getListPublicationsDownloaded();
	public Set<IPublication> getListPublicationsNotDownloaded();
	public Set<IPublication> getListPublicationRetrictedDownloaded();
	public Set<IPublication> getListPublicationsAlreadyDownloaded();
	public void addFileDownloaded(IPublication pub);
	public void addFileNotDownloaded(IPublication pub);
	public void addFileRestrictedDownloaded(IPublication pub);
	public void addFileAlreadyDownloaded(IPublication pub);
}
