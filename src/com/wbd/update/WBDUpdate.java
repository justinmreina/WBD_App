/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer
 * 	@created	2/8/19
 * 	@last rev	9/4/19
 *
 *
 * 	@section 	Performance
 * 		• Observed approximate 45 second search time
 * 		• Content located in administrator's locations (e.g. 'C:\Program Files' must be handled in supervisor mode
 *
 *  @section 	Deferred Opens
 * 		• Inject empty dirs on test boot
 * 		• Clean File/String mashup for use
 * 		
 * 	@section	Legal Disclaimer
 * 			2019© Justin Reina, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.update;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.wbd.update.Log;

public class WBDUpdate {

	//Constants	
	public static final String SEARCH_DIR = "D:\\";							/* search directory name								*/
	public static final String TEST_DIR   = "data\\TestDirs"; 				/* test directory to search for WBD locations			*/
	
	public static final String WBD_NAME   = "WorkByDay";					/* directory name										*/

	//Global Variables
	public static Log log;													/* log file operations									*/
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
		log = new Log();
		searchDir = SEARCH_DIR;												/* App: SEARCH_DIR, Dev: TEST_DIR						*/

		//Grab Start Time
		date1 = new Date();

		//Setup
		WBDPath.setupTest(searchDir);

		
		//**************************************************************************************************************************//
		//															SEARCH															//
		//**************************************************************************************************************************//	
		Log.info("Beginning search query for WorkByDay -");

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
		//														  ADD TODAY														  	//
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
		Log.info("WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + "). Elapsed time: " + time_s + "m");

		return;
	}
}

