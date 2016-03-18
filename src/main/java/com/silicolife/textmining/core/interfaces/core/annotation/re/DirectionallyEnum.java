package com.silicolife.textmining.core.interfaces.core.annotation.re;

public enum DirectionallyEnum {
	LeftToRight
	{
		
		public int databaseValue()
		{
			return 0;
		}
		
		public String toString()
		{
			return "LeftToRight";
		}
		
	},
	RightToLeft
	{
		
		public int databaseValue()
		{
			return 1;
		}
		
		public String toString()
		{
			return "RightToLeft";
		}
	},
	Both
	{
		public int databaseValue()
		{
			return 2;
		}
		
		public String toString()
		{
			return "Both";
		}
	},
	Unknown{
		
		public int databaseValue()
		{
			return 3;
		}
		
		public String toString()
		{
			return "Unknown";
		}
	};
	
	public String toString()
	{
		return this.toString();
	}
	
	public int databaseValue()
	{
		return this.databaseValue();
	}
	
	public static DirectionallyEnum covertIntToDirectionallyEnum(int number)
	{
		switch (number) {
		case 0:
			return DirectionallyEnum.LeftToRight;
		case 1:
			return DirectionallyEnum.RightToLeft;
		case 2:
			return DirectionallyEnum.Both;
		default:
			return  DirectionallyEnum.Unknown;
		}
	}

	public static DirectionallyEnum convertStringToEnum(String property) {
		if(property.equals(DirectionallyEnum.LeftToRight.name()))
		{
			return DirectionallyEnum.LeftToRight;
		}
		else if(property.equals(DirectionallyEnum.RightToLeft.name()))
		{
			return DirectionallyEnum.RightToLeft;
		}
		else if(property.equals(DirectionallyEnum.Both.name()))
		{
			return DirectionallyEnum.Both;
		}
		else if(property.equals(DirectionallyEnum.Unknown.name()))
		{
			return DirectionallyEnum.Unknown;
		}
		return null;
	}
	
}
