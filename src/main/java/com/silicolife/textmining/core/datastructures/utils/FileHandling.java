package com.silicolife.textmining.core.datastructures.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * This class contain method for manager directory and files
 * 
 * 
 */
public class FileHandling {
	
	public static boolean createDirectory(String dir){
		
		return (new File(dir)).mkdir();
	
	}
	
	public static boolean existDirectory(String dir){
	
		return (new File(dir)).exists();
		
	}
	
	/**
	 * Method that remove a directory
	 * 
	 * @param dir - Directory File
	 * @return true -- If process completes
	 * 		   false -- otherwise
	 */
	public static boolean removeDirectory(File dir){
		//If the directory is not empty, it is necessary to first recursively delete all files and subdirectories in the directory. Here is a method that will delete a non-empty directory.
		
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for(String child: children)
			{
				boolean success = removeDirectory(new File(dir, child));
				if (!success) {
					// Deletion failed
					return false;					
				}
			}
			return true;
		}
			
		// The directory is now empty so delete it
		return removeEmptyDirectory(dir);
	
	}
	
	public static int getFileLines(File file) throws IOException
	{
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		fr.close();
		return lines;
	}
	
	public static Set<String> getFileLinesTExt(File file) throws IOException{
		Set<String> lines = new HashSet<String>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);			
		String line = br.readLine();
		while(line != null)
		{
			lines.add(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return lines;
	}
	
	private static boolean removeEmptyDirectory(File dir){		
		return dir.delete();
	}
	
	/**
	 * Method that copy a file
	 * 
	 * @param in - File input
	 * @param out - file output
	 * @return true -- If process completes
	 * 		   false -- otherwise
	 */
	public static boolean copyFile(File in, File out){
		try {
			FileReader fr = new FileReader(in);
			FileWriter fw = new FileWriter(out);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = br.readLine();
			while(line != null)
			{
				bw.write(line + "\n");
				line = br.readLine();
			}
			
			br.close();
			bw.close();
			fr.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Method that copy a file in Binary
	 * 
	 * @param in - File input
	 * @param out - file output
	 * @return true -- If process completes
	 * 		   false -- otherwise
	 */
	public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
	
	public static boolean appendToFile(String text, File in, File out){
		try {
			FileReader fr = new FileReader(in);
			FileWriter fw = new FileWriter(out);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = text;
			while(line != null)
			{
				bw.write(line + "\n");
				line = br.readLine();
			}
			
			br.close();
			bw.close();
			fr.close();
			fw.close();
 		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 *  Method that return a list with names of the files is a given directory
	 *  
	 *  @param directory - Directory
	 *  @return list with names of the files on directory
	 */
	public static ArrayList<String> listFileNames(File directory){
		ArrayList<String> fileList = new ArrayList<String>();
		
		if(!directory.isDirectory())
			return fileList;
		
		for(File file: directory.listFiles())
			fileList.add(file.getName());
		
		return fileList;
	}
	
	public static void writeInformationOnFile(File fileInput,String theText) throws IOException 
	{
		FileWriter fw = new FileWriter(fileInput);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(theText);
		bw.close();
		fw.close();
	}

	public static String fileToString(File file) throws FileNotFoundException {
		String result = null;
        DataInputStream in = null;

        try {
            byte[] buffer = new byte[(int) file.length()];
            in = new DataInputStream(new FileInputStream(file));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { /* ignore it */
            }
        }
        return result;
	} 
	
	
	/**
	 * MEthod that reads a file and return a set of lines text
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> getFileLinesContent(File filePath) throws FileNotFoundException, IOException {
		List<String> files = new ArrayList<String>();
		FileInputStream fstream = new FileInputStream(filePath);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
			
			files.add(strLine);
		}
		br.close();
		in.close();
		fstream.close();
		return files;
	}
	
	/**
	 * MEthod that reads a file and return a set of lines text
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileFirstLine(File filePath) throws FileNotFoundException, IOException {
		FileInputStream fstream = new FileInputStream(filePath);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
			br.close();
			in.close();
			fstream.close();
			return strLine;
		}
		br.close();
		in.close();
		fstream.close();
		return null;
	}
	
	public static String getFileContent(File filePath) throws FileNotFoundException, IOException
	{
		List<String> lines = getFileLinesContent(filePath);
		String content = new String();
		for(String line:lines)
		{
			content = content + line;
		}
		return content;
	}
	
	
	
	
}
