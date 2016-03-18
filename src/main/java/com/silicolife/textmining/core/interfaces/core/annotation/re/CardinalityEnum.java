package com.silicolife.textmining.core.interfaces.core.annotation.re;

public enum CardinalityEnum {
	OneToOne{
		public int databaseValue()
		{
			return 0;
		}
		
		public String toString()
		{
			return "One-One";
		}
	},
	OneToMany{
		public int databaseValue()
		{
			return 1;
		}
		
		public String toString()
		{
			return "One-Many";
		}
	},
	ZeroToMany{
		public int databaseValue()
		{
			return 2;
		}
		
		public String toString()
		{
			return "Zero-Many";
		}
	},
	ManyToZero{
		public int databaseValue()
		{
			return 3;
		}
		
		public String toString()
		{
			return "Many-Zero";
		}
	},
	ManyToOne{
		public int databaseValue()
		{
			return 4;
		}
		
		public String toString()
		{
			return "Many-One";
		}
	},ManyToMany{
		public int databaseValue()
		{
			return 5;
		}
		
		public String toString()
		{
			return "Many-Many";
		}
	},Other{
		public int databaseValue()
		{
			return 6;
		}
		
		public String toString()
		{
			return "Other";
		}
	};
	
	
	public static CardinalityEnum covertIntToCardinalityEnum(int number) {
		switch (number) {
		case 0:
			return CardinalityEnum.OneToOne;
		case 1:
			return CardinalityEnum.OneToMany;
		case 2:
			return CardinalityEnum.ZeroToMany;
		case 3:
			return CardinalityEnum.ManyToZero;
		case 4:
			return CardinalityEnum.ManyToOne;
		case 5:
			return CardinalityEnum.ManyToMany;
		default:
			return  CardinalityEnum.Other;
		}
	}

	public String toString()
	{
		return this.toString();
	}
	
	public int databaseValue()
	{
		return this.databaseValue();
	}

	public static CardinalityEnum convertStringToEnum(String string) {
		if(string.equals(CardinalityEnum.OneToOne.toString()))
		{
			return CardinalityEnum.OneToOne;
		}
		else if(string.equals(CardinalityEnum.OneToMany.toString()))
		{
			return CardinalityEnum.OneToMany;
		}
		else if(string.equals(CardinalityEnum.ZeroToMany.toString()))
		{
			return CardinalityEnum.ZeroToMany;
		}
		else if(string.equals(CardinalityEnum.ManyToZero.toString()))
		{
			return CardinalityEnum.ManyToZero;
		}
		else if(string.equals(CardinalityEnum.ManyToOne.toString()))
		{
			return CardinalityEnum.ManyToOne;
		}
		else if(string.equals(CardinalityEnum.ManyToMany.toString()))
		{
			return CardinalityEnum.ManyToMany;
		}
		return null;
	}

}
