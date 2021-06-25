/************************************************************************************************************************************/
/** @file		FileSys.java
 * 	@brief		WorkByDay file descriptor & utility
 * 	@details	API for filesystem access & help
 *
 * 	@section	Opens
 * 		• none listed
 * 		
 * 	@section	Legal Disclaimer
 * 		2021© Justin Reina, All rights reserved. All contents of this source file and/or any other related source  
 *		files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.file;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class FileSys {

	/********************************************************************************************************************************/
	/**	@fcn		public String[] getAllDirs(String rootPath)
	 *  @brief		Get Directory Listing under rootPath
	 *  @details	x
	 *
	 *  @param		[in] (String) root - root directory
	 *  
	 *  @return 	(String[]) all directories under rootPath, recursively
	 *  
	 *  @open		File input?
	 *  
	 *  @assum 		rootPath is found
	 */
	/********************************************************************************************************************************/
	public static String[] getAllDirs(String rootPath) {
		
		//Locals
		LinkedList<String> vals;
		File root;
		
		
		//Init
		vals = new LinkedList<String>();
		root = new File(rootPath);
		
		//Search
		vals = getSubDirs(root, true);

		//Convert to result
		String[] arr = new String[vals.size()];
		arr =  vals.toArray(arr);
		
		return arr;
	}


	/********************************************************************************************************************************/
	/**	@fcn		public void getSubDirs(File path)
	 *  @brief		list all sub-directories under a specified path
	 *  @details	x
	 *
	 *  @param		[in] (File) path - root directory to search for subdirs
	 *  @param		[in] (boolean) recurse - look at subdirs within subdirs
	 *  
	 *  @return 	(LinkedList<String>) subdir listing
	 */
	/********************************************************************************************************************************/	
	public static LinkedList<String> getSubDirs(File path, boolean recurse) {

		//Locals
		LinkedList<String> subdirs;											/* subdirectory listing for path						*/
		LinkedList<String> allSubDirs;										/* response value										*/
		
		
		//Init
		subdirs = new LinkedList<>();
		allSubDirs = new LinkedList<>();


		//**************************************************************************************************************************//
		//												 IMMEDIATE SUB DIR LISTING												    //
		//**************************************************************************************************************************//
		//Get immediate sub-directories
    	File files[] = path.listFiles();
        
        //Safety
        if(files == null) {
        	return subdirs;												/* return on invalid content							*/
        }		

        //Extract directories
        for(File file : files) {
        	if(file.isDirectory()) {
        		subdirs.add(file.getAbsolutePath());
        	}
        }
        
        //Append to result
        allSubDirs.addAll(subdirs);
        
        
		//**************************************************************************************************************************//
		//												 RECURSE SUB DIR LISTING												    //
		//**************************************************************************************************************************//
        if(recurse) {
	        for(String subdir : subdirs) {
	        	
	        	//Get this directories
	        	LinkedList<String> newSubDirs = getSubDirs(new File(subdir), true);
	        	
	        	//Append
	        	allSubDirs.addAll(newSubDirs);        	
	        }
        }
		
		return allSubDirs; 													/* all sub dirs 										*/
	}
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void test()
	 *  @brief		Create a new dir at root of D:\, populate & confirm
	 *  @details	x
	 *  
	 *  
	 *  @section 	Test Contents
	 *  	D:\fsTest\a\empty.txt
	 *  	D:\fsTest\b\empty.txt
	 *  	D:\fsTest\c\empty.txt
	 *  	D:\fsTest\d\empty.txt
	 *  	D:\fsTest\a\a\empty.txt
	 *  	D:\fsTest\a\b\empty.txt
	 *  	D:\fsTest\a\c\empty.txt
	 *  	D:\fsTest\a\d\empty.txt
	 *  	D:\fsTest\a\a\a\empty.txt
	 */
	/********************************************************************************************************************************/	
	public static void test() {

		//Locals
		String result;
		File loc;
		File d[] = new File[100];
		
		
		loc = new File("D:\\");
		
		//Create a dir
		d[0] = Dir.create(loc, "fsTest");									/* root													*/
		
		d[1] = Dir.create(d[0], "a");										/* first layer											*/
		d[2] = Dir.create(d[0], "b");
		d[3] = Dir.create(d[0], "c");
		d[4] = Dir.create(d[0], "d");

		d[5] = Dir.create(d[1], "a");										/* second layer 										*/
		d[6] = Dir.create(d[1], "b");
		d[7] = Dir.create(d[1], "c");
		d[8] = Dir.create(d[1], "d");

		d[9] = Dir.create(d[5], "a");										/* third layer 											*/
		
		//Stuff some files (just cause)
		for(int i=0; i<10; i++) {
			
			//Locals
			String path;
			File f, dir;
			
			//Init
			dir = d[i];
			
			//Gen
			path = dir.getAbsolutePath() + "\\" + "empty.txt";
			f = new File(path);

			//Create
			f.getParentFile().mkdirs(); 
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();										/* @todo Auto-generated catch block						*/
			}
		}
		
		//Confirm
		boolean pass = true;
		for(int i=0; i<10; i++) {
			if(!d[i].exists()) {
				System.out.println("["+i+"]: fail");
				pass = false;
			} else {
				System.out.println("["+i+"]: pass");
			}
		}
				
		//Confirm result
		result = (pass) ? "pass":"fail";		
		System.out.println("Test Result: " + result);
				
		return;
	}	
}


