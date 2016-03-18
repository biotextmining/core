package com.silicolife.textmining.core.datastructures.resources.lookuptable;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.lookuptables.ILookupTable;

public class LookupTableImpl extends ResourceImpl implements ILookupTable{

	public LookupTableImpl(long id, String name, String info,boolean active) {
		super(id, name, info,ResourcesTypeEnum.lookuptable.toString(),active);
	}
	
	public LookupTableImpl(String name, String info,boolean active) {
		super(name, info,ResourcesTypeEnum.lookuptable.toString(),active);
	}

	
	
	public LookupTableImpl() {
		super();
	}

	public int compareTo(ILookupTable dic)
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
	
	public boolean equals(Object ruleset)
	{
		if(!(ruleset instanceof ILookupTable))
			return false;
		if(this.getId() == ((ILookupTable) ruleset).getId())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		if(getId() < 1)
		{
			return "None";
		}
		else
		{
			String info = new String();
			info =LanguageProperties.getLanguageStream("pt.uminho.anote2.general.lookuptable")+" : " + getName() + " (ID :"+ getId() + " ) ";
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
}
