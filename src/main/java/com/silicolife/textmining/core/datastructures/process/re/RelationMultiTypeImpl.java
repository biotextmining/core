package com.silicolife.textmining.core.datastructures.process.re;

import java.util.Set;

import com.silicolife.textmining.core.datastructures.general.ClassPropertiesManagement;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;

/**
 * Class That represent a multi relation type. Relation type represent witch multi class are present in left and right side of relation
 * 
 * @author Hugo Costa
 *
 */
public class RelationMultiTypeImpl implements  IRelationMultiType{

	private Set<Long> leftClassID;
	private Set<Long> rightClassID;


	public RelationMultiTypeImpl(Set<Long> leftClassID, Set<Long> rightClassID) {
		this.leftClassID = leftClassID;
		this.rightClassID = rightClassID;
	}


	public Set<Long> getRightClassID() {
		return rightClassID;
	}

	public Set<Long> getLeftClassID() {
		return leftClassID;
	}
	
	public int compareTo(Object o) {
		if(this.equals(o))
		{
			return 0;
		}
		return -1;
	}
	
	public boolean equals(Object o)	{
		if(o instanceof IRelationMultiType)
		{
			IRelationMultiType retaionType = (IRelationMultiType) o;
			if(this.getLeftClassID().size() != retaionType.getLeftClassID().size() || this.getRightClassID().size()!=retaionType.getRightClassID().size())
			{
				return false;
			}
			else if(!this.getLeftClassID().containsAll(retaionType.getLeftClassID()))
			{
				return false;
			}
			else if(!this.getRightClassID().containsAll(retaionType.getRightClassID()))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	public String toString()
	{	
		try {
			String leftclasses = new String();
			for(Long classID: leftClassID){
				leftclasses = leftclasses +  ClassPropertiesManagement.getClassGivenClassID(classID).getName() + ",";
			}
			if(leftclasses.endsWith(","))
				leftclasses = leftclasses.substring(0,leftclasses.length()-1);
			String rightclasses = new String();;
			for(Long classID: rightClassID){
				rightclasses = rightclasses +  ClassPropertiesManagement.getClassGivenClassID(classID).getName() + ",";
			}
			if(rightclasses.endsWith(","))
				rightclasses = rightclasses.substring(0,rightclasses.length()-1);
			return leftclasses+"<>"+rightclasses;
		} catch (Exception e) {
		}
		return "?";
	}
}
