/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer, Company Name
 * 	@created	2/8/19
 * 	@last rev	6/7/19
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


public class WBDPath {

	public static int count = 0;
	public static int wbdcount = 0;

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
		String[] vals = {"",""};
		File root;
		
		//Init
		root = new File(rootPath);

		//Search
		displayAll(root);
		
		return vals;
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

}
