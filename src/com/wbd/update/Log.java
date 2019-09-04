//@todo 	header
//@open 	shares specific location of occurence
package com.wbd.update;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {

	//Locals
	public static FileHandler fh;
	public static Logger LOGGER;
	
	//@todo 	header
	public Log() {
		System.out.print("Hi,");
		
		//Init
		LOGGER = Logger.getLogger(WBDUpdate.class.getName());

		try {
			fh = new FileHandler("WBDLog.xml");
	        LOGGER.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  

		System.out.println(" logger is ready.");

		return;
	}
	
	//@todo 	header
	public void info(String msg) {
		LOGGER.info(">I: " + msg);
		System.out.println(">I: " + msg);
		return;
	}
	
	//@todo 	header
	public void warning(String msg) {
		LOGGER.warning(">W: " + msg);
		System.out.println(">W: " + msg);
		return;
	}
	
	//@todo 	header
	public void error(String msg) {
		LOGGER.info(">E: " + msg);
		System.out.println(">E: " + msg);
		return;
	}
	
	//@todo 	header
	public void demo() {

		LOGGER.info("1Logger Name: "+LOGGER.getName() + "C");         
        LOGGER.warning("2Can cause ArrayIndexOutOfBoundsException");
         
        //An array of size 3
        int []a = {1,2,3};
        int index = 4;
        LOGGER.config("3index is set to "+index);
         
        try{
            System.out.println(a[index]);
        }catch(ArrayIndexOutOfBoundsException ex){
            LOGGER.log(Level.SEVERE, "4Exception occur", ex);
        }
        
        return;
	}
}

