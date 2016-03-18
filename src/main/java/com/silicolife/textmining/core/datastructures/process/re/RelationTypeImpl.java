package com.silicolife.textmining.core.datastructures.process.re;

import com.silicolife.textmining.core.datastructures.general.ClassPropertiesManagement;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;

/**
 * Class That represent a relation type. Relation type represnt witch class are present in left and right side of relation
 * 
 * @author Hugo Costa
 *
 */
public class RelationTypeImpl implements  IRelationsType{

	private long leftClassID;
	private long rightClassID;


	public RelationTypeImpl(long leftClassID, long rightClassID) {
		super();
		this.leftClassID = leftClassID;
		this.rightClassID = rightClassID;
	}

	public Long getRightClassID() {
		return rightClassID;
	}

	public Long getLeftClassID() {
		return leftClassID;
	}
	
	public int compareTo(IRelationsType o) {
		if(this.leftClassID==o.getLeftClassID())
		{
			return (int) (this.rightClassID-o.getRightClassID());
		}
		return (int) (this.leftClassID-o.getLeftClassID());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (leftClassID ^ (leftClassID >>> 32));
		result = prime * result + (int) (rightClassID ^ (rightClassID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelationTypeImpl other = (RelationTypeImpl) obj;
		if (leftClassID != other.leftClassID)
			return false;
		if (rightClassID != other.rightClassID)
			return false;
		return true;
	}

	public String toString()
	{
		try {
			return ClassPropertiesManagement.getClassGivenClassID(leftClassID).getName()+"--"+ClassPropertiesManagement.getClassGivenClassID(rightClassID).getName();
		} catch (ANoteException e) {
			e.printStackTrace();
		}
		return "?";
	}
	
}
