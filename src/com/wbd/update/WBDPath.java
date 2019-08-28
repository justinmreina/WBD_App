/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer, Company Name
 * 	@created	2/8/19
 * 	@last rev	8/28/19
 *
 *
 * 	@notes		observed approximate 45 second search time
 *
 * 	@section	Opens
 * 			public vs. private variables?
 * 			sprintf...
 *
 * 	@section	Legal Disclaimer
 * 			© Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Company Name. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.update;
import java.io.File;
import java.util.LinkedList;


public class WBDPath {

	public static int count = 0;
	public static int wbdcount = 0;

	/********************************************************************************************************************************/
	/**	@fcn		public void getAllWbdDirs(String rootPath)
	 *  @brief		Get 'WorkByDay' Directories under rootPath
	 *  @details	x
	 *
	 *  @param		[in] (String) root - root directory
	 *  
	 *  @open		File input?
	 *  
	 *  @assum 		rootPath is found
	 */
	/********************************************************************************************************************************/
	public static String[] getAllWbdDirs(String rootPath) {
		
		//Locals
		String[] allDirs;
		LinkedList<String> wbdDirs;
		
		//Init
		wbdDirs = new LinkedList<String>();
		
		//Retrieve all directories
		allDirs = WBDPath.getAllDirs(rootPath);
		
		//Parse wbd
		for(String dir : allDirs) {

			String[] array = dir.split("[\\\\/]",-1) ;
			String dirName = array[array.length-1];

			if(dirName.equals("WorkByDay")) {
				wbdDirs.add(dir);
			}			
		}
		
		return wbdDirs.toArray(new String[wbdDirs.size()]);
	}
	
	/********************************************************************************************************************************/
	/**	@fcn		public void getAllDirs(String rootPath)
	 *  @brief		Get Directory Listing under rootPath
	 *  @details	x
	 *
	 *  @param		[in] (String) root - root directory
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
	/**	@fcn		public void displayAll(File path)
	 *  @brief		x
	 *  @details	x
	 *
	 *  @param		[in] 
	 */
	/********************************************************************************************************************************/	
	public static void displayAll(File path) {   
		
		//For each directory, recurse
	    if(path.isDirectory()) {

	    	//Get Directories
	    	File files[] = path.listFiles();
	        
	        //Safety
	        if(files == null) {
	        	return;														/* return on invalid content							*/
	        }
	        
	        //For each item
	        for(File dirOrFile: files) {	        	
	            displayAll(dirOrFile);
	        }
	        
	    }
	    
	    //Process Dir
	    WBDPath.count++;    
	    String name = path.getName();
	    
	    //Handle WBD directories
	    if(name.equals("WorkByDay")) {	   
	    	WBDPath.wbdcount++;												/* Update Count											*/
	    	WBDUpdate.processDir(path);										/* Process the WorkByDay folder							*/
	    }
	    
	    return;
	}

	
	/********************************************************************************************************************************/
	/**	@fcn		public static void cleanEmpties(File path)
	 *  @brief		Remove empty dirs at root
	 *  @details	x
	 *
	 *  @param		[in] 
	 */
	/********************************************************************************************************************************/	
	public static void cleanEmpties(File path) {
		
		return;
	}

	
	/********************************************************************************************************************************/
	/**	@fcn		public static void addToday(File path) 
	 *  @brief		Add today's dir
	 *  @details	x
	 *
	 *  @param		[in] 
	 */
	/********************************************************************************************************************************/	
	public static void addToday(File path) { 
		
		return;
	}
}

