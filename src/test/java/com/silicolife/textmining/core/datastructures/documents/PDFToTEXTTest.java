package com.silicolife.textmining.core.datastructures.documents;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class PDFToTEXTTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		String url = "src/test/resources/pdf/2780325519275702332.pdf";
		PDFtoText.convertPDFDocument(url);
	}

}
