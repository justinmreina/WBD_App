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
 * 	@last rev	2/8/19
 *
 *
 * 	@notes		x
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

	public static String WBD_NAME = "WorkByDay";
	
	//Global Variables
	public static String[] directories = {
										  "data\\TestA", 
										  "data\\TestB", 
										  "data\\TestC"};
	
	public static List<File> wbd_directories;
	
	/************************************************************************************************************************************/
	/**	@fcn		public static void main(String[] args)
	 *  @brief		Application entry point
	 *  @details	x
	 *
	 *  @param		[in] (String[]) args - 
	 */
	/************************************************************************************************************************************/
	public static void main(String[] args) {

		//Init
		wbd_directories = new ArrayList<>();
		
		//Find Wbd
		for(int i=0; i<directories.length; i++) {
			WBDUpdate.listDir(directories[i]);
		}
		
		//Print Results
		for(int i=0; i<wbd_directories.size(); i++) {
			System.out.print("[");
			System.out.print(i);
			System.out.print("]: ");
			System.out.println(wbd_directories.get(i));
			
		}
		
		System.out.println("WBDUpate complete.");		
		
		return;
	}
	
	
	/************************************************************************************************************************************/
	/**	@fcn		public static void main(String[] args)
	 *  @brief		Application entry point
	 *  @details	x
	 *
	 *  @param		[in] (String) path - 
	 */
	/************************************************************************************************************************************/
	public static void listDir(String path) {

//		System.out.print("Dir Listing for - ");
//		System.out.print(path);
//		System.out.println(".");
		
		//Search dir
		File dir = new File(path);
		
		File[] filesList = dir.listFiles();
		
		for (File file : filesList) {
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
