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
	public static String[] toppings = {"Cheese", "Pepperoni", "Black Olives"};
	
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
		toppings[0] = "Justin";
		toppings[1] = "likes";
		toppings[2] = "cheese";
		
		// Store the formatted string in 'result'
		String message = String.format("%s %s %s.", toppings[0], toppings[1], toppings[2]);

		// Write the result to standard output
		System.out.println(message);
		
		System.out.println("WBDUpate complete.");
		
		return;
	}

}
