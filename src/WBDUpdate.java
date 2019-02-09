import java.io.File;

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
 * 			none current
 *
 * 	@section	Legal Disclaimer
 * 			© Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Company Name. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
public class WBDUpdate {

	//Global Variables
	public static String[] directories = {
										  "data\\TestA", 
										  "data\\TestB", 
										  "data\\TestC"};
	
	
	/************************************************************************************************************************************/
	/**	@fcn		public static void main(String[] args)
	 *  @brief		Application entry point
	 *  @details	x
	 *
	 *  @param		[in] (String[]) args - 
	 */
	/************************************************************************************************************************************/
	public static void main(String[] args) {

		for(int i=0; i<directories.length; i++) {
			WBDUpdate.listDir(directories[i]);
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

		System.out.print("Dir Listing for - ");
		System.out.print(path);
		System.out.println(".");
		
		//Search dir
		File dir = new File(path);
		
		File[] filesList = dir.listFiles();
		
		for (File file : filesList) {
		    if (file.isFile()) {
		    	System.out.print(" - ");
		        System.out.println(file.getName());
		    } else {
		    	System.out.print("Here we will list the contents of ");
		    	System.out.print(file.getName());
		    	System.out.println(".");
		    	
		    	WBDUpdate.listDir(file.getAbsolutePath());
		    	
		    	System.out.println("Subdir listing complete");
		    }
		}
		
		System.out.println("Dir listing complete.");
		
		return;
	}
	
	
		

}
