package com.mte.util;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class logutil {
	
	private static FileUtil fileUtil = null;
	final static String prop_path = "lib//log4j.properties";
	
	static Logger logger = Logger.getLogger(logutil.class);
	
	public static void logInfo(String s){
		PropertyConfigurator.configure(prop_path);
		logger.info(s);
	}
	
	public static void logInfo(int i){
		PropertyConfigurator.configure(prop_path);
		logger.info(String.valueOf(i));
	}
	
	public static void logInfo(Double d){
		PropertyConfigurator.configure(prop_path);
		logger.info(String.valueOf(d));
	}
	
    public static void main(String[] args) {
      // BasicConfigurator replaced with PropertyConfigurator.
 //     PropertyConfigurator.configure(args[0]);
 //     PropertyConfigurator.configure(prop_path);
 //     logger.info("Entering application.");

  //    logger.info("Exiting application.");

    }

}
