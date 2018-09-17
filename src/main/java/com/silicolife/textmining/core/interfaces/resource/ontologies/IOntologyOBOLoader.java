package com.silicolife.textmining.core.interfaces.resource.ontologies;

import java.io.File;
import java.io.IOException;


public interface IOntologyOBOLoader extends IOntologyLoader{
	
	public boolean validateFile(File file) throws IOException;


}
