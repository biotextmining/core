package com.silicolife.textmining.core.datastructures.resources.lexiacalwords;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.MapResourceElementDeserialize;
import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.lexicalwords.ILexicalWords;

public class LexicalWordsImpl  extends ResourceImpl implements ILexicalWords{
	

	@JsonDeserialize(using = MapResourceElementDeserialize.class)
	private Map<String,IResourceElement> lexicalWordElement;
	private List<Long> insertedTermIDList;
	private boolean cancel = false;
	
	public LexicalWordsImpl()
	{
		super();
	}
	
	public LexicalWordsImpl(long id, String name, String info,boolean active) {
		super(id, name, info,ResourcesTypeEnum.lexicalwords.toString(), active);
		lexicalWordElement = null;
	}
	
	public LexicalWordsImpl(String name, String info,boolean active) {
		super(name, info,ResourcesTypeEnum.lexicalwords.toString(),active);
		lexicalWordElement = null;
	}

	public LexicalWordsImpl(IResource<IResourceElement> resource) {
		this(resource.getId(),resource.getName(),resource.getInfo(),resource.isActive());
	}
	
	public Map<String, IResourceElement> getLexicalWordElement() {
		return lexicalWordElement;
	}

	public List<Long> getInsertedTermIDList() {
		return insertedTermIDList;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setLexicalWordElement(
			Map<String, IResourceElement> lexicalWordElement) {
		this.lexicalWordElement = lexicalWordElement;
	}

	public void setInsertedTermIDList(List<Long> insertedTermIDList) {
		this.insertedTermIDList = insertedTermIDList;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	
	@JsonIgnore
	public synchronized  Map<String, IResourceElement> getLexicalWords() throws ANoteException {
		if(lexicalWordElement==null)
		{
			Map<String,IResourceElement> lexicalWordDatabaseIDMap = new HashMap<String, IResourceElement>();
			List<IResourceElement> elements = this.getResourceElements().getAlphabeticOrder();
			for(IResourceElement elem:elements)
			{
				lexicalWordDatabaseIDMap.put(elem.getTerm(), elem);
			}
			this.lexicalWordElement = lexicalWordDatabaseIDMap;
		}
		return lexicalWordElement;
	}
	

	public boolean exportCSVFile(String csvFile) throws IOException, ANoteException{
		cancel = false;
		File file = new File(csvFile);
		if(!file.exists())
			file.createNewFile();
		PrintWriter pw = new PrintWriter(file);	
		String line = new String();
		int step = 0;
		int total = getLexicalWords().size();
		for(String word : getLexicalWords().keySet())
		{
			line = line + word+"\n";
			if(step % 10 == 0)
			{
				memoryAndProgress(step, total);
			}
			step++;
		}
		pw.write(line);
		pw.close();
		return true;
	}



	public String toString()
	{
		if(getId() < 1)
		{
			return "None";
		}
		String info = LanguageProperties.getLanguageStream("pt.uminho.anote2.general.lexicalwords")+" : " + getName() + "( ID :"+ getId() + ") ";
		if(!getInfo().equals(""))
		{
			info = info + LanguageProperties.getLanguageStream("pt.uminho.anote2.general.notes")+": "+getInfo();
		}
		if(!isActive())
		{
			info = info + " ("+LanguageProperties.getLanguageStream("pt.uminho.anote2.general.inactive")+") ";
		}
		return  info;
	}

	public boolean equals(Object lexicalWordsw)
	{
		if(!(lexicalWordsw instanceof ILexicalWords))
			return false;
		if(this.getId() == ((ILexicalWords) lexicalWordsw).getId())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void cleanMemory() {
		lexicalWordElement = null;		
	}

}
