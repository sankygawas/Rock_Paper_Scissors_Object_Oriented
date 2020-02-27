package rps.logger;

import java.util.Date;

/**
 * This class implements the Logger Interface. Provides a global configuration for the logger
 * @author Sanket
 *
 */
public class PrintLogger implements Logger {
	
	/**
	 * Config level which decides the global level of logging
	 */
	int globalLoggerLevel = 4;

	/** This method prints the log, It will print the message only if the log level is below or equal to the global log level 
	 * @param level The log level of the log statement
	 * @param message The message to be printed in the log
	 */
	@Override
	public void log(int level, Object message) {

		
		StringBuffer msg = new StringBuffer();
		switch(level) {
			case Logger.DEBUG:
				msg.append("[DEBUG]");
				break;
			case Logger.INFO:
				msg.append("[INFO] ");
				break;
			case Logger.ERROR:
				msg.append("[ERROR]");
				break;
			default:
				msg.append("Unkown log level Type");
				break;
		}
		msg.append(new Date());
		if(level <= globalLoggerLevel)
			System.out.println(msg.append(" " + message));
	}

}
