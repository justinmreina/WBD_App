/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer
 * 	@created	2/8/19
 * 	@last rev	8/28/19
 *
 *
 * 	@notes		observed approximate 45 second search time
 *
 * 	@section	Opens
 * 		run from batch file
 * 		run from scheduled windows task
 * 
 *  @section 	Deferred Opens
 * 		inject empty dirs on test boot
 * 		clean File/String mashup for use
 * 		only deletes dirs if valid folder name (wbd path)
 * 		
 * 	@section	Legal Disclaimer
 * 			2019© Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Justin Reina. Do not distribute. Do not copy.
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
	 *  @param		[in] (String[]) args - executable arguments from command line
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

		//Setup
		WBDPath.setupTest(searchDir);

		
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
		//														 CLEAN EMPTIES														//
		//**************************************************************************************************************************//

		//Grab Start Time
		date1 = new Date();

		//Clean
		for(String dir : searchDirs) {
			WBDPath.cleanEmpties(new File(dir));							/* For each WBD, check for empties at root				*/
		}
		
		
		//**************************************************************************************************************************//
		//															ADD TODAY														//
		//**************************************************************************************************************************//
		
		//Add today
		for(String dir : searchDirs) {
			WBDPath.addToday(new File(dir)); 								/* add today if not found								*/
		}
		
		
		//**************************************************************************************************************************//
		//															 END															//
		//**************************************************************************************************************************//

		//Find elapsed time
		date2 = new Date();
		
		//Calc
		long delta_ms = date2.getTime() - date1.getTime();
		float time_s = (((float)delta_ms )/1000);

		//Exit Message
		System.out.println("\n>>WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + "). Elapsed time: " + time_s + "s");
		
		return;
	}
}

