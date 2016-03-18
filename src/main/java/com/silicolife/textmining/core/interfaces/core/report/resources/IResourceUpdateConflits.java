package com.silicolife.textmining.core.interfaces.core.report.resources;


public interface IResourceUpdateConflits {
	public ResourceUpdateConflitsType getConflitType();
	public String getOriginalTerm();
	public String getConflitTerm();
	
	public boolean isSolve();
	public void setSolve(boolean newStatus);
}
