package com.silicolife.textmining.core.datastructures.documents;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import com.silicolife.textmining.core.datastructures.textprocessing.NormalizationForm;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageIOHelper;

public class PDFtoText {



	public static String convertPDFDocument(String url) throws IOException {
		String text = null;
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			PDDocument document = PDDocument.load(new File(url));
			text = stripper.getText(document);
			document.close();
		} catch (IOException e) {
		}
		if(text!=null)
			text = NormalizationForm.removeOffsetProblemSituation(text);
		if(text==null || text.isEmpty() || !verifyValidOCRlenght(text))
		{
			try {
				text =  fileOCR(url);
			} catch (TesseractException | IOException e) {
				text = new String();
			}
		}
		if(text!=null)
			text = NormalizationForm.removeOffsetProblemSituation(text);
		if(text==null || text.isEmpty() || !verifyValidOCRlenght(text))
		{
			try {
				text =  convertEncryptedPDFDocument(url);
			} catch (TesseractException | IOException e) {
				text = new String();
			}
		}
		return NormalizationForm.removeOffsetProblemSituation(text);
	}

	private static String convertEncryptedPDFDocument(String url) throws IOException, TesseractException{
		int imageDPIValue = 300;
		PDDocument document = PDDocument.load(new File(url));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		List<BufferedImage> imagesPages = new ArrayList<>();
		int pageCounter = 0;
		for (@SuppressWarnings("unused") PDPage pdPage : document.getPages()){
			imagesPages.add(pdfRenderer.renderImageWithDPI(pageCounter, imageDPIValue, ImageType.RGB));
			pageCounter++;
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
		if(!isValidText(text))
			return false;
		String newText = text.replaceAll("\\s", "");
		if (newText.length()<200){
			return false;
		}
		return true;
	}

	public static boolean isValidText(String text)
	{
		return text.contains("is") || text.contains("are") || text.contains("the");
	}
	

}




