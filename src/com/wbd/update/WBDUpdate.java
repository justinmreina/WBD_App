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
import java.util.ArrayList;
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

		
		//**************************************************************************************************************************//
		//															INIT															//
		//**************************************************************************************************************************//
		wbd_directories = new ArrayList<>();

		System.out.println(">>Beginning search query for WorkByDay(" + WBDPath.wbdcount + "/" + WBDPath.count + ") -");

		WBDPath.getAllDirs("D:\\");		
		
		System.out.println(">>WBDUpate complete(" + WBDPath.wbdcount + "\\" + WBDPath.count + ").");		
		
		return;
	}
}

