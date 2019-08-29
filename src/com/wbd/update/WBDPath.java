/************************************************************************************************************************************/
/** @file		WBDPath.java
 * 	@brief		WorkByDay Path content
 * 	@details	Description, use and management
 *
 * 	@section	Opens
 * 			none listed
 *
 * 	@section	Legal Disclaimer
 * 			2019� Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.update;
import java.io.File;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;


public class WBDPath {

	//Constants	
	public static final int MONTHS_IN_YEAR = 12;
	public static final int DAYS_IN_MONTH  = 31;

	//Global Variables
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
		
		//Get subdirs
		LinkedList<String> dirs = getSubDirs(path, false);
		
		for(String dir : dirs) {
			
			File dirPath = new File(dir);
			
			//If empty, toss
			if(isDirEmpty(dirPath)) {
				System.out.println(" " + dirPath.getName() + " was deleted.");
				dirPath.delete();
			}
		}
		
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
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static String[] daysofYear(int year)
	 *  @brief		Get listing of all possible days for the year
	 *  @details	x
	 *
	 *	@param 		[in] (int) year - year for name specification
	 *
	 *  @return 	(String[]) days of the year describing folder names
	 *  
	 *  @note 		naming not exact, days not exact but provide full coverage
	 */
	/********************************************************************************************************************************/	
	public static String[] daysofYear(int year) { 
		
		//Locals
		String[] days;
		
		//Init
		days = new String[(MONTHS_IN_YEAR*DAYS_IN_MONTH)];					/* get listing for all possible month & day values		*/

		//Trim year to last two digits
		year %= 100;
		
		//For each day
		for(int i=0; i<(MONTHS_IN_YEAR*DAYS_IN_MONTH); i++) {
			
			//Calc
			int month = (i/DAYS_IN_MONTH)+1;
			int day = i-((month-1)*DAYS_IN_MONTH)+1;
			
			days[i] = String.format("%02d_%02d_%02d", year, month, day);
		}
		
		return days;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static boolean isDirEmpty(File dir)
	 *  @brief		check if directory is empty
	 *  @details	x
	 *
	 *  @param		[in] (File) dir - x
	 *  
	 *  @return 	(boolean) is empty?
	 */
	/********************************************************************************************************************************/	
	public static boolean isDirEmpty(File dir) { 
		
		//Locals
		boolean isDir;
		boolean hasContents;
		boolean isEmpty;
		
		isDir = dir.isDirectory();
		hasContents = dir.list().length != 0;
		
		isEmpty = !(isDir&&hasContents);
				
		return isEmpty;
	}


	/********************************************************************************************************************************/
	/**	@fcn		public static boolean isDirEmpty(File dir)
	 *  @brief		check if directory is empty
	 *  @details	x
	 *
	 *  @param		[in] (File) dir - x
	 *  
	 *  @section 	Opens
	 *  	complete routine
	 */
	/********************************************************************************************************************************/	
	public static void setupTest(String dir) { 
		
		//Test Check
		if(dir != WBDUpdate.TEST_DIR) {
			return;															/* only setup for test on TEST_DIR case					*/
		}

		//Inject a handful of empty directories with valid syntax
				
		return;
	}
	

	/********************************************************************************************************************************/
	/**	@fcn		public static String getToday()
	 *  @brief		get wbd dir name for today
	 *  @details	x
	 *
	 *  @return 	(String) today's path (e.g. "19_08_28" for 8/28/19)
	 *  
	 *  @section 	Opens
	 *  	complete routine
	 *  
	 *  @assum		US-Pacific location
	 */
	/********************************************************************************************************************************/	
	public static String getToday() { 
		
		//Locals
		ZoneId zonedId;
		ZonedDateTime zdt;
		Month month;
		int m, d, y;

		//Init
		zonedId = ZoneId.of("US/Pacific");
		zdt = ZonedDateTime.now(zonedId);

		//Parse
		month = zdt.getMonth();
		
		//Parse Month
		switch(month) {
			case JANUARY:
				m = 1;
				break;
			case FEBRUARY:
				m = 2;
				break;
			case MARCH:
				m = 3;
				break;
			case APRIL:
				m = 4;
				break;
			case MAY:
				m = 5;
				break;
			case JUNE:
				m = 6;
				break;
			case JULY:
				m = 7;
				break;
			case AUGUST:
				m = 8;
				break;
			case SEPTEMBER:
				m = 9;
				break;
			case OCTOBER:
				m = 10;
				break;
			case NOVEMBER:
				m = 11;
				break;
			case DECEMBER:
				m = 12;
				break;		
			default:
				m = 0;
		}
		
		//Parse Day & Year
		d = zdt.getDayOfMonth();
		y = zdt.getYear();
		
		//Trim Year to two digits
		y %= 100;
		
		//Form String
		String name = String.format("%02d_%02d_%02d", y, m, d);
		
		return name;
	}
}

