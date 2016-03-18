package com.silicolife.textmining.core.interfaces.process.IE.manualcuration;

public enum ManualCurationEnum {
	NER
	{
		public String getProcessName()
		{
			return "Manual Curation";
		}
	},
	RE{
		public String getProcessName()
		{
			return "Manual Curation RE";
		}
	};
	
	
	public String getProcessName()
	{
		return this.getProcessName();
	}

}
