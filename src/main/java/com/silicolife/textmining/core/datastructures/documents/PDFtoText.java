package com.silicolife.textmining.core.datastructures.documents;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

import com.silicolife.textmining.core.datastructures.textprocessing.NormalizationForm;
import com.silicolife.textmining.core.datastructures.textprocessing.TermSeparator;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageIOHelper;

public class PDFtoText {



	public static String convertPDFDocument(String url) throws FileNotFoundException, IOException {

		PDFTextStripper stripper = new PDFTextStripper();
		PDFParser parser = new PDFParser(new FileInputStream(url));
		parser.parse();
		PDDocument doc = parser.getPDDocument();
		String text = stripper.getText(doc);
		parser.clearResources();
		doc.close();
		if(text==null || text.isEmpty() || verifyValidOCRlenght(text)==false)
		{
			try {
				text =  fileOCR(url);
			} catch (TesseractException e) {
				text = new String();
			}
		}
		if(text==null || text.isEmpty() || verifyValidOCRlenght(text)==false)
		{
			try {
				text =  convertEncryptedPDFDocument(url);
			} catch (TesseractException e) {
				text = new String();
			}
		}
		String newText = TermSeparator.termSeparator(text);
		return NormalizationForm.removeOffsetProblemSituation(newText);
	}
	
	private static String convertEncryptedPDFDocument(String url) throws IOException, TesseractException{
		int imageDPIValue = 300;
		PDDocument document = PDDocument.loadNonSeq(new File(url), null);
		@SuppressWarnings("unchecked")
		List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
		List<BufferedImage> imagesPages = new ArrayList<>();
		for (PDPage pdPage : pdPages){ 
			imagesPages.add(pdPage.convertToImage(BufferedImage.TYPE_INT_RGB, imageDPIValue));
		}
		document.close();
		Tesseract tessaract = TessaractManager.getInstance().getTessaract();
		List<IIOImage> pagesToOCR = new ArrayList<>();
		for(BufferedImage image :imagesPages){
			List<IIOImage> content = ImageIOHelper.getIIOImageList(image);
			pagesToOCR.addAll(content);
		}
		String originalText = tessaract.doOCR(pagesToOCR, null);
		return originalText;
	}
	
	/**
	 * Apply OCR algorithm to a PDF file given an pathway to the file
	 * 
	 */
	public static String fileOCR (String pdfPath) throws IOException, TesseractException{
		File imageFile = new File(pdfPath);
		List<IIOImage> imageList = ImageIOHelper.getIIOImageList(imageFile);
		ITesseract instance = TessaractManager.getInstance().getTessaract();
		String result = instance.doOCR(imageList, null);
		return result;
	}
	
	
	private static boolean verifyValidOCRlenght(String text){
		String newText = text.replaceAll("\\s", "");
		if (newText.length()<200){
			return false;
		}
		return true;
	}


}



	
