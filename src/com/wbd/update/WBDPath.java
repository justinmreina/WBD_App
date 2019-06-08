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

public class WBDPath {

	/********************************************************************************************************************************/
	/**	@fcn		public void getAllDirs(String rootPath)
	 *  @brief		Get Directory Listing under rootPath
	 *  @details	x
	 *
	 *  @param		[in] (String) root - root directory
	 *  
	 *  @open		File input?
	 *  
	 *  @assum 		rootPath is found
	 */
	/********************************************************************************************************************************/
	public String[] getAllDirs(String rootPath) {
		
		//Locals
		String[] vals = {"",""};
		
		
		//Init
		File root = new File(rootPath);
		
		
		
		
		
		
		
		//Post
		System.out.println("A");
		System.out.println(root.exists());
			
		vals[0] = "Hi";
		vals[1] = "Bob";
		
		return vals;
	}

}
