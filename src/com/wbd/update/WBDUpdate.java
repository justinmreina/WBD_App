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
 * 		Check for empties & remove
 * 		Check for today's dir and insert if not found
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
	public static final String WBD_NAME   = "WorkByDay";					/* directory name										*/
	public static final String TEST_DIR   =  "data\\TestB"; 				/* test directory to search for WBD locations			*/

	//Global Variables
	public static List<File> wbd_directories;								/* dirs holding work-by-day content (e.g. '19_06_05/')	*/
	
	
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

		//Grab Start Time
		date1 = new Date();
				
		//**************************************************************************************************************************//
		//															SEARCH															//
		//**************************************************************************************************************************//	
		System.out.println(">>Beginning search query for WorkByDay -");

		//Find 
		searchDirs = WBDPath.getAllDirs(SEARCH_DIR);		
		

		//**************************************************************************************************************************//
		//															CLEAN EMPTIES															//
		//**************************************************************************************************************************//
		
		//For each WBD
		for(String name : searchDirs) {
			
			File dir = new File(name);
			
			System.out.println(name + dir.exists());			
		}
		
		
		//**************************************************************************************************************************//
		//															 END															//
		//**************************************************************************************************************************//
		System.out.println(">>WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + ").");		

		//Find elapsed time
		date2 = new Date();
		long delta_ms = date2.getTime() - date1.getTime();
		
		float time_s = (((float)delta_ms)/1000);
		
		System.out.println("Elapsed: " + time_s + "s");
		
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

