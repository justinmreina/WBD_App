/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer
 * 	@created	2/8/19
 * 	@last rev	6/8/19
 *
 *
 * 	@notes		observed approximate 45 second search time
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
	
	public static final String WBD_NAME = "WorkByDay";						/* directory name										*/
	public static final String testDir =  "data\\TestB"; 					/* test directory to search for WBD locations			*/

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
		Date date1, date2;

		
		//Grab Start Time
		date1 = new Date();
				
		//**************************************************************************************************************************//
		//															INIT															//
		//**************************************************************************************************************************//
		wbd_directories = new ArrayList<>();

		System.out.println(">>Beginning search query for WorkByDay -");

		WBDPath.getAllDirs("D:\\");		
		
		System.out.println(">>WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + ").");		

		//Find elapsed time
		date2 = new Date();
		long delta_ms = date2.getTime() - date1.getTime();
		
		float time_s = (((float)delta_ms)/1000);
		
		System.out.println("Elapsed: " + time_s + "s");
		
		return;
	}
}

