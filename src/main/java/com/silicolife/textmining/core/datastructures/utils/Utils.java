package com.silicolife.textmining.core.datastructures.utils;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import com.silicolife.textmining.core.interfaces.core.document.IPublication;

/**
 * Class that contains some util methods 
 * 
 *
 */
public class Utils {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT2 = "yyyy-MM-dd";

	public static final SimpleDateFormat SimpleDataFormat= new SimpleDateFormat(DATE_FORMAT);
	public static final SimpleDateFormat SimpleDataFormat2= new SimpleDateFormat(DATE_FORMAT2);

	/**Returns the current time of the system */
	public static String currentTime() {
	    
	  	Calendar cal = Calendar.getInstance();
	    return SimpleDataFormat.format(cal.getTime());
	}
	
	/**Returns the current time of the system */
	public static String currentTimeSimple() {
	    
	  	Calendar cal = Calendar.getInstance();
	    return SimpleDataFormat2.format(cal.getTime());
	}
	
	public static String formatDate(String date){

		int i = date.indexOf(";");
		
		String[] tokens = date.substring(0,i).split("[ -/]");
		
		if(tokens.length==1)
			return tokens[0];
		
		if(tokens.length==2)
			return tokens[0] + "-" + tokens[1];
		
		return tokens[0] + "-" + tokens[1] + "-" + tokens[2];

	}
	
	public static  boolean isIntNumber(String num){
	    try{
	        Integer.parseInt(num);
	    } catch(NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static  boolean isLongNumber(String num){
	    try{
	        Long.parseLong(num);
	    } catch(NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	/** Returns the current time of the system */
	public static int currentYear() {
	     
	    Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);
	    return year;

	}
	
	/** Returns the current time of the system */
	public static int currentMonth() {
	     
	    Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH);
	    return month;

	}
	
	/** Returns the current time of the system */
	public static int currentDay() {
	     
	    Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    return day;

	}
	
	/** Swap element of a Hash*/
	public static Map<? extends Object, ? extends Object> swapHashElements(Map<? extends Object,? extends Object> hash)
	{
		Map<Object,Object> result = new HashMap<Object,Object>();
		for(Object key:hash.keySet())
		{
			result.put(hash.get(key),key);
		}
		return result;
	}
	
	public static String treatSentenceForXMLProblems(String sentence)
	{
		sentence=sentence.replaceAll("&"," ");	
		sentence=sentence.replaceAll("<"," ");
		sentence=sentence.replaceAll(">"," ");
		sentence=sentence.replaceAll("'"," ");
		return sentence;
	}
	

	public static String  randomAlphaNumColor(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = length; i > 0; i -= 12) {
			int n = min(12, abs(i));
			sb.append(leftPad(Long.toString(round(random() * pow(16, n)), 16), n, '0'));
		}
		return "#"+sb.toString();
	}
	
	
	public boolean isInteger(String input )  
	{  
		try  
		{  
			Integer.parseInt( input );  
			return true;  
		}  
		catch( Exception e)  
		{  
			return false;  
		}  
	}
	
	public static String convertTimeToString(long time)
	{
		String hours;
		String minutes;
		String seconds;
		int hoursTime = (int) (time / (1000*3600));
		if(hoursTime < 10)
			hours = String.valueOf("0"+hoursTime);
		else
			hours = String.valueOf(hoursTime);
		int minutesTime = (int) ((time /1000/60) % 60);
		if(minutesTime < 10)
			minutes = String.valueOf("0"+minutesTime);
		else
			minutes = String.valueOf(minutesTime);
		int secondsTime = (int) ((time /1000) % 60);
		if(secondsTime < 10 )
			seconds = String.valueOf("0"+secondsTime);
		else
			seconds = String.valueOf(secondsTime);
		return hours+":"+minutes+":"+seconds;
	}
	
	public static Image getScaledImage(Image srcImg, final int w, final int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    Color color = Color.GRAY;
		g2.setBackground(color);
	    g2.dispose();
	    return resizedImg;
	}

	public static String get64Base(String autentication) {
		if(autentication==null)
			return null;
		byte[] encoded = Base64.encodeBase64(autentication.getBytes()); 
		return new String(encoded);
	}
	
	public static boolean isMacOS()
	{
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf( "mac" ) >= 0) {
			return true;
		}
		return false;
	}
	
	public static Set<String> getDocummentClassification(IPublication publicaiton)
	{
		Set<String> out = new HashSet<>();
		String  notes = publicaiton.getNotes();
		if(notes !=null)
		{
			String notesChange;
			if(notes.startsWith("Classification IPCR"))
			{
				notesChange = notes.replaceAll("Classification IPCR : ", "");
				getClassifications(out, notesChange);
			}
			else if(notes.contains("Owners") && notes.contains("Classification IPCR"))
			{
				notesChange = notes.replaceAll(".*Classification IPCR : ", "");
				getClassifications(out, notesChange);
			}
		}
		return out;
	}

	private static void getClassifications(Set<String> out, String notesChange) {
		String[] classifications = notesChange.split(",");
		for(String classification:classifications)
		{
			String classificationSRT = classification.trim();
			if(!classificationSRT.isEmpty() && classificationSRT.length() > 2)
			{
				String classification3letter = classificationSRT.substring(0,3);
				out.add(classification3letter);
			}
		}
	}
	
	private static void getOwnersSRT(Set<String> out, String notesChange) {
		String[] classifications = notesChange.split(",");
		for(String classification:classifications)
		{
			String classificationSRT = classification.trim();
			if(!classificationSRT.isEmpty())
			{
				out.add(classificationSRT);
			}
		}
	}

	public static Set<String> getDocummentOwners(IPublication publication) {
		Set<String> out = new HashSet<>();
		String  notes = publication.getNotes();
		if(notes !=null)
		{
			if(notes.contains("Owners") &&  !notes.contains("Classification"))
			{
				getOwnersSRT(out, notes);
			}
			else if(notes.contains("Owners"))
			{
				String notesChange = notes.replaceAll("Classification.*", "");
				getOwnersSRT(out, notesChange);
			}
		}
		return out;
	}
	
}
