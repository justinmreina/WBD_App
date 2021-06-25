/************************************************************************************************************************************/
/** @file		Dir.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@section	Opens
 * 		• none listed
 * 		
 * 	@section	Legal Disclaimer
 * 		2021© Justin Reina, All rights reserved. All contents of this source file and/or any other related source  
 *		files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.file;

import java.io.File;

public class Dir {

	
	/********************************************************************************************************************************/
	/**	@fcn		public static File create(String name)
	 *  @brief		Create a new filesystem directory
	 *  @details	x
	 *
	 *  @param		[in] (String) name - name for new directory
	 *  
	 *  @param 		[out] (File) output directory created
	 *  
	 *  @section 	Opens
	 *  	get working (currently just plops at proj root)
	 *  
	 *	@section 	Reference
	 *		1. www.tutorialspoint.com/how-to-create-a-new-directory-by-using-file-object-in-java
	 */
	/********************************************************************************************************************************/	
	public static File create(File loc, String name) {
		
		//Locals
		boolean bool;
		String path;
		File dir;															/* new directory for creation							*/ 
	
		
		//Create full path
		path = loc.getPath() + name;	
		dir = new File(path);		

		//(Pre) Check if exists
		if (dir.exists()){
			System.out.println("Directory exists, aborting.");
			return new File("");
		}
		
		//Create
		bool = dir.mkdirs();

		//Check
		if(bool){
			System.out.println("Directory created successfully");
		}else{
			System.out.println("Sorry couldn’t create specified directory");	
		}
	
		return dir;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void test()
	 *  @brief		Class validation test
	 *  @details	x
	 */
	/********************************************************************************************************************************/	
	public static void test() {

		//Locals
		String result;
		File loc;
		File d;
		
		
		loc = new File("D:\\");
		
		//Create a dir
		d = Dir.create(loc, "demoTest");
		
		//Check it is there
		if (d.exists()){
			result = "pass";
		} else {
			result = "fail";
		}

		//Confirm result
		System.out.println("Test Result: " + result);

				
		return;
	}	
}
