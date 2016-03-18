package com.silicolife.textmining.core.interfaces.core.annotation.re;


public enum PolarityEnum{
	Negative
	{
		public int databaseValue()
		{
			return 0;
		}
		
		public String toString()
		{
			return "Negative (-)";
		}
	}
	,
	Conditional
	{
		public int databaseValue()
		{
			return 1;
		}
		
		public String toString()
		{
			return "Conditional (?)";
		}
	},
	Positive
	{
		public int databaseValue()
		{
			return 2;
		}
		
		public String toString()
		{
			return "Positive (+)";
		}
	},
	Unknown
	{
		public int databaseValue()
		{
			return 3;
		}
		
		public String toString()
		{
			return "Unknown";
		}
	};
	
	public static PolarityEnum covertIntToPolarityEnum(int number) {
		switch (number) {
		case 2:
			return PolarityEnum.Positive;
		case 0:
			return PolarityEnum.Negative;
		case 1:
			return PolarityEnum.Conditional;
		default:
			return  PolarityEnum.Unknown;
		}
	}

	public int databaseValue() {
		return this.databaseValue();
	}

	public static PolarityEnum convertStringToEnum(String string) {
		if(string.equals(PolarityEnum.Negative.name()))
		{
			return PolarityEnum.Negative;
		}
		else if(string.equals(PolarityEnum.Conditional.name()))
		{
			return PolarityEnum.Conditional;
		}
		else if(string.equals(PolarityEnum.Positive.name()))
		{
			return PolarityEnum.Positive;
		}
		else if(string.equals(PolarityEnum.Unknown.name()))
		{
			return PolarityEnum.Unknown;
		}
		return null;
	}
}
