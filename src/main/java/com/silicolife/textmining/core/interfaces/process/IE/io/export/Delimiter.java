/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package com.silicolife.textmining.core.interfaces.process.IE.io.export;

/**
 * 
 * @author pmaia
 * @author Hugo Costa
 *
 */
public enum Delimiter {
	
	
	COMMA{
		public String getValue() {
			return ",";
		}
		
		public String toString() {
			return "Comma ( , )";
		}
	},
	TAB{
		public String getValue() {
			return "\t";
		}
		
		public String toString() {
			return "Tab ( \t )";
		}
	},
	WHITE_SPACE{
		public String getValue() {
			return " ";
		}
		
		public String toString() {
			return "White Space ( )";
		}
	},
	SEMICOLON{
		public String getValue() {
			return ";";
		}
		
		public String toString() {
			return "Semi-colon ( ; )";
		}
	},
	COLON {
		public String getValue() {
			return ":";
		}
		
		public String toString() {
			return "COLON ( : )";
		}
	},
	VERTICAL_BAR {
		public String getValue() {
			return "\\|";
		}
		
		public String toString() {
			return "Vertical Bar ( | )";
		}
	},
	USER{					
		public String getValue(){
			return userDelimiter;
		}
		
		public String toString() {
			return "Other :";
		}
	};

	public void setUserDelimiter(String delimiter) {
		this.userDelimiter = delimiter;
	}
	
	public String getValue(){
		return this.getValue();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	protected String userDelimiter = null;
	protected String name;
	
	public static Delimiter getDelimiterByString(String settingsDefaultValue) {
		if(settingsDefaultValue.equals(Delimiter.COMMA.name()))
		{
			return Delimiter.COMMA;
		}
		else if(settingsDefaultValue.equals(Delimiter.TAB.name()))
		{
			return Delimiter.TAB;
		}
		else if(settingsDefaultValue.equals(Delimiter.WHITE_SPACE.name()))
		{
			return Delimiter.WHITE_SPACE;
		}
		else if(settingsDefaultValue.equals(Delimiter.SEMICOLON.name()))
		{
			return Delimiter.SEMICOLON;
		}
		else if(settingsDefaultValue.equals(Delimiter.COLON.name()))
		{
			return Delimiter.COLON;
		}
		else if(settingsDefaultValue.equals(Delimiter.VERTICAL_BAR.name()))
		{
			return Delimiter.VERTICAL_BAR;
		}
		else
			return null;
	}
	
	

}
