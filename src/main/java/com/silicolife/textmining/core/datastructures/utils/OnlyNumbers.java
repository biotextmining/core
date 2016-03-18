package com.silicolife.textmining.core.datastructures.utils;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class OnlyNumbers extends DocumentFilter {

	public static String SOLUTIONS;
	private boolean isInteger;
	private int maxLength;


	public OnlyNumbers(int maxLength,boolean isInteger) {
		this.isInteger = isInteger;
		this.maxLength = maxLength;
		setSolutions();
	}
	public OnlyNumbers() {
		this.isInteger = true;
		this.maxLength = -1;
		setSolutions();
	}

	private void setSolutions() {
		if (isInteger) {
			SOLUTIONS = "0123456789";
		} else {
			SOLUTIONS = "0123456789.";
		}
	}

	public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {

		if (text == null)
			return;

		for (int i = 0; i < text.length(); i++) {
			if (SOLUTIONS.indexOf(String.valueOf(text.charAt(i))) == -1) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		if(!isInteger){
			if (fb.getDocument().getText(0, fb.getDocument().getLength()).indexOf(".") != -1) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		if(maxLength >= 0){
			int size = fb.getDocument().getLength() + text.length();
			if(maxLength < size){
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		super.insertString(fb, offset, text, attr);
	}

	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		super.remove(fb, offset, length);
	}

	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
		if (text == null)
			return;

		for (int i = 0; i < text.length(); i++) {
			if (SOLUTIONS.indexOf(String.valueOf(text.charAt(i))) == -1) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		if(!isInteger){
			if (fb.getDocument().getText(0, fb.getDocument().getLength()).indexOf(".") != -1) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		if(maxLength >= 0){
			int size = fb.getDocument().getLength() + text.length();
			if(maxLength < size){
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		}
		
		super.replace(fb, offset, length, text, attr);//(fb, offset, text, attr);
	}
}