package com.silicolife.textmining.core.datastructures.utils;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitedFieldChar extends DocumentFilter {

	private int maxLength;


	public LimitedFieldChar(int maxLength) {
		this.maxLength = maxLength;
	}
	public LimitedFieldChar() {
		this.maxLength = -1;
	}


	public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {

		if (text == null)
			return;
		
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