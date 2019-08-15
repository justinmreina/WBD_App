/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer
 * 	@created	2/8/19
 * 	@last rev	6/12/19
 *
 *
 * 	@notes		observed approximate 45 second search time
 *
 *	@section 	Next Steps
 *		displayAll() stores all existing WBD and returns result
 *
 * 	@section	Opens
 * 		addToday() - Check for today's dir and insert if not found
 * 		cleanEmpties() - Check for empties & remove
 * 		
 * 	@section	Legal Disclaimer
 * 			2019© Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Company Name. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.update;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WBDUpdate {

	//Constants	
	public static final String SEARCH_DIR = "D:\\";							/* search directory name								*/
	public static final String TEST_DIR   =  "data\\TestDirs"; 				/* test directory to search for WBD locations			*/
	public static final String WBD_NAME   = "WorkByDay";					/* directory name										*/

	//Global Variables
	public static List<File> wbd_directories;								/* dirs holding work-by-day content (e.g. '19_06_05/')	*/
	public static String searchDir;											/* search directory for application						*/
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void main(String[] args)
	 *  @brief		Application entry point
	 *  @details	x
	 *
	 *  @param		[in] (String[]) args - 
	 */
	/********************************************************************************************************************************/
	public static void main(String[] args) {

		//Locals
		Date date1, date2;													/* dates found for time measurement						*/
		String searchDirs[];												/* wbd dirs found										*/
	
		//Init
		wbd_directories = new ArrayList<>();
		searchDir = TEST_DIR;												/* App: SEARCH_DIR, Dev: TEST_DIR						*/
		
		//Grab Start Time
		date1 = new Date();
				
		//**************************************************************************************************************************//
		//															SEARCH															//
		//**************************************************************************************************************************//	
		System.out.println(">>Beginning search query for WorkByDay -");

		//Find all WBD in target
		searchDirs = WBDPath.getAllWbdDirs(searchDir);		
				
		//Update Dir Counts
		WBDPath.wbdcount = searchDirs.length;
		WBDPath.count = WBDPath.getAllDirs(searchDir).length;
		
		//**************************************************************************************************************************//
		//															CLEAN EMPTIES															//
		//**************************************************************************************************************************//
		
		//For each WBD
		for(String name : searchDirs) {
			
			//Grab the WBD Dir
			File dir = new File(name);
			
			//Remove empty dirs at root
			WBDPath.cleanEmpties(dir);
			
			//Add today's dir
			WBDPath.addToday(dir);
			
			System.out.println("  Updated  " + dir.getPath());
		}
		
		//**************************************************************************************************************************//
		//															 END															//
		//**************************************************************************************************************************//

		//Find elapsed time
		date2 = new Date();
		long delta_ms = date2.getTime() - date1.getTime();
		
		float time_s = (((float)delta_ms)/1000);

		//Exit Message
		System.out.println("\n>>WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + "). Elapsed time: " + time_s + "s");
		
		return;
	}


	/********************************************************************************************************************************/
	/**	@fcn		public static void processDir(Path wbd_path)
	 *  @brief		x
	 *  @details	x
	 *
	 *  @param		[in] (Path) wbd_dir - WorkByDay directory
	 *  
	 *  @assum		wbd_dir is valid WorkByDay directory
	 */
	/********************************************************************************************************************************/
	public static void processDir(File wbd_dir) {
		System.out.println("Processing: " + wbd_dir.getName());
		return;
	}
	
}

