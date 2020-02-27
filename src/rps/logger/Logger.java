package rps.logger;

/**
 * Interface for the Logger with 3 log levels
 * @author Sanket
 *
 */
public interface Logger {

	/**
	 * Prints the log with level Debug
	 */
	public static final int DEBUG = 3;
	/**
	 * Print the log with level Info
	 */
	public static final int INFO = 2;
	/**
	 * Prints the log with level Error
	 */
	public static final int ERROR = 1;
	
	/**
	 * 
	 * @param level Log level
	 * @param message Message to be printed in the log
	 */
	void log(int level, Object message);
}
