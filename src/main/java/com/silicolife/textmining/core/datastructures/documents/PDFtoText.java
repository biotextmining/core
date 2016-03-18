package com.silicolife.textmining.core.datastructures.documents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.silicolife.textmining.core.datastructures.textprocessing.NormalizationForm;

public class PDFtoText {



	public static String convertPDFDocument(String url) throws FileNotFoundException, IOException {

		PDFTextStripper stripper = new PDFTextStripper();
		PDFParser parser = new PDFParser(new FileInputStream(url));
		parser.parse();
		PDDocument doc = parser.getPDDocument();
		String text = stripper.getText(doc);
		parser.clearResources();
		doc.close();
		return NormalizationForm.removeOffsetProblemSituation(text);
	}


}



	
