/************************************************************************************************************************************/
/** @file		Log.java
 * 	@brief		Log to file and console
 * 	@details	x
 *
 * 	@section	Opens
 * 		• Shares specific location of occurrence (e.g. 'WBDUpdate.fcn()' not 'Log.info()')
 * 
 * 	@section	Legal Disclaimer
 * 			2019© Justin Reina, All rights reserved. All contents of this source file and/or any other related source  
 *			files are the explicit property of Justin Reina. Do not distribute. Do not copy.
 */
/************************************************************************************************************************************/
package com.wbd.update;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {

	//Locals
	public static FileHandler fh;
	public static Logger LOGGER;
	
	//Constants
	public static final String LOGFILE_NAME = "WBDLog.xml";
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public Log()
	 *  @brief		Log constructor
	 *  @details	x
	 */
	/********************************************************************************************************************************/
	public Log() {
		
		//Init
		LOGGER = Logger.getLogger(WBDUpdate.class.getName());

		//Config
		try {
			fh = new FileHandler(LOGFILE_NAME);
	        LOGGER.addHandler(fh);
			Log.info("Logger is initialized, ready for use.");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  


		return;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void info(String msg)
	 *  @brief		Print info message to console and log
	 *  @details	x
	 *
	 *  @param		[in] (String) msg - log message to store and print
	 *  
	 *  @assum 		log is initialized
	 */
	/********************************************************************************************************************************/
	public static void info(String msg) {
		
		//Print to log
		LOGGER.info(msg);

		return;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void warning(String msg)
	 *  @brief		Print warning message to console and log
	 *  @details	x
	 *
	 *  @param		[in] (String) msg - log message to store and print
	 *  
	 *  @assum 		log is initialized
	 */
	/********************************************************************************************************************************/
	public void warning(String msg) {

		//Print to log
		LOGGER.warning(msg);

		return;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public static void error(String msg)
	 *  @brief		Print error message to console and log
	 *  @details	x
	 *
	 *  @param		[in] (String) msg - log message to store and print
	 *  
	 *  @assum 		log is initialized
	 */
	/********************************************************************************************************************************/
	public void error(String msg) {

		//Print to log
		LOGGER.info(msg);
		
		return;
	}
	
	
	/********************************************************************************************************************************/
	/**	@fcn		public void demo()
	 *  @brief		Log application demo
	 *  @details	x
	 */
	/********************************************************************************************************************************/
	public void demo() {

		LOGGER.info("Logger Name: "+LOGGER.getName() + "C");         
        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
         
        //An array of size 3
        int []a = {1,2,3};
        int index = 4;
        LOGGER.config("3index is set to "+index);
         
        try{
            System.out.println(a[index]);
        }catch(ArrayIndexOutOfBoundsException ex){
            LOGGER.log(Level.SEVERE, "Exception occur", ex);
        }
        
        return;
	}
}

