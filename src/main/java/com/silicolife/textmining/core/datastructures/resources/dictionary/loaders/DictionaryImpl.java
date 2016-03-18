package com.silicolife.textmining.core.datastructures.resources.dictionary.loaders;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;

/**
 * 
 * @author Hugo Costa
 *
 */
public class DictionaryImpl extends ResourceImpl implements IDictionary{

	
	public DictionaryImpl(long id,String name,String info,boolean active)
	{
		super(id,name,info,ResourcesTypeEnum.dictionary.toString(),active);
	}
	
	public DictionaryImpl(String name,String info,boolean active)
	{
		super(name,info,ResourcesTypeEnum.dictionary.toString(),active);
	}
	
	public DictionaryImpl(IResource<IResourceElement> resource) {
		this(resource.getId(),resource.getName(),resource.getInfo(),resource.isActive());
	}
	
	public DictionaryImpl() {
		super();
	}

	


	public int compareTo(IDictionary dic)
	{
		if(this.getId()==dic.getId())
		{
			return 0;
		}
		else if(this.getId()<=dic.getId())
		{
			return -1;
		}
		return 1;
	}
	
	public String toString()
	{
		String info = new String();
		info =LanguageProperties.getLanguageStream("pt.uminho.anote2.general.dictionary")+" : " + getName() + " (ID :"+ getId() + " ) ";
		if(!getInfo().equals(""))
		{
			info = info + LanguageProperties.getLanguageStream("pt.uminho.anote2.general.notes")+": "+getInfo();
		}
		if(!isActive())
		{
			info = info + " ("+LanguageProperties.getLanguageStream("pt.uminho.anote2.general.inactive")+") ";
		}
		return info;
	}
}
