package com.silicolife.textmining.core.interfaces.core.user;


public interface IUser {

	public long getAuId();

	public String getAuUsername();

	public String getAuFullname();

	public IGroup getAuthGroups();

	public String getAuPassword();
	
	public void setAuPassword(String auPassword);

	public String getAuEmail();

	public Integer getAuPhone();

	public String getAuAddress();

	public String getAuZipCode();

	public String getAuLocation();

}
