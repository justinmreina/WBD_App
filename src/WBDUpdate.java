import java.io.File;
import java.util.ArrayList;
import java.util.List;

/************************************************************************************************************************************/
/** @file		WBDUpdate.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer, Company Name
 * 	@created	2/8/19
 * 	@last rev	6/5/19
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

		System.out.println("Beginning search query for WorkByDay -");

		
		//**************************************************************************************************************************//
		//													  FIND WBD LOCATIONS													//
		//**************************************************************************************************************************//
		//Find Wbd
		WBDUpdate.listDir(testDir);
		
		System.out.println("Search query complete");
		
		//Print Results
		printResults();

		//**************************************************************************************************************************//
		//												   CLEAN EMPTY DIRECTORIES													//
		//**************************************************************************************************************************//
		//Clean empty days
		cleanEmptyDirs();

		
		//**************************************************************************************************************************//
		//													  ADD TODAY DIRECTORY													//
		//**************************************************************************************************************************//
		//Check for Active Day, add if missing
		updateActiveDir();
		
		System.out.println("WBDUpate complete.");		
		
		return;
	}

	/********************************************************************************************************************************/
	/**	@fcn		private static void updateActiveDir()
	 *  @brief		x
	 *  @details	x
	 */
	/********************************************************************************************************************************/
	private static void updateActiveDir() {
		return;
	}

	
	/********************************************************************************************************************************/
	/**	@fcn		private static void cleanEmptyDirs()
	 *  @brief		x
	 *  @details	x
	 */
	/********************************************************************************************************************************/
	private static void cleanEmptyDirs() {
		return;
	}


	/********************************************************************************************************************************/
	/**	@fcn		public static void printResults()
	 *  @brief		Print results found
	 *  @details	x
	 */
	/********************************************************************************************************************************/
	public static void printResults() {
		
		for(int i=0; i<wbd_directories.size(); i++) {
			System.out.print("[");
			System.out.print(i);
			System.out.print("]: ");
			System.out.println(wbd_directories.get(i));	
		}

		return;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void listDir(String path)
	 *  @brief		x
	 *  @details	x
	 *
	 *  @param		[in] (String) path - 
	 */
	/********************************************************************************************************************************/
	public static void listDir(String path) {

//		System.out.print("Dir Listing for - ");
//		System.out.print(path);
//		System.out.println(".");
		
		//Search dir
		File dir = new File(path);
		
		File[] filesList = dir.listFiles();
		int i = 0;
		
		//Safety
		if(filesList == null) {
			return;
		}
		
		for (File file : filesList) {
			
//			System.out.print(i++);
//			System.out.print(" ");
//			System.out.println(file.getName());
			
		    if (file.isDirectory()) {
		    	String name = file.getName();
//		    	System.out.print("found: ");
//		    	System.out.println(name);
		    	Boolean matches = name.equals(WBD_NAME);
		    	
		    	if(matches) {
//		    		System.out.println("WorkByDay was found.");
		    		wbd_directories.add(file);
		 
		    	} else {
//		    		System.out.println("WorkByDay was not found.");
		    	}
		    	
		    	WBDUpdate.listDir(file.getAbsolutePath());
		    }
		}
		
//		System.out.println("Dir listing complete.");
		
		return;
	}
}

