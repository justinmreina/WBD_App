/************************************************************************************************************************************/
/** @file		App.java
 * 	@brief		WorkByDay check and update for directories
 * 	@details	x
 *
 * 	@author		Justin Reina, Firmware Engineer
 * 	@created	6/24/21
 * 	@last rev	6/24/21
 *
 *
 * 	@section 	Performance
 * 		� ?
 *
 * 	@section	Opens
 * 		� Working, start small
 * 
 * 	@section	Legal Disclaimer
 * 		2021� Year Company Name, All rights reserved. All contents of this source file and/or any other related source  
 *		files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package wbd;

import com.wbd.file.FileSys;

public class App {

	//Constants	

	//Global Variables

	
	/********************************************************************************************************************************/
	/**	@fcn		public static void main(String[] args)
	 *  @brief		Application entry point
	 *  @details	x
	 *
	 *	@section 	Opens
	 *		Dir.create()
	 *		Dir.list()
	 *		File.exists()
	 *  @param		[in] (String[]) args - executable arguments from command line
	 */
	/********************************************************************************************************************************/
	public static void main(String[] args) {

		//Locals
		
		//Init & Stamp

		//Setup

		
		//**************************************************************************************************************************//
		//															SEARCH															//
		// @brief 		x																											/
		//**************************************************************************************************************************//	
		System.out.println(">>Beginning search query for WorkByDay -");


		//**************************************************************************************************************************//
		//														 CLEAN EMPTIES														//
		// @brief 		x																											//
		//**************************************************************************************************************************//
		FileSys.test();
		
		//**************************************************************************************************************************//
		//														  ADD TODAY														  	//
		//**************************************************************************************************************************//
		
		
		//**************************************************************************************************************************//
		//															 END															//
		//**************************************************************************************************************************//

		//Find elapsed time
		
		//Calc
		System.out.print("\n  Hello, World!\n");

		//Exit Message
		System.out.println("\n>>WBDUpate complete()");
		
		return;
	}
}

